/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author idali
 */
public class CommonText {

  private static final String SIMPLE_SEARCH_DEFAULT_TEXT_EN = "Search collections (species, genus, family, collectors, location, etc.)";
  private static final String SIMPLE_SEARCH_DEFAULT_TEXT_SV = "Sök i samlingar (art, släkte, familj, insamlare, plats etc.)";
  
  private static final String EMPTY_STRING = "";
  private static final String EMPTY_SPACE = " ";

  private static final String NRM_CODE = "nrm*";
  private static final String GNM_CODE = "gnm*";
  private static final String NRM_EN = "Swedish Museum of Natural History";
  private static final String NRM_SV = "Naturhistoriska riksmuseet";
  private static final String GNM_EN = "Gothenburg Natural History Museum";
  private static final String GNM_SV = "Göteborgs naturhistoriska museum";

  private final static String MONTH_CHART_TITLE_SV = "Registrerade föremål senaste 12 månaderna";
  private final static String YEAR_CHART_TITLE_SV = "Ackumulerat antal registrerade föremål";

  private final static String MONTH_CHART_TITLE_EN = "Registered specimens last 12 months";
  private final static String YEAR_CHART_TITLE_EN = "Cumulative number of registered specimens";

  private static final String MONTH_CHART_AXIS_SV = "Månad";
  private static final String MONTH_CHART_AXIS_EN = "Month";

  private static final String YEAR_CHART_AXIS_SV = "År";
  private static final String YEAR_CHART_AXIS_EN = "Year";

  private static final String CHART_YAXIS_SV = "Antal föremål";
  private static final String CHART_YAXIS_EN = "Total specimens";

  private static final String ERROR_REPORT_TITLE_EN = "Error Report";
  private static final String ERROR_REPORT_TITLE_SV = "Felrapport";

  private static final String TOTAL_EN = "Total";
  private static final String TOTAL_SV = "Totalt";

  private static final String REG_NO_EN = "Reg. no.: ";
  private static final String REG_NO_SV = "Reg. nr.: ";
  private static final String SPECIES_NAME_SV = "Artnamn: ";
  private static final String SPECIES_NAME_EN = "Species name: ";
  private static final String TYPE_INFORMATION_SV = "Typinformation: ";
  private static final String TYPE_INFORMATION_EN = "Type information: ";
  private static final String FAMILY_EN = "Family: ";
  private static final String FAMILY_SV = "Familj: ";
  private static final String COLLECTORS_SV = "Insamlare: ";
  private static final String COLLECTORS_EN = "Collectors: ";
  private static final String DATE_SV = "Datum: ";
  private static final String DATE_EN = "Date: ";
  private static final String LOCALITY_SV = "Lokal: ";
  private static final String LOCALITY_EN = "Locality: ";
  private static final String CONTINENT_EN = "Continent: ";
  private static final String CONTINENT_SV = "Kontinent: ";
  private static final String COUNTRY_EN = "Country: ";
  private static final String COUNTRY_SV = "Land: ";
  private static final String COORDINATE_SV = "Koordinat: ";
  private static final String COORDINATE_EN = "Coordinate: ";
  private static final String OTHER_INFORMATION_SV = "&Ouml;vrig etikettinfo: ";
  private static final String OTHER_INFORMATION_EN = "Other information: ";
  private static final String DETERMINER_SV = "Best&auml;mningar: ";
  private static final String DETERMINER_EN = "Determiner: ";
  private static final String DESCRIPTION_SV = "Beskrivning: ";
  private static final String DESCRIPTION_EN = "Description: ";
  private static final String REPORT_BY_SV = "Rapporterat av: ";
  private static final String REPORT_BY_EN = "Report by: ";

  private static final String HITS_EN = "hits";
  private static final String HITS_SV = "träffar";
  private static final String SELECTED_EN = "Selected";
  private static final String SELECTED_SV = "Valda";

