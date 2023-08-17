package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock; 
import static org.mockito.Mockito.doThrow; 
import static org.mockito.Mockito.when; 
import org.mockito.runners.MockitoJUnitRunner;  
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
//@RunWith(MockitoJUnitRunner.class)    
//@RunWith(PowerMockRunner.class)
public class SolrImageServiceTest {
  
  private SolrImageService instance;
   
  @Mock
  private SolrClient solr;
  @Mock
  private QueryResponse response;
  
  @Mock
  private InitialProperties properties;
  
  private final String imageThubPath = "http://morphbank/123";
  
  public SolrImageServiceTest() {
  }

  @Before
  public void setUp() throws SolrServerException, IOException { 
    
    SolrDocumentList list = new SolrDocumentList();
    list.setNumFound(20);
    SolrDocument document = new SolrDocument();
    List<String> strings = new ArrayList<>();
    strings.add("123/body");
    document.addField(CommonText.getInstance().getImageView(), strings);
    list.add(document);
 
    when(response.getResults()).thenReturn(list);
    when(solr.query(any(SolrQuery.class))).thenReturn(response); 
    when(properties.getMorphbankThumbPath()).thenReturn(imageThubPath);
    instance = new SolrImageService(solr, properties);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
//  @Test(expected = NullPointerException.class)
//  public void testDefaultConstructor() {
//    instance = new SolrImageService();
//    instance.getImagesByMorphbankId("1234");
//  }

  /**
   * Test of getImageTotalCount method, of class SolrImageService. 
   */
//  @Test
  public void testGetImageTotalCount()  {
    System.out.println("getImageTotalCount");
    String searchQueryText = "tx:taxon";
    Map<String, String> filters = new HashMap<>(); 
    filters.put("cn", "cn123");
    int expResult = 20;
    
    int result = instance.getImageTotalCount(searchQueryText, filters);
    assertEquals(expResult, result); 
  }
  
//  @Test
  public void testGetImageTotalCountWithIOException() throws SolrServerException, IOException  {
    System.out.println("getImageTotalCount");
    String searchQueryText = "tx:taxon";
    Map<String, String> filters = new HashMap<>(); 
    filters.put("cn", "cn123");
    int expResult = 0;
     
    doThrow(new IOException()).when(solr).query(any(SolrQuery.class));
    int result = instance.getImageTotalCount(searchQueryText, filters);
    assertEquals(expResult, result);  
  }
  
//  @Test
  public void testGetImageTotalCountWithSolrServerException() throws SolrServerException, IOException  {
    System.out.println("getImageTotalCount");
    String searchQueryText = "tx:taxon";
    Map<String, String> filters = new HashMap<>(); 
    filters.put("cn", "cn123");
    int expResult = 0;
     
    Throwable t = new Throwable();
    doThrow(new SolrServerException(t)).when(solr).query(any(SolrQuery.class));
    int result = instance.getImageTotalCount(searchQueryText, filters);
    assertEquals(expResult, result);  
  }

  /**
   * Test of getImageList method, of class SolrImageService.
   */
//  @Test
  public void testGetImageList() {
    System.out.println("getImageList");
    
    String searchQueryText = "image:123";
    int start = 0;
    int numPerPage = 10;
    Map<String, String> filters = null;
    List<String> filterList = null;  
    List<ImageModel> result = instance.getImageList(searchQueryText, start, numPerPage, filters, filterList); 
    String imageId = result.get(0).getImageId();
    String view = result.get(0).getMorphBankView();
    assertEquals(1, result.size()); 
//    assertEquals(imageId, "http://morphbank/123?id=123&imgType=thumbs"); 
    assertEquals(view, "body"); 
  }

  /**
   * Test of getImagesByMorphbankId method, of class SolrImageService.
   */
//  @Test
//  public void testGetImagesByMorphbankIdWithEmptyValue() {
//    System.out.println("getImagesByMorphbankId");
//     
//    List<SolrData> dataList = new ArrayList();
//    when(response.getBeans(SolrData.class)).thenReturn(dataList);
//    String morphbankId = "123"; 
//    SolrData expResult = null;
//    SolrData result = instance.getImagesByMorphbankId(morphbankId);
//    assertEquals(expResult, result); 
//  }
//  
//  @Test
//  public void testGetImagesByMorphbankIdWithNull() {
//    System.out.println("getImagesByMorphbankId");
//      
//    when(response.getBeans(SolrData.class)).thenReturn(null);
//    String morphbankId = "123"; 
//    SolrData expResult = null;
//    SolrData result = instance.getImagesByMorphbankId(morphbankId);
//    assertEquals(expResult, result); 
//  }
//  
//  @Test
//  public void testGetImagesByMorphbankId() {
//    System.out.println("getImagesByMorphbankId");
//     
//    List<SolrData> dataList = new ArrayList();
//    SolrData data = new SolrData(); 
//    dataList.add(data);
//    when(response.getBeans(SolrData.class)).thenReturn(dataList);
//    String morphbankId = "123";  
//    SolrData result = instance.getImagesByMorphbankId(morphbankId);
//    assertNotNull(result);  
//    assertEquals(0, result.getThumbs().size());
//    assertEquals(0, result.getJpgs().size());
//  }
  
//  @Test
//  public void testGetImagesByMorphbankIdWithId() {
//    System.out.println("getImagesByMorphbankId");
//     
//    String morphbankId = "123";  
//    String[] morphbankImageIds = new String[3];
//    morphbankImageIds[0] = "897";
//    morphbankImageIds[1] = "758";
//    morphbankImageIds[2] = "376";
//    List<SolrData> dataList = new ArrayList();
//    SolrData data = new SolrData();  
//    data.setMorphbankId(morphbankId);
//    data.setMorphbankImageId(morphbankImageIds); 
//    dataList.add(data);
//    when(response.getBeans(SolrData.class)).thenReturn(dataList);
//    
//    SolrData result = instance.getImagesByMorphbankId(morphbankId);
//    assertNotNull(result);  
//    assertEquals(3, result.getThumbs().size());
//    assertEquals(3, result.getJpgs().size());
//  }
}
