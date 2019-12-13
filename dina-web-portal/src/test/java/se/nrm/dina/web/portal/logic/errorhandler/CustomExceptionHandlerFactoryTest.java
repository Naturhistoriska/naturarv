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
public class CustomExceptionHandlerFactoryTest {
  
  public CustomExceptionHandlerFactoryTest() {
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
   * Test of getExceptionHandler method, of class CustomExceptionHandlerFactory.
   */
  @Test
  public void testGetExceptionHandler() {
    System.out.println("getExceptionHandler");
    CustomExceptionHandlerFactory instance = new CustomExceptionHandlerFactory();
    ExceptionHandler expResult = null;
    ExceptionHandler result = instance.getExceptionHandler();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
