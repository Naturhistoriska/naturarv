package se.nrm.dina.web.portal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class DateHelperTest {
  
  private DateHelper instance;
  private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
  
  public DateHelperTest() {
  }
 
  @Before
  public void setUp() {
    instance = DateHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class DateHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    DateHelper result = DateHelper.getInstance();
    assertNotNull(result); 
  }

  /**
   * Test of getYearMonthOfToday method, of class DateHelper.
   */
  @Test
  public void testGetYearMonthOfToday() {
    System.out.println("getYearMonthOfToday"); 
    YearMonth expResult = YearMonth.from(LocalDate.now());
    YearMonth result = instance.getYearMonthOfToday();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getYearOfToday method, of class DateHelper.
   */
  @Test
  public void testGetYearOfToday() {
    System.out.println("getYearOfToday"); 
    int expResult = YearMonth.from(LocalDate.now()).getYear();
    int result = instance.getYearOfToday();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMonthOfToday method, of class DateHelper.
   */
  @Test
  public void testGetMonthOfToday() {
    System.out.println("getMonthOfToday"); 
    int expResult = YearMonth.from(LocalDate.now()).getMonth().getValue();
    int result = instance.getMonthOfToday();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDayOfYear method, of class DateHelper.
   */
  @Test
  public void testGetDayOfYear() {
    System.out.println("getDayOfYear");
    int month = 7;
    int day = 18; 
    int expResult = LocalDate.of(2000, month, day).getDayOfYear();
    int result = instance.getDayOfYear(month, day);
    assertEquals(expResult, result); 
  }

  /**
   * Test of dateToString method, of class DateHelper.
   */
  @Test
  public void testDateToStringNull() {
    System.out.println("dateToString");
    Date date = null;  
    String result = instance.dateToString(date);
    assertNull(result); 
  }
  
  @Test
  public void testDateToString() {
    System.out.println("dateToString");
    Date date = new Date();  
    String expResult = DATE_FORMAT.format(date);
    String result = instance.dateToString(date);
    assertEquals(expResult, result); 
  }
  
  

  
  /**
   * Test of stringToDate method, of class DateHelper.
   * @throws java.text.ParseException
   */
  @Test
  public void testStringToDate() throws ParseException {
    System.out.println("stringToDate");
    String strDate = "2020-01-18"; 
    Date expResult = DATE_FORMAT.parse(strDate);
    Date result = instance.stringToDate(strDate);
    assertEquals(expResult, result); 
  }
   
  @Test
  public void testStringToDateThrowException() {
    System.out.println("stringToDate");
    String strDate = "2011 11 19";  
    Date result = instance.stringToDate(strDate); 
    assertNull(result);
  }

  /**
   * Test of convertDateToLocalDate method, of class DateHelper.
   */
  @Test
  public void testConvertDateToLocalDate() {
    System.out.println("convertDateToLocalDate");
    Date date = new Date(); 
    LocalDate expResult = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate result = instance.convertDateToLocalDate(date);
    assertEquals(expResult, result); 
  }

  /**
   * Test of convertDateToLocalDateTime method, of class DateHelper.
   */
  @Test
  public void testConvertDateToLocalDateTime_Date() {
    System.out.println("convertDateToLocalDateTime");
    Date date = new Date(); 
    LocalDateTime expResult = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime result = instance.convertDateToLocalDateTime(date);
    assertEquals(expResult, result); 
  }

  /**
   * Test of convertDateToLocalDateTime method, of class DateHelper.
   */
  @Test
  public void testConvertDateToLocalDateTime_3argsWithNullDate() {
    System.out.println("convertDateToLocalDateTime");
    Date date = null;
    boolean isStartDay = false;
    boolean isEndDay = false; 
    LocalDateTime expResult = null;
    LocalDateTime result = instance.convertDateToLocalDateTime(date, isStartDay, isEndDay);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertDateToLocalDateTime_3argsWithWithStartDay() {
    System.out.println("convertDateToLocalDateTime");
    Date date = new Date();
    boolean isStartDay = true;
    boolean isEndDay = false; 
    LocalDateTime expResult = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
    LocalDateTime result = instance.convertDateToLocalDateTime(date, isStartDay, isEndDay);
    assertEquals(expResult, result); 
  }

  @Test
  public void testConvertDateToLocalDateTime_3argsWithWithEndDay() {
    System.out.println("convertDateToLocalDateTime");
    Date date = new Date();
    boolean isStartDay = false;
    boolean isEndDay = true; 
    LocalDateTime expResult = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(23, 59, 59);
    LocalDateTime result = instance.convertDateToLocalDateTime(date, isStartDay, isEndDay);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertDateToLocalDateTime_3args() {
    System.out.println("convertDateToLocalDateTime");
    Date date = new Date();
    boolean isStartDay = false;
    boolean isEndDay = false; 
    LocalDateTime expResult = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    LocalDateTime result = instance.convertDateToLocalDateTime(date, isStartDay, isEndDay);
    assertEquals(expResult, result); 
  }

  
  /**
   * Test of convertLocalDateTimeToString method, of class DateHelper.
   */
  @Test
  public void testConvertLocalDateTimeToStringWithNullDates() {
    System.out.println("convertLocalDateTimeToString");
    LocalDateTime fromDate = null;
    LocalDateTime toDate = null;
    String field = "date";
    String fromZoom = "";
    String toZoom = ""; 
    String expResult = "date:[* TO *]";
    String result = instance.convertLocalDateTimeToString(fromDate, toDate, field, fromZoom, toZoom);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testConvertLocalDateTimeToStringWithDates() {
    System.out.println("convertLocalDateTimeToString");
    LocalDateTime fromDate = LocalDateTime.of(2020, Month.JANUARY, 1, 01, 01);
    LocalDateTime toDate = LocalDateTime.of(2020, Month.JANUARY, 15, 23, 59);
    String field = "date";
    String fromZoom = "";
    String toZoom = ""; 
    String expResult = "date:[2020-01-01T01:01 TO 2020-01-15T23:59]";
    String result = instance.convertLocalDateTimeToString(fromDate, toDate, field, fromZoom, toZoom); 
    assertEquals(expResult, result); 
  }
  
}
