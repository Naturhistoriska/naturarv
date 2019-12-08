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

/**
 *
 * @author idali
 */
public class LanguagesTest {
  
  public LanguagesTest() {
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
   * Test of getLocale method, of class Languages.
   */
//  @Test
  public void testGetLocale() {
    System.out.println("getLocale");
    Languages instance = new Languages();
    String expResult = "";
    String result = instance.getLocale();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeLanguage method, of class Languages.
   */
//  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    String locale = "";
    Languages instance = new Languages();
    instance.changeLanguage(locale);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLanguage method, of class Languages.
   */
//  @Test
  public void testGetLanguage() {
    System.out.println("getLanguage");
    Languages instance = new Languages();
    String expResult = "";
    String result = instance.getLanguage();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isSwedish method, of class Languages.
   */
//  @Test
  public void testIsSwedish() {
    System.out.println("isSwedish");
    Languages instance = new Languages();
    boolean expResult = false;
    boolean result = instance.isSwedish();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
