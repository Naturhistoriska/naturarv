package se.nrm.dina.web.portal.logic.utils;

import ch.hsr.geohash.GeoHash;
import java.util.List; 
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock; 
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Rectangle;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.model.RectangleData; 

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class MapHelperTest {
  
  private MapHelper instance;
  
  private final String defaultSearchRange = "[\"-180 -90\" TO \"180 90\"]";
  private final int defaultRangeZoom = 2;
  private final String geohash = "u3fh";
  
  private final String servername = "naturarv";
  private final int serverPort = 8080;
  private final String path = "";
  
  private FacesContext context;
  
  @Mock
  private ExternalContext externalContext; 
  @Mock
  HttpServletRequest request;
  
  public MapHelperTest() {
   
  }
 
  @Before
  public void setUp() {
      
    when(request.getServerName()).thenReturn(servername);
    when(request.getServerPort()).thenReturn(serverPort);
    when(externalContext.getRequest()).thenReturn(request);
    when(externalContext.getRequestContextPath()).thenReturn(path); 
    
    context = ContextMocker.mockFacesContext();
    when(context.getExternalContext()).thenReturn(externalContext);
    
    instance = MapHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class MapHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance); 
  }

  /**
   * Test of getDefaultRegion method, of class MapHelper.
   */
  @Test
  public void testGetDefaultRegion() {
    System.out.println("getDefaultRegion");  
    String result = instance.getDefaultRegion();
    assertEquals(defaultSearchRange, result); 
  }

  /**
   * Test of getDefaultZoom method, of class MapHelper.
   */
  @Test
  public void testGetDefaultZoom() {
    System.out.println("getDefaultZoom");  
    int result = instance.getDefaultZoom();
    assertEquals(defaultRangeZoom, result); 
  }

  /**
   * Test of buildRectangle method, of class MapHelper.
   */
  @Test
  public void testBuildRectangle() {
    System.out.println("buildRectangle"); 
    int total = 100;
    String colorCode = "#750202"; 
     
    Rectangle result = instance.buildRectangle(geohash, total, colorCode);
    RectangleData data = (RectangleData) result.getData();
    
    assertNotNull(result);  
    assertEquals(data.getCount(), total);
    assertEquals(data.getGeohash(), geohash);
    assertEquals(result.getFillColor(), colorCode); 
  }

  /**
   * Test of getGridLevel method, of class MapHelper.
   */
  @Test
  public void testGetGridLevelZoom0() {
    System.out.println("getGridLevel");
    int zoom = 0; 
    int expResult = 2;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom1() {
    System.out.println("getGridLevel");
    int zoom = 1; 
    int expResult = 2;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom2() {
    System.out.println("getGridLevel");
    int zoom = 2; 
    int expResult = 2;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom3() {
    System.out.println("getGridLevel");
    int zoom = 3; 
    int expResult = 3;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom4() {
    System.out.println("getGridLevel");
    int zoom = 4; 
    int expResult = 3;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom5() {
    System.out.println("getGridLevel");
    int zoom = 5; 
    int expResult = 4;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom6() {
    System.out.println("getGridLevel");
    int zoom = 6; 
    int expResult = 4;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom7() {
    System.out.println("getGridLevel");
    int zoom = 7; 
    int expResult = 4;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom8() {
    System.out.println("getGridLevel");
    int zoom = 8; 
    int expResult = 5;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom9() {
    System.out.println("getGridLevel");
    int zoom = 9; 
    int expResult = 5;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom10() {
    System.out.println("getGridLevel");
    int zoom = 10; 
    int expResult = 6;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom11() {
    System.out.println("getGridLevel");
    int zoom = 11; 
    int expResult = 6;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom12() {
    System.out.println("getGridLevel");
    int zoom = 12; 
    int expResult = 6;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom13() {
    System.out.println("getGridLevel");
    int zoom = 13; 
    int expResult = 7;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom14() {
    System.out.println("getGridLevel");
    int zoom = 14; 
    int expResult = 7;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGridLevelZoom15() {
    System.out.println("getGridLevel");
    int zoom = 15; 
    int expResult = 7;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result); 
  }
  

  /**
   * Test of getLatLng method, of class MapHelper.
   */
  @Test
  public void testGetLatLng() {
    System.out.println("getLatLng");
     
    LatLng coordOrg = new LatLng(55.680, 14.2400);
    int index = 5;
    int size = 8;
    int zoom = 4;  
    LatLng result = instance.getLatLng(coordOrg, index, size, zoom);
    assertEquals(String.valueOf(result.getLat()), "53.558679656440354"); 
    assertEquals(String.valueOf(result.getLng()), "12.118679656440356"); 
  }

  /**
   * Test of getGeoHashPrefix method, of class MapHelper.
   */
  @Test
  public void testGetGeoHashPrefixZoom0() {
    System.out.println("getGeoHashPrefix");
    int zoom = 0; 
    String expResult = "2_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom1() {
    System.out.println("getGeoHashPrefix");
    int zoom = 1; 
    String expResult = "2_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom2() {
    System.out.println("getGeoHashPrefix");
    int zoom = 2; 
    String expResult = "2_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom3() {
    System.out.println("getGeoHashPrefix");
    int zoom = 3; 
    String expResult = "3_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom4() {
    System.out.println("getGeoHashPrefix");
    int zoom = 4; 
    String expResult = "3_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom5() {
    System.out.println("getGeoHashPrefix");
    int zoom = 5; 
    String expResult = "3_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom6() {
    System.out.println("getGeoHashPrefix");
    int zoom = 6; 
    String expResult = "4_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom7() {
    System.out.println("getGeoHashPrefix");
    int zoom = 7; 
    String expResult = "4_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom8() {
    System.out.println("getGeoHashPrefix");
    int zoom = 8; 
    String expResult = "5_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom9() {
    System.out.println("getGeoHashPrefix");
    int zoom = 9; 
    String expResult = "5_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom10() {
    System.out.println("getGeoHashPrefix");
    int zoom = 10; 
    String expResult = "5_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom11() {
    System.out.println("getGeoHashPrefix");
    int zoom = 11; 
    String expResult = "6_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom12() {
    System.out.println("getGeoHashPrefix");
    int zoom = 12; 
    String expResult = "6_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom13() {
    System.out.println("getGeoHashPrefix");
    int zoom = 13; 
    String expResult = "7_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom14() {
    System.out.println("getGeoHashPrefix");
    int zoom = 14; 
    String expResult = "7_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom15() {
    System.out.println("getGeoHashPrefix");
    int zoom = 15; 
    String expResult = "7_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom16() {
    System.out.println("getGeoHashPrefix");
    int zoom = 16; 
    String expResult = "8_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom17() {
    System.out.println("getGeoHashPrefix");
    int zoom = 17; 
    String expResult = "8_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom18() {
    System.out.println("getGeoHashPrefix");
    int zoom = 18; 
    String expResult = "8_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom19() {
    System.out.println("getGeoHashPrefix");
    int zoom = 19; 
    String expResult = "9_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom20() {
    System.out.println("getGeoHashPrefix");
    int zoom = 20; 
    String expResult = "9_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetGeoHashPrefixZoom21() {
    System.out.println("getGeoHashPrefix");
    int zoom = 21; 
    String expResult = "9_";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result); 
  }      
 
  /**
   * Test of resetZoom method, of class MapHelper.
   */
  @Test
  public void testResetZoom_4args() {
    System.out.println("resetZoom");
    double minLat = 8.0;
    double minLng = 23.0;
    double maxLat = 68.0;
    double maxLng = 56.0; 
    
    int expResult = 3;
    int result = instance.resetZoom(minLat, minLng, maxLat, maxLng); 
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testResetZoom_4args1() {
    System.out.println("resetZoom");
    double minLat = 8.0;
    double minLng = 158.0;
    double maxLat = 68.0;
    double maxLng = 8.0; 
    
    int expResult = 2;
    int result = instance.resetZoom(minLat, minLng, maxLat, maxLng); 
    assertEquals(expResult, result);  
  }
    
  /**
   * Test of resetZoom method, of class MapHelper.
   */
  @Test
  public void testResetZoom_GeoHash() {
    System.out.println("resetZoom");
    
    GeoHash geoHash = GeoHash.fromGeohashString(this.geohash); 
    int expResult = 11;
    int result = instance.resetZoom(geoHash);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getZoomLevel method, of class MapHelper.
   */
  @Test
  public void testGetZoomLevel1() {
    System.out.println("getZoomLevel");
    double latD = 56.0;
    double lngD = 250.0; 
    int expResult = 1;
    int result = instance.getZoomLevel(latD, lngD);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetZoomLevel2() {
    System.out.println("getZoomLevel");
    double latD = 110.0;
    double lngD = 130.0; 
    int expResult = 1;
    int result = instance.getZoomLevel(latD, lngD);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetZoomLevel3() {
    System.out.println("getZoomLevel");
    double latD = 10.0;
    double lngD = 100.0; 
    int expResult = 3;
    int result = instance.getZoomLevel(latD, lngD);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetZoomLevel4() {
    System.out.println("getZoomLevel");
    double latD = 10.0;
    double lngD = 50.0; 
    int expResult = 4;
    int result = instance.getZoomLevel(latD, lngD);
    assertEquals(expResult, result); 
  }
  
  

  /**
   * Test of setDefaultColorBar method, of class MapHelper.
   */
  @Test
  public void testSetDefaultColorBar() {
    System.out.println("setDefaultColorBar");  
    List<String> result = instance.setDefaultColorBar();
    assertNotNull(result); 
    assertEquals(result.size(), 6);
  }

  /**
   * Test of setColorBar method, of class MapHelper.
   */
  @Test
  public void testSetColorBar1() {
    System.out.println("setColorBar");
    int size = 1;  
    List<String> result = instance.setColorBar(size);
    assertEquals(1, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
  }
  
  @Test
  public void testSetColorBar2() {
    System.out.println("setColorBar");
    int size = 2;  
    List<String> result = instance.setColorBar(size);
    assertEquals(2, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
    assertTrue(result.contains("#790022"));
  }

  @Test
  public void testSetColorBar3() {
    System.out.println("setColorBar");
    int size = 3;  
    List<String> result = instance.setColorBar(size);
    assertEquals(3, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
    assertTrue(result.contains("#DA323D"));
    assertTrue(result.contains("#790022"));
  }
  
  @Test
  public void testSetColorBar4() {
    System.out.println("setColorBar");
    int size = 4;  
    List<String> result = instance.setColorBar(size);
    assertEquals(4, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
    assertTrue(result.contains("#DA323D"));
    assertTrue(result.contains("#A2002E"));
    assertTrue(result.contains("#790022"));
  }
  
  @Test
  public void testSetColorBar5() {
    System.out.println("setColorBar");
    int size = 5;  
    List<String> result = instance.setColorBar(size);
    assertEquals(5, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
    assertTrue(result.contains("#E98990"));
    assertTrue(result.contains("#DA323D")); 
    assertTrue(result.contains("#A2002E"));
    assertTrue(result.contains("#790022"));
  }
  
  @Test
  public void testSetColorBar6() {
    System.out.println("setColorBar");
    int size = 6;  
    List<String> result = instance.setColorBar(size);
    assertEquals(6, result.size()); 
    assertTrue(result.contains("#F7C7C7"));
    assertTrue(result.contains("#E98990"));
    assertTrue(result.contains("#DA323D")); 
    assertTrue(result.contains("#A2002E"));
    assertTrue(result.contains("#8E0028"));
    assertTrue(result.contains("#790022"));
  } 
  
  /**
   * Test of getColorCode method, of class MapHelper.
   */
  @Test
  public void testGetColorCode() {
    System.out.println("getColorCode");
    
    int colorIndex = 0;
    int setSize = 10;
    boolean isFirst = true;
    boolean isLast = false; 
    String expResult = "#F7C7C7";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetColorCode1() {
    System.out.println("getColorCode");
    
    int colorIndex = 0;
    int setSize = 6;
    boolean isFirst = false;
    boolean isLast = true; 
    String expResult = "#790022";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result); 
  }
   
  @Test
  public void testGetColorCode2() {
    System.out.println("getColorCode");
    
    int colorIndex = 7;
    int setSize = 16;
    boolean isFirst = false;
    boolean isLast = false; 
    String expResult = "#DA323D";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetColorCode3() {
    System.out.println("getColorCode");
    
    int colorIndex = 10;
    int setSize = 16;
    boolean isFirst = false;
    boolean isLast = false; 
    String expResult = "#A2002E";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetColorCode4() {
    System.out.println("getColorCode");
    
    int colorIndex = 18;
    int setSize = 16;
    boolean isFirst = false;
    boolean isLast = false; 
    String expResult = "#8E0028";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMapMarkPath method, of class MapHelper.
   */
  @Test
  public void testGetMapMarkPath() {
    System.out.println("getMapMarkPath");
    String marker = "single"; 
    String expResult = "http://naturarv:8080/resources/images/icons/red10.png";
    String result = instance.getMapMarkPath(marker);
     
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetMapMarkPath1() {
    System.out.println("getMapMarkPath");
    String marker = "pink"; 
    String expResult = "http://naturarv:8080/resources/images/icons/pink_10.png";
    String result = instance.getMapMarkPath(marker);
     
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetMapMarkPath2() {
    System.out.println("getMapMarkPath");
    String marker = "plus"; 
    String expResult = "http://naturarv:8080/resources/images/icons/marker_red_plus_19.png";
    String result = instance.getMapMarkPath(marker);
     
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetMapMarkPath3() {
    System.out.println("getMapMarkPath");
    String marker = "minus"; 
    String expResult = "http://naturarv:8080/resources/images/icons/marker_red_minus_19.png";
    String result = instance.getMapMarkPath(marker);
     
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetMapMarkPath4() {
    System.out.println("getMapMarkPath");
    String marker = "sometext"; 
    String expResult = "http://naturarv:8080/resources/images/icons/red10.png";
    String result = instance.getMapMarkPath(marker);
     
    assertEquals(expResult, result); 
  }
  
}
