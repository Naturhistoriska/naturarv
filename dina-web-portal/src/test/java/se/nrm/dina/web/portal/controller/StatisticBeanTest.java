package se.nrm.dina.web.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith; 
import org.mockito.Mock; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.solr.SolrStatisticService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)   
public class StatisticBeanTest {
  
  private StatisticBean instance;
  
  @Mock
  private SolrStatisticService solr;
  private StatisticData data;  
  
  private int total;
  private int totalDnas;
  private int totalImages;
  private int totalMaps;
  private int totalInSweden;
  private int totalType;
  private List<CollectionData> collections;
  
  public StatisticBeanTest() {
  }
 
  @Before
  public void setUp() {
    total = 550;
    total = 168;
    totalImages = 320;
    totalMaps = 480;
    totalInSweden = 500;
    totalType = 328; 
    
    collections = new ArrayList<>();
    collections.add(new CollectionData("123", "collection1", 152));
    collections.add(new CollectionData("567", "collection2", 63));
    collections.add(new CollectionData("789", "collection3", 89));
    collections.add(new CollectionData("4", "collection4", 58));
    
    data = new StatisticData(total, totalDnas, totalImages, totalMaps, 
                             totalInSweden, totalType, collections);
   
    when(solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null)).thenReturn(data);
    instance = new StatisticBean(solr);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test(expected = NullPointerException.class)
  public void testDefaultConstructor() {
    instance = new StatisticBean();
    assertNotNull(instance);  
    
    instance.init();
  }
  

  /**
   * Test of init method, of class StatisticBean.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null); 
  }

  /**
   * Test of changeLanguage method, of class StatisticBean.
   */
  @Test
  public void testChangeLanguage() {
    System.out.println("changeLanguage");
    boolean isSwedish = false; 
    instance.changeLanguage(isSwedish);  
  }

  /**
   * Test of resetData method, of class StatisticBean.
   */;
  @Test
  public void testResetData() {
    System.out.println("resetData");
     
    String text = "text:sweden";
    Map<String, String> queries = new HashMap();
    
    when(solr.getStatisticData(text, queries)).thenReturn(data);
    instance.resetData(text, queries); 
    verify(solr, times(1)).getStatisticData(text, queries); 
  }

