/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.errorhandler;

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.Map;
import javax.faces.context.ExternalContext;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Slf4j
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

  private final ExceptionHandler exceptionHandler;
  private final String home = "/faces/pages/home.xhtml";

  public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  @Override
  public ExceptionHandler getWrapped() {
    return exceptionHandler;
  }

  @Override
  public void handle() throws FacesException {
    final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();

    while (queue.hasNext()) {
      ExceptionQueuedEvent item = queue.next();
      ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) item.getSource();

      try {
        Throwable throwable = exceptionQueuedEventContext.getException();
        log.error(throwable.getMessage());

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
        NavigationHandler nav = context.getApplication().getNavigationHandler();

        requestMap.put("error-message", throwable.getMessage());
        requestMap.put("error-stack", throwable.getStackTrace());
//        nav.handleNavigation(context, null, "/start.xhtml");

        context.renderResponse();

        ExternalContext externalContext = context.getExternalContext();
        try {
          externalContext.redirect(externalContext.getRequestContextPath() + home);
        } catch (IOException ex) {
        }

      } finally {
        queue.remove();
      }
    }
  }
}