  private static final String SELECT_MONTH_EN = "Select month";
  private static final String SELECT_MONTH_SV = "Välj månad";
  private static final String LOCALE = "locale";
  private static final String STATISTIC = "statistic";
  private static final String MONTH_CHART_DATA = "monthChartData";
  private static final String YEAR_CHART_DATA = "yearChartData";
  private static final String COLLECTIONS_MONTH_CHART_DATA = "collectionsMonthChartData";
  private static final String COLLECTIONS_YEAR_CHART_DATA = "collectionsYearChartData";
  private static final String CATALOGED_MONTH = "catalogedMonth";
  private static final String CATALOGED_MONTH_STRING = "catalogedMonthString";

  private static final String SORT_BY_SCORE = "score";

  private static final String WILD_SEARCH_QUERY = "q:*";
  private static final String WILD_SEARCH_TEXT = "*:*";
  private static final String WILD_CARD = "*";
  private static final String IMAGE_KEY = "image:";
  private static final String TYPE_KEY = "isType:";
  private static final String DNA_KEY = "dna:";
  private static final String SWEDEN_KEY = "inSweden:";
  private static final String COLLECTION_CODE_KEY = "collectionId:";
  private static final String COLLECTION_NAME_KEY = "collectionName:";
  private static final String ID_KEY = "id:";
//  private static final String GEO_KEY = "geo:";
  private static final String MAP_KEY = "map:";
  private static final String COORDINATE_KEY = "coordinate:";

  private static final String IMAGE_VIEW_KEY = "morphBankView:";
  private static final String MORPHBANK_ID_KEY = "morphbankId:";
  private static final String TEXT_FIELD = "text";
  private static final String GEOPOINT = "geopoint";

  private static final String ID = "id";
  private static final String COLLECTION_NAME = "collectionName";
  private static final String COLLECTION_ID = "collectionId"; 
  private static final String CATALOGED_YEAR = "catalogedYear";
  private static final String TAXON_FULL_NAME = "txFullName";
  private static final String ACCESSION_NUMBER = "accessionNumber";
  private static final String LOCATIONS = "locations"; 
  private static final String COORDINATE = "coordinate";
  private static final String CATALOG_NUMBER = "catalogNumber";
  private static final String CATALOGED_DATE = "catalogedDate";
  private static final String CREATED_DATE = "createdDate";
  private static final String IMAGE_VIEW = "morphBankView";
  private static final String IMAGE_ID = "morphbankImageId";
  private static final String MORPHBANK_ID = "morphbankId";
  private static final String SYNONYM = "synonym"; 
  private static final String AUTHOR = "author";
  private static final String COMMON_NAME = "commonName";
  private static final String COLLECTOR = "collector";
  private static final String HIGH_TAXA = "higherTx";
  
  private static final String TX_SEARCH = "tx";
  private static final String LOCALITY_SEARCH = "lc";
  private static final String ACCESSION_SEARCH = "acc";
  private static final String DETERMINER_SEARCH = "dtm";
  private static final String STATION_FIELD_SEARCH = "sfn";
  private static final String AUTHOR_SEARCH = "auth";
  private static final String CATALOG_NUMBER_SEARCH = "cn";
  private static final String COLLECTOR_SEARCH = "clt";
  
  
  private static final String DNA = "dna";
  private static final String IMAGE = "image";
  private static final String MAP = "map";
  private static final String SWEDEN = "sweden";
  private static final String TYPE = "type";
  private static final String IS_TYPE = "isType";
  private static final String IN_SWEDEN = "inSweden";
  
  private static final String ALL = "all";
  private static final String CONTAINS = "contains";
  private static final String EXACT = "exact"; 
  private static final String START_WITH = "startswith";
  
  private static final String AND = "and";
  private static final String NOT = "not";
  private static final String OR = "or";

  private static final String COLOR_1 = "coloer1";
  private static final String COLOR_2 = "coloer2";
  private static final String COLOR_3 = "coloer3";
  private static final String COLOR_4 = "coloer4";
  private static final String COLOR_5 = "coloer5";
  private static final String COLOR_6 = "coloer6";

  private static final String GROUP = "group";
  private static final String GROUP_FIELD = "group.field";
  private static final String GROUPS = "groups";
  private static final String GROUPED = "grouped";
  private static final String MATCHES = "matches";

