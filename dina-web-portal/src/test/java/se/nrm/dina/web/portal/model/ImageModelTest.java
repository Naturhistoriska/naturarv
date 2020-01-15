package se.nrm.dina.web.portal.model;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class ImageModelTest {
  
  private ImageModel instance;
  
  private final String catalogNumber = "cat1234";
  private final String collectionId = "smtp";
  private final String morphbankId = "123";
  private final String imageId = "567";
  private final String txFullName = "taxon";
  private final String morphBankView = "body";
  
  public ImageModelTest() {
  }
 
  @Before
  public void setUp() {
    instance = new ImageModel(catalogNumber, collectionId, morphbankId, imageId, txFullName, morphBankView);
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getCatalogNumber method, of class ImageModel.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");  
    String result = instance.getCatalogNumber();
    assertEquals(catalogNumber, result); 
  }

  /**
   * Test of getCollectionId method, of class ImageModel.
   */
  @Test
  public void testGetCollectionId() {
    System.out.println("getCollectionId");  
    String result = instance.getCollectionId();
    assertEquals(collectionId, result); 
  }

  /**
   * Test of getMorphbankId method, of class ImageModel.
   */
  @Test
  public void testGetMorphbankId() {
    System.out.println("getMorphbankId"); 
    String result = instance.getMorphbankId();
    assertEquals(morphbankId, result); 
  }

  /**
   * Test of getImageId method, of class ImageModel.
   */
  @Test
  public void testGetImageId() {
    System.out.println("getImageId"); 
    String result = instance.getImageId();
    assertEquals(imageId, result); 
  }

  /**
   * Test of getTxFullName method, of class ImageModel.
   */
  @Test
  public void testGetTxFullName() {
    System.out.println("getTxFullName"); 
    String result = instance.getTxFullName();
    assertEquals(txFullName, result); 
  }

  /**
   * Test of getMorphBankView method, of class ImageModel.
   */
  @Test
  public void testGetMorphBankView() {
    System.out.println("getMorphBankView"); 
    String result = instance.getMorphBankView();
    assertEquals(morphBankView, result); 
  }
  
}
