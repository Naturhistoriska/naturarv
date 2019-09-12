/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.logic.mail.MailHandler;
import se.nrm.dina.web.portal.model.ErrorReport;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("error")
@Slf4j
public class ErrorReportBean implements Serializable {

  private ErrorReport errorReport; 
  private SolrData errorData;

  @Inject
  private Navigator navigator;
  @Inject 
  private MailHandler mail;
  
  
  public ErrorReportBean() {
    errorReport = new ErrorReport();
  }
  
  public void onBlur() {
    log.info("onBlur");
  }
  
  public void sendErrorReport() {
    log.info("sendErrorReport");
    mail.sendMail(errorData, errorReport, true);
    navigator.gotoResults();
  }

  public void validateEmail() {
    log.info("validateEmail");
  }
  
  public void reportError(SolrData errorData) {
    log.info("reportError: {}", errorData.getCatalogNumber());
    this.errorData = errorData;
    navigator.gotoErrorReportPage();
  }
   
  public SolrData getErrorData() {
    return errorData;
  }

  public ErrorReport getErrorReport() {
    return errorReport;
  }

  public void setErrorReport(ErrorReport errorReport) {
    this.errorReport = errorReport;
  }  
}
