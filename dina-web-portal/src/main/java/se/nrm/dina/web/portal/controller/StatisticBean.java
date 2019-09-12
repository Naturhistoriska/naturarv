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
  private StatisticData filteredData;
  private final HttpSession session;  
  
  private boolean isSwedish; 
  
  @Inject
  private SolrService solr;
  
  public StatisticBean() {
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
  }
  
  @PostConstruct
  public void init() {
    log.info("StatisticData.init");
    isSwedish =  ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv");
            
    initStatisticData();
  }

  public void initStatisticData() {
    data = solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null);  
  }
  
  public void resetData(String text, Map<String, String> queries) {
    filteredData = solr.getStatisticData(text, queries);   
  }
  
  public List<CollectionData> getFilteredCollections() {
    return filteredData == null ? getCollections() : filteredData.getCollections(); 
  }
  
  public List<CollectionData> getCollections() { 
    if (data == null) { 
      initStatisticData();
    }
    return data.getCollections();
  }
   
  public int getTotalRecords() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotal(); 
  }
  
  public int getFilteredTotalDnas() {
    return filteredData == null ? getTotalDnas() : filteredData.getTotalDnas(); 
  }
  
  public int getTotalDnas() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotalDnas();
  }
  
  public int getFilteredTotalImages() {
    return filteredData == null ? getTotalImages() : filteredData.getTotalImages();
  }
 
  public int getTotalImages() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotalImages();
  }
  
  public int getFilteredTotalMaps() {
    return filteredData == null ? getTotalMaps() : filteredData.getTotalMaps();
  }

  public int getTotalMaps() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotalMaps();
  }
  
  public int getFilteredTotalInSweden() {
    return filteredData == null ? getTotalInSweden() : filteredData.getTotalInSweden();
  }
  
  public int getTotalInSweden() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotalInSweden();
  }
  
  public int getFilteredTotalType() {
    return filteredData == null ? getTotalType() : filteredData.getTotalType();
  }
  
  public int getTotalType() {
    if (data == null) {
      initStatisticData();
    }
    return data.getTotalType();
  }
  
  public Map<String, Integer> getFilteredInstitutions() {
    return filteredData == null ? getInstitutions() : buildInstitutionsMap(filteredData);
  }

  public Map<String, Integer> getInstitutions() {
    if (data == null) {
      initStatisticData();
    }
    return buildInstitutionsMap(data); 
  }
  
  private Map<String, Integer> buildInstitutionsMap(StatisticData statisticData) {
    Map<Boolean, List<CollectionData>> partitions = statisticData.getCollections().stream()
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