package se.nrm.dina.web.portal.controller;
  
import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils; 
import se.nrm.dina.web.portal.logic.utils.MapHelper;  
import se.nrm.dina.web.portal.solr.SolrMapService;

/**
 *
 * @author idali
 */
@Named("myMapBean")
@SessionScoped 
@Slf4j
public class MyMap implements Serializable {
  
  private Map dinaMap;
   
  private final String centerLat = "40.0";
  private final String centerLng = "31.0"; 
  private final int zoom = 4; 
  
  private final String mapWidth = "720px";
  private final String mapHeight = "400px";
//  private final String mapAttribute = "OpenStreetMap";
  private final String mapAttribute = "Map data &copy; <a href=\"http://openstreetmap.org\">OpenStreetMap</a> contributors,<a href=\"http://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>"; 
  private final String tile = "http://{s}.tile.osm.org/{z}/{x}/{y}.png";
  private final String layer = "Cluster";
  private final String north = "N";
  private final String east = "E";  
  private final String newLine = "\\n";
  private final String singleQout = "'";
  private final String escaptSingleQout = "\\'";
  private final String northDegree = "° N --- ";
  private final String eastDegree = "° E";
  private final String coordinatesKey = "coordinate";
  private final String bucketsKey = "buckets";
  private final String valueKey = "val";
  private final String countKey = "count";
  private final String localityKey = "locality";
  
  private String searchText;
  private java.util.Map<String, String> filters; 
   
  @Inject
  private SolrMapService solr;
   
  public void setMapView(String searchText, java.util.Map<String, String> filters) {
    log.info("setMapView : {} -- {}", searchText, filters);
        
    this.searchText = searchText;
    this.filters = filters; 
    
    fetchDataSet(MapHelper.getInstance().getDefaultRegion()); 
  }
  
  private void fetchDataSet(String regionText) {
    log.info("fetchDataSet: {}", regionText);
    
    dinaMap = new Map();
    dinaMap.setWidth(mapWidth).setHeight(mapHeight)
            .setCenter(new LatLong(centerLat, centerLng))
            .setZoom(zoom);
    dinaMap.setAttribution(mapAttribute); 
    dinaMap.setUrlTemplate(tile); 
     
    String geohashPrefix = MapHelper.getInstance().getGeoHashPrefix(2);
    JsonArray jsonArray = solr.searchGeoHash(searchText, 
            regionText, filters, geohashPrefix);
       
    //Cluster Layer
    Layer clusterLayer = (new Layer()).setLabel(layer).setClusterEnabled(true);
    JsonArray array;
    List<Marker> markers;
    for(int i = 0; i < jsonArray.size(); i++) {
      array = jsonArray.getJsonObject(i)
              .getJsonObject(coordinatesKey).getJsonArray(bucketsKey);
      markers = new ArrayList();
      for(int j = 0; j < array.size(); j++) {
        JsonObject json = array.getJsonObject(j);
        String coordinates = json.getString(valueKey);
        int count = json.getInt(countKey); 
 
        String locality = json.getJsonObject(localityKey)
                .getJsonArray(bucketsKey).getJsonObject(0)
                .getString(valueKey);

        String lat = StringUtils.substringBetween(coordinates, north, east);
        String lng = StringUtils.substringAfter(coordinates, east);
        for (int k = 0; k < count; k++) { 
          Marker marker = new Marker(new LatLong(lat, lng), getPopupText(lat, lng, locality));  
          markers.add(marker); 
        }
      }
      clusterLayer.addMarker(markers);   
    }
    dinaMap.addLayer(clusterLayer);  
  }
 
  private String getPopupText(String lat, String lng, String locality) {
    StringBuilder sb = new StringBuilder();    
    if(locality.contains(singleQout)) { 
      locality = StringUtils.replace(locality, singleQout, escaptSingleQout);
    }
    sb.append(locality);
    sb.append(newLine);
    sb.append(lat);
    sb.append(northDegree);
    sb.append(lng);
    sb.append(eastDegree);
    return sb.toString();
  }
  
  public Map getDinaMap() { 
    log.info("getDinaMap" );  
    return dinaMap;
  } 
}
