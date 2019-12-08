/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.MapModel;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class SingleMapTest {
  
  public SingleMapTest() {
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
   * Test of getSingledModel method, of class SingleMap.
   */
//  @Test
  public void testGetSingledModel() {
    System.out.println("getSingledModel");
    SolrData data = null;
    SingleMap instance = new SingleMap();
    MapModel expResult = null;
    MapModel result = instance.getSingledModel(data);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onSingleMarkerSelect method, of class SingleMap.
   */
//  @Test
  public void testOnSingleMarkerSelect() {
    System.out.println("onSingleMarkerSelect");
    OverlaySelectEvent event = null;
    SingleMap instance = new SingleMap();
    instance.onSingleMarkerSelect(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
