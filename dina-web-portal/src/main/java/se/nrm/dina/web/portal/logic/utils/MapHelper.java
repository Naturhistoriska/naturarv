/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import ch.hsr.geohash.GeoHash;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors; 
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.map.LatLng; 
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.Rectangle;
import se.nrm.dina.web.portal.model.RectangleData; 
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class MapHelper {

  private final String defaultSearchRange = "[\"-180 -90\" TO \"180 90\"]";
  private final int defaultRangeZoom = 2;
  private static final Map<String, String> DEFAULT_COLOR_MAP;
//  private StringBuilder markTitleSb;
//  private StringBuilder searchRegionSb;
  private GeoHash geohash;
//  private double maxLat;
//  private double maxLng;
//  private double minLat;
//  private double minLng;
  private LatLng northEast;
  private LatLng southWest;
  private LatLngBounds bounds; 
  private Rectangle rect; 

  private static MapHelper instance = null;

  public static synchronized MapHelper getInstance() {
    if (instance == null) {
      instance = new MapHelper();
    }
    return instance;
  }

  static {
    DEFAULT_COLOR_MAP = new TreeMap<>();
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor1(), "#F7C7C7");
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor2(), "#E98990");
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor3(), "#DA323D");
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor4(), "#A2002E");
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor5(), "#8E0028");
    DEFAULT_COLOR_MAP.put(CommonText.getInstance().getColor6(), "#790022");
  }

  public String getDefaultRegion() {
    return defaultSearchRange;
  }

  public int getDefaultZoom() {
    return defaultRangeZoom;
  }

  public Rectangle buildRectangle(String geoHashData, int total, String colorCode) { 
    geohash = GeoHash.fromGeohashString(geoHashData);   
    northEast = new LatLng(geohash.getBoundingBox().getMaxLat(), geohash.getBoundingBox().getMinLon());
    southWest = new LatLng(geohash.getBoundingBox().getMinLat(), geohash.getBoundingBox().getMaxLon()); 
    bounds = new LatLngBounds(southWest, northEast);  
    rect = new Rectangle(bounds);
    rect.setStrokeColor(colorCode);
    rect.setStrokeOpacity(0.8);
    rect.setStrokeWeight(0);

    rect.setFillColor(colorCode);
    rect.setFillOpacity(0.8);
    rect.setData(new RectangleData(total, geoHashData));
    return rect;
  }

//  /**
//   * Build search region with low latitude, low longitude, upper latitude, upper longitude
//   * 
//   * @param lowLat - double
//   * @param lowLng - double
//   * @param upperLat - double
//   * @param upperLng - double
//   * 
//   * @return String
//   */
//  private String buildSearchRegion(double lowLat, double lowLng, double upperLat, double upperLng) {
////    log.info("searchRegion: {} -- {}", lowLat + " -- " + lowLng, upperLat + " -- " + upperLng);
//
//    StringBuilder sb = new StringBuilder();
//    sb.append("[");
//    sb.append(lowLat);
//    sb.append(",");
//    sb.append(lowLng);
//    sb.append(" TO ");
//    sb.append(upperLat);
//    sb.append(",");
//    sb.append(upperLng);
//    sb.append("]");
//    return sb.toString();
//  }

 
//  public String buildSearchRegion(LatLngBounds bound) { 
////    double north = bound.getNorthEast().getLat();
////    double south = bound.getSouthWest().getLat();
////    double east = bound.getNorthEast().getLng();
////    double west = bound.getSouthWest().getLng();
//    searchRegionSb = new StringBuilder();
//    searchRegionSb.append("[");
//    searchRegionSb.append(bound.getSouthWest().getLat());
//    searchRegionSb.append(",");
//    searchRegionSb.append(bound.getSouthWest().getLng());
//    searchRegionSb.append(" TO ");
//    searchRegionSb.append(bound.getNorthEast().getLat());
//    searchRegionSb.append(",");
//    searchRegionSb.append(bound.getNorthEast().getLng());
//    searchRegionSb.append("]");
//    return searchRegionSb.toString(); 
//  }

