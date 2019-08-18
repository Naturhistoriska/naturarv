/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;  
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.lazy.datamodel.ImageLazyDataModel;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Named("galleria")
@SessionScoped
@Slf4j
public class GalleriaBean implements Serializable {

  private final int PARTS_COUNT = 17;
  private final int VIEW_COUNT = 6;
  
  private final static String ALL = "all";
  private final static String LABEL = "Label";
  private final static String DORSAL = "Dorsal";
  private final static String VENTRAL = "Ventral";
  private final static String LATERAL = "Lateral";
  private final static String FRONTAL = "Frontal";
  private final static String CAUDAL = "Caudal";
  
  private final static String ABDOMEN = "Abdomen";
  private final static String FACE = "Face";
  private final static String GENITALIA = "Genitalia";
  private final static String WINGS = "Wings";
  private final static String HEAD = "Head";
  private final static String LEGS = "Legs";
  private final static String LOBE = "Lobe"; 
  private final static String PRONOTUM = "Pronotum";
  private final static String VARI = "Vari";
  private final static String MESONOTUM = "Mesonotum";
  private final static String POSTERIOR = "Posterior";
  private final static String PALPS = "Palps";
  private final static String TARSI = "Tarsi";
  private final static String LABRUM = "Labrum"; 
  private final static String NOTUM = "Notum";
  private final static String MOUTH = "Mouth";
  private final static String CHELICERAE = "Chelicerae";
  
  private final static String MALE = "/male/";
  private final static String FEMALE = "female";
  
  private final static String LARVA = "larva";
  private final static String ADULT = "adult";
 
  private boolean isDefaultView = true;
  
  private Map<String, String> filterMap;
  private final List<String> filters; 
  private String searchText;
  private StringBuilder searchTextSb;
 
  private boolean isAllViews = false;
  private boolean isAllParts = false;
  private boolean isAllSexes = false;
  private boolean isAllStages = false; 
 

  private List<String> viewList;
  private List<String> partsList;
  private List<String> sexList;
  private List<String> stageList;

  private StringJoiner sj;

  private String mbid;

  @Inject
  private SolrService solr;

  @Inject
  private InitialProperties properties;
  @Inject
  private SearchBean search;

  ImageLazyDataModel dataModel;

  public GalleriaBean() {
    viewList = new ArrayList<>();
    partsList = new ArrayList<>();
    sexList = new ArrayList<>();
    stageList = new ArrayList<>();
    filterMap = new HashMap();
    filters = new ArrayList<>();
  }

  @PostConstruct
  public void init() {
    log.info("init");
    filterMap = search.getQueries();  
    this.searchText = search.getSearchText(); 
    
    searchTextSb = new StringBuilder();
    searchTextSb.append(searchText);
    
    dataModel = new ImageLazyDataModel(solr, filterMap, filters, searchTextSb);
  }

  public LazyDataModel<ImageModel> getModel() {
    return dataModel;
  }

  public void selectViews() {
    log.info("selectViews: {}", viewList);

    boolean hasAll = viewList.contains(ALL) ? true : viewList.size() == VIEW_COUNT; 
 
    if (isAllViews) {
      isAllViews = false;
      if(viewList.contains(ALL)) {
        viewList.remove(ALL);  
      } else {
        viewList.clear(); 
      }   
    } else {
      if (hasAll) {
        isAllViews = true;
        viewList.clear();
        viewList.add(ALL);
        viewList.add(LABEL);
        viewList.add(DORSAL);
        viewList.add(VENTRAL);
        viewList.add(LATERAL);
        viewList.add(FRONTAL);
        viewList.add(CAUDAL);  
      }  
    } 
    viewOptionChanged();
  }
  
