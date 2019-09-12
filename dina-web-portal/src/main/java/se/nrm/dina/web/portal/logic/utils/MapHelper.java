/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@Slf4j
public class MapHelper {

  private static final String DEFAULT_REGION_TEXT = "[\"-180 -90\" TO \"180 90\"]";
  private static final int DEFAULT_REGION_ZOOM = 2;

  private static MapHelper instance = null;

  public static synchronized MapHelper getInstance() {
    if (instance == null) {
      instance = new MapHelper();
    }
    return instance;
  }

  public String getDefaultRegion() {
    return DEFAULT_REGION_TEXT;
  }

  public int getDefaultZoom() {
    return DEFAULT_REGION_ZOOM;
  }

  public String buildSearchRegion(double lowLat, double lowLng, double upperLat, double upperLng) {
    log.info("searchRegion: {} -- {}", lowLat + " -- " + lowLng, upperLat + " -- " + upperLng);
    
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(lowLat);
    sb.append(",");
    sb.append(lowLng);
    sb.append(" TO ");
    sb.append(upperLat);
    sb.append(",");
    sb.append(upperLng);
    sb.append("]");
    return sb.toString();
  }

  public String buildSearchRegion(LatLngBounds bound) {
    
    double north = bound.getNorthEast().getLat();
    double south = bound.getSouthWest().getLat();
    double east = bound.getNorthEast().getLng();
    double west = bound.getSouthWest().getLng();
    
    log.info("bound: {} -- {}", north + " -- " + east, south + " -- " + west);
     
//    if(north > south && east < west) {
//      return buildSearchRegion(south, east, north, west);
//    }  
//    if(north < south && east > west) {
//      return buildSearchRegion(north, west, south, east);
//    } 
//    if(north < south && east < west) {
//      return buildSearchRegion(north, east, south, west);
//    }   
    return buildSearchRegion(south, west, north, east);
  }

  public String buildMakerTitle(SolrData data, int count) {
    StringBuilder sb = new StringBuilder();
    sb.append(data.getTxFullName());
    sb.append("\n");
    sb.append(data.getLocality());
    sb.append("\n");
    sb.append(data.getLatitudeText());
    sb.append(" -- ");
    sb.append(data.getLongitudeText());
    if (count > 1) {
      sb.append("\nTotal: ");
      sb.append(count);
    } 

    return sb.toString().trim();
  }

  public String buildMultipleDataText(int count, List<SolrData> solrDataList) {

    SolrData solrData = solrDataList.get(0);
    StringBuilder mapInfoSb = new StringBuilder();
    mapInfoSb.append("Total count: ");
    mapInfoSb.append(solrDataList.size());
    mapInfoSb.append("\n");
    mapInfoSb.append(solrData.getLocality());
    mapInfoSb.append("\n");
    mapInfoSb.append(solrData.getLatitudeText());
    mapInfoSb.append(" -- ");
    mapInfoSb.append(solrData.getLongitude());
    mapInfoSb.append("\n\n");

    int index = count > 10 ? 10 : count - 1;

    IntStream.range(0, index)
            .forEach(i -> {
              SolrData data = solrDataList.get(i);
              mapInfoSb.append(data.getCatalogNumber());
              mapInfoSb.append("\t");
              mapInfoSb.append(data.getTxFullName());
              mapInfoSb.append("\n");
            });

    return mapInfoSb.toString();
  }

  public int getGridLevel(int zoom) {
    switch (zoom) {
      case 1:
      case 2:
      case 3:
        return 2;
      case 4:
      case 5:
        return 3;
      case 6:
      case 7:
        return 4;
      case 8:
      case 9:
        return 5;
      case 10:
      case 11:
      case 12:
        return 6;
      case 13:
      case 14:
      case 15:
        return 7;
      default:
        return 2;
    }
  }

  public LatLng getLatLng(LatLng coordOrg, int index, int size, int zoom) { 
    double d = getDestance(zoom); 
    double dAngle = index * getAngleIncrease(size) * Math.PI / 180; 
    return new LatLng(coordOrg.getLat() + d * Math.sin(dAngle), coordOrg.getLng() + d * Math.cos(dAngle));
  }
  
  private double getDestance(int zoom) { 
    switch (zoom) {
      case 1:
        return 18; 
      case 2:
        return 9; 
      case 3:
        return 5; 
      case 4:
        return 3; 
      case 5:
        return 2; 
      case 6:
        return 1; 
      case 7:
        return 0.5; 
      case 8:
        return 0.3; 
      case 9:
        return 0.1; 
      case 10:
        return 0.07; 
      case 11:
        return 0.04; 
      case 12:
        return 0.02; 
      case 13:
        return 0.008; 
      case 14:
        return 0.006; 
      case 15:
        return 0.005; 
      case 16:
        return 0.004; 
      default:
        return 1;
    } 
  }
  
  private double getAngleIncrease(int count) { 
    switch (count) {
      case 2:
        return 180; 
      case 3:
        return 120; 
      case 4:
        return 90; 
      case 5:
        return 72; 
      case 6:
        return 60; 
      case 7:
        return 51; 
      case 8:
        return 45; 
      case 9:
        return 40; 
      case 10:
        return 36; 
      case 11:
        return 32.7; 
      default:
        return 30;
    }
  }
}
