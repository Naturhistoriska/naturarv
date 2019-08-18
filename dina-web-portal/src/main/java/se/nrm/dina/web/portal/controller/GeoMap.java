/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Rectangle;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.GeoData;
import se.nrm.dina.web.portal.model.HeatmapData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Named("geo")
@SessionScoped
@Slf4j
public class GeoMap implements Serializable {

  private MapModel model;
  private int zoom;
  private Double centerLat;
  private Double centerLng;

  private int minLat;
  private int minLng;
  private int maxLat;
  private int maxLng;
  private static String DEFAULT_REGION_TEXT;

  private Map<String, String> filterMap;
  private String searchText;

//  @Inject
//  private SolrService solr;
// 
//  @Inject
//  private SearchBean search;
//  @Inject
//  private StatisticBean statistic; 
  
  @Inject
  private InitialProperties properties;

  public GeoMap() {
    filterMap = new HashMap<>(); 
    DEFAULT_REGION_TEXT = "[\"-180 -90\" TO \"180 90\"]";
  }

  @PostConstruct
  public void init() {
    log.info("init");

//    this.filterMap = search.getQueries();
//    searchText = search.getSearchText(); 
    model = new DefaultMapModel();
    zoom = 1;

    centerLat = 30.0;
    centerLng = 31.0;
    minLat = 90;
    minLng = 180;
    maxLat = -90;
    maxLng = -180;  
//    initMap();
  }

  public void gotoMapView(int totalCount, String searchText, Map<String, String> filters, SolrService solr) { 
    log.info("gotoMapView");   
    if (totalCount > 500) {
      HeatmapData data = solr.searchHeatmapWithFilter(searchText, filters, DEFAULT_REGION_TEXT, 3);
      List<GeoData> list = data.calculate();
      
      
//      LatLng sw = new LatLng(22.5, 45.0);
//      LatLng ne = new LatLng(28.125, 56.25);
//  
//      //Rectangle
//      Rectangle rect = new Rectangle(new LatLngBounds(ne, sw));
//      rect.setStrokeColor("#d93c3c");
//      rect.setFillColor("#d93c3c");
//      rect.setFillOpacity(0.5);
//      model.addOverlay(rect);

//      model.addOverlay(list.get(0).getRect());
      list.stream().forEach(g -> {
        model.addOverlay(g.getRect());
      });
    }
  }

//  private void initMap() {
//    log.info("initMap: {} ", filterMap);
//    int totalCount = statistic.getFilteredTotalMaps();
//    
//    if(!filterMap.containsKey(CommonText.getInstance().getMapKey())) {
//      filterMap.put(CommonText.getInstance().getMapKey(), String.valueOf(true)); 
//    }
//    if(totalCount > 500) {
//      solr.searchHeatmapWithFilter(searchText, filterMap, DEFAULT_REGION_TEXT, 1);
//    }
//  }

  public void setMapData(SolrResult result) {
    log.info("setMapData : {}", result);
    
    if(!filterMap.containsKey(CommonText.getInstance().getMapKey())) {
      filterMap.put(CommonText.getInstance().getMapKey(), String.valueOf(true)); 
    }

//        openedMarkerMap = new HashMap<>();
//        polylineMap = new HashMap<>();
//        
//        colors = new ArrayList<>();
//        mapDataFilter = new HashMap<>();
//        mapDataFilter.putAll(filterMap);
//        isType = type;
//        isImage = image;
//        this.searchText = searchText;
//    initMap();
//    if (result != null) {
//      List<SolrRecord> solrRecords = result.getRecords();
//
//      if (solrRecords != null) {
//        addMarkersIntoModel(solrRecords, true);
//      }
//      setZoom();
//    }
  }

  public void onStateChange() {
    log.info("onStateChange");
  }

  public void onMarkerSelect() {
    log.info("onMarkerSelect");
  }

  public int getZoom() {
    return zoom;
  }

  public void setZoom(int zoom) {
    this.zoom = zoom;
  }

  public Double getCenterLat() {
    return centerLat;
  }

  public void setCenterLat(Double centerLat) {
    this.centerLat = centerLat;
  }

  public Double getCenterLng() {
    return centerLng;
  }

  public void setCenterLng(Double centerLng) {
    this.centerLng = centerLng;
  }

  public MapModel getModel() {
    return model;
  }  
  
  public String getMapKey() {
    return properties.getMapKey();
  }
}
