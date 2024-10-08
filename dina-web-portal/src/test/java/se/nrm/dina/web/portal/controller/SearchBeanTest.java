package se.nrm.dina.web.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.event.SelectEvent;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class) 
public class SearchBeanTest {
  
  @Mock
  private SolrService solr;
  @Mock
  private Navigator navigator;
  @Mock
  private PagingNavigation paging;
  @Mock
  private ResultHeader resultHeader;
  @Mock
  private StatisticBean statistic;
  @Mock
  private GeoHashMap geo;
  @Mock
  private GalleriaBean galleria;
  @Mock
  private InitialProperties properties;
  
//  @Mock
//  private StringBuffer url; // +setter

  private SearchBean instance;
  private SolrResult result;
  private List<SolrData> solrData;
  
  public SearchBeanTest() {
  }
 
  @Before
  public void setUp() {
    solrData = new ArrayList();
    result = new SolrResult(25, solrData);
    instance = new SearchBean(solr, navigator, paging, resultHeader, statistic, geo, galleria, properties); 
  }
  
  @After
  public void tearDown() {
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new SearchBean();
    instance.all(); 
  }

  /**
   * Test of init method, of class SearchBean.
   */
//  @Test
//  public void testInit() {
//    System.out.println("init"); 
//    instance.init(); 
//    assertTrue(instance.getFreeText() == null);
//  }

  /**
   * Test of all method, of class SearchBean.
   */
  @Test
  public void testAll() {
    System.out.println("all"); 
    
    when(solr.searchAll(0, 10)).thenReturn(result);
    instance.all(); 
    assertEquals(instance.getTotalResult(), 25);
    verify(navigator, times(1)).gotoResults(); 
    verify(statistic, times(1)).resetAllData();
    verify(resultHeader, times(1)).setSimpleView();
  }

  /**
   * Test of simpleSearch method, of class SearchBean.
   */
  @Test
  public void testSimpleSearch() {
    System.out.println("simpleSearch"); 
    instance.simpleSearch(); 
  }

