package se.nrm.dina.web.portal.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author idali
 */
public class GeoHashData {
   
  private String geohashString;
  private String coordinates;
  private int total;
  private List<String> geohashList;
  private Map<String, Integer> map;
  
 public GeoHashData(Map<String, Integer> map) {  
    this.map = map; 
  }
  
  public GeoHashData(String geohashString, String coordinates, int total, 
           Map<String, Integer> map) { 
    this.geohashString = geohashString;
    this.coordinates = coordinates;
    this.total = total;
    this.map = map; 
  }
  
  public GeoHashData(String geohashString, String coordinates, int total, 
          List<String> geohashList ) {
    this.geohashString = geohashString;
    this.coordinates = coordinates;
    this.total = total;
    this.geohashList = geohashList; 
  }
  
  
  public GeoHashData(String geohashString, String coordinates, int total, 
          List<String> geohashList, Map<String, Integer> map) {
    this.geohashString = geohashString;
    this.coordinates = coordinates;
    this.total = total;
    this.geohashList = geohashList;
    this.map = map;
  }

  public String getGeohashString() {
    return geohashString;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public int getTotal() {
    return total;
  } 
  
  public List<String> getGeohashList() {
    return geohashList;
  }

  public Map<String, Integer> getMap() {
    return map;
  }
  
  
}
