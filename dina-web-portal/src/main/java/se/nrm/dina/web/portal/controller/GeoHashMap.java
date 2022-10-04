package se.nrm.dina.web.portal.controller;

import ch.hsr.geohash.GeoHash;
import com.codepoetics.protonpack.StreamUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Inject;
import javax.inject.Named; 
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import se.nrm.dina.web.portal.model.GeoHashData;
import se.nrm.dina.web.portal.model.RectangleData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.solr.SolrMapService;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;
import se.nrm.dina.web.portal.utils.SearchHelper;

/**
 *
 * @author idali
 */
@Named("geoHash")
@SessionScoped
@Slf4j
public class GeoHashMap implements Serializable {

//  private static final String GMAP_MARKER_PATH = "http://maps.google.com/mapfiles/ms/micons/red-dot.png"; 

  private final String coordinatesSeparator = "E";
  private final String single = "single";
  private final String pink = "pink";
  private final String plus = "plus";
  private final String minus = "minus";

  private final String singleMarkerPath;
  private final String pinkMarkerPath;
  private final String plusMarkerPath;
  private final String minusMarkerPath;
  
  private final String polylineStrokeColor = "#750202";

  private boolean displayingColorBar;
  private List<String> colorBar;

  private String searchText;
  private Map<String, String> filters; 
  private MapModel model;
  private int zoom;
  private Double centerLat;
  private Double centerLng;

  private int minCount = 90000;
  private int maxCount = 0;
 

  private String geohashPrefix;

  private List<SolrData> selectedDataList;
  private List<GeoHashData> listData;
  private SolrData selectedData;
  private RectangleData selectedRectangle;
  private String selectedLocality;
  private String selectedCoordinate;

  private List<GeoHashData> mapData;
  private TreeSet<Integer> set;

  @Inject
  private InitialProperties properties;
  @Inject
  private SolrMapService solr;

  public GeoHashMap() { 
    singleMarkerPath = MapHelper.getInstance().getMapMarkPath(single);
    pinkMarkerPath = MapHelper.getInstance().getMapMarkPath(pink);
    plusMarkerPath = MapHelper.getInstance().getMapMarkPath(plus);
    minusMarkerPath = MapHelper.getInstance().getMapMarkPath(minus); 
  }
   
  public GeoHashMap(InitialProperties properties, SolrMapService solr) {
    this.properties = properties;
    this.solr = solr; 
    singleMarkerPath = MapHelper.getInstance().getMapMarkPath(single);
    pinkMarkerPath = MapHelper.getInstance().getMapMarkPath(pink);
    plusMarkerPath = MapHelper.getInstance().getMapMarkPath(plus);
    minusMarkerPath = MapHelper.getInstance().getMapMarkPath(minus); 
  }

  @PostConstruct
  public void init() {
    log.info("init");
    setDefaultMapData();
  }

