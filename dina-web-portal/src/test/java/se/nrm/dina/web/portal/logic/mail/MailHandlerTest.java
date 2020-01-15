package se.nrm.dina.web.portal.logic.mail;

import org.junit.After; 
import org.junit.Before;  
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.ErrorReport;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class MailHandlerTest {
  
  private MailHandler instance;
  private SolrData data;
  private ErrorReport error;
  
  private String mailHostName;
  private String mailHost;
  private String supportMail;
  
  @Mock
  private InitialProperties properties;
  
  public MailHandlerTest() {
  }
 
  @Before
  public void setUp() {
    data = new SolrData();
    error = new ErrorReport();
    
    mailHostName = "mail.smtp.host";
    mailHost = "mail.nrm.se";
    supportMail = "teamsupport@nrm.se";
    when(properties.getMailHostName()).thenReturn(mailHostName);
    when(properties.getMailHost()).thenReturn(mailHost);
    when(properties.getSupportMail()).thenReturn(supportMail);
    
    instance = new MailHandler(properties);
  }
  
  @After
  public void tearDown() {
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new MailHandler();
    assertNotNull(instance); 
    instance.sendMail(data, error, true);
  }

  /**
   * Test of sendMail method, of class MailHandler.
   */
  @Test
  public void testSendMail() {
    System.out.println("sendMail");
     
    boolean isSwedish = false;
    instance.sendMail(data, error, isSwedish); 
    
    verify(properties, times(1)).getMailHostName();
  }
  
}
