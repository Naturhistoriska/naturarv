/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;  
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.DateHelper;
import se.nrm.dina.web.portal.utils.HelpClass;
import se.nrm.dina.web.portal.utils.MonthElement;
import se.nrm.dina.web.portal.utils.SearchHelper;

/**
 *
 * @author idali
 */
@Named("searchBean")
@SessionScoped
@Slf4j
public class SearchBean implements Serializable {
  
  private String freeText; 
  private HttpSession session;
  private boolean isSwedish;
  private int totalResult;
  private int numDisplay;
  private String sortby;
    
  private CollectionData selectedCollection;
  private String selectedInstitution;
  private List<SolrData> selectedRecords;
  private List<SolrData> selectedOneRecord;
  private List<SolrData> exportDataSet;
  private List<QueryData> queryDataList;
  
  private String queryText;
  private StringBuilder queryTextSb;
  
  private boolean isSelectedOne;
  private boolean isSimpleSearch;
  private boolean selectedAll;
  
  private List<SolrData> resultList;
  private SolrResult result;
  private Map<String, String> queries;
  private Map<String, String> filters;
  
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
  private GeoHashMap geo;
  @Inject
  private GalleriaBean galleria;
  @Inject
  private InitialProperties properties;
 
  
  public SearchBean() {  
  }

  /**
   * Initialize data after class constructed
   */
  @PostConstruct
  public void init() {
    log.info("init");
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    isSwedish = ((String) session.getAttribute(CommonText.getInstance().getLocale())).equals("sv");
    clearData();
  }
  
  /**
   * Search all the data from solr without filters
   */
  public void all() {
    log.info("all - search all records");
    
    clearData();
    result = solr.searchAll(0, numDisplay);
    statistic.resetAllData();
    gotoSimpleView();
    setResult();
  }

  /**
   * Free text search
   */
  public void simpleSearch() {
    log.info("simpleSearch: {}", freeText);

    String searchText = SearchHelper.getInstance().buildFullSearchText(freeText); 
    statistic.resetData(searchText, queries);
    boolean isListSearch = true;
    if(navigator.isResultView()) {
      if(resultHeader.isImageView()) {
        galleria.setImageView(statistic.getFilteredTotalImages(), searchText, queries);
        isListSearch = false;
      } else if (resultHeader.isMapView()) {
        geo.setMapView(searchText, queries);
        isListSearch = false;
      }
    }
     
    if (isListSearch) {
      clearSelectedData();
      result = solr.searchWithFilter(searchText, queries, 0, numDisplay,
              CommonText.getInstance().getCreatedDate(), false); 
      gotoSimpleView();
      setResult();
    }
  }

  /**
   * Search link from home page: filter with map, dna, type, image, and
   * collected in Sweden
   *
   * @param filterKey
   */
  public void searchWithSingleFilter(String filterKey) {
    filterSearch(filterKey, String.valueOf(true));
  }
   
  /**
   * Search collection data filtered with collection code
   *
   * @param collection
   */
  public void searchCollectionWithSingleFilter(CollectionData collection) {
    log.info("searchCollectionWithoutFilter: {}", collection.getCode());
    filterSearch(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
    this.selectedCollection = collection;
  }

  /**
   * Search institution data filtered institution code
   *
   * @param key
   */
  public void searchInstitutionWithSingleFilter(String key) {
    log.info("searchInstitution: {}", key);
     
    filterSearch(CommonText.getInstance().getIdKey(), 
            CommonText.getInstance().getInstitutionCode(key, isSwedish));
    selectedInstitution = key;
  }
   
  private void filterSearch(String key, String value) {
    log.info("filterSearch");
    
    clearData();
    String searchText = SearchHelper.getInstance().buildFullSearchText(freeText);
    queries.put(key, value);
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay, 
            CommonText.getInstance().getCreatedDate(), false);
    statistic.resetData(searchText, queries);
    
    updateFilters();
    gotoSimpleView();
    setResult();
  }
  
