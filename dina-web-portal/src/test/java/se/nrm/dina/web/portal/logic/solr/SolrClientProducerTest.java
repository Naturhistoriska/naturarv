package se.nrm.dina.web.portal.logic.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.logic.config.InitialProperties;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class SolrClientProducerTest {
   
  @Mock
  private InitialProperties properties;
  
  @Mock
  private SolrClient solr;
  
  private SolrClientProducer instance;
  private String solrUrl;
   
  public SolrClientProducerTest() {
  }
  
  @Before
  public void setUp() {
    solrUrl = "https://solr.nrm.se";
    when(properties.getSolrURL()).thenReturn(solrUrl); 
    instance = new SolrClientProducer(properties );
    
    instance.init();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new SolrClientProducer();
    instance.init();
  }

  /**
   * Test of init method, of class SolrClientProducer.
   */
  @Test
  public void testInit() {
    System.out.println("init");  
    assertNotNull(instance.getSolrClient());
  }

  /**
   * Test of getSolrClient method, of class SolrClientProducer.
   */
  @Test
  public void testGetSolrClient() {
    System.out.println("getSolrClient"); 
    assertNotNull(instance.getSolrClient());
  }

  /**
   * Test of preDestroy method, of class SolrClientProducer.
   */
  @Test
  public void testPreDestroy() {
    System.out.println("preDestroy"); 
    instance.preDestroy(); 
  }
  
}
