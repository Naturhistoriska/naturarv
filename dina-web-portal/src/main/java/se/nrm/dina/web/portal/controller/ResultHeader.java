package se.nrm.dina.web.portal.controller;
 
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 

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
  
  private final String list = "list";
  private final String detail = "detail";
  private final String selected = "selected";
  private final String image = "image";
  private final String galleria = "galleria";
  private final String map = "map";
  
  private String viewPath;
  private String resultView;

  public ResultHeader() {
    log.info("ResultHeader");
    viewPath = LIST_VIEW_PATH;
    resultView = list;
  }

  public void setSimpleView() {
    log.info("simpleview");
    viewPath = LIST_VIEW_PATH;
    resultView = list; 
  }

  public void setDetailView() {
    log.info("detialview");
    viewPath = DETAIL_VIEW_PATH;
    resultView = detail; 
  }

  public void setSelectedView() {
    log.info("selectedview");
    viewPath = SELECTED_VIEW_PATH;
    resultView = selected;
  }

  public void setMapView() {
    log.info("mapView");
    resultView = map;
    viewPath = MAP_VIEW_PATH;
  }

  public void setImageView() {
    log.info("imageView");
    resultView = image;
    viewPath = IMAGE_VIEW_PATH;
  }
    
  public String getViewPath() {
    return viewPath;
  }

  public String getResultView() {
    return resultView;
  }

  public boolean isDisplayPaging() {
    return resultView.equals(list) || resultView.equals(detail);
  }

  public boolean isDisplaySelectedView() {
    return resultView.equals(selected);
  }
  
  public boolean isDisplayListView() {
    return resultView.equals(list) || resultView.equals(detail);
  }

  public boolean isDisplayBackToListLink() {
    return resultView.equals(map) || resultView.equals(image);
  }

  public boolean isMapView() {
    return resultView.equals(map);
  }
  
  public boolean isImageView() { 
    return resultView.equals(image);
  }  
}
