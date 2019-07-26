/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Named("search")
@SessionScoped
@Slf4j
public class SearchBean implements Serializable {

  private String freeText;
  private String defaultText;

  private final FacesContext facesContext;
  private final HttpSession session;
  private boolean isSwedish;
  
  @Inject
  private SolrService service;

  public SearchBean() {
    facesContext = FacesContext.getCurrentInstance();
    session = (HttpSession) facesContext.getExternalContext().getSession(false);
  }

  /**
   * Initialize data after class constructed
   */
  @PostConstruct
  public void init() {
    log.info("init");

    isSwedish = ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv"); 
    defaultText = CommonText.getInstance().getSearchDefaultText(isSwedish); 
  }
  
  public void simpleSearch() {
    log.info("simpleSearch: {}", freeText);
    if(!freeText.equals(defaultText) && freeText.length() > 0) {
      service.simpleSearch(freeText);
    }
  }
  
  public void all() {
    log.info("all - search all records");
  }
  
  
  
  
  public void searchInstitution() {
    log.info("searchInstitution");
  
//        savedFilterMap.put("institution", institution.getInstCode()); 
//        session.setAttribute(SAVED_QUERY, savedFilterMap);  
//        queries.put("Institution", institution.getInstitution());
//        
//        String searchText = getSavedSearchText();
//        solrResult = search(searchText, savedFilterMap, 0);
// 
//        statistic.searchFilteredData(searchText, savedFilterMap, map, type, image);
//        setResultView();  
    
  }

  public String getDefaultText() {

    isSwedish = ((String) session.getAttribute("locale")).equals("sv"); 
    return CommonText.getInstance().getSearchDefaultText(isSwedish); 
  }

  public void setDefaultText(String defaultText) {
    this.defaultText = defaultText;
  }

  public String getFreeText() {
    return freeText;
  }

  public void setFreeText(String freeText) {
    this.freeText = freeText;
  }

}
