package se.nrm.dina.web.portal.model;

import java.util.Date;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class QueryDataTest {
  
  private QueryData instance;
  private final String operation = "and";
  private final String content = "exact";
  private final String field = "cn";
  private final String value = "HRM1234"; 
  
  public QueryDataTest() {
  }
  
  @Before
  public void setUp() {
    instance = new QueryData(operation, content, field, value, true);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  
  @Test
  public void testDefaultConstructor() {
    instance = new QueryData();
    assertTrue(instance.getContent() == null);
  }

  /**
   * Test of getOperation method, of class QueryData.
   */
  @Test
  public void testGetOperation() {
    System.out.println("getOperation");  
    String result = instance.getOperation();
    assertEquals(operation, result); 
  }

  /**
   * Test of setOperation method, of class QueryData.
   */
  @Test
  public void testSetOperation() {
    System.out.println("setOperation");
    String newOperation = "or"; 
    instance.setOperation(newOperation);
    assertEquals(newOperation, instance.getOperation()); 
  }

  /**
   * Test of getContent method, of class QueryData.
   */
  @Test
  public void testGetContent() {
    System.out.println("getContent");  
    String result = instance.getContent();
    assertEquals(content, result); 
  }

  /**
   * Test of setContent method, of class QueryData.
   */
  @Test
  public void testSetContent() {
    System.out.println("setContent");
    String newContent = "contain"; 
    instance.setContent(newContent); 
    assertEquals(newContent, instance.getContent()); 
  }

  /**
   * Test of getField method, of class QueryData.
   */
  @Test
  public void testGetField() {
    System.out.println("getField");  
    String result = instance.getField();
    assertEquals(field, result); 
  }

  /**
   * Test of setField method, of class QueryData.
   */
  @Test
  public void testSetField() {
    System.out.println("setField");
    String newField = "tx"; 
    instance.setField(newField); 
    assertEquals(newField, instance.getField());
  }

  /**
   * Test of getValue method, of class QueryData.
   */
  @Test
  public void testGetValue() {
    System.out.println("getValue");  
    String result = instance.getValue();
    assertEquals(value, result); 
  }

  /**
   * Test of setValue method, of class QueryData.
   */
  @Test
  public void testSetValue() {
    System.out.println("setValue");
    String newValue = "test"; 
    instance.setValue(newValue);
    assertEquals(newValue, instance.getValue());
  }

  /**
   * Test of getFromDate method, of class QueryData.
   */
  @Test
  public void testGetFromDate() {
    System.out.println("getFromDate"); 
    Date expResult = null;
    Date result = instance.getFromDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setFromDate method, of class QueryData.
   */
  @Test
  public void testSetFromDate() {
    System.out.println("setFromDate");
    Date fromDate = new Date(); 
    instance.setFromDate(fromDate); 
    assertNotNull(instance.getFromDate());
  }

  /**
   * Test of getToDate method, of class QueryData.
   */
  @Test
  public void testGetToDate() {
    System.out.println("getToDate"); 
    Date expResult = null;
    Date result = instance.getToDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setToDate method, of class QueryData.
   */
  @Test
  public void testSetToDate() {
    System.out.println("setToDate");
    Date toDate = new Date(); 
    instance.setToDate(toDate); 
    assertNotNull(instance.getToDate());
  }

  /**
   * Test of getStartMonth method, of class QueryData.
   */
  @Test
  public void testGetStartMonth() {
    System.out.println("getStartMonth"); 
    int expResult = 0;
    int result = instance.getStartMonth();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setStartMonth method, of class QueryData.
   */
  @Test
  public void testSetStartMonth() {
    System.out.println("setStartMonth");
    int startMonth = 6; 
    instance.setStartMonth(startMonth); 
    assertEquals(6, instance.getStartMonth());
  }

  /**
   * Test of getEndMonth method, of class QueryData.
   */
  @Test
  public void testGetEndMonth() {
    System.out.println("getEndMonth"); 
    int expResult = 0;
    int result = instance.getEndMonth();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setEndMonth method, of class QueryData.
   */
  @Test
  public void testSetEndMonth() {
    System.out.println("setEndMonth");
    int endMonth = 10; 
    instance.setEndMonth(endMonth); 
    assertEquals(10, instance.getEndMonth());
  }

  /**
   * Test of getStMon method, of class QueryData.
   */
  @Test
  public void testGetStMon() {
    System.out.println("getStMon"); 
    int expResult = 1;
    int result = instance.getStMon();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setStMon method, of class QueryData.
   */
  @Test
  public void testSetStMon() {
    System.out.println("setStMon");
    int stMon = 10; 
    instance.setStMon(stMon); 
    
    assertEquals(10, instance.getStMon());
  }

  /**
   * Test of getEndMon method, of class QueryData.
   */
  @Test
  public void testGetEndMon() {
    System.out.println("getEndMon"); 
    int expResult = 12;
    int result = instance.getEndMon();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setEndMon method, of class QueryData.
   */
  @Test
  public void testSetEndMon() {
    System.out.println("setEndMon");
    int endMon = 10; 
    instance.setEndMon(endMon);
    assertEquals(10, instance.getEndMon());
  }

  /**
   * Test of getStartDay method, of class QueryData.
   */
  @Test
  public void testGetStartDay() {
    System.out.println("getStartDay"); 
    int expResult = 1;
    int result = instance.getStartDay();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setStartDay method, of class QueryData.
   */
  @Test
  public void testSetStartDay() {
    System.out.println("setStartDay");
    int startDay = 10;
    instance.setStartDay(startDay); 
    
    assertEquals(10, instance.getStartDay());
  }

  /**
   * Test of getEndDay method, of class QueryData.
   */
  @Test
  public void testGetEndDay() {
    System.out.println("getEndDay"); 
    int expResult = 1;
    int result = instance.getEndDay();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setEndDay method, of class QueryData.
   */
  @Test
  public void testSetEndDay() {
    System.out.println("setEndDay");
    int endDay = 6; 
    instance.setEndDay(endDay);
    assertEquals(6, instance.getEndDay());
  }

  /**
   * Test of isAppendValue method, of class QueryData.
   */
  @Test
  public void testIsAppendValue() {
    System.out.println("isAppendValue"); 
    boolean expResult = true;
    boolean result = instance.isAppendValue();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isIsSearchAllType method, of class QueryData.
   */
  @Test
  public void testIsIsSearchAllType() {
    System.out.println("isIsSearchAllType"); 
    boolean expResult = true;
    boolean result = instance.isIsSearchAllType();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setIsSearchAllType method, of class QueryData.
   */
  @Test
  public void testSetIsSearchAllType() {
    System.out.println("setIsSearchAllType");
    boolean isSearchAllType = true; 
    instance.setIsSearchAllType(isSearchAllType); 
    assertEquals(true, instance.isIsSearchAllType());
  }

  /**
   * Test of toString method, of class QueryData.
   */
  @Test
  public void testToString() {
    System.out.println("toString"); 
    String expResult = "and exact cn HRM1234";
    String result = instance.toString();
    assertEquals(expResult, result); 
  }
  
}
