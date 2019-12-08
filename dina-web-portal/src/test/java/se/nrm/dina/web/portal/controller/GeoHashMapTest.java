/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.MapModel;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class GeoHashMapTest {
  
  public GeoHashMapTest() {
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
   * Test of init method, of class GeoHashMap.
   */
//  @Test
  public void testInit() {
    System.out.println("init");
    GeoHashMap instance = new GeoHashMap();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMapView method, of class GeoHashMap.
   */
//  @Test
  public void testSetMapView() {
    System.out.println("setMapView");
    String searchText = "";
    Map<String, String> filters = null;
    GeoHashMap instance = new GeoHashMap();
    instance.setMapView(searchText, filters);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onStateChange method, of class GeoHashMap.
   */
//  @Test
  public void testOnStateChange() {
    System.out.println("onStateChange");
    StateChangeEvent event = null;
    GeoHashMap instance = new GeoHashMap();
    instance.onStateChange(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onMarkerSelect method, of class GeoHashMap.
   */
//  @Test
  public void testOnMarkerSelect() {
    System.out.println("onMarkerSelect");
    OverlaySelectEvent event = null;
    GeoHashMap instance = new GeoHashMap();
    instance.onMarkerSelect(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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
