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
import se.nrm.dina.web.portal.model.CollectionData;

/**
 *
 * @author idali
 */
public class StatisticBeanTest {
  
  public StatisticBeanTest() {
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
   * Test of init method, of class StatisticBean.
   */
//  @Test
  public void testInit() {
    System.out.println("init");
    StatisticBean instance = new StatisticBean();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeLanguage method, of class StatisticBean.
   */
//  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    boolean isSwedish = false;
    StatisticBean instance = new StatisticBean();
    instance.changeLanguage(isSwedish);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of resetData method, of class StatisticBean.
   */
//  @Test
  public void testResetData() {
    System.out.println("resetData");
    String text = "";
    Map<String, String> queries = null;
    StatisticBean instance = new StatisticBean();
    instance.resetData(text, queries);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of resetAllData method, of class StatisticBean.
   */
//  @Test
  public void testResetAllData() {
    System.out.println("resetAllData");
    StatisticBean instance = new StatisticBean();
    instance.resetAllData();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredCollections method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredCollections() {
    System.out.println("getFilteredCollections");
    StatisticBean instance = new StatisticBean();
    List<CollectionData> expResult = null;
    List<CollectionData> result = instance.getFilteredCollections();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCollections method, of class StatisticBean.
   */
//  @Test
  public void testGetCollections() {
    System.out.println("getCollections");
    StatisticBean instance = new StatisticBean();
    List<CollectionData> expResult = null;
    List<CollectionData> result = instance.getCollections();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalRecords method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalRecords() {
    System.out.println("getTotalRecords");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalRecords();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredTotalDnas method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredTotalDnas() {
    System.out.println("getFilteredTotalDnas");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getFilteredTotalDnas();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalDnas method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalDnas() {
    System.out.println("getTotalDnas");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalDnas();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredTotalImages method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredTotalImages() {
    System.out.println("getFilteredTotalImages");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getFilteredTotalImages();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalImages method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalImages() {
    System.out.println("getTotalImages");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalImages();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredTotalMaps method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredTotalMaps() {
    System.out.println("getFilteredTotalMaps");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getFilteredTotalMaps();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalMaps method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalMaps() {
    System.out.println("getTotalMaps");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalMaps();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredTotalInSweden method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredTotalInSweden() {
    System.out.println("getFilteredTotalInSweden");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getFilteredTotalInSweden();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalInSweden method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalInSweden() {
    System.out.println("getTotalInSweden");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalInSweden();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredTotalType method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredTotalType() {
    System.out.println("getFilteredTotalType");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getFilteredTotalType();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalType method, of class StatisticBean.
   */
//  @Test
  public void testGetTotalType() {
    System.out.println("getTotalType");
    StatisticBean instance = new StatisticBean();
    int expResult = 0;
    int result = instance.getTotalType();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilteredInstitutions method, of class StatisticBean.
   */
//  @Test
  public void testGetFilteredInstitutions() {
    System.out.println("getFilteredInstitutions");
    StatisticBean instance = new StatisticBean();
    Map<String, Integer> expResult = null;
    Map<String, Integer> result = instance.getFilteredInstitutions();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getInstitutions method, of class StatisticBean.
   */
//  @Test
  public void testGetInstitutions() {
    System.out.println("getInstitutions");
    StatisticBean instance = new StatisticBean();
    Map<String, Integer> expResult = null;
    Map<String, Integer> result = instance.getInstitutions();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