  private static final String IMAGE_TYPE_THUMB = "&imgType=thumb";
  private static final String IMAGE_QUERY_ID = "?id=";
  private static final String IMAGE_TYPE_JPG = "&imgType=jpg";
  
  
  
  
  private static final String SV = "sv";
  private static final String EN = "en";

  private static final Map<String, String> FIELD_NAME_MAP = new HashMap<>();
  private static CommonText instance = null;

  static {
    FIELD_NAME_MAP.put("tx_en", "Scientific name");
    FIELD_NAME_MAP.put("tx_sv", "Vetenskapligt namn");

    FIELD_NAME_MAP.put("ftx_en", "Classification");
    FIELD_NAME_MAP.put("ftx_sv", "Klassifikation");

    FIELD_NAME_MAP.put("eftx_en", "Determination");
    FIELD_NAME_MAP.put("eftx_sv", "Bestämning");

    FIELD_NAME_MAP.put("commonName_en", "Common name");
    FIELD_NAME_MAP.put("commonName_sv", "Svenska namn");
    FIELD_NAME_MAP.put("au_en", "Author");
    FIELD_NAME_MAP.put("au_sv", "Auktor");

    FIELD_NAME_MAP.put("author_en", "Author");
    FIELD_NAME_MAP.put("author_sv", "Auktor");

    FIELD_NAME_MAP.put("catalogNumber_en", "Catalog number");
    FIELD_NAME_MAP.put("catalogNumber_sv", "Cataloguenumber");

    FIELD_NAME_MAP.put("locality_en", "Locality");
    FIELD_NAME_MAP.put("locality_sv", "Lokal / Geografi");

    FIELD_NAME_MAP.put("stationFieldNumber_en", "Station field number");
    FIELD_NAME_MAP.put("stationFieldNumber_sv", "Station field number");

    FIELD_NAME_MAP.put("collector_en", "Collector");
    FIELD_NAME_MAP.put("collector_sv", "Insamlare av");
    FIELD_NAME_MAP.put("determiner_en", "Determiner");
    FIELD_NAME_MAP.put("determiner_sv", "Bestämd av");
    FIELD_NAME_MAP.put("accessionNumber_en", "Accession");
    FIELD_NAME_MAP.put("accessionNumber_sv", "Accession"); 
  }

  public static synchronized CommonText getInstance() {
    if (instance == null) {
      instance = new CommonText();
    }
    return instance;
  }
  
  public String getFieldName(String key, boolean isSwedish) {
    key = isSwedish ? key + "_" + SV : key + "_" + EN;
    return FIELD_NAME_MAP.get(key);
  }
  
  public String getSv() {
    return SV;
  }
  
  public String getDna() {
    return DNA;
  }
  
  public String getImage() {
    return IMAGE;
  }
  
  public String getSweden() {
    return SWEDEN;
  }
  
  public String getMap() {
    return MAP;
  }
  
  public String getType() {
    return TYPE;
  }
  
  public String getIsType() {
    return IS_TYPE;
  }
  
  public String getInSweden() {
    return IN_SWEDEN;
  }

  public String getColor1() {
    return COLOR_1;
  }

  public String getColor2() {
    return COLOR_2;
  }

  public String getColor3() {
    return COLOR_3;
  }

  public String getColor4() {
    return COLOR_4;
  }

  public String getColor5() {
    return COLOR_5;
  }

  public String getColor6() {
    return COLOR_6;
  }

  public String getGroup() {
    return GROUP;
  }

  public String getGroupField() {
    return GROUP_FIELD;
  }

  public String getGroups() {
    return GROUPS;
  }

  public String getGrouped() {
    return GROUPED;
  }

  public String getMatches() {
    return MATCHES;
  }

  public String getId() {
    return ID;
  }

  public String getCoordinate() {
    return COORDINATE;
  }

  public String getCatalogNumber() {
    return CATALOG_NUMBER;
  }
  
  public String getCatalogedDate() {
    return CATALOGED_DATE;
  }
  
  public String getCreatedDate() {
    return CREATED_DATE;
  }
  
  public String getCommonName() {
    return COMMON_NAME;
  }
  
  public String getCollector() {
    return COLLECTOR;
  }

  public String getTaxonFullName() {
    return TAXON_FULL_NAME;
  }
  