  /**
   * Test of searchWithSingleFilter method, of class SearchBean.
   */
//  @Test
  public void testSearchWithSingleFilter() {
    System.out.println("searchWithSingleFilter");
    String filterKey = "";
    SearchBean instance = new SearchBean();
    instance.searchWithSingleFilter(filterKey);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of searchCollectionWithSingleFilter method, of class SearchBean.
   */
//  @Test
  public void testSearchCollectionWithSingleFilter() {
    System.out.println("searchCollectionWithSingleFilter");
    CollectionData collection = null;
    SearchBean instance = new SearchBean();
    instance.searchCollectionWithSingleFilter(collection);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

 

  /**
   * Test of searchDataWithFilter method, of class SearchBean.
   */
//  @Test
  public void testSearchDataWithFilter() {
    System.out.println("searchDataWithFilter");
    String filterKey = "";
    SearchBean instance = new SearchBean();
    instance.searchDataWithFilter(filterKey);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of searchCollectionWithFilter method, of class SearchBean.
   */
//  @Test
  public void testSearchCollectionWithFilter() {
    System.out.println("searchCollectionWithFilter");
    CollectionData collection = null;
    SearchBean instance = new SearchBean();
    instance.searchCollectionWithFilter(collection);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
 
  /**
   * Test of removeFilter method, of class SearchBean.
   */
//  @Test
  public void testRemoveFilter() {
    System.out.println("removeFilter");
    String key = "";
    String value = "";
    SearchBean instance = new SearchBean();
    instance.removeFilter(key, value);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeAllQueries method, of class SearchBean.
   */
//  @Test
  public void testRemoveAllQueries() {
    System.out.println("removeAllQueries");
    SearchBean instance = new SearchBean();
    instance.removeAllQueries();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of queryComplete method, of class SearchBean.
   */
//  @Test
  public void testQueryComplete() {
    System.out.println("queryComplete");
    QueryData data = null;
    SearchBean instance = new SearchBean();
    List<String> expResult = null;
    List<String> result = instance.queryComplete(data);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of advanceSearch method, of class SearchBean.
   */
//  @Test
  public void testAdvanceSearch() {
    System.out.println("advanceSearch");
    SearchBean instance = new SearchBean();
    instance.advanceSearch();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of nextPage method, of class SearchBean.
   */
//  @Test
  public void testNextPage() {
    System.out.println("nextPage");
    SearchBean instance = new SearchBean();
    instance.nextPage();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of previousPage method, of class SearchBean.
   */
//  @Test
  public void testPreviousPage() {
    System.out.println("previousPage");
    SearchBean instance = new SearchBean();
    instance.previousPage();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of firstPage method, of class SearchBean.
   */
//  @Test
  public void testFirstPage() {
    System.out.println("firstPage");
    SearchBean instance = new SearchBean();
    instance.firstPage();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of lastPage method, of class SearchBean.
   */
//  @Test
  public void testLastPage() {
    System.out.println("lastPage");
    SearchBean instance = new SearchBean();
    instance.lastPage();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changePage method, of class SearchBean.
   */
//  @Test
  public void testChangePage() {
    System.out.println("changePage");
    int pageNumber = 0;
    SearchBean instance = new SearchBean();
    instance.changePage(pageNumber);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeNumDisplay method, of class SearchBean.
   */
//  @Test
  public void testChangeNumDisplay() {
    System.out.println("changeNumDisplay");
    SearchBean instance = new SearchBean();
    instance.changeNumDisplay();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of showOneDetail method, of class SearchBean.
   */
//  @Test
  public void testShowOneDetail() {
    System.out.println("showOneDetail");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.showOneDetail(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeone method, of class SearchBean.
   */
//  @Test
  public void testRemoveone() {
    System.out.println("removeone");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.removeone(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeAllSelectedRecords method, of class SearchBean.
   */
//  @Test
  public void testRemoveAllSelectedRecords() {
    System.out.println("removeAllSelectedRecords");
    SearchBean instance = new SearchBean();
    instance.removeAllSelectedRecords();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of backToListView method, of class SearchBean.
   */
//  @Test
  public void testBackToListView() {
    System.out.println("backToListView");
    SearchBean instance = new SearchBean();
    instance.backToListView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of gotoSimpleView method, of class SearchBean.
   */
//  @Test
  public void testGotoSimpleView() {
    System.out.println("gotoSimpleView");
    SearchBean instance = new SearchBean();
    instance.gotoSimpleView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of gotoDetailView method, of class SearchBean.
   */
//  @Test
  public void testGotoDetailView() {
    System.out.println("gotoDetailView");
    SearchBean instance = new SearchBean();
    instance.gotoDetailView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of gotoSelectedView method, of class SearchBean.
   */
//  @Test
  public void testGotoSelectedView() {
    System.out.println("gotoSelectedView");
    SearchBean instance = new SearchBean();
    instance.gotoSelectedView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of showMapView method, of class SearchBean.
   */
//  @Test
  public void testShowMapView() {
    System.out.println("showMapView");
    SearchBean instance = new SearchBean();
    instance.showMapView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of listMapData method, of class SearchBean.
   */
//  @Test
//  public void testListMapData() {
//    System.out.println("listMapData");
//    SearchBean instance = new SearchBean();
//    instance.listMapData();
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of showImageView method, of class SearchBean.
   */
//  @Test
  public void testShowImageView() {
    System.out.println("showImageView");
    SearchBean instance = new SearchBean();
    instance.showImageView();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of advanceClear method, of class SearchBean.
   */
//  @Test
  public void testAdvanceClear() {
    System.out.println("advanceClear");
    SearchBean instance = new SearchBean();
    instance.advanceClear();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of openAdvanceSearch method, of class SearchBean.
   */
//  @Test
  public void testOpenAdvanceSearch() {
    System.out.println("openAdvanceSearch");
    SearchBean instance = new SearchBean();
    instance.openAdvanceSearch();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of handleTypeStatusSelect method, of class SearchBean.
   */
//  @Test
  public void testHandleTypeStatusSelect() {
    System.out.println("handleTypeStatusSelect");
    SearchBean instance = new SearchBean();
    instance.handleTypeStatusSelect();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of handleDateSelect method, of class SearchBean.
   */
//  @Test
  public void testHandleDateSelect() {
    System.out.println("handleDateSelect");
    SelectEvent event = null;
    SearchBean instance = new SearchBean();
    instance.handleDateSelect(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMonthList method, of class SearchBean.
   */
//  @Test
  public void testGetMonthList() {
    System.out.println("getMonthList");
    SearchBean instance = new SearchBean();
    List<SelectItem> expResult = null;
    List<SelectItem> result = instance.getMonthList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of closeAdvanceSearch method, of class SearchBean.
   */
//  @Test
  public void testCloseAdvanceSearch() {
    System.out.println("closeAdvanceSearch");
    SearchBean instance = new SearchBean();
    instance.closeAdvanceSearch();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of operationChange method, of class SearchBean.
   */
//  @Test
  public void testOperationChange() {
    System.out.println("operationChange");
    QueryData data = null;
    int index = 0;
    SearchBean instance = new SearchBean();
    instance.operationChange(data, index);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of startDayList method, of class SearchBean.
   */
//  @Test
  public void testStartDayList() {
    System.out.println("startDayList");
    int index = 0;
    SearchBean instance = new SearchBean();
    List expResult = null;
    List result = instance.startDayList(index);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of endDayList method, of class SearchBean.
   */
//  @Test
  public void testEndDayList() {
    System.out.println("endDayList");
    int index = 0;
    SearchBean instance = new SearchBean();
    List expResult = null;
    List result = instance.endDayList(index);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeStartMonth method, of class SearchBean.
   */
//  @Test
  public void testChangeStartMonth() {
    System.out.println("changeStartMonth");
    SearchBean instance = new SearchBean();
    instance.changeStartMonth();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeEndMonth method, of class SearchBean.
   */
//  @Test
  public void testChangeEndMonth() {
    System.out.println("changeEndMonth");
    SearchBean instance = new SearchBean();
    instance.changeEndMonth();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeStartDay method, of class SearchBean.
   */
//  @Test
  public void testChangeStartDay() {
    System.out.println("changeStartDay");
    SearchBean instance = new SearchBean();
    instance.changeStartDay();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeEndDay method, of class SearchBean.
   */
//  @Test
  public void testChangeEndDay() {
    System.out.println("changeEndDay");
    SearchBean instance = new SearchBean();
    instance.changeEndDay();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeQueryLine method, of class SearchBean.
   */
//  @Test
  public void testRemoveQueryLine() {
    System.out.println("removeQueryLine");
    int index = 0;
    SearchBean instance = new SearchBean();
    instance.removeQueryLine(index);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of addQueryLine method, of class SearchBean.
   */
//  @Test
  public void testAddQueryLine() {
    System.out.println("addQueryLine");
    QueryData qb = null;
    int index = 0;
    SearchBean instance = new SearchBean();
    instance.addQueryLine(qb, index);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of itemSelect method, of class SearchBean.
   */
//  @Test
  public void testItemSelect() {
    System.out.println("itemSelect");
    SelectEvent event = null;
    SearchBean instance = new SearchBean();
    instance.itemSelect(event);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of keyup method, of class SearchBean.
   */
//  @Test
  public void testKeyup() {
    System.out.println("keyup");
    SearchBean instance = new SearchBean();
    instance.keyup();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of freeTextKeyup method, of class SearchBean.
   */
//  @Test
  public void testFreeTextKeyup() {
    System.out.println("freeTextKeyup");
    SearchBean instance = new SearchBean();
    instance.freeTextKeyup();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectOne method, of class SearchBean.
   */
//  @Test
  public void testSelectOne() {
    System.out.println("selectOne");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.selectOne(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of showSingleMap method, of class SearchBean.
   */
//  @Test
  public void testShowSingleMap() {
    System.out.println("showSingleMap");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.showSingleMap(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of closeMap method, of class SearchBean.
   */
//  @Test
  public void testCloseMap() {
    System.out.println("closeMap");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.closeMap(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of displayImages method, of class SearchBean.
   */
////  @Test
//  public void testDisplayImages() {
//    System.out.println("displayImages");
//    SolrData data = null;
//    SearchBean instance = new SearchBean();
//    instance.displayImages(data);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of closeImage method, of class SearchBean.
   */
//  @Test
  public void testCloseImage() {
    System.out.println("closeImage");
    SolrData data = null;
    SearchBean instance = new SearchBean();
    instance.closeImage(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of sortResult method, of class SearchBean.
   */
//  @Test
  public void testSortResult() {
    System.out.println("sortResult");
    SearchBean instance = new SearchBean();
    instance.sortResult();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of export method, of class SearchBean.
   */
//  @Test
  public void testExport() {
    System.out.println("export");
    SearchBean instance = new SearchBean();
    instance.export();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getExportDataSet method, of class SearchBean.
   */
//  @Test
  public void testGetExportDataSet() {
    System.out.println("getExportDataSet");
    SearchBean instance = new SearchBean();
    List<SolrData> expResult = null;
    List<SolrData> result = instance.getExportDataSet();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDefaultText method, of class SearchBean.
   */
//  @Test
  public void testGetDefaultText() {
    System.out.println("getDefaultText");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getDefaultText();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFreeText method, of class SearchBean.
   */
//  @Test
  public void testGetFreeText() {
    System.out.println("getFreeText");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getFreeText();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFreeText method, of class SearchBean.
   */
//  @Test
  public void testSetFreeText() {
    System.out.println("setFreeText");
    String freeText = "";
    SearchBean instance = new SearchBean();
    instance.setFreeText(freeText);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getResultList method, of class SearchBean.
   */
//  @Test
  public void testGetResultList() {
    System.out.println("getResultList");
    SearchBean instance = new SearchBean();
    List<SolrData> expResult = null;
    List<SolrData> result = instance.getResultList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setResultList method, of class SearchBean.
   */
//  @Test
  public void testSetResultList() {
    System.out.println("setResultList");
    List<SolrData> resultList = null;
    SearchBean instance = new SearchBean();
    instance.setResultList(resultList);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalResult method, of class SearchBean.
   */
//  @Test
  public void testGetTotalResult() {
    System.out.println("getTotalResult");
    SearchBean instance = new SearchBean();
    int expResult = 0;
    int result = instance.getTotalResult();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isIsSimpleSearch method, of class SearchBean.
   */
//  @Test
  public void testIsIsSimpleSearch() {
    System.out.println("isIsSimpleSearch");
    SearchBean instance = new SearchBean();
    boolean expResult = false;
    boolean result = instance.isIsSimpleSearch();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getQueries method, of class SearchBean.
   */
//  @Test
  public void testGetQueries() {
    System.out.println("getQueries");
    SearchBean instance = new SearchBean();
    Map<String, String> expResult = null;
    Map<String, String> result = instance.getQueries();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setQueries method, of class SearchBean.
   */
//  @Test
  public void testSetQueries() {
    System.out.println("setQueries");
    Map<String, String> queries = null;
    SearchBean instance = new SearchBean();
    instance.setQueries(queries);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilters method, of class SearchBean.
   */
//  @Test
  public void testGetFilters() {
    System.out.println("getFilters");
    SearchBean instance = new SearchBean();
    Map<String, String> expResult = null;
    Map<String, String> result = instance.getFilters();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getNumDisplay method, of class SearchBean.
   */
//  @Test
  public void testGetNumDisplay() {
    System.out.println("getNumDisplay");
    SearchBean instance = new SearchBean();
    int expResult = 0;
    int result = instance.getNumDisplay();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setNumDisplay method, of class SearchBean.
   */
//  @Test
  public void testSetNumDisplay() {
    System.out.println("setNumDisplay");
    int numDisplay = 0;
    SearchBean instance = new SearchBean();
    instance.setNumDisplay(numDisplay);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSortby method, of class SearchBean.
   */
//  @Test
  public void testGetSortby() {
    System.out.println("getSortby");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getSortby();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSortby method, of class SearchBean.
   */
//  @Test
  public void testSetSortby() {
    System.out.println("setSortby");
    String sortby = "";
    SearchBean instance = new SearchBean();
    instance.setSortby(sortby);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedRecords method, of class SearchBean.
   */
//  @Test
  public void testGetSelectedRecords() {
    System.out.println("getSelectedRecords");
    SearchBean instance = new SearchBean();
    List<SolrData> expResult = null;
    List<SolrData> result = instance.getSelectedRecords();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSelectedCollectionName method, of class SearchBean.
   */
//  @Test
  public void testGetSelectedCollectionName() {
    System.out.println("getSelectedCollectionName");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getSelectedCollectionName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

 

  /**
   * Test of getResultHeaderSummary method, of class SearchBean.
   */
//  @Test
  public void testGetResultHeaderSummary() {
    System.out.println("getResultHeaderSummary");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getResultHeaderSummary();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isSelectedAll method, of class SearchBean.
   */
//  @Test
  public void testIsSelectedAll() {
    System.out.println("isSelectedAll");
    SearchBean instance = new SearchBean();
    boolean expResult = false;
    boolean result = instance.isSelectedAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSelectedAll method, of class SearchBean.
   */
//  @Test
  public void testSetSelectedAll() {
    System.out.println("setSelectedAll");
    boolean selectedAll = false;
    SearchBean instance = new SearchBean();
    instance.setSelectedAll(selectedAll);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isIsSelectedOne method, of class SearchBean.
   */
//  @Test
  public void testIsIsSelectedOne() {
    System.out.println("isIsSelectedOne");
    SearchBean instance = new SearchBean();
    boolean expResult = false;
    boolean result = instance.isIsSelectedOne();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isOverMaxDownloadSize method, of class SearchBean.
   */
//  @Test
  public void testIsOverMaxDownloadSize() {
    System.out.println("isOverMaxDownloadSize");
    SearchBean instance = new SearchBean();
    boolean expResult = false;
    boolean result = instance.isOverMaxDownloadSize();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getQueryDataList method, of class SearchBean.
   */
//  @Test
  public void testGetQueryDataList() {
    System.out.println("getQueryDataList");
    SearchBean instance = new SearchBean();
    List<QueryData> expResult = null;
    List<QueryData> result = instance.getQueryDataList();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMaxDate method, of class SearchBean.
   */
//  @Test
  public void testGetMaxDate() {
    System.out.println("getMaxDate");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getMaxDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getQueryText method, of class SearchBean.
   */
//  @Test
  public void testGetQueryText() {
    System.out.println("getQueryText");
    SearchBean instance = new SearchBean();
    String expResult = "";
    String result = instance.getQueryText();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of isDisableAdvanceSearch method, of class SearchBean.
   */
//  @Test
  public void testIsDisableAdvanceSearch() {
    System.out.println("isDisableAdvanceSearch");
    SearchBean instance = new SearchBean();
    boolean expResult = false;
    boolean result = instance.isDisableAdvanceSearch();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of changeLanguage method, of class SearchBean.
   */
//  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    boolean isSwedish = false;
    SearchBean instance = new SearchBean();
    instance.changeLanguage(isSwedish);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
