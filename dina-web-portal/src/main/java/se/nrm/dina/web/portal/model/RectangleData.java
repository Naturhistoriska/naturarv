/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model; 

/**
 *
 * @author idali
 */
public class RectangleData {
   
  private final int count;
  private final String geohash;
  
  public RectangleData(int count, String geohash) { 
    this.count = count;
    this.geohash = geohash;
  }
 

  public int getCount() {
    return count;
  } 

  public String getGeohash() {
    return geohash;
  } 
}
