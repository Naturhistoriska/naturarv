package se.nrm.dina.web.portal.model;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class ErrorReportTest {
  
  private ErrorReport instance;
  
  public ErrorReportTest() {
  }
   
  @Before
  public void setUp() { 
    instance = new ErrorReport();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of isCatalogNumber method, of class ErrorReport.
   */
  @Test
  public void testIsCatalogNumber() {
    System.out.println("isCatalogNumber"); 
    boolean expResult = false;
    boolean result = instance.isCatalogNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setCatalogNumber method, of class ErrorReport.
   */
  @Test
  public void testSetCatalogNumber() {
    System.out.println("setCatalogNumber");
    boolean catalogNumber = true; 
    instance.setCatalogNumber(catalogNumber); 
    assertEquals(true, instance.isCatalogNumber()); 
  }

  /**
   * Test of isSpecimenName method, of class ErrorReport.
   */
  @Test
  public void testIsSpecimenName() {
    System.out.println("isSpecimenName"); 
    boolean expResult = false;
    boolean result = instance.isSpecimenName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setSpecimenName method, of class ErrorReport.
   */
  @Test
  public void testSetSpecimenName() {
    System.out.println("setSpecimenName");
    boolean specimenName = true; 
    instance.setSpecimenName(specimenName); 
    assertEquals(true, instance.isSpecimenName()); 
  }

  /**
   * Test of isFamily method, of class ErrorReport.
   */
  @Test
  public void testIsFamily() {
    System.out.println("isFamily"); 
    boolean expResult = false;
    boolean result = instance.isFamily();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setFamily method, of class ErrorReport.
   */
  @Test
  public void testSetFamily() {
    System.out.println("setFamily");
    boolean family = true; 
    instance.setFamily(family); 
    assertEquals(true, instance.isFamily());
  }

  /**
   * Test of isTypeinfo method, of class ErrorReport.
   */
  @Test
  public void testIsTypeinfo() {
    System.out.println("isTypeinfo"); 
    boolean expResult = false;
    boolean result = instance.isTypeinfo();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setTypeinfo method, of class ErrorReport.
   */
  @Test
  public void testSetTypeinfo() {
    System.out.println("setTypeinfo");
    boolean typeinfo = true; 
    instance.setTypeinfo(typeinfo); 
    assertEquals(true, instance.isTypeinfo());
  }

  /**
   * Test of isCollector method, of class ErrorReport.
   */
  @Test
  public void testIsCollector() {
    System.out.println("isCollector"); 
    boolean expResult = false;
    boolean result = instance.isCollector();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setCollector method, of class ErrorReport.
   */
  @Test
  public void testSetCollector() {
    System.out.println("setCollector");
    boolean collector = true; 
    instance.setCollector(collector); 
    assertEquals(true, instance.isCollector());
  }

  /**
   * Test of isDate method, of class ErrorReport.
   */
  @Test
  public void testIsDate() {
    System.out.println("isDate"); 
    boolean expResult = false;
    boolean result = instance.isDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setDate method, of class ErrorReport.
   */
  @Test
  public void testSetDate() {
    System.out.println("setDate");
    boolean date = true; 
    instance.setDate(date); 
    assertEquals(true, instance.isDate());
  }

  /**
   * Test of isLocality method, of class ErrorReport.
   */
  @Test
  public void testIsLocality() {
    System.out.println("isLocality"); 
    boolean expResult = false;
    boolean result = instance.isLocality();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setLocality method, of class ErrorReport.
   */
  @Test
  public void testSetLocality() {
    System.out.println("setLocality");
    boolean locality = true; 
    instance.setLocality(locality); 
    assertEquals(true, instance.isLocality());
  }

  /**
   * Test of isContinent method, of class ErrorReport.
   */
  @Test
  public void testIsContinent() {
    System.out.println("isContinent"); 
    boolean expResult = false;
    boolean result = instance.isContinent();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setContinent method, of class ErrorReport.
   */
  @Test
  public void testSetContinent() {
    System.out.println("setContinent");
    boolean continent = true; 
    instance.setContinent(continent); 
    assertEquals(true, instance.isContinent());
  }

  /**
   * Test of isCountry method, of class ErrorReport.
   */
  @Test
  public void testIsCountry() {
    System.out.println("isCountry"); 
    boolean expResult = false;
    boolean result = instance.isCountry();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setCountry method, of class ErrorReport.
   */
  @Test
  public void testSetCountry() {
    System.out.println("setCountry");
    boolean country = true; 
    instance.setCountry(country); 
    assertEquals(true, instance.isCountry());
  }

  /**
   * Test of isCoordinate method, of class ErrorReport.
   */
  @Test
  public void testIsCoordinate() {
    System.out.println("isCoordinate"); 
    boolean expResult = false;
    boolean result = instance.isCoordinate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setCoordinate method, of class ErrorReport.
   */
  @Test
  public void testSetCoordinate() {
    System.out.println("setCoordinate");
    boolean coordinate = true; 
    instance.setCoordinate(coordinate); 
    assertEquals(true, instance.isCoordinate());
  }

  /**
   * Test of isOtherinfo method, of class ErrorReport.
   */
  @Test
  public void testIsOtherinfo() {
    System.out.println("isOtherinfo"); 
    boolean expResult = false;
    boolean result = instance.isOtherinfo();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setOtherinfo method, of class ErrorReport.
   */
  @Test
  public void testSetOtherinfo() {
    System.out.println("setOtherinfo");
    boolean otherinfo = true; 
    instance.setOtherinfo(otherinfo);
    assertEquals(true, instance.isOtherinfo());
  }

  /**
   * Test of isDeterminater method, of class ErrorReport.
   */
  @Test
  public void testIsDeterminater() {
    System.out.println("isDeterminater"); 
    boolean expResult = false;
    boolean result = instance.isDeterminater();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setDeterminater method, of class ErrorReport.
   */
  @Test
  public void testSetDeterminater() {
    System.out.println("setDeterminater");
    boolean determinater = true; 
    instance.setDeterminater(determinater); 
    assertEquals(true, instance.isDeterminater());
  }

  /**
   * Test of getErrorDesc method, of class ErrorReport.
   */
  @Test
  public void testGetErrorDesc() {
    System.out.println("getErrorDesc"); 
    String result = instance.getErrorDesc();
    assertEquals(null, result); 
  }

  /**
   * Test of setErrorDesc method, of class ErrorReport.
   */
  @Test
  public void testSetErrorDesc() {
    System.out.println("setErrorDesc");
    String errorDesc = "test"; 
    instance.setErrorDesc(errorDesc); 
    assertEquals(errorDesc, instance.getErrorDesc());
  }

  /**
   * Test of getReportorsEmail method, of class ErrorReport.
   */
  @Test
  public void testGetReportorsEmail() {
    System.out.println("getReportorsEmail");  
    String result = instance.getReportorsEmail();
    assertEquals(null, result); 
  }

  /**
   * Test of setReportorsEmail method, of class ErrorReport.
   */
  @Test
  public void testSetReportorsEmail() {
    System.out.println("setReportorsEmail");
    String reportorsEmail = "test@nrm.se"; 
    instance.setReportorsEmail(reportorsEmail); 
    assertEquals(reportorsEmail, instance.getReportorsEmail());
  }
  
}