  public String getHighTaxa() {
    return HIGH_TAXA;
  }
  
  public String getTxSearch() {
    return TX_SEARCH;
  }
  
  public String getLocalitySearch() {
    return LOCALITY_SEARCH;
  }
  
  public String getCollectorSearch() {
    return COLLECTOR_SEARCH;
  }
  
  public String getDeterminerSearch() {
    return DETERMINER_SEARCH;
  }
  
  public String getCatalogNumberSearch() {
    return CATALOG_NUMBER_SEARCH;
  }
  
  public String getAuthorSearch() {
    return AUTHOR_SEARCH;
  }
  
  public String getAccessionSearch() {
    return ACCESSION_SEARCH;
  }
  
  public String getStationFieldSearch() {
    return STATION_FIELD_SEARCH;
  }

  public String getCollectionId() {
    return COLLECTION_ID;
  }

  public String getLocations() {
    return LOCATIONS;
  }

  public String getCatalogedYear() {
    return CATALOGED_YEAR;
  }

  public String getMorphbankId() {
    return MORPHBANK_ID;
  }

  public String getMorphbankIdKey() {
    return MORPHBANK_ID_KEY;
  }

  public String getImageView() {
    return IMAGE_VIEW;
  }

  public String getImageId() {
    return IMAGE_ID;
  }

  public String getAuthor() {
    return AUTHOR;
  }
  
  public String getAll() {
    return ALL;
  }
  
  public String getContains() {
    return CONTAINS;
  }
  
  public String getExact() {
    return EXACT;
  }
  
  public String getStartWith() {
    return START_WITH;
  }

  public String getAnd(){
    return AND;
  }
  
  public String getOr() {
   return OR; 
  }
  
  public String getNot() {
    return NOT;
  }
  
  public String getNrmCode() {
    return NRM_CODE;
  }

  public String getGnmCode() {
    return GNM_CODE;
  }

  public String getSortByScore() {
    return SORT_BY_SCORE;
  }

  public String getWildCard() {
    return WILD_CARD;
  }

  public String getIdKey() {
    return ID_KEY;
  }

//  public String getGeoKey() {
//    return GEO_KEY;
//  }

  public String getMapKey() {
    return MAP_KEY;
  }

  public String getCollectionNameKey() {
    return COLLECTION_NAME_KEY;
  }

  public String getCollectionCodeKey() {
    return COLLECTION_CODE_KEY;
  }

  public String getTextField() {
    return TEXT_FIELD;
  }

  public String getCoordinateKey() {
    return COORDINATE_KEY;
  }

  public String getGeopoint() {
    return GEOPOINT;
  }

  public String getCollectionName() {
    return COLLECTION_NAME;
  }

  public String getImageViewKey() {
    return IMAGE_VIEW_KEY;
  }

  public String getImageKey() {
    return IMAGE_KEY;
  }

  public String getDNAKey() {
    return DNA_KEY;
  }

  public String getTypeKey() {
    return TYPE_KEY;
  }

  public String getSwedenKey() {
    return SWEDEN_KEY;
  }

  public String getWildSearchQuery() {
    return WILD_SEARCH_QUERY;
  }
  
  public String getWildSearchText() {
    return WILD_SEARCH_TEXT;
  }

  public String getLocale() {
    return LOCALE;
  }

  public String getStatistic() {
    return STATISTIC;
  }

  public String getSynonmy() {
    return SYNONYM;
  }
  
  public String getMonthChartData() {
    return MONTH_CHART_DATA;
  }

  public String getYearChartData() {
    return YEAR_CHART_DATA;
  }

  public String getCollectionsMonthChart() {
    return COLLECTIONS_MONTH_CHART_DATA;
  }

  public String getCollectionsYearChart() {
    return COLLECTIONS_YEAR_CHART_DATA;
  }

  public String getCatalogedMonth() {
    return CATALOGED_MONTH;
  }

  public String getCatalogedMonthString() {
    return CATALOGED_MONTH_STRING;
  }
  
  public String getAccessionNumber() {
    return ACCESSION_NUMBER;
  }

  public String getImageTypeThumb() {
    return IMAGE_TYPE_THUMB;
  }

