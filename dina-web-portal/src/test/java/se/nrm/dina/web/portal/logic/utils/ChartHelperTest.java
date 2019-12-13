/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author idali
 */
public class ChartHelperTest {
  
  public ChartHelperTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getInstance method, of class ChartHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance");
    ChartHelper expResult = null;
    ChartHelper result = ChartHelper.getInstance();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of buildSeriesLabel method, of class ChartHelper.
   */
  @Test
  public void testBuildSeriesLabel() {
    System.out.println("buildSeriesLabel");
    Month month = null;
    int yearOfStartDate = 0;
    boolean isSwedish = false;
    ChartHelper instance = new ChartHelper();
    String expResult = "";
    String result = instance.buildSeriesLabel(month, yearOfStartDate, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of addOptions method, of class ChartHelper.
   */
  @Test
  public void testAddOptions() {
    System.out.println("addOptions");
    BarChartModel model = null;
    boolean isMonth = false;
    boolean isSwedish = false;
    ChartHelper instance = new ChartHelper();
    instance.addOptions(model, isMonth, isSwedish);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeMonthChartLanguage method, of class ChartHelper.
   */
  @Test
  public void testChangeMonthChartLanguage() {
    System.out.println("changeMonthChartLanguage");
    BarChartModel chart = null;
    boolean isSwedish = false;
    ChartHelper instance = new ChartHelper();
    BarChartModel expResult = null;
    BarChartModel result = instance.changeMonthChartLanguage(chart, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeYearChartLanguage method, of class ChartHelper.
   */
  @Test
  public void testChangeYearChartLanguage() {
    System.out.println("changeYearChartLanguage");
    BarChartModel chart = null;
    boolean isSwedish = false;
    ChartHelper instance = new ChartHelper();
    BarChartModel expResult = null;
    BarChartModel result = instance.changeYearChartLanguage(chart, isSwedish);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
