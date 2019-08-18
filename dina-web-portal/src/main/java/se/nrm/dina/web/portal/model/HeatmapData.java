/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import com.codepoetics.protonpack.StreamUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.Rectangle;

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
  List<List<Integer>> countList;

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
  }

  public List<GeoData> calculate() {
    double width = (maxX - minX) / columnCount;
    double height = (maxY - minY) / rowCount;

    log.info("width and height: {} -- {}", width, height);
    List<GeoData> listData = new ArrayList<>();
    StreamUtils
            .zipWithIndex(countList.stream())
            .forEach(i -> {
              int rowIndex = (int) i.getIndex();
              List<Integer> list = i.getValue();
              log.info("value ? : {}", list);
              if (list != null) {
                StreamUtils
                        .zipWithIndex(list.stream())
                        .forEach(j -> {
                          int columnIndex = (int) j.getIndex();
                          int value = j.getValue();
                          log.info("column value : {} -- {}", columnIndex, value);
                          if (value > 0) { 
                            double lowLat = maxY - (rowIndex + 1) * height;
                            double lowLnt = minX + columnIndex * width;
                            LatLng lowerLeft = new LatLng(lowLat, lowLnt);

                            double upperLat = maxY - (rowIndex + 1) * height + height;
                            double upperLnt = minX + columnIndex * width + width;
                            LatLng upperRignt = new LatLng(upperLat, upperLnt );
                            
                            log.info("coord : {} -- {}", lowLat + " -- " + lowLnt, upperLat + " -- " + upperLnt);
                             
                            Rectangle rect = new Rectangle(new LatLngBounds(upperRignt, lowerLeft));
                              rect.setStrokeColor("#d93c3c");
                              rect.setFillColor("#d93c3c");
                              rect.setFillOpacity(0.5); 
                            listData.add(new GeoData(value, rect));
                          }
                        });
              }
            });
    return listData;
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
