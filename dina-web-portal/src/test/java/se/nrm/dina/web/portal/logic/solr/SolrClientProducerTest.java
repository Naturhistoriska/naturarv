/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.solr;

import org.apache.solr.client.solrj.SolrClient;
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
public class SolrClientProducerTest {
  
  public SolrClientProducerTest() {
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
   * Test of init method, of class SolrClientProducer.
   */
  @Test
  public void testInit() {
    System.out.println("init");
    SolrClientProducer instance = new SolrClientProducer();
    instance.init();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSolrClient method, of class SolrClientProducer.
   */
  @Test
  public void testGetSolrClient() {
    System.out.println("getSolrClient");
    SolrClientProducer instance = new SolrClientProducer();
    SolrClient expResult = null;
    SolrClient result = instance.getSolrClient();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of preDestroy method, of class SolrClientProducer.
   */
  @Test
  public void testPreDestroy() {
    System.out.println("preDestroy");
    SolrClientProducer instance = new SolrClientProducer();
    instance.preDestroy();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
