package se.nrm.dina.web.portal.utils;

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
public class MonthElementTest {
  
  private MonthElement instance;
  
  public MonthElementTest() {
  }
  
  @Before
  public void setUp() { 
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of values method, of class MonthElement.
   */
  @Test
  public void testValues() {
    System.out.println("values");
    MonthElement[] expResult = null;
    MonthElement[] result = MonthElement.values();
    assertArrayEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of valueOf method, of class MonthElement.
   */
  @Test
  public void testValueOf() {
    System.out.println("valueOf");
    String name = "";
    MonthElement expResult = null;
    MonthElement result = MonthElement.valueOf(name);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of valueOfShortName method, of class MonthElement.
   */
  @Test
  public void testValueOfShortName() {
    System.out.println("valueOfShortName");
    String month = "";
    boolean isSwedish = false;
    String expResult = "";
    String result = MonthElement.valueOfShortName(month, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of valueOfName method, of class MonthElement.
   */
  @Test
  public void testValueOfName() {
    System.out.println("valueOfName");
    String month = "";
    boolean isSwedish = false;
    String expResult = "";
    String result = MonthElement.valueOfName(month, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of valueOfNameByNumberOfMonth method, of class MonthElement.
   */
  @Test
  public void testValueOfNameByNumberOfMonth() {
    System.out.println("valueOfNameByNumberOfMonth");
    Integer numberOfMonth = null;
    boolean isSwedish = false;
    String expResult = "";
    String result = MonthElement.valueOfNameByNumberOfMonth(numberOfMonth, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeLanguage method, of class MonthElement.
   */
  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    String month = "";
    boolean isSwedish = false;
    String expResult = "";
    String result = MonthElement.changeLanguage(month, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeMonthLanguageWithYear method, of class MonthElement.
   */
  @Test
  public void testChangeMonthLanguageWithYear() {
    System.out.println("changeMonthLanguageWithYear");
    String value = "";
    boolean isSwedish = false;
    String expResult = "";
    String result = MonthElement.changeMonthLanguageWithYear(value, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
