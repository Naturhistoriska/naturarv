package se.nrm.dina.web.portal.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*; 
import org.junit.runner.RunWith; 
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when; 
import org.mockito.runners.MockitoJUnitRunner; 
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import se.nrm.dina.web.portal.model.CollectionData;

/**
 *
 * @author idali
 */ 
@RunWith(MockitoJUnitRunner.class)   
public class CollectionsViewTest {
  
  private CollectionsView instance;
  
  @Mock
  private StatisticBean data;
  
  private List<CollectionData> list;
  
  public CollectionsViewTest() {
  } 
  
  @Before
  public void setUp() {
    list = new ArrayList();
    
    when(data.getCollections()).thenReturn(list);
    instance = new CollectionsView(data);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
  @Test
  public void testDefaultConstruction() {
    instance = new CollectionsView();
    assertNotNull(instance);
    assertEquals(-1, instance.getActiveIndex());
  }

  /**
   * Test of init method, of class CollectionsView.
   */
  @Test
  public void testInit() {
    System.out.println("init"); 
    instance.init(); 
  }

  /**
   * Test of onTabChange method, of class CollectionsView.
   */
  @Test
  public void testOnTabChange() {
    System.out.println("onTabChange");
    TabChangeEvent event = mock(TabChangeEvent.class);  
    Tab tab = mock(Tab.class); 
    when(event.getTab()).thenReturn(tab);
  
    instance.onTabChange(event);   
  }

  /**
   * Test of onTabClose method, of class CollectionsView.
   */
  @Test
  public void testOnTabClose() {
    System.out.println("onTabClose");
    TabCloseEvent event = mock(TabCloseEvent.class); 
    instance.onTabClose(event); 
    assertEquals(-1, instance.getActiveIndex());
  }

  /**
   * Test of getCollections method, of class CollectionsView.
   */
  @Test
  public void testGetCollections() {
    System.out.println("getCollections");  
    List<CollectionData> result = instance.getCollections();
    assertNotNull(result); 
    verify(data, times(1)).getCollections();
    
    result = instance.getCollections();
    assertNotNull(result); 
    verifyZeroInteractions(data);
  }

  /**
   * Test of getActiveIndex method, of class CollectionsView.
   */
  @Test
  public void testGetActiveIndex() {
    System.out.println("getActiveIndex"); 
    int expResult = 2;
    instance.setActiveIndex(2);
    int result = instance.getActiveIndex();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setActiveIndex method, of class CollectionsView.
   */
  @Test
  public void testSetActiveIndex() {
    System.out.println("setActiveIndex");
    int activeIndex = 5; 
    instance.setActiveIndex(activeIndex); 
    assertEquals(activeIndex, instance.getActiveIndex());
  }
  
}
