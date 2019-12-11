package se.nrm.dina.web.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Stream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Before; 
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock; 
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
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
  private static String serverName;
  private static int serverPort;
  private static String path;
  
  private static String searchText;
  private static String coordinates;
  private static String geohash;
  private static Map<String, String> filters; 
  private static List<GeoHashData> mapData; 
  
  
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
  
    
  @BeforeClass
  public static void setUpClass() {
    searchText = "text:sweden";
    coordinates = "N55.6800000000E14.2400000000";
    geohash = "4_u3fh";
    
    serverName = "naturarv";
    path = "https://naturarv.nrm.se";
    serverPort = 8080;
    
    filters = null; 
    mapData = new ArrayList();
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  
  @Before
  public void setUp() { 
    context = ContextMocker.mockFacesContext(); 
     
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
  public void testSetMapViewEmptyMapData() {
    System.out.println("setMapView");
   
    mapData = new ArrayList<>();
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
    assertTrue(instance.getModel().getMarkers().isEmpty());
    assertTrue(instance.getModel().getRectangles().isEmpty());
    assertTrue(instance.getModel().getPolylines().isEmpty());
  }
  
  @Test
  public void testSetMapViewNullMapData() {
    System.out.println("setMapView");
 
    mapData = null; 
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
    assertTrue(instance.getModel().getMarkers().isEmpty());
    assertTrue(instance.getModel().getRectangles().isEmpty());
    assertTrue(instance.getModel().getPolylines().isEmpty());
  }
  
  @Test
  public void testSetMapViewWithOneData() {
    System.out.println("setMapView");
  
    mapData = new ArrayList(); 
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
    assertTrue(instance.getModel().getRectangles().isEmpty());
    assertTrue(instance.getModel().getPolylines().isEmpty());
  }

  @Test
  public void testSetMapViewWithMultipleDataTwo() {
    System.out.println("setMapView");
  
    mapData = new ArrayList(); 
    GeoHashData data1 = mock(GeoHashData.class);
    GeoHashData data2 = mock(GeoHashData.class);  
     
    when(data1.getGeohashString()).thenReturn(geohash);
    when(data1.getTotal()).thenReturn(120); 
    when(data1.getCoordinates()).thenReturn(coordinates);
    mapData.add(data1);
    
    String geohash2 = "4_u6dc";
    String coordinates2 = "N59.28330E15.21670";
    when(data2.getGeohashString()).thenReturn(geohash2);
    when(data2.getTotal()).thenReturn(1); 
    when(data2.getCoordinates()).thenReturn(coordinates2);
    mapData.add(data2);
     
    when(solr.searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class))).thenReturn(mapData); 
    
    Map<String, Integer> map1 = new HashMap();
    map1.put(coordinates, 1); 
    map1.put(coordinates2, 11); 
    when(solr.searchSmallDataSet(searchText, filters, geohash)).thenReturn(map1);
     
            
    instance.setMapView(searchText, filters); 
    verify(solr, times(1)).searchGeoHash(eq(searchText), any(String.class), eq(filters), any(String.class)); 
    verify(solr, times(1)).searchSmallDataSet(searchText, filters, geohash); 
    assertFalse(instance.isDisplayingColorBar());
    assertTrue(instance.getColorBar().isEmpty());
    assertEquals(instance.getModel().getMarkers().size(), 3);
    assertTrue(instance.getModel().getRectangles().isEmpty());
    assertEquals(instance.getModel().getPolylines().size(), 0);  
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
      
    List<GeoHashData> mockList = mock(List.class);
    when(mockList.size()).thenReturn(210);   
     
    List<String> geoHashList = new ArrayList();
    geoHashList.add("2_u3");
    geoHashList.add("3_u3f");
    geoHashList.add("4_u3fh");
    geoHashList.add("5_u3fhu");
    geoHashList.add("6_u3fhu0");
    
    GeoHashData data = mock(GeoHashData.class); 
    when(data.getGeohashString()).thenReturn(geohash);
    when(data.getTotal()).thenReturn(11);
    when(data.getCoordinates()).thenReturn(coordinates);
    when(data.getGeohashList()).thenReturn(geoHashList);
    when(mockList.get(any(int.class))).thenReturn(data);
     
    Stream<GeoHashData> value = Stream.of(data, data, data, data, data, data, data, data, data, data, data); 
    when(mockList.stream()).thenReturn(value);
     
    when(solr.searchGeoHash(any(String.class), any(String.class), eq(null), any(String.class))).thenReturn(mockList); 
     
    TreeSet<Integer> set = mock(TreeSet.class);
    when(set.first()).thenReturn(2);
    when(set.last()).thenReturn(20);
    when(set.size()).thenReturn(8);
    when(solr.getSet()).thenReturn(set);
    
    instance.onStateChange(event); 
    verify(solr, times(1)).searchGeoHash(any(String.class), any(String.class), eq(null), any(String.class)); 
    assertTrue(instance.isDisplayingColorBar());
    assertEquals(instance.getColorBar().size(), 6);
    assertEquals(instance.getModel().getRectangles().size(), 11);
  }
  
 /**
   * Test of onMarkerSelect method, of class GeoHashMap.
   */
  @Test
  public void testOnMarkerSelect() {
    System.out.println("onMarkerSelect");
      
    OverlaySelectEvent event = mock(OverlaySelectEvent.class); 
    Overlay overlay = mock(Overlay.class);   
    when(event.getOverlay()).thenReturn(overlay);
     
    instance.onMarkerSelect(event); 
    verifyZeroInteractions(solr); 
    assertTrue(instance.getSelectedData() == null); 
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
     
    List<SolrData> list = new ArrayList();
    SolrData data = mock(SolrData.class);
    list.add(data); 
    when(solr.searchSpatialData(any(String.class), eq(filters), any(String.class))).thenReturn(list);  
    
    instance.onMarkerSelect(event); 
    verify(solr, times(1)).searchSpatialData(any(String.class), eq(filters), any(String.class)); 
    assertNotNull(instance.getSelectedData()); 
  }
  
  @Test
  public void testOnMarkerSelectWithPinkMarker() {
    System.out.println("onMarkerSelect");
    
    SolrData data = mock(SolrData.class);
     
    String pinkMarkerPath = instance.getPinkMarkerPath();
    OverlaySelectEvent event = mock(OverlaySelectEvent.class); 
    Marker marker = mock(Marker.class);  
    when(marker.getIcon()).thenReturn(pinkMarkerPath);
    when(marker.getData()).thenReturn(data);
    when(event.getOverlay()).thenReturn(marker); 
    
    instance.onMarkerSelect(event); 
    verifyZeroInteractions(solr); 
    assertNotNull(instance.getSelectedData()); 
  }
  
  @Test
  public void testOnMarkerSelectWithLargeSize() {
    System.out.println("onMarkerSelect");
    
    String locality = "Tyreso, Sweden";
    String coordinateString = "N55.6800000000E14.2400000000"; 
    
    String plusMarkerPath = instance.getPlusMarkerPath();
    OverlaySelectEvent event = mock(OverlaySelectEvent.class); 
    Marker marker = mock(Marker.class);  
    when(marker.getIcon()).thenReturn(plusMarkerPath); 
    when(event.getOverlay()).thenReturn(marker); 
     
    SolrData data = mock(SolrData.class);
    when(data.getLocality()).thenReturn(locality);
    when(data.getCoordinateString()).thenReturn(coordinateString);
    List<SolrData> mockList = mock(List.class);
    when(mockList.size()).thenReturn(210);  
    when(mockList.get(0)).thenReturn(data);
     
    when(solr.searchSpatialData(any(String.class), eq(filters), any(String.class))).thenReturn(mockList);  
     
    instance.onMarkerSelect(event); 
    verify(solr, times(1)).searchSpatialData(any(String.class), eq(filters), any(String.class)); 
    assertTrue(instance.getSelectedData() == null); 
    assertEquals(instance.getSelectedCoordinate(), coordinateString);
    assertEquals(instance.getSelectedLocality(), locality);
  }

  /**
   * Test of getSelectedDataList method, of class GeoHashMap.
   */
//  @Test
  public void testGetSelectedDataList() {
    System.out.println("getSelectedDataList"); 
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
