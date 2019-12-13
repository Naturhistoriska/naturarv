package se.nrm.dina.web.portal.logic.errorhandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.MockIterator;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class)
public class CustomExceptionHandlerTest {
  
  private CustomExceptionHandler instance;
  
  @Mock
  private ExceptionHandler exceptionHandler;
  
  public CustomExceptionHandlerTest() {
  }
 
  @Before
  public void setUp() {
    instance = new CustomExceptionHandler(exceptionHandler);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getWrapped method, of class CustomExceptionHandler.
   */
  @Test
  public void testGetWrapped() {
    System.out.println("getWrapped"); 
    ExceptionHandler result = instance.getWrapped();
    assertNotNull(result);
  }

  /**
   * Test of handle method, of class CustomExceptionHandler.
   * @throws java.io.IOException
   */
  @Test
  public void testHandle() throws IOException {
    System.out.println("handle");
    Map<String, Object> requestMap = new HashMap<>();
    ExternalContext ext = mock(ExternalContext.class);
    when(ext.getRequestMap()).thenReturn(requestMap);
    
    FacesContext context = ContextMocker.mockFacesContext();   
    when(context.getExternalContext()).thenReturn(ext);
 
    ExceptionQueuedEvent mockEvent = mock(ExceptionQueuedEvent.class);
    ExceptionQueuedEventContext exceptionQueuedEventContext = mock(ExceptionQueuedEventContext.class);
 
    Set<ExceptionQueuedEvent> mockIterable = mock(Set.class);
    MockIterator.mockIterable(mockIterable, mockEvent, mockEvent, mockEvent);
     
    Throwable throwable = mock(Throwable.class);
    when(throwable.getMessage()).thenReturn("java.lang.NullPointerException");
      
    Iterator<ExceptionQueuedEvent> mockIterator = mock(Iterator.class);
    when(mockIterable.iterator()).thenReturn(mockIterator);
    when(mockIterator.hasNext()).thenReturn(true, false);
    when(mockIterator.next()).thenReturn(mockEvent);
    when(mockEvent.getSource()).thenReturn(exceptionQueuedEventContext);
    when(exceptionQueuedEventContext.getException()).thenReturn(throwable);
    when(exceptionHandler.getUnhandledExceptionQueuedEvents()).thenReturn(mockIterable);
    try {
      instance.handle();
      verify(context, times(2)).getExternalContext(); 
      verify(ext, times(1)).redirect(any(String.class)); 
      verify(mockIterator, times(1)).remove(); 
    } finally {
      context.release();
    }  
  }
  
}
