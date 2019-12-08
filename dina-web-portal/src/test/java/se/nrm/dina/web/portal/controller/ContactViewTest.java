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
public class ContactViewTest {
  
  public ContactViewTest() {
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
   * Test of init method, of class ContactView.
   */
//  @Test
  public void testInit() {
    System.out.println("init");
    ContactView instance = new ContactView();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSupportMail method, of class ContactView.
   */
//  @Test
  public void testGetSupportMail() {
    System.out.println("getSupportMail");
    ContactView instance = new ContactView();
    String expResult = "";
    String result = instance.getSupportMail();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSupportMail method, of class ContactView.
   */
//  @Test
  public void testSetSupportMail() {
    System.out.println("setSupportMail");
    String supportMail = "";
    ContactView instance = new ContactView();
    instance.setSupportMail(supportMail);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSupportPhone method, of class ContactView.
   */
//  @Test
  public void testGetSupportPhone() {
    System.out.println("getSupportPhone");
    ContactView instance = new ContactView();
    String expResult = "";
    String result = instance.getSupportPhone();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSupportPhone method, of class ContactView.
   */
//  @Test
  public void testSetSupportPhone() {
    System.out.println("setSupportPhone");
    String supportPhone = "";
    ContactView instance = new ContactView();
    instance.setSupportPhone(supportPhone);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
