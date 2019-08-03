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
import se.nrm.dina.web.portal.model.SolrData;

/**
 * 
 *
 * @author idali
 */
@Named("resultHeader")
@SessionScoped
@Slf4j
public class ResultHeader implements Serializable {
   
  private static final String LIST_VIEW_PATH = "/pages/listView.xhtml";
  private static final String DETAIL_VIEW_PATH = "/pages/detailView.xhtml";
  private static final String SELECTED_VIEW_PATH = "/pages/selectedView.xhtml";
  private static final String IMAGE_VIEW_PATH = "/pages/imageView.xhtml";
  private static final String MAP_VIEW_PATH = "/pages/mapView.xhtml";
   
  private String viewPath;
  private String resultView;
  
  public ResultHeader() { 
    log.info("ResultHeader");
    viewPath = LIST_VIEW_PATH; 
    resultView = "list";
  }
 
   public void simpleView() {
    log.info("simpleview");
    viewPath = LIST_VIEW_PATH; 
    resultView = "list";
  }

  public void detailView() {
    log.info("detialview"); 
    viewPath = DETAIL_VIEW_PATH; 
    resultView = "detail";
  }

  public void selectedView() { 
    log.info("selectedview"); 
    viewPath = SELECTED_VIEW_PATH; 
    resultView = "selected";
  }

  public void showOneDetail(SolrData data) {
    log.info("showOneDetail : {}", data.getCatalogNumber());
    
    viewPath = SELECTED_VIEW_PATH; 
    resultView = "selected";

//        List<SolrRecord> list = new ArrayList<>();
//        list.add(record);
//        displayOneDetail = true;
//        
//        record.setSelected(true);
//        selectall = true;
//        
//        selectedRecords.add(record);
//        session.setAttribute(SAVED_SELECTED_RECORDS, selectedRecords); 
//        selectedRecords = new ArrayList<>();
//        selectedRecords.add(record);
//     
//        resultview = 2;
  }
  
  
  
  public void backToListView() {
    log.info("backToListView");
    resultView = "list";
  }
  
  public void mapView() {
    log.info("mapView"); 
    resultView = "map";
    viewPath = MAP_VIEW_PATH;
  }
  
  public void imageView() {
    log.info("imageView");
    resultView = "image";
    viewPath = IMAGE_VIEW_PATH;
  }

  public String getViewPath() {
    return viewPath;
  }

  public String getResultView() {
    return resultView;
  } 
}
