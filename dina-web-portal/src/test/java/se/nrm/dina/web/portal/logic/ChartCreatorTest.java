package se.nrm.dina.web.portal.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author idali
 */
public class ChartCreatorTest {
  
  private ChartCreator instance;
  private Map<String, Integer> dataMap;
  
  public ChartCreatorTest() {
  }
 
  @Before
  public void setUp() { 
    dataMap = new HashMap();
    dataMap.put(Month.JANUARY.name(), 20);
    dataMap.put(Month.FEBRUARY.name(), 20);
    dataMap.put(Month.MARCH.name(), 20);
//    dataMap.put(Month.APRIL.name(), 20);
    dataMap.put(Month.MAY.name(), 20);
    dataMap.put(Month.JUNE.name(), 20);
    dataMap.put(Month.JULY.name(), 20);
    dataMap.put(Month.AUGUST.name(), 20);
    dataMap.put(Month.SEPTEMBER.name(), 20);
    dataMap.put(Month.OCTOBER.name(), 20);
    dataMap.put(Month.NOVEMBER.name(), 20);
    dataMap.put(Month.DECEMBER.name(), 20);
    
    instance = new ChartCreator();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of createMonthChart method, of class ChartCreator.
   */
  @Test
  public void testCreateMonthChart() {
    System.out.println("createMonthChart"); 
    LocalDateTime startDate = LocalDateTime.of(2019, Month.MARCH, 01, 0, 0);
    boolean isSwedish = false; 
     
    BarChartModel result = instance.createMonthChart(dataMap, startDate, isSwedish);
    assertNotNull(result); 
    assertEquals(result.getSeries().size(), 1);
  }

  /**
   * Test of createYearChart method, of class ChartCreator.
   */
  @Test
  public void testCreateYearChart() {
    System.out.println("createYearChart");
    BarChartModel model = new BarChartModel();
    boolean isSwedish = false; 
    instance.createYearChart(model, dataMap, isSwedish);  
  }
  
}
