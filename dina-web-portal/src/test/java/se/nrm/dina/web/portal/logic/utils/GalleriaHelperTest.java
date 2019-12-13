/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class GalleriaHelperTest {
  
  public GalleriaHelperTest() {
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
   * Test of getInstance method, of class GalleriaHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    GalleriaHelper expResult = null;
    GalleriaHelper result = GalleriaHelper.getInstance();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAllViewList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllViewList() {
    System.out.println("setAllViewList");
    List<String> viewList = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.setAllViewList(viewList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAllPartsList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllPartsList() {
    System.out.println("setAllPartsList");
    List<String> partsList = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.setAllPartsList(partsList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAllSexList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllSexList() {
    System.out.println("setAllSexList");
    List<String> sexList = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.setAllSexList(sexList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setAllStagesList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllStagesList() {
    System.out.println("setAllStagesList");
    List<String> stagesList = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.setAllStagesList(stagesList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of viewOptionChanged method, of class GalleriaHelper.
   */
  @Test
  public void testViewOptionChanged() {
    System.out.println("viewOptionChanged");
    List<String> selectedViews = null;
    List<String> viewList = null;
    List<String> partsList = null;
    List<String> sexList = null;
    List<String> stagesList = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.viewOptionChanged(selectedViews, viewList, partsList, sexList, stagesList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSelectedList method, of class GalleriaHelper.
   */
  @Test
  public void testSetSelectedList() {
    System.out.println("setSelectedList");
    List<String> list = null;
    GalleriaHelper instance = new GalleriaHelper();
    instance.setSelectedList(list);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of hasAll method, of class GalleriaHelper.
   */
  @Test
  public void testHasAll() {
    System.out.println("hasAll");
    List<String> list = null;
    int count = 0;
    GalleriaHelper instance = new GalleriaHelper();
    boolean expResult = false;
    boolean result = instance.hasAll(list, count);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