  public void setMapView(String searchText, Map<String, String> filters) {
    log.info("setMapView : {}", searchText);

    this.searchText = searchText;
    this.filters = filters;

    setDefaultMapData();
    set = new TreeSet<>();
//    fetchDataSet(MapHelper.getInstance().getDefaultRegion(), true); 
  }

//  private void fetchDataSet(String regionText, boolean resetZoom) {
//    log.info("fetchDataSet: {}", regionText);
//
//    listData = new ArrayList<>(); 
//    geohashPrefix = MapHelper.getInstance().getGeoHashPrefix(zoom);
//    model = new DefaultMapModel();
//    mapData = solr.searchGeoHash(searchText, regionText, filters, geohashPrefix);
//    if (mapData != null && !mapData.isEmpty()) {
//      mapData.stream()
//              .forEach(data -> {
//                String geohashString = data.getGeohashString();
//                GeoHash geohash = GeoHash.fromGeohashString(StringUtils.substringAfter(geohashString, "_"));
//                if (resetZoom) {
//                  minLat = minLat < geohash.getBoundingBox().getMinLat() ? minLat : geohash.getBoundingBox().getMinLat();
//                  maxLat = maxLat < geohash.getBoundingBox().getMaxLat() ? geohash.getBoundingBox().getMaxLat() : maxLat;
//                  minLng = minLng < geohash.getBoundingBox().getMinLon() ? minLng : geohash.getBoundingBox().getMinLon();
//                  maxLng = maxLng < geohash.getBoundingBox().getMaxLon() ? geohash.getBoundingBox().getMaxLon() : maxLng; 
//                }
//                int total = data.getTotal();
//                if (total == 1) {
//                  addSingleMarker(data.getCoordinates(), singleMarkerPath);
//                } else {
//                  listData.add(data);
//                } 
//              }); 
//      if (resetZoom) {
//        zoom = MapHelper.getInstance().resetZoom(minLat, minLng, maxLat, maxLng); 
//        if (zoom > 1) {
//          centerLat = (maxLat + minLat) / 2;
//        }
//        centerLng = (maxLng + minLng) / 2;
//      }
//
//      if (!listData.isEmpty()) {
//        if (listData.size() > 3) {
////          set = solr.getSet();
//          addRectangleMarker();
//          minCount = set.first();
//          maxCount = set.last();
//          displayingColorBar = model.getRectangles().size() > 0;
//          colorBar = displayingColorBar ? set.size() >= 6 ? MapHelper.getInstance().setDefaultColorBar()
//                  : MapHelper.getInstance().setColorBar(set.size()) : new ArrayList();
//        } else {
//          listData.stream()
//                  .forEach(data -> {
//                    addMultipleMarkers(data);
//                  });
//        }
//      } 
//    }
//  }

//  private void addMultipleMarkers(GeoHashData data) {
//    String geohashString = data.getGeohashString();
//    Map<String, Integer> map = solr.searchSmallDataSet(searchText, filters, geohashString); 
//    if(map != null) {
//      map.entrySet().stream()
//              .forEach(m -> {
//                String markPath = m.getValue() == 1 ? singleMarkerPath : plusMarkerPath; 
//                addSingleMarker(m.getKey(), markPath);
//              });
//    } 
//  }

  private void addRectangleMarker() {
    String prefix = MapHelper.getInstance().getGeoHashPrefix(zoom); 
    listData.stream()
            .forEach(data -> {
              int total = data.getTotal();
              String geoHash = data.getGeohashList().stream()
                      .filter(h -> h.contains(prefix))
                      .findFirst()
                      .get();
              String colorCode = MapHelper.getInstance().getColorCode(set.headSet(total).size(),
                      set.size(), total == set.first(), total == set.last());
              model.addOverlay(MapHelper.getInstance().buildRectangle(
                      geoHash.replace(prefix, CommonText.getInstance().getEmptyString()), total, colorCode));
            });
  }

  private void addSingleMarker(String coordinate, String markerPath) {
    String[] coordinates = StringUtils.split(coordinate, coordinatesSeparator);
    LatLng latLng = new LatLng(Double.valueOf(coordinates[0].substring(1)), Double.valueOf(coordinates[1]));
    model.addOverlay(new Marker(latLng, coordinate, latLng, markerPath));
  }

