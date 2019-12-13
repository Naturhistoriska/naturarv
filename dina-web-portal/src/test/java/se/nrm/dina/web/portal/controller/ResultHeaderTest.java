package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.PrimeFaces;
import se.nrm.dina.web.portal.PrimeFacesMocker;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class ResultHeaderTest {
  
  private ResultHeader instance;
  
  private PrimeFaces faces;  
  
  public ResultHeaderTest() {
  }
    
  @Before
  public void setUp() {  
    faces = PrimeFacesMocker.mockPrimeFaces(); 
    instance = new ResultHeader();
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of setSimpleView method, of class ResultHeader.
   */
  @Test
  public void testSetSimpleView() {
    System.out.println("setSimpleView"); 
    instance.setSimpleView(); 
    assertEquals(instance.getViewPath(), "/pages/listView.xhtml");
    assertEquals(instance.getResultView(), "list");
    verify(faces, times(1)).scrollTo(any(String.class));
  }

  /**
   * Test of setDetailView method, of class ResultHeader.
   */
  @Test
  public void testSetDetailView() {
    System.out.println("setDetailView"); 
    instance.setDetailView(); 
    assertEquals(instance.getViewPath(), "/pages/detailView.xhtml");
    assertEquals(instance.getResultView(), "detail");
    verifyZeroInteractions(faces); 
  }

  /**
   * Test of setSelectedView method, of class ResultHeader.
   */
  @Test
  public void testSetSelectedView() {
    System.out.println("setSelectedView"); 
    instance.setSelectedView(); 
    assertEquals("/pages/selectedView.xhtml", instance.getViewPath());
    assertEquals("selected", instance.getResultView());
    verifyZeroInteractions(faces); 
  }

  /**
   * Test of setMapView method, of class ResultHeader.
   */
  @Test
  public void testSetMapView() {
    System.out.println("setMapView");
    instance.setMapView();
    assertEquals("/pages/mapView.xhtml", instance.getViewPath());
    assertEquals("map", instance.getResultView());
    verifyZeroInteractions(faces); 
  }

  /**
   * Test of setImageView method, of class ResultHeader.
   */
  @Test
  public void testSetImageView() {
    System.out.println("setImageView"); 
    instance.setImageView(); 
    assertEquals("/pages/imageView.xhtml", instance.getViewPath());
    assertEquals("image", instance.getResultView());
    verifyZeroInteractions(faces); 
  }

  /**
   * Test of getViewPath method, of class ResultHeader.
   */
  @Test
  public void testGetViewPath() {
    System.out.println("getViewPath"); 
    String expResult = "/pages/listView.xhtml";
    String result = instance.getViewPath();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getResultView method, of class ResultHeader.
   */
  @Test
  public void testGetResultView() {
    System.out.println("getResultView"); 
    String expResult = "list";
    String result = instance.getResultView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isDisplayPaging method, of class ResultHeader.
   */
  @Test
  public void testIsDisplayPagingTrue1() {
    System.out.println("isDisplayPaging"); 
    boolean expResult = true;
    boolean result = instance.isDisplayPaging();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsDisplayPagingTrue2() {
    System.out.println("isDisplayPaging"); 
    boolean expResult = true;
    instance.setDetailView();
    boolean result = instance.isDisplayPaging();
    assertEquals(expResult, result); 
  }

  @Test
  public void testIsDisplayPagingFalse() {
    System.out.println("isDisplayPaging"); 
    boolean expResult = false;
    instance.setMapView();
    boolean result = instance.isDisplayPaging();
    assertEquals(expResult, result); 
  }
  
  /**
   * Test of isDisplaySelectedView method, of class ResultHeader.
   */
  @Test
  public void testIsDisplaySelectedViewFalse() {
    System.out.println("isDisplaySelectedView"); 
    boolean expResult = false;
    boolean result = instance.isDisplaySelectedView();
    assertEquals(expResult, result); 
  }
    
  @Test
  public void testIsDisplaySelectedViewTrue() {
    System.out.println("isDisplaySelectedView"); 
    boolean expResult = true;
    instance.setSelectedView();
    boolean result = instance.isDisplaySelectedView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isDisplayListView method, of class ResultHeader.
   */
  @Test
  public void testIsDisplayListViewTrue1() {
    System.out.println("isDisplayListView"); 
    boolean expResult = true;
    boolean result = instance.isDisplayListView();
    assertEquals(expResult, result); 
  } 
  
  @Test
  public void testIsDisplayListViewTrue2() {
    System.out.println("isDisplayListView"); 
    boolean expResult = true;
    
    instance.setDetailView();
    boolean result = instance.isDisplayListView();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsDisplayListViewFalse() {
    System.out.println("isDisplayListView"); 
    boolean expResult = false;
    
    instance.setMapView();
    boolean result = instance.isDisplayListView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isDisplayBackToListLink method, of class ResultHeader.
   */
  @Test
  public void testIsDisplayBackToListLinkFalse() {
    System.out.println("isDisplayBackToListLink"); 
    boolean expResult = false;
    boolean result = instance.isDisplayBackToListLink();
    assertEquals(expResult, result); 
  }

  @Test
  public void testIsDisplayBackToListLinkTrue1() {
    System.out.println("isDisplayBackToListLink"); 
    boolean expResult = true;
    instance.setMapView();
    boolean result = instance.isDisplayBackToListLink();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsDisplayBackToListLinkTrue2() {
    System.out.println("isDisplayBackToListLink"); 
    boolean expResult = true;
    instance.setImageView();
    boolean result = instance.isDisplayBackToListLink();
    assertEquals(expResult, result); 
  }
  /**
   * Test of isMapView method, of class ResultHeader.
   */
  @Test
  public void testIsMapViewFalse() {
    System.out.println("isMapView"); 
    boolean expResult = false;
    boolean result = instance.isMapView();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsMapViewTrue() {
    System.out.println("isMapView"); 
    boolean expResult = true;
    instance.setMapView();
    boolean result = instance.isMapView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isImageView method, of class ResultHeader.
   */
  @Test
  public void testIsImageViewFalse() {
    System.out.println("isImageView"); 
    boolean expResult = false;
    boolean result = instance.isImageView();
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testIsImageViewTrue() {
    System.out.println("isImageView"); 
    boolean expResult = true;
    instance.setImageView();
    boolean result = instance.isImageView();
    assertEquals(expResult, result); 
  }
  
}