  /**
   * Search with filter
   * 
   * @param filterKey - String
   */
  public void searchDataWithFilter(String filterKey) {
    
//    result = null;
//    resultList.clear();
//    totalResult = 0;
//    clearSelectedData(); 
//    String searchText = isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
//            : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    queries.put(filterKey, String.valueOf(true));
    searchData();
    
//    statistic.resetData(searchText, queries);
//    if (resultHeader.isMapView()) {
//      geo.setMapView(searchText, queries); 
//    } else {
//      filterSearchWithQueries(searchText);
//    }
    filters = new HashMap();
    filters = queries.entrySet().stream()
            .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
  
   /**
   * Search collection data filtered with collection code and other applied
   * filters
   *
   * @param collection
   */
  public void searchCollectionWithFilter(CollectionData collection) {
    log.info("searchCollectionWithFilter: {}", collection.getCode());
    
//    result = null;
//    resultList.clear();
//    totalResult = 0;
//    clearSelectedData();
    
//    String searchText = SearchHelper.getInstance().buildFullSearchText(freeText);
    queries.put(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
    searchData();
//    statistic.resetData(searchText, queries);
//    if (resultHeader.isMapView()) {
//      geo.setMapView(searchText, queries);
//    } else {
//      filterSearchWithQueries(searchText);
//    }
    
    filters = new HashMap();
    filters = queries.entrySet().stream()
            .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    this.selectedCollection = collection;
  }
  
   /**
   * Search institution data filtered institution code and other applied filters
   *
   * @param key
   */
  public void searchInstitutionWithFilter(String key) {
    log.info("searchInstitutionWithFilter: {}", key);
//    
//    result = null;
//    resultList.clear();
//    totalResult = 0;
//    clearSelectedData();
//     
//    String searchText = SearchHelper.getInstance().buildFullSearchText(freeText);
    queries.put(CommonText.getInstance().getIdKey(),
            CommonText.getInstance().getInstitutionCode(key, isSwedish));
    
    searchData();
//    statistic.resetData(searchText, queries);
//    if (resultHeader.isMapView()) {
//      geo.setMapView(searchText, queries);
//    } else {
//      filterSearchWithQueries(searchText);
//    }
    selectedInstitution = key;
    filters = new HashMap();
    filters = queries.entrySet().stream()
            .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
  
  private void searchData() {
    result = null;
    resultList.clear();
    totalResult = 0;
    clearSelectedData();
    
    String searchText = isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
            : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    statistic.resetData(searchText, queries);
    if (resultHeader.isMapView()) {
      geo.setMapView(searchText, queries);
    } else {
      result = solr.searchWithFilter(searchText, queries, 0, numDisplay, 
              CommonText.getInstance().getCreatedDate(), false);
      setResult();
    }
  }
  
//    private void filterSearchWithQueries(String searchText) {
//    result = solr.searchWithFilter(searchText, queries, 0, numDisplay, 
//            CommonText.getInstance().getCreatedDate(), false);
//    setResult();
//  }
 
  
 
   /**
   *
   * @param key
   * @param value
   */
  public void removeFilter(String key, String value) {
    log.info("removeFilter: {} -- {}", key, value);
    
    if (filters.size() == 1) {
      removeAllQueries();
    } else {
      filters.remove(key);
      if (key.equals(CommonText.getInstance().getCollectionCodeKey())) {
        selectedCollection = null;
      }
      if (key.equals(CommonText.getInstance().getIdKey())) {
        selectedInstitution = null;
      }
      
      key = key.equals(CommonText.getInstance().getGeopoint())
              ? CommonText.getInstance().getCoordinateKey() : key;
      queries.remove(key);
      searchData();
    }
  }

  /**
   * Remove all filters
   */
  public void removeAllQueries() {
    log.info("removeAllQueries");
    
    filters.clear();
    queries.clear();
    searchData();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * Advance search auto suggestion
   * 
   * @param data - QueryData
   * @return List
   */
  public List<String> queryComplete(QueryData data) {
    log.info("queryComplete : {} -- {}", data.getValue(), data.getField());
    String fieldName = data.getField();  
    String value = data.getValue();
    switch (fieldName) {
      case "text":
        return solr.autoCompleteSearchAllField(SearchHelper.getInstance()
                .buildSearchString(data.getValue(), fieldName, 
                data.getContent()), fieldName); 
      case "ftx":
      case "eftx":
        return solr.autoCompleteTaxon(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getTxSearch(), data.getContent()));
      case "locality":
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getLocalitySearch(), 
                        data.getContent()), fieldName); 
      case "accessionNumber":
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getAccessionSearch(), 
                        data.getContent()), fieldName);  
      case "stationFieldNumber":    
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getStationFieldSearch(), 
                        data.getContent()), fieldName);   
      case "catalogNumber":
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getCatalogNumberSearch(), 
                        data.getContent()), fieldName);   
      case "determiner":    
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getDeterminerSearch(), 
                        data.getContent()), fieldName);    
      case "author":
        return solr.autoComleteMultivalue(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getAuthorSearch(), 
                        data.getContent()), value, fieldName); 
      case "collector":
        return solr.autoComleteMultivalue(SearchHelper.getInstance()
                .buildSearchString(value, CommonText.getInstance().getCollectorSearch(), 
                        data.getContent()), value, fieldName);     
      case "commonName":    
        return solr.autoComleteMultivalue(SearchHelper.getInstance()
                .buildSearchString(value, fieldName, 
                        data.getContent()), value, fieldName);   
      default:
        return solr.autoComleteSearch(SearchHelper.getInstance()
                .buildSearchString(value, fieldName, 
                        data.getContent()), fieldName);    
    }
  }
  
  /**
   * Advance search
   */
  public void advanceSearch() {
    log.info("advenceSearch"); 
    String searchText = queryDataList.isEmpty()
            ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    
    log.info("searchText: {}", searchText);
    statistic.resetData(searchText, queries);
    boolean isListSearch = true;
    if(navigator.isResultView()) {
      if(resultHeader.isImageView()) {
        galleria.setImageView(statistic.getFilteredTotalImages(), searchText, queries);
        isListSearch = false;
      } else if (resultHeader.isMapView()) {
        geo.setMapView(searchText, queries);
        isListSearch = false;
      }
    }
     
    if (isListSearch) {
      clearSelectedData();
      result = solr.searchWithFilter(searchText, queries, 0, numDisplay,
              CommonText.getInstance().getCreatedDate(), false);
      statistic.resetData(searchText, queries);
      gotoSimpleView();
      setResult();
    } 
  }
  
  private String buildAdvanceSearch() {
    log.info("buildAdvanceSearch");
    
    clearSelectedData();
    String searchText; 
    
    List<QueryData> list = queryDataList.stream()
            .filter(q -> q.getValue() != null && !q.getValue().isEmpty())
            .collect(Collectors.toList());
    if (list.size() == 1) {
      QueryData data = list.get(0);
      searchText = SearchHelper.getInstance().buildQueryDataString(data);
    } else {
      searchText = buildQueryString(list);
    } 
    log.info("buildAdvanceSearch:searchText: {}", searchText); 
    return searchText;
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
    
    selectedOneRecord.clear();
    if (!data.isSelected()) {
      data.setSelected(true);
      selectedRecords.add(data);
    }
    selectedOneRecord.add(data);
    selectedAll = true;
    isSelectedOne = true;
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
      gotoSimpleView();
    }
  }

  /**
   * Remove all the selected data
   */
  public void removeAllSelectedRecords() {
    log.info("removeAllSelectedRecords");
    selectedRecords.clear();
    
    resultList.stream()
            .filter(d -> d.isSelected())
            .forEach(d -> {
              d.setSelected(false);
            });
    selectedAll = false;
    gotoSimpleView();
  }

 

  
  public void backToListView() {
    log.info("backToListView");
    
    String searchText = isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
            : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay, CommonText.getInstance().getCreatedDate(), false);
    setResult();
    gotoSimpleView();
  }
  
  public void gotoSimpleView() {
    log.info("gotoSimpleView");
    
    isSelectedOne = false;
    selectedOneRecord.clear();
    resultHeader.setSimpleView();
  }
  
  public void gotoDetailView() {
    isSelectedOne = false;
    selectedOneRecord.clear();
    resultHeader.setDetailView();
  }
  
  public void gotoSelectedView() {
    isSelectedOne = false;
    selectedOneRecord.clear();
    selectedAll = true;
    resultHeader.setSelectedView();
  }
   

  /**
   * Display map view
   */
  public void showMapView() {
    log.info("showMapView");
    
    clearSelectedData();
    
    String searchText = isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
            : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    
    geo.setMapView(searchText, queries);
    resultHeader.setMapView();
  }
  
  public void listMapData() {
    log.info("listMapData");
    
    String searchText = isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
            : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    
    SolrData solrData = geo.getSelectedDataList().get(0);
    queries.put(CommonText.getInstance().getCoordinateKey(), solrData.getCoordinate());
    statistic.resetData(searchText, queries);
    
    gotoSimpleView(); 
    searchData(); 
    filters.put("geopoint", solrData.getLatitudeText() + " " + solrData.getLongitudeText());
    
    HelpClass.getInstance().updateView("resultsForm:result");
  }
  
  public void showImageView() {
    log.info("showImageView");
    clearSelectedData();
    galleria.setImageView(statistic.getFilteredTotalImages(), SearchHelper.getInstance().buildFullSearchText(freeText), queries);
    resultHeader.setImageView();
  }
 
  public void advanceClear() {
    log.info("advanceClear");
    
    clearAdvanceData();
    appendQuery();
    searchData();
    statistic.resetData(SearchHelper.getInstance().buildFullSearchText(freeText), queries);
  }
  
 
  
  private String addBean(String text, QueryData bean) {
    StringBuilder sb = new StringBuilder();
    
    String op = bean.getOperation();
    if (op.equals("and")) {
      sb.append("+");
    }
    sb.append("(");
    sb.append(text);
    sb.append(") ");
    sb.append(SearchHelper.getInstance().buildQueryDataString(bean));
    return sb.toString().trim();
  }
  
  private String buildTwoBeans(QueryData bean1, QueryData bean2) {
    StringBuilder sb = new StringBuilder();
  
    if (bean2.getOperation().equals("and")) {
      sb.append("+");
    }
    sb.append("(");
    sb.append(SearchHelper.getInstance().buildQueryDataString(bean1));
    
    sb.append(") ");
    sb.append(SearchHelper.getInstance().buildQueryDataString(bean2));
    return sb.toString().trim();
  }
  
