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
import se.nrm.dina.web.portal.model.ErrorReport;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class ErrorReportBeanTest {
  
  public ErrorReportBeanTest() {
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
   * Test of onBlur method, of class ErrorReportBean.
   */
//  @Test
  public void testOnBlur() {
    System.out.println("onBlur");
    ErrorReportBean instance = new ErrorReportBean();
    instance.onBlur();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of sendErrorReport method, of class ErrorReportBean.
   */
//  @Test
  public void testSendErrorReport() {
    System.out.println("sendErrorReport");
    ErrorReportBean instance = new ErrorReportBean();
    instance.sendErrorReport();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of validateEmail method, of class ErrorReportBean.
   */
//  @Test
  public void testValidateEmail() {
    System.out.println("validateEmail");
    ErrorReportBean instance = new ErrorReportBean();
    instance.validateEmail();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of reportError method, of class ErrorReportBean.
   */
//  @Test
  public void testReportError() {
    System.out.println("reportError");
    SolrData errorData = null;
    ErrorReportBean instance = new ErrorReportBean();
    instance.reportError(errorData);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getErrorData method, of class ErrorReportBean.
   */
//  @Test
  public void testGetErrorData() {
    System.out.println("getErrorData");
    ErrorReportBean instance = new ErrorReportBean();
    SolrData expResult = null;
    SolrData result = instance.getErrorData();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getErrorReport method, of class ErrorReportBean.
   */
//  @Test
  public void testGetErrorReport() {
    System.out.println("getErrorReport");
    ErrorReportBean instance = new ErrorReportBean();
    ErrorReport expResult = null;
    ErrorReport result = instance.getErrorReport();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setErrorReport method, of class ErrorReportBean.
   */
//  @Test
  public void testSetErrorReport() {
    System.out.println("setErrorReport");
    ErrorReport errorReport = null;
    ErrorReportBean instance = new ErrorReportBean();
    instance.setErrorReport(errorReport);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
