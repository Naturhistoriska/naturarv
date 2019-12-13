package se.nrm.dina.web.portal.logic.errorhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class CustomExceptionHandlerFactoryTest {
  
  private CustomExceptionHandlerFactory instance;
   
  public CustomExceptionHandlerFactoryTest() {
  }
   
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
  
  @Test
  public void testDefaultConstuctor() {
    instance = new CustomExceptionHandlerFactory();
    assertNotNull(instance);
  }

  /**
   * Test of getExceptionHandler method, of class CustomExceptionHandlerFactory.
   */
  @Test
  public void testGetExceptionHandler() {
    System.out.println("getExceptionHandler");
    ExceptionHandlerFactory exceptionHandlerFactory = mock(ExceptionHandlerFactory.class);
    ExceptionHandler handler = mock(ExceptionHandler.class);
    when(exceptionHandlerFactory.getExceptionHandler()).thenReturn(handler);
    instance = new CustomExceptionHandlerFactory(exceptionHandlerFactory);
 
    ExceptionHandler result = instance.getExceptionHandler();
    assertNotNull(result);
  }
  
}
