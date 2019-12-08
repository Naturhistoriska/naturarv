/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import se.nrm.dina.web.portal.model.CollectionData;

/**
 *
 * @author idali
 */
public class CollectionsViewTest {
  
  public CollectionsViewTest() {
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
   * Test of init method, of class CollectionsView.
   */
//  @Test
  public void testInit() {
    System.out.println("init");
    CollectionsView instance = new CollectionsView();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onTabChange method, of class CollectionsView.
   */
//  @Test
  public void testOnTabChange() {
    System.out.println("onTabChange");
    TabChangeEvent event = null;
    CollectionsView instance = new CollectionsView();
    instance.onTabChange(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of onTabClose method, of class CollectionsView.
   */
//  @Test
  public void testOnTabClose() {
    System.out.println("onTabClose");
    TabCloseEvent event = null;
    CollectionsView instance = new CollectionsView();
    instance.onTabClose(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCollections method, of class CollectionsView.
   */
//  @Test
  public void testGetCollections() {
    System.out.println("getCollections");
    CollectionsView instance = new CollectionsView();
    List<CollectionData> expResult = null;
    List<CollectionData> result = instance.getCollections();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getActiveIndex method, of class CollectionsView.
   */
//  @Test
  public void testGetActiveIndex() {
    System.out.println("getActiveIndex");
    CollectionsView instance = new CollectionsView();
    int expResult = 0;
    int result = instance.getActiveIndex();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setActiveIndex method, of class CollectionsView.
   */
//  @Test
  public void testSetActiveIndex() {
    System.out.println("setActiveIndex");
    int activeIndex = 0;
    CollectionsView instance = new CollectionsView();
    instance.setActiveIndex(activeIndex);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
