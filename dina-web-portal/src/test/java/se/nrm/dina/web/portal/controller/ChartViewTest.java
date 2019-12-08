package se.nrm.dina.web.portal.controller;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.chart.BarChartModel;
import se.nrm.dina.web.portal.logic.ChartCreator;
import se.nrm.dina.web.portal.solr.SolrChartService;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class)   
public class ChartViewTest {
  
  private ChartView instance;
  
  @Mock 
  private SolrChartService solr;
  @Mock
  private ChartCreator chartCreator;
  
  public ChartViewTest() {
  }
   
  @Before
  public void setUp() {
    instance = new ChartView();
  }
  
  @After
  public void tearDown() { 
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new ChartView();
    assertNotNull(instance); 
  }

  /**
   * Test of init method, of class ChartView.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
  }

  /**
   * Test of getTotalMonthChart method, of class ChartView.
   */
//  @Test
  public void testGetTotalMonthChart() {
    System.out.println("getTotalMonthChart"); 
    BarChartModel expResult = null;
    BarChartModel result = instance.getTotalMonthChart();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTotalTenYearsChart method, of class ChartView.
   */
//  @Test
  public void testGetTotalTenYearsChart() {
    System.out.println("getTotalTenYearsChart");
    ChartView instance = new ChartView();
    BarChartModel expResult = null;
    BarChartModel result = instance.getTotalTenYearsChart();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCollectionMonthChart method, of class ChartView.
   */
//  @Test
  public void testGetCollectionMonthChart() {
    System.out.println("getCollectionMonthChart");
    String collectionCode = "";
    ChartView instance = new ChartView();
    BarChartModel expResult = null;
    BarChartModel result = instance.getCollectionMonthChart(collectionCode);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCollectionYearChart method, of class ChartView.
   */
//  @Test
  public void testGetCollectionYearChart() {
    System.out.println("getCollectionYearChart");
    String collectionCode = "";
    ChartView instance = new ChartView();
    BarChartModel expResult = null;
    BarChartModel result = instance.getCollectionYearChart(collectionCode);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeLanguage method, of class ChartView.
   */
//  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    boolean isSwedish = false;
    ChartView instance = new ChartView();
    instance.changeLanguage(isSwedish);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
