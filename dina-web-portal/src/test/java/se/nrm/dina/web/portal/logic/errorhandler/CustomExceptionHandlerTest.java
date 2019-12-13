/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.errorhandler;

import javax.faces.context.ExceptionHandler;
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
public class CustomExceptionHandlerTest {
  
  public CustomExceptionHandlerTest() {
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
   * Test of getWrapped method, of class CustomExceptionHandler.
   */
  @Test
  public void testGetWrapped() {
    System.out.println("getWrapped");
    CustomExceptionHandler instance = null;
    ExceptionHandler expResult = null;
    ExceptionHandler result = instance.getWrapped();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of handle method, of class CustomExceptionHandler.
   */
  @Test
  public void testHandle() {
    System.out.println("handle");
    CustomExceptionHandler instance = null;
    instance.handle();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
