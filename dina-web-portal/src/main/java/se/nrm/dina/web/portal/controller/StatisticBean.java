package se.nrm.dina.web.portal.controller;

import java.io.Serializable;  
import java.util.List;
import java.util.Map;  
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Inject;
import javax.inject.Named; 
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.StatisticData; 
import se.nrm.dina.web.portal.solr.SolrStatisticService;
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
  
  @Inject
  private SolrStatisticService solr;
  
  public StatisticBean() {  
  }
  
  public StatisticBean(SolrStatisticService solr) { 
    this.solr = solr; 
  }
  
  @PostConstruct
  public void init() {
    log.info("StatisticData.init");
    data = solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null);  
  } 
   
  public void resetData(String text, Map<String, String> queries) {
    filteredData = solr.getStatisticData(text, queries);   
  }
  
  public void resetAllData() {
    if(data == null) {
      data = solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null); 
    }
    filteredData = data;
  }
  
  public List<CollectionData> getFilteredCollections() {
    return filteredData == null ? getCollections() : filteredData.getCollections(); 
  }
  
  public List<CollectionData> getCollections() { 
    if (data == null) { 
      resetAllData();
    }
    return data.getCollections();
  }
   
  public int getTotalRecords() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotal(); 
  }
  
  public int getFilteredTotalDnas() {
    return filteredData == null ? getTotalDnas() : filteredData.getTotalDnas(); 
  }
  
  public int getTotalDnas() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotalDnas();
  }
  
  public int getFilteredTotalImages() {
    return filteredData == null ? getTotalImages() : filteredData.getTotalImages();
  }
 
  public int getTotalImages() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotalImages();
  }
  
  public int getFilteredTotalMaps() {
    return filteredData == null ? getTotalMaps() : filteredData.getTotalMaps();
  }

  public int getTotalMaps() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotalMaps();
  }
  
  public int getFilteredTotalInSweden() {
    return filteredData == null ? getTotalInSweden() : filteredData.getTotalInSweden();
  }
  
  public int getTotalInSweden() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotalInSweden();
  }
  
  public int getFilteredTotalType() {
    return filteredData == null ? getTotalType() : filteredData.getTotalType();
  }
  
  public int getTotalType() {
    if (data == null) {
      resetAllData();
    }
    return data.getTotalType();
  }
} 