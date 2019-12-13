package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class StyleBeanTest {
  
  private StyleBean instance;
   
  private final String ACTIVE_LINK = "activelink";
  private final String INACTIVE_LINK = "inactivelink";
  private final String INACTIVE_TAB_LINK = "inactiveTablink";

  private final String HOME = "home";
  private final String COLLECTIONS = "collections";
  private final String PARTNERS = "partners";
  private final String FAQ = "faq";
  private final String ABOUT = "about";
  private final String CONTACT = "contact";

  
  public StyleBeanTest() {
  }
   
  @Before
  public void setUp() {
    instance = new StyleBean();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getEnLink method, of class StyleBean.
   */
  @Test
  public void testGetEnLink() {
    System.out.println("getEnLink"); 
      
    String result = instance.getEnLink();
    assertEquals(INACTIVE_LINK, result); 
  }

  /**
   * Test of getSvLink method, of class StyleBean.
   */
 @Test
  public void testGetSvLink() {
    System.out.println("getSvLink");  
    String result = instance.getSvLink();
    assertEquals(ACTIVE_LINK, result); 
  }

  /**
   * Test of getTabStart method, of class StyleBean.
   */
  @Test
  public void testGetTabStart() {
    System.out.println("getTabStart");  
    String result = instance.getTabStart();
    assertEquals(ACTIVE_LINK, result); 
  }

  /**
   * Test of getTabCollections method, of class StyleBean.
   */
  @Test
  public void testGetTabCollections() {
    System.out.println("getTabCollections");  
    String result = instance.getTabCollections();
    assertEquals(INACTIVE_TAB_LINK, result); 
  }

  /**
   * Test of getTabPartners method, of class StyleBean.
   */
  @Test
  public void testGetTabPartners() {
    System.out.println("getTabPartners");  
    String result = instance.getTabPartners(); 
    assertEquals(INACTIVE_TAB_LINK, result); 
  }

  /**
   * Test of getTabFaq method, of class StyleBean.
   */
  @Test
  public void testGetTabFaq() {
    System.out.println("getTabFaq");  
    String result = instance.getTabFaq();
    assertEquals(INACTIVE_TAB_LINK, result); 
  }

  /**
   * Test of getTabAbout method, of class StyleBean.
   */
  @Test
  public void testGetTabAbout() {
    System.out.println("getTabAbout");  
    String result = instance.getTabAbout();
    assertEquals(INACTIVE_TAB_LINK, result); 
  }

  /**
   * Test of setTabStyle method, of class StyleBean.
   */
  @Test
  public void testSetTabStyle1() {
    System.out.println("setTabStyle"); 
    
    instance.setTabStyle(HOME);   
    assertEquals(ACTIVE_LINK, instance.getTabStart()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabAbout()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabCollections()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabFaq()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabPartners());  
  }
  
    @Test
  public void testSetTabStyle2() {
    System.out.println("setTabStyle");
    instance.setTabStyle(ABOUT);   
    assertEquals(INACTIVE_TAB_LINK, instance.getTabStart()); 
    assertEquals(ACTIVE_LINK, instance.getTabAbout()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabCollections()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabFaq()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabPartners());  
  }
  
    @Test
  public void testSetTabStyle3() {
    System.out.println("setTabStyle");
 
    instance.setTabStyle(COLLECTIONS); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabStart()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabAbout()); 
    assertEquals(ACTIVE_LINK, instance.getTabCollections()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabFaq()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabPartners());  
  }
  
    @Test
  public void testSetTabStyle4() {
    System.out.println("setTabStyle"); 
    instance.setTabStyle(FAQ); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabStart()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabAbout()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabCollections()); 
    assertEquals(ACTIVE_LINK, instance.getTabFaq()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabPartners());  
  }
  
  
  @Test
  public void testSetTabStyle5() {
    System.out.println("setTabStyle"); 
    instance.setTabStyle(PARTNERS); 
    
    assertEquals(INACTIVE_TAB_LINK, instance.getTabStart()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabAbout()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabCollections()); 
    assertEquals(INACTIVE_TAB_LINK, instance.getTabFaq()); 
    assertEquals(ACTIVE_LINK, instance.getTabPartners());  
  }

  /**
   * Test of setLanguageStyle method, of class StyleBean.
   */
  @Test
  public void testSetLanguageStyleSv() { 
    String locale = "sv"; 
    instance.setLanguageStyle(locale); 
    assertEquals(instance.getSvLink(), ACTIVE_LINK);
    assertEquals(instance.getEnLink(), INACTIVE_LINK);
  }
  
  @Test
  public void testSetLanguageStyleEn() { 
    String locale = "en"; 
    instance.setLanguageStyle(locale); 
    assertEquals(instance.getSvLink(), INACTIVE_LINK);
    assertEquals(instance.getEnLink(), ACTIVE_LINK);
  }
  
}
