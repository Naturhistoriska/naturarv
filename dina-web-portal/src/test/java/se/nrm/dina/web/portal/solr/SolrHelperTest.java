package se.nrm.dina.web.portal.solr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class SolrHelperTest {
  
  private SolrHelper instance;
  
  public SolrHelperTest() {
  }
   
  @Before
  public void setUp() {
    instance = SolrHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class SolrHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    SolrHelper result = SolrHelper.getInstance();
    assertNotNull(result); 
  }

  /**
   * Test of getBucketsTotal method, of class SolrHelper.
   */
  @Test
  public void testGetBucketsTotalWithNull() {
    System.out.println("getBucketsTotal");
    BucketBasedJsonFacet bucket = null; 
    int expResult = 0;
    int result = instance.getBucketsTotal(bucket);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetBucketsTotalWithNull1() {
    System.out.println("getBucketsTotal");
    BucketBasedJsonFacet bucket = mock(BucketBasedJsonFacet.class); 
    when(bucket.getBuckets()).thenReturn(null);
    
    int expResult = 0;
    int result = instance.getBucketsTotal(bucket);
    assertEquals(expResult, result); 
  }

  @Test
  public void testGetBucketsTotalWithEmpty() {
    System.out.println("getBucketsTotal");
     
    List<BucketJsonFacet> list = new ArrayList();
    BucketBasedJsonFacet bucket = mock(BucketBasedJsonFacet.class); 
 
    when(bucket.getBuckets()).thenReturn(list);
    int expResult = 0;
    int result = instance.getBucketsTotal(bucket);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetBucketsTotal() {
    System.out.println("getBucketsTotal");
     
    List<BucketJsonFacet> list = new ArrayList();
    BucketJsonFacet facet = mock(BucketJsonFacet.class);
    when(facet.getCount()).thenReturn((long)18);
    list.add(facet);
    BucketBasedJsonFacet bucket = mock(BucketBasedJsonFacet.class);   
    when(bucket.getBuckets()).thenReturn(list);
    
    int expResult = 18;
    int result = instance.getBucketsTotal(bucket);
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of addSearchFilters method, of class SolrHelper.
   */
  @Test
  public void testAddSearchFilters_SolrQuery_MapWithNull() {
    System.out.println("addSearchFilters"); 
    SolrQuery query = new SolrQuery();
    
    Map<String, String> filterQueries = null; 
    instance.addSearchFilters(query, filterQueries); 
    assertNull(query.getFilterQueries());
  }
  
  @Test
  public void testAddSearchFilters_SolrQuery_MapWithEmpty() {
    System.out.println("addSearchFilters"); 
    SolrQuery query = new SolrQuery();
    
    Map<String, String> filterQueries = new HashMap<>(); 
    instance.addSearchFilters(query, filterQueries); 
    assertNull(query.getFilterQueries());
  }
  
  @Test
  public void testAddSearchFilters_SolrQuery() {
    System.out.println("addSearchFilters"); 
    SolrQuery query = new SolrQuery();
    
    Map<String, String> filterQueries = new HashMap<>(); 
    filterQueries.put("tx", "taxon");
    filterQueries.put("cn", "cat123");
    instance.addSearchFilters(query, filterQueries); 
    assertEquals(query.getFilterQueries().length, 2);
  }

  /**
   * Test of addSearchFilters method, of class SolrHelper.
   */
  @Test
  public void testAddSearchFilters_JsonQueryRequest_MapWithNull() {
    System.out.println("addSearchFilters");
    JsonQueryRequest jsonQuery = mock(JsonQueryRequest.class);
    Map<String, String> filterQuerues = null; 
    instance.addSearchFilters(jsonQuery, filterQuerues); 
    verifyZeroInteractions(jsonQuery); 
  }
  
  @Test
  public void testAddSearchFilters_JsonQueryRequest_MapWithEmpty() {
    System.out.println("addSearchFilters");
    JsonQueryRequest jsonQuery = mock(JsonQueryRequest.class);
    Map<String, String> filterQuerues = new HashMap<>(); 
    instance.addSearchFilters(jsonQuery, filterQuerues); 
    verifyZeroInteractions(jsonQuery); 
  }
  
  @Test
  public void testAddSearchFilters_JsonQueryRequest_Map() {
    System.out.println("addSearchFilters");
    JsonQueryRequest jsonQuery = mock(JsonQueryRequest.class);
    Map<String, String> filterQuerues = new HashMap<>(); 
    filterQuerues.put("cn", "catNumber:1234");
    instance.addSearchFilters(jsonQuery, filterQuerues); 
    verify(jsonQuery, times(1)).withFilter(any(String.class));  
  }
}