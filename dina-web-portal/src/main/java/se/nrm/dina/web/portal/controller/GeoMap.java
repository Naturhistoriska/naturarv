/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import com.codepoetics.protonpack.StreamUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Overlay;
import org.primefaces.model.map.Polyline;
import org.primefaces.model.map.Rectangle;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.utils.MapHelper;
import se.nrm.dina.web.portal.model.GeoData;
import se.nrm.dina.web.portal.model.HeatmapData;
import se.nrm.dina.web.portal.model.RectangleData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Named("geo")
@SessionScoped
@Slf4j
public class GeoMap implements Serializable {

  private MapModel model;
  private int zoom;
  private Double centerLat;
  private Double centerLng;

  private int minCount;
  private int maxCount;
  private int totalFound;
  private boolean displayingColorBar;
  private Map<String, String> defaultColorMap;
  private List<String> colorBar;

  private TreeSet<Integer> set;
  private List<GeoData> listData;

  private String searchText;
  private Map<String, String> filters;

  private static final String RELATIVE_IMAGE_PATH = "/resources/images/icons/red10.png";
  private static final String RELATIVE_IMAGE_PATH_PINK = "/resources/images/icons/pink_10.png";
  private static final String RELATIVE_IMAGE_PATH_PLUS = "/resources/images/icons/marker_red_plus_19.png";
  private static final String RELATIVE_IMAGE_PATH_MINUS = "/resources/images/icons/marker_red_minus_19.png";
  

  private final String singleMarkerPath;
  private final String pinkMarkerPath;
  private final String plusMarkerPath;
  private final String minusMarkerPath;

  private SolrData selectedData;
  private List<SolrData> selectedDataList;
  private String selectedLocality;
  private String selectedCoordinate;

  private boolean isSwedish;
  private final HttpSession session;

  @Inject
  private InitialProperties properties;
  @Inject
  private SolrService solr;

  public GeoMap() {
    String servername = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
    int serverport = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerPort();
    String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    StringBuilder sb = new StringBuilder();
    sb.append("http://");
    sb.append(servername);
    sb.append(":");
    sb.append(serverport);
    sb.append(path);

    singleMarkerPath = sb.toString() + RELATIVE_IMAGE_PATH;
    pinkMarkerPath = sb.toString() + RELATIVE_IMAGE_PATH_PINK;
    plusMarkerPath = sb.toString() + RELATIVE_IMAGE_PATH_PLUS;
    minusMarkerPath = sb.toString() + RELATIVE_IMAGE_PATH_MINUS;
 
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
  }

  @PostConstruct
  public void init() {
    log.info("init");

    defaultColorMap = new TreeMap<>();
    defaultColorMap.put(CommonText.getInstance().getColor1(), "#F7C7C7");
    defaultColorMap.put(CommonText.getInstance().getColor2(), "#E98990");
    defaultColorMap.put(CommonText.getInstance().getColor3(), "#DA323D");
    defaultColorMap.put(CommonText.getInstance().getColor4(), "#A2002E");
    defaultColorMap.put(CommonText.getInstance().getColor5(), "#8E0028");
    defaultColorMap.put(CommonText.getInstance().getColor6(), "#790022");

    setDefaultMapData();
  }

  private void setDefaultMapData() {
    model = new DefaultMapModel();
    zoom = 1;

    centerLat = 32.0;
    centerLng = 31.0;

    displayingColorBar = false;
    colorBar = new ArrayList<>();
  }

  public void setMapView(int totalResults, String searchText, Map<String, String> filters) {
    log.info("setMapView: {}", totalResults);

    this.searchText = searchText;
    this.filters = filters;

    setDefaultMapData();
    set = new TreeSet<>();
    listData = new ArrayList<>();

    totalFound = totalResults;
    fetchDataSet(MapHelper.getInstance().getDefaultRegion(), MapHelper.getInstance().getDefaultZoom());
  }

