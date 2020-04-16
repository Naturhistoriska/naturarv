package se.nrm.dina.web.portal.controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.ContextMocker;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class IdleMonitorControllerTest {
  
  public IdleMonitorControllerTest() {
  }
 
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of idleListener method, of class IdleMonitorController.
   * @throws java.lang.Exception
   */
  @Test
  public void testIdleListener() throws Exception {
    HttpSession session = mock(HttpSession.class); 
    HttpServletRequest request = mock(HttpServletRequest.class); 
    when(request.getSession(false)).thenReturn(session);
    
    ExternalContext ext = mock(ExternalContext.class);
    when(ext.getRequest()).thenReturn(request);
    
    FacesContext context = ContextMocker.mockFacesContext(); 
    when(context.getExternalContext()).thenReturn(ext);
    
    IdleMonitorController instance = new IdleMonitorController();
    instance.idleListener(); 
    verify(session, times(1)).invalidate();
  }
  
}
