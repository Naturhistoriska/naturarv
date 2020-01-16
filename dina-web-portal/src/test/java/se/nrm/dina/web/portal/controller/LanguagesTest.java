package se.nrm.dina.web.portal.controller;
 
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.PrimeFaces;
import org.primefaces.PrimeFaces.Ajax;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.PrimeFacesMocker;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class LanguagesTest {
  
  private Languages instance;
  private FacesContext context;   
  private PrimeFaces faces;   
  
  @Mock
  private ExternalContext externalContext;  
  @Mock
  private HttpSession session;  
  
  
  @Mock
  private ChartView chart; 
  @Mock
  private SearchBean search; 
  @Mock
  private StatisticBean statistic; 
  @Mock
  private StyleBean style;
  
  public LanguagesTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    context = ContextMocker.mockFacesContext(); 
    when(externalContext.getSession(true)).thenReturn(session);
    when(context.getExternalContext()).thenReturn(externalContext);
    
    Ajax ajax = mock(PrimeFaces.Ajax.class);
    faces = PrimeFacesMocker.mockPrimeFaces(); 
    when(faces.ajax()).thenReturn(ajax);
    instance = new Languages(chart, search, statistic, style);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new Languages();
    assertNotNull(instance);
    instance.changeLanguage("en");
  }

  /**
   * Test of getLocale method, of class Languages.
   */
  @Test
  public void testGetLocale() {
    System.out.println("getLocale"); 
    String expResult = "sv";
    String result = instance.getLocale();
    assertEquals(expResult, result); 
  }

  /**
   * Test of changeLanguage method, of class Languages.
   */
  @Test
  public void testChangeLanguageSv() {
    System.out.println("changeLanguage");
    String locale = "sv";  
    
    try {
      instance.changeLanguage(locale); 
      verifyZeroInteractions(style);
      verifyZeroInteractions(search);
      verifyZeroInteractions(chart);
      verifyZeroInteractions(statistic);
      verifyZeroInteractions(session); 
    } finally {
      context.release(); 
    } 
  }
  
    /**
   * Test of changeLanguage method, of class Languages.
   */
  @Test
  public void testChangeLanguageEn() {
    System.out.println("changeLanguage");
    String locale = "en"; 
    try {
      instance.changeLanguage(locale);  
      verify(style, times(1)).setLanguageStyle(any(String.class)); 
      verify(chart, times(1)).changeLanguage(any(boolean.class));
      verify(statistic, times(1)).changeLanguage(any(boolean.class));
      verify(search, times(1)).changeLanguage(any(boolean.class));
      verify(faces, times(1)).ajax();
    } finally {
      context.release(); 
    } 
  }

  /**
   * Test of getLanguage method, of class Languages.
   */
  @Test
  public void testGetLanguageSv() {
    System.out.println("getLanguage"); 
    String expResult = "English";
    String result = instance.getLanguage();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetLanguageEn() {
    System.out.println("getLanguage"); 
    
    instance.changeLanguage("en");
    String expResult = "Svenska";
    String result = instance.getLanguage();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isSwedish method, of class Languages.
   */
  @Test
  public void testIsSwedish() {
    System.out.println("isSwedish"); 
    boolean expResult = true;
    boolean result = instance.isSwedish();
    assertEquals(expResult, result); 
  }
  
}
