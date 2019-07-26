/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.MonthElement;

/**
 *
 * @author idali
 */
@Named("chart")
@ApplicationScoped
@Slf4j
public class ChartView implements Serializable {

  private BarChartModel totalMonthChart;
  private BarChartModel totalTenYearsChart;
 
  private Map<String, Map<String, Integer>> collectionMonthsDataMap;
  private Map<String, Map<String, Integer>> collectionYearsDataMap;
  private List<String> collectionNameList;
  
  private boolean isSwedish;

  private final HttpSession session;
  private final LocalDateTime startDate;
  private final int yearOfStartDate;
  private final int yearOfToday;
  private final int monthOfToday;  
  private final int lastTenYear;
  private final int nextYear;
 
  private final CommonText common;
  
  @Inject
  private SolrService solr;
  
  public ChartView() {
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    isSwedish = true;
    
    YearMonth yearMonth = YearMonth.from(LocalDate.now());
    yearOfToday = yearMonth.getYear();
    monthOfToday = yearMonth.getMonth().getValue();
    startDate = yearMonth.minusMonths(11).atDay(1).atStartOfDay();    
    yearOfStartDate = startDate.getYear();
    nextYear = yearOfToday + 1;
    lastTenYear = yearOfStartDate - 10;
    
    common = CommonText.getInstance();
    collectionNameList = new ArrayList<>();
  }
  
  @PostConstruct
  public void init() {
    log.info("init");
    isSwedish = ((String) session.getAttribute(common.getLocale())).equals("sv");
    createTotalMonthChart();
    createSpecimenModelForLastTenYears();
    
    initCollectionMonthChartData();
    initCollectionYearChartData();
  } 
  
  private void initCollectionMonthChartData() {
    log.info("initCollectionMonthChartData");
    collectionMonthsDataMap = solr.getCollectionsMonthChartData(startDate);
    if(collectionMonthsDataMap != null && collectionMonthsDataMap.size() > 0) {
      session.setAttribute(common.getCollectionsMonthChart(), solr);
    } 
  }
  
  private void initCollectionYearChartData() { 
    log.info("initCollectionYearChartData");
    collectionYearsDataMap = solr.getCollectionsYearData(lastTenYear, nextYear);
    if(collectionYearsDataMap != null && collectionYearsDataMap.size() > 0) {
      session.setAttribute(common.getCollectionsYearChart(), solr);
    } 
  }
  
  private void createCollectionMonthChart(BarChartModel collectionMonthChart, String collectionName) {
    log.info("createCollectionMonthChart");
    if(session.getAttribute(common.getCollectionsMonthChart()) == null) {
      initCollectionMonthChartData();
    }
    Map<String, Integer> monthData = collectionMonthsDataMap.get(collectionName);
     
    if(monthData == null) {
      monthData = new HashMap<>();
    }
    createMonthChart(collectionMonthChart, monthData); 
    session.setAttribute(collectionName, collectionMonthChart); 
    if(!collectionNameList.contains(collectionName)) { 
      collectionNameList.add(collectionName);
    }
  }
  
  private void createCollectionYearChart(BarChartModel collectionYearChart, String collectionName) {
    log.info("createCollectionrYeaChart");
    if(session.getAttribute(common.getCollectionsYearChart()) == null) {
      initCollectionYearChartData();
    }
    Map<String, Integer> yearData = collectionYearsDataMap.get(collectionName);
     
    if(yearData == null) {
      yearData = new HashMap<>();
    }
    createYearChart(collectionYearChart, yearData); 
    session.setAttribute(collectionName + "_year", collectionYearChart); 
  }
  
  private void createSpecimenModelForLastTenYears() {
    log.info("createSpecimenModelForLastTenYears");
     
    Map<String, Integer> yearMap = solr.getLastTenYearsRegistedData(lastTenYear, nextYear);
    session.setAttribute(common.getYearChartData(), yearMap);
    totalTenYearsChart = new BarChartModel(); 
    createYearChart(totalTenYearsChart, yearMap);
  }
  
