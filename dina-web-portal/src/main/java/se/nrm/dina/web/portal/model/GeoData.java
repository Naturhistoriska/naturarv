/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import org.primefaces.model.map.Rectangle;

/**
 *
 * @author idali
 */
public class GeoData {
  private final int total;
  private final Rectangle rect;
  
  public GeoData(int total, Rectangle rect) {
    this.total = total;
    this.rect = rect;
  }

  public int getTotal() {
    return total;
  }

  public Rectangle getRect() {
    return rect;
  } 
}
