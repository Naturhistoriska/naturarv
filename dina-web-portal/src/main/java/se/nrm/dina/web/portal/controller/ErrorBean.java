/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;
  
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author idali
 */
@Named
@RequestScoped
public class ErrorBean {
  
  public void throwError() {
    throw new RuntimeException("throwing new error");
  }
}