//  /**
//   * Build title for marker
//   * 
//   * @param data - SolrData
//   * @param count - int
//   * 
//   * @return - String
//   */
//  public String buildMakerTitle(SolrData data, int count) {
//    markTitleSb = new StringBuilder();
//    markTitleSb.append(data.getTxFullName());
//    markTitleSb.append(newLine);
//    markTitleSb.append(data.getLocality());
//    markTitleSb.append(newLine);
//    markTitleSb.append(data.getLatitudeText());
//    markTitleSb.append(" -- ");
//    markTitleSb.append(data.getLongitudeText());
//    if (count > 1) {
//      markTitleSb.append(newLine);
//      markTitleSb.append("Total: ");
//      markTitleSb.append(count);
//    } 
//    return markTitleSb.toString().trim();
//  }
 
   /**
   * Build search region with LatLngBounds
   * @param zoom - int
   * 
   * @return int
   */
  public int getGridLevel(int zoom) {
    switch (zoom) {
      case 1:
      case 2:
        return 2;
      case 3:
      case 4:
        return 3;
      case 5:
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
      case 14:
      case 15:
      case 13:
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
  
  public String getGeoHashPrefix(int zoom) {
    switch(zoom) { 
      case 1:
      case 2:
        return "2_";
      case 3:
      case 4:
      case 5:   
        return "3_"; 
      case 6:
      case 7:
        return "4_";
      case 8:
      case 9:
      case 10:
        return "5_"; 
      case 11:
      case 12:
        return "6_";
      case 13:
      case 14:
      case 15: 
        return "7_";
      case 16: 
      case 17: 
      case 18: 
        return "8_";
      case 19: 
      case 20: 
      case 21: 
        return "9_";
      default:
        return "2_"; 
    } 
  }
  
//  public double getCenterLat() {
//    return  maxLat + minLat / 2; 
//  }
//  
//  public double getCenterLng() {
//    return maxLng + minLng / 2;
//  }
  
//  public int setZoom(List<SolrData> results) {
//    log.info("setZoom :  {}", results.size());
//    results.stream()
//            .forEach(data -> {
//              maxLat = maxLat > data.getLatitude() ? maxLat : data.getLatitude();
//              minLat = minLat > data.getLatitude() ? data.getLatitude() : minLat;
//              maxLng = maxLng > data.getLongitude() ? maxLng : data.getLongitude();
//              minLng = minLng > data.getLongitude() ? data.getLongitude() : minLng;
//            });
//    return resetZoom(minLat, minLng, maxLat, maxLng);
//  }
  
  public int resetZoom(double minLat, double minLng, double maxLat, double maxLng) { 
    double lngD = maxLng < minLng ? 360 + maxLng - minLng : maxLng - minLng;
    double latD = maxLat - minLat;
    return getZoomLevel(latD, lngD); 
  }
  
  public int resetZoom(GeoHash geoHash) {     
    return resetZoom(geoHash.getBoundingBox().getMinLat(), geoHash.getBoundingBox().getMinLon(), 
            geoHash.getBoundingBox().getMaxLat(), geoHash.getBoundingBox().getMaxLon());
  }

  public int getZoomLevel(double latD, double lngD) { 
    int zoomLevelLng = 1;
    if (lngD >= 240) {
      zoomLevelLng = 1;
    } else if (lngD < 240 && lngD >= 122) {
      zoomLevelLng = 2;
    } else if (lngD < 122 && lngD >= 62) {
      zoomLevelLng = 3;
    } else if (lngD < 62 && lngD >= 32) {
      zoomLevelLng = 4;
    } else if (lngD < 32 && lngD >= 15) {
      zoomLevelLng = 5;
    } else if (lngD < 15 && lngD >= 7) {
      zoomLevelLng = 6;
    } else if (lngD < 7 && lngD >= 4) {
      zoomLevelLng = 7;
    } else if (lngD < 4 && lngD >= 2) {
      zoomLevelLng = 8;
    } else if (lngD < 2 && lngD >= 1) {
      zoomLevelLng = 9;
    } else if (lngD < 1 && lngD >= 0.48) {
      zoomLevelLng = 10;
    } else if (lngD < 0.48 && lngD >= 0.24) {
      zoomLevelLng = 11;
    } else if (lngD < 0.24 && lngD >= 0.12) {
      zoomLevelLng = 12;
    } else if (lngD < 0.12 && lngD >= 0.06) {
      zoomLevelLng = 13;
    } else if (lngD < 0.06 && lngD >= 0.03) {
      zoomLevelLng = 14;
    } else if (lngD < 0.03) {
      zoomLevelLng = 15;
    }

    int zoomLevelLat = 1;
    if (latD >= 107) {
      zoomLevelLat = 1;
    } else if (latD < 107 && latD >= 65) {
      zoomLevelLat = 2;
    } else if (latD < 65 && latD >= 34.28) {
      zoomLevelLat = 3;
    } else if (latD < 34.28 && latD >= 17.4) {
      zoomLevelLat = 4;
    } else if (latD < 17.4 && latD >= 8.72) {
      zoomLevelLat = 5;
    } else if (latD < 8.72 && latD >= 4.4) {
      zoomLevelLat = 6;
    } else if (latD < 4.4 && latD >= 2.2) {
      zoomLevelLat = 7;
    } else if (latD < 2.2 && latD >= 1.1) {
      zoomLevelLat = 8;
    } else if (latD < 1.1 && latD >= 0.55) {
      zoomLevelLat = 9;
    } else if (latD < 0.55 && latD >= 0.28) {
      zoomLevelLat = 10;
    } else if (latD < 0.28 && latD >= 0.134) {
      zoomLevelLat = 11;
    } else if (latD < 0.134 && latD >= 0.04) {
      zoomLevelLat = 12;
    } else if (latD < 0.04 && latD >= 0.186) {
      zoomLevelLat = 13;
    } else if (latD < 0.186 && latD >= 0.009) {
      zoomLevelLat = 14;
    } else if (latD < 0.009) {
      zoomLevelLat = 15;
    }
    return zoomLevelLng > zoomLevelLat ? zoomLevelLat : zoomLevelLng;
  }

  public List<String> setDefaultColorBar() { 
    return DEFAULT_COLOR_MAP.values()
            .stream()
            .collect(Collectors.toList());
  }

  public List<String> setColorBar(int size ) {
    log.info("setColorBar: {}", size); 
    
    List<String> colorBar = new ArrayList<>();  
    switch (size) {
      case 1:
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1()));
        break;
      case 2:
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6()));
        break;
      case 3:
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6()));
        break;
      case 4:
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6()));
        break;
      case 5:
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4()));
        colorBar.add(DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6()));
        break;
      default:
        return setDefaultColorBar();
    }
    return colorBar;
  }

