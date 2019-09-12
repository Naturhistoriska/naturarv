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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.solr.SolrHelper;
import se.nrm.dina.web.portal.solr.SolrService;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

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
  private List<SolrData> selectedRecords;
  private List<SolrData> selectedOneRecord;
  private List<SolrData> exportDataSet;
  private QueryData queryData;
  private List<QueryData> queryDataList;

  private String queryText;

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
  private GeoMap geo;
  @Inject
  private GalleriaBean galleria;
  @Inject
  private InitialProperties properties;

  public SearchBean() {
    facesContext = FacesContext.getCurrentInstance();
    session = (HttpSession) facesContext.getExternalContext().getSession(false);

    QueryData qd = new QueryData("", "contains", "text", "");
    queryDataList = new ArrayList<>();
    queryDataList.add(qd);
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
//    statistic.resetData(CommonText.getInstance().getWildSearchText(), queries);
    gotoSimpleView();
    setResult();
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

    String institutionCode = key.equals(CommonText.getInstance().getNrmName(isSwedish))
            ? CommonText.getInstance().getNrmCode() : CommonText.getInstance().getGnmCode();
    filterSearch(CommonText.getInstance().getIdKey(), institutionCode + CommonText.getInstance().getWildCard());
    selectedInstitution = key;
  }

  private void filterSearch(String key, String value) {
    log.info("filterSearch");

    clearData();
    String searchText = getBuildSearchText();
    queries.put(key, value);
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
    statistic.resetData(getBuildSearchText(), queries);

    updateFilters();
    gotoSimpleView();
    setResult();
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

  public void searchDataWithFilter(String filterKey) {

    result = null;
    resultList.clear();
    totalResult = 0;
    clearSelectedData();

    String searchText = getBuildSearchText();
    queries.put(filterKey, String.valueOf(true));

    statistic.resetData(searchText, queries);
    if (resultHeader.isMapView()) {
      geo.setMapView(statistic.getFilteredTotalMaps(), searchText, queries);
    } else {
      filterSearchWithQueries(searchText);
    }
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

    result = null;
    resultList.clear();
    totalResult = 0;
    clearSelectedData();

    String searchText = getBuildSearchText();
    queries.put(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
    statistic.resetData(searchText, queries);
    if (resultHeader.isMapView()) {
      geo.setMapView(statistic.getFilteredTotalMaps(), searchText, queries);
    } else {
      filterSearchWithQueries(searchText);
    }

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

    result = null;
    resultList.clear();
    totalResult = 0;
    clearSelectedData();

    String institutionCode = key.equals(CommonText.getInstance().getNrmName(isSwedish))
            ? CommonText.getInstance().getNrmCode() : CommonText.getInstance().getGnmCode();

    String searchText = getBuildSearchText();
    queries.put(CommonText.getInstance().getIdKey(), institutionCode + "*");

    statistic.resetData(searchText, queries);
    if (resultHeader.isMapView()) {
      geo.setMapView(statistic.getFilteredTotalMaps(), searchText, queries);
    } else {
      filterSearchWithQueries(searchText);
    }
    selectedInstitution = key;
    filters = new HashMap();
    filters = queries.entrySet().stream()
            .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }

  private void filterSearchWithQueries(String searchText) {
    result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
    setResult();
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

  public void blur() {
    log.info("blur: {}", freeText);
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
    resultHeader.selectedView();
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

  private void searchData() {
    result = null;
    resultList.clear();
    totalResult = 0;
    clearSelectedData();

    String searchText = getBuildSearchText();
    statistic.resetData(searchText, queries);
    if (resultHeader.isMapView()) {
      geo.setMapView(statistic.getFilteredTotalMaps(), searchText, queries);
    } else {
      result = solr.searchWithFilter(searchText, queries, 0, numDisplay);
      setResult();
    }
  }

  public void backToListView() {
    log.info("backToListView");

    result = solr.searchWithFilter(getBuildSearchText(), queries, 0, numDisplay);
    setResult();
    gotoSimpleView();
  }

  public void gotoSimpleView() {
    log.info("gotoSimpleView");

    isSelectedOne = false;
    selectedOneRecord.clear();
    resultHeader.simpleView();
  }

  public void gotoDetailView() {
    isSelectedOne = false;
    selectedOneRecord.clear();
    resultHeader.detailView();
  }

  public void gotoSelectedView() {
    isSelectedOne = false;
    selectedOneRecord.clear();
    selectedAll = true;
    resultHeader.selectedView();
  }

  /**
   * Display map view
   */
  public void showMapView() {
    log.info("showMapView");

    clearSelectedData();
    geo.setMapView(statistic.getFilteredTotalMaps(), getBuildSearchText(), queries);
    resultHeader.mapView();
  }

  public void listMapData() {
    log.info("listMapData");

    SolrData solrData = geo.getSelectedDataList().get(0);
    queries.put(CommonText.getInstance().getCoordinateKey(), solrData.getCoordinate());
    statistic.resetData(getSearchText(), queries);
    filterSearchWithQueries(getSearchText());
    filters.put("geopoint", solrData.getLatitudeText() + " " + solrData.getLongitudeText());
    gotoSimpleView();
    updateView("resultsForm:result");
  }

  public void showImageView() {
    log.info("showImageView");
    clearSelectedData();
    galleria.setImageView(statistic.getFilteredTotalImages(), getSearchText(), queries);
    resultHeader.imageView();
  }

  public String getSearchText() {
    return getBuildSearchText();
  }

  public void openAdvanceSearch() {
    log.info("openAdvanceSearch");
    isSimpleSearch = false;

    String searchText = freeText == null || freeText.isEmpty() ? "" : freeText;
    QueryData qb = new QueryData("", "contains", "text", searchText);
    queryDataList = new ArrayList<>();
    queryDataList.add(qb);
    queryText = "";
    if (welcomePage) {
      cleardata();
    } else {
      String searchText;
      QueryBean qb = new QueryBean("", "contains", "text", "");
      if (!input.equals(defaultSearchText) && !StringUtils.isEmpty(input)) {
        qb.setValue(input);
        queryBeans = new ArrayList<>();
        queryBeans.add(qb);
        querytext = "";
        searchText = buildSearchText(WILD_CHAR);
        session.setAttribute(SAVED_SEARCH_TEXT, searchText);
        appendQuery();
      }
    }

    updateView("searchForm:searchPanel");
  }

  private void appendQuery() {
//        log.info("appandQuery : {}", queryBeans.size());

    StringBuilder sb = new StringBuilder();
    for (QueryData bean : queryDataList) {
      if (queryDataList.indexOf(bean) > 0) {
        sb.append(" ");
        sb.append(bean.getOperattion());
        sb.append(" ");
      }
      sb.append(buildQueryString(bean));
    }
    queryText = sb.toString();
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
          sb.append(HelpClass.getInstance().dateToString(startDate));
        }
        sb.append(" [To date] ");
        if (endDate == null) {
          sb.append("*");
        } else {
          sb.append(HelpUtil.getInstance().dateToString(bean.getToDate()));
        }
        break;
      case "season":
        //            int startMonth = bean.getStartMonth();
//            int endMonth = bean.getEndMonth();

//            if(startMonth == 0) {
//                startMonth = 1;
//            } 
//
//            if (endMonth == 0) {
//                endMonth = 12;
//            }
        sb.append(" [Form] ");
        sb.append(StringMap.getInstance().getMonth(bean.getStartMonth(), locale, true));
        sb.append(" ");
        sb.append(bean.getStartDay());
        sb.append(" [To] ");
        sb.append(StringMap.getInstance().getMonth(bean.getEndMonth(), locale, false));
        sb.append(" ");
        sb.append(bean.getEndDay());
        break;
      default:
        if (bean.getValue() != null && bean.getValue().trim().length() > 0) {
          sb.append(bean.getValue());
          if (!bean.getField().equals("text")) {
            String field = StringMap.getInstance().getFieldName(bean.getField() + "_" + locale);
            sb.append(" [");
            sb.append(field);
            sb.append("] ");
          }
        }
        break;
    }
    return sb.toString();
  }

  public void closeAdvanceSearch() {
    log.info("closeAdvanceSearch");

    isSimpleSearch = true;
    updateView("searchForm:searchPanel");
  }

  /**
   * operationchange -- when operation changes in ui from advance search
   *
   * @param data
   * @param index
   */
  public void operationChange(QueryData data, int index) {
    log.info("operationChange : {} -- {}", data.getOperattion(), index);
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
    log.info("sortResult: {}", sortby);

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
      SolrResult data = solr.searchWithFilter(getSearchText(), queries, 0, numToFetch);
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
    result = solr.searchWithFilter(getBuildSearchText(), queries, start, numDisplay);
    resultList = result.getSolrData();
  }

  public String getBuildSearchText() {
    return SolrHelper.getInstance().buildSearchText(CommonText.getInstance().getTextKey(), freeText);
  }

  public boolean isOverMaxDownloadSize() {
    return selectedRecords.size() > 0 ? selectedRecords.size() > 1000 : result.getTotalFound() > 1000;
  }

  public QueryData getQueryData() {
    return queryData;
  }

  public List<QueryData> getQueryDataList() {
    return queryDataList;
  }

  public String getQueryText() {
    return queryText;
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
    clearSelectedData();
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
            .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
}
