package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.List; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;  
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
     
  private List<CollectionData> collectionData;
  private int activeIndex;

  @Inject
  private StatisticBean data;

  public CollectionsView() {
    activeIndex = -1;
  }
  
  public CollectionsView(StatisticBean data) {
    this.data = data;
    activeIndex = -1;
  }

  @PostConstruct
  public void init() {
    log.info("init");    
  }
  
  public void onTabChange(TabChangeEvent event) {
    log.info("onTabChange: {}", event.getTab());  
  }
   
  public void onTabClose(TabCloseEvent event) {
    log.info("onTabChange: {}", event); 
    activeIndex = -1;
  }
     
  public List<CollectionData> getCollections() {  
    log.info("collections");
    if(collectionData == null) {
      collectionData = data.getCollections();
    } 
    return collectionData;
  } 
  
 
  public int getActiveIndex() {
    return activeIndex;
  }

  public void setActiveIndex(int activeIndex) {
    this.activeIndex = activeIndex;
  } 
}
