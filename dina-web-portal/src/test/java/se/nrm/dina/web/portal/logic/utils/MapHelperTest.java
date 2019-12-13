/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import ch.hsr.geohash.GeoHash;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Rectangle;

/**
 *
 * @author idali
 */
public class MapHelperTest {
  
  public MapHelperTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getInstance method, of class MapHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    MapHelper expResult = null;
    MapHelper result = MapHelper.getInstance();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDefaultRegion method, of class MapHelper.
   */
  @Test
  public void testGetDefaultRegion() {
    System.out.println("getDefaultRegion");
    MapHelper instance = new MapHelper();
    String expResult = "";
    String result = instance.getDefaultRegion();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDefaultZoom method, of class MapHelper.
   */
  @Test
  public void testGetDefaultZoom() {
    System.out.println("getDefaultZoom");
    MapHelper instance = new MapHelper();
    int expResult = 0;
    int result = instance.getDefaultZoom();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of buildRectangle method, of class MapHelper.
   */
  @Test
  public void testBuildRectangle() {
    System.out.println("buildRectangle");
    String geoHashData = "";
    int total = 0;
    String colorCode = "";
    MapHelper instance = new MapHelper();
    Rectangle expResult = null;
    Rectangle result = instance.buildRectangle(geoHashData, total, colorCode);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getGridLevel method, of class MapHelper.
   */
  @Test
  public void testGetGridLevel() {
    System.out.println("getGridLevel");
    int zoom = 0;
    MapHelper instance = new MapHelper();
    int expResult = 0;
    int result = instance.getGridLevel(zoom);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLatLng method, of class MapHelper.
   */
  @Test
  public void testGetLatLng() {
    System.out.println("getLatLng");
    LatLng coordOrg = null;
    int index = 0;
    int size = 0;
    int zoom = 0;
    MapHelper instance = new MapHelper();
    LatLng expResult = null;
    LatLng result = instance.getLatLng(coordOrg, index, size, zoom);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getGeoHashPrefix method, of class MapHelper.
   */
  @Test
  public void testGetGeoHashPrefix() {
    System.out.println("getGeoHashPrefix");
    int zoom = 0;
    MapHelper instance = new MapHelper();
    String expResult = "";
    String result = instance.getGeoHashPrefix(zoom);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of resetZoom method, of class MapHelper.
   */
  @Test
  public void testResetZoom_4args() {
    System.out.println("resetZoom");
    double minLat = 0.0;
    double minLng = 0.0;
    double maxLat = 0.0;
    double maxLng = 0.0;
    MapHelper instance = new MapHelper();
    int expResult = 0;
    int result = instance.resetZoom(minLat, minLng, maxLat, maxLng);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of resetZoom method, of class MapHelper.
   */
  @Test
  public void testResetZoom_GeoHash() {
    System.out.println("resetZoom");
    GeoHash geoHash = null;
    MapHelper instance = new MapHelper();
    int expResult = 0;
    int result = instance.resetZoom(geoHash);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getZoomLevel method, of class MapHelper.
   */
  @Test
  public void testGetZoomLevel() {
    System.out.println("getZoomLevel");
    double latD = 0.0;
    double lngD = 0.0;
    MapHelper instance = new MapHelper();
    int expResult = 0;
    int result = instance.getZoomLevel(latD, lngD);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setDefaultColorBar method, of class MapHelper.
   */
  @Test
  public void testSetDefaultColorBar() {
    System.out.println("setDefaultColorBar");
    MapHelper instance = new MapHelper();
    List<String> expResult = null;
    List<String> result = instance.setDefaultColorBar();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setColorBar method, of class MapHelper.
   */
  @Test
  public void testSetColorBar() {
    System.out.println("setColorBar");
    int size = 0;
    MapHelper instance = new MapHelper();
    List<String> expResult = null;
    List<String> result = instance.setColorBar(size);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getColorCode method, of class MapHelper.
   */
  @Test
  public void testGetColorCode() {
    System.out.println("getColorCode");
    int colorIndex = 0;
    int setSize = 0;
    boolean isFirst = false;
    boolean isLast = false;
    MapHelper instance = new MapHelper();
    String expResult = "";
    String result = instance.getColorCode(colorIndex, setSize, isFirst, isLast);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMapMarkPath method, of class MapHelper.
   */
  @Test
  public void testGetMapMarkPath() {
    System.out.println("getMapMarkPath");
    String marker = "";
    MapHelper instance = new MapHelper();
    String expResult = "";
    String result = instance.getMapMarkPath(marker);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
