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
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext; 
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Named("searchBean")
@SessionScoped
@Slf4j
public class SearchBean implements Serializable {

  private String freeText;
  private String defaultText;

  private final FacesContext facesContext;
  private final HttpSession session;
  private boolean isSwedish;
//  private boolean hasResult;
  private int totalResult;
  private String numDisplay;
  private String sortby; 
  private String resultView;

  private List<SolrData> resultList;
  private List<SolrData> selectedRecords;
  private boolean isSimpleSearch; 
  private boolean filterMap;
  private boolean filterDNA;
  private boolean filterType;
  private boolean filterSweden;
  private boolean filterImage;
  
  private List<String> filters;
  
  private static final String WILD_SEARCH_TEXT = "*:*";
  private static final String LIST_VIEW_PATH = "/pages/listView.xhtml";
  private static final String DETAIL_VIEW_PATH = "/pages/detailView.xhtml";
  private static final String SELECTED_VIEW_PATH = "/pages/selectedView.xhtml";
  
  private String viewPath;

  private Map<String, String> queries;

  @Inject
  private SolrService solr;

  @Inject
  private Navigator navigator;

  @Inject
  private PagingNavigation paging;

  public SearchBean() {
    facesContext = FacesContext.getCurrentInstance();
    session = (HttpSession) facesContext.getExternalContext().getSession(false);
//    hasResult = false;
    isSimpleSearch = true;
    resultList = new ArrayList();
    selectedRecords = new ArrayList<>();
    filters = new ArrayList<>();
    viewPath = LIST_VIEW_PATH; 
    resultView = "list";
  }

  /**
   * Initialize data after class constructed
   */
  @PostConstruct
  public void init() {
    log.info("init");

    isSwedish = ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv");
    defaultText = CommonText.getInstance().getSearchDefaultText(isSwedish);
    queries = new HashMap();
    sortby = "score";                                                       // reset sorting to sore -- default sort
    numDisplay = "10"; 
  }

  public void openAdvanceSearch() {
    log.info("openAdvanceSearch");
    isSimpleSearch = false;
    updateView("searchForm:searchPanel");
  }

  public void closeAdvanceSearch() {
    log.info("closeAdvanceSearch");

    isSimpleSearch = true;
    updateView("searchForm:searchPanel");
  }

  public void simpleSearch() {
    log.info("simpleSearch: {}", freeText);
    if (!freeText.equals(defaultText) && freeText.length() > 0) {
      solr.simpleSearch(freeText);
    }
  }

  public void all() {
    log.info("all - search all records");

    SolrResult result = solr.searchAll("*:*", 0, Integer.parseInt(numDisplay));
    setResults(result);
  }

  public void searchCollection(CollectionData collection) {
    log.info("searchCollection: {}", collection);
  }

  public void searchInstitution() {
    log.info("searchInstitution");

//        savedFilterMap.put("institution", institution.getInstCode()); 
//        session.setAttribute(SAVED_QUERY, savedFilterMap);  
//        queries.put("Institution", institution.getInstitution());
//        
//        String searchText = getSavedSearchText();
//        solrResult = search(searchText, savedFilterMap, 0);
// 
//        statistic.searchFilteredData(searchText, savedFilterMap, map, type, image);
//        setResultView();  
  }

  public void filterhWithMap() {
    log.info("filterhWithMap");
    
    if(!filters.contains("map:*")) {
      filters.add("map:*");
    }
    SolrResult result = solr.searchWithFilter(buildSearchText(), filters, 0, Integer.parseInt(numDisplay));
    setResults(result);
  }
  

  private String buildSearchText() {
    if (freeText != null && freeText.length() > 0) {
      return "text:" + freeText;
    }
    return WILD_SEARCH_TEXT;
  }

  public void filterhWithDNA() {
    log.info("filterhWithDNA");

    if (!filters.contains("dna:*")) {
      filters.add("dna:*");
    }
    SolrResult result = solr.searchWithFilter(buildSearchText(), filters, 0, Integer.parseInt(numDisplay));
    setResults(result);
  }

  public void filterhWithType() {
    log.info("filterhWithType");
    
    if (!filters.contains("typeStatus:*")) {
      filters.add("typeStatus:*");
    }
    SolrResult result = solr.searchWithFilter(buildSearchText(), filters, 0, Integer.parseInt(numDisplay));
    setResults(result);
  }

  public void filterhWithImage() {
    log.info("filterhWithImage");
    
    if (!filters.contains("image:*")) {
      filters.add("image:*");
    }
    SolrResult result = solr.searchWithFilter(buildSearchText(), filters, 0, Integer.parseInt(numDisplay));
    setResults(result);
  }

  public void filterhWithSweden() {
    log.info("filterhWithSweden");
    
    if (!filters.contains("inSweden:*")) {
      filters.add("inSweden:*");
    }
    SolrResult result = solr.searchWithFilter(buildSearchText(), filters, 0, Integer.parseInt(numDisplay));
    setResults(result);
  }

  public void removeFilter(String key, String value) {
    log.info("removeFilter: {} -- {}", key, value);
  }

  public void removeAllQueries() {
    log.info("removeAllQueries");
  }
  