  public void onStateChange(StateChangeEvent event) {
    log.info("onStateChange: {}", event.getZoomLevel());

    setDefaultMapData();
    zoom = event.getZoomLevel();
    centerLat = event.getCenter().getLat();
    centerLng = event.getCenter().getLng();

    LatLngBounds bounds = event.getBounds();
    model = new DefaultMapModel(); 
//    fetchDataSet(SearchHelper.getInstance().buildSearchRegion(bounds.getNorthEast().getLat(),
//            bounds.getSouthWest().getLat(), bounds.getNorthEast().getLng(), bounds.getSouthWest().getLng()), false); 
  }

//  public void onMarkerSelect(OverlaySelectEvent event) {
//    log.info("onMarkerSelect");
//
//    selectedData = null;
//    selectedDataList = new ArrayList();
//    Overlay overlay = event.getOverlay();
//    if (overlay instanceof Marker) {
//      Marker marker = (Marker) overlay; 
//      if(marker.getIcon().equals(pinkMarkerPath)) {
//        selectedData = (SolrData) marker.getData();
//      } else {
//        selectedDataList = solr.searchSpatialData(searchText, filters, marker.getTitle());   
//        SolrData solrData = selectedDataList.get(0);
//        if(marker.getIcon().equals(singleMarkerPath)) {
//          selectedData = solrData;
//          selectedDataList = new ArrayList();
//        } else {  
//          selectedData = null;
//          if (selectedDataList.size() <= 12) {
//            if (marker.getIcon().equals(plusMarkerPath)) {
//              addPolyline(solrData);
//              marker.setIcon(minusMarkerPath);
//            } else {
//              marker.setIcon(plusMarkerPath);
//              removePolylineAndSubMarkers(marker);
//            }
//            HelpClass.getInstance().updateView("resultsForm:largeMap");
//          } else {
//            selectedLocality = solrData.getLocality();
//            selectedCoordinate = solrData.getCoordinateString();
//          }
//        }
//      } 
//    } else if (overlay instanceof Rectangle) {
//      setDefaultMapData();
//      Rectangle rectangle = (Rectangle) overlay;
//      LatLngBounds bounds = rectangle.getBounds();
//
//      selectedRectangle = (RectangleData) rectangle.getData(); 
//      String geohashString = selectedRectangle.getGeohash();
//      GeoHash geohash = GeoHash.fromGeohashString(geohashString);
//      zoom = MapHelper.getInstance().resetZoom(geohash);
//      centerLat = geohash.getBoundingBoxCenterPoint().getLatitude();
//      centerLng = geohash.getBoundingBoxCenterPoint().getLongitude();
//
//      String searchRegion = SearchHelper.getInstance().buildSearchRegion(bounds.getSouthWest().getLat(),
//              bounds.getNorthEast().getLat(), bounds.getNorthEast().getLng(), bounds.getSouthWest().getLng());
////      fetchDataSet(searchRegion, true); 
//      HelpClass.getInstance().updateView("resultsForm:mapPanel");
//    }
//  }
 
  private void removePolylineAndSubMarkers(Marker marker) {
    List<Marker> markers = model.getMarkers();
    List<Polyline> polylines = model.getPolylines(); 
    List<Marker> subMarks = markers.stream()
            .filter(m -> m.getIcon().equals(pinkMarkerPath))
            .collect(Collectors.toList());
    subMarks.stream()
            .filter(m -> ((SolrData) m.getData()).getCoordinate().equals(marker.getTitle()))
            .forEach(m -> {
              markers.remove(m);
            });
 
    List<Polyline> subPolylines = polylines.stream()
            .filter(p -> !((SolrData)p.getData()).getCoordinate().equals(marker.getTitle()))
            .collect(Collectors.toList());

    polylines.clear();
    polylines.addAll(subPolylines);
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
              polyline.setStrokeColor(polylineStrokeColor);
              polyline.setStrokeOpacity(1); 
              model.addOverlay(polyline);
            });
  }
   
  public List<SolrData> getSelectedDataList() {
    return selectedDataList;
  }

  public void showSelectedMarkDetail() {
    log.info("showSelectedMarkDetail");
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

  public int getZoom() {
    return zoom;
  }

  public boolean isDisplayingColorBar() {
    return displayingColorBar;
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

  private void setDefaultMapData() {
    model = new DefaultMapModel();
    zoom = 1;
 
    centerLat = 32.0;
    centerLng = 31.0;
    
    minCount = 99999;
    maxCount = 0;
    displayingColorBar = false;

    selectedDataList = new ArrayList<>();
    selectedData = null; 
    colorBar = new ArrayList<>();
  }

  public SolrData getSelectedData() {
    return selectedData;
  }

  public String getSelectedLocality() {
    return selectedLocality;
  }

  public String getSelectedCoordinate() {
    return selectedCoordinate;
  }

  public String getSingleMarkerPath() {
    return singleMarkerPath;
  }

  public String getPinkMarkerPath() {
    return pinkMarkerPath;
  }

  public String getPlusMarkerPath() {
    return plusMarkerPath;
  }

  public String getMinusMarkerPath() {
    return minusMarkerPath;
  } 
}
