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
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.model.ImageModel;

/**
 *
 * @author idali
 */
public class GalleriaBeanTest {
  
  public GalleriaBeanTest() {
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
   * Test of setImageView method, of class GalleriaBean.
   */
//  @Test
  public void testSetImageView() {
    System.out.println("setImageView");
    int totalImages = 0;
    String searchText = "";
    Map<String, String> filterMap = null;
    GalleriaBean instance = new GalleriaBean();
    instance.setImageView(totalImages, searchText, filterMap);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getModel method, of class GalleriaBean.
   */
//  @Test
  public void testGetModel() {
    System.out.println("getModel");
    GalleriaBean instance = new GalleriaBean();
    LazyDataModel<ImageModel> expResult = null;
    LazyDataModel<ImageModel> result = instance.getModel();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectViews method, of class GalleriaBean.
   */
//  @Test
  public void testSelectViews() {
    System.out.println("selectViews");
    GalleriaBean instance = new GalleriaBean();
    instance.selectViews();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectParts method, of class GalleriaBean.
   */
//  @Test
  public void testSelectParts() {
    System.out.println("selectParts");
    GalleriaBean instance = new GalleriaBean();
    instance.selectParts();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectSexes method, of class GalleriaBean.
   */
//  @Test
  public void testSelectSexes() {
    System.out.println("selectSexes");
    GalleriaBean instance = new GalleriaBean();
    instance.selectSexes();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectStages method, of class GalleriaBean.
   */
//  @Test
  public void testSelectStages() {
    System.out.println("selectStages");
    GalleriaBean instance = new GalleriaBean();
    instance.selectStages();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getViewList method, of class GalleriaBean.
   */
//  @Test
  public void testGetViewList() {
    System.out.println("getViewList");
    GalleriaBean instance = new GalleriaBean();
    List<String> expResult = null;
    List<String> result = instance.getViewList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setViewList method, of class GalleriaBean.
   */
//  @Test
  public void testSetViewList() {
    System.out.println("setViewList");
    List<String> viewList = null;
    GalleriaBean instance = new GalleriaBean();
    instance.setViewList(viewList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPartsList method, of class GalleriaBean.
   */
//  @Test
  public void testGetPartsList() {
    System.out.println("getPartsList");
    GalleriaBean instance = new GalleriaBean();
    List<String> expResult = null;
    List<String> result = instance.getPartsList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPartsList method, of class GalleriaBean.
   */
//  @Test
  public void testSetPartsList() {
    System.out.println("setPartsList");
    List<String> partsList = null;
    GalleriaBean instance = new GalleriaBean();
    instance.setPartsList(partsList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSexList method, of class GalleriaBean.
   */
//  @Test
  public void testGetSexList() {
    System.out.println("getSexList");
    GalleriaBean instance = new GalleriaBean();
    List<String> expResult = null;
    List<String> result = instance.getSexList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSexList method, of class GalleriaBean.
   */
//  @Test
  public void testSetSexList() {
    System.out.println("setSexList");
    List<String> sexList = null;
    GalleriaBean instance = new GalleriaBean();
    instance.setSexList(sexList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getStageList method, of class GalleriaBean.
   */
//  @Test
  public void testGetStageList() {
    System.out.println("getStageList");
    GalleriaBean instance = new GalleriaBean();
    List<String> expResult = null;
    List<String> result = instance.getStageList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setStageList method, of class GalleriaBean.
   */
//  @Test
  public void testSetStageList() {
    System.out.println("setStageList");
    List<String> stageList = null;
    GalleriaBean instance = new GalleriaBean();
    instance.setStageList(stageList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