//  public String getColorCode(int count, int index) {
//    if (count == 1) {
//      return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//    }
//
//    if (count == 2) {
//      return index == 0
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6())
//              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//    }
//
//    if (count == 3) {
//      switch (index) {
//        case 0:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//        case 1:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//        case 2:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//        default:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//    }
//
//    if (count == 4) {
//      switch (index) {
//        case 0:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//        case 1:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//        case 2:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//        case 3:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//        default:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//    }
//
//    if (count == 5) {
//      switch (index) {
//        case 0:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//        case 1:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//        case 2:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//        case 3:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
//        case 4:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//        default:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//    }
//
//    if (count == 6) {
//      switch (index) {
//        case 0:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//        case 1:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor5());
//        case 2:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//        case 3:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//        case 4:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
//        case 5:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//        default:
//          return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//    }
//
//    if (count > 6) {
//      int step = (int) Math.floor(count / 6);
//
//      if (index < step) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//      }
//
//      if (index < step * 2) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor5());
//      }
//
//      if (index < step * 3) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//      }
//
//      if (index < step * 4) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//      }
//
//      if (index < step * 5) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
//      }
//
//      if (index <= step * 6) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//    }
//    return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//  }
  
  public String getColorCode(int colorIndex, int setSize, boolean isFirst, boolean isLast) {
    if(setSize >= 6) {
      if(isFirst) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
      }
      if(isLast) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
      }
      
      double divid = setSize / 4;
      if (colorIndex > 0 && colorIndex <= divid) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
      }
      
       if (colorIndex > divid && colorIndex <= divid * 2) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
      }

      if (colorIndex > divid * 2 && colorIndex <= divid * 3) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
      }   
      return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor5()); 
    }
    
    if (setSize == 5) {
      if (isFirst) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
      }
      if (isLast) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
      }
      if(colorIndex == 2) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
      }
      return colorIndex == 5
              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3())
              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4()); 
    }
    
    if (setSize == 4) {
      if (isFirst) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
      }
      if (isLast) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
      }

      return colorIndex == 2
              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3())
              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
    }

    if (setSize == 3) {
      if(isFirst) {
        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
      }
      return isLast ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6())
              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
    }

    if (setSize == 2) {
      return isFirst
              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1())
              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
    }

    if (setSize == 1) {
      return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
    }

    return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
  }

//  public String getColorCode(int count, TreeSet<Integer> set) { 
//    int setSize = set.size();
//    if (setSize >= 6) {
//      if (count == set.first()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//
//      if (count == set.last()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//      }
//
//      int colorIndex = set.headSet(count).size();
//      double divid = set.size() / 4;
//      if (colorIndex > 0 && colorIndex <= divid) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2());
//      }
//
//      if (colorIndex > divid && colorIndex <= divid * 2) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//      }
//
//      if (colorIndex > divid * 2 && colorIndex <= divid * 3) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//      } else if (colorIndex > divid * 3 && colorIndex < set.size() - 1) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor5());
//      }
//    }
//
//    if (setSize == 5) {
//      if (count == set.first()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//      if (count == set.last()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//      }
//
//      return set.headSet(count).size() == 2
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor2())
//              : set.headSet(count).size() == 5
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3())
//              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//    }
//
//    if (setSize == 4) {
//      if (count == set.first()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//      }
//      if (count == set.last()) {
//        return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//      }
//
//      return set.headSet(count).size() == 2
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3())
//              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor4());
//    }
//
//    if (setSize == 3) {
//      return count == set.first()
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1())
//              : count == set.last() ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6())
//              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor3());
//    }
//
//    if (setSize == 2) {
//      return count == set.first()
//              ? DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1())
//              : DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//    }
//
//    if (setSize == 1) {
//      return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor6());
//    }
//
//    return DEFAULT_COLOR_MAP.get(CommonText.getInstance().getColor1());
//  }
}
