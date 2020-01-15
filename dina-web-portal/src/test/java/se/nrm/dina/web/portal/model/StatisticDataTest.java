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
public class StatisticDataTest {
  
  private StatisticData instance;
  
  private final int total = 2800;
  private final int totalDnas = 28;
  private final int totalImages = 360;
  private final int totalMaps = 28;
  private final int totalInSweden = 168;
  private final int totalType = 57;
  private List<CollectionData> collections;
  
  public StatisticDataTest() {
  }
   
  @Before
  public void setUp() {
    collections = new ArrayList();
    instance = new StatisticData(total, totalDnas, totalImages, totalMaps, totalInSweden, totalType, collections);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new StatisticData();
    assertEquals(0, instance.getTotal());
  }

  /**
   * Test of getTotal method, of class StatisticData.
   */
  @Test
  public void testGetTotal() {
    System.out.println("getTotal");  
    int result = instance.getTotal();
    assertEquals(total, result); 
  }

  /**
   * Test of getTotalDnas method, of class StatisticData.
   */
  @Test
  public void testGetTotalDnas() {
    System.out.println("getTotalDnas"); 
    int result = instance.getTotalDnas();
    assertEquals(totalDnas, result); 
  }

  /**
   * Test of getTotalImages method, of class StatisticData.
   */
  @Test
  public void testGetTotalImages() {
    System.out.println("getTotalImages");  
    int result = instance.getTotalImages();
    assertEquals(totalImages, result); 
  }

  /**
   * Test of getTotalMaps method, of class StatisticData.
   */
  @Test
  public void testGetTotalMaps() {
    System.out.println("getTotalMaps"); 
    int result = instance.getTotalMaps();
    assertEquals(totalMaps, result); 
  }

  /**
   * Test of getTotalInSweden method, of class StatisticData.
   */
  @Test
  public void testGetTotalInSweden() {
    System.out.println("getTotalInSweden"); 
    int result = instance.getTotalInSweden();
    assertEquals(totalInSweden, result); 
  }

  /**
   * Test of getTotalType method, of class StatisticData.
   */
  @Test
  public void testGetTotalType() {
    System.out.println("getTotalType"); 
    int result = instance.getTotalType();
    assertEquals(totalType, result); 
  }

  /**
   * Test of getCollections method, of class StatisticData.
   */
  @Test
  public void testGetCollections() {
    System.out.println("getCollections"); 
    List<CollectionData> result = instance.getCollections();
    assertEquals(collections, result); 
  }
  
}
