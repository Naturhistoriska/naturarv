package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.logic.mail.MailHandler;
import se.nrm.dina.web.portal.model.ErrorReport;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class ErrorReportBeanTest {
  
  private ErrorReportBean instance;
  
  @Mock
  private Navigator navigator;
  @Mock 
  private MailHandler mail;
  
  public ErrorReportBeanTest() {
  }
   
  @Before
  public void setUp() { 
    instance = new ErrorReportBean(navigator, mail);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new ErrorReportBean();
    assertNotNull(instance);
    
    ErrorReport report = instance.getErrorReport();
    assertNotNull(report);
    instance.sendErrorReport();
  }

  /**
   * Test of onBlur method, of class ErrorReportBean.
   */
  @Test
  public void testOnBlur() {
    System.out.println("onBlur"); 
    instance.onBlur(); 
  }

  /**
   * Test of sendErrorReport method, of class ErrorReportBean.
   */
  @Test
  public void testSendErrorReport() {
    System.out.println("sendErrorReport"); 
    
    SolrData errorData = mock(SolrData.class);
    instance.reportError(errorData);
    instance.sendErrorReport(); 
    ErrorReport report = instance.getErrorReport();
    
    verify(mail, times(1)).sendMail(errorData, report, true);
    verify(navigator, times(1)).gotoResults();
  }

  /**
   * Test of validateEmail method, of class ErrorReportBean.
   */
  @Test
  public void testValidateEmail() {
    System.out.println("validateEmail"); 
    
    instance.validateEmail(); 
  }

  /**
   * Test of reportError method, of class ErrorReportBean.
   */
  @Test
  public void testReportError() {
    System.out.println("reportError");
    SolrData errorData = mock(SolrData.class); 
    String catalogNumber = "cat12345";
    when(errorData.getCatalogNumber()).thenReturn(catalogNumber);
    instance.reportError(errorData); 
    
    assertNotNull(instance.getErrorData());
    assertEquals(instance.getErrorData().getCatalogNumber(), catalogNumber);
    verify(navigator, times(1)).gotoErrorReportPage();
  }

  /**
   * Test of getErrorData method, of class ErrorReportBean.
   */
  @Test
  public void testGetErrorData() {
    System.out.println("getErrorData");  
    
    SolrData errorData = mock(SolrData.class); 
    String catalogNumber = "cat12345";
    when(errorData.getCatalogNumber()).thenReturn(catalogNumber);
    instance.reportError(errorData); 
    SolrData result = instance.getErrorData(); 
    
    assertNotNull(result);
    assertEquals(result.getCatalogNumber(), catalogNumber);
  }

  /**
   * Test of getErrorReport method, of class ErrorReportBean.
   */
  @Test
  public void testGetErrorReport() {
    System.out.println("getErrorReport");  
    ErrorReport result = instance.getErrorReport();
    assertNotNull(result); 
  }

  /**
   * Test of setErrorReport method, of class ErrorReportBean.
   */
  @Test
  public void testSetErrorReport() {
    System.out.println("setErrorReport"); 
    
    ErrorReport errorReport = instance.getErrorReport();
    instance.setErrorReport(errorReport); 
    
    assertNotNull(instance.getErrorReport());
  } 
}
