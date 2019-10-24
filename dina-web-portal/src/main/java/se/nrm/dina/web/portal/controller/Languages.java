/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;  
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@SessionScoped
@Named
@Slf4j
public class Languages implements Serializable {

  private String locale = "sv";
  private final List<String> updateList; 
  private HttpSession session;  
  
  @Inject
  private ChartView chart;
 
  @Inject
  private StyleBean style;

  public Languages() {
    log.info("Languages");
    
    updateList = new ArrayList<>();
    updateList.add("headerForm:header");
    updateList.add("topMenuForm:topmenupanel");
    updateList.add("searchForm:searchPanel");
    updateList.add("footerPanel"); 
    updateList.add("mainPanel"); 
     
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    session.setAttribute(CommonText.getInstance().getLocale(), locale);
  }

  public String getLocale() {
    return locale;
  }

  public void changeLanguage(String locale) { 
    log.info("changeLanguage - locale: {}", locale); 
    
    style.setLanguageStyle(locale);
    this.locale = locale;
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    session.setAttribute(CommonText.getInstance().getLocale(), locale);
     
    chart.changeLanguage(isSwedish());
    HelpClass.getInstance().updateView(updateList);
  }

  public String getLanguage() {
    return locale.equals("sv") ? "English" : "Svenska";
  }

  public boolean isSwedish() {
    return locale.equals("sv");
  }
}
