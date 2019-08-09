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
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.ImageData;

/**
 *
 * @author idali
 */
@Named("switch")
@SessionScoped
@Slf4j
public class ImageSwitcher implements Serializable {

  private List<String> thumbs;
  private String[] imageId;
  private String catalogNumber;
  private String scientificName;
  
  @Inject
  InitialProperties properties;

  public ImageSwitcher() {

  }

  public void imageSwitch(ImageData imageData) { 
    log.info("imageSwitch : {}", imageData);

    thumbs = new ArrayList<>(); 
    this.imageId = imageData.getMorphbankImageId();
            
    this.scientificName = imageData.getTxFullName();
    this.catalogNumber = imageData.getCatalogNumber(); 
  }
  
  public String getThumbPath() {
    String thumbPath = properties.getMorphbankThumbPath();
    String thumb = thumbPath + "?id=" + imageId + "&imgType=jpg";
    log.info("thumb. {}", thumb);
    return thumb;        
  }
}
