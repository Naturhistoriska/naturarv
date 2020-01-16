package se.nrm.dina.web.portal.utils;

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class CommonTextTest {
  
  private CommonText instance;
  
  private final String institutionNameNrmEn = "Swedish Museum of Natural History";
  private final String institutionNameNrmSv = "Naturhistoriska riksmuseet";
  private final String institutionNameGnmEn = "Gothenburg Natural History Museum";
  private final String institutionNameGnmSv = "Göteborgs naturhistoriska museum";
  
  public CommonTextTest() {
  }
  
  @Before
  public void setUp() {
    instance = CommonText.getInstance();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of getInstance method, of class CommonText.
   */
  @Test
  public void testGetInstance() {
    System.out.println("getInstance"); 
    CommonText result = CommonText.getInstance();
    assertNotNull(result); 
  }

  /**
   * Test of getFieldName method, of class CommonText.
   */
  @Test
  public void testGetFieldNameSv() {
    System.out.println("getFieldName");
    String key = "cn";
    boolean isSwedish = true; 
    String expResult = "Cataloguenumber";
    String result = instance.getFieldName(key, isSwedish);
    assertEquals(expResult, result); 
  }
  
  @Test
  public void testGetFieldNameEn() {
    System.out.println("getFieldName");
    String key = "cn";
    boolean isSwedish = false; 
    String expResult = "Catalog number";
    String result = instance.getFieldName(key, isSwedish);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSearchField method, of class CommonText.
   */
  @Test
  public void testGetSearchField() {
    System.out.println("getSearchField");
    String key = "auth"; 
    String expResult = "author";
    String result = instance.getSearchField(key);
    assertEquals(expResult, result); 
  }

  /**
   * Test of getSv method, of class CommonText.
   */
  @Test
  public void testGetSv() {
    System.out.println("getSv"); 
    String expResult = "sv";
    String result = instance.getSv();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getDna method, of class CommonText.
   */
  @Test
  public void testGetDna() {
    System.out.println("getDna"); 
    String expResult = "dna";
    String result = instance.getDna();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getImage method, of class CommonText.
   */
  @Test
  public void testGetImage() {
    System.out.println("getImage"); 
    String expResult = "image";
    String result = instance.getImage();
    assertEquals(expResult, result);  
  }

  /**
   * Test of getSweden method, of class CommonText.
   */
  @Test
  public void testGetSweden() {
    System.out.println("getSweden"); 
    String expResult = "sweden";
    String result = instance.getSweden();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMap method, of class CommonText.
   */
  @Test
  public void testGetMap() {
    System.out.println("getMap"); 
    String expResult = "map";
    String result = instance.getMap();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getType method, of class CommonText.
   */
  @Test
  public void testGetType() {
    System.out.println("getType"); 
    String expResult = "type";
    String result = instance.getType();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getIsType method, of class CommonText.
   */
  @Test
  public void testGetIsType() {
    System.out.println("getIsType"); 
    String expResult = "isType";
    String result = instance.getIsType();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getInSweden method, of class CommonText.
   */
  @Test
  public void testGetInSweden() {
    System.out.println("getInSweden"); 
    String expResult = "inSweden";
    String result = instance.getInSweden();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor1 method, of class CommonText.
   */
  @Test
  public void testGetColor1() {
    System.out.println("getColor1"); 
    String expResult = "coloer1";
    String result = instance.getColor1();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor2 method, of class CommonText.
   */
  @Test
  public void testGetColor2() {
    System.out.println("getColor2"); 
    String expResult = "coloer2";
    String result = instance.getColor2();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor3 method, of class CommonText.
   */
  @Test
  public void testGetColor3() {
    System.out.println("getColor3"); 
    String expResult = "coloer3";
    String result = instance.getColor3();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor4 method, of class CommonText.
   */
  @Test
  public void testGetColor4() {
    System.out.println("getColor4"); 
    String expResult = "coloer4";
    String result = instance.getColor4();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor5 method, of class CommonText.
   */
  @Test
  public void testGetColor5() {
    System.out.println("getColor5"); 
    String expResult = "coloer5";
    String result = instance.getColor5();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getColor6 method, of class CommonText.
   */
  @Test
  public void testGetColor6() {
    System.out.println("getColor6"); 
    String expResult = "coloer6";
    String result = instance.getColor6();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGroup method, of class CommonText.
   */
  @Test
  public void testGetGroup() {
    System.out.println("getGroup"); 
    String expResult = "group";
    String result = instance.getGroup();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGroupField method, of class CommonText.
   */
  @Test
  public void testGetGroupField() {
    System.out.println("getGroupField");
    String expResult = "group.field";
    String result = instance.getGroupField();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGroups method, of class CommonText.
   */
  @Test
  public void testGetGroups() {
    System.out.println("getGroups"); 
    String expResult = "groups";
    String result = instance.getGroups();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getGrouped method, of class CommonText.
   */
  @Test
  public void testGetGrouped() {
    System.out.println("getGrouped"); 
    String expResult = "grouped";
    String result = instance.getGrouped();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMatches method, of class CommonText.
   */
  @Test
  public void testGetMatches() {
    System.out.println("getMatches"); 
    String expResult = "matches";
    String result = instance.getMatches();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getId method, of class CommonText.
   */
  @Test
  public void testGetId() {
    System.out.println("getId"); 
    String expResult = "id";
    String result = instance.getId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCoordinate method, of class CommonText.
   */
  @Test
  public void testGetCoordinate_0args() {
    System.out.println("getCoordinate"); 
    String expResult = "coordinate";
    String result = instance.getCoordinate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogNumber method, of class CommonText.
   */
  @Test
  public void testGetCatalogNumber() {
    System.out.println("getCatalogNumber"); 
    String expResult = "catalogNumber";
    String result = instance.getCatalogNumber();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogedDate method, of class CommonText.
   */
  @Test
  public void testGetCatalogedDate() {
    System.out.println("getCatalogedDate"); 
    String expResult = "catalogedDate";
    String result = instance.getCatalogedDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCreatedDate method, of class CommonText.
   */
  @Test
  public void testGetCreatedDate() {
    System.out.println("getCreatedDate"); 
    String expResult = "createdDate";
    String result = instance.getCreatedDate();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTaxonFullName method, of class CommonText.
   */
  @Test
  public void testGetTaxonFullName() {
    System.out.println("getTaxonFullName"); 
    String expResult = "txFullName";
    String result = instance.getTaxonFullName();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getHighTaxa method, of class CommonText.
   */
  @Test
  public void testGetHighTaxa() {
    System.out.println("getHighTaxa"); 
    String expResult = "higherTx";
    String result = instance.getHighTaxa();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTxSearch method, of class CommonText.
   */
  @Test
  public void testGetTxSearch() {
    System.out.println("getTxSearch"); 
    String expResult = "tx";
    String result = instance.getTxSearch();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCollectionId method, of class CommonText.
   */
  @Test
  public void testGetCollectionId() {
    System.out.println("getCollectionId"); 
    String expResult = "collectionId";
    String result = instance.getCollectionId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getLocations method, of class CommonText.
   */
  @Test
  public void testGetLocations() {
    System.out.println("getLocations"); 
    String expResult = "locations";
    String result = instance.getLocations();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getCatalogedYear method, of class CommonText.
   */
  @Test
  public void testGetCatalogedYear() {
    System.out.println("getCatalogedYear"); 
    String expResult = "catalogedYear";
    String result = instance.getCatalogedYear();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMorphbankId method, of class CommonText.
   */
  @Test
  public void testGetMorphbankId() {
    System.out.println("getMorphbankId"); 
    String expResult = "morphbankId";
    String result = instance.getMorphbankId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getMorphbankIdKey method, of class CommonText.
   */
  @Test
  public void testGetMorphbankIdKey() {
    System.out.println("getMorphbankIdKey"); 
    String expResult = "morphbankId:";
    String result = instance.getMorphbankIdKey();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getImageView method, of class CommonText.
   */
  @Test
  public void testGetImageView() {
    System.out.println("getImageView"); 
    String expResult = "morphBankView";
    String result = instance.getImageView();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getImageId method, of class CommonText.
   */
  @Test
  public void testGetImageId() {
    System.out.println("getImageId"); 
    String expResult = "morphbankImageId";
    String result = instance.getImageId();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAuthor method, of class CommonText.
   */
  @Test
  public void testGetAuthor() {
    System.out.println("getAuthor"); 
    String expResult = "author";
    String result = instance.getAuthor();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAll method, of class CommonText.
   */
  @Test
  public void testGetAll() {
    System.out.println("getAll"); 
    String expResult = "all";
    String result = instance.getAll();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getContains method, of class CommonText.
   */
  @Test
  public void testGetContains() {
    System.out.println("getContains"); 
    String expResult = "contains";
    String result = instance.getContains();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getExact method, of class CommonText.
   */
  @Test
  public void testGetExact() {
    System.out.println("getExact"); 
    String expResult = "exact";
    String result = instance.getExact();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getStartWith method, of class CommonText.
   */
  @Test
  public void testGetStartWith() {
    System.out.println("getStartWith"); 
    String expResult = "startswith";
    String result = instance.getStartWith();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getAnd method, of class CommonText.
   */
  @Test
  public void testGetAnd() {
    System.out.println("getAnd"); 
    String expResult = "and";
    String result = instance.getAnd();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getOr method, of class CommonText.
   */
  @Test
  public void testGetOr() {
    System.out.println("getOr"); 
    String expResult = "or";
    String result = instance.getOr();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getNot method, of class CommonText.
   */
  @Test
  public void testGetNot() {
    System.out.println("getNot");
    String expResult = "not";
    String result = instance.getNot();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNrmCode method, of class CommonText.
   */
  @Test
  public void testGetNrmCode() {
    System.out.println("getNrmCode");
    String expResult = "nrm*";
    String result = instance.getNrmCode();
    assertEquals(expResult, result);
  }

  /**
   * Test of getGnmCode method, of class CommonText.
   */
  @Test
  public void testGetGnmCode() {
    System.out.println("getGnmCode");
    String expResult = "gnm*";
    String result = instance.getGnmCode();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSortByScore method, of class CommonText.
   */
  @Test
  public void testGetSortByScore() {
    System.out.println("getSortByScore");
    String expResult = "score";
    String result = instance.getSortByScore();
    assertEquals(expResult, result);
  }

  /**
   * Test of getWildCard method, of class CommonText.
   */
  @Test
  public void testGetWildCard() {
    System.out.println("getWildCard");
    String expResult = "*";
    String result = instance.getWildCard();
    assertEquals(expResult, result);
  }

  /**
   * Test of getIdKey method, of class CommonText.
   */
  @Test
  public void testGetIdKey() {
    System.out.println("getIdKey");
    String expResult = "id:";
    String result = instance.getIdKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getMapKey method, of class CommonText.
   */
  @Test
  public void testGetMapKey() {
    System.out.println("getMapKey");
    String expResult = "map:";
    String result = instance.getMapKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectionNameKey method, of class CommonText.
   */
  @Test
  public void testGetCollectionNameKey() {
    System.out.println("getCollectionNameKey");
    String expResult = "collectionName:";
    String result = instance.getCollectionNameKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectionCodeKey method, of class CommonText.
   */
  @Test
  public void testGetCollectionCodeKey() {
    System.out.println("getCollectionCodeKey");
    String expResult = "collectionId:";
    String result = instance.getCollectionCodeKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTextField method, of class CommonText.
   */
  @Test
  public void testGetTextField() {
    System.out.println("getTextField");
    String expResult = "text";
    String result = instance.getTextField();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTextSearch method, of class CommonText.
   */
  @Test
  public void testGetTextSearch() {
    System.out.println("getTextSearch");
    String expResult = "textsearch";
    String result = instance.getTextSearch();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoordinateKey method, of class CommonText.
   */
  @Test
  public void testGetCoordinateKey() {
    System.out.println("getCoordinateKey");
    String expResult = "coordinate:";
    String result = instance.getCoordinateKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getGeopoint method, of class CommonText.
   */
  @Test
  public void testGetGeopoint() {
    System.out.println("getGeopoint");
    String expResult = "geopoint";
    String result = instance.getGeopoint();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectionName method, of class CommonText.
   */
  @Test
  public void testGetCollectionName() {
    System.out.println("getCollectionName");
    String expResult = "collectionName";
    String result = instance.getCollectionName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getImageViewKey method, of class CommonText.
   */
  @Test
  public void testGetImageViewKey() {
    System.out.println("getImageViewKey");
    String expResult = "morphBankView:";
    String result = instance.getImageViewKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getImageKey method, of class CommonText.
   */
  @Test
  public void testGetImageKey() {
    System.out.println("getImageKey");
    String expResult = "image:";
    String result = instance.getImageKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getDNAKey method, of class CommonText.
   */
  @Test
  public void testGetDNAKey() {
    System.out.println("getDNAKey");
    String expResult = "dna:";
    String result = instance.getDNAKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTypeKey method, of class CommonText.
   */
  @Test
  public void testGetTypeKey() {
    System.out.println("getTypeKey");
    String expResult = "isType:";
    String result = instance.getTypeKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSwedenKey method, of class CommonText.
   */
  @Test
  public void testGetSwedenKey() {
    System.out.println("getSwedenKey");
    String expResult = "inSweden:";
    String result = instance.getSwedenKey();
    assertEquals(expResult, result);
  }

  /**
   * Test of getWildSearchQuery method, of class CommonText.
   */
  @Test
  public void testGetWildSearchQuery() {
    System.out.println("getWildSearchQuery");
    String expResult = "q:*";
    String result = instance.getWildSearchQuery();
    assertEquals(expResult, result);
  }

  /**
   * Test of getWildSearchText method, of class CommonText.
   */
  @Test
  public void testGetWildSearchText() {
    System.out.println("getWildSearchText");
    String expResult = "*:*";
    String result = instance.getWildSearchText();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLocale method, of class CommonText.
   */
  @Test
  public void testGetLocale() {
    System.out.println("getLocale");
    String expResult = "locale";
    String result = instance.getLocale();
    assertEquals(expResult, result);
  }

  /**
   * Test of getStatistic method, of class CommonText.
   */
  @Test
  public void testGetStatistic() {
    System.out.println("getStatistic");
    String expResult = "statistic";
    String result = instance.getStatistic();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSynonmy method, of class CommonText.
   */
  @Test
  public void testGetSynonmy() {
    System.out.println("getSynonmy");
    String expResult = "synonym";
    String result = instance.getSynonmy();
    assertEquals(expResult, result);
  }

  /**
   * Test of getMonthChartData method, of class CommonText.
   */
  @Test
  public void testGetMonthChartData() {
    System.out.println("getMonthChartData");
    String expResult = "monthChartData";
    String result = instance.getMonthChartData();
    assertEquals(expResult, result);
  }

  /**
   * Test of getYearChartData method, of class CommonText.
   */
  @Test
  public void testGetYearChartData() {
    System.out.println("getYearChartData");
    String expResult = "yearChartData";
    String result = instance.getYearChartData();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectionsMonthChart method, of class CommonText.
   */
  @Test
  public void testGetCollectionsMonthChart() {
    System.out.println("getCollectionsMonthChart");
    String expResult = "collectionsMonthChartData";
    String result = instance.getCollectionsMonthChart();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectionsYearChart method, of class CommonText.
   */
  @Test
  public void testGetCollectionsYearChart() {
    System.out.println("getCollectionsYearChart");
    String expResult = "collectionsYearChartData";
    String result = instance.getCollectionsYearChart();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCatalogedMonth method, of class CommonText.
   */
  @Test
  public void testGetCatalogedMonth() {
    System.out.println("getCatalogedMonth");
    String expResult = "catalogedMonth";
    String result = instance.getCatalogedMonth();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCatalogedMonthString method, of class CommonText.
   */
  @Test
  public void testGetCatalogedMonthString() {
    System.out.println("getCatalogedMonthString");
    String expResult = "catalogedMonthString";
    String result = instance.getCatalogedMonthString();
    assertEquals(expResult, result);
  }

  /**
   * Test of getAccessionNumber method, of class CommonText.
   */
  @Test
  public void testGetAccessionNumber() {
    System.out.println("getAccessionNumber");
    String expResult = "accessionNumber";
    String result = instance.getAccessionNumber();
    assertEquals(expResult, result);
  }

  /**
   * Test of getImageTypeThumb method, of class CommonText.
   */
  @Test
  public void testGetImageTypeThumb() {
    System.out.println("getImageTypeThumb");
    String expResult = "&imgType=thumb";
    String result = instance.getImageTypeThumb();
    assertEquals(expResult, result);
  }

  /**
   * Test of getImageTypeJpg method, of class CommonText.
   */
  @Test
  public void testGetImageTypeJpg() {
    System.out.println("getImageTypeJpg");
    String expResult = "&imgType=jpg";
    String result = instance.getImageTypeJpg();
    assertEquals(expResult, result);
  }

  /**
   * Test of getImageQueryId method, of class CommonText.
   */
  @Test
  public void testGetImageQueryId() {
    System.out.println("getImageQueryId");
    String expResult = "?id=";
    String result = instance.getImageQueryId();
    assertEquals(expResult, result);
  }

  /**
   * Test of getEmptyString method, of class CommonText.
   */
  @Test
  public void testGetEmptyString() {
    System.out.println("getEmptyString");
    String expResult = "";
    String result = instance.getEmptyString();
    assertEquals(expResult, result);
  }

  /**
   * Test of getEmptySpace method, of class CommonText.
   */
  @Test
  public void testGetEmptySpace() {
    System.out.println("getEmptySpace");
    String expResult = " ";
    String result = instance.getEmptySpace();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSearchDefaultText method, of class CommonText.
   */
  @Test
  public void testGetSearchDefaultTextSv() {
    System.out.println("getSearchDefaultText");
    boolean isSwedish = true;
    String expResult = "Sök i samlingar (art, släkte, familj, insamlare, plats etc.)";
    String result = instance.getSearchDefaultText(isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetSearchDefaultTextEn() {
    System.out.println("getSearchDefaultText");
    boolean isSwedish = false;
    String expResult = "Search collections (species, genus, family, collectors, location, etc.)";
    String result = instance.getSearchDefaultText(isSwedish);
    assertEquals(expResult, result);
  }
  
  /**
   * Test of getNrmName method, of class CommonText.
   */
  @Test
  public void testGetNrmNameSv() {
    System.out.println("getNrmName");
    boolean isSwedeish = true; 
    String result = instance.getNrmName(isSwedeish);
    assertEquals(institutionNameNrmSv, result);
  }
 
  @Test
  public void testGetNrmNameEn() {
    System.out.println("getNrmName");
    boolean isSwedeish = false; 
    String result = instance.getNrmName(isSwedeish);
    assertEquals(institutionNameNrmEn, result);
  }

  /**
   * Test of getGnmName method, of class CommonText.
   */
  @Test
  public void testGetGnmNameEn() {
    System.out.println("getGnmName");
    boolean isSwedish = false; 
    String result = instance.getGnmName(isSwedish);
    assertEquals(institutionNameGnmEn, result);
  }
  
  @Test
  public void testGetGnmNameSv() {
    System.out.println("getGnmName");
    boolean isSwedish = true; 
    String result = instance.getGnmName(isSwedish);
    assertEquals(institutionNameGnmSv, result);
  }

  /**
   * Test of getInstitutionCode method, of class CommonText.
   */
  @Test
  public void testGetInstitutionCodeNrmEn() {
    System.out.println("getInstitutionCode"); 
    boolean isSwedish = false;
    String expResult = "nrm*";
    String result = instance.getInstitutionCode(institutionNameNrmEn, isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetInstitutionCodeNrmSv() {
    System.out.println("getInstitutionCode"); 
    boolean isSwedish = true;
    String expResult = "nrm*";
    String result = instance.getInstitutionCode(institutionNameNrmSv, isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetInstitutionCodeGnmEn() {
    System.out.println("getInstitutionCode"); 
    boolean isSwedish = true;
    String expResult = "gnm*";
    String result = instance.getInstitutionCode(institutionNameGnmEn, isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetInstitutionCodeGnmSv() {
    System.out.println("getInstitutionCode"); 
    boolean isSwedish = true;
    String expResult = "gnm*";
    String result = instance.getInstitutionCode(institutionNameGnmSv, isSwedish);
    assertEquals(expResult, result);
  }
  
  /**
   * Test of getMonthChartTitle method, of class CommonText.
   */
  @Test
  public void testGetMonthChartTitleEn() {
    System.out.println("getMonthChartTitle");
    boolean isSwedish = false;
    String expResult = "Registered specimens last 12 months";
    String result = instance.getMonthChartTitle(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetMonthChartTitleSv() {
    System.out.println("getMonthChartTitle");
    boolean isSwedish = true;
    String expResult = "Registrerade föremål senaste 12 månaderna";
    String result = instance.getMonthChartTitle(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getYearChartTitle method, of class CommonText.
   */
  @Test
  public void testGetYearChartTitleEn() {
    System.out.println("getYearChartTitle");
    boolean isSwedish = false;
    String expResult = "Cumulative number of registered specimens";
    String result = instance.getYearChartTitle(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetYearChartTitleSv() {
    System.out.println("getYearChartTitle");
    boolean isSwedish = true;
    String expResult = "Ackumulerat antal registrerade föremål";
    String result = instance.getYearChartTitle(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getMonthChartXAxisLabel method, of class CommonText.
   */
  @Test
  public void testGetMonthChartXAxisLabelEn() {
    System.out.println("getMonthChartXAxisLabel");
    boolean isSwedish = false;
    String expResult = "Month";
    String result = instance.getMonthChartXAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetMonthChartXAxisLabelSv() {
    System.out.println("getMonthChartXAxisLabel");
    boolean isSwedish = true;
    String expResult = "Månad";
    String result = instance.getMonthChartXAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getYearChartXAxisLabel method, of class CommonText.
   */
  @Test
  public void testGetYearChartXAxisLabelEn() {
    System.out.println("getYearChartXAxisLabel");
    boolean isSwedish = false;
    String expResult = "Year";
    String result = instance.getYearChartXAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetYearChartXAxisLabelSv() {
    System.out.println("getYearChartXAxisLabel");
    boolean isSwedish = true;
    String expResult = "År";
    String result = instance.getYearChartXAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getChartYAxisLabel method, of class CommonText.
   */
  @Test
  public void testGetChartYAxisLabelEn() {
    System.out.println("getChartYAxisLabel");
    boolean isSwedish = false;
    String expResult = "Total specimens";
    String result = instance.getChartYAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetChartYAxisLabelSv() {
    System.out.println("getChartYAxisLabel");
    boolean isSwedish = true;
    String expResult = "Antal föremål";
    String result = instance.getChartYAxisLabel(isSwedish);
    assertEquals(expResult, result);
  }

  
  /**
   * Test of getTotal method, of class CommonText.
   */
  @Test
  public void testGetTotal() {
    System.out.println("getTotal");
    boolean isSwedish = false;
    String expResult = "Total";
    String result = instance.getTotal(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getErrorReportTitle method, of class CommonText.
   */
  @Test
  public void testGetErrorReportTitleEn() {
    System.out.println("getErrorReportTitle");
    boolean isSwedish = false;
    String expResult = "Error Report";
    String result = instance.getErrorReportTitle(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetErrorReportTitleSv() {
    System.out.println("getErrorReportTitle");
    boolean isSwedish = true;
    String expResult = "Felrapport";
    String result = instance.getErrorReportTitle(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getRegNo method, of class CommonText.
   */
  @Test
  public void testGetRegNoEn() {
    System.out.println("getRegNo");
    boolean isSweden = false;
    String expResult = "Reg. no.: ";
    String result = instance.getRegNo(isSweden);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetRegNoSv() {
    System.out.println("getRegNo");
    boolean isSweden = true;
    String expResult = "Reg. nr.: ";
    String result = instance.getRegNo(isSweden);
    assertEquals(expResult, result);
  }

  /**
   * Test of getSpeciesName method, of class CommonText.
   */
  @Test
  public void testGetSpeciesNameEn() {
    System.out.println("getSpeciesName");
    boolean isSwedish = false;
    String expResult = "Species name: ";
    String result = instance.getSpeciesName(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetSpeciesNameSv() {
    System.out.println("getSpeciesName");
    boolean isSwedish = true;
    String expResult = "Artnamn: ";
    String result = instance.getSpeciesName(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getTypeInformation method, of class CommonText.
   */
  @Test
  public void testGetTypeInformationEn() {
    System.out.println("getTypeInformation");
    boolean isSwedish = false;
    String expResult = "Type information: ";
    String result = instance.getTypeInformation(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetTypeInformationSv() {
    System.out.println("getTypeInformation");
    boolean isSwedish = true;
    String expResult = "Typinformation: ";
    String result = instance.getTypeInformation(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getFamily method, of class CommonText.
   */
  @Test
  public void testGetFamilyEn() {
    System.out.println("getFamily");
    boolean isSwedish = false;
    String expResult = "Family: ";
    String result = instance.getFamily(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetFamilySv() {
    System.out.println("getFamily");
    boolean isSwedish = true;
    String expResult = "Familj: ";
    String result = instance.getFamily(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCollectors method, of class CommonText.
   */
  @Test
  public void testGetCollectorsEn() {
    System.out.println("getCollectors");
    boolean isSwedish = false;
    String expResult = "Collectors: ";
    String result = instance.getCollectors(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetCollectorsSv() {
    System.out.println("getCollectors");
    boolean isSwedish = true;
    String expResult = "Insamlare: ";
    String result = instance.getCollectors(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getDate method, of class CommonText.
   */
  @Test
  public void testGetDateEn() {
    System.out.println("getDate");
    boolean isSwedish = false;
    String expResult = "Date: ";
    String result = instance.getDate(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetDateSv() {
    System.out.println("getDate");
    boolean isSwedish = true;
    String expResult = "Datum: ";
    String result = instance.getDate(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getLocality method, of class CommonText.
   */
  @Test
  public void testGetLocalityEn() {
    System.out.println("getLocality");
    boolean isSwedish = false;
    String expResult = "Locality: ";
    String result = instance.getLocality(isSwedish);
    assertEquals(expResult, result);
  }
 
  @Test
  public void testGetLocalitySv() {
    System.out.println("getLocality");
    boolean isSwedish = true;
    String expResult = "Lokal: ";
    String result = instance.getLocality(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCoordinate method, of class CommonText.
   */
  @Test
  public void testGetCoordinate_booleanEn() {
    System.out.println("getCoordinate");
    boolean isSwedish = false;
    String expResult = "Coordinate: ";
    String result = instance.getCoordinate(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetCoordinate_booleanSv() {
    System.out.println("getCoordinate");
    boolean isSwedish = true;
    String expResult = "Koordinat: ";
    String result = instance.getCoordinate(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getContinent method, of class CommonText.
   */
  @Test
  public void testGetContinentEn() {
    System.out.println("getContinent");
    boolean isSwedish = false;
    String expResult = "Continent: ";
    String result = instance.getContinent(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetContinentSv() {
    System.out.println("getContinent");
    boolean isSwedish = true;
    String expResult = "Kontinent: ";
    String result = instance.getContinent(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getCountry method, of class CommonText.
   */
  @Test
  public void testGetCountryEn() {
    System.out.println("getCountry");
    boolean isSwedish = false;
    String expResult = "Country: ";
    String result = instance.getCountry(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetCountrySv() {
    System.out.println("getCountry");
    boolean isSwedish = true;
    String expResult = "Land: ";
    String result = instance.getCountry(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getOtherInformation method, of class CommonText.
   */
  @Test
  public void testGetOtherInformationEn() {
    System.out.println("getOtherInformation");
    boolean isSwedish = false;
    String expResult = "Other information: ";
    String result = instance.getOtherInformation(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetOtherInformationSv() {
    System.out.println("getOtherInformation");
    boolean isSwedish = true;
    String expResult = "&Ouml;vrig etikettinfo: ";
    String result = instance.getOtherInformation(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getDeterminer method, of class CommonText.
   */
  @Test
  public void testGetDeterminerEn() {
    System.out.println("getDeterminer");
    boolean isSwedish = false;
    String expResult = "Determiner: ";
    String result = instance.getDeterminer(isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetDeterminerSv() {
    System.out.println("getDeterminer");
    boolean isSwedish = true;
    String expResult = "Best&auml;mningar: ";
    String result = instance.getDeterminer(isSwedish);
    assertEquals(expResult, result);
  }

  
  /**
   * Test of getDescription method, of class CommonText.
   */
  @Test
  public void testGetDescriptionEn() {
    System.out.println("getDescription");
    boolean isSwedish = false;
    String expResult = "Description: ";
    String result = instance.getDescription(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetDescriptionSv() {
    System.out.println("getDescription");
    boolean isSwedish = true;
    String expResult = "Beskrivning: ";
    String result = instance.getDescription(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getReportBy method, of class CommonText.
   */
  @Test
  public void testGetReportByEn() {
    System.out.println("getReportBy");
    boolean isSwedish = false;
    String expResult = "Report by: ";
    String result = instance.getReportBy(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetReportBySv() {
    System.out.println("getReportBy");
    boolean isSwedish = true;
    String expResult = "Rapporterat av: ";
    String result = instance.getReportBy(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getHits method, of class CommonText.
   */
  @Test
  public void testGetHitsEn() {
    System.out.println("getHits");
    boolean isSwedish = false;
    String expResult = "hits";
    String result = instance.getHits(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetHitsSv() {
    System.out.println("getHits");
    boolean isSwedish = true;
    String expResult = "träffar";
    String result = instance.getHits(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getAllType method, of class CommonText.
   */
  @Test
  public void testGetAllTypeEn() {
    System.out.println("getAllType");
    boolean isSwedish = false;
    String expResult = "All type";
    String result = instance.getAllType(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetAllTypeSv() {
    System.out.println("getAllType");
    boolean isSwedish = true;
    String expResult = "alla typer";
    String result = instance.getAllType(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getSelected method, of class CommonText.
   */
  @Test
  public void testGetSelectedEn() {
    System.out.println("getSelected");
    boolean isSwedish = false;
    String expResult = "Selected";
    String result = instance.getSelected(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetSelectedSv() {
    System.out.println("getSelected");
    boolean isSwedish = true;
    String expResult = "Valda";
    String result = instance.getSelected(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getSelectMonth method, of class CommonText.
   */
  @Test
  public void testGetSelectMonthEn() {
    System.out.println("getSelectMonth");
    boolean isSwedish = false;
    String expResult = "Select month";
    String result = instance.getSelectMonth(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetSelectMonthSv() {
    System.out.println("getSelectMonth");
    boolean isSwedish = true;
    String expResult = "Välj månad";
    String result = instance.getSelectMonth(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getFromDate method, of class CommonText.
   */
  @Test
  public void testGetFromDateEn() {
    System.out.println("getFromDate");
    boolean isSwedish = false;
    String expResult = "[From date] ";
    String result = instance.getFromDate(isSwedish);
    assertEquals(expResult, result);
  }

  @Test
  public void testGetFromDateSv() {
    System.out.println("getFromDate");
    boolean isSwedish = true;
    String expResult = "[från datum] ";
    String result = instance.getFromDate(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getToDate method, of class CommonText.
   */
  @Test
  public void testGetToDateEn() {
    System.out.println("getToDate");
    boolean isSwedish = false;
    String expResult = " [To date] ";
    String result = instance.getToDate(isSwedish);
    assertEquals(expResult, result);
  }
 
  @Test
  public void testGetToDateSv() {
    System.out.println("getToDate");
    boolean isSwedish = true;
    String expResult = " [till datum] ";
    String result = instance.getToDate(isSwedish);
    assertEquals(expResult, result);
  }


  /**
   * Test of getFrom method, of class CommonText.
   */
  @Test
  public void testGetFromEn() {
    System.out.println("getFrom");
    boolean isSwedish = false;
    String expResult = " [Form] ";
    String result = instance.getFrom(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetFromSv() {
    System.out.println("getFrom");
    boolean isSwedish = true;
    String expResult = " [från] ";
    String result = instance.getFrom(isSwedish);
    assertEquals(expResult, result);
  }

  /**
   * Test of getTo method, of class CommonText.
   */
  @Test
  public void testGetToEn() {
    System.out.println("getTo");
    boolean isSwedish = false;
    String expResult = " [To] ";
    String result = instance.getTo(isSwedish);
    assertEquals(expResult, result);
  }
  
  @Test
  public void testGetToSv() {
    System.out.println("getTo");
    boolean isSwedish = true;
    String expResult = " [till] ";
    String result = instance.getTo(isSwedish);
    assertEquals(expResult, result);
  }
}
