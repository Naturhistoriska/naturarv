package se.nrm.dina.web.portal.controller;
  
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;   
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrImageService;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class GalleriaBeanTest {
  
  private GalleriaBean instance;
  private final String all = "all";
  
  @Mock
  private SolrImageService solr;
  
  public GalleriaBeanTest() { 
  }
  
  @Before
  public void setUp() {  
    instance = new GalleriaBean(solr);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstructor() {
    instance = new GalleriaBean();
    assertNotNull(instance); 
  }

  /**
   * Test of setImageView method, of class GalleriaBean.
   */
  @Test
  public void testSetImageView() {
    System.out.println("setImageView");
    int totalImages = 20;
    String searchText = "text:sweden";
    Map<String, String> filterMap = null; 
    
    instance.setImageView(totalImages, searchText, filterMap);  
    assertNotNull(instance.getModel());
  }

  /**
   * Test of getModel method, of class GalleriaBean.
   */
  @Test
  public void testGetModel() {
    System.out.println("getModel");
    
    int totalImages = 20;
    String searchText = "text:sweden";
    Map<String, String> filterMap = null; 
    
    instance.setImageView(totalImages, searchText, filterMap);  
    LazyDataModel<ImageModel> result = instance.getModel();
    assertNotNull(result); 
  }

  /**
   * Test of selectViews method, of class GalleriaBean.
   */
  @Test
  public void testSelectViews1() {
    System.out.println("selectViews"); 
    
    List<String> viewList = new ArrayList();  
    instance.setViewList(viewList);
    instance.selectViews(); 
    
    assertTrue(instance.getViewList().isEmpty());   
  }
  
  @Test
  public void testSelectViews2() {
    System.out.println("selectViews"); 
    
    List<String> viewList = new ArrayList();  
    viewList.add(all);
    instance.setViewList(viewList);
    instance.selectViews(); 
    
    assertFalse(instance.getViewList().isEmpty());   
    assertEquals(viewList.size(), 7);
  }
  
  @Test
  public void testSelectViews3() {
    System.out.println("selectViews"); 
    
    List<String> viewList = new ArrayList();   
    viewList.add(all);
    instance.setViewList(viewList);
    instance.selectViews(); 
    
    viewList = new ArrayList();   
    instance.setViewList(viewList);
    instance.selectViews(); 
    assertTrue(instance.getViewList().isEmpty());    
  }
  
  @Test
  public void testSelectViews4() {
    System.out.println("selectViews"); 
    
    List<String> viewList = new ArrayList();   
    viewList.add(all);
    instance.setViewList(viewList);
    instance.selectViews(); 
     
    viewList.remove(viewList.size() - 1);
    instance.setViewList(viewList);
    instance.selectViews(); 
    assertFalse(instance.getViewList().isEmpty());   
    assertEquals(viewList.size(), 5);
  }

  /**
   * Test of selectParts method, of class GalleriaBean.
   */
  @Test
  public void testSelectParts1() {
    System.out.println("selectParts"); 
    List<String> partsList = new ArrayList();  
    instance.setPartsList(partsList);
    instance.selectParts();  
    assertTrue(instance.getPartsList().isEmpty());    
  }
  
  
  @Test
  public void testSelectParts2() {
    System.out.println("selectParts"); 
    List<String> partsList = new ArrayList();  
    partsList.add(all);
    instance.setPartsList(partsList);
    instance.selectParts();  
    assertFalse(instance.getPartsList().isEmpty());     
    assertEquals(partsList.size(), 18); 
  }
  
  @Test
  public void testSelectParts3() {
    System.out.println("selectParts"); 
    List<String> partsList = new ArrayList();  
    partsList.add(all); 
    instance.setPartsList(partsList);
    instance.selectParts();  
    
    partsList = new ArrayList(); 
    instance.setPartsList(partsList);
    instance.selectParts();  
    assertTrue(instance.getPartsList().isEmpty());     
  }
  
  @Test
  public void testSelectParts4() {
    System.out.println("selectParts"); 
    List<String> partsList = new ArrayList();  
    partsList.add(all);
    instance.setPartsList(partsList);
    instance.selectParts();  
    
    partsList.remove(partsList.size() - 1);
    instance.setPartsList(partsList);
    instance.selectParts();  
    assertFalse(instance.getPartsList().isEmpty());   
    assertEquals(partsList.size(), 16); 
  }

  /**
   * Test of selectSexes method, of class GalleriaBean.
   */
  @Test
  public void testSelectSexes1() {
    System.out.println("selectSexes"); 
    
    List<String> sexList = new ArrayList();  
    instance.setSexList(sexList);
    instance.selectSexes();  
    assertTrue(instance.getSexList().isEmpty());     
  }
  
  @Test
  public void testSelectSexes2() {
    System.out.println("selectSexes"); 
    
    List<String> sexList = new ArrayList();  
    sexList.add(all);
    instance.setSexList(sexList);
    instance.selectSexes();  
    assertFalse(instance.getSexList().isEmpty());   
    assertEquals(sexList.size(), 3);  
  }
   
  @Test
  public void testSelectSexes3() {
    System.out.println("selectSexes"); 
    
    List<String> sexList = new ArrayList();  
    sexList.add(all);
    instance.setSexList(sexList);
    instance.selectSexes();  
    
    sexList = new ArrayList();  
    instance.setSexList(sexList);
    instance.selectSexes();
    assertTrue(instance.getSexList().isEmpty());    
  }
  
  @Test
  public void testSelectSexes4() {
    System.out.println("selectSexes"); 
    
    List<String> sexList = new ArrayList();  
    sexList.add(all);
    instance.setSexList(sexList);
    instance.selectSexes();  
    
    sexList.remove(sexList.size() - 1);
    instance.setSexList(sexList);
    instance.selectSexes();
    assertFalse(instance.getSexList().isEmpty());    
    assertEquals(sexList.size(), 1); 
    
    List<String> partsList = new ArrayList();  
    partsList.add(all);
    instance.setPartsList(partsList);
    instance.selectParts();   
  }

  /**
   * Test of selectStages method, of class GalleriaBean.
   */
  @Test
  public void testSelectStages1() {
    System.out.println("selectStages");
   List<String> stagesList = new ArrayList();  
    instance.setStageList(stagesList);
    instance.selectStages();  
    assertTrue(instance.getStageList().isEmpty());    
  }
  
  @Test
  public void testSelectStages2() {
    System.out.println("selectStages");
    
    List<String> stagesList = new ArrayList();  
    instance.setStageList(stagesList);
    stagesList.add(all);
    instance.selectStages();  
    assertFalse(instance.getStageList().isEmpty());    
    assertEquals(stagesList.size(), 3);  
  }
  
  @Test
  public void testSelectStages3() {
    System.out.println("selectStages");
    
    List<String> stagesList = new ArrayList(); 
    stagesList.add(all); 
    instance.setStageList(stagesList);  
    instance.selectStages();  
    
    stagesList = new ArrayList(); 
    instance.setStageList(stagesList);  
    instance.selectStages();  
    assertTrue(instance.getStageList().isEmpty());     
  }
  
  @Test
  public void testSelectStages4() {
    System.out.println("selectStages");
    
    List<String> stagesList = new ArrayList(); 
    stagesList.add(all); 
    instance.setStageList(stagesList);  
    instance.selectStages();  
    
    stagesList.remove(stagesList.size() - 1);
    instance.setStageList(stagesList);  
    instance.selectStages();  
    assertFalse(instance.getStageList().isEmpty());  
    assertEquals(stagesList.size(), 1);  
  }

  /**
   * Test of getViewList method, of class GalleriaBean.
   */
  @Test
  public void testGetViewList() {
    System.out.println("getViewList"); 
     
    List<String> result = instance.getViewList();
    assertNotNull(result); 
  }

  /**
   * Test of setViewList method, of class GalleriaBean.
   */
  @Test
  public void testSetViewList() {
    System.out.println("setViewList");
    List<String> viewList = new ArrayList(); 
    instance.setViewList(viewList); 
    assertNotNull(instance.getViewList()); 
  }

  /**
   * Test of getPartsList method, of class GalleriaBean.
   */
  @Test
  public void testGetPartsList() {
    System.out.println("getPartsList");  
    List<String> result = instance.getPartsList();
    assertNotNull(result); 
  }

  /**
   * Test of setPartsList method, of class GalleriaBean.
   */
  @Test
  public void testSetPartsList() {
    System.out.println("setPartsList");
    List<String> partsList = new ArrayList(); 
    instance.setPartsList(partsList); 
    assertNotNull(instance.getPartsList()); 
  }

  /**
   * Test of getSexList method, of class GalleriaBean.
   */
  @Test
  public void testGetSexList() {
    System.out.println("getSexList");  
    List<String> result = instance.getSexList();
    assertNotNull(result); 
  }

  /**
   * Test of setSexList method, of class GalleriaBean.
   */
  @Test
  public void testSetSexList() {
    System.out.println("setSexList");
    List<String> sexList = new ArrayList(); 
    instance.setSexList(sexList); 
    assertNotNull(instance.getSexList()); 
  }

  /**
   * Test of getStageList method, of class GalleriaBean.
   */
  @Test
  public void testGetStageList() {
    System.out.println("getStageList"); 
    List<String> expResult = new ArrayList();
    List<String> result = instance.getStageList();
    assertNotNull(result); 
  }

  /**
   * Test of setStageList method, of class GalleriaBean.
   */
  @Test
  public void testSetStageList() {
    System.out.println("setStageList");
    List<String> stageList = new ArrayList(); 
    instance.setStageList(stageList); 
    assertNotNull(instance.getStageList());
  }
  
}
