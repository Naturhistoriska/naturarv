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
import se.nrm.dina.web.portal.utils.HelpClass;
import se.nrm.dina.web.portal.utils.SearchHelper;

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
  private List<String> colorBar;

  private double minLat;
  private double maxLat;
  private double minLng;
  private double maxLng;

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
    setDefaultMapData();
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
    setMapData(true);
    resetZoom();
    zoom = zoom >= 3 ? zoom - 2 : zoom;
  }

  public void onStateChange(StateChangeEvent event) {
    log.info("onStateChange: {}", event.getZoomLevel());

    zoom = event.getZoomLevel();
    centerLat = event.getCenter().getLat();
    centerLng = event.getCenter().getLng();

    set = new TreeSet<>();
    listData = new ArrayList<>();
    model = new DefaultMapModel();
//    if (zoom < 15) {
//      fetchDataSet(MapHelper.getInstance().buildSearchRegion(event.getBounds()),
//              MapHelper.getInstance().getGridLevel(zoom));
//    } else {
//      fetchSmallDataSet(MapHelper.getInstance().buildSearchRegion(event.getBounds()));
//    }
    setMapData(false);
  }

  private void fetchDataSet(String regionText, int gridLevel) {
    log.info("fetchDataSet: {} -- {}", regionText, gridLevel);

//    HeatmapData data = solr.searchHeatmapWithFilter(searchText, filters, regionText, gridLevel);

//    if (data != null) {
//      totalFound = data.getTotal();
//      if (totalFound > 0) {
//        displayingColorBar = totalFound > 500;
//        if (totalFound > 500) {
//          extractData(data);
//        } else {
//          fetchSmallDataSet(regionText);
//        }
//      }
//    }
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
    if (set.size() >= 6) {
      if (colorBar.size() < 6) {
        colorBar = MapHelper.getInstance().setDefaultColorBar();
      }
    } else {
      colorBar = MapHelper.getInstance().setColorBar(set.size());
    }
  }

  private void buildGeoData(int rowIndex, int columnIndex, int count, HeatmapData data) {
    if (count == 1) {
//      SolrData solrData = searchSingleData(data.getSearchRegionText(rowIndex, columnIndex));

      SolrData solrData = null;
      listData.add(new GeoData(count, solrData));
    } else {
      listData.add(new GeoData(count, data.getLowLat(rowIndex), data.getLowLng(columnIndex),
              data.getUpperLat(rowIndex), data.getUpperLng(columnIndex)));
      set.add(count);
    }
  }

  private void fetchSmallDataSet(String regionText) {
    displayingColorBar = false;
//    listData = solr.searchSmallDataSet(searchText, filters, regionText);
  }

  private void setMapData(boolean resetMinMaxLatLng) {
    log.info("setMapData");

    listData.stream().forEach(geoData -> {
      int count = geoData.getTotal();
      if (count == 1) {
        addSingleMarker(geoData.getSolrData(), resetMinMaxLatLng);
      } else {
        if (geoData.getSolrDataList() != null) {
          addMultipleDataMarker(geoData, resetMinMaxLatLng);
        } else {
          addRectanglerToModel(count, geoData.getLatLngBounds(), resetMinMaxLatLng);
        }
      }
    });
//    resetZoom();
  }

  private void addMultipleDataMarker(GeoData geoData, boolean resetMinMaxLatLng) {
    SolrData data = geoData.getSolrDataList().get(0);
    if (resetMinMaxLatLng) {
      setMinAndMaxLatLng(data.getLatitude(), data.getLongitude());
    }
    String text = SearchHelper.getInstance().buildMultipleDataText(geoData.getTotal(), geoData.getSolrDataList());
    Marker marker = new Marker(data.getLatLng(), text, geoData.getSolrDataList(), plusMarkerPath);
    model.addOverlay(marker);
  }

  private void addRectanglerToModel(int count, LatLngBounds bounds, boolean resetMinMaxLatLng) {
    if (resetMinMaxLatLng) {
      setMinLat(bounds.getSouthWest().getLat());
      setMaxLat(bounds.getNorthEast().getLat());
      setMinLng(bounds.getSouthWest().getLng());
      setMaxLng(bounds.getNorthEast().getLat()); 
    }

//    String colorCode = MapHelper.getInstance().getColorCode(count, set);
    String colorCode = "";
    Rectangle rect = new Rectangle(bounds);
    rect.setStrokeColor(colorCode);
    rect.setStrokeOpacity(0.8);
    rect.setStrokeWeight(0);

    rect.setFillColor(colorCode);
    rect.setFillOpacity(0.8);

//    rect.setData(new RectangleData(bounds, count));
    model.addOverlay(rect);
  }

  private void addSingleMarker(SolrData solrData, boolean resetMinMaxLatLng) {
    if (resetMinMaxLatLng) {
      setMinAndMaxLatLng(solrData.getLatitude(), solrData.getLongitude());
    }

    model.addOverlay(new Marker(solrData.getLatLng(),
            SearchHelper.getInstance().buildMakerTitle(solrData, 1),
            solrData, singleMarkerPath));
  }

  private SolrData searchSingleData(String regionText) {
//    log.info("searchSingleData: {}", regionText);
//    return solr.searchSpatialData(searchText, filters, regionText).get(0);
    return null;
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
              String title = SearchHelper.getInstance().buildMakerTitle(data, 1);
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
          HelpClass.getInstance().updateView("resultsForm:largeMap");
        } else {
          selectedLocality = solrData.getLocality();
          selectedCoordinate = solrData.getCoordinateString();
        }
      }
    } else if (overlay instanceof Rectangle) {
      set = new TreeSet<>();
      listData = new ArrayList();
      setDefaultMapData();
      Rectangle rectangle = (Rectangle) overlay;

      LatLngBounds bound = rectangle.getBounds();
      minLat = bound.getSouthWest().getLat();
      maxLat = bound.getNorthEast().getLat();
      minLng = bound.getSouthWest().getLng();
      maxLng = bound.getNorthEast().getLng(); 
      resetZoom();

//      String searchRegion = MapHelper.getInstance().buildSearchRegion(bound);
      String searchRegion = "";
      if (zoom < 15) {
        fetchDataSet(searchRegion, MapHelper.getInstance().getGridLevel(zoom));
      } else {
        fetchSmallDataSet(searchRegion);
      }
      
      setMapData(false); 
      HelpClass.getInstance().updateView("resultsForm:mapPanel");
    }
  }

  private void resetZoom() {  
    double lngD = maxLng < minLng ? 360 + maxLng - minLng : maxLng - minLng;
    double latD = maxLat - minLat;
    zoom = MapHelper.getInstance().getZoomLevel(latD, lngD);

    log.info("resetZoom: zoom: {} ", zoom);

    minLng = maxLng > minLng ? minLng : maxLng;

    centerLat = minLat + latD / 2;
    centerLng = minLng + lngD / 2;

//    double north = bound.getNorthEast().getLat();
//    double south = bound.getSouthWest().getLat();
//    double east = bound.getNorthEast().getLng();
//    double west = bound.getSouthWest().getLng();
//    double lngD = east < west ? 360 + east - west : east - west;
//    double latD = north - south; 
//    zoom = MapHelper.getInstance().getZoomLevel(latD, lngD); 
//    double mimLng = east > west ? west : east;
//
//    centerLat = south + latD / 2;
//    centerLng = mimLng + lngD / 2; 
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

  private void setDefaultMapData() {
    model = new DefaultMapModel();
    zoom = 1;

    minLat = 90;
    minLng = 180;
    maxLat = -90;
    maxLng = -180;

    centerLat = 32.0;
    centerLng = 31.0;

    displayingColorBar = false;
    colorBar = new ArrayList<>();
  }

  private void setMinLat(double latitude) {
    minLat = latitude > minLat ? minLat : latitude;
  }

  private void setMaxLat(double latitude) {
    maxLat = latitude > maxLat ? latitude : maxLat;
  }

  private void setMinLng(double longitude) {
    minLng = longitude > minLng ? minLng : longitude;
  }

  private void setMaxLng(double longitude) {
    maxLng = longitude > maxLng ? longitude : maxLng;
  }

  private void setMinAndMaxLatLng(double latitude, double longitude) {
    setMinLat(latitude);
    setMaxLat(latitude);
    setMinLng(longitude);
    setMaxLng(longitude);
  }
}