  public String getImageTypeJpg() {
    return IMAGE_TYPE_JPG;
  }

  public String getImageQueryId() {
    return IMAGE_QUERY_ID;
  }
  
  public String getEmptyString() {
    return EMPTY_STRING;
  }
  
  public String getEmptySpace() {
    return EMPTY_SPACE;
  }

  public String getSearchDefaultText(boolean isSwedish) {
    return isSwedish ? SIMPLE_SEARCH_DEFAULT_TEXT_SV : SIMPLE_SEARCH_DEFAULT_TEXT_EN;
  }

  public String getNrmName(boolean isSwedeish) {
    return isSwedeish ? NRM_SV : NRM_EN;
  }

  public String getGnmName(boolean isSwedish) {
    return isSwedish ? GNM_SV : GNM_EN;
  }

  public String getInstitutionCode(String institutionName, boolean isSwedish) {
    return institutionName.equals(getNrmName(isSwedish)) ? NRM_CODE : GNM_CODE;
  }
  
  public String getMonthChartTitle(boolean isSwedish) {
    return isSwedish ? MONTH_CHART_TITLE_SV : MONTH_CHART_TITLE_EN;
  }

  public String getYearChartTitle(boolean isSwedish) {
    return isSwedish ? YEAR_CHART_TITLE_SV : YEAR_CHART_TITLE_EN;
  }

  public String getMonthChartXAxisLabel(boolean isSwedish) {
    return isSwedish ? MONTH_CHART_AXIS_SV : MONTH_CHART_AXIS_EN;
  }

  public String getYearChartXAxisLabel(boolean isSwedish) {
    return isSwedish ? YEAR_CHART_AXIS_SV : YEAR_CHART_AXIS_EN;
  }

  public String getChartYAxisLabel(boolean isSwedish) {
    return isSwedish ? CHART_YAXIS_SV : CHART_YAXIS_EN;
  }

  public String getTotal(boolean isSwedish) {
    return isSwedish ? TOTAL_SV : TOTAL_EN;
  }

  public String getErrorReportTitle(boolean isSwedish) {
    return isSwedish ? ERROR_REPORT_TITLE_SV : ERROR_REPORT_TITLE_EN;
  }

  public String getRegNo(boolean isSweden) {
    return isSweden ? REG_NO_SV : REG_NO_EN;
  }

  public String getSpeciesName(boolean isSwedish) {
    return isSwedish ? SPECIES_NAME_SV : SPECIES_NAME_EN;
  }

  public String getTypeInformation(boolean isSwedish) {
    return isSwedish ? TYPE_INFORMATION_SV : TYPE_INFORMATION_EN;
  }

  public String getFamily(boolean isSwedish) {
    return isSwedish ? FAMILY_SV : FAMILY_EN;
  }

  public String getCollectors(boolean isSwedish) {
    return isSwedish ? COLLECTORS_SV : COLLECTORS_EN;
  }

  public String getDate(boolean isSwedish) {
    return isSwedish ? DATE_SV : DATE_EN;
  }

  public String getLocality(boolean isSwedish) {
    return isSwedish ? LOCALITY_SV : LOCALITY_EN;
  }

  public String getCoordinate(boolean isSwedish) {
    return isSwedish ? COORDINATE_SV : COORDINATE_EN;
  }

  public String getContinent(boolean isSwedish) {
    return isSwedish ? CONTINENT_SV : CONTINENT_EN;
  }

  public String getCountry(boolean isSwedish) {
    return isSwedish ? COUNTRY_SV : COUNTRY_EN;
  }

  public String getOtherInformation(boolean isSwedish) {
    return isSwedish ? OTHER_INFORMATION_SV : OTHER_INFORMATION_EN;
  }

  public String getDeterminer(boolean isSwedish) {
    return isSwedish ? DETERMINER_SV : DETERMINER_EN;
  }

  public String getDescription(boolean isSwedish) {
    return isSwedish ? DESCRIPTION_SV : DESCRIPTION_EN;
  }

  public String getReportBy(boolean isSwedish) {
    return isSwedish ? REPORT_BY_SV : REPORT_BY_EN;
  }

