/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.IOException;  
import java.io.Serializable;
import javax.faces.context.FacesContext; 
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;



/**
 *
 * @author idali
 */
@Named("idle")
@ViewScoped
public class IdleMonitorController implements Serializable {

  public void idleListener() throws IOException {
    FacesContext ctx = FacesContext.getCurrentInstance();

    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
            .getExternalContext().getRequest();
    HttpSession session = request.getSession(false);
    session.invalidate();

    ctx.getExternalContext().redirect("/");
  }
}
