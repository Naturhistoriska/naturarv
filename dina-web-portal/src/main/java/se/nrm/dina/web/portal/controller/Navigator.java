package se.nrm.dina.web.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Named("navigate")
@SessionScoped
@Slf4j
public class Navigator implements Serializable {

  private ExternalContext externalContext;

  private static final String HOME_PATH = "/faces/pages/home.xhtml";
  private static final String COLLECTIONS_PATH = "/faces/pages/collections.xhtml";
  private static final String PARTNERS_PATH = "/faces/pages/partners.xhtml";
  private static final String FAQ_PATH = "/faces/pages/faq.xhtml";
  private static final String ABOUT_PATH = "/faces/pages/about.xhtml";
  private static final String CONTACT_PATH = "/faces/pages/contact.xhtml";

  private static final String RESULTS_PATH = "/faces/pages/results.xhtml";
  private static final String NO_RESULTS_PATH = "/faces/pages/noResult.xhtml";
  private static final String ERROR_REPORT_PATH = "/faces/pages/errorReport.xhtml";

  private final static String HOME = "home";
  private final static String COLLECTIONS = "collections";
  private final static String PARTNERS = "partners";
  private final static String FAQ = "faq";
  private final static String ABOUT = "about";
  private final static String CONTACT = "contact"; 
   
  private boolean isHomeView;
  
  private HttpServletRequest req;

  @Inject
  private StyleBean style;
 
  public Navigator() {
    log.info("Navigator");
    isHomeView = true;
  }
  
  public Navigator(StyleBean style) {
    this.style = style;
    isHomeView = true;
  }

  public void gotoHome() {
    log.info("gotoHome"); 
    style.setTabStyle(HOME);
    
    if(isResultView()) {
      isHomeView = true;
      redirectPage(HOME_PATH);
    } else {
      redirectPage(isHomeView ? HOME_PATH : RESULTS_PATH);
    }
    
  }

  public void gotoResults() {
    log.info("gotoResults");

    isHomeView = false;
    style.setTabStyle(HOME);
    redirectPage(RESULTS_PATH);
  }

  public void gotoNoResults() {
    log.info("gotoNoResults");

    style.setTabStyle(HOME);
    redirectPage(NO_RESULTS_PATH);
  }

  public void gotoCollections() {
    log.info("gotoCollections");
 
    style.setTabStyle(COLLECTIONS);
    redirectPage(COLLECTIONS_PATH);
  }

  public void gotoPartners() {
    log.info("gotoPartners"); 
    
    style.setTabStyle(PARTNERS);
    redirectPage(PARTNERS_PATH);
  }

  public void gotoFAQ() {
    log.info("gotoFAQ");

    style.setTabStyle(FAQ);
    redirectPage(FAQ_PATH);
  }

  public void gotoAbout() {
    log.info("gotoAbout");

    style.setTabStyle(ABOUT);
    redirectPage(ABOUT_PATH);
  }

  public void gotoContactPage() {
    log.info("gotoContactPage");

    style.setTabStyle(CONTACT);
    redirectPage(CONTACT_PATH);
  }

  public void gotoErrorReportPage() {
    log.info("gotoErrorReportPage");

    style.setTabStyle(HOME);
    redirectPage(ERROR_REPORT_PATH);
  }

  public boolean isShowSearchPanel() {
    req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String url = req.getRequestURL().toString();
    
    if(url.contains(COLLECTIONS_PATH)) {
      return false;
    }
    if(url.contains(ABOUT_PATH)) {
      return false;
    }
    if(url.contains(CONTACT_PATH)) {
      return false;
    }
    if(url.contains(FAQ_PATH)) {
      return false;
    }
    return !url.contains(PARTNERS_PATH);
  }

  public boolean isResultView() {
    req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String url = req.getRequestURL().toString();
    return url.contains(RESULTS_PATH);
  }

  private void redirectPage(String path) {

    externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(externalContext.getRequestContextPath() + path);
    } catch (IOException ex) {
    }
  }  
}