  public String getHits(boolean isSwedish) {
    return isSwedish ? HITS_SV : HITS_EN;
  }

  public String getSelected(boolean isSwedish) {
    return isSwedish ? SELECTED_SV : SELECTED_EN;
  }
  
  public String getSelectMonth(boolean isSwedish) {
    return isSwedish ? SELECT_MONTH_SV : SELECT_MONTH_EN;
  }
}

//  private final static String JAN = "Jan";
//  private final static String FEB = "Feb";
//  private final static String MAR = "Mar";
//  private final static String APR = "Apr";
//  private final static String MAY = "May";
//  private final static String JUN = "Jun";
//  private final static String JUL = "Jul";
//  private final static String AUG = "Aug";
//  private final static String SEP = "Sep";
//  private final static String OCT = "Oct";
//  private final static String NOV = "Nov";
//  private final static String DEC = "Dec";
//  private final static String JAN_SHORT = "Jan";
//  private final static String FEB_SHORT = "Feb";
//  private final static String MAR_SHORT = "Mar";
//  private final static String APR_SHORT = "Apr";
//  private final static String MAY_SHORT = "May";
//  private final static String JUN_SHORT = "Jun";
//  private final static String JUL_SHORT = "Jul";
//  private final static String AUG_SHORT = "Aug";
//  private final static String SEP_SHORT = "Sep";
//  private final static String OCT_SHORT = "Oct";
//  private final static String NOV_SHORT = "Nov";
//  private final static String DEC_SHORT = "Dec";
//
//  private final static String JAN_SV_SHORT = "jan";
//  private final static String FEB_SV_SHORT = "feb";
//  private final static String MAR_SV_SHORT = "mar";
//  private final static String APR_SV_SHORT = "apr";
//  private final static String MAY_SV_SHORT = "maj";
//  private final static String JUN_SV_SHORT = "jun";
//  private final static String JUL_SV_SHORT = "jul";
//  private final static String AUG_SV_SHORT = "aug";
//  private final static String SEP_SV_SHORT = "sep";
//  private final static String OCT_SV_SHORT = "okt";
//  private final static String NOV_SV_SHORT = "nov";
//  private final static String DEC_SV_SHORT = "dec";
//  private String languageMonthMatch(String mon, boolean isSwedish) {
//
//    switch (mon) {
//      case JAN_SHORT:
//      case JAN_SV_SHORT:
//        return isSwedish ? JAN_SV_SHORT : JAN_SHORT;
//      case FEB_SHORT:
//      case FEB_SV_SHORT:
//        return isSwedish ? FEB_SV_SHORT : FEB_SHORT;
//      case MAR_SHORT:
//      case MAR_SV_SHORT:
//        return isSwedish ? MAR_SV_SHORT : MAR_SHORT;
//      case APR_SHORT:
//      case APR_SV_SHORT:
//        return isSwedish ? APR_SV_SHORT : APR_SHORT;
//      case MAY_SHORT:
//      case MAY_SV_SHORT:
//        return isSwedish ? MAY_SV_SHORT : MAR_SHORT;
//      case JUN_SHORT:
//      case JUN_SV_SHORT:
//        return isSwedish ? JUN_SV_SHORT : JUN_SHORT;
//      case JUL_SHORT:
//      case JUL_SV_SHORT:
//        return isSwedish ? JUL_SV_SHORT : JUL_SHORT;
//      case AUG_SHORT:
//      case AUG_SV_SHORT:
//        return isSwedish ? AUG_SV_SHORT : AUG_SHORT;
//      case SEP_SHORT:
//      case SEP_SV_SHORT:
//        return isSwedish ? SEP_SV_SHORT : SEP_SHORT;
//      case OCT_SHORT:
//      case OCT_SV_SHORT:
//        return isSwedish ? OCT_SV_SHORT : OCT_SHORT;
//      case NOV_SHORT:
//      case NOV_SV_SHORT:
//        return isSwedish ? NOV_SV_SHORT : NOV_SHORT;
//      case DEC_SHORT:
//      case DEC_SV_SHORT:
//        return isSwedish ? DEC_SV_SHORT : DEC_SHORT;
//      default:
//        return mon;
//    }
//  }
