package se.nrm.dina.web.portal.logic.config;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 

/**
 *
 * @author idali
 */
public class InitialPropertiesTest {
  
  private InitialProperties instance1; 
  private InitialProperties instance2;
  private final String solrPath = "http://solr.nrm.se";
  private final String solrCore = "core";     
  private final String morphbankThumbPath = "morphbankThumbPath";
  private final String mapKey = "mapKey";
  private final String captchaPublicKey = "captchaPublicKey";
  private final String captchaPrivateKey = "captchaPrivateKey";
  private final String mailHost = "mailHost";
  private final String mailHostName = "mailHostName";
  private final String supportMail = "supportMail";
  private final String teamsupportMail = "teamsupportMail";
  private final String supportPhone = "supportPhone";
  
  public InitialPropertiesTest() {
  }
    
  @Before
  public void setUp() {
    instance1 = new InitialProperties();
    instance2 = new InitialProperties(solrPath, solrCore, morphbankThumbPath, mapKey, 
            captchaPublicKey, captchaPrivateKey, mailHost, mailHostName, supportMail, 
            teamsupportMail, supportPhone);
  }
  
  @After
  public void tearDown() {
    instance1 = null;
    instance2 = null;
  }

  /**
   * Test of getSolrPath method, of class InitialProperties.
   */
  @Test
  public void testGetSolrPath1() {
    System.out.println("getSolrPath");  
    String result = instance2.getSolrPath();
    assertEquals(solrPath, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetSolrPath2() {
    System.out.println("getSolrPath");  
    instance1.getSolrPath();
  }


  /**
   * Test of getSolrURL method, of class InitialProperties.
   */
  @Test
  public void testGetSolrURL1() {
    System.out.println("getSolrURL");  
    String result = instance2.getSolrURL();
    assertEquals(solrPath + "/" + solrCore, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetSolrURL2() {
    System.out.println("getSolrURL");  
    instance1.getSolrURL(); 
  }


  /**
   * Test of getMorphbankThumbPath method, of class InitialProperties.
   */
  @Test
  public void testGetMorphbankThumbPath1() {
    System.out.println("getMorphbankThumbPath");  
    String result = instance2.getMorphbankThumbPath();
    assertEquals(morphbankThumbPath, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetMorphbankThumbPath2() {
    System.out.println("getMorphbankThumbPath");  
    instance1.getMorphbankThumbPath(); 
  }

  /**
   * Test of getMapKey method, of class InitialProperties.
   */
  @Test
  public void testGetMapKey1() {
    System.out.println("getMapKey");  
    String result = instance2.getMapKey();
    assertEquals(mapKey, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetMapKey2() {
    System.out.println("getMapKey");  
    instance1.getMapKey(); 
  }

  /**
   * Test of getCaptchaPublicKey method, of class InitialProperties.
   */
  @Test
  public void testGetCaptchaPublicKey1() {
    System.out.println("getCaptchaPublicKey");  
    String result = instance2.getCaptchaPublicKey();
    assertEquals(captchaPublicKey, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetCaptchaPublicKey2() {
    System.out.println("getCaptchaPublicKey");  
    instance1.getCaptchaPublicKey(); 
  }

  /**
   * Test of getCaptchaPrivateKey method, of class InitialProperties.
   */
  @Test
  public void testGetCaptchaPrivateKey1() {
    System.out.println("getCaptchaPrivateKey"); 
    String result = instance2.getCaptchaPrivateKey();
    assertEquals(captchaPrivateKey, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetCaptchaPrivateKey2() {
    System.out.println("getCaptchaPrivateKey"); 
    instance1.getCaptchaPrivateKey(); 
  }

  /**
   * Test of getMailHost method, of class InitialProperties.
   */
  @Test
  public void testGetMailHost1() {
    System.out.println("getMailHost"); 
    String result = instance2.getMailHost();
    assertEquals(mailHost, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetMailHost2() {
    System.out.println("getMailHost"); 
    instance1.getMailHost(); 
  }

  /**
   * Test of getMailHostName method, of class InitialProperties.
   */
  @Test
  public void testGetMailHostName1() {
    System.out.println("getMailHostName"); 
    String result = instance2.getMailHostName();
    assertEquals(mailHostName, result);  
  }
  
  @Test(expected = RuntimeException.class) 
  public void testGetMailHostName2() {
    System.out.println("getMailHostName"); 
    instance1.getMailHostName(); 
  }

  /**
   * Test of getSupportMail method, of class InitialProperties.
   */
  @Test
  public void testGetSupportMail1() {
    System.out.println("getSupportMail");  
    String result = instance2.getSupportMail();
    assertEquals(supportMail, result); 
  }
   
  @Test(expected = RuntimeException.class)
  public void testGetSupportMail2() {
    System.out.println("getSupportMail");  
    instance1.getSupportMail(); 
  }

  /**
   * Test of getTeamSupportMail method, of class InitialProperties.
   */
  @Test
  public void testGetTeamSupportMail1() {
    System.out.println("getTeamSupportMail"); 
    String result = instance2.getTeamSupportMail();
    assertEquals(teamsupportMail, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetTeamSupportMai2l() {
    System.out.println("getTeamSupportMail"); 
    instance1.getTeamSupportMail(); 
  }

  /**
   * Test of getSupportPhone method, of class InitialProperties.
   */
  @Test
  public void testGetSupportPhone1() {
    System.out.println("getSupportPhone"); 
    String result = instance2.getSupportPhone();
    assertEquals(supportPhone, result); 
  }
  
  @Test(expected = RuntimeException.class)
  public void testGetSupportPhone2() {
    System.out.println("getSupportPhone"); 
    instance1.getSupportPhone(); 
  }
  
}
