package se.nrm.dina.web.portal.model;

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
public class ErropReportEmailTest {
  
  private ErropReportEmail instance;
  
  public ErropReportEmailTest() {
  }
 
  @Before
  public void setUp() {
    instance = new ErropReportEmail();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of createErrorReport method, of class ErropReportEmail.
   */
  @Test
  public void testCreateErrorReport() {
    System.out.println("createErrorReport");
    SolrData data = null;
    ErrorReport error = null;
    boolean isSwedish = false; 
    
    String expResult = "";
    String result = instance.createErrorReport(data, error, isSwedish);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testCreateErrorReport1() {
    System.out.println("createErrorReport");
    SolrData data = new SolrData();
    
    ErrorReport error = new ErrorReport();
    error.setReportorsEmail("test@nrm.se");
    error.setCatalogNumber(true);
    boolean isSwedish = false; 
     
    String result = instance.createErrorReport(data, error, isSwedish);
    assertFalse(result.isEmpty());  
  }
  
}
