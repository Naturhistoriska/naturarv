package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.logic.config.InitialProperties;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class ContactViewTest {
  
  private ContactView instance;
  private String supportMail;
  private String supportPhone;
  
  @Mock
  private InitialProperties properties;
  
  public ContactViewTest() {
  }
 
  @Before
  public void setUp() {
    supportMail = "team@support.se";
    supportPhone = "1234567";
    when(properties.getTeamSupportMail()).thenReturn(supportMail);
    when(properties.getSupportPhone()).thenReturn(supportPhone);
    
    instance = new ContactView(properties) ; 
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
 
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new ContactView(); 
    assertNotNull(instance); 
    instance.init();
  }
  
  /**
   * Test of init method, of class ContactView.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
    assertNotNull(instance.getSupportMail());
    assertNotNull(instance.getSupportPhone());
  }

  /**
   * Test of getSupportMail method, of class ContactView.
   */
  @Test
  public void testGetSupportMail() {
    System.out.println("getSupportMail"); 
    instance.init();
    String result = instance.getSupportMail();
    assertEquals(supportMail, result); 
  }

  /**
   * Test of setSupportMail method, of class ContactView.
   */
  @Test
  public void testSetSupportMail() {
    System.out.println("setSupportMail"); 
    
    instance.setSupportMail(supportMail); 
    assertEquals(supportMail, instance.getSupportMail()); 
  }

  /**
   * Test of getSupportPhone method, of class ContactView.
   */
  @Test
  public void testGetSupportPhone() {
    System.out.println("getSupportPhone");  
    
    instance.init();
    String result = instance.getSupportPhone();
    assertEquals(supportPhone, result); 
  }

  /**
   * Test of setSupportPhone method, of class ContactView.
   */
  @Test
  public void testSetSupportPhone() {
    System.out.println("setSupportPhone"); 
    instance.setSupportPhone(supportPhone); 
    assertEquals(supportPhone, instance.getSupportPhone()); 
  } 
}
