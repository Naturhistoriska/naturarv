package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 

/**
 *
 * @author idali
 */
public class ErrorBeanTest {
  
  private ErrorBean instance;
  
  public ErrorBeanTest() {
  }
 
  @Before
  public void setUp() {
    instance = new ErrorBean();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of throwError method, of class ErrorBean.
   */
  @Test(expected = RuntimeException.class)
  public void testThrowError() {
    System.out.println("throwError"); 
    instance.throwError(); 
  }
  
}
