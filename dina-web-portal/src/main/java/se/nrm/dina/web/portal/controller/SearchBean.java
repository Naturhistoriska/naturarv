package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named; 
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
    private boolean isSwedish;
    private int totalResult;
    private int numDisplay;
    private String sortby;

    private CollectionData selectedCollection; 
    private List<CollectionData> selectionCollections;
    private List<SolrData> selectedRecords;
    private List<SolrData> selectedOneRecord;
    private List<SolrData> exportDataSet;
    private List<QueryData> queryDataList;

    private String queryText;
    private StringBuilder queryTextSb;

    private boolean isSelectedOne;
    private boolean isSimpleSearch;
    private boolean selectedAll;
    
    private final String queryStringAll = "collection=all";
     
    private final String pzVert = "PzVert";
    private final String pzInvert = "PzInvert";
    
    private final String wildCard = "*";
    private final String emptySpace = " ";

    private List<SolrData> resultList;
    private SolrResult result;
    private Map<String, String> queries;
    private Map<String, String> filters;
    private Map<String, String> collectionFilters;  
    private final String vertebrates = "vertebrates";
    private final String invertebrates = "invertebrates";
    private final String vertebratesSearch = "+(txFullName:Chordata higherTx:Chordata*)";
    private final String invertebratesSearch = "-(txFullName:Chordata higherTx:Chordata*)";
