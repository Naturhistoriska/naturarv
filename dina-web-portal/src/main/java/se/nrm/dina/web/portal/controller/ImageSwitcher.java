/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;  
import java.util.List; 
import javax.enterprise.context.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;  
import se.nrm.dina.web.portal.model.SolrData; 
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
@Named("switch")
@SessionScoped
@Slf4j
public class ImageSwitcher implements Serializable {
 
  private String catalogNumber;
  private String scientificName;
  private String mbid;
  private List<String> thumbs;
  private List<String> jpgs;
  
  @Inject
  private SolrService solr;
 
  public ImageSwitcher() {
  } 
  
  public void imageSwitch() {
    log.info("imageSwitch" );
    
    String imageId =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("imageId");
          
    log.info("imageId: {}", imageId);
    SolrData data = solr.getImagesByMorphbankId(imageId);
    log.info("data: {}", data);
    catalogNumber = data.getCatalogNumber();
    scientificName = data.getTxFullName();
    jpgs = data.getJpgs();
  }
  
  public void imageSwitch(SolrData data) {
    this.mbid = data.getMorphbankId();
    this.catalogNumber = data.getCatalogNumber();
    this.scientificName = data.getTxFullName(); 
    this.thumbs = data.getThumbs();
    this.jpgs = data.getJpgs();
//    PrimeFaces.current().ajax().update("resultsForm:imgswitchdialog");
  }

  public String getMbid() {
    return mbid;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public String getScientificName() {
    return scientificName;
  }

  public List<String> getThumbs() {
    return thumbs;
  } 

  public List<String> getJpgs() {
    return jpgs;
  } 
}
