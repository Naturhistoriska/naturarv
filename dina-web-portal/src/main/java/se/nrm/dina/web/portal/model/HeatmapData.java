/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.logic.utils.MapHelper;

/**
 *
 * @author idali
 */
@Slf4j
public class HeatmapData {

  private final int total;
  private final int rowCount;
  private final int columnCount;
  private final double minX;
  private final double maxX;
  private final double minY;
  private final double maxY;
  private final List<List<Integer>> countList;
  private final double widthRatio;
  private final double heightRatio;

  public HeatmapData(int total, int rowCount, int columnCount, double minX,
          double maxX, double minY, double maxY, List<List<Integer>> countList) {
    this.total = total;
    this.rowCount = rowCount;
    this.columnCount = columnCount;
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
    this.countList = countList;

    widthRatio = maxX < minX ? (360 + maxX - minX) / columnCount : (maxX - minX) / columnCount;  
    heightRatio = (maxY - minY) / rowCount; 
  }

  public double getLowLat(int rowIndex) { 
    return maxY - (rowIndex + 1) * heightRatio; 
  }

  public double getLowLng(int columnIndex) {
    double lng = minX + columnIndex * widthRatio;
    return lng > 180 ? lng - 360 : lng;
  }

  public double getUpperLat(int rowIndex) {
    return getLowLat(rowIndex) + heightRatio;
  }

  public double getUpperLng(int columnIndex) {
    return getLowLng(columnIndex) + widthRatio;
  }

  public String getSearchRegionText(int rowIndex, int columnIndex) {
    return MapHelper.getInstance().buildSearchRegion(getLowLat(rowIndex),
            getLowLng(columnIndex), getUpperLat(rowIndex), getUpperLng(columnIndex));

  }

  public int getTotal() {
    return total;
  }

  public int getRowCount() {
    return rowCount;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public double getMinX() {
    return minX;
  }

  public double getMaxX() {
    return maxX;
  }

  public double getMinY() {
    return minY;
  }

  public double getMaxY() {
    return maxY;
  }

  public List<List<Integer>> getCountList() {
    return countList;
  }

}
