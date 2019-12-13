package se.nrm.dina.web.portal.controller;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import se.nrm.dina.web.portal.ContextMocker;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class)  
public class NavigatorTest {
  
  private Navigator instance;
  private FacesContext context;  
  
  private StringBuffer sb;
  
  @Mock
  private StyleBean style;
  
  @Mock
  private ExternalContext externalContext; 
  @Mock
  private HttpServletRequest request;
  
  public NavigatorTest() {
  }
 
  
  @Before
  public void setUp() {
    context = ContextMocker.mockFacesContext(); 
   
    sb = new StringBuffer("https://naturarv.nrm.se"); 
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    when(externalContext.getRequestContextPath()).thenReturn("https://naturarv.nrm.se");   
    when(context.getExternalContext()).thenReturn(externalContext);
    instance = new Navigator(style);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new Navigator();
    assertNotNull(instance);
    instance.gotoAbout();
  }

  /**
   * Test of gotoHome method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoHome1() throws IOException {
    System.out.println("gotoHome"); 
    try {
      instance.gotoHome(); 
      verify(style, times(1)).setTabStyle("home");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }  
  }
  
  @Test
  public void testGotoHome2() throws IOException {
    System.out.println("gotoHome"); 
    try { 
      sb.append("/faces/pages/results.xhtml");
      when(request.getRequestURL()).thenReturn(sb);
      when(externalContext.getRequest()).thenReturn(request); 
      when(context.getExternalContext()).thenReturn(externalContext);
    
      instance.gotoHome();  
      verify(style, times(1)).setTabStyle("home");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }  
  }
  
  @Test
  public void testGotoHome3() throws IOException {
    System.out.println("gotoHome"); 
    try {
      instance.gotoResults();
      instance.gotoHome(); 
      verify(style, times(2)).setTabStyle("home");
      verify(externalContext, times(2)).redirect(any(String.class));  
    } finally {
      context.release();
    }  
  }
  
  
  @Test
  public void testGotoHomeCatchException() throws IOException {
    System.out.println("gotoHome"); 
    
    PowerMockito.doThrow(new IOException()).when(externalContext ).redirect(any(String.class)); 
    try { 
      instance.gotoHome();
      verify(style, times(1)).setTabStyle("home");
    } finally {
      context.release();
    }  
  }

  /**
   * Test of gotoResults method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoResults() throws IOException {
    System.out.println("gotoResults"); 
    
    try {
      instance.gotoResults(); 
      verify(style, times(1)).setTabStyle("home");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoNoResults method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoNoResults() throws IOException {
    System.out.println("gotoNoResults");
    try {
      instance.gotoNoResults();
      verify(style, times(1)).setTabStyle("home");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoCollections method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoCollections() throws IOException {
    System.out.println("gotoCollections");
    try {
      instance.gotoCollections();
      verify(style, times(1)).setTabStyle("collections");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoPartners method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoPartners() throws IOException {
    System.out.println("gotoPartners");
    try {
      instance.gotoPartners();
      verify(style, times(1)).setTabStyle("partners");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoFAQ method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoFAQ() throws IOException {
    System.out.println("gotoFAQ");
    try {
      instance.gotoFAQ();
      verify(style, times(1)).setTabStyle("faq");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoAbout method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoAbout() throws IOException {
    System.out.println("gotoAbout");
    try {
      instance.gotoAbout();
      verify(style, times(1)).setTabStyle("about");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoContactPage method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoContactPage() throws IOException {
    System.out.println("gotoContactPage");
    try {
      instance.gotoContactPage();
      verify(style, times(1)).setTabStyle("contact");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of gotoErrorReportPage method, of class Navigator.
   * @throws java.io.IOException
   */
  @Test
  public void testGotoErrorReportPage() throws IOException {
    System.out.println("gotoErrorReportPage");
    try {
      instance.gotoErrorReportPage();
      verify(style, times(1)).setTabStyle("home");
      verify(externalContext, times(1)).redirect(any(String.class));  
    } finally {
      context.release();
    }   
  }

  /**
   * Test of isShowSearchPanel method, of class Navigator.
   */
  @Test
  public void testIsShowSearchPanelCollectionsPage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/collections.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = false;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsShowSearchPanelAboutPage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/about.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = false;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }
   
  @Test
  public void testIsShowSearchPanelContactPage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/contact.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = false;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsShowSearchPanelFaqPage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/faq.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = false;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsShowSearchPanelPartnersPage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/partners.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = false;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsShowSearchPanelHomePage() {
    System.out.println("isShowSearchPanel");
     
    sb.append("/faces/pages/home.xhtml");
    when(request.getRequestURL()).thenReturn(sb);
    when(externalContext.getRequest()).thenReturn(request);  
    
    when(context.getExternalContext()).thenReturn(externalContext);
    boolean expResult = true;
    boolean result = instance.isShowSearchPanel();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isResultView method, of class Navigator.
   */
  @Test
  public void testIsResultView() {
    System.out.println("isResultView"); 
    boolean expResult = false;
    boolean result = instance.isResultView();
    assertEquals(expResult, result); 
  }
  
}