  public void onFilters(String searchText, Map<String, String> filters) {
    this.searchText = searchText;
    this.filters = filters;
    fetchDataSet(MapHelper.getInstance().getDefaultRegion(), MapHelper.getInstance().getDefaultZoom());
  }

  public void onStateChange(StateChangeEvent event) {
    log.info("onStateChange: {}", event.getZoomLevel());

    zoom = event.getZoomLevel();
    centerLat = event.getCenter().getLat();
    centerLng = event.getCenter().getLng();

    set = new TreeSet<>();
    listData = new ArrayList<>();
    model = new DefaultMapModel();

    if (zoom < 16) {
      fetchDataSet(MapHelper.getInstance().buildSearchRegion(event.getBounds()),
              MapHelper.getInstance().getGridLevel(zoom));
    } else {
      fetchSmallDataSet(MapHelper.getInstance().buildSearchRegion(event.getBounds()));
      setMapData();
    }
  }

  private void fetchDataSet(String regionText, int gridLevel) {
    log.info("fetchDataSet: {} -- {}", regionText, gridLevel);

    HeatmapData data = solr.searchHeatmapWithFilter(searchText, filters, regionText, gridLevel);

    if (data != null) {
      int total = data.getTotal();
      if (total > 0) {
        displayingColorBar = total > 500;
        if (total > 500) {
          extractData(data);
        } else {
          fetchSmallDataSet(regionText);
        }
        setMapData();
      }
    }
  }

  private void extractData(HeatmapData data) {
    StreamUtils
            .zipWithIndex(data.getCountList().stream())
            .filter(i -> i.getValue() != null)
            .forEach(i -> {
              int rowIndex = (int) i.getIndex();
              List<Integer> list = i.getValue();
              StreamUtils
                      .zipWithIndex(list.stream())
                      .filter(j -> j.getValue() > 0)
                      .forEach(j -> {
                        int columnIndex = (int) j.getIndex();
                        int value = j.getValue();
                        buildGeoData(rowIndex, columnIndex, value, data);
                      });
            });
    minCount = set.first();
    maxCount = set.last();
    log.info("set size: {}", set.size());
    if (set.size() >= 6) {
      if (colorBar.size() < 6) {
        colorBar = defaultColorMap.values()
                .stream().collect(Collectors.toList());
      }
    } else {
      setColorBar();
    }
  }

  private void buildGeoData(int rowIndex, int columnIndex, int count, HeatmapData data) {
    if (count == 1) {
      SolrData solrData = searchSingleData(data.getSearchRegionText(rowIndex, columnIndex));
      listData.add(new GeoData(count, solrData));
    } else {
      listData.add(new GeoData(count, data.getLowLat(rowIndex), data.getLowLng(columnIndex),
              data.getUpperLat(rowIndex), data.getUpperLng(columnIndex)));
      set.add(count);
    }
  }

  private void fetchSmallDataSet(String regionText) {
    displayingColorBar = false;
    listData = solr.searchSmallDataSet(searchText, filters, regionText);
  }

  private void setMapData() {
    log.info("setMapData");

    listData.stream().forEach(geoData -> {
      int count = geoData.getTotal();
      if (count == 1) {
        addSingleMarker(geoData.getSolrData());
      } else {
        if (geoData.getSolrDataList() != null) {
          addMultipleDataMarker(geoData);
        } else {
          addRectanglerToModel(count, geoData.getLatLngBounds());
        }
      }
    });
  }

  private void addMultipleDataMarker(GeoData geoData) {
    SolrData data = geoData.getSolrDataList().get(0);
    String text = MapHelper.getInstance().buildMultipleDataText(geoData.getTotal(), geoData.getSolrDataList());
    Marker marker = new Marker(data.getLatLng(), text, geoData.getSolrDataList(), plusMarkerPath);
    model.addOverlay(marker);
  }

