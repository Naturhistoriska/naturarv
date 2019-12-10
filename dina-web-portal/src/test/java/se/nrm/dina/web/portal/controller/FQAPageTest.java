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
public class FQAPageTest {
  
  private FQAPage instance;
  
  @Mock
  private Languages language;
  
  public FQAPageTest() {
  }
   
  @Before
  public void setUp() { 
    instance = new FQAPage(language);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new FQAPage();
    assertNotNull(instance);
    instance.getHowContactText();
  }

  /**
   * Test of getWhatIsNaturarvText method, of class FQAPage.
   */
  @Test
  public void testGetWhatIsNaturarvTextSv() {
    System.out.println("getWhatIsNaturarvText"); 
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Naturarv är en webbplats där du kan söka";
    String result = instance.getWhatIsNaturarvText();
    assertTrue(result.startsWith(expResult)); 
  }
  
  @Test
  public void testGetWhatIsNaturarvTextEn() {
    System.out.println("getWhatIsNaturarvText"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "Naturarv is a website where you can search databases";
    String result = instance.getWhatIsNaturarvText();
    assertTrue(result.startsWith(expResult)); 
  }

  /**
   * Test of getWhoIsResponsibleText1 method, of class FQAPage.
   */
  @Test
  public void testGetWhoIsResponsibleText1Sv() {
    System.out.println("getWhoIsResponsibleText1");
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Merparten av innehållet som publiceras på Naturarv";
    String result = instance.getWhoIsResponsibleText1();
    assertTrue(result.startsWith(expResult));
  }

  @Test
  public void testGetWhoIsResponsibleText1En() {
    System.out.println("getWhoIsResponsibleText1");
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "The majority of Naturarv content published";
    String result = instance.getWhoIsResponsibleText1();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getWhoIsResponsibleText2 method, of class FQAPage.
   */
  @Test
  public void testGetWhoIsResponsibleText2Sv() {
    System.out.println("getWhoIsResponsibleText2");
    
    when(language.isSwedish()).thenReturn(true);
    
    String expResult = "kan du läsa om vilka som bidrar med innehåll";
    String result = instance.getWhoIsResponsibleText2();
    assertTrue(result.startsWith(expResult));
  }

  @Test
  public void testGetWhoIsResponsibleText2En() {
    System.out.println("getWhoIsResponsibleText2");
    
    when(language.isSwedish()).thenReturn(false);
    
    String expResult = " one can read about the institutions that have contributed data";
    String result = instance.getWhoIsResponsibleText2();
    assertTrue(result.startsWith(expResult));
  }
  
  /**
   * Test of getWhatInformationText1 method, of class FQAPage.
   */
  @Test
  public void testGetWhatInformationText1Sv() {
    System.out.println("getWhatInformationText1"); 
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Naturarv presenterar information från naturhistoriska";
    String result = instance.getWhatInformationText1();
    assertTrue(result.startsWith(expResult));
  }

  @Test
  public void testGetWhatInformationText1En() {
    System.out.println("getWhatInformationText1"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "Naturarv presents information from a wide range of natural history ";
    String result = instance.getWhatInformationText1();
    assertTrue(result.startsWith(expResult));
  }
  
  /**
   * Test of getWhatInformationText2 method, of class FQAPage.
   */
  @Test
  public void testGetWhatInformationText2Sv() {
    System.out.println("getWhatInformationText2"); 
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "På Naturarv publiceras bara en del av innehållet";
    String result = instance.getWhatInformationText2();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetWhatInformationText2En() {
    System.out.println("getWhatInformationText2"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "Only a proportion of the total collections";
    String result = instance.getWhatInformationText2();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getWhatInformationText3 method, of class FQAPage.
   */
  @Test
  public void testGetWhatInformationText3Sv() {
    System.out.println("getWhatInformationText3");
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Hur information hanteras kan ibland skilja sig ";
    String result = instance.getWhatInformationText3();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetWhatInformationText3En() {
    System.out.println("getWhatInformationText3");
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "How information is handled may differ among ";
    String result = instance.getWhatInformationText3();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getWhatSourceText1 method, of class FQAPage.
   */
  @Test
  public void testGetWhatSourceText1Sv() {
    System.out.println("getWhatSourceText1"); 
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Informationen på Naturarv hämtas från ";
    String result = instance.getWhatSourceText1();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetWhatSourceText1En() {
    System.out.println("getWhatSourceText1"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "Information in Naturarv is compiled ";
    String result = instance.getWhatSourceText1();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getWhatSourceText2 method, of class FQAPage.
   */
  @Test
  public void testGetWhatSourceText2Sv() {
    System.out.println("getWhatSourceText2"); 
    
     when(language.isSwedish()).thenReturn(true);
    String expResult = "kan du se alla institutioner som finns";
    String result = instance.getWhatSourceText2();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetWhatSourceText2En() {
    System.out.println("getWhatSourceText2"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = ", one can find a list of institutions ";
    String result = instance.getWhatSourceText2();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getTrustText method, of class FQAPage.
   */
  @Test
  public void testGetTrustTextSv() {
    System.out.println("getTrustText");
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Målet är att alla uppgifter som publiceras ";
    String result = instance.getTrustText();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetTrustTextEn() {
    System.out.println("getTrustText");
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "The objective is to provide data via Naturarv ";
    String result = instance.getTrustText();
    assertTrue(result.startsWith(expResult));
  }

  /**
   * Test of getHowOftenText method, of class FQAPage.
   */
  @Test
  public void testGetHowOftenTextSe() {
    System.out.println("getHowOftenText"); 
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Den senaste informationen hämtas en gång ";
    String result = instance.getHowOftenText();
    assertTrue(result.startsWith(expResult));
  }
  
  @Test
  public void testGetHowOftenTextEn() {
    System.out.println("getHowOftenText"); 
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "The latest information is imported once daily ";
    String result = instance.getHowOftenText();
    assertTrue(result.startsWith(expResult));
  }

  /** 
   * Test of getHowContactText method, of class FQAPage.
   */
  @Test
  public void testGetHowContactTextSv() {
    System.out.println("getHowContactText");
    
    when(language.isSwedish()).thenReturn(true);
    String expResult = "Våra kontaktuppgifter hittar du under";
    String result = instance.getHowContactText();
    assertTrue(result.startsWith(expResult));
  }
   
  @Test
  public void testGetHowContactTextEn() {
    System.out.println("getHowContactText");
    
    when(language.isSwedish()).thenReturn(false);
    String expResult = "Our contact information is found under";
    String result = instance.getHowContactText();
    assertTrue(result.startsWith(expResult));
  }
}
