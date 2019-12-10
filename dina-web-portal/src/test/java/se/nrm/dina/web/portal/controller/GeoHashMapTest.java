package se.nrm.dina.web.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.junit.After; 
import org.junit.Before; 
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner; 
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Overlay;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.GeoHashData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.solr.SolrMapService;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class)  
public class GeoHashMapTest {
  
  private GeoHashMap instance;
  private FacesContext context;  
  private String serverName;
  private int serverPort;
  private String path;
  
  @Mock
  private InitialProperties properties;
  @Mock
  private SolrMapService solr;
  @Mock
  private ExternalContext externalContext; 
  @Mock
  HttpServletRequest request;
  
  public GeoHashMapTest() {
  }
  
  @Before
  public void setUp() {
    
    serverName = "naturarv";
    serverPort = 8080;
    context = ContextMocker.mockFacesContext(); 
    path = "https://naturarv.nrm.se";
    
    when(request.getServerName()).thenReturn(serverName); 
    when(request.getServerPort()).thenReturn(serverPort); 
    when(externalContext.getRequest()).thenReturn(request);    
    when(externalContext.getRequestContextPath()).thenReturn(path);        
    when(context.getExternalContext()).thenReturn(externalContext);
    instance = new GeoHashMap(properties, solr);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new GeoHashMap();
    assertNotNull(instance);
    
    instance.getMapKey();
  }

  /**
   * Test of init method, of class GeoHashMap.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
    assertNotNull(instance.getModel());
    assertEquals(1, instance.getZoom());
  }

  /**
   * Test of setMapView method, of class GeoHashMap.
   */
  @Test
  public void testSetMapView() {
    System.out.println("setMapView");
    String searchText = "text:sweden";
    Map<String, String> filters = null; 
    List<GeoHashData> mapData = new ArrayList(); 
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
  }
  
  @Test
  public void testSetMapViewNull() {
    System.out.println("setMapView");
    String searchText = "text:sweden";
    Map<String, String> filters = null; 
    List<GeoHashData> mapData = null; 
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
  }
  
  @Test
  public void testSetMapViewWithData() {
    System.out.println("setMapView");
    String searchText = "text:sweden";
    String coordinates = "N55.6800000000E14.2400000000";
    String geohash = "4_u3fh";
    Map<String, String> filters = null; 
    List<GeoHashData> mapData = new ArrayList(); 
    GeoHashData data = mock(GeoHashData.class);
    
    when(data.getGeohashString()).thenReturn(geohash);
    when(data.getTotal()).thenReturn(1);
    when(data.getCoordinates()).thenReturn(coordinates);
    mapData.add(data);
    
    
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
    assertEquals(instance.getModel().getMarkers().size(), 1);
  }

