/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;  
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.lazy.datamodel.ImageLazyDataModel; 
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
@Named("galleria")
@SessionScoped
@Slf4j
public class GalleriaBean implements Serializable {

  private List<String> viewList;
  private List<String> partsList;
  private List<String> sexList;
  private List<String> stageList; 
  
  private Map<String, String> filterMap;
  
  @Inject
  private SolrService solr;
  
  @Inject
  private InitialProperties properties;
  @Inject
  private SearchBean search;       
  
  ImageLazyDataModel dataModel;

  public GalleriaBean() {

  }
  
  @PostConstruct
  public void init() { 
    dataModel = new ImageLazyDataModel(solr, search.getQueries()); 
  }

  public LazyDataModel<ImageModel> getModel() {
    return dataModel;
  }
   
  public void selectViews() {
    log.info("selextViews");
  }

  public void selectParts() {
    log.info("selectParts");
  }
  
  public void selectSexes() {
    log.info("selectSexes");
  }
  
  public void selectStages() {
    log.info("selectStages"); 
  }
  

  public List<String> getViewList() {
    return viewList;
  }

  public void setViewList(List<String> viewList) {
    this.viewList = viewList;
  }

  public List<String> getPartsList() {
    return partsList;
  }

  public void setPartsList(List<String> partsList) {
    this.partsList = partsList;
  }

  public List<String> getSexList() {
    return sexList;
  }

  public void setSexList(List<String> sexList) {
    this.sexList = sexList;
  }

  public List<String> getStageList() {
    return stageList;
  }

  public void setStageList(List<String> stageList) {
    this.stageList = stageList;
  } 
   
  public String getThumbPath(String imageId) { 
    return properties.getMorphbankThumbPath() + "?id=" + imageId + "&imgType=thumb"; 
  }
}
