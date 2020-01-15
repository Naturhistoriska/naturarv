package se.nrm.dina.web.portal.model;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class CollectionDataTest {
  
  private CollectionData instance;
  private final String name = "Swedish Malaise Trap Project (SMTP) Collection Obj";
  private final String code ="NRH";
  private final int total = 28;
  
  public CollectionDataTest() {
  }
 
  
  @Before
  public void setUp() {
    instance = new CollectionData(code, name, total);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new CollectionData();
    assertTrue(instance.getName() == null);
  }

  /**
   * Test of getCode method, of class CollectionData.
   */
  @Test
  public void testGetCode() {
    System.out.println("getCode");  
    String result = instance.getCode();
    assertEquals(code, result); 
  }

  /**
   * Test of getName method, of class CollectionData.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");  
    String result = instance.getName();
    assertEquals(name, result); 
  }

  /**
   * Test of getTotal method, of class CollectionData.
   */
  @Test
  public void testGetTotal() {
    System.out.println("getTotal");  
    int result = instance.getTotal();
    assertEquals(total, result); 
  }

  /**
   * Test of getShortName method, of class CollectionData.
   */
  @Test
  public void testGetShortName() {
    System.out.println("getShortName"); 
     
    String expResult = "Swedish Malaise Tra...";
    String result = instance.getShortName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of toString method, of class CollectionData.
   */
  @Test
  public void testToString() {
    System.out.println("toString"); 
    String expResult = code + " : " + name + " : " + total;
    String result = instance.toString();
    assertEquals(expResult, result); 
  }
}
