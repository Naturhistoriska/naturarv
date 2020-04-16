package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.model.GeoHashData;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)    
public class SolrMapServiceTest {
  
  private SolrMapService instance;
  
  @Mock
  private SolrClient solr;
//  @Mock
//  private QueryResponse response;
  
  public SolrMapServiceTest() {
  }
   
  @Before
  public void setUp() {  
    instance = new SolrMapService(solr);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new SolrMapService();
    instance.searchSpatialData("sometext", null, "some coordinates");
  }

  /**
   * Test of searchGeoHash method, of class SolrMapService.
   * @throws org.apache.solr.client.solrj.SolrServerException
   * @throws java.io.IOException
   */
//  @Test
  public void testSearchGeoHash() throws SolrServerException, IOException {
    System.out.println("searchGeoHash");
    String text = "";
    String regionQueryText = "";
    Map<String, String> filters = null;
    String prefix = ""; 
    List<GeoHashData> expResult = null;
    QueryResponse response = new QueryResponse();
    JsonQueryRequest request = new JsonQueryRequest();
    when(request.process(Mockito.any(SolrClient.class))).thenReturn(response);
    List<GeoHashData> result = instance.searchGeoHash(text, regionQueryText, filters, prefix);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSet method, of class SolrMapService.
   */
//  @Test
  public void testGetSet() {
    System.out.println("getSet"); 
    TreeSet<Integer> expResult = null;
    TreeSet<Integer> result = instance.getSet();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of searchSmallDataSet method, of class SolrMapService.
   */
//  @Test
  public void testSearchSmallDataSet() {
    System.out.println("searchSmallDataSet");
    String searchText = "";
    Map<String, String> filters = null;
    String geohash = "";
    SolrMapService instance = new SolrMapService();
    Map<String, Integer> expResult = null;
    Map<String, Integer> result = instance.searchSmallDataSet(searchText, filters, geohash);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of searchSpatialData method, of class SolrMapService.
   */
//  @Test
  public void testSearchSpatialData() {
    System.out.println("searchSpatialData");
    String text = "";
    Map<String, String> filters = null;
    String coordinates = "";
    SolrMapService instance = new SolrMapService();
    List<SolrData> expResult = null;
    List<SolrData> result = instance.searchSpatialData(text, filters, coordinates);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
