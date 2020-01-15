package se.nrm.dina.web.portal.model;

import java.util.Date;
import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author idali
 */
public class SolrDataTest {
  
  private SolrData instance;
  
  public SolrDataTest() {
  }
 
  @Before
  public void setUp() {
    instance = new SolrData();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getAccessionNumber method, of class SolrData.
   */
  @Test
  public void testGetAccessionNumber() {
    System.out.println("getAccessionNumber");  
    String result = instance.getAccessionNumber();
    assertEquals(null, result); 
  }

  /**
   * Test of setAccessionNumber method, of class SolrData.
   */
  @Test
  public void testSetAccessionNumber() {
    System.out.println("setAccessionNumber");
    String accessionNumber = "acc123"; 
    instance.setAccessionNumber(accessionNumber); 
    assertEquals(accessionNumber, instance.getAccessionNumber()); 
  }

  /**
   * Test of getAccessionRemarks method, of class SolrData.
   */
  @Test
  public void testGetAccessionRemarks() {
    System.out.println("getAccessionRemarks"); 
    String[] expResult = null;
    String[] result = instance.getAccessionRemarks();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setAccessionRemarks method, of class SolrData.
   */
  @Test
  public void testSetAccessionRemarks() {
    System.out.println("setAccessionRemarks");
    String[] accessionRemarks = new String[1];
    accessionRemarks[0] = "testRemark"; 
    instance.setAccessionRemarks(accessionRemarks); 
    
    assertEquals(1, instance.getAccessionRemarks().length); 
  }

  /**
   * Test of getAuthor method, of class SolrData.
   */
  @Test
  public void testGetAuthor() {
    System.out.println("getAuthor"); 
    String[] expResult = null;
    String[] result = instance.getAuthor();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setAuthor method, of class SolrData.
   */
  @Test
  public void testSetAuthor() {
    System.out.println("setAuthor");
    String[] author = new String[1];
    author[0] = "test author"; 
    instance.setAuthor(author); 
    assertEquals(1, instance.getAuthor().length); 
  }

  /**
   * Test of getCatalogNumber method, of class SolrData.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber");  
    String result = instance.getCatalogNumber();
    assertEquals(null, result); ;
  }

  /**
   * Test of setCatalogNumber method, of class SolrData.
   */
  @Test
  public void testSetCatalogNumber() {
    System.out.println("setCatalogNumber");
    String catalogNumber = "cat123"; 
    instance.setCatalogNumber(catalogNumber); 
    assertEquals(catalogNumber, instance.getCatalogNumber());
  }

  /**
   * Test of getCatalogedDate method, of class SolrData.
   */
  @Test
  public void testGetCatalogedDate() {
    System.out.println("getCatalogedDate"); 
    Date expResult = null;
    Date result = instance.getCatalogedDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setCatalogedDate method, of class SolrData.
   */
  @Test
  public void testSetCatalogedDate() {
    System.out.println("setCatalogedDate");
    Date catalogedDate = new Date(); 
    instance.setCatalogedDate(catalogedDate); 
    assertNotNull(instance.getCatalogedDate());
  }

  /**
   * Test of getCollector method, of class SolrData.
   */
  @Test
  public void testGetCollector() {
    System.out.println("getCollector"); 
    String[] expResult = null;
    String[] result = instance.getCollector();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setCollector method, of class SolrData.
   */
  @Test
  public void testSetCollector() {
    System.out.println("setCollector");
    String[] collector = new String[1];
    collector[0] = "collector"; 
    instance.setCollector(collector); 
    assertEquals(1, instance.getCollector().length);
  }

  /**
   * Test of getDeterminer method, of class SolrData.
   */
  @Test
  public void testGetDeterminer() {
    System.out.println("getDeterminer");  
    String result = instance.getDeterminer();
    assertEquals(null, result); 
  }

  /**
   * Test of setDeterminer method, of class SolrData.
   */
  @Test
  public void testSetDeterminer() {
    System.out.println("setDeterminer");
    String determiner = "determiner"; 
    instance.setDeterminer(determiner); 
    assertEquals(determiner, instance.getDeterminer());
  }

  /**
   * Test of getStartDate method, of class SolrData.
   */
  @Test
  public void testGetStartDate() {
    System.out.println("getStartDate"); 
    Date expResult = null;
    Date result = instance.getStartDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setStartDate method, of class SolrData.
   */
  @Test
  public void testSetStartDate() {
    System.out.println("setStartDate");
    Date startDate = new Date(); 
    instance.setStartDate(startDate); 
    assertNotNull(instance.getStartDate());
  }

  /**
   * Test of getCollectionName method, of class SolrData.
   */
  @Test
  public void testGetCollectionName() {
    System.out.println("getCollectionName");  
    String result = instance.getCollectionName();
    assertEquals(null, result); 
  }

  /**
   * Test of setCollectionName method, of class SolrData.
   */
  @Test
  public void testSetCollectionName() {
    System.out.println("setCollectionName");
    String collectionName = "collection"; 
    instance.setCollectionName(collectionName); 
    assertEquals(collectionName, instance.getCollectionName());
  }

  /**
   * Test of getCollectionId method, of class SolrData.
   */
  @Test
  public void testGetCollectionId() {
    System.out.println("getCollectionId");  
    String result = instance.getCollectionId();
    assertEquals(null, result); 
  }

  /**
   * Test of setCollectionId method, of class SolrData.
   */
  @Test
  public void testSetCollectionId() {
    System.out.println("setCollectionId");
    String collectionId = "c123"; 
    instance.setCollectionId(collectionId); 
    assertEquals(collectionId, instance.getCollectionId());
  }

  /**
   * Test of getContinent method, of class SolrData.
   */
  @Test
  public void testGetContinent() {
    System.out.println("getContinent");  
    String result = instance.getContinent();
    assertEquals(null, result); 
  }

  /**
   * Test of setContinent method, of class SolrData.
   */
  @Test
  public void testSetContinent() {
    System.out.println("setContinent");
    String continent = "Europe"; 
    instance.setContinent(continent); 
    assertEquals(continent, instance.getContinent());
  }

  /**
   * Test of getLatitudeText method, of class SolrData.
   */
  @Test
  public void testGetLatitudeText() {
    System.out.println("getLatitudeText");  
    String result = instance.getLatitudeText();
    assertEquals(null, result); 
  }

  /**
   * Test of setLatitudeText method, of class SolrData.
   */
  @Test
  public void testSetLatitudeText() {
    System.out.println("setLatitudeText");
    String latitudeText = "N34"; 
    instance.setLatitudeText(latitudeText);
    assertEquals(latitudeText, instance.getLatitudeText());
  }

  /**
   * Test of getLatitude method, of class SolrData.
   */
  @Test
  public void testGetLatitude() {
    System.out.println("getLatitude"); 
    double expResult = 0.0;
    double result = instance.getLatitude();
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of setLatitude method, of class SolrData.
   */
  @Test
  public void testSetLatitude() {
    System.out.println("setLatitude");
    double latitude = 12.0; 
    instance.setLatitude(latitude); 
    assertEquals(String.valueOf(latitude), String.valueOf(instance.getLatitude()));
  }

  /**
   * Test of getLongitude method, of class SolrData.
   */
  @Test
  public void testGetLongitude() {
    System.out.println("getLongitude"); 
    double expResult = 0.0;
    double result = instance.getLongitude();
    assertEquals(expResult, result, 0.0); 
  }

  /**
   * Test of setLongitude method, of class SolrData.
   */
  @Test
  public void testSetLongitude() {
    System.out.println("setLongitude");
    double longitude = 18.0; 
    instance.setLongitude(longitude);
    assertEquals(String.valueOf(longitude), String.valueOf(instance.getLongitude()));
  }

  /**
   * Test of getLongitudeText method, of class SolrData.
   */
  @Test
  public void testGetLongitudeText() {
    System.out.println("getLongitudeText"); 
    String expResult = "";
    String result = instance.getLongitudeText();
    assertEquals(null, result); 
  }

  /**
   * Test of setLongitudeText method, of class SolrData.
   */
  @Test
  public void testSetLongitudeText() {
    System.out.println("setLongitudeText");
    String longitudeText = "E35"; 
    instance.setLongitudeText(longitudeText); 
    assertEquals(longitudeText, instance.getLongitudeText());
  }

  /**
   * Test of getCountry method, of class SolrData.
   */
  @Test
  public void testGetCountry() {
    System.out.println("getCountry");  
    String result = instance.getCountry();
    assertEquals(null, result); 
  }

  /**
   * Test of setCountry method, of class SolrData.
   */
  @Test
  public void testSetCountry() {
    System.out.println("setCountry");
    String country = "Sweden"; 
    instance.setCountry(country); 
    assertEquals(country, instance.getCountry());
  }

  /**
   * Test of getCounty method, of class SolrData.
   */
  @Test
  public void testGetCounty() {
    System.out.println("getCounty"); 
    String result = instance.getCounty();
    assertEquals(null, result); 
  }

  /**
   * Test of setCounty method, of class SolrData.
   */
  @Test
  public void testSetCounty() {
    System.out.println("setCounty");
    String county = "Stockholm"; 
    instance.setCounty(county); 
    assertEquals(county, instance.getCounty());
  }

  /**
   * Test of getHigherTx method, of class SolrData.
   */
  @Test
  public void testGetHigherTx() {
    System.out.println("getHigherTx"); 
    String result = instance.getHigherTx();
    assertEquals(null, result); 
  }

  /**
   * Test of setHigherTx method, of class SolrData.
   */
  @Test
  public void testSetHigherTx() {
    System.out.println("setHigherTx");
    String higherTx = "ht"; 
    instance.setHigherTx(higherTx); 
    assertEquals(higherTx, instance.getHigherTx());
  }

  /**
   * Test of isMap method, of class SolrData.
   */
  @Test
  public void testIsMap() {
    System.out.println("isMap"); 
    boolean expResult = false;
    boolean result = instance.isMap();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setMap method, of class SolrData.
   */
  @Test
  public void testSetMap() {
    System.out.println("setMap");
    boolean map = true; 
    instance.setMap(map); 
    assertTrue(instance.isMap());
  }

  /**
   * Test of getMorphbankId method, of class SolrData.
   */
  @Test
  public void testGetMorphbankId() {
    System.out.println("getMorphbankId");  
    String result = instance.getMorphbankId();
    assertEquals(null, result); 
  }

  /**
   * Test of setMorphbankId method, of class SolrData.
   */
  @Test
  public void testSetMorphbankId() {
    System.out.println("setMorphbankId");
    String morphbankId = "123"; 
    instance.setMorphbankId(morphbankId); 
    assertEquals(morphbankId, instance.getMorphbankId());
  }

  /**
   * Test of getMorphbankImageId method, of class SolrData.
   */
  @Test
  public void testGetMorphbankImageId() {
    System.out.println("getMorphbankImageId"); 
    String[] expResult = null;
    String[] result = instance.getMorphbankImageId();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setMorphbankImageId method, of class SolrData.
   */
  @Test
  public void testSetMorphbankImageId() {
    System.out.println("setMorphbankImageId");
    String[] morphbankImageId = new String[1];
    morphbankImageId[0] = "123"; 
    instance.setMorphbankImageId(morphbankImageId); 
    assertEquals(1, instance.getMorphbankImageId().length);
  }

  /**
   * Test of getLocality method, of class SolrData.
   */
  @Test
  public void testGetLocality() {
    System.out.println("getLocality");  
    String result = instance.getLocality();
    assertEquals(null, result); 
  }

  /**
   * Test of setLocality method, of class SolrData.
   */
  @Test
  public void testSetLocality() {
    System.out.println("setLocality");
    String locality = "stockholm"; 
    instance.setLocality(locality); 
    assertEquals(locality, instance.getLocality());
  }

  /**
   * Test of getState method, of class SolrData.
   */
  @Test
  public void testGetState() {
    System.out.println("getState"); 
    String result = instance.getState();
    assertEquals(null, result); 
  }

  /**
   * Test of setState method, of class SolrData.
   */
  @Test
  public void testSetState() {
    System.out.println("setState");
    String state = "stockholm"; 
    instance.setState(state); 
    assertEquals(state, instance.getState());
  }

  /**
   * Test of getPrepration method, of class SolrData.
   */
  @Test
  public void testGetPrepration() {
    System.out.println("getPrepration"); 
    String[] result = instance.getPrepration();
    assertArrayEquals(null, result); 
  }

  /**
   * Test of setPrepration method, of class SolrData.
   */
  @Test
  public void testSetPrepration() {
    System.out.println("setPrepration");
    String[] prepration = new String[1];
    prepration[0] = "pre1"; 
    instance.setPrepration(prepration); 
    assertEquals(1, instance.getPrepration().length);
  }

  /**
   * Test of getColremarks method, of class SolrData.
   */
  @Test
  public void testGetColremarks() {
    System.out.println("getColremarks"); 
    String[] result = instance.getColremarks();
    assertArrayEquals(null, result); 
  }

  /**
   * Test of setColremarks method, of class SolrData.
   */
  @Test
  public void testSetColremarks() {
    System.out.println("setColremarks");
    String[] colremarks = new String[1];
    colremarks[0] = "test"; 
    instance.setColremarks(colremarks); 
    assertEquals(1, instance.getColremarks().length);
  }

  /**
   * Test of getRemarks method, of class SolrData.
   */
  @Test
  public void testGetRemarks() {
    System.out.println("getRemarks"); 
    String[] expResult = null;
    String[] result = instance.getRemarks();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setRemarks method, of class SolrData.
   */
  @Test
  public void testSetRemarks() {
    System.out.println("setRemarks");
    String[] remarks = new String[1];
    remarks[0] = "test";
    instance.setRemarks(remarks); 
    assertEquals(1, instance.getRemarks().length);
  }

  /**
   * Test of getSerie method, of class SolrData.
   */
  @Test
  public void testGetSerie() {
    System.out.println("getSerie");  
    String result = instance.getSerie();
    assertEquals(null, result); 
  }

  /**
   * Test of setSerie method, of class SolrData.
   */
  @Test
  public void testSetSerie() {
    System.out.println("setSerie");
    String serie = "test"; 
    instance.setSerie(serie);
    assertEquals(serie, instance.getSerie());
  }

  /**
   * Test of getStationFieldNumber method, of class SolrData.
   */
  @Test
  public void testGetStationFieldNumber() {
    System.out.println("getStationFieldNumber"); 
    String result = instance.getStationFieldNumber();
    assertEquals(null, result); 
  }

  /**
   * Test of setStationFieldNumber method, of class SolrData.
   */
  @Test
  public void testSetStationFieldNumber() {
    System.out.println("setStationFieldNumber");
    String stationFieldNumber = "test"; 
    instance.setStationFieldNumber(stationFieldNumber); 
    assertEquals(stationFieldNumber, instance.getStationFieldNumber());
  }

  /**
   * Test of getSpecies method, of class SolrData.
   */
  @Test
  public void testGetSpecies() {
    System.out.println("getSpecies"); 
    String result = instance.getSpecies();
    assertEquals(null, result); 
  }

  /**
   * Test of setSpecies method, of class SolrData.
   */
  @Test
  public void testSetSpecies() {
    System.out.println("setSpecies");
    String species = "test"; 
    instance.setSpecies(species); 
    assertEquals(species, instance.getSpecies()); 
  }

  /**
   * Test of getSynonym method, of class SolrData.
   */
  @Test
  public void testGetSynonym() {
    System.out.println("getSynonym"); 
    String[] expResult = null;
    String[] result = instance.getSynonym();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setSynonym method, of class SolrData.
   */
  @Test
  public void testSetSynonym() {
    System.out.println("setSynonym");
    String[] synonym = new String[1];
    synonym[0] = "test";
    instance.setSynonym(synonym);
    assertEquals(1, instance.getSynonym().length);
  }

  /**
   * Test of getTypeStatus method, of class SolrData.
   */
  @Test
  public void testGetTypeStatus() {
    System.out.println("getTypeStatus");  
    String result = instance.getTypeStatus();
    assertEquals(null, result); 
  }

  /**
   * Test of setTypeStatus method, of class SolrData.
   */
  @Test
  public void testSetTypeStatus() {
    System.out.println("setTypeStatus");
    String typeStatus = "type"; 
    instance.setTypeStatus(typeStatus); 
    assertEquals(typeStatus, instance.getTypeStatus());
  }

  /**
   * Test of getTxFullName method, of class SolrData.
   */
  @Test
  public void testGetTxFullName() {
    System.out.println("getTxFullName");  
    String result = instance.getTxFullName();
    assertEquals(null, result); 
  }

  /**
   * Test of setTxFullName method, of class SolrData.
   */
  @Test
  public void testSetTxFullName() {
    System.out.println("setTxFullName");
    String txFullName = "test"; 
    instance.setTxFullName(txFullName); 
    assertEquals(txFullName, instance.getTxFullName());
  }

  /**
   * Test of getTaxa method, of class SolrData.
   */
  @Test
  public void testGetTaxa() {
    System.out.println("getTaxa"); 
    String[] expResult = null;
    String[] result = instance.getTaxa();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setTaxa method, of class SolrData.
   */
  @Test
  public void testSetTaxa() {
    System.out.println("setTaxa");
    String[] taxa = new String[1];
    taxa[0] = "test";
    instance.setTaxa(taxa); 
    assertEquals(1, instance.getTaxa().length);
  }

  /**
   * Test of isSelected method, of class SolrData.
   */
  @Test
  public void testIsSelected() {
    System.out.println("isSelected"); 
    boolean expResult = false;
    boolean result = instance.isSelected();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setSelected method, of class SolrData.
   */
  @Test
  public void testSetSelected() {
    System.out.println("setSelected");
    boolean selected = true; 
    instance.setSelected(selected); 
    assertTrue(instance.isSelected());
  }

  /**
   * Test of isImageExist method, of class SolrData.
   */
  @Test
  public void testIsImageExist() {
    System.out.println("isImageExist"); 
    boolean expResult = false;
    boolean result = instance.isImageExist();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setImageExist method, of class SolrData.
   */
  @Test
  public void testSetImageExist() {
    System.out.println("setImageExist");
    boolean imageExist = true; 
    instance.setImageExist(imageExist); 
    assertTrue(instance.imageExist);
  }

  /**
   * Test of isDisplayMap method, of class SolrData.
   */
  @Test
  public void testIsDisplayMap() {
    System.out.println("isDisplayMap"); 
    boolean expResult = false;
    boolean result = instance.isDisplayMap();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setDisplayMap method, of class SolrData.
   */
  @Test
  public void testSetDisplayMap() {
    System.out.println("setDisplayMap");
    boolean displayMap = true; 
    instance.setDisplayMap(displayMap); 
    assertTrue(instance.displayMap);
  }

  /**
   * Test of isDisplayImage method, of class SolrData.
   */
  @Test
  public void testIsDisplayImage() {
    System.out.println("isDisplayImage"); 
    boolean expResult = false;
    boolean result = instance.isDisplayImage();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setDisplayImage method, of class SolrData.
   */
  @Test
  public void testSetDisplayImage() {
    System.out.println("setDisplayImage");
    boolean displayImage = true; 
    instance.setDisplayImage(displayImage); 
    assertTrue(instance.displayImage);
  }

  /**
   * Test of getAuthors method, of class SolrData.
   */
  @Test
  public void testGetAuthors() {
    System.out.println("getAuthors"); 
    String result = instance.getAuthors();
    assertEquals(null, result); 
  }

  /**
   * Test of isOpenRemark method, of class SolrData.
   */
  @Test
  public void testIsOpenRemark() {
    System.out.println("isOpenRemark"); 
    boolean expResult = false;
    boolean result = instance.isOpenRemark();
    assertEquals(expResult, result); 
  }

  /**
   * Test of setOpenRemark method, of class SolrData.
   */
  @Test
  public void testSetOpenRemark() {
    System.out.println("setOpenRemark");
    boolean openRemark = true; 
    instance.setOpenRemark(openRemark); 
    assertTrue(instance.openRemark);
  }

  /**
   * Test of getRemarkBtn method, of class SolrData.
   */
  @Test
  public void testGetRemarkBtn() {
    System.out.println("getRemarkBtn");  
    String result = instance.getRemarkBtn();
    assertEquals("downarrow.gif", result); 
  }

  /**
   * Test of getSynonymAuthors method, of class SolrData.
   */
  @Test
  public void testGetSynonymAuthors() {
    System.out.println("getSynonymAuthors");  
    String result = instance.getSynonymAuthors();
    assertEquals(null, result); 
  }

  /**
   * Test of getCollectors method, of class SolrData.
   */
  @Test
  public void testGetCollectors() {
    System.out.println("getCollectors");  
    String result = instance.getCollectors();
    assertEquals(null, result); 
  }

  /**
   * Test of getTaxaList method, of class SolrData.
   */
  @Test
  public void testGetTaxaList() {
    System.out.println("getTaxaList");  
    String result = instance.getTaxaList();
    assertEquals(null, result); 
  }

  /**
   * Test of getAllRemarkes method, of class SolrData.
   */
  @Test
  public void testGetAllRemarkes() {
    System.out.println("getAllRemarkes");  
    List<String> result = instance.getAllRemarkes();
    assertTrue(result.isEmpty()); 
  }

  /**
   * Test of getExportRemarks method, of class SolrData.
   */
  @Test
  public void testGetExportRemarks() {
    System.out.println("getExportRemarks");  
    String result = instance.getExportRemarks();
    assertEquals("", result); 
  }

  /**
   * Test of getGeopointText method, of class SolrData.
   */
  @Test
  public void testGetGeopointText() {
    System.out.println("getGeopointText"); 
    String expResult = "null null";
    String result = instance.getGeopointText();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAllCoAttrRemarkes method, of class SolrData.
   */
  @Test
  public void testGetAllCoAttrRemarkes() {
    System.out.println("getAllCoAttrRemarkes");  
    String result = instance.getAllCoAttrRemarkes();
    assertEquals(null, result); 
  }

  /**
   * Test of getPrepCount method, of class SolrData.
   */
  @Test
  public void testGetPrepCount() {
    System.out.println("getPrepCount"); 
    String[] expResult = null;
    String[] result = instance.getPrepCount();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setPrepCount method, of class SolrData.
   */
  @Test
  public void testSetPrepCount() {
    System.out.println("setPrepCount");
    String[] prepCount = null; 
    instance.setPrepCount(prepCount); 
  }

  /**
   * Test of getAllPreparations method, of class SolrData.
   */
  @Test
  public void testGetAllPreparations() {
    System.out.println("getAllPreparations");  
    String result = instance.getAllPreparations();
    assertEquals(null, result); 
  }

  /**
   * Test of getLatLng method, of class SolrData.
   */
  @Test
  public void testGetLatLng() {
    System.out.println("getLatLng"); 
    LatLng expResult = new LatLng(0.0, 0.0);
    LatLng result = instance.getLatLng();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCoordinate method, of class SolrData.
   */
  @Test
  public void testGetCoordinate() {
    System.out.println("getCoordinate"); 
    String result = instance.getCoordinate();
    assertEquals(null, result); 
  }

  /**
   * Test of setCoordinate method, of class SolrData.
   */
  @Test
  public void testSetCoordinate() {
    System.out.println("setCoordinate");
    String coordinate = "N18E36"; 
    instance.setCoordinate(coordinate); 
    assertEquals(coordinate, instance.getCoordinate());
  }

  /**
   * Test of getAdditionalDet method, of class SolrData.
   */
  @Test
  public void testGetAdditionalDet() {
    System.out.println("getAdditionalDet"); 
    String[] expResult = null;
    String[] result = instance.getAdditionalDet();
    assertArrayEquals(expResult, result); 
  }

  /**
   * Test of setAdditionalDet method, of class SolrData.
   */
  @Test
  public void testSetAdditionalDet() {
    System.out.println("setAdditionalDet");
    String[] additionalDet = new String[1];
    additionalDet[0] = "test";
    instance.setAdditionalDet(additionalDet); 
    assertEquals(1, instance.getAdditionalDet().length);
  }

  /**
   * Test of setImages method, of class SolrData.
   */
  @Test
  public void testSetImages() {
    System.out.println("setImages");
    String morphbankImageUrl = "imageurl"; 
    instance.setImages(morphbankImageUrl);  
  }

  /**
   * Test of isOpenMap method, of class SolrData.
   */
  @Test
  public void testIsOpenMap() {
    System.out.println("isOpenMap"); 
    boolean expResult = false;
    boolean result = instance.isOpenMap();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isSingleMapLink method, of class SolrData.
   */
  @Test
  public void testIsSingleMapLink() {
    System.out.println("isSingleMapLink"); 
    boolean expResult = false;
    boolean result = instance.isSingleMapLink();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getThumbs method, of class SolrData.
   */
  @Test
  public void testGetThumbs() {
    System.out.println("getThumbs"); 
    List<String> expResult = null;
    List<String> result = instance.getThumbs();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getJpgs method, of class SolrData.
   */
  @Test
  public void testGetJpgs() {
    System.out.println("getJpgs"); 
    List<String> expResult = null;
    List<String> result = instance.getJpgs();
    assertEquals(expResult, result); 
  }

  /**
   * Test of isMineral method, of class SolrData.
   */
  @Test
  public void testIsMineral() {
    System.out.println("isMineral"); 
    boolean expResult = false;
    boolean result = instance.isMineral();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTotalPreparations method, of class SolrData.
   */
  @Test
  public void testGetTotalPreparations() {
    System.out.println("getTotalPreparations"); 
    int expResult = 0;
    int result = instance.getTotalPreparations();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDetermination method, of class SolrData.
   */
  @Test
  public void testGetDetermination() {
    System.out.println("getDetermination");  
    String result = instance.getDetermination();
    assertEquals(null, result); 
  }

  /**
   * Test of openCloseRemarks method, of class SolrData.
   */
  @Test
  public void testOpenCloseRemarks() {
    System.out.println("openCloseRemarks"); 
    instance.openCloseRemarks(); 
    assertTrue(instance.openRemark);
  }

  /**
   * Test of getOpenCloseRemarkImg method, of class SolrData.
   */
  @Test
  public void testGetOpenCloseRemarkImg() {
    System.out.println("getOpenCloseRemarkImg"); 
    String result = instance.getOpenCloseRemarkImg();
    assertEquals("hidearrow.gif", result); 
  }

  /**
   * Test of getAllAdditionalDeterminations method, of class SolrData.
   */
  @Test
  public void testGetAllAdditionalDeterminations() {
    System.out.println("getAllAdditionalDeterminations");  
    String result = instance.getAllAdditionalDeterminations();
    assertEquals(null, result); 
  }

  /**
   * Test of getPrepCountList method, of class SolrData.
   */
  @Test
  public void testGetPrepCountList() {
    System.out.println("getPrepCountList");  
    String result = instance.getPrepCountList();
    assertEquals(null, result); 
  }

  /**
   * Test of getCoordinateString method, of class SolrData.
   */
  @Test
  public void testGetCoordinateString() {
    System.out.println("getCoordinateString");  
    String result = instance.getCoordinateString();
    assertEquals(null, result); 
  }
  
}