  /**
   * Test of resetAllData method, of class StatisticBean.
   */
  @Test
  public void testResetAllData1() {
    System.out.println("resetAllData"); 
    
    instance.resetAllData(); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null);    
  }
  
  @Test
  public void testResetAllData2() {
    System.out.println("resetAllData"); 
    
    testInit();
    instance.resetAllData(); 
    verifyZeroInteractions(solr);    
  }


  /**
   * Test of getFilteredCollections method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredCollections1() {
    System.out.println("getFilteredCollections");  
    List<CollectionData> result = instance.getFilteredCollections();
    assertNotNull(result); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null);    
  }
  
  @Test
  public void testGetFilteredCollections2() {
    System.out.println("getFilteredCollections");  
    
    instance.resetAllData();
    List<CollectionData> result = instance.getFilteredCollections();
    assertNotNull(result); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null);    
  }

  /**
   * Test of getCollections method, of class StatisticBean.
   */
  @Test
  public void testGetCollections1() {
    System.out.println("getCollections"); 
    
    List<CollectionData> result = instance.getCollections();
    assertNotNull(result); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null);   
  }
  
  @Test
  public void testGetCollections2() {
    System.out.println("getCollections"); 
    
    instance.init();
    List<CollectionData> result = instance.getCollections();
    assertNotNull(result); 
    verify(solr, times(1)).getStatisticData(CommonText.getInstance().getWildSearchText(), null);   
  }

  /**
   * Test of getTotalRecords method, of class StatisticBean.
   */
  @Test
  public void testGetTotalRecords1() {
    System.out.println("getTotalRecords"); 
 
    int result = instance.getTotalRecords();
    assertEquals(total, result); 
  }
  
  @Test
  public void testGetTotalRecords2() {
    System.out.println("getTotalRecords"); 
 
    instance.init();
    int result = instance.getTotalRecords();
    assertEquals(total, result); 
  }

  /**
   * Test of getFilteredTotalDnas method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredTotalDnas1() {
    System.out.println("getFilteredTotalDnas"); 
 
    int result = instance.getFilteredTotalDnas();
    assertEquals(totalDnas, result); 
  }
  
  @Test
  public void testGetFilteredTotalDnas2() {
    System.out.println("getFilteredTotalDnas"); 
 
    instance.resetAllData();
    int result = instance.getFilteredTotalDnas();
    assertEquals(totalDnas, result); 
  }


  /**
   * Test of getTotalDnas method, of class StatisticBean.
   */
  @Test
  public void testGetTotalDnas1() {
    System.out.println("getTotalDnas"); 
     
    int result = instance.getTotalDnas();
    assertEquals(totalDnas, result); 
  }

  @Test
  public void testGetTotalDnas2() {
    System.out.println("getTotalDnas"); 
    
    instance.init(); 
    int result = instance.getTotalDnas();
    assertEquals(totalDnas, result); 
  }
  /**
   * Test of getFilteredTotalImages method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredTotalImages1() {
    System.out.println("getFilteredTotalImages"); 
 
    int result = instance.getFilteredTotalImages();
    assertEquals(totalImages, result); 
  }
  
  @Test
  public void testGetFilteredTotalImages2() {
    System.out.println("getFilteredTotalImages"); 
 
    instance.resetAllData();
    int result = instance.getFilteredTotalImages();
    assertEquals(totalImages, result); 
  }

  /**
   * Test of getTotalImages method, of class StatisticBean.
   */
  @Test
  public void testGetTotalImages1() {
    System.out.println("getTotalImages");  
    int result = instance.getTotalImages();
    assertEquals(totalImages, result); 
  }

  @Test
  public void testGetTotalImages2() {
    System.out.println("getTotalImages");  
    instance.init();
    int result = instance.getTotalImages();
    assertEquals(totalImages, result); 
  }

  /**
   * Test of getFilteredTotalMaps method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredTotalMaps1() {
    System.out.println("getFilteredTotalMaps");  
    int result = instance.getFilteredTotalMaps();
    assertEquals(totalMaps, result); 
  }

  @Test
  public void testGetFilteredTotalMaps2() {
    System.out.println("getFilteredTotalMaps");  
    instance.resetAllData();
    int result = instance.getFilteredTotalMaps();
    assertEquals(totalMaps, result); 
  } 
  
  
  /**
   * Test of getTotalMaps method, of class StatisticBean.
   */
  @Test
  public void testGetTotalMaps1() {
    System.out.println("getTotalMaps");  
    int result = instance.getTotalMaps();
    assertEquals(totalMaps, result); 
  }
  
  
  @Test
  public void testGetTotalMaps2() {
    System.out.println("getTotalMaps");  
    instance.init();
    int result = instance.getTotalMaps();
    assertEquals(totalMaps, result); 
  }

  /**
   * Test of getFilteredTotalInSweden method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredTotalInSweden1() {
    System.out.println("getFilteredTotalInSweden"); 
    int result = instance.getFilteredTotalInSweden();
    assertEquals(totalInSweden, result); 
  }
  
  @Test
  public void testGetFilteredTotalInSweden2() {
    System.out.println("getFilteredTotalInSweden"); 
    instance.resetAllData();
    int result = instance.getFilteredTotalInSweden();
    assertEquals(totalInSweden, result); 
  }
  
  /**
   * Test of getTotalInSweden method, of class StatisticBean.
   */
  @Test
  public void testGetTotalInSweden1() {
    System.out.println("getTotalInSweden"); 
    int result = instance.getTotalInSweden();
    assertEquals(totalInSweden, result); 
  }
  
  @Test
  public void testGetTotalInSweden2() {
    System.out.println("getTotalInSweden"); 
    
    instance.init();
    int result = instance.getTotalInSweden();
    assertEquals(totalInSweden, result); 
  }

  /**
   * Test of getFilteredTotalType method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredTotalType1() {
    System.out.println("getFilteredTotalType"); 
    int result = instance.getFilteredTotalType();
    assertEquals(totalType, result); 
  }
 
  @Test
  public void testGetFilteredTotalType2() {
    System.out.println("getFilteredTotalType"); 
    instance.resetAllData();
    int result = instance.getFilteredTotalType();
    assertEquals(totalType, result); 
  }

  /**
   * Test of getTotalType method, of class StatisticBean.
   */
  @Test
  public void testGetTotalType1() {
    System.out.println("getTotalType"); 
    int result = instance.getTotalType();
    assertEquals(totalType, result); 
  }

  @Test
  public void testGetTotalType2() {
    System.out.println("getTotalType"); 
    
    instance.init();
    int result = instance.getTotalType();
    assertEquals(totalType, result); 
  }
 
  /**
   * Test of getFilteredInstitutions method, of class StatisticBean.
   */
  @Test
  public void testGetFilteredInstitutions1() {
    System.out.println("getFilteredInstitutions");  
    Map<String, Integer> result = instance.getFilteredInstitutions();
    assertNotNull(result); 
    assertEquals(result.size(), 2); 
  }
  
  @Test
  public void testGetFilteredInstitutions2() {
    System.out.println("getFilteredInstitutions");  
    
    instance.resetAllData();
    Map<String, Integer> result = instance.getFilteredInstitutions();
    assertNotNull(result); 
    assertEquals(result.size(), 2); 
  }

  /**
   * Test of getInstitutions method, of class StatisticBean.
   */
  @Test
  public void testGetInstitutions1() {
    System.out.println("getInstitutions"); 
    Map<String, Integer> result = instance.getInstitutions();
    assertNotNull(result); 
    assertEquals(result.size(), 2); 
  }
  
  @Test
  public void testGetInstitutions2() {
    System.out.println("getInstitutions"); 
    
    instance.init();
    Map<String, Integer> result = instance.getInstitutions();
    assertNotNull(result); 
    assertEquals(result.size(), 2); 
  }
}