  private void createYearChart(BarChartModel model, Map<String, Integer> dataMap) {
    ChartSeries series = new ChartSeries();
    dataMap.entrySet().stream().forEach(e -> {
      series.set(e.getKey(), e.getValue());
    });
  
    model.addSeries(series); 
    model.setTitle(common.getYearChartTitle(isSwedish));
    addOptions(model, common.getYearChartXAxisLabel(isSwedish));  
  }

  private void createTotalMonthChart() {
    log.info("createTotalMonthChart");
    
    Map<String, Integer> result = solr.getLastYearRegistedData(startDate);    
    session.setAttribute(common.getMonthChartData(), result);
    totalMonthChart = new BarChartModel();    
    createMonthChart(totalMonthChart, result); 
  }
  
  private void createMonthChart(BarChartModel model, Map<String, Integer> dataMap) {
    ChartSeries series = new ChartSeries();    
    IntStream.range(0, 12)
            .forEach(x -> {              
              Month month = startDate.plusMonths(x).getMonth();          
              series.set(buildSeriesLabel(month),
                      dataMap.get(month.name()) == null ? 0 : dataMap.get(month.name()) );
            });
    
    model.addSeries(series);    
    model.setTitle(common.getMonthChartTitle(isSwedish));    
    addOptions(model, common.getMonthChartXAxisLabel(isSwedish));    
  }
  
  private void addOptions(BarChartModel model, String label) {    
    model.setShowPointLabels(true);
    model.setShowDatatip(false);
    
    Axis xAxis = model.getAxis(AxisType.X);
    xAxis.setTickAngle(-50);
    xAxis.setLabel(label);
    
    Axis yAxis = model.getAxis(AxisType.Y);
    yAxis.setLabel(common.getChartYAxisLabel(isSwedish));    
  }
  
  private String buildSeriesLabel(Month month) {
    int year = month.getValue() <= monthOfToday ? yearOfToday : yearOfStartDate;
    StringBuilder sb = new StringBuilder();
    sb.append(MonthElement.valueOfShortName(month.name(), isSwedish));
    sb.append(" ");
    sb.append(year);
    
    return sb.toString();
  } 
  
  public BarChartModel getTotalMonthChart() {
    return totalMonthChart;
  }

  public BarChartModel getTotalTenYearsChart() {
    return totalTenYearsChart;
  }
 
  public BarChartModel getCollectionMonthChart(String collectionName) {
    log.info("getCollectionMonthChart: {}", collectionName);
    if(session.getAttribute(collectionName) == null) {
      BarChartModel collectionMonthChart = new BarChartModel(); 
      createCollectionMonthChart(collectionMonthChart, collectionName);
    } 
    return (BarChartModel) session.getAttribute(collectionName);
  }
  
  public BarChartModel getCollectionYearChart(String collectionName) {
    log.info("getCollectionYearChart: {}", collectionName);
    if(session.getAttribute(collectionName  + "_year") == null) {
      BarChartModel collectionYearChart = new BarChartModel(); 
      createCollectionYearChart(collectionYearChart, collectionName);
    } 
    return (BarChartModel) session.getAttribute(collectionName + "_year");
  }
  
  
  public void changeLanguage() {
    isSwedish = ((String) session.getAttribute(common.getLocale())).equals("sv");
    
    totalMonthChart = new BarChartModel();
    totalTenYearsChart = new BarChartModel();
    createMonthChart(totalMonthChart, (Map)session.getAttribute(common.getMonthChartData()));    
    createYearChart(totalTenYearsChart, (Map)session.getAttribute(common.getYearChartData()));    
    
    collectionNameList.stream()
            .forEach(c -> {
              if(session.getAttribute(c) != null) {
                BarChartModel collectionMonthChart = (BarChartModel) session.getAttribute(c);
                collectionMonthChart.clear(); 
                createCollectionMonthChart(collectionMonthChart, c); 
                
                BarChartModel collectionYearChart = (BarChartModel) session.getAttribute(c + "_year");
                collectionYearChart.clear(); 
                createCollectionYearChart(collectionYearChart, c);
              }
            });
  } 
}
