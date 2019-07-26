/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map; 
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("statistic")
@Slf4j
public class StatisticBean implements Serializable {
  
  private StatisticData data; 
  private final HttpSession session;  
  
  private boolean isSwedish;
  private final String statistic;
  
  @Inject
  private SolrService solr;
  
  public StatisticBean() {
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
    statistic = CommonText.getInstance().getStatistic();
  }
  
  @PostConstruct
  public void init() {
    log.info("StatisticData.init");
    isSwedish =  ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv");
            
    initStatisticData();
  }

  public void initStatisticData() {
    data = solr.getStatisticData();
    session.setAttribute(statistic, data); 
  }

  public List<CollectionData> getCollections() { 
    if (session.getAttribute(statistic) == null) { 
      initStatisticData();
    }
    return data.getCollections();
  }
  
  public int getTotalRecords() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotal(); 
  }
  
  public int getTotalDnas() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotalDnas();
  }
 
  public int getTotalImages() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotalImages();
  }

  public int getTotalMaps() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotalMaps();
  }
  
  public int getTotalInSweden() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotalInSweden();
  }
  
  public int getTotalType() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }
    return data.getTotalType();
  }

  public Map<String, Integer> getInstitutions() {
    if (session.getAttribute(statistic) == null) {
      initStatisticData();
    }

    Map<Boolean, List<CollectionData>> partitions = data.getCollections().stream()
            .collect(Collectors.partitioningBy(c -> Integer.parseInt(c.getCode()) != 4));
 
    Map<String, Integer> map = new LinkedHashMap<>();

    map.put(CommonText.getInstance().getNrmName(isSwedish), partitions.get(true).stream()
                              .mapToInt(CollectionData::getTotal)
                              .sum());
    map.put(CommonText.getInstance().getGnmName(isSwedish), partitions.get(false).stream()
            .mapToInt(CollectionData::getTotal)
            .sum()); 
    return map;
  }
} 