/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.List;

/**
 *
 * @author idali
 */
public class GeoHashData {
   
  private final String geohashString;
  private final String coordinates;
  private final int total;
  private final List<String> geohashList;
  
  public GeoHashData(String geohashString, String coordinates, int total, List<String> geohashList) {
    this.geohashString = geohashString;
    this.coordinates = coordinates;
    this.total = total;
    this.geohashList = geohashList;
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
}
