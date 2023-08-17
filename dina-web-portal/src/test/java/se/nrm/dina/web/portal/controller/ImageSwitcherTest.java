package se.nrm.dina.web.portal.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext; 
import org.junit.After; 
import org.junit.AfterClass;
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith; 
import org.mockito.Mock;  
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.nrm.dina.web.portal.ContextMocker;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.solr.SolrImageService;

/**
 *
 * @author idali
 */
@RunWith(MockitoJUnitRunner.class)  
public class ImageSwitcherTest {
  
  private ImageSwitcher instance;
  private FacesContext context;   
  
  private static final String IMAGE_ID = "imageId";
   
  private static String morphbankId;
  private static String catalogNumber;
  private static String scientificName;  
  private static List<String> jpgs; 
  private static SolrData data;
  
  private static String[] morphbankImageId;
  
  
  private static String morphbankUrl;
  
  @Mock
  private SolrImageService solr;
  
  @Mock
  private ExternalContext externalContext;  
  
  public ImageSwitcherTest() {
  }
 
      
  @BeforeClass
  public static void setUpClass() {
    morphbankId = "1234";
    catalogNumber = "cat1234";
    scientificName = "taxon";   
    
    morphbankImageId = new String[3];
    morphbankImageId[0] = "123";
    morphbankImageId[1] = "456";
    morphbankImageId[2] = "789";
    
    morphbankUrl = "http://morphbank.nrm.se";
    
    data = new SolrData();
    data.setMorphbankId(morphbankId);
    data.setCatalogNumber(catalogNumber);
    data.setTxFullName(scientificName);
    data.setMorphbankImageId(morphbankImageId);
    data.setImages(morphbankUrl);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    context = ContextMocker.mockFacesContext(); 
    when(context.getExternalContext()).thenReturn(externalContext);
     
    instance = new ImageSwitcher(solr);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }
  
//  @Test(expected = NullPointerException.class)
//  public void testDefaultConstructor() {
//    instance = new ImageSwitcher();
//    assertNotNull(instance);
//    instance.imageSwitch();
//  }

  /**
   * Test of imageSwitch method, of class ImageSwitcher.
   */
//  @Test
//  public void testImageSwitch_0args() {
//    System.out.println("imageSwitch");  
//     
//    Map<String, String> map = new HashMap();
//    map.put(IMAGE_ID, IMAGE_ID);
//    when(externalContext.getRequestParameterMap()).thenReturn(map); 
//   
////    when(solr.getImagesByMorphbankId(any(String.class))).thenReturn(data);
//    instance.imageSwitch(); 
//    assertEquals(instance.getCatalogNumber(), catalogNumber);
//    assertEquals(instance.getScientificName(), scientificName);
//    assertEquals(instance.getJpgs().size(), 3);
////    verify(solr, times(1)).getImagesByMorphbankId(any(String.class)); 
//  }

  /**
   * Test of imageSwitch method, of class ImageSwitcher.
   */
//  @Test
  public void testImageSwitch_SolrData() {
    System.out.println("imageSwitch");

 
    
    instance.imageSwitch(data); 
    assertEquals(3, instance.getThumbs().size());
    assertEquals(3, instance.getJpgs().size());
    assertEquals(instance.getMbid(), morphbankId);
    assertEquals(instance.getCatalogNumber(), catalogNumber);
    assertEquals(instance.getScientificName(), scientificName);
  }

  /**
   * Test of getMbid method, of class ImageSwitcher.
   */
  @Test
  public void testGetMbid() {
    System.out.println("getMbid");  
    instance.imageSwitch(data);
    String result = instance.getMbid();
    assertEquals(morphbankId, result); 
  }

  /**
   * Test of getCatalogNumber method, of class ImageSwitcher.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");  
    instance.imageSwitch(data); 
    String result = instance.getCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of getScientificName method, of class ImageSwitcher.
   */
  @Test
  public void testGetScientificName() {
    System.out.println("getScientificName"); 
    instance.imageSwitch(data);
    String result = instance.getScientificName();
    assertEquals(scientificName, result); 
  }

  /**
   * Test of getThumbs method, of class ImageSwitcher.
   */
  @Test
  public void testGetThumbs() {
    System.out.println("getThumbs"); 
    instance.imageSwitch(data);
    List<String> result = instance.getThumbs();
    assertEquals(3, result.size()); 
  }

  /**
   * Test of getJpgs method, of class ImageSwitcher.
   */
//  @Test
  public void testGetJpgs() {
    System.out.println("getJpgs");  
    instance.imageSwitch(data);
    List<String> result = instance.getJpgs();
    assertEquals(3, result.size()); 
  }
  
}
