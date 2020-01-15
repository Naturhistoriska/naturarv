package se.nrm.dina.web.portal.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class SolrResultTest {
  
  private SolrResult instance;
  private final int total = 280;
  private List<SolrData> list;
  
  public SolrResultTest() {
  }
   
  @Before
  public void setUp() {
    SolrData data = new SolrData();
    list = new ArrayList();
    list.add(data);
    instance = new SolrResult(total, list);
  }
  
  @After
  public void tearDown() { 
    instance = null;
  }

  /**
   * Test of getTotalFound method, of class SolrResult.
   */
  @Test
  public void testGetTotalFound() {
    System.out.println("getTotalFound");  
    int result = instance.getTotalFound();
    assertEquals(total, result); 
  }

  /**
   * Test of getSolrData method, of class SolrResult.
   */
  @Test
  public void testGetSolrData() {
    System.out.println("getSolrData"); 
    List<SolrData> result = instance.getSolrData();
    assertEquals(list, result); 
    assertEquals(1, result.size());
  }
  
}
