package se.nrm.dina.web.portal.model;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class RectangleDataTest {
   
  private RectangleData instance;
  
  private final String geohashString = "u3fh";
  
  public RectangleDataTest() {
  }
   
  
  @Before
  public void setUp() {
    instance =  new RectangleData(20, geohashString);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getCount method, of class RectangleData.
   */
  @Test
  public void testGetCount() {
    System.out.println("getCount");  
    int result = instance.getCount();
    assertEquals(20, result); 
  }

  /**
   * Test of getGeohash method, of class RectangleData.
   */
  @Test
  public void testGetGeohash() {
    System.out.println("getGeohash"); 
    String result = instance.getGeohash();
    assertEquals(geohashString, result); 
  }
  
}