  private void addRectanglerToModel(int count, LatLngBounds bounds) {
    String colorCode = getColorCode(count);                    // color to fill the rectangle

    Rectangle rect = new Rectangle(bounds);
    rect.setStrokeColor(colorCode);
    rect.setStrokeOpacity(0.8);
    rect.setStrokeWeight(0);

    rect.setFillColor(colorCode);
    rect.setFillOpacity(0.8);

    rect.setData(new RectangleData(bounds, count));
    model.addOverlay(rect);
  }

  private void addSingleMarker(SolrData solrData) {
    model.addOverlay(new Marker(solrData.getLatLng(),
            MapHelper.getInstance().buildMakerTitle(solrData, 1),
            solrData, singleMarkerPath));
  }

  private SolrData searchSingleData(String regionText) {
    log.info("searchSingleData: {}", regionText);
    return solr.searchSpatialData(searchText, filters, regionText).get(0);
  }

  private String getColorCode(int count) {

    int setSize = set.size();
    if (setSize >= 6) {
      if (count == set.first()) {
        return defaultColorMap.get(CommonText.getInstance().getColor1());
      }

      if (count == set.last()) {
        return defaultColorMap.get(CommonText.getInstance().getColor6());
      }

      int colorIndex = set.headSet(count).size();
      double divid = set.size() / 4;
      if (colorIndex > 0 && colorIndex <= divid) {
        return defaultColorMap.get(CommonText.getInstance().getColor2());
      }

      if (colorIndex > divid && colorIndex <= divid * 2) {
        return defaultColorMap.get(CommonText.getInstance().getColor3());
      }

      if (colorIndex > divid * 2 && colorIndex <= divid * 3) {
        return defaultColorMap.get(CommonText.getInstance().getColor4());
      } else if (colorIndex > divid * 3 && colorIndex < set.size() - 1) {
        return defaultColorMap.get(CommonText.getInstance().getColor5());
      }
    }

    if (setSize == 5) {
      if (count == set.first()) {
        return defaultColorMap.get(CommonText.getInstance().getColor1());
      }
      if (count == set.last()) {
        return defaultColorMap.get(CommonText.getInstance().getColor6());
      }

      return set.headSet(count).size() == 2
              ? defaultColorMap.get(CommonText.getInstance().getColor2())
              : set.headSet(count).size() == 5
              ? defaultColorMap.get(CommonText.getInstance().getColor3())
              : defaultColorMap.get(CommonText.getInstance().getColor4());
    }

    if (setSize == 4) {
      if (count == set.first()) {
        return defaultColorMap.get(CommonText.getInstance().getColor1());
      }
      if (count == set.last()) {
        return defaultColorMap.get(CommonText.getInstance().getColor6());
      }

      return set.headSet(count).size() == 2
              ? defaultColorMap.get(CommonText.getInstance().getColor3())
              : defaultColorMap.get(CommonText.getInstance().getColor4());
    }

    if (setSize == 3) {
      return count == set.first()
              ? defaultColorMap.get(CommonText.getInstance().getColor1())
              : count == set.last() ? defaultColorMap.get(CommonText.getInstance().getColor6())
              : defaultColorMap.get(CommonText.getInstance().getColor3());
    }

    if (setSize == 2) {
      return count == set.first()
              ? defaultColorMap.get(CommonText.getInstance().getColor1())
              : defaultColorMap.get(CommonText.getInstance().getColor6());
    }

    if (setSize == 1) {
      return defaultColorMap.get(CommonText.getInstance().getColor6());
    }

    return defaultColorMap.get(CommonText.getInstance().getColor1());
  }

