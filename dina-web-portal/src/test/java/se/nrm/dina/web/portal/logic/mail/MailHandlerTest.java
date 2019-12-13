/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.mail;

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
public class MailHandlerTest {
  
  public MailHandlerTest() {
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
   * Test of sendMail method, of class MailHandler.
   */
  @Test
  public void testSendMail() {
    System.out.println("sendMail");
    SolrData data = null;
    ErrorReport error = null;
    boolean isSwedish = false;
    MailHandler instance = new MailHandler();
    instance.sendMail(data, error, isSwedish);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
