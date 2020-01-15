package se.nrm.dina.web.portal.logic.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class GalleriaHelperTest {
  
  private GalleriaHelper instance;
   
  private final static String ALL = "all";
  private final static String LABEL = "Label";
  private final static String DORSAL = "Dorsal";
  private final static String VENTRAL = "Ventral";
  private final static String LATERAL = "Lateral";
  private final static String FRONTAL = "Frontal";
  private final static String CAUDAL = "Caudal";
  
  private final static String ABDOMEN = "Abdomen";
  private final static String FACE = "Face";
  private final static String GENITALIA = "Genitalia";
  private final static String WINGS = "Wings";
  private final static String HEAD = "Head";
  private final static String LEGS = "Legs";
  private final static String LOBE = "Lobe";
  private final static String PRONOTUM = "Pronotum";
  private final static String VARI = "Vari";
  private final static String MESONOTUM = "Mesonotum";
  private final static String POSTERIOR = "Posterior";
  private final static String PALPS = "Palps";
  private final static String TARSI = "Tarsi";
  private final static String LABRUM = "Labrum";
  private final static String NOTUM = "Notum";
  private final static String MOUTH = "Mouth";
  private final static String CHELICERAE = "Chelicerae";
  
  private final static String MALE = "/male/";
  private final static String FEMALE = "female";

  private final static String LARVA = "larva";
  private final static String ADULT = "adult";
  
  private final static String SOMETEXT = "some text";
  
  public GalleriaHelperTest() {
  }
 
  @Before
  public void setUp() {
    instance = GalleriaHelper.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class GalleriaHelper.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    assertNotNull(instance);
  }

  /**
   * Test of setAllViewList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllViewList() {
    System.out.println("setAllViewList");
    List<String> viewList = new ArrayList<>(); 
    instance.setAllViewList(viewList); 
    assertEquals(viewList.size(), 7);
    assertTrue(viewList.contains(ALL));
    assertTrue(viewList.contains(LABEL));
    assertTrue(viewList.contains(DORSAL));
    assertTrue(viewList.contains(VENTRAL));
    assertTrue(viewList.contains(FRONTAL));
    assertTrue(viewList.contains(CAUDAL));
    assertTrue(viewList.contains(LATERAL)); 
  }

  @Test
  public void testSetAllViewList1() {
    System.out.println("setAllViewList");
    List<String> viewList = new ArrayList<>(); 
    viewList.add(SOMETEXT);
      
    instance.setAllViewList(viewList); 
    assertEquals(viewList.size(), 7);
    assertTrue(viewList.contains(ALL));
    assertTrue(viewList.contains(LABEL));
    assertTrue(viewList.contains(DORSAL));
    assertTrue(viewList.contains(VENTRAL));
    assertTrue(viewList.contains(FRONTAL));
    assertTrue(viewList.contains(CAUDAL));
    assertTrue(viewList.contains(LATERAL)); 
    assertFalse(viewList.contains(SOMETEXT)); 
  }
  
  /**
   * Test of setAllPartsList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllPartsList() {
    System.out.println("setAllPartsList");
    List<String> partsList = new ArrayList<>(); 
    instance.setAllPartsList(partsList); 
    
    assertEquals(partsList.size(), 18);
    assertTrue(partsList.contains(ALL));
    assertTrue(partsList.contains(ABDOMEN));
    assertTrue(partsList.contains(FACE));
    assertTrue(partsList.contains(GENITALIA));
    assertTrue(partsList.contains(WINGS));
    assertTrue(partsList.contains(HEAD));
    assertTrue(partsList.contains(LEGS)); 
    assertTrue(partsList.contains(LOBE));  
    assertTrue(partsList.contains(PRONOTUM));
    assertTrue(partsList.contains(VARI));
    assertTrue(partsList.contains(MESONOTUM));
    assertTrue(partsList.contains(POSTERIOR));
    assertTrue(partsList.contains(PALPS));
    assertTrue(partsList.contains(TARSI));
    assertTrue(partsList.contains(LABRUM)); 
    assertTrue(partsList.contains(NOTUM)); 
    assertTrue(partsList.contains(MOUTH)); 
    assertTrue(partsList.contains(CHELICERAE)); 
  }
  
  @Test
  public void testSetAllPartsList1() {
    System.out.println("setAllPartsList");
    List<String> partsList = new ArrayList<>();  
    partsList.add(SOMETEXT);
    
    instance.setAllPartsList(partsList); 
    
    assertEquals(partsList.size(), 18);
    assertTrue(partsList.contains(ALL));
    assertTrue(partsList.contains(ABDOMEN));
    assertTrue(partsList.contains(FACE));
    assertTrue(partsList.contains(GENITALIA));
    assertTrue(partsList.contains(WINGS));
    assertTrue(partsList.contains(HEAD));
    assertTrue(partsList.contains(LEGS)); 
    assertTrue(partsList.contains(LOBE));  
    assertTrue(partsList.contains(PRONOTUM));
    assertTrue(partsList.contains(VARI));
    assertTrue(partsList.contains(MESONOTUM));
    assertTrue(partsList.contains(POSTERIOR));
    assertTrue(partsList.contains(PALPS));
    assertTrue(partsList.contains(TARSI));
    assertTrue(partsList.contains(LABRUM)); 
    assertTrue(partsList.contains(NOTUM)); 
    assertTrue(partsList.contains(MOUTH)); 
    assertTrue(partsList.contains(CHELICERAE)); 
    
    assertFalse(partsList.contains(SOMETEXT)); 
  }

  /**
   * Test of setAllSexList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllSexList() {
    System.out.println("setAllSexList");
    List<String> sexList = new ArrayList<>(); 
    instance.setAllSexList(sexList); 
    
    assertEquals(sexList.size(), 3);
    assertTrue(sexList.contains(ALL));
    assertTrue(sexList.contains(MALE));
    assertTrue(sexList.contains(FEMALE)); 
  }
  
  @Test
  public void testSetAllSexList1() {
    System.out.println("setAllSexList");
    List<String> sexList = new ArrayList<>(); 
    sexList.add(SOMETEXT);
    
    instance.setAllSexList(sexList); 
    
    assertEquals(sexList.size(), 3);
    assertTrue(sexList.contains(ALL));
    assertTrue(sexList.contains(MALE));
    assertTrue(sexList.contains(FEMALE)); 
    
    assertFalse(sexList.contains(SOMETEXT)); 
  }

  /**
   * Test of setAllStagesList method, of class GalleriaHelper.
   */
  @Test
  public void testSetAllStagesList() {
    System.out.println("setAllStagesList");
    List<String> stagesList = new ArrayList<>(); 
    instance.setAllStagesList(stagesList); 
    
    assertEquals(stagesList.size(), 3);
    assertTrue(stagesList.contains(ALL));
    assertTrue(stagesList.contains(LARVA));
    assertTrue(stagesList.contains(ADULT)); 
  }
  
  @Test
  public void testSetAllStagesList1() {
    System.out.println("setAllStagesList");
    List<String> stagesList = new ArrayList<>(); 
    stagesList.add(SOMETEXT);
    
    instance.setAllStagesList(stagesList); 
    
    assertEquals(stagesList.size(), 3);
    assertTrue(stagesList.contains(ALL));
    assertTrue(stagesList.contains(LARVA));
    assertTrue(stagesList.contains(ADULT)); 
    assertFalse(stagesList.contains(SOMETEXT)); 
  }

  @Test
  public void testViewOptionChanged() {
    System.out.println("viewOptionChanged");
    List<String> selectedViews = new ArrayList<>(); 
    List<String> viewList = new ArrayList<>(); 
    List<String> partsList = new ArrayList<>(); 
    List<String> sexList = new ArrayList<>(); 
    List<String> stagesList = new ArrayList<>(); 
  
    instance.viewOptionChanged(selectedViews, viewList, partsList, sexList, stagesList);
    assertEquals(selectedViews.size(), 0);
    assertTrue(selectedViews.isEmpty());  
  }
  
  /**
   * Test of viewOptionChanged method, of class GalleriaHelper.
   */
  @Test
  public void testViewOptionChanged1() {
    System.out.println("viewOptionChanged");
    List<String> selectedViews = new ArrayList<>(); 
    List<String> viewList = new ArrayList<>(); 
    List<String> partsList = new ArrayList<>(); 
    List<String> sexList = new ArrayList<>(); 
    List<String> stagesList = new ArrayList<>(); 
 
    viewList.add(LABEL);
    viewList.add(DORSAL);
    viewList.add(VENTRAL);
    
    partsList.add(HEAD);
    partsList.add(LEGS);
    
    sexList.add(MALE);
    
    stagesList.add(LARVA);
    
    instance.viewOptionChanged(selectedViews, viewList, partsList, sexList, stagesList);
    assertEquals(selectedViews.size(), 7);
    assertTrue(selectedViews.contains(LABEL)); 
    assertTrue(selectedViews.contains(DORSAL)); 
    assertTrue(selectedViews.contains(VENTRAL)); 
    assertTrue(selectedViews.contains(HEAD)); 
    assertTrue(selectedViews.contains(LEGS)); 
    assertTrue(selectedViews.contains(MALE)); 
    assertTrue(selectedViews.contains(LARVA)); 
    assertFalse(selectedViews.contains(ALL)); 
  }
  
  @Test
  public void testViewOptionChanged2() {
    System.out.println("viewOptionChanged");
    List<String> selectedViews = new ArrayList<>(); 
    List<String> viewList = new ArrayList<>(); 
    List<String> partsList = new ArrayList<>(); 
    List<String> sexList = new ArrayList<>(); 
    List<String> stagesList = new ArrayList<>(); 
 
    viewList.add(ALL);
    viewList.add(LABEL);
    viewList.add(DORSAL);
    viewList.add(VENTRAL);
    
    partsList.add(HEAD);
    partsList.add(LEGS);
    
    sexList.add(MALE);
    
    stagesList.add(LARVA);
    
    instance.viewOptionChanged(selectedViews, viewList, partsList, sexList, stagesList);
    assertEquals(selectedViews.size(), 7);
    assertTrue(selectedViews.contains(LABEL)); 
    assertTrue(selectedViews.contains(DORSAL)); 
    assertTrue(selectedViews.contains(VENTRAL)); 
    assertTrue(selectedViews.contains(HEAD)); 
    assertTrue(selectedViews.contains(LEGS)); 
    assertTrue(selectedViews.contains(MALE)); 
    assertTrue(selectedViews.contains(LARVA)); 
    assertFalse(selectedViews.contains(ALL)); 
  }

  /**
   * Test of setSelectedList method, of class GalleriaHelper.
   */
  @Test
  public void testSetSelectedList() {
    System.out.println("setSelectedList");
    List<String> list = new ArrayList<>(); 
    list.add(SOMETEXT);
    
    instance.setSelectedList(list); 
    assertEquals(list.size(), 0);
  }
  
  @Test
  public void testSetSelectedList1() {
    System.out.println("setSelectedList");
    List<String> list = new ArrayList<>(); 
    list.add(ALL); 
    list.add(LABEL);
    list.add(DORSAL);
    list.add(VENTRAL);
    list.add(LATERAL); 
    
    instance.setSelectedList(list); 
    assertEquals(list.size(), 4);
    assertFalse(list.contains(ALL));
  }

  /**
   * Test of hasAll method, of class GalleriaHelper.
   */
  @Test
  public void testHasAll() {
    System.out.println("hasAll");
    List<String> list = new ArrayList<>(); 
    int count = 2; 
    boolean expResult = false;
    boolean result = instance.hasAll(list, count);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testHasAll1() {
    System.out.println("hasAll");
    List<String> list = new ArrayList<>(); 
    list.add(ALL);
    int count = 2; 
    boolean expResult = true;
    boolean result = instance.hasAll(list, count);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testHasAll2() {
    System.out.println("hasAll");
    List<String> list = new ArrayList<>(); 
    list.add(FEMALE);
    list.add(MALE);
    int count = 2; 
    boolean expResult = true;
    boolean result = instance.hasAll(list, count);
    assertEquals(expResult, result); 
  }
  
}