  public void selectParts() {
    log.info("selectParts: {}", partsList);
    
    boolean hasAll = partsList.contains(ALL) ? true : partsList.size() == PARTS_COUNT; 
    
    if (isAllParts) {
      isAllParts = false;
      if(partsList.contains(ALL)) {
        partsList.remove(ALL);  
      } else {
        partsList.clear(); 
      }   
    } else {
      if (hasAll) {
        isAllParts = true;
        partsList.clear();
        partsList.add(ALL);
        partsList.add(ABDOMEN);
        partsList.add(FACE);
        partsList.add(GENITALIA);
        partsList.add(WINGS);
        partsList.add(HEAD);
        partsList.add(LEGS);
        partsList.add(LOBE);   
        partsList.add(PRONOTUM);
        partsList.add(VARI);
        partsList.add(MESONOTUM);
        partsList.add(GENITALIA);
        partsList.add(POSTERIOR);
        partsList.add(PALPS);
        partsList.add(TARSI);
        partsList.add(LABRUM); 
        partsList.add(NOTUM);
        partsList.add(MOUTH);
        partsList.add(CHELICERAE);
      }
    }
    viewOptionChanged(); 
  }

  public void selectSexes() {
    log.info("selectSexes;: {}", sexList);
    
    boolean hasAll = sexList.contains(ALL) ? true : sexList.size() == 2; 
 
    if (isAllSexes) {
      isAllSexes = false;
      if(sexList.contains(ALL)) {
        sexList.remove(ALL);  
      } else {
        sexList.clear(); 
      }   
    } else {
      if (hasAll) {
        isAllSexes = true;
        sexList.clear();
        sexList.add(ALL);
        sexList.add(MALE);
        sexList.add(FEMALE); 
      }  
    } 
    viewOptionChanged();
  }

  public void selectStages() {
    log.info("selectStages: {}", stageList);
    
    boolean hasAll = stageList.contains(ALL) ? true : stageList.size() == 2; 
 
    if (isAllStages) {
      isAllStages = false;
      if(stageList.contains(ALL)) {
        stageList.remove(ALL);  
      } else {
        stageList.clear(); 
      }   
    } else {
      if (hasAll) {
        isAllStages = true;
        stageList.clear();
        stageList.add(ALL);
        stageList.add(LARVA);
        stageList.add(ADULT); 
      }  
    } 
    viewOptionChanged();
  }  
    
    
  
  private void buildString(StringBuilder sb, List<String> selectedList) {
    if (!selectedList.isEmpty()) { 
      sb.append(" +(");
      sb.append(CommonText.getInstance().getImageViewKey());
      sb.append(" (*");
      sj = new StringJoiner("* *");

      selectedList.stream()
              .filter(pNotAll)
              .forEach(v -> {
                sj.add(v);
              });
      sb.append(sj.toString());
      sb.append("*)) ");
    }
  }

  private void viewOptionChanged() {
    log.info("changeViewEvent : {}", filters);

    isDefaultView = (viewList.isEmpty() && partsList.isEmpty() && sexList.isEmpty() && stageList.isEmpty());
    
    filters.clear();
    if(isDefaultView) {
      searchTextSb.replace(0, searchTextSb.toString().length(), searchText); 
    }
    if (!isDefaultView) { 
      StringBuilder sb = new StringBuilder();
   
      sb.append("+(");
      sb.append(searchText);
      sb.append(") ");
      
      buildString(sb, viewList);
      buildString(sb, partsList);
      buildString(sb, sexList);
      buildString(sb, stageList); 
      log.info("sb ? {}", searchTextSb.toString());
       
      searchTextSb.replace(0, searchTextSb.toString().length(), sb.toString()); 
      Stream.of(viewList, partsList, sexList, stageList)
              .flatMap(x -> x.stream())
              .filter(x -> !x.equals(ALL))
              .forEach(x -> {
                filters.add(x);
              });
    }
 
    log.info("all list: {} -- {}", filters, filterMap); 
  }
 
  public List<String> getViewList() {
    return viewList;
  }

  public void setViewList(List<String> viewList) {
    this.viewList = viewList;
  }

  public List<String> getPartsList() {
    return partsList;
  }

  public void setPartsList(List<String> partsList) {
    this.partsList = partsList;
  }

  public List<String> getSexList() {
    return sexList;
  }

  public void setSexList(List<String> sexList) {
    this.sexList = sexList;
  }

  public List<String> getStageList() {
    return stageList;
  }

  public void setStageList(List<String> stageList) {
    this.stageList = stageList;
  }

  public String getThumbPath(String imageId) {
    return properties.getMorphbankThumbPath() + "?id=" + imageId + "&imgType=thumb";
  }

  Predicate<String> pAll = v -> v.equals(ALL);
  Predicate<String> pNotAll = v -> !v.equals(ALL);

}
