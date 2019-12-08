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
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class ImageSwitcherTest {
  
  public ImageSwitcherTest() {
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
   * Test of imageSwitch method, of class ImageSwitcher.
   */
//  @Test
  public void testImageSwitch_0args() {
    System.out.println("imageSwitch");
    ImageSwitcher instance = new ImageSwitcher();
    instance.imageSwitch();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of imageSwitch method, of class ImageSwitcher.
   */
//  @Test
  public void testImageSwitch_SolrData() {
    System.out.println("imageSwitch");
    SolrData data = null;
    ImageSwitcher instance = new ImageSwitcher();
    instance.imageSwitch(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMbid method, of class ImageSwitcher.
   */
//  @Test
  public void testGetMbid() {
    System.out.println("getMbid");
    ImageSwitcher instance = new ImageSwitcher();
    String expResult = "";
    String result = instance.getMbid();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCatalogNumber method, of class ImageSwitcher.
   */
//  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");
    ImageSwitcher instance = new ImageSwitcher();
    String expResult = "";
    String result = instance.getCatalogNumber();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getScientificName method, of class ImageSwitcher.
   */
//  @Test
  public void testGetScientificName() {
    System.out.println("getScientificName");
    ImageSwitcher instance = new ImageSwitcher();
    String expResult = "";
    String result = instance.getScientificName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getThumbs method, of class ImageSwitcher.
   */
//  @Test
  public void testGetThumbs() {
    System.out.println("getThumbs");
    ImageSwitcher instance = new ImageSwitcher();
    List<String> expResult = null;
    List<String> result = instance.getThumbs();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getJpgs method, of class ImageSwitcher.
   */
//  @Test
  public void testGetJpgs() {
    System.out.println("getJpgs");
    ImageSwitcher instance = new ImageSwitcher();
    List<String> expResult = null;
    List<String> result = instance.getJpgs();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
