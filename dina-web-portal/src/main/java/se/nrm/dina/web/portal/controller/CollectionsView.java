/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.List; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import java.util.stream.Collectors; 
import lombok.extern.slf4j.Slf4j; 
import org.primefaces.event.TabChangeEvent; 
import org.primefaces.event.TabCloseEvent;
import se.nrm.dina.web.portal.model.CollectionData; 

/**
 *
 * @author idali
 */
@Named("collections")
@ApplicationScoped
@Slf4j
public class CollectionsView implements Serializable {
    
  private List<String> collections;
  private int activeIndex;

  @Inject
  private StatisticBean data;

  public CollectionsView() {
    activeIndex = -1;
  }

  @PostConstruct
  public void init() {
    log.info("init");  
    
    buildCollections();
  }
  
  public void onTabChange(TabChangeEvent event) {
    log.info("onTabChange: {}", event.getTab().getTitle()); 
  }
   
  public void onTabClose(TabCloseEvent event) {
    log.info("onTabChange: {}", event.getTab().getTitle()); 
    activeIndex = -1;
  }
    
  private void buildCollections() {
    log.info("collection: {} -- {}", data, data.getCollections());
    collections = data.getCollections()
            .stream()
            .map(CollectionData::getName)
            .collect(Collectors.toList()); 
  }
   
  
  public List<String> getCollections() {  
    log.info("collections");
    if(collections == null) {
      buildCollections();
    } 
    return collections;
  } 

  public int getActiveIndex() {
    return activeIndex;
  }

  public void setActiveIndex(int activeIndex) {
    this.activeIndex = activeIndex;
  }
  
  
  
}