//    private final String zooCollectionSearch = "(e* 163840)";
    private final String zoo = "zoo";
    
    
    private boolean showCollectionStatisticData;
    private String pbDataset; 
    private boolean isAllCollections;
    
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
    private MyMap geo;
    @Inject
    private GalleriaBean galleria;
    @Inject
    private InitialProperties properties;

    public SearchBean() {
        isSwedish = true;
    }

    public SearchBean(SolrService solr, Navigator navigator, PagingNavigation paging,
            ResultHeader resultHeader, StatisticBean statistic, GeoHashMap geo,
            GalleriaBean galleria, InitialProperties properties) {
        this.solr = solr;
        this.navigator = navigator;
        this.paging = paging;
        this.resultHeader = resultHeader;
        this.statistic = statistic; 
        this.galleria = galleria;
        this.properties = properties;
    }

    /**
     * Initialize data after class constructed
     */
    @PostConstruct
    public void init() {
        log.info("init from search...");

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
        if (navigator.isResultView()) {
            if (resultHeader.isImageView()) {
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

    public void searchCollectionWithQuery(String collectionId, String dataset, String queryString) {
        log.info("searchCollectionWithQuery: {} -- {}", collectionId, dataset);

        pbDataset = null;
        String searchText = SearchHelper.getInstance().buildFullSearchText(freeText); 
        clearData();
        if (dataset != null) {
            pbDataset = dataset;
            if (dataset.equals(vertebrates)) {  
                searchText = vertebratesSearch;
            } else if (dataset.equals(invertebrates)) {
                searchText = invertebratesSearch;
            }
        }  
//        if(!collectionId.equals("zoo")) {
//            queries.put(CommonText.getInstance().getCollectionCodeKey(), collectionId);
//        } else {
//            searchText = zooCollectionSearch;
//        }
        
        queries.put(CommonText.getInstance().getCollectionCodeKey(), collectionId);
        collectionSearch(queryString, searchText);

        
        if (result.getTotalFound() > 0) {
            SolrData data = result.getSolrData().get(0);
            CollectionData collectionData = new CollectionData(collectionId,
                    data.getCollectionName(), result.getTotalFound()); 
            
            if(statistic.getFilteredCollections().size() == 1) {
                this.selectedCollection = collectionData;
            } else {
                this.selectedCollection = null;
                if(collectionId.equals(wildCard)) {
                    this.selectionCollections = null;
                } else {
                    selectionCollections = new ArrayList();
                    this.selectionCollections.addAll(statistic.getFilteredCollections());
                }
            } 
//            this.selectedCollection = statistic.getFilteredCollections().size() > 1 ? 
//                    null : collectionData;
        }
    }
     
    private void collectionSearch(String queryString, String searchText) {
        log.info("collectionSearch : {}", queryString);
 
        result = solr.searchWithFilter(searchText, queries, 0, numDisplay,
                CommonText.getInstance().getCreatedDate(), false);
        statistic.resetData(searchText, queries);

        updateFilters();
        gotoSimpleView();
        setResult(queryString);
    }

    private void setResult(String queryString) {
        if (result == null) {
            navigator.gotoNoResults(queryString);
        } else {
            resultList = result.getSolrData();
            totalResult = result.getTotalFound();
            paging.calculateTotalPages(totalResult, numDisplay);
            if (!navigator.isResultView()) {
                navigator.gotoResults(queryString);
            }
        }
    }
    
    public void searchAllcollections() {
        log.info("searchAllcollections");
         
        searchCollectionWithQuery(wildCard, null, queryStringAll);
        navigator.gotoResults(queryStringAll);
    }
    
    public void showCollectionStatistic() {
        log.info("showCollectionStatistic : {}", selectedCollection);
        showCollectionStatisticData = true; 
    }
    
    public void closeCollectionStatistic() {
        log.info("closeCollectionStatistic");
        showCollectionStatisticData = false;
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
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
//                .getExternalContext().getRequest();
         
        queries.put(filterKey, String.valueOf(true));
        searchData();
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
        queries.put(CommonText.getInstance().getCollectionCodeKey(), collection.getCode());
     
        searchData();
        filters = new HashMap();
        filters = queries.entrySet().stream()
                .filter(e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey()))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        this.selectedCollection = collection;
    }

    private void searchData() {
        result = null;
        resultList.clear();
        totalResult = 0;
        clearSelectedData();

        String searchText = getSearchText();
    
        statistic.resetData(searchText, queries);
        if (resultHeader.isMapView()) {
            geo.setMapView(searchText, queries);
        } else {
            result = solr.searchWithFilter(searchText, queries, 0, numDisplay,
                    CommonText.getInstance().getCreatedDate(), false);
            setResult();
        }
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

            key = key.equals(CommonText.getInstance().getGeopoint())
                    ? CommonText.getInstance().getCoordinateKey() : key;
            queries.remove(key);
            searchData();
        }
    }
    
    /**
     *
     * @param key
     * @param value
     */
    public void removeCollectionFilter(String key, String value) {
        log.info("removeCollectionFilter: {} -- {}", key, value);

        if(!key.equals(CommonText.getInstance().getCollectionCodeKey()) && 
                !key.equals(CommonText.getInstance().getHighTxKey())) {
            filters.remove(key);
            
            key = key.equals(CommonText.getInstance().getGeopoint())
                    ? CommonText.getInstance().getCoordinateKey() : key;
            queries.remove(key);
        } 
        searchData(); 
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
    
    public void removeAllCollectionQueries() {
        log.info("removeAllCollectionQueries : {} ", queries);

        filters.remove(CommonText.getInstance().getIsTypeKey());
        filters.remove(CommonText.getInstance().getMapKey());
        filters.remove(CommonText.getInstance().getInSwedenKey());
        filters.remove(CommonText.getInstance().getImageKey());
        
        queries.remove(CommonText.getInstance().getIsTypeKey());
        queries.remove(CommonText.getInstance().getMapKey());
        queries.remove(CommonText.getInstance().getInSwedenKey());
        queries.remove(CommonText.getInstance().getImageKey());
                   
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
        switch (data.getField()) {
            case "textsearch":
            case "auth":
            case "clt":
            case "tx":    
            case "cm":
                return solr.autoComleteMultivalue(data);
            case "ftx":
            case "eftx":
                return solr.autoCompleteTaxon(data);
            default:
                return solr.autoComleteSearch(data);
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
        if (navigator.isResultView()) {
            if (resultHeader.isImageView()) {
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
        log.info("buildAdvanceSearch : {}", queryDataList.size());

        clearSelectedData();
        String searchText;

        List<QueryData> list = queryDataList.stream()
                .filter(q -> q.isAppendValue())
                .collect(Collectors.toList());

        if (list.size() == 1) {
            QueryData data = list.get(0);
            searchText = SearchHelper.getInstance().buildQueryDataString(data);
        } else if (list.size() > 1) {
            searchText = buildQueryString(list);
        } else {
            searchText = CommonText.getInstance().getWildSearchText();
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

        result = solr.searchWithFilter(getSearchText(), queries, 0, numDisplay,
                CommonText.getInstance().getCreatedDate(), false);
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
        geo.setMapView(getSearchText(), queries);
        resultHeader.setMapView();
    }

    public void showImageView() {
        log.info("showImageView " );
        clearSelectedData();
        galleria.setImageView(statistic.getFilteredTotalImages(),
                SearchHelper.getInstance().buildFullSearchText(freeText), queries);
        resultHeader.setImageView();
    }

    public void advanceClear() {
        log.info("advanceClear");

        clearAdvanceData();
        appendQuery();

        if (navigator.isResultView()) {
            searchData();
        }
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
        QueryData qb = new QueryData("", "contains", CommonText.getInstance().getTextSearch(), searchText, false);
        queryDataList = new ArrayList<>();
        queryDataList.add(qb);
        queryText = "";

        appendQuery();
        HelpClass.getInstance().updateView("searchForm:searchPanel");
    }

    public void handleTypeStatusSelect() {
        log.info("handleTypeStatusSelect");
        appendQuery();
    }

    public void handleDateSelect(SelectEvent event) {
        log.info("handleDateSelect");

        appendQuery();
    }

    private void appendQuery() {
//        log.info("appandQuery : {}", queryBeans.size());

        queryTextSb = new StringBuilder();
        queryDataList.stream().map((bean) -> {
            if (queryDataList.indexOf(bean) > 0) {
                if (bean.isAppendValue()) {
                    queryTextSb.append(emptySpace);
                    queryTextSb.append(bean.getOperation());
                    queryTextSb.append(emptySpace);
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
            case "ts":
                if (data.isIsSearchAllType()) {
                    sb.append(CommonText.getInstance().getAllType(isSwedish));
                    data.setValue("");
                } else {
                    if (data.getValue() != null && !data.getValue().isEmpty()) {
                        sb.append(data.getValue());
                        String field = CommonText.getInstance().getFieldName(data.getField(), isSwedish);
                        sb.append(" [");
                        sb.append(field);
                        sb.append("] ");
                    }
                }
                break;
            case "date":
                Date startDate = data.getFromDate();
                Date endDate = data.getToDate();
                sb.append(CommonText.getInstance().getFromDate(isSwedish));
                if (startDate == null) {
                    sb.append(wildCard);
                } else {
                    sb.append(DateHelper.getInstance().dateToString(startDate));
                }
                sb.append(CommonText.getInstance().getToDate(isSwedish));
                if (endDate == null) {
                    sb.append(wildCard);
                } else {
                    sb.append(DateHelper.getInstance().dateToString(data.getToDate()));
                }
                break;
            case "season":
                int startMonth = data.getStartMonth() == 0 ? 1 : data.getStartMonth();
                int endMonth = data.getEndMonth() == 0 ? 12 : data.getEndMonth();
                sb.append(CommonText.getInstance().getFrom(isSwedish));
                sb.append(MonthElement.valueOfNameByNumberOfMonth(startMonth, isSwedish));
                sb.append(" ");
                sb.append(data.getStartDay());
                sb.append(CommonText.getInstance().getTo(isSwedish));
                sb.append(MonthElement.valueOfNameByNumberOfMonth(endMonth, isSwedish));
                sb.append(" ");
                sb.append(data.getEndDay());
                break;
            default:
                if (data.getValue() != null && !data.getValue().isEmpty()) {
                    sb.append(data.getValue());
                    if (!data.getField().equals(CommonText.getInstance().getTextSearch())) {
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
        log.info("getMonthList : {}", isSwedish);
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
        appendQuery();
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
        queryDataList.add(new QueryData("and", "contains", "searchtext", "", false));
    }

    public void itemSelect(SelectEvent event) {
        appendQuery();
    }

    public void keyup() {
        log.info("keyup");
        appendQuery();
    }

    public void freeTextKeyup() {

    }

    public boolean pbCollection(SolrData data) {
        log.info("pbCollection : {}", data.getCollectionId());

        return true;
    }

    public void selectOne(SolrData data) {
        log.info("selectOne: {}", data.isSelected());

        if (data.isSelected()) {
            selectedRecords.add(data);
            log.info("data added: {}", selectedRecords.size());
        } else {
            selectedRecords.remove(data);
            log.info("data removeed: {}", selectedRecords.size());
        }
    }

    public void showSingleMap(SolrData data) {
        log.info("showSingleMap : {}", resultList.size());

        resultList.stream()
                .forEach(r -> {
                    r.setDisplayMap(false);
                });

        data.setDisplayMap(true);
    }

    public void closeMap(SolrData data) {
        data.setDisplayMap(false);
    }
    
    public void displayKboImages(SolrData data) {
        log.info("displayKboImages : {}", data);
        
        List<String> urls = Arrays.asList(data.getAssociatedMedia());
 
        if (urls != null) {
            data.setImageExist(true);
            data.setDisplayImage(true);
            data.setKboImages(properties.getMorphbankThumbPath());
        }
    }

    public void displayImages(SolrData data) {
        log.info("displayImages : {}", data);

        if(data.isCommonCollection()) {
            String mbid = data.getMorphbankId();
            log.info("mbid : {}", mbid);
            if (mbid != null) {
                data.setImageExist(true);
                data.setDisplayImage(true);
                data.setImages(properties.getMorphbankThumbPath());
            }
        } else if(data.isFungiCollection() || data.isVasclarPlantsCollection()) {
            log.info("isFungi dataset");
            data.setImageExist(true);
            data.setDisplayImage(true);
            data.setKboImages(properties.getMorphbankThumbPath());
        }
    }

    public void closeImage(SolrData data) {
        data.setDisplayImage(false);
    }

    private String getSearchText() {
        return isSimpleSearch ? SearchHelper.getInstance().buildFullSearchText(freeText)
                : queryDataList.isEmpty() ? CommonText.getInstance().getEmptyString() : buildAdvanceSearch();
    }

    public void sortResult() {
        log.info("sorteResult: {}", sortby);

        String searchText = getSearchText();
        clearSelectedData();
        result = solr.searchWithFilter(searchText, queries, 0, numDisplay, sortby, true);
        statistic.resetData(searchText, queries);
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
            SolrResult data = solr.searchWithFilter(getSearchText(), queries, 0,
                    numToFetch, CommonText.getInstance().getCreatedDate(), true);
            exportDataSet = data.getSolrData();
        }
    }
     
    public String getSearchHeader() {
        log.info("getSearchHeader : {}", selectionCollections);
        if(navigator.isIsCollectionSearch()) {
            isAllCollections = false;
            if(pbDataset != null) {
                if(pbDataset.equals(vertebrates)) {
                    return isSwedish ? 
                        CommonText.getInstance().getSearchTextSvMap(pzVert) :
                        CommonText.getInstance().getSearchTextEnMap(pzVert); 
                }
                return isSwedish ? 
                        CommonText.getInstance().getSearchTextSvMap(pzInvert) :
                        CommonText.getInstance().getSearchTextEnMap(pzInvert); 
            } 
            if(selectedCollection != null) {
                return isSwedish ? 
                    CommonText.getInstance().getSearchTextSvMap(selectedCollection.getName()) :
                    CommonText.getInstance().getSearchTextEnMap(selectedCollection.getName()); 
            } else if(selectionCollections != null) {
                return isSwedish ? 
                    CommonText.getInstance().getSearchTextSvMap(zoo) :
                    CommonText.getInstance().getSearchTextEnMap(zoo); 
            } 
        }
        isAllCollections = true;
        return CommonText.getInstance().getSearchInAllCollections(isSwedish);
    }

    public String getDefaultText() { 
        if(navigator.isIsCollectionSearch()) { 
            if(pbDataset != null) {
                if(pbDataset.equals(vertebrates)) {
                    return isSwedish ? 
                        CommonText.getInstance().getSearchTextSvMap(pzVert) :
                        CommonText.getInstance().getSearchTextEnMap(pzVert); 
                }
                return isSwedish ? 
                        CommonText.getInstance().getSearchTextSvMap(pzInvert) :
                        CommonText.getInstance().getSearchTextEnMap(pzInvert); 
            } 
            if(selectedCollection != null) {
                return isSwedish ? 
                    CommonText.getInstance().getSearchTextSvMap(selectedCollection.getName()) :
                    CommonText.getInstance().getSearchTextEnMap(selectedCollection.getName()); 
            } else if(selectionCollections != null) {
                return isSwedish ? 
                    CommonText.getInstance().getSearchTextSvMap(zoo) :
                    CommonText.getInstance().getSearchTextEnMap(zoo); 
            } 
        } 
        return CommonText.getInstance().getSearchDefaultText(isSwedish);
        
        
        
//        if (navigator.isIsCollectionSearch()) { 
//            StringBuilder textBuilder = new StringBuilder();
//            textBuilder.append(CommonText.getInstance().getSearchCollectionDefaultText1(isSwedish));
//            if(pbDataset != null) {
//                if(pbDataset.equals(vertebrates)) {
//                    textBuilder.append(isSwedish ? CommonText.getInstance().getCollectionSwedishName(selectedCollection.getName()) : selectedCollection.getName());
//                }
//            }
//            
//            
//            
//            
//            textBuilder.append(CommonText.getInstance().getSearchCollectionDefaultText1(isSwedish));
//            textBuilder.append(isSwedish ? CommonText.getInstance().getCollectionSwedishName(selectedCollection.getName()) : selectedCollection.getName());
//            textBuilder.append(CommonText.getInstance().getSearchCollectionDefaultText2(isSwedish));
//            return textBuilder.toString().trim(); 
//        }
//        return CommonText.getInstance().getSearchDefaultText(isSwedish);

        
//        if(navigator.isIsCollectionSearch()) {
//            if(pbDataset != null) {
//                return pbDataset.equals(vertebrates) ? 
//                        CommonText.getInstance().getSearchTextSvMap(pzInvert) :
//                        CommonText.getInstance().getSearchTextEnMap(pzInvert); 
//            }
//            if(selectedCollection != null) { 
//                return isSwedish ? 
//                    CommonText.getInstance().getSearchTextSvMap(selectedCollection.getName()) :
//                    CommonText.getInstance().getSearchTextEnMap(selectedCollection.getName()); 
//            }
//        }
//        return CommonText.getInstance().getSearchDefaultText(isSwedish);
        
        
//        if(!navigator.isIsCollectionSearch()) {
//            return CommonText.getInstance().getSearchDefaultText(isSwedish);
//        } else {
//            if(statistic.getFilteredCollections().size() == 1) { 
//                if(pbDataset != null && pbDataset.equals(vertebrates)) {
//                    return isSwedish ? 
//                        CommonText.getInstance().getSearchTextSvMap(selectedCollection.getName()) :
//                        CommonText.getInstance().getSearchTextEnMap(selectedCollection.getName()); 
//                }
//                return isSwedish ? 
//                    CommonText.getInstance().getSearchTextSvMap(selectedCollection.getName()) :
//                    CommonText.getInstance().getSearchTextEnMap(selectedCollection.getName()); 
//            } else {
//                if(statistic.getFilteredCollections().size() == 2) {
//                    return isSwedish ? 
//                           CommonText.getInstance().getSearchTextSvMap(paleontology) :
//                           CommonText.getInstance().getSearchTextEnMap(paleontology); 
//                }
//                return CommonText.getInstance().getSearchDefaultText(isSwedish);
//            }
            
            
//            StringBuilder textBuilder = new StringBuilder();
//            textBuilder.append(CommonText.getInstance().getSearchCollectionDefaultText1(isSwedish));
//            textBuilder.append(isSwedish ? selectedCollection.getSwedishName() : selectedCollection.getName());
//            textBuilder.append(CommonText.getInstance().getSearchCollectionDefaultText2(isSwedish));
//            return textBuilder.toString().trim();
      
    }

    public boolean isIsAllCollections() {
        return isAllCollections;
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

    public Map<String, String> getCollectionFilters() {
        collectionFilters = new HashMap();
        
        filters.entrySet().stream()
                .filter(e -> !e.getKey().contains(CommonText.getInstance().getHighTxKey())) 
                .filter(e -> !e.getKey().equals(CommonText.getInstance().getCollectionCodeKey())) 
                .forEach(e -> {
                    collectionFilters.put(e.getKey(), e.getValue());
                }); 
        log.info("what... {} -- {}", filters, collectionFilters);

        return collectionFilters;
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
        return selectedCollection == null ? "" : isSwedish ? 
                selectedCollection.getSwedishName() : selectedCollection.getShortName();
    }
    
    public String getSelectedCollectionFullName() {
        return selectedCollection == null ? "" : selectedCollection.getName(); 
    }
    
    public String getSelectedCollectionCode() {
        return selectedCollection == null ? "" : selectedCollection.getCode();
    }
    
    public boolean getSelectedCollection() {
        return selectedCollection != null;
    }
 
    public String getResultHeaderSummary() {
        if (resultHeader.isMapView()) {
            return HelpClass.getInstance()
                    .buildResultHeaderSummaryForMapView(statistic.getFilteredTotalMaps(), isSwedish);
        }
        if (resultHeader.isImageView()) {
            return emptySpace;
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
        log.info("pagingSearch : {}", start);

        result = solr.searchWithFilter(getSearchText(), queries, start, numDisplay,
                CommonText.getInstance().getCreatedDate(), false);
        resultList = result.getSolrData();
    }

    public boolean isOverMaxDownloadSize() {
        return !selectedRecords.isEmpty() ? selectedRecords.size() > 1000 : totalResult > 1000;
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

    public void changeLanguage(boolean isSwedish) {
        this.isSwedish = isSwedish;
        appendQuery();
    }
 
    private void clearAdvanceData() {
        freeText = null;
        result = null;
        resultList = new ArrayList<>();
        exportDataSet = new ArrayList<>();
        selectedAll = false;

        queryDataList = new ArrayList<>();
        queryDataList.add(new QueryData("", "contains", CommonText.getInstance().getTextSearch(), "", false));
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
        showCollectionStatisticData = false;
        pbDataset = null;
//    selectedInstitution = null;
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
                CommonText.getInstance().getTextField(), CommonText.getInstance().getEmptyString(), false);
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

    public boolean isShowCollectionStatisticData() {
        return showCollectionStatisticData;
    }

    public String getPbDataset() {
        return pbDataset;
    }
     
     
    Predicate<Entry<String, String>> notCoordinateKey
            = e -> !e.getKey().equals(CommonText.getInstance().getCoordinateKey());
}