  public void removeAllSelectedRecords() {
    log.info("removeAllSelectedRecords");
    selectedRecords.clear();
  }
   

  public void selectOne(SolrData data) {
    log.info("selectOne: {}", data.isSelected());

    if (data.isSelected()) { 
      selectedRecords.add(data);
      log.info("data added: {}", selectedRecords.size());
////      checkedRecordsCatlogNumList.add(record.getCatalogNum());
    } else {
      selectedRecords.remove(data);
      log.info("data removeed: {}", selectedRecords.size());
////      checkedRecordsCatlogNumList.remove(record.getCatalogNum());
    }
    log.info("selected records isEmpty : {}", selectedRecords.isEmpty());
////    updateView("resultsForm:resultsForm");
//
////    if (selectedRecords.isEmpty()) {
////      int totalRecords = (int) solrResult.getNumFound();
////      if (totalRecords > 10000) {
////        overMaxium = true;
////      }
////      selectall = false;
////    } else {
////      selectall = true;
////      overMaxium = selectedRecords.size() > 10000;
//    }
  }

  public void simpleView() {
    log.info("simpleview");
    viewPath = LIST_VIEW_PATH; 
    resultView = "list";
  }

  public void detailView() {
    log.info("detialview"); 
    viewPath = DETAIL_VIEW_PATH; 
    resultView = "detail";
  }

  public void selectedView() { 
    log.info("selectedview"); 
    viewPath = SELECTED_VIEW_PATH; 
    resultView = "selected";
  }

  public void showOneDetail(SolrData data) {
    log.info("showOneDetail : {}", data.getCatalogNumber());
    
    viewPath = SELECTED_VIEW_PATH; 
    resultView = "selected";

//        List<SolrRecord> list = new ArrayList<>();
//        list.add(record);
//        displayOneDetail = true;
//        
//        record.setSelected(true);
//        selectall = true;
//        
//        selectedRecords.add(record);
//        session.setAttribute(SAVED_SELECTED_RECORDS, selectedRecords); 
//        selectedRecords = new ArrayList<>();
//        selectedRecords.add(record);
//     
//        resultview = 2;
  }

  public void displayImages(SolrData data) {
    log.info("displayImages : {}", data);

    String[] mbids = data.getMorphbankId();
//    if (mbids != null) {
//      record.setImageExist(true);
//      record.setDisplayImage(true);
//    }
  }

  public void changeNumDisplay() {
    log.info("changeNumDisplay : {}", numDisplay);
    SolrResult result = solr.searchAll("*:*", 0, Integer.parseInt(numDisplay));
    resultList = result.getSolrData();
//    setResults(result);
    setPaging(0, 1); 
//    updateView("resultsForm:result");
  }

  public void sortResult() {
    log.info("sortResult: {}", sortby);

  }

  public void export() {
    log.info("export");
//    exportDataSet = new ArrayList<>();
  }

  private void updateView(String viewId) {
    PrimeFaces.current().ajax().update(viewId);
  }

  public String getDefaultText() {

    isSwedish = ((String) session.getAttribute("locale")).equals("sv");
    return CommonText.getInstance().getSearchDefaultText(isSwedish);
  }
  
  private void setResults(SolrResult result) {
//    hasResult = result != null;
    if (result != null) {
      resultList = result.getSolrData();
      totalResult = result.getTotalFound();
      setPaging(0, 1); 
      navigator.gotoResults();
    } else {
      navigator.gotoNoResults();
    }   
  }
  

  private void setPaging(int start, int currentPage) {
    paging.setStart(start);
    paging.setEnd(Integer.parseInt(numDisplay));
    paging.setTotalFound(totalResult);
    paging.setCurrentPage(currentPage);
  }

  public void setDefaultText(String defaultText) {
    this.defaultText = defaultText;
  }

  public String getFreeText() {
    return freeText;
  }

  public void setFreeText(String freeText) {
    this.freeText = freeText;
  }

//  public boolean isHasResult() {
//    return hasResult;
//  }
//
//  public void setHasResult(boolean hasResult) {
//    this.hasResult = hasResult;
//  }

  public List<SolrData> getResultList() {
    log.info("getResultList: {}", resultList.isEmpty()); 
    return resultList;
  }

  public void setResultList(List<SolrData> resultList) {
    this.resultList = resultList;
  }

  public int getTotalResult() {
    return totalResult;
  }

  public void setTotalResult(int totalResult) {
    this.totalResult = totalResult;
  }

  public List<SolrData> getSelectedRecords() {
    return selectedRecords;
  }

  public void setSelectedRecords(List<SolrData> selectedRecords) {
    this.selectedRecords = selectedRecords;
  }

  public boolean isIsSimpleSearch() {
    return isSimpleSearch;
  }

  public Map<String, String> getQueries() {
    return queries;
  }

  public void setQueries(Map<String, String> queries) {
    this.queries = queries;
  }

  public String getNumDisplay() {
    return numDisplay;
  }

  public void setNumDisplay(String numDisplay) {
    this.numDisplay = numDisplay;
  }

  public String getSortby() {
    return sortby;
  }

  public void setSortby(String sortby) {
    this.sortby = sortby;
  }
 
  public String getViewPath() {
    return viewPath;
  }

  public String getResultView() {
    return resultView;
  }
  
  

}