  private void setColorBar() {
    log.info("setColorBar: {}", set.size());
    colorBar.clear();
    switch (set.size()) {
      case 1:
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor6()));
        break;
      case 2:
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor1()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor6()));
        break;
      case 3:
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor1()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor3()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor6()));
        break;
      case 4:
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor1()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor3()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor4()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor6()));
        break;
      case 5:
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor1()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor2()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor3()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor4()));
        colorBar.add(defaultColorMap.get(CommonText.getInstance().getColor6()));
        break;
      default:
        colorBar = defaultColorMap.values()
                .stream().collect(Collectors.toList());
        break;
    }
    log.info("colorBar : {}", colorBar);
  }

  private void addPolyline(SolrData solrData) {
    LatLng coordOrg = new LatLng(solrData.getLatitude(), solrData.getLongitude());
    int size = selectedDataList.size();
    StreamUtils
            .zipWithIndex(selectedDataList.stream())
            .filter(i -> i.getValue() != null)
            .forEach(i -> {
              int index = (int) i.getIndex();
              SolrData data = i.getValue();
              String title = MapHelper.getInstance().buildMakerTitle(data, 1);
              LatLng latLng = MapHelper.getInstance().getLatLng(coordOrg, index, size, zoom);
              Marker newMarker = new Marker(latLng, title, data, pinkMarkerPath, pinkMarkerPath);
              model.addOverlay(newMarker);

              Polyline polyline = new Polyline();
              polyline.getPaths().add(coordOrg);
              polyline.getPaths().add(latLng);
              polyline.setData(data);
              polyline.setStrokeWeight(1);
              polyline.setStrokeColor("#750202");
              polyline.setStrokeOpacity(1);
              model.addOverlay(polyline);
            });
  }

  private void removePolylineAndSubMarkers() {
    List<Marker> markers = model.getMarkers();
    List<Polyline> polylines = model.getPolylines();

    List<Marker> subMarks = markers.stream()
            .filter(m -> m.getIcon().equals(pinkMarkerPath))
            .collect(Collectors.toList());
    subMarks.stream()
            .filter(m -> selectedDataList.contains((SolrData) m.getData()))
            .forEach(m -> {
              markers.remove(m);
            });

    List<Polyline> subPolylines = polylines.stream()
            .filter(p -> !selectedDataList.contains((SolrData) p.getData()))
            .collect(Collectors.toList());

    polylines.clear();
    polylines.addAll(subPolylines);
  }

  public void onMarkerSelect(OverlaySelectEvent event) {
    log.info("onMarkerSelect");

    selectedData = null;
    selectedDataList = new ArrayList();
    Overlay overlay = event.getOverlay();
    if (overlay instanceof Marker) {
      Marker marker = (Marker) overlay;
      if (marker.getData() instanceof SolrData) {
        selectedData = (SolrData) marker.getData();
      } else if (marker.getData() instanceof List) {
        selectedDataList = (List<SolrData>) marker.getData();
        SolrData solrData = selectedDataList.get(0);
        selectedDataList = (List<SolrData>) marker.getData();
        if (selectedDataList.size() <= 12) {
          if (marker.getIcon().equals(plusMarkerPath)) {
            addPolyline(solrData);
            marker.setIcon(minusMarkerPath);
          } else {
            marker.setIcon(plusMarkerPath);
            removePolylineAndSubMarkers();
          }
          updateView("resultsForm:largeMap");
        } else {
          selectedLocality = solrData.getLocality();
          selectedCoordinate = solrData.getCoordinateString();
        }
      }
    } else if (overlay instanceof Rectangle) {
      colorBar = new ArrayList<>();
      model = new DefaultMapModel();
      Rectangle rectangle = (Rectangle) overlay;

      LatLngBounds bound = rectangle.getBounds();
      String searchRegion = MapHelper.getInstance().buildSearchRegion(bound);
      log.info("Search region: {}", searchRegion);
      setZoom(bound);
      fetchDataSet(searchRegion, MapHelper.getInstance().getGridLevel(zoom));
      updateView("resultsForm:mapPanel");
    }
  }

  private void setZoom(LatLngBounds bound) {

    double north = bound.getNorthEast().getLat();
    double south = bound.getSouthWest().getLat();
    double east = bound.getNorthEast().getLng();
    double west = bound.getSouthWest().getLng();

    double lngD = east < west ? 360 + east - west : east - west;
    double latD = north - south;

    log.info("lngD, latD: {} -- {}", lngD, latD);

    int zoomlevelLng = 1;
    if (lngD >= 240) {
      zoomlevelLng = 1;
    } else if (lngD < 240 && lngD >= 120) {
      zoomlevelLng = 2;
    } else if (lngD < 120 && lngD >= 62) {
      zoomlevelLng = 3;
    } else if (lngD < 62 && lngD >= 25) {
      zoomlevelLng = 4;
    } else if (lngD < 25 && lngD >= 10) {
      zoomlevelLng = 5;
    } else if (lngD < 10 && lngD >= 7) {
      zoomlevelLng = 6;
    } else if (lngD < 7) {
      if (zoom > 7) {
        zoomlevelLng = zoom;
      } else {
        zoomlevelLng = 7;
      }
    }

    int zoomlevelLat = 1;
    if (latD >= 100) {
      zoomlevelLat = 1;
    } else if (latD < 100 && latD >= 60) {
      zoomlevelLat = 2;
    } else if (latD < 60 && latD >= 32) {
      zoomlevelLat = 3;
    } else if (latD < 32 && latD >= 12) {
      zoomlevelLat = 4;
    } else if (latD < 12 && latD >= 6) {
      zoomlevelLat = 5;
    } else if (latD < 6 && latD >= 3) {
      zoomlevelLat = 6;
    } else if (latD < 3) {
      if (zoom > 7) {
        zoomlevelLat = zoom;
      } else {
        zoomlevelLat = 7;
      }
    }

    zoom = zoomlevelLng > zoomlevelLat ? zoomlevelLat : zoomlevelLng;
//    centerLat = north + latD / 2;
//    centerLng = east + lngD / 2;

    centerLat = (south + north) / 2.0;
    centerLng = (west + east) / 2.0 + west <= north ? 0 : 180.0;

    log.info("zoom : {} - {}", zoom, centerLat + " -- " + centerLng);
  }

  //  private void addMultipleMarkers(List<SolrData> solrDataList) {