  /**
   * Test of onStateChange method, of class GeoHashMap.
   */
  @Test
  public void testOnStateChange() {
    System.out.println("onStateChange");
    
    StateChangeEvent event = mock(StateChangeEvent.class); 
    when(event.getZoomLevel()).thenReturn(5);
    
    LatLng latLng = mock(LatLng.class);
    when(latLng.getLat()).thenReturn(55.680);
    when(latLng.getLng()).thenReturn(14.2400);
    when(event.getCenter()).thenReturn(latLng);
    
    LatLngBounds bounds = mock(LatLngBounds.class);
    when(bounds.getNorthEast()).thenReturn(latLng);
    when(bounds.getSouthWest()).thenReturn(latLng);
    when(event.getBounds()).thenReturn(bounds);
     
    String coordinates = "N55.6800000000E14.2400000000";
    String geohash = "4_u3fh"; 
    List<GeoHashData> mapData = new ArrayList(); 
    GeoHashData data = mock(GeoHashData.class);
    
    when(data.getGeohashString()).thenReturn(geohash);
    when(data.getTotal()).thenReturn(1);
    when(data.getCoordinates()).thenReturn(coordinates);
    mapData.add(data); 
    when(solr.searchGeoHash(any(String.class), any(String.class), eq(null), any(String.class))).thenReturn(mapData); 
     
    instance.onStateChange(event); 
    verify(solr, times(1)).searchGeoHash(any(String.class), any(String.class), eq(null), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
    assertEquals(instance.getModel().getMarkers().size(), 1);
  }

  /**
   * Test of onMarkerSelect method, of class GeoHashMap.
   */
  @Test
  public void testOnMarkerSelectWithSingleMarker() {
    System.out.println("onMarkerSelect");
     
    String singleMarkerPath = instance.getSingleMarkerPath();
    OverlaySelectEvent event = mock(OverlaySelectEvent.class); 
    Marker marker = mock(Marker.class);  
    when(marker.getIcon()).thenReturn(singleMarkerPath);
    when(event.getOverlay()).thenReturn(marker);
    
    Map<String, String> filters = null; 
    List<SolrData> list = new ArrayList();
    SolrData data = mock(SolrData.class);
    list.add(data); 
    when(solr.searchSpatialData(any(String.class), eq(filters), any(String.class))).thenReturn(list);  
    
    instance.onMarkerSelect(event); 
    verify(solr, times(1)).searchSpatialData(any(String.class), eq(filters), any(String.class)); 
    assertNotNull(instance.getSelectedData());
             
  }

  /**
   * Test of getSelectedDataList method, of class GeoHashMap.
   */
//  @Test
  public void testGetSelectedDataList() {
    System.out.println("getSelectedDataList");
    GeoHashMap instance = new GeoHashMap();
    List<SolrData> expResult = null;
    List<SolrData> result = instance.getSelectedDataList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of showSelectedMarkDetail method, of class GeoHashMap.
   */
//  @Test
  public void testShowSelectedMarkDetail() {
    System.out.println("showSelectedMarkDetail");
    GeoHashMap instance = new GeoHashMap();
    instance.showSelectedMarkDetail();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCenterLat method, of class GeoHashMap.
   */
//  @Test
  public void testGetCenterLat() {
    System.out.println("getCenterLat");
    GeoHashMap instance = new GeoHashMap();
    Double expResult = null;
    Double result = instance.getCenterLat();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setCenterLat method, of class GeoHashMap.
   */
//  @Test
  public void testSetCenterLat() {
    System.out.println("setCenterLat");
    Double centerLat = null;
    GeoHashMap instance = new GeoHashMap();
    instance.setCenterLat(centerLat);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCenterLng method, of class GeoHashMap.
   */
//  @Test
  public void testGetCenterLng() {
    System.out.println("getCenterLng");
    GeoHashMap instance = new GeoHashMap();
    Double expResult = null;
    Double result = instance.getCenterLng();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setCenterLng method, of class GeoHashMap.
   */
//  @Test
  public void testSetCenterLng() {
    System.out.println("setCenterLng");
    Double centerLng = null;
    GeoHashMap instance = new GeoHashMap();
    instance.setCenterLng(centerLng);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getModel method, of class GeoHashMap.
   */
//  @Test
  public void testGetModel() {
    System.out.println("getModel");
    GeoHashMap instance = new GeoHashMap();
    MapModel expResult = null;
    MapModel result = instance.getModel();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMapKey method, of class GeoHashMap.
   */
//  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");
    GeoHashMap instance = new GeoHashMap();
    String expResult = "";
    String result = instance.getMapKey();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getZoom method, of class GeoHashMap.
   */
//  @Test
  public void testGetZoom() {
    System.out.println("getZoom");
    GeoHashMap instance = new GeoHashMap();
    int expResult = 0;
    int result = instance.getZoom();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isDisplayingColorBar method, of class GeoHashMap.
   */
//  @Test
  public void testIsDisplayingColorBar() {
    System.out.println("isDisplayingColorBar");
    GeoHashMap instance = new GeoHashMap();
    boolean expResult = false;
    boolean result = instance.isDisplayingColorBar();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getColorBar method, of class GeoHashMap.
   */
//  @Test
  public void testGetColorBar() {
    System.out.println("getColorBar");
    GeoHashMap instance = new GeoHashMap();
    List<String> expResult = null;
    List<String> result = instance.getColorBar();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMinCount method, of class GeoHashMap.
   */
//  @Test
  public void testGetMinCount() {
    System.out.println("getMinCount");
    GeoHashMap instance = new GeoHashMap();
    int expResult = 0;
    int result = instance.getMinCount();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMaxCount method, of class GeoHashMap.
   */
//  @Test
  public void testGetMaxCount() {
    System.out.println("getMaxCount");
    GeoHashMap instance = new GeoHashMap();
    int expResult = 0;
    int result = instance.getMaxCount();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedData method, of class GeoHashMap.
   */
//  @Test
  public void testGetSelectedData() {
    System.out.println("getSelectedData");
    GeoHashMap instance = new GeoHashMap();
    SolrData expResult = null;
    SolrData result = instance.getSelectedData();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedLocality method, of class GeoHashMap.
   */
//  @Test
  public void testGetSelectedLocality() {
    System.out.println("getSelectedLocality");
    GeoHashMap instance = new GeoHashMap();
    String expResult = "";
    String result = instance.getSelectedLocality();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedCoordinate method, of class GeoHashMap.
   */
//  @Test
  public void testGetSelectedCoordinate() {
    System.out.println("getSelectedCoordinate");
    GeoHashMap instance = new GeoHashMap();
    String expResult = "";
    String result = instance.getSelectedCoordinate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
