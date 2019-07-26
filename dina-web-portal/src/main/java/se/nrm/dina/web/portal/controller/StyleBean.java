/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 

/**
 *
 * @author idali
 */
@SessionScoped
@Named("style")
@Slf4j
public class StyleBean implements Serializable {

  private String svLink;
  private String enLink;

  private final static String ACTIVE_LINK = "activelink";
  private final static String INACTIVE_LINK = "inactivelink";
  private final static String INACTIVE_TAB_LINK = "inactiveTablink";

  private final static String HOME = "home";
  private final static String COLLECTIONS = "collections";
  private final static String PARTNERS = "partners";
  private final static String FAQ = "faq";
  private final static String ABOUT = "about";
  private final static String CONTACT = "contact";

  private String tabStart;
  private String tabCollections;
  private String tabPartners;
  private String tabFaq;
  private String tabAbout;

  public StyleBean() {
    svLink = ACTIVE_LINK;
    enLink = INACTIVE_LINK;

    tabStart = ACTIVE_LINK;
    tabCollections = INACTIVE_TAB_LINK;
    tabPartners = INACTIVE_TAB_LINK;
    tabFaq = INACTIVE_TAB_LINK;
    tabAbout = INACTIVE_TAB_LINK;
  }

  public String getEnLink() {
    return enLink;
  }

//  public void setEnLink(boolean isActive) {
//    this.enLink = isActive ? activeLink : inactiveLink;
//  }
  public String getSvLink() {
    return svLink;
  }

//  public void setSvLink(boolean isActive) {
//    this.svLink = isActive ? activeLink : inactiveLink;
//  }
  public String getTabStart() {
    return tabStart;
  }

  public String getTabCollections() {
    return tabCollections;
  }

  public String getTabPartners() {
    return tabPartners;
  }

  public String getTabFaq() {
    return tabFaq;
  }

  public String getTabAbout() {
    return tabAbout;
  }
 
  public void setTabStyle(String tab) {

    tabStart = INACTIVE_TAB_LINK;
    tabCollections = INACTIVE_TAB_LINK;
    tabPartners = INACTIVE_TAB_LINK;
    tabFaq = INACTIVE_TAB_LINK;
    tabAbout = INACTIVE_TAB_LINK;
 
    switch (tab) {
      case HOME:
        tabStart = ACTIVE_LINK;
        break;
      case COLLECTIONS:
        tabCollections = ACTIVE_LINK;
        break;
      case PARTNERS:
        tabPartners = ACTIVE_LINK;
        break;
      case FAQ:
        tabFaq = ACTIVE_LINK;
        break;
      case ABOUT:
        tabAbout = ACTIVE_LINK;
        break;
      case CONTACT:
        break;
      default:
        tabStart = ACTIVE_LINK;
    } 
  }

  public void setLanguageStyle(String locale) {

    svLink = INACTIVE_LINK;
    enLink = INACTIVE_LINK;

    if (locale.equals("sv")) {
      svLink = ACTIVE_LINK;
    } else {
      enLink = ACTIVE_LINK;
    }
  }
}
