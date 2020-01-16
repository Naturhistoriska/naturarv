package se.nrm.dina.web.portal.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.PrimeFaces;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.PrimeFacesMocker;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class HelpClassTest {
  
  private HelpClass instance;
  
  private PrimeFaces faces;   
  private FacesContext context;  
  @Mock
  private ExternalContext externalContext;  
  @Mock
  private HttpSession session; 
  
  public HelpClassTest() {
  }
 
  @Before
  public void setUp() {
    context = ContextMocker.mockFacesContext(); 
    when(externalContext.getSession(true)).thenReturn(session);  
    when(context.getExternalContext()).thenReturn(externalContext);
    
    PrimeFaces.Ajax ajax = mock(PrimeFaces.Ajax.class);
    faces = PrimeFacesMocker.mockPrimeFaces(); 
    when(faces.ajax()).thenReturn(ajax);
    instance = HelpClass.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class HelpClass.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance); 
  }

  /**
   * Test of getSession method, of class HelpClass.
   */
  @Test
  public void testGetSession() {
    System.out.println("getSession");  
     
    try {
      HttpSession result = instance.getSession();
      assertNotNull(result); 
    } finally {
      context.release(); 
    } 
  }

  /**
   * Test of buildResultHeaderSummaryForMapView method, of class HelpClass.
   */
  @Test
  public void testBuildResultHeaderSummaryForMapViewEn() {
    System.out.println("buildResultHeaderSummaryForMapView");
    int totalResult = 200;
    boolean isSwedish = false; 
    String expResult = "(200 hits)";
    String result = instance.buildResultHeaderSummaryForMapView(totalResult, isSwedish);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testBuildResultHeaderSummaryForMapViewSv() {
    System.out.println("buildResultHeaderSummaryForMapView");
    int totalResult = 200;
    boolean isSwedish = true; 
    String expResult = "(200 träffar)";
    String result = instance.buildResultHeaderSummaryForMapView(totalResult, isSwedish);
    assertEquals(expResult, result); 
  }

  /**
   * Test of buildResultHeaderSummaryForResultView method, of class HelpClass.
   */
  @Test
  public void testBuildResultHeaderSummaryForResultView() {
    System.out.println("buildResultHeaderSummaryForResultView");
    int totalResult = 0;
    boolean isSwedish = false; 
    String expResult = " ";
    String result = instance.buildResultHeaderSummaryForResultView(totalResult, isSwedish);
    assertEquals(expResult, result); 
  }

  @Test
  public void testBuildResultHeaderSummaryForResultViewEn() {
    System.out.println("buildResultHeaderSummaryForResultView");
    int totalResult = 200;
    boolean isSwedish = false; 
    String expResult = "Selected 200";
    String result = instance.buildResultHeaderSummaryForResultView(totalResult, isSwedish);
    assertEquals(expResult, result); 
  }

  @Test
  public void testBuildResultHeaderSummaryForResultViewSv() {
    System.out.println("buildResultHeaderSummaryForResultView");
    int totalResult = 200;
    boolean isSwedish = true; 
    String expResult = "Valda 200";
    String result = instance.buildResultHeaderSummaryForResultView(totalResult, isSwedish);
    assertEquals(expResult, result); 
  }
 
  /**
   * Test of buildImagePath method, of class HelpClass.
   */
  @Test
  public void testBuildImagePath() {
    System.out.println("buildImagePath");
    String id = "123";
    String type = ".jpeg";
    String morphbankImagePath = "http://morphbank.se"; 
    String expResult = "http://morphbank.se?id=123.jpeg";
    String result = instance.buildImagePath(id, type, morphbankImagePath);
    assertEquals(expResult, result); 
  }

  /**
   * Test of buildEmptyString method, of class HelpClass.
   */
  @Test
  public void testBuildEmptyString() {
    System.out.println("buildEmptyString"); 
    String expResult = " ";
    String result = instance.buildEmptyString();
    assertEquals(expResult, result); 
  }

  /**
   * Test of replaceChars method, of class HelpClass.
   */
  @Test
  public void testReplaceChars() {
    System.out.println("replaceChars");
    String value = "cat[567]"; 
    String expResult = "cat 567";
    String result = instance.replaceChars(value);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTodaysDateInString method, of class HelpClass.
   */
  @Test
  public void testGetTodaysDateInString() {
    System.out.println("getTodaysDateInString"); 
    String expResult = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String result = instance.getTodaysDateInString();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDayList method, of class HelpClass.
   */
  @Test
  public void testGetDayList() {
    System.out.println("getDayList");
    int month = 3;  
    List result = instance.getDayList(month);
    assertEquals(31, result.size()); 
  }

  /**
   * Test of getNumberOfDaysInMonth method, of class HelpClass.
   */
  @Test
  public void testGetNumberOfDaysInMonthJan() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 1; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthFeb() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 2; 
    int expResult = 29;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthMar() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 3; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthApr() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 4; 
    int expResult = 30;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthMay() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 5; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthJun() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 6; 
    int expResult = 30;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthJul() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth =7; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthAug() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 8; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthSep() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 9; 
    int expResult = 30;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthOct() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 10; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthNov() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 11; 
    int expResult = 30;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonthDec() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 12; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetNumberOfDaysInMonth() {
    System.out.println("getNumberOfDaysInMonth");
    int numberOfMonth = 0; 
    int expResult = 31;
    int result = instance.getNumberOfDaysInMonth(numberOfMonth);
    assertEquals(expResult, result); 
  }

  /**
   * Test of resetValue method, of class HelpClass.
   */
  @Test
  public void testResetValueEmpty() {
    System.out.println("resetValue");
    String value = ""; 
    String expResult = "*";
    String result = instance.resetValue(value);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testResetValueNull() {
    System.out.println("resetValue");
    String value = null; 
    String expResult = "*";
    String result = instance.resetValue(value);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testResetValue() {
    System.out.println("resetValue");
    String value = "cat[123]"; 
    String expResult = "cat 123";
    String result = instance.resetValue(value);
    assertEquals(expResult, result); 
  }

  /**
   * Test of updateView method, of class HelpClass.
   */
  @Test
  public void testUpdateView_String() {
    System.out.println("updateView");
    String viewId = "test";  
    instance.updateView(viewId); 
    verify(faces, times(1)).ajax(); 
  } 
}