//  private String buildQueryBeanString(QueryData bean) {    
//    log.info("buildQueryBeanString : bean : {}", bean);
//    
//    String field = bean.getField();    
//    switch (field) {
//      case "date":
//        return SolrHelper.getInstance().buildDate(bean);      
//      case "season":
//        return SolrHelper.getInstance().buildSeason(bean);      
//      case "ftx":
////        searchText = SolrHelper.getInstance().buildClassificationSearch(bean);
//        return null;
//      case "eftx":
////        searchText = SolrHelper.getInstance().buildDeterminationSearch(bean);
//        return null;
//      case "text":
////        searchText = SolrHelper.getInstance().buildFullTextSearchText(bean);
//        return null;
//      case "cm":
////        searchText = SolrHelper.getInstance().buildCommonNameSearchText(bean);
//        return null;
//      default:
//        return SolrHelper.getInstance().buildAdvanceSearchText(bean);      
//    }    
//  }
  
  private String buildQueryString(List<QueryData> list) {
    log.info("buildQueryString: {}", list.size());
    
    QueryData b1 = list.get(0);
    QueryData b2 = list.get(1);
    String text = buildTwoBeans(b1, b2);
    log.info("text: {}", text);
    StringBuilder sb = new StringBuilder();
    if (queryDataList.size() == 2) {
      return text;
    } else {
      for (int i = 2; i < list.size(); i++) {
        text = addBean(text, list.get(i));
      }
      sb.append(text);
    }
    
    return text;    
  }
  
 
  
  public void openAdvanceSearch() {
    log.info("openAdvanceSearch");
    isSimpleSearch = false;
    
    String searchText = freeText == null || freeText.isEmpty() ? "" : freeText;
    QueryData qb = new QueryData("", "contains", "text", searchText);
    queryDataList = new ArrayList<>();
    queryDataList.add(qb);
    queryText = "";
    
    appendQuery();
//    if (welcomePage) {
//      cleardata();
//    } else {
//      String searchText;
//      QueryBean qb = new QueryBean("", "contains", "text", "");
//      if (!input.equals(defaultSearchText) && !StringUtils.isEmpty(input)) {
//        qb.setValue(input);
//        queryBeans = new ArrayList<>();
//        queryBeans.add(qb);
//        querytext = "";
//        searchText = buildSearchText(WILD_CHAR);
//        session.setAttribute(SAVED_SEARCH_TEXT, searchText);
//        appendQuery();
//      }
//    }

    HelpClass.getInstance().updateView("searchForm:searchPanel");
  }
  
  public void handleDateSelect(SelectEvent event) {
    log.info("handleDateSelect : {}", event);
    
    appendQuery();
  }
  
  private void appendQuery() {
//        log.info("appandQuery : {}", queryBeans.size());

    queryTextSb = new StringBuilder();
    queryDataList.stream().map((bean) -> {
      if (queryDataList.indexOf(bean) > 0) {
        if (bean.isAppendValue()) {
          queryTextSb.append(" ");
          queryTextSb.append(bean.getOperation());
          queryTextSb.append(" ");
        }
      }
      return bean;
    }).forEachOrdered((bean) -> {
      queryTextSb.append(buildQueryString(bean));
    });
    queryText = queryTextSb.toString();
  }
  
  private String buildQueryString(QueryData data) {
    StringBuilder sb = new StringBuilder();
    switch (data.getField()) {
      case "date":
        Date startDate = data.getFromDate();
        Date endDate = data.getToDate();
        sb.append("[From date] ");
        if (startDate == null) {
          sb.append("*");
        } else {
          sb.append(DateHelper.getInstance().dateToString(startDate));
        }
        sb.append(" [To date] ");
        if (endDate == null) {
          sb.append("*");
        } else {
          sb.append(DateHelper.getInstance().dateToString(data.getToDate()));
        }
        break;
      case "season":
        int startMonth = data.getStartMonth() == 0 ? 1 : data.getStartMonth();
        int endMonth = data.getEndMonth() == 0 ? 12 : data.getEndMonth();
        sb.append(" [Form] ");
        sb.append(MonthElement.valueOfNameByNumberOfMonth(startMonth, isSwedish));
        sb.append(" ");
        sb.append(data.getStartDay());
        sb.append(" [To] ");
        sb.append(MonthElement.valueOfNameByNumberOfMonth(endMonth, isSwedish));
        sb.append(" ");
        sb.append(data.getEndDay());
        break;
      default:
        if (data.getValue() != null && !data.getValue().isEmpty()) {
          sb.append(data.getValue());
          if (!data.getField().equals("text")) {
            String field = CommonText.getInstance().getFieldName(data.getField(), isSwedish);
            sb.append(" [");
            sb.append(field);
            sb.append("] ");
          }
        }
        break;
    }
    return sb.toString();
  }
  
  public List<SelectItem> getMonthList() {
    
    List<SelectItem> dropMonthList = new ArrayList();
    dropMonthList.add(new SelectItem(0, CommonText.getInstance().getSelectMonth(isSwedish)));
    IntStream.range(1, 13)
            .forEach(x -> {
              dropMonthList.add(new SelectItem(String.valueOf(x),
                      MonthElement.valueOfNameByNumberOfMonth(x, isSwedish)));
            });
    return dropMonthList;
  }
  
  public void closeAdvanceSearch() {
    log.info("closeAdvanceSearch");
    
    isSimpleSearch = true;
    HelpClass.getInstance().updateView("searchForm:searchPanel");
  }

  /**
   * operationChange -- when operation changes in ui from advance search
   *
   * @param data
   * @param index
   */
  public void operationChange(QueryData data, int index) {
    log.info("operationChange : {} -- {}", data.getField(), index);

//    appendQuery();
//
//    if (index == 1) {
//      QueryBean bean = queryBeans.get(0);
//      if (qb.getOperattion().equals("and")) {
//        bean.setOperattion("and");
//      } else {
//        bean.setOperattion("");
//      }
//    }
  }
  
  public List startDayList(int index) {
    log.info("getStartDayList: {}", index);
    QueryData data = queryDataList.get(index);
    return HelpClass.getInstance().getDayList(data.getStartMonth());
  }
  
  public List endDayList(int index) {
    log.info("getEndDayList: {}", index);
    QueryData data = queryDataList.get(index);
    return HelpClass.getInstance().getDayList(data.getEndMonth());
  }
  
  public void changeStartMonth() {
    log.info("changeStartMonth");
    appendQuery();
  }
  
  public void changeEndMonth() {
    log.info("changeEndMonth");
    appendQuery();
  }
  
  public void changeStartDay() {
    log.info("changeStartDay");
    appendQuery();
  }
  
  public void changeEndDay() {
    log.info("changeEndDay");
    appendQuery();
  }
  
  public void removeQueryLine(int index) {
    log.info("removeQueryLine : {} ", index);
    
    queryDataList.remove(index);
    appendQuery();
  }
  
  public void addQueryLine(QueryData qb, int index) {
    log.info("addQueryLine : {} -- {}", qb, index);

//    if (index == queryDataList.size() - 1) {
//      QueryBean bean = queryDataList.get(queryDataList.size() - 1);                     // get last query bean
//      bean.setField(qb.getField());
//      bean.setValue(qb.getValue());
//      bean.setOperattion(qb.getOperattion());
//      bean.setStMon(qb.getStMon());
//      bean.setEndMon(qb.getEndMon());
//      bean.setFromDate(qb.getFromDate());
//      bean.setToDate(qb.getToDate());
//
//      if (index == 1 && qb.getOperattion().equals("and")) {
//        QueryBean querybean = queryBeans.get(0);
//        querybean.setOperattion("and");
//      }
// 
    queryDataList.add(new QueryData("and", "contains", "text", ""));
//    }
  }
  
 
  
  public void itemSelect(SelectEvent event) {
//        log.info("itemSelect"); 
    appendQuery();
  }
  
  public void keyup() {
    appendQuery();
  }
  
  public void freeTextKeyup() {
    
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
  
  public void showSingleMap(SolrData data) {
    log.info("showSingleMap");
    
    data.setDisplayMap(true);
  }
  
  public void closeMap(SolrData data) {
    data.setDisplayMap(false);
  }
  
  public void displayImages(SolrData data) {
    log.info("displayImages : {}", data);
    
    String mbid = data.getMorphbankId();
    if (mbid != null) {
      data.setImageExist(true);
      data.setDisplayImage(true);
      data.setImages(properties.getMorphbankThumbPath());
    }
  }
  
  public void closeImage(SolrData data) {
    data.setDisplayImage(false);
  }

  public void sortResult() {
    log.info("sorteResult: {}", sortby);

    String searchText = SearchHelper.getInstance().buildFullSearchText(freeText);

    clearSelectedData();
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay, sortby, true);
    statistic.resetData(searchText, queries);
//    gotoSimpleView();
    setResult(); 
  }

  public void export() {
    log.info("export");
    exportDataSet = new ArrayList<>();
  }
  
  public List<SolrData> getExportDataSet() {
    if (exportDataSet.isEmpty()) {
      prepareExportData();
    }
    return exportDataSet;
  }
  
  private void prepareExportData() {
    log.info("prepareExportData");
    
    exportDataSet = new ArrayList<>();
    if (!selectedRecords.isEmpty()) {
      exportDataSet.addAll(selectedRecords);
    } else {
      int numToFetch = totalResult <= 1000 ? totalResult : 1000;
      SolrResult data = solr.searchWithFilter(SearchHelper.getInstance().buildFullSearchText(freeText), 
              queries, 0, numToFetch, CommonText.getInstance().getCreatedDate(), true);
      exportDataSet = data.getSolrData();
    }
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
  
  public List<SolrData> getResultList() {
    return resultList;
  }
  
  public void setResultList(List<SolrData> resultList) {
    this.resultList = resultList;
  }
  
  public int getTotalResult() {
    return totalResult;
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
  
  public Map<String, String> getFilters() {
    return filters;
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
    return isSelectedOne ? selectedOneRecord : selectedRecords;
  }
  
  public String getSelectedCollectionName() {
    return selectedCollection == null ? "" : selectedCollection.getShortName();
  }
  
  public String getSelectedInstitution() {
    return selectedInstitution;
  }
  
  public String getResultHeaderSummary() {
    if (resultHeader.isMapView()) {
      return HelpClass.getInstance()
              .buildResultHeaderSummaryForMapView(statistic.getFilteredTotalMaps(), isSwedish);
    }
    if (resultHeader.isImageView()) {
      return HelpClass.getInstance().buildEmptyString();
    }
    int total = isSelectedOne ? 1 : selectedRecords.size();
    return HelpClass.getInstance().buildResultHeaderSummaryForResultView(total, isSwedish);
  }
  
  public boolean isSelectedAll() {
    return selectedAll;
  }
  
  public void setSelectedAll(boolean selectedAll) {
    this.selectedAll = selectedAll;
  }
  
  public boolean isIsSelectedOne() {
    return isSelectedOne;
  }
  
  private void pagingSearch(int start) {
    result = solr.searchWithFilter(SearchHelper.getInstance().buildFullSearchText(freeText), 
            queries, start, numDisplay, CommonText.getInstance().getCreatedDate(), false);
    resultList = result.getSolrData();
  }
 
  public boolean isOverMaxDownloadSize() {
    return selectedRecords.size() > 0 ? selectedRecords.size() > 1000 : totalResult > 1000;
  }
  
  public List<QueryData> getQueryDataList() {
    return queryDataList;
  }
  
  public String getMaxDate() {
    return HelpClass.getInstance().getTodaysDateInString();
  }
  
  public String getQueryText() {
    return queryText;
  }
  
  public boolean isDisableAdvanceSearch() {
    return queryText == null || queryText.isEmpty();
  }
  
  private void clearAdvanceData() {
    freeText = null;
    result = null;
    resultList = new ArrayList<>();
    exportDataSet = new ArrayList<>();
    selectedAll = false;
    
    queryDataList = new ArrayList<>();
    queryDataList.add(new QueryData("", "contains", "text", ""));
    queryTextSb = new StringBuilder();
    queryText = "";
    clearSelectedData();
  }

  /**
   * Clear data to initial state
   */
  private void clearData() {
    freeText = null;
    isSimpleSearch = true;
    numDisplay = 10;
    queries = new HashMap();
    filters = new HashMap<>();
    selectedCollection = null;
    selectedInstitution = null;
    sortby = CommonText.getInstance().getSortByScore();
    result = null;
    resultList = new ArrayList<>();
    exportDataSet = new ArrayList<>();
    selectedAll = false;
    totalResult = 0;
    
    queryDataList = new ArrayList<>();
    queryDataList.add(getNewQueryData());
    queryTextSb = new StringBuilder();
    queryText = "";
    
    clearSelectedData();
  }
  
  private QueryData getNewQueryData() {
    return new QueryData(CommonText.getInstance().getEmptyString(), CommonText.getInstance().getContains(),
            CommonText.getInstance().getTextField(), CommonText.getInstance().getEmptyString());
  }
  
  private void clearSelectedData() {
    isSelectedOne = false;
    selectedRecords = new ArrayList<>();
    selectedOneRecord = new ArrayList<>();
    selectedAll = false;
  }
  
  private void updateFilters() {
    filters = new HashMap();
    filters = queries.entrySet().stream()
            .filter(notCoordinateKey)
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
  
  Predicate<Entry<String, String>> notCoordinateKey
          = e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey());
}
