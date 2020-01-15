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
public class GeoHashDataTest {
  
  private GeoHashData instance;
  private final String geohashString = "u3fh";
  private final String coordinates = "N58E18";
  private final int total = 256;
  private List<String> geohashList;
  
  
  public GeoHashDataTest() {
  }
 
  @Before
  public void setUp() {
    geohashList = new ArrayList();
    instance = new GeoHashData(geohashString, coordinates, total, geohashList);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
   
  /**
   * Test of getGeohashString method, of class GeoHashData.
   */
  @Test
  public void testGetGeohashString() {
    System.out.println("getGeohashString");  
    String result = instance.getGeohashString();
    assertEquals(geohashString, result); 
  }

  /**
   * Test of getCoordinates method, of class GeoHashData.
   */
  @Test
  public void testGetCoordinates() {
    System.out.println("getCoordinates");  
    String result = instance.getCoordinates();
    assertEquals(coordinates, result); 
  }

  /**
   * Test of getTotal method, of class GeoHashData.
   */
  @Test
  public void testGetTotal() {
    System.out.println("getTotal");  
    int result = instance.getTotal();
    assertEquals(total, result); 
  }

  /**
   * Test of getGeohashList method, of class GeoHashData.
   */
  @Test
  public void testGetGeohashList() {
    System.out.println("getGeohashList"); 
    List<String> result = instance.getGeohashList();
    assertTrue(result.isEmpty()); 
  }
  
}
