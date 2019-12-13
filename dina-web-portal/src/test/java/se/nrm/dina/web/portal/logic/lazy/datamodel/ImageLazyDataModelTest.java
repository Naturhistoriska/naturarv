package se.nrm.dina.web.portal.logic.lazy.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.SortOrder;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrImageService;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageLazyDataModelTest {
  
  private ImageLazyDataModel instance;
  private List<ImageModel> list;
  private SolrImageService solr;
  private String searchText;
  private Map<String, String> filterMap;
  private List<String> filterList;    
  
  public ImageLazyDataModelTest() {
  }
 
  @Before
  public void setUp() {
    
    solr = mock(SolrImageService.class); 
    list = new ArrayList();
    
    searchText = "text:sweden";
    filterMap = new HashMap();
    filterList = new ArrayList();  
    instance = new ImageLazyDataModel(solr, filterMap, filterList, searchText, 836);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of load method, of class ImageLazyDataModel.
   */
  @Test
  public void testLoad1() {
    System.out.println("load");
    int first = 12;
    int pageSize = 50;
    String sortField = "text";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;  
    int totalCount = 280;
 
    when(solr.getImageList(searchText, first, pageSize, filterMap, filterList)).thenReturn(list); 
    when(solr.getImageTotalCount(any(String.class), eq(null))).thenReturn(totalCount);
    
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertNotNull(result); 
    assertEquals(instance.getRowCount(), 836);
    verify(solr, times(0)).getImageTotalCount(any(String.class), eq(null));
    verify(solr, times(1)).getImageList("+(text:sweden)", first, pageSize, filterMap, filterList);
  }
  
  @Test
  public void testLoad2() {
    System.out.println("load");
    
    filterList = null;  
    instance = new ImageLazyDataModel(solr, filterMap, filterList, searchText, 836);
    
    int first = 12;
    int pageSize = 50;
    String sortField = "text";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;  
    int totalCount = 280;
 
    when(solr.getImageList(searchText, first, pageSize, filterMap, filterList)).thenReturn(list); 
    when(solr.getImageTotalCount(any(String.class), eq(null))).thenReturn(totalCount);
    
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertNotNull(result); 
    assertEquals(instance.getRowCount(), 836);
    verify(solr, times(0)).getImageTotalCount(any(String.class), eq(null));
    verify(solr, times(1)).getImageList("+(text:sweden)", first, pageSize, filterMap, filterList);
  }
  
  @Test
  public void testLoad3() {
    System.out.println("load");
    
    filterMap = null;  
    instance = new ImageLazyDataModel(solr, filterMap, filterList, searchText, 836);
    
    int first = 12;
    int pageSize = 50;
    String sortField = "text";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;  
    int totalCount = 280;
 
    when(solr.getImageList(searchText, first, pageSize, filterMap, filterList)).thenReturn(list); 
    when(solr.getImageTotalCount(any(String.class), eq(null))).thenReturn(totalCount);
    
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertNotNull(result); 
    assertEquals(instance.getRowCount(), 836);
    verify(solr, times(0)).getImageTotalCount(any(String.class), eq(null));
    verify(solr, times(1)).getImageList("+(text:sweden)", first, pageSize, filterMap, filterList);
  }
  
  @Test
  public void testLoad4() {
    System.out.println("load");
    
    filterList.add("map:true");  
    instance = new ImageLazyDataModel(solr, filterMap, filterList, searchText, 836);
    
    int first = 12;
    int pageSize = 50;
    String sortField = "text";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;  
    int totalCount = 280;
 
    when(solr.getImageList(searchText, first, pageSize, filterMap, filterList)).thenReturn(list); 
    when(solr.getImageTotalCount("+(text:sweden) +(morphBankView: (**map:true* ))", filterMap)).thenReturn(totalCount);
    
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertNotNull(result); 
    assertEquals(instance.getRowCount(), totalCount);
    verify(solr, times(1)).getImageTotalCount("+(text:sweden) +(morphBankView: (**map:true* ))", filterMap);
    verify(solr, times(1)).getImageList("+(text:sweden) +(morphBankView: (**map:true* ))", first, pageSize, filterMap, filterList);
  }
  
  @Test
  public void testLoad5() {
    System.out.println("load");
    
    filterMap.put("map", "true");
    instance = new ImageLazyDataModel(solr, filterMap, filterList, searchText, 836);
    
    int first = 12;
    int pageSize = 50;
    String sortField = "text";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;  
    int totalCount = 280;
 
    when(solr.getImageList(searchText, first, pageSize, filterMap, filterList)).thenReturn(list); 
    when(solr.getImageTotalCount("+(text:sweden)", filterMap)).thenReturn(totalCount);
    
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertNotNull(result); 
    assertEquals(instance.getRowCount(), totalCount);
    verify(solr, times(1)).getImageTotalCount("+(text:sweden)", filterMap);
    verify(solr, times(1)).getImageList("+(text:sweden)", first, pageSize, filterMap, filterList);
  }
}
