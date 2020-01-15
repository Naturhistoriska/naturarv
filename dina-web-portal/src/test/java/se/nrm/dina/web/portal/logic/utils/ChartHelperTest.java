package se.nrm.dina.web.portal.logic.utils;

import java.time.Month;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author idali
 */
public class ChartHelperTest {
  
  private ChartHelper instance;
  
  public ChartHelperTest() {
  }
  
  @Before
  public void setUp() {
    instance = ChartHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class ChartHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    ChartHelper result = ChartHelper.getInstance();
    assertNotNull(result); 
  }

  /**
   * Test of buildSeriesLabel method, of class ChartHelper.
   */
  @Test
  public void testBuildSeriesLabel() {
    System.out.println("buildSeriesLabel");
    Month month = Month.MARCH;
    int yearOfStartDate = 2020;
    boolean isSwedish = false; 
    String result = instance.buildSeriesLabel(month, yearOfStartDate, isSwedish);
    assertNotNull(result); 
  }

  /**
   * Test of addOptions method, of class ChartHelper.
   */
  @Test
  public void testAddOptionsYear() {
    System.out.println("addOptions");
    BarChartModel model = new BarChartModel();
    boolean isMonth = false;
    boolean isSwedish = false; 
    instance.addOptions(model, isMonth, isSwedish); 
    assertEquals("Year", model.getAxis(AxisType.X).getLabel());
  }
  
  @Test
  public void testAddOptionsMonth() {
    System.out.println("addOptions");
    BarChartModel model = new BarChartModel();
    boolean isMonth = true;
    boolean isSwedish = false; 
    instance.addOptions(model, isMonth, isSwedish); 
    assertEquals("Month", model.getAxis(AxisType.X).getLabel());
  }

  /**
   * Test of changeMonthChartLanguage method, of class ChartHelper.
   */
  @Test
  public void testChangeMonthChartLanguage() {
    System.out.println("changeMonthChartLanguage");
    BarChartModel chart = new BarChartModel(); 
    ChartSeries series = new ChartSeries(); 
    chart.addSeries(series);
    boolean isSwedish = false;  
    BarChartModel result = instance.changeMonthChartLanguage(chart, isSwedish);
    assertNotNull(result); 
  }

  /**
   * Test of changeYearChartLanguage method, of class ChartHelper.
   */
  @Test
  public void testChangeYearChartLanguage() {
    System.out.println("changeYearChartLanguage");
    BarChartModel chart = new BarChartModel();
    boolean isSwedish = false;  
    BarChartModel result = instance.changeYearChartLanguage(chart, isSwedish);
    assertNotNull(result); 
  }
  
}
