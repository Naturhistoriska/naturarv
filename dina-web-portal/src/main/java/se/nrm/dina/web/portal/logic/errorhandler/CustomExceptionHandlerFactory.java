/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
