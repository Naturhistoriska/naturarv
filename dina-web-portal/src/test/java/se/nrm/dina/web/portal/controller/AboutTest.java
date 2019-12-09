package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class AboutTest {
  
  private About instance;
  
  @Mock
  private Languages languages;
  
  public AboutTest() {
  }
   
  @Before
  public void setUp() {
    instance = new About(languages);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
 
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new About();
    assertNotNull(instance); 
    instance.getDevelopmentText();
  }
  /**
   * Test of getWhatIsNaturarvText method, of class About.
   */
  @Test
  public void testGetWhatIsNaturarvTextSv() {
    System.out.println("getWhatIsNaturarvText"); 
    
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "På Naturarv kan du söka information om föremål i";
    String result = instance.getWhatIsNaturarvText();
    assertTrue(result.startsWith(expResult)); 
  }
  
  /**
   * Test of getWhatIsNaturarvText method, of class About.
   */
  @Test
  public void testGetWhatIsNaturarvTextEn() {
    System.out.println("getWhatIsNaturarvText"); 
    
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "You can use this search portal to find information about specimens";
    String result = instance.getWhatIsNaturarvText();
    assertTrue(result.startsWith(expResult)); 
  }


  /**
   * Test of getWhatIsNaurarvSubText method, of class About.
   */
  @Test
  public void testGetWhatIsNaurarvSubTextEn() {
    System.out.println("getWhatIsNaurarvSubText"); 
    
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "The target audience is primarily collections-based researchers and museum staff";
    String result = instance.getWhatIsNaurarvSubText();
    assertTrue(result.startsWith(expResult)); 
  }
  
   /**
   * Test of getWhatIsNaurarvSubText method, of class About.
   */
  @Test
  public void testGetWhatIsNaurarvSubTextSv() {
    System.out.println("getWhatIsNaurarvSubText"); 
    
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "Naturarv vänder sig i första hand till forskare och personal";
    String result = instance.getWhatIsNaurarvSubText();
    assertTrue(result.startsWith(expResult)); 
  }

  /**
   * Test of getPartInfrastructureText method, of class About.
   */
  @Test
  public void testGetPartInfrastructureTextSv() {
    System.out.println("getPartInfrastructureText"); 
    
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "Arbetet med att bygga upp en digital infrastruktur för Sveriges naturhistoriska";
    String result = instance.getPartInfrastructureText();
    assertTrue(result.startsWith(expResult)); 
  }
  
  @Test
  public void testGetPartInfrastructureTextEn() {
    System.out.println("getPartInfrastructureText"); 
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "Efforts to build a digital infrastructure for Swedish natural history collections";
    String result = instance.getPartInfrastructureText();
    assertTrue(result.startsWith(expResult)); 
  }

  /**
   * Test of getPartInfrastructureSubText method, of class About.
   */
  @Test
  public void testGetPartInfrastructureSubTextEn() {
    System.out.println("getPartInfrastructureSubText"); 
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "Naturarv is intended to eventually contain";
    String result = instance.getPartInfrastructureSubText();
    assertTrue(result.startsWith(expResult));  
  }
  
  @Test
  public void testGetPartInfrastructureSubTextSv() {
    System.out.println("getPartInfrastructureSubText"); 
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "Förhoppningen är att Naturarv så småningom ska";
    String result = instance.getPartInfrastructureSubText();
    assertTrue(result.startsWith(expResult));  
  }

  /**
   * Test of getDevelopmentText method, of class About.
   */
  @Test
  public void testGetDevelopmentTextSv() {
    System.out.println("getDevelopmentText"); 
    
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "Den tekniska utvecklingen av komponenterna";
    String result = instance.getDevelopmentText();
    assertTrue(result.startsWith(expResult));  
  }
  
  @Test
  public void testGetDevelopmentTextEn() {
    System.out.println("getDevelopmentText");
    
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "The development of Naturarv components has";
    String result = instance.getDevelopmentText();
    assertTrue(result.startsWith(expResult));  
  }
   
  /**
   * Test of getFinancialSupportText method, of class About.
   */
  @Test
  public void testGetFinancialSupportTextSv() {
    System.out.println("getFinancialSupportText"); 
    
    when(languages.isSwedish()).thenReturn(true);
    String expResult = "DINA-projektet stöds av de samarbetande institutionerna";
    String result = instance.getFinancialSupportText();
    assertTrue(result.startsWith(expResult));  
  }
  
  @Test
  public void testGetFinancialSupportTextEn() {
    System.out.println("getFinancialSupportText"); 
     
    when(languages.isSwedish()).thenReturn(false);
    String expResult = "The DINA project is supported by participating";
    String result = instance.getFinancialSupportText();
    assertTrue(result.startsWith(expResult));  
  }
  
}
