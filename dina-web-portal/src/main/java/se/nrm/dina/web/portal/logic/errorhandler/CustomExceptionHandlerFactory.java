package se.nrm.dina.web.portal.logic.errorhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author idali
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

  private ExceptionHandlerFactory exceptionHandlerFactory;

  public CustomExceptionHandlerFactory() {
  }

  public CustomExceptionHandlerFactory(ExceptionHandlerFactory exceptionHandlerFactory) {
    this.exceptionHandlerFactory = exceptionHandlerFactory;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {
    return new CustomExceptionHandler(exceptionHandlerFactory.getExceptionHandler());
  }
}