//    solrDataList.stream()
//            .forEach(d -> {
//              String markerTitle = MapHelper.getInstance().buildMakerTitle(d, solrDataList.size());
//              LatLng latLng = d.getLatLng();
//              Marker marker = new Marker(latLng, markerTitle, d, plusMarkerPath, plusMarkerPath);
//              model.addOverlay(marker);
//            });
//  }
  private void updateView(String viewId) {
    PrimeFaces.current().ajax().update(viewId);
  }

  public void showSelectedMarkDetail() {
    log.info("showSelectedMarkDetail");
  }

  public int getZoom() {
    return zoom;
  }

  public void setZoom(int zoom) {
    this.zoom = zoom;
  }

  public Double getCenterLat() {
    return centerLat;
  }

  public void setCenterLat(Double centerLat) {
    this.centerLat = centerLat;
  }

  public Double getCenterLng() {
    return centerLng;
  }

  public void setCenterLng(Double centerLng) {
    this.centerLng = centerLng;
  }

  public MapModel getModel() {
    return model;
  }

  public String getMapKey() {
    return properties.getMapKey();
  }

  public List<String> getColorBar() {
    return colorBar;
  }

  public int getMinCount() {
    return minCount;
  }

  public int getMaxCount() {
    return maxCount;
  }

  public int getTotalFound() {
    return totalFound;
  }

  public boolean isDisplayingColorBar() {
    return displayingColorBar;
  }

  public SolrData getSelectedData() {
    return selectedData;
  }

  public List<SolrData> getSelectedDataList() {
    return selectedDataList;
  }

  public String getSelectedListSize() {
    String locale = (String) session.getAttribute(CommonText.getInstance().getLocale());
    isSwedish = locale == null ? true : locale.equals("sv");
    return CommonText.getInstance().getTotal(isSwedish) + ": " + selectedDataList.size();
  }

  public String getSelectedLocality() {
    return selectedLocality;
  }

  public String getSelectedCoordinate() {
    return selectedCoordinate;
  }

}
