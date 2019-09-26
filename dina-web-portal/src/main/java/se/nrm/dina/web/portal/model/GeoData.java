/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.List;
import org.primefaces.model.map.LatLng; 
import org.primefaces.model.map.LatLngBounds;


/**
 *
 * @author idali
 */
public class GeoData {
  private final int total; 
  private double lowLat;
  private double lowLng;
  private double upperLat;
  private double upperLng;
   
  private SolrData solrData;
  private List<SolrData> solrDataList;
  
  public GeoData(int total, SolrData solrData) {
    this.total = total; 
    this.solrData = solrData;
  }
  
    
  public GeoData(int total, List<SolrData> solrDataList) {
    this.total = total; 
    this.solrDataList = solrDataList;
  }
  
  public GeoData(int total, double lowLat, double lowLng, double upperLat, double upperLng) {
    this.total = total; 
    this.lowLat = lowLat;
    this.lowLng = lowLng;
    this.upperLat = upperLat;
    this.upperLng = upperLng; 
  }
  
  public LatLng getNorthEast() {
    return new LatLng(upperLat, upperLng);
  }
  
  public LatLng getSouthWest() {
    return new LatLng(lowLat, lowLng);
  }
  
  public LatLngBounds getLatLngBounds() {
    return new LatLngBounds(getNorthEast(), getSouthWest());
  }
  
  public LatLng getLatLng(SolrData solrData) {
    return new LatLng(solrData.getLatitude(), solrData.getLongitude());
  }
 
  public int getTotal() {
    return total;
  }
  
  public double getLowLat() {
    return lowLat;
  }
  
  public double getLowLng() {
    return lowLng;
  }
 
  public double getUpperLat() {
    return upperLat;
  }
 
  public double getUpperLng() {
    return upperLng;
  }

  public SolrData getSolrData() {
    return solrData;
  }
  
  public List<SolrData> getSolrDataList() {
    return solrDataList;
  } 
}
