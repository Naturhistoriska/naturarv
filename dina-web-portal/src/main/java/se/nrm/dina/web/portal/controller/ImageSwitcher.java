package se.nrm.dina.web.portal.controller;

import java.io.Serializable;  
import java.util.List; 
import javax.enterprise.context.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;  
import se.nrm.dina.web.portal.model.SolrData; 
import se.nrm.dina.web.portal.solr.SolrImageService; 

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
  
  private static final String IMAGE_ID = "imageId";
  
  @Inject
  private SolrImageService solr;
 
  public ImageSwitcher() {
  } 
  
  public ImageSwitcher(SolrImageService solr) {
    this.solr = solr;
  }
  
  /**
   * imageSwitch
   */
  public void imageSwitch() {
    log.info("imageSwitch" );
    
    String imageId =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(IMAGE_ID); 
    SolrData data = solr.getImagesByMorphbankId(imageId); 
    catalogNumber = data.getCatalogNumber();
    scientificName = data.getTxFullName();
    jpgs = data.getJpgs();
  }
  
  /**
   * 
   * @param data - SolrData
   */
  public void imageSwitch(SolrData data) {
    this.mbid = data.getMorphbankId();
    this.catalogNumber = data.getCatalogNumber();
    this.scientificName = data.getTxFullName(); 
    this.thumbs = data.getThumbs();
    this.jpgs = data.getJpgs(); 
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
