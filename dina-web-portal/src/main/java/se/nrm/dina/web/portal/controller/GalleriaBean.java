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
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import se.nrm.dina.web.portal.model.ImageData;

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

  private List<List<ImageData>> images;
  private List<List<ImageData>> results;

  public GalleriaBean() {

  }
  
  public void setSearchResults(List<List<ImageData>> results) {
    this.results = results;
    images = new ArrayList();
    images.addAll(results);
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

  public List<List<ImageData>> getImages() {
    return images;
  } 
}
