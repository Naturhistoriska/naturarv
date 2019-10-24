/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.time.YearMonth;
import java.util.ArrayList; 
import java.util.List;
import java.util.Map; 
import javax.annotation.PostConstruct;  
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j; 
import org.primefaces.model.chart.BarChartModel;   
import se.nrm.dina.web.portal.logic.ChartCreator; 
import se.nrm.dina.web.portal.logic.utils.ChartHelper;
import se.nrm.dina.web.portal.solr.SolrChartService; 
import se.nrm.dina.web.portal.utils.CommonText;  
import se.nrm.dina.web.portal.utils.SearchHelper;

/**
 *
 * @author idali
 */
@Named("chart")
@SessionScoped
@Slf4j
public class ChartView implements Serializable {

  private BarChartModel totalMonthChart;
  private BarChartModel totalTenYearsChart;

  private Map<String, Integer> resultMap;  
  private final List<String> collectionCodeList;
 
  private HttpSession session;
  private LocalDateTime startDate; 
  private int yearOfToday; 
  private int lastTenYear;
  private int nextYear;
  
  private String searchDateRange;

  private final CommonText common;
  private final static String YEAR_SURFFIX = "_year";
    
  @Inject
  private SolrChartService solr;
  @Inject
  private ChartCreator chartCreator;

  public ChartView() { 
    common = CommonText.getInstance();
    collectionCodeList = new ArrayList<>();
  }

  @PostConstruct
  public void init() {
    log.info("init");
    initData();  
  }
  
  private void initData() { 
    YearMonth yearMonth = YearMonth.from(LocalDate.now());
    yearOfToday = yearMonth.getYear(); 
    startDate = yearMonth.minusMonths(11).atDay(1).atStartOfDay(); 
    nextYear = yearOfToday + 1;
    lastTenYear = yearOfToday - 10;  
    searchDateRange = SearchHelper.getInstance().buildSearchDateRange(startDate, null);
  }
  
  public BarChartModel getTotalMonthChart() {
    session = getSession();
    
    if(session.getAttribute(common.getMonthChartData()) != null) {
      return (BarChartModel) session.getAttribute(common.getMonthChartData());
    }  
    totalMonthChart = new BarChartModel(); 
    resultMap = solr.getLastYearRegistedData(searchDateRange, null); 
    if (resultMap != null) {
      chartCreator.createMonthChart(totalMonthChart, resultMap, startDate, isSwedish());
      getSession().setAttribute(common.getMonthChartData(), totalMonthChart);  
    } 
    return totalMonthChart;    
  }
  
  public BarChartModel getTotalTenYearsChart() {
    session = getSession();
    
    if(session.getAttribute(common.getYearChartData()) != null) {
      return (BarChartModel) session.getAttribute(common.getYearChartData());
    } 
    resultMap = solr.getLastTenYearsRegistedData(lastTenYear, nextYear, null);  
    totalTenYearsChart = new BarChartModel();
    chartCreator.createYearChart(totalTenYearsChart, resultMap, isSwedish()); 
    session.setAttribute(common.getYearChartData(), totalTenYearsChart);  
    return totalTenYearsChart;
  }

  /**
   * Create chart for register data from last ten years
   * @param collectionCode
   * @return 
   */
  public BarChartModel getCollectionMonthChart(String collectionCode) {
    log.info("getCollectionMonthChart: {}", collectionCode);
    
    if(!collectionCodeList.contains(collectionCode)) {
      collectionCodeList.add(collectionCode);
    }
    session = getSession();
    if (session.getAttribute(collectionCode) != null) {
      return (BarChartModel) session.getAttribute(collectionCode); 
    } 
    BarChartModel collectionMonthChart = new BarChartModel();  
    chartCreator.createMonthChart(collectionMonthChart, 
            solr.getLastYearRegistedData(searchDateRange, collectionCode), 
            startDate, isSwedish());
    session.setAttribute(collectionCode, collectionMonthChart);
    return collectionMonthChart;  
  }
  
  public BarChartModel getCollectionYearChart(String collectionCode) {
    log.info("getCollectionYearChart: {}", collectionCode);
    
    session = getSession();
    if (session.getAttribute(collectionCode + YEAR_SURFFIX) != null) {
      return (BarChartModel) session.getAttribute(collectionCode + YEAR_SURFFIX); 
    }
    BarChartModel collectionYearChart = new BarChartModel(); 
    chartCreator.createYearChart(collectionYearChart, 
            solr.getLastTenYearsRegistedData(lastTenYear, nextYear, collectionCode), isSwedish()); 
    session.setAttribute(collectionCode + YEAR_SURFFIX, collectionYearChart);
    return collectionYearChart;
  }
  
  public void changeLanguage(boolean isSwedish) {
    session = getSession();   
    
    if(session.getAttribute(common.getMonthChartData()) != null) {
      totalMonthChart = (BarChartModel) session.getAttribute(common.getMonthChartData());  
      session.setAttribute(common.getMonthChartData(), 
            ChartHelper.getInstance().changeMonthChartLanguage(totalMonthChart, isSwedish));
    }
    
    if(session.getAttribute(common.getYearChartData()) != null) {
      totalTenYearsChart = (BarChartModel) session.getAttribute(common.getYearChartData());  
      session.setAttribute(common.getYearChartData(), 
            ChartHelper.getInstance().changeYearChartLanguage(totalTenYearsChart, isSwedish));
    } 
    
    collectionCodeList.stream()
            .forEach(c -> {
              if(session.getAttribute(c) != null) { 
                session.setAttribute(c, ChartHelper.getInstance()
                        .changeMonthChartLanguage(
                                (BarChartModel)session.getAttribute(c), isSwedish));
              }
              String attString = c + YEAR_SURFFIX;
              if(session.getAttribute(attString) != null) {  
                session.setAttribute(attString, ChartHelper.getInstance()
                        .changeYearChartLanguage(
                                (BarChartModel)session.getAttribute(attString), isSwedish));
              }
            }); 
  }
  
  private HttpSession getSession() {
    if(session == null) {
      session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    return session;
  } 
  
  private boolean isSwedish() {
    return ((String)getSession().getAttribute(CommonText.getInstance().getLocale()))
            .equals(CommonText.getInstance().getSv());
  } 
}
