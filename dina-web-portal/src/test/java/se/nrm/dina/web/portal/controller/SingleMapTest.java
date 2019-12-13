package se.nrm.dina.web.portal.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class SingleMapTest {
  
  private SingleMap instance;
  
  public SingleMapTest() {
  }
 
  @Before
  public void setUp() {
    instance = new SingleMap();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getSingledModel method, of class SingleMap.
   */
  @Test
  public void testGetSingledModel() {
    System.out.println("getSingledModel");
    
    String locality = "Tyreso, Sweden";
    
    SolrData data = new SolrData();  
    data.setLatitude(58.2);
    data.setLongitude(18.6);
    data.setLocality(locality);
     
    MapModel result = instance.getSingledModel(data);
    assertNotNull(result); 
    assertEquals(result.getMarkers().size(), 1);
    assertEquals(locality, result.getMarkers().get(0).getTitle());
  }
  
  @Test
  public void testGetSingledModelWithNullData() {
    System.out.println("getSingledModel");
     
    SolrData data = null;   
     
    MapModel result = instance.getSingledModel(data);
    assertNotNull(result);  
    assertTrue(result.getMarkers().isEmpty());
  }

  /**
   * Test of onSingleMarkerSelect method, of class SingleMap.
   */
  @Test
  public void testOnSingleMarkerSelect() {
    System.out.println("onSingleMarkerSelect");
    OverlaySelectEvent event = null; 
    instance.onSingleMarkerSelect(event); 
  }
  
}
