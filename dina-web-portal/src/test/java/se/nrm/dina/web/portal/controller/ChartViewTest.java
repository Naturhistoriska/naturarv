package se.nrm.dina.web.portal.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType; 
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.logic.ChartCreator;
import se.nrm.dina.web.portal.solr.SolrChartService;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ChartView.class, HelpClass.class})
@PowerMockIgnore({"javax.management.*",
  "org.apache.http.conn.ssl.*",
  "com.amazonaws.http.conn.ssl.*",
  "javax.net.ssl.*"})
public class ChartViewTest {

  private ChartView instance;

  private FacesContext context;
  private HttpSession session;
  private BarChartModel model;

  @Mock
  private SolrChartService solr;
  @Mock
  private ChartCreator chartCreator;

  public ChartViewTest() {
  }

  @Before
  public void setUp() {
    session = mock(HttpSession.class);
    context = ContextMocker.mockFacesContext();
    ExternalContext ext = mock(ExternalContext.class);
    when(ext.getSession(true)).thenReturn(session);
    when(context.getExternalContext()).thenReturn(ext);
    model = mock(BarChartModel.class);

    instance = new ChartView(solr, chartCreator);
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
  @Test
  public void testGetTotalMonthChartFromSession() {
    System.out.println("getTotalMonthChart");

    when(session.getAttribute(any(String.class))).thenReturn(model);
    BarChartModel result = instance.getTotalMonthChart();
    assertNotNull(result);
    verify(session, times(2)).getAttribute(any(String.class));
    verify(session, times(0)).setAttribute(any(String.class), eq(model));
    verifyZeroInteractions(chartCreator);
    verifyZeroInteractions(solr);
  }

  @Test
  public void testGetTotalMonthChart() throws Exception {
    System.out.println("getTotalMonthChart");

    when(session.getAttribute(any(String.class))).thenReturn(null);
    Map<String, Integer> map = new HashMap<>();
    when(solr.getLastYearRegistedData(any(String.class), eq(null))).thenReturn(map);

    PowerMockito.whenNew(BarChartModel.class).withNoArguments().thenReturn(model);
    when(chartCreator.createMonthChart(eq(map), any(LocalDateTime.class), any(Boolean.class))).thenReturn(model);

    instance.init();
    BarChartModel result = instance.getTotalMonthChart();
    assertNotNull(result);

    verify(session, times(1)).getAttribute(any(String.class));
    verify(session, times(1)).setAttribute(any(String.class), eq(model));
    verify(solr, times(1)).getLastYearRegistedData(any(String.class), eq(null));
    verify(chartCreator, times(1)).createMonthChart(eq(map), any(LocalDateTime.class), any(Boolean.class));
  }

  /**
   * Test of getTotalTenYearsChart method, of class ChartView.
   */
  @Test
  public void testGetTotalTenYearsChartFromSession() {
    System.out.println("getTotalTenYearsChart");
    when(session.getAttribute(any(String.class))).thenReturn(model);
    BarChartModel result = instance.getTotalTenYearsChart();
    assertNotNull(result);
    verify(session, times(2)).getAttribute(any(String.class));
    verify(session, times(0)).setAttribute(any(String.class), eq(model));
    verifyZeroInteractions(chartCreator);
    verifyZeroInteractions(solr);
  }

  /**
   * Test of getTotalTenYearsChart method, of class ChartView.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetTotalTenYearsChart() throws Exception {
    System.out.println("getTotalTenYearsChart");
    when(session.getAttribute(any(String.class))).thenReturn(null);

    instance.init();
    Map<String, Integer> map = new HashMap<>();
    when(solr.getLastTenYearsRegistedData(eq(instance.getLastTenYear()), eq(instance.getNextYear()), eq(null))).thenReturn(map);

    PowerMockito.whenNew(BarChartModel.class).withNoArguments().thenReturn(model);
    BarChartModel result = instance.getTotalTenYearsChart();
    assertNotNull(result);
    verify(session, times(1)).getAttribute(any(String.class));
    verify(session, times(1)).setAttribute(any(String.class), eq(model));
    verify(solr, times(1)).getLastTenYearsRegistedData(eq(instance.getLastTenYear()), eq(instance.getNextYear()), eq(null));
    verify(chartCreator, times(1)).createYearChart(model, map, true);
  }

  /**
   * Test of getCollectionMonthChart method, of class ChartView.
   */
  @Test
  public void testGetCollectionMonthChartFromSession() {
    System.out.println("getCollectionMonthChart");
    String collectionCode = "12345";
    when(session.getAttribute(any(String.class))).thenReturn(model);
    BarChartModel result = instance.getCollectionMonthChart(collectionCode);
    assertNotNull(result);
    verify(session, times(2)).getAttribute(any(String.class));
    verify(session, times(0)).setAttribute(any(String.class), eq(model));
    verifyZeroInteractions(chartCreator);
    verifyZeroInteractions(solr);
  }

  @Test
  public void testGetCollectionMonthChart() throws Exception {
    System.out.println("getCollectionMonthChart");
    String collectionCode = "12345";
    when(session.getAttribute(any(String.class))).thenReturn(null);

    instance.init();

    Map<String, Integer> map = new HashMap<>();
    when(solr.getLastYearRegistedData(any(String.class), eq(collectionCode))).thenReturn(map);
    when(chartCreator.createMonthChart(eq(map), any(LocalDateTime.class), any(Boolean.class))).thenReturn(model);

    BarChartModel result = instance.getCollectionMonthChart(collectionCode);
    assertNotNull(result);
    verify(session, times(1)).getAttribute(any(String.class));
    verify(session, times(1)).setAttribute(any(String.class), eq(model));

  }

  /**
   * Test of getCollectionYearChart method, of class ChartView.
   */
  @Test
  public void testGetCollectionYearChartFromSession() {
    System.out.println("getCollectionYearChart");
    String collectionCode = "12345";

    when(session.getAttribute(any(String.class))).thenReturn(model);
    BarChartModel result = instance.getCollectionYearChart(collectionCode);
    assertNotNull(result);
    verify(session, times(2)).getAttribute(any(String.class));
    verify(session, times(0)).setAttribute(any(String.class), eq(model));
    verifyZeroInteractions(chartCreator);
    verifyZeroInteractions(solr);
  }
  
  @Test
  public void testGetCollectionYearChart() throws Exception {
    System.out.println("getCollectionYearChart");
    String collectionCode = "12345";

    when(session.getAttribute(any(String.class))).thenReturn(null);
    
    instance.init();
    Map<String, Integer> map = new HashMap<>();
    when(solr.getLastTenYearsRegistedData(eq(instance.getLastTenYear()), eq(instance.getNextYear()), eq(collectionCode))).thenReturn(map);

    PowerMockito.whenNew(BarChartModel.class).withNoArguments().thenReturn(model);
    BarChartModel result = instance.getCollectionYearChart(collectionCode);
    assertNotNull(result);
    verify(session, times(1)).getAttribute(any(String.class));
    verify(session, times(1)).setAttribute(any(String.class), eq(model));
    verify(solr, times(1)).getLastTenYearsRegistedData(eq(instance.getLastTenYear()), eq(instance.getNextYear()), eq(collectionCode));
    verify(chartCreator, times(1)).createYearChart(model, map, true); 
  }

  /**
   * Test of changeLanguage method, of class ChartView.
   * @throws java.lang.Exception
   */
  @Test
  public void testChangeLanguage() throws Exception {
    System.out.println("changeLanguage");

    String monthChart = CommonText.getInstance().getMonthChartData();
    String yearChart = CommonText.getInstance().getYearChartData();
    when(session.getAttribute(monthChart)).thenReturn(model);
    when(session.getAttribute(yearChart)).thenReturn(model);

    Map<Object, Number> map = new HashMap<>();
    map.put("Jan 2019", 3);
    map.put("Feb 2019", 3);
    boolean isSwedish = false;
    ChartSeries series = mock(ChartSeries.class);
    when(series.getData()).thenReturn(map);
    
    PowerMockito.whenNew(ChartSeries.class).withNoArguments().thenReturn(series);
    
    
    List<ChartSeries> list = new ArrayList();
    list.add(series);
    
    when(model.getSeries()).thenReturn(list);
    
    Axis xAxis = mock(Axis.class);  
    Axis yAxis = mock(Axis.class);
    when(model.getAxis(AxisType.X)).thenReturn(xAxis);
    when(model.getAxis(AxisType.Y)).thenReturn(yAxis);
     
    instance.init();
    instance.changeLanguage(isSwedish);

    verify(session, times(2)).getAttribute(monthChart);
    verify(session, times(2)).getAttribute(yearChart);
    verify(session, times(1)).setAttribute(eq(monthChart), any(BarChartModel.class));
    verify(session, times(1)).setAttribute(eq(yearChart), any(BarChartModel.class));
  }
  
  @Test
  public void testChangeLanguageWithCollections() throws Exception {
    System.out.println("changeLanguage");

    String monthChart = CommonText.getInstance().getMonthChartData();
    String yearChart = CommonText.getInstance().getYearChartData();
    when(session.getAttribute(monthChart)).thenReturn(null);
    when(session.getAttribute(yearChart)).thenReturn(null);

    Map<Object, Number> map = new HashMap<>();
    map.put("Jan 2019", 3);
    map.put("Feb 2019", 3);
    boolean isSwedish = false;
    ChartSeries series = mock(ChartSeries.class);
    when(series.getData()).thenReturn(map);
    
    PowerMockito.whenNew(ChartSeries.class).withNoArguments().thenReturn(series);
    
    
    List<ChartSeries> list = new ArrayList();
    list.add(series);
    
    when(model.getSeries()).thenReturn(list);
    
    Axis xAxis = mock(Axis.class);  
    Axis yAxis = mock(Axis.class);
    when(model.getAxis(AxisType.X)).thenReturn(xAxis);
    when(model.getAxis(AxisType.Y)).thenReturn(yAxis);
     
    instance.init();
    List<String> collectionList = new ArrayList();
    collectionList.add("1234");
    collectionList.add("6789");
    collectionList.stream()
            .forEach(c -> {
              instance.getCollectionMonthChart(c); 
              when(session.getAttribute(c)).thenReturn(model);
              when(session.getAttribute(c + "_year")).thenReturn(model);
            }); 
    instance.changeLanguage(isSwedish);

    collectionList.stream()
            .forEach(c -> {
              verify(session, times(3)).getAttribute(c);
              verify(session, times(2)).getAttribute(c + "_year");
            });  
  }

}
