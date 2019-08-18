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
import se.nrm.dina.web.portal.solr.SolrHelper;
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

  private final FacesContext facesContext;
  private final HttpSession session;
  private boolean isSwedish;
  private int totalResult;
  private int numDisplay;
  private String sortby;

  private CollectionData selectedCollection;
  private String selectedInstitution;
  private final List<SolrData> selectedRecords;
  private boolean isSimpleSearch;

  private List<SolrData> resultList;
  private SolrResult result;
  private Map<String, String> queries;

  @Inject
  private SolrService solr;
  @Inject
  private Navigator navigator;
  @Inject
  private PagingNavigation paging;
  @Inject
  private ResultHeader resultHeader;
  @Inject
  private StatisticBean statistic;
  @Inject
  private GeoMap geo;
   
  public SearchBean() {
    facesContext = FacesContext.getCurrentInstance();
    session = (HttpSession) facesContext.getExternalContext().getSession(false);
    isSimpleSearch = true;
    resultList = new ArrayList();
    selectedRecords = new ArrayList<>();
    queries = new HashMap();
    selectedCollection = null;
  }

  /**
   * Initialize data after class constructed
   */
  @PostConstruct
  public void init() {
    log.info("init");

    isSwedish = ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv");
    clearData();
  }

  /**
   * Search all the data from solr without filters
   */
  public void all() {
    log.info("all - search all records");

    clearData();
    result = solr.searchAll(CommonText.getInstance().getWildSearchText(), 0, numDisplay);
    statistic.resetData(CommonText.getInstance().getWildSearchText(), queries);
    setResult();
  }

  /**
   * Search all the data has coordinates from solr
   */
  public void searchAllDataHasCoordinates() {
    log.info("searchAllDataHasCoordinates");
    filterSearch(CommonText.getInstance().getMapKey(), String.valueOf(true));
  }

  /**
   * Search all the data has DNA sequences from solr
   */
  public void searchAllDataHasDNASequences() {
    log.info("searchAllDataHasDNASequences");
    filterSearch(CommonText.getInstance().getDNAKey(), String.valueOf(true));
  }

  /**
   * Search all the data has images from solr
   */
  public void searchAllDataHasImages() {
    log.info("searchAllDataHasImages");
    filterSearch(CommonText.getInstance().getImageKey(), String.valueOf(true));
  }

  /**
   * Search all the data are type specimens from solr
   */
  public void searchAllDataAreTypeSpecimems() {
    log.info("searchAllDataAreTypeSpecimems");
    filterSearch(CommonText.getInstance().getTypeKey(), String.valueOf(true));
  }

  /**
   * Search all the data are collected in Sweden from solr
   */
  public void searchAllDataCollectedInSweden() {
    log.info("searchAllDataCollectedInSweden");
    filterSearch(CommonText.getInstance().getSwedenKey(), String.valueOf(true));
  }

  /**
   * Search data has coordinates with filters from solr
   */
  public void searchDataHasCoordinatesWithFilters() {
    log.info("searchDataHasCoordinatesWithFilters");

    if (!queries.containsKey(CommonText.getInstance().getMapKey())) {
      filterSearchWithQueries(CommonText.getInstance().getMapKey(), String.valueOf(true));
    }
  }

  /**
   * Search data has dna sequences with filters from solr
   */
  public void searchDataHasDNASequencesWithFilters() {
    log.info("searchDataHasDNASequencesWithFilters");

    if (!queries.containsKey(CommonText.getInstance().getDNAKey())) {
      filterSearchWithQueries(CommonText.getInstance().getDNAKey(), String.valueOf(true));
    }
  }

  /**
   * Search data are type specimens with filters from solr
   */
  public void searchDataAreTypeSpecimenaWithFilters() {
    log.info("searchDataAreTypeSpecimenaWithFilters");

    if (!queries.containsKey(CommonText.getInstance().getTypeKey())) {
      filterSearchWithQueries(CommonText.getInstance().getTypeKey(), String.valueOf(true));
    }
  }

  /**
   * Search data has images with filters from solr
   */
  public void searchDataHasImagesWithFilters() {
    log.info("searchDataHasImagesWithFilters");

    if (!queries.containsKey(CommonText.getInstance().getImageKey())) {
      filterSearchWithQueries(CommonText.getInstance().getImageKey(), String.valueOf(true));
    }
  }

  /**
   * Search data collected in Sweden with filters from solr
   */
  public void searchDataCollectedInSwedenWithFilters() {
    log.info("searchDataCollectedInSwedenWithFilters");

    if (!queries.containsKey(CommonText.getInstance().getSwedenKey())) {
      filterSearchWithQueries(CommonText.getInstance().getSwedenKey(), String.valueOf(true));
    }
  }

  /**
   * Search collection data filtered with collection code
   *
   * @param collection
   */
  public void searchCollectionWithoutFilter(CollectionData collection) {
    log.info("searchCollectionWithoutFilter: {}", collection.getCode());
    filterSearch(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
    this.selectedCollection = collection;
  }

  /**
   * Search collection data filtered with collection code and other applied
   * filters
   *
   * @param collection
   */
  public void searchCollectionWithFilter(CollectionData collection) {
    log.info("searchCollectionWithFilter: {}", collection.getCode());
    filterSearchWithQueries(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
    this.selectedCollection = collection;
  }

  /**
   * Search institution data filtered institution code and other applied filters
   *
   * @param key
   */
  public void searchInstitutionWithFilter(String key) {
    log.info("searchInstitutionWithFilter: {}", key);

    String institutionCode = key.equals(CommonText.getInstance().getNrmName(isSwedish))
            ? CommonText.getInstance().getNrmCode() : CommonText.getInstance().getGnmCode();
    filterSearchWithQueries(CommonText.getInstance().getIdKey(), institutionCode + "*");
    selectedInstitution = key;
  }

  /**
   * Search institution data filtered institution code
   *
   * @param key
   */
  public void searchInstitutionWithoutFilter(String key) {
    log.info("searchInstitution: {}", key);

    String institutionCode = key.equals(CommonText.getInstance().getNrmName(isSwedish))
            ? CommonText.getInstance().getNrmCode() : CommonText.getInstance().getGnmCode();
    filterSearch(CommonText.getInstance().getIdKey(), institutionCode + CommonText.getInstance().getWildCard());
    selectedInstitution = key;
  }

  /**
   * Free text search
   */
  public void simpleSearch() {
    log.info("simpleSearch: {}", freeText);

    boolean isResultView = navigator.isResultView();
    if (!isResultView) {
      queries.clear();
    }
    if (freeText != null && freeText.length() > 0) { 
      String searchText = getBuildSearchText();
      result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
      setResult();
      statistic.resetData(searchText, queries);
    }
  }

  /**
   * Search next page data
   */
  public void nextPage() {
    log.info("nextPage");
    pagingSearch(paging.getEnd());
    paging.setNextPage(numDisplay);
  }

  /**
   * Search previous page data
   */
  public void previousPage() {
    log.info("previousPage");
    pagingSearch(paging.getStart() - numDisplay - 1);
    paging.setPreviousPage(numDisplay);
  }

  /**
   * Search first page data
   */
  public void firstPage() {
    log.info("firstPage");
    pagingSearch(0);
    paging.setFirstPage(numDisplay);
  }

  /**
   * Search last page data
   */
  public void lastPage() {
    log.info("lastPage");

    int totalPages = paging.getTotalPages();
    int start = numDisplay * (totalPages - 1);
    pagingSearch(start);
    paging.setLastPage(numDisplay);
  }

  /**
   * Search selected page data
   *
   * @param pageNumber
   */
  public void changePage(int pageNumber) {
    log.info("changePage: {}", pageNumber);
    int start = (pageNumber - 1) * numDisplay;
    pagingSearch((pageNumber - 1) * numDisplay);
    paging.setPaging(start, numDisplay, pageNumber);
  }

  /**
   * Search data when display number per page changed
   */
  public void changeNumDisplay() {
    log.info("changeNumDisplay : {}", numDisplay);
    pagingSearch(0);
    paging.calculateTotalPages(result.getTotalFound(), numDisplay);
  }

  /**
   * Display data details
   *
   * @param data
   */
  public void showOneDetail(SolrData data) {
    log.info("showOneDetail: {}", data);

    data.setSelected(true);
    selectedRecords.add(data);
    resultHeader.setSelectedView();
  }

  /**
   * Remove one selected data from selected data list
   *
   * @param data
   */
  public void removeone(SolrData data) {
    log.info("removeone: {}", data);
    data.setSelected(false);
    selectedRecords.remove(data);

    if (selectedRecords.isEmpty()) {
      resultHeader.backToListView();
    }
  }

  /**
   * Remove all the selected data
   */
  public void removeAllSelectedRecords() {
    log.info("removeAllSelectedRecords");
    selectedRecords.clear();
  }

  /**
   *
   * @param key
   * @param value
   */
  public void removeFilter(String key, String value) {
    log.info("removeFilter: {} -- {}", key, value);
    queries.remove(key);

    if (key.equals(CommonText.getInstance().getCollectionCodeKey())) {
      selectedCollection = null;
    }
    if (key.equals(CommonText.getInstance().getIdKey())) {
      selectedInstitution = null;
    } 
    String searchText = getBuildSearchText();
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
    setResult();
    statistic.resetData(searchText, queries);
  }

  /**
   * Remove all filters
   */
  public void removeAllQueries() {
    log.info("removeAllQueries");
    queries.clear();
    selectedCollection = null;
    selectedInstitution = null;
    String searchText = getBuildSearchText();
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
    setResult();
    statistic.resetData(searchText, queries);
  }
  
  
  /**
   * Display map view
   */
  public void showMapView() {
    log.info("showMapView");

    geo.gotoMapView(statistic.getTotalMaps(), getBuildSearchText(), queries, solr);
    resultHeader.mapView();
  }
  
  
//
//  /**
//   * Display images view
//   */
//  public void showImages() {
//    log.info("showImages");
//
//    resultHeader.imageView();
//  }
  
  public String getSearchText() {
    return getBuildSearchText();
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

  public void displayImages(SolrData data) {
    log.info("displayImages : {}", data);

    String mbids = data.getMorphbankId();
//    if (mbids != null) {
//      record.setImageExist(true);
//      record.setDisplayImage(true);
//    }
  }

  public void sortResult() {
    log.info("sortResult: {}", sortby);

  }

  public void export() {
    log.info("export");
//    exportDataSet = new ArrayList<>();
  }

  public String getDefaultText() {

    isSwedish = ((String) session.getAttribute("locale")).equals("sv");
    return CommonText.getInstance().getSearchDefaultText(isSwedish);
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

//  public void setTotalResult(int totalResult) {
//    this.totalResult = totalResult;
//  }
  
  
  
  
  
  public boolean isIsSimpleSearch() {
    return isSimpleSearch;
  }

  public Map<String, String> getQueries() {
    return queries;
  }

  public void setQueries(Map<String, String> queries) {
    this.queries = queries;
  }

  public int getNumDisplay() {
    return numDisplay;
  }

  public void setNumDisplay(int numDisplay) {
    this.numDisplay = numDisplay;
  }

  public String getSortby() {
    return sortby;
  }

  public void setSortby(String sortby) {
    this.sortby = sortby;
  }

  public List<SolrData> getSelectedRecords() {
    return selectedRecords;
  }

  public String getSelectedCollectionName() {
    return selectedCollection == null ? "" : selectedCollection.getShortName();
  }

  public String getSelectedInstitution() {
    return selectedInstitution;
  }

  private void pagingSearch(int start) { 
    result = solr.searchWithFilter(getBuildSearchText(), queries, start, numDisplay);
    resultList = result.getSolrData();
  }

  private void filterSearch(String key, String value) {
    log.info("filterSearch");

    selectedCollection = null;
    selectedInstitution = null;
    queries.clear();
    freeText = null;
    filterSearchWithQueries(key, value);
  }

  private void filterSearchWithQueries(String key, String value) {
    String searchText = getBuildSearchText(); 
    queries.put(key, value);
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
    setResult();
    statistic.resetData(searchText, queries);
  }

  private void setResult() {
    if (result == null) {
      navigator.gotoNoResults();
    } else {
      resultList = result.getSolrData();
      totalResult = result.getTotalFound();
      paging.calculateTotalPages(totalResult, numDisplay);

      if (!navigator.isResultView()) {
        navigator.gotoResults();
      }
    }
  }
    
  public String getBuildSearchText() {
    return SolrHelper.getInstance().buildSearchText(CommonText.getInstance().getTextKey(), freeText);
  }

  /**
   * Update ui
   *
   * @param viewId
   */
  private void updateView(String viewId) {
    PrimeFaces.current().ajax().update(viewId);
  }

  /**
   * Clear data to initial state
   */
  private void clearData() {
    freeText = null;
    isSimpleSearch = true;
    numDisplay = 10;
    queries.clear();
    selectedCollection = null;
    selectedInstitution = null;
    sortby = CommonText.getInstance().getSortByScore();
    result = null;
    resultList.clear();
    selectedRecords.clear();
    totalResult = 0; 
  } 
}
