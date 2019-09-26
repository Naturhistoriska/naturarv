/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import org.primefaces.model.map.LatLngBounds;

/**
 *
 * @author idali
 */
public class RectangleData {
  
  private final LatLngBounds bounds;
  private final int count;
  
  public RectangleData(LatLngBounds bounds, int count) {
    this.bounds = bounds;
    this.count = count;
  }

  public LatLngBounds getBounds() {
    return bounds;
  }

  public int getCount() {
    return count;
  } 
}
