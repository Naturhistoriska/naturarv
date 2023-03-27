package se.nrm.dina.web.portal.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author idali
 */
public class CommonText {

    private final String simpleSearchDefaultTextEn = "Search collections (species, genus, family, collectors, location, etc.)";
    private final String simpleSearchDefaultTextSv = "Sök i samlingar (art, släkte, familj, insamlare, plats etc.)";

    private final String simpleSearchCollectionDefaultTextEn1 = "Search ";
    private final String simpleSearchCollectionDefaultTextEn2 = " collection (species, genus, family, collectors, location, etc.)";
    
    private final String simpleSearchCollectionDefaultTextSv1 = "Sök i var ";
    private final String simpleSearchCollectionDefaultTextSv2 = " samling (art, släkte, familj, insamlare, plats etc.)";
    
    private final String emptyString = "";
    private final String emptySpace = " ";
    private final String underScore = "_";

    private final String nrmCode = "nrm*";
    private final String gnmCode = "gnm*";
    private final String nrmEn = "Swedish Museum of Natural History";
    private final String nrmSv = "Naturhistoriska riksmuseet";
    private final String gnmEn = "Gothenburg Natural History Museum";
    private final String gnmSv = "Göteborgs naturhistoriska museum";

    private final String monthChartTitleSv = "Registrerade föremål senaste 12 månaderna";
    private final String yearChartTitleSv = "Ackumulerat antal registrerade föremål";

    private final String monthChartTitleEn = "Registered specimens last 12 months";
    private final String yearChartTitleEn = "Cumulative number of registered specimens";

    private final String sessionAttColSearchQry = "collectionSearchQry";

    private final String monthChartAxisSv = "Månad";
    private final String monthChartAxisEn = "Month";

    private final String yearChartAxisSv = "År";
    private final String yearXChartAxisEn = "Year";

    private final String chartYaxisSv = "Antal föremål";
    private final String chartYaxisEn = "Total specimens";

    private final String errorReportTitleEn = "Error Report";
    private final String errorReportTitleSv = "Felrapport";

    private final String totalEn = "Total";
    private final String totalSv = "Totalt";

    private final String regNoEv = "Reg. no.: ";
    private final String regNoSv = "Reg. nr.: ";
    private final String speciesNameSv = "Artnamn: ";
    private final String speciesNameEn = "Species name: ";
    private final String typeInformationSv = "Typinformation: ";
    private final String typeInformationEn = "Type information: ";
    private final String familyEn = "Family: ";
    private final String familySv = "Familj: ";
    private final String collectorSv = "Insamlare: ";
    private final String collectorEn = "Collectors: ";
    private final String dateSv = "Datum: ";
    private final String dateEn = "Date: ";
    private final String localitySv = "Lokal: ";
    private final String localityEn = "Locality: ";
    private final String continentEn = "Continent: ";
    private final String continentSv = "Kontinent: ";
    private final String countryEn = "Country: ";
    private final String countrySv = "Land: ";
    private final String coordinateSv = "Koordinat: ";
    private final String coordinatesEn = "Coordinate: ";
    private final String otherInformationSv = "&Ouml;vrig etikettinfo: ";
    private final String otherInformationEn = "Other information: ";
    private static final String DETERMINER_SV = "Best&auml;mningar: ";
    private static final String DETERMINER_EN = "Determiner: ";
    private static final String DESCRIPTION_SV = "Beskrivning: ";
    private static final String DESCRIPTION_EN = "Description: ";
    private static final String REPORT_BY_SV = "Rapporterat av: ";
    private static final String REPORT_BY_EN = "Report by: ";

    private final String allTypeEn = "All type";
    private final String allTypeSv = "alla typer";

    private final String fromDateEn = "[From date] ";
    private final String fromDateSv = "[från datum] ";

    private final String toDateEn = " [To date] ";
    private final String toDateSv = " [till datum] ";

    private final String fromEn = " [Form] ";
    private final String fromSv = " [från] ";
    private final String toEn = " [To] ";
    private final String toSv = " [till] ";

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
    private final String catalogedMonth = "catalogedMonth";
    private static final String CATALOGED_MONTH_STRING = "catalogedMonthString";

    private static final String SORT_BY_SCORE = "score";

    private static final String WILD_SEARCH_QUERY = "q:*";
    private static final String WILD_SEARCH_TEXT = "*:*";
    private static final String WILD_CARD = "*";
    private final String imageKey = "image:";
    private final String isTypeKey = "isType:";
    private final String dnaKey = "dna:";
    private final String inSwedenKey = "inSweden:";
    private final String collectionCodeKey = "collectionId:";
    private final String highTxKey = "higherTx:";
    private final String highTxKeyNotContain = "-higherTx:";

    private final String idKey = "id:";
    private final String mapKey = "map:";
    private static final String COORDINATE_KEY = "coordinate:";

    private final String imageViewKey = "morphBankView:";
    private final String morphBankIdKey  = "morphbankId:";
    private final String associatedMediaKey = "associatedMedia"; 
    private static final String TEXT_FIELD = "text";
    private static final String TEXT_SEARCH = "textsearch";
    private static final String GEOPOINT = "geopoint";

    private final String id = "id";
    private final String collectionName = "collectionName";
    private final String collectionNameKey = "collectionName:";
    private final String collectionId = "collectionId";
    private static final String CATALOGED_YEAR = "catalogedYear";
    private final String taxonFullName = "txFullName";
    private static final String ACCESSION_NUMBER = "accessionNumber";
    private static final String STATION_FIELD = "stationFieldNumber";
    private static final String LOCATIONS = "locations";
    private static final String COORDINATE = "coordinate";
    private static final String CATALOG_NUMBER = "catalogNumber";
    private static final String cnKey = "cn:";
    private final String catalogedDate = "catalogedDate";
    private static final String CREATED_DATE = "createdDate";
    private final String imageView = "morphBankView";
    private static final String IMAGE_ID = "morphbankImageId";
    private final String morphBankId = "morphbankId";
    private static final String SYNONYM = "synonym";
    private static final String AUTHOR = "author";
    private static final String COMMON_NAME = "commonName";
    private static final String COLLECTOR = "collector";
    private final String highTaxa = "higherTx";
    private static final String LOCALITY = "locality";
    private static final String DETERMINER = "determiner";
    private static final String TYPESTATUS = "typeStatus";
 

    private static final String TX_SEARCH = "tx";

    private static final String DNA = "dna";
    private final String image = "image";
    private final String map = "map";
    private final String sweden = "sweden";
    private final String type = "type";
    private final String isType = "isType";
    private final String inSweden = "inSweden";

    private static final String ALL = "all";
    private static final String CONTAINS = "contains";
    private static final String EXACT = "exact";
    private static final String START_WITH = "startswith";

    private static final String AND = "and";
    private static final String NOT = "not";
    private static final String OR = "or";

//    private static final String COLOR_1 = "coloer1";
//    private static final String COLOR_2 = "coloer2";
//    private static final String COLOR_3 = "coloer3";
//    private static final String COLOR_4 = "coloer4";
//    private static final String COLOR_5 = "coloer5";
//    private static final String COLOR_6 = "coloer6";

    private static final String GROUP = "group";
    private static final String GROUP_FIELD = "group.field";
    private static final String GROUPS = "groups";
    private static final String GROUPED = "grouped";
    private static final String MATCHES = "matches";

    private final String imageTypeThumb = "&imgType=thumbs";
    private final String imageQueryId = "?id=";
    private final String imageTypeJpg = "&imgType=jpg";
    private final String imageDataset ="&dataset=";
    
    private final String searchInAllCollectionsEn = "Search in all collections";
    private final String searchInAllCollectionsSv = "Sök i alla samlingar";

    private static final String SV = "sv";
    private static final String EN = "en";

    private static final Map<String, String> FIELD_NAME_MAP = new HashMap<>();
    private static final Map<String, String> SEARCH_FIELD_MAP = new HashMap<>();
    private static final Map<String, String> COLLECTION_NAME_MAP = new HashMap<>();
    private static final Map<String, String> SEARCH_TEXT_SV_MAP = new HashMap<>();
    private static final Map<String, String> SEARCH_TEXT_EN_MAP = new HashMap<>();
    
    private String searchImageQuery = "image:true";
    
    private static CommonText instance = null;

    static {
        FIELD_NAME_MAP.put("tx_en", "Scientific name");
        FIELD_NAME_MAP.put("tx_sv", "Vetenskapligt namn");

        FIELD_NAME_MAP.put("ftx_en", "Classification");
        FIELD_NAME_MAP.put("ftx_sv", "Klassifikation");

        FIELD_NAME_MAP.put("eftx_en", "Determination");
        FIELD_NAME_MAP.put("eftx_sv", "Bestämning");

        FIELD_NAME_MAP.put("cm_en", "Common name");
        FIELD_NAME_MAP.put("cm_sv", "Svenska namn");
        FIELD_NAME_MAP.put("au_en", "Author");
        FIELD_NAME_MAP.put("au_sv", "Auktor");

        FIELD_NAME_MAP.put("auth_en", "Author");
        FIELD_NAME_MAP.put("auth_sv", "Auktor");

        FIELD_NAME_MAP.put("cn_en", "Catalog number");
        FIELD_NAME_MAP.put("cn_sv", "Cataloguenumber");

        FIELD_NAME_MAP.put("lc_en", "Locality");
        FIELD_NAME_MAP.put("lc_sv", "Lokal / Geografi");

        FIELD_NAME_MAP.put("sfn_en", "Station field number");
        FIELD_NAME_MAP.put("sfn_sv", "Station field number");

        FIELD_NAME_MAP.put("clt_en", "Collector");
        FIELD_NAME_MAP.put("clt_sv", "Insamlare av");
        FIELD_NAME_MAP.put("dtm_en", "Determiner");
        FIELD_NAME_MAP.put("dtm_sv", "Bestämd av");
        FIELD_NAME_MAP.put("aacc_en", "Accession");
        FIELD_NAME_MAP.put("acc_sv", "Accession");
        FIELD_NAME_MAP.put("ts_en", "Type status");
        FIELD_NAME_MAP.put("ts_sv", "Typ-status");
        
        FIELD_NAME_MAP.put("synonym_en", "Synonyms");
        FIELD_NAME_MAP.put("synonym_sv", "Synonymer");

        SEARCH_FIELD_MAP.put("textsearch", TEXT_FIELD);
        SEARCH_FIELD_MAP.put("auth", AUTHOR);
        SEARCH_FIELD_MAP.put("clt", COLLECTOR);
        SEARCH_FIELD_MAP.put("cm", COMMON_NAME);
        SEARCH_FIELD_MAP.put("dtm", DETERMINER);
        SEARCH_FIELD_MAP.put("lc", LOCALITY);
        SEARCH_FIELD_MAP.put("acc", ACCESSION_NUMBER);
        SEARCH_FIELD_MAP.put("sfn", STATION_FIELD);
        SEARCH_FIELD_MAP.put("cn", CATALOG_NUMBER);
        SEARCH_FIELD_MAP.put("ts", TYPESTATUS); 
        SEARCH_FIELD_MAP.put("synonym", SYNONYM); 
        
        COLLECTION_NAME_MAP.put("Paleozoology", "Paleozoologisk");
        COLLECTION_NAME_MAP.put("Paleobotany", "Paleobotanisk");
        COLLECTION_NAME_MAP.put("NRM Entomology Collection Objects", "Entomologisk");
        COLLECTION_NAME_MAP.put("Invertebrate main collection", "Evertebrater");
        COLLECTION_NAME_MAP.put("Invertebrate type collection", "Evertebrater typsamling");
        COLLECTION_NAME_MAP.put("NRM Mineralogy", "Mineralogisk"); 
        COLLECTION_NAME_MAP.put("NRM Nodules", "Noduler"); 
        COLLECTION_NAME_MAP.put("Fish", "Fisk"); 
        COLLECTION_NAME_MAP.put("Amphibians and reptiles", "Grod- och kräldjur"); 
        COLLECTION_NAME_MAP.put("Fungi/Lichens", "Svampar/Lavar"); 
        COLLECTION_NAME_MAP.put("Mosses", "Mossor"); 
        COLLECTION_NAME_MAP.put("Algae", "Alger"); 
        COLLECTION_NAME_MAP.put("Vascular Plants", "Kärlväxter"); 
        COLLECTION_NAME_MAP.put("Mammals", "Däggdjur"); 
        COLLECTION_NAME_MAP.put("NRM Isotope Geology", "Isotopgeologi"); 
        COLLECTION_NAME_MAP.put("Swedish Malaise Trap Project (SMTP) Collection Obj", "Swedish Malaise Tra...");
        COLLECTION_NAME_MAP.put("Swedish Malaise Trap Project (SMTP) Species Lists", "	Swedish Malaise Tra...");
        
        SEARCH_TEXT_SV_MAP.put("zoo", "Sök i våra zoologiska samlingar");
        SEARCH_TEXT_SV_MAP.put("Paleontology", "Sök i våra paleontologiska samlingar");
        SEARCH_TEXT_SV_MAP.put("PzVert", "Sök i vår samling av fossila ryggradsdjur");
        SEARCH_TEXT_SV_MAP.put("PzInvert", "Sök i vår samling av fossila ryggradslösa djur");
        SEARCH_TEXT_SV_MAP.put("Paleozoology", "Sök i våra paleozoologiska samlingar");
        SEARCH_TEXT_SV_MAP.put("Paleobotany", "Sök i vår samling av fossila växter (paleobotaniska samling)");
        SEARCH_TEXT_SV_MAP.put("NRM Entomology Collection Objects", "Sök i vår insektssamling (entomologiska samling)");
        SEARCH_TEXT_SV_MAP.put("Invertebrate main collection", "Sök i vår samling av ryggradslösa djur (evertebrater)");
        SEARCH_TEXT_SV_MAP.put("Invertebrate type collection", "Sök i vår typsamling av ryggradslösa djur");
        SEARCH_TEXT_SV_MAP.put("NRM Mineralogy", "Sök i våra mineralogiska samlingar"); 
        SEARCH_TEXT_SV_MAP.put("Swedish Malaise Trap Project (SMTP) Collection Obj", "Sök i våra Swedish Malaise Trap Project samlingar");
        SEARCH_TEXT_SV_MAP.put("Swedish Malaise Trap Project (SMTP) Species Lists", "Sök i våra Swedish Malaise Trap Project samlingar");
        SEARCH_TEXT_SV_MAP.put("NRM Nodules", "Sök i vår samling av noduler"); 
        SEARCH_TEXT_SV_MAP.put("NRM Isotope Geology", "Sök i vår samling av isotopgeologi"); 
        SEARCH_TEXT_SV_MAP.put("Fungi/Lichens", "Sök i vår samling av svampar/lavar"); 
        SEARCH_TEXT_SV_MAP.put("Mosses", "Sök i vår samling av mossor"); 
        SEARCH_TEXT_SV_MAP.put("Algae", "Sök i vår samling av alger"); 
        SEARCH_TEXT_SV_MAP.put("Vascular Plants", "Sök i vår samling av kärlväxter"); 
        SEARCH_TEXT_SV_MAP.put("Fish", "Sök i vår samling av fisk"); 
        SEARCH_TEXT_EN_MAP.put("Mammals", "Sök i vår samling av däggdjur"); 
        SEARCH_TEXT_SV_MAP.put("Amphibians and reptiles", "Sök i våra Grod- och kräldjur samlingar"); 
        
        
        SEARCH_TEXT_EN_MAP.put("zoo", "Search in our zoological collections");
        SEARCH_TEXT_EN_MAP.put("Paleontology", "Search in our paleontological collections");
        SEARCH_TEXT_EN_MAP.put("PzVert", "Search in our vertebrate fossil collection");
        SEARCH_TEXT_EN_MAP.put("PzInvert", "Search in our invertebrate fossil collection");
        SEARCH_TEXT_EN_MAP.put("Paleozoology", "Search in our paleozoology collections");
        SEARCH_TEXT_EN_MAP.put("Paleobotany", "Search in our plant fossils collection (paleobotanical collection)");
        SEARCH_TEXT_EN_MAP.put("NRM Entomology Collection Objects", "Search in our insect collection (entomology)");
        SEARCH_TEXT_EN_MAP.put("Invertebrate main collection", "Search in our invertebrate collection");
        SEARCH_TEXT_EN_MAP.put("Invertebrate type collection", "Search in our invertebrate type collection");
        SEARCH_TEXT_EN_MAP.put("NRM Mineralogy", "Search in our mineralogical collections"); 
        SEARCH_TEXT_EN_MAP.put("Swedish Malaise Trap Project (SMTP) Collection Obj", "Search in our Swedish Malaise Trap Project collections");
        SEARCH_TEXT_EN_MAP.put("Swedish Malaise Trap Project (SMTP) Species Lists", "Search in our Swedish Malaise Trap Project collections");
        SEARCH_TEXT_EN_MAP.put("NRM Nodules", "Search in our nodules collection"); 
        SEARCH_TEXT_EN_MAP.put("NRM Isotope Geology", "Search in our isotope geological collection"); 
        SEARCH_TEXT_EN_MAP.put("Fish", "Search in our fish collection"); 
        SEARCH_TEXT_EN_MAP.put("Fungi/Lichens", "Search in our fungi/lichens collection"); 
        SEARCH_TEXT_EN_MAP.put("Mosses", "Search in our mosses collection"); 
        SEARCH_TEXT_EN_MAP.put("Algae", "Search in our algae collection"); 
        SEARCH_TEXT_EN_MAP.put("Vascular Plants", "Search in our vascular plants collection"); 
        SEARCH_TEXT_EN_MAP.put("Mammals", "Search in our mammals collection"); 
        SEARCH_TEXT_EN_MAP.put("Amphibians and reptiles", "Search in our amphibians and reptiles collection"); 
    }

    public static synchronized CommonText getInstance() {
        if (instance == null) {
            instance = new CommonText();
        }
        return instance;
    }

    public String getFieldName(String key, boolean isSwedish) {
        key = isSwedish ? key + underScore + SV : key + underScore + EN;
        return FIELD_NAME_MAP.get(key);
    }

    public String getSearchField(String key) {
        return SEARCH_FIELD_MAP.get(key);
    }
    
    public String getCollectionSwedishName(String key) {
        return COLLECTION_NAME_MAP.get(key);
    }
    
    public String getSearchTextSvMap(String key) {
        return SEARCH_TEXT_SV_MAP.get(key);
    }
    
    public String getSearchTextEnMap(String key) {
        return SEARCH_TEXT_EN_MAP.get(key);
    }
    
    public String getSearchInAllCollections(boolean isSwedish ) {
        return isSwedish ? searchInAllCollectionsSv : searchInAllCollectionsEn;
    }

    public String getSv() {
        return SV;
    }

    public String getDna() {
        return DNA;
    }

    public String getImage() {
        return image;
    }

    public String getSweden() {
        return sweden;
    }

    public String getMap() {
        return map;
    }

    public String getType() {
        return type;
    }

    public String getIsType() {
        return isType;
    }

    public String getInSweden() {
        return inSweden;
    }

//    public String getColor1() {
//        return COLOR_1;
//    }
//
//    public String getColor2() {
//        return COLOR_2;
//    }
//
//    public String getColor3() {
//        return COLOR_3;
//    }
//
//    public String getColor4() {
//        return COLOR_4;
//    }
//
//    public String getColor5() {
//        return COLOR_5;
//    }
//
//    public String getColor6() {
//        return COLOR_6;
//    }

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
        return id;
    }

    public String getCoordinate() {
        return COORDINATE;
    }

    public String getCatalogNumber() {
        return CATALOG_NUMBER;
    }

    public String getCatalogedDate() {
        return catalogedDate;
    }

    public String getCreatedDate() {
        return CREATED_DATE;
    }

    public String getTaxonFullName() {
        return taxonFullName;
    }

    public String getHighTaxa() {
        return highTaxa;
    }

    public String getTxSearch() {
        return TX_SEARCH;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getLocations() {
        return LOCATIONS;
    }

    public String getCatalogedYear() {
        return CATALOGED_YEAR;
    }

    public String getMorphbankId() {
        return morphBankId;
    }

    public String getMorphbankIdKey() {
        return morphBankIdKey;
    }

    public String getImageView() {
        return imageView;
    }

    public String getImageId() {
        return IMAGE_ID;
    }
    
    public String getAssociatedMediaKey() {
        return associatedMediaKey;
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

    public String getAnd() {
        return AND;
    }

    public String getOr() {
        return OR;
    }

    public String getNot() {
        return NOT;
    }

    public String getNrmCode() {
        return nrmCode;
    }

    public String getGnmCode() {
        return gnmCode;
    }

    public String getSortByScore() {
        return SORT_BY_SCORE;
    }

    public String getWildCard() {
        return WILD_CARD;
    }

    public String getIdKey() {
        return idKey;
    }

    public String getMapKey() {
        return mapKey;
    }

    public String getCollectionNameKey() {
        return collectionNameKey;
    }

    public String getCollectionCodeKey() {
        return collectionCodeKey;
    }

    public String getHighTxKey() {
        return highTxKey;
    }

    public String getHighTxNotContainKey() {
        return highTxKeyNotContain;
    }

    public String getTextField() {
        return TEXT_FIELD;
    }

    public String getTextSearch() {
        return TEXT_SEARCH;
    }

    public String getCoordinateKey() {
        return COORDINATE_KEY;
    }

    public String getGeopoint() {
        return GEOPOINT;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getImageViewKey() {
        return imageViewKey;
    }

    public String getImageKey() {
        return imageKey;
    }

    public String getDNAKey() {
        return dnaKey;
    }

    public String getIsTypeKey() {
        return isTypeKey;
    }

    public String getInSwedenKey() {
        return inSwedenKey;
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
    
    public String getCnKey() {
        return cnKey;
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
        return catalogedMonth;
    }

    public String getCatalogedMonthString() {
        return CATALOGED_MONTH_STRING;
    }

    public String getAccessionNumber() {
        return ACCESSION_NUMBER;
    }

    public String getImageTypeThumb() {
        return imageTypeThumb;
    }

    public String getImageTypeJpg() {
        return imageTypeJpg;
    }
    
    public String getImageDataset() {
        return imageDataset;
    }

    public String getImageQueryId() {
        return imageQueryId;
    }

    public String getEmptyString() {
        return emptyString;
    }

    public String getEmptySpace() {
        return emptySpace;
    }

    public String getSearchDefaultText(boolean isSwedish) {
        return isSwedish ? simpleSearchDefaultTextSv : simpleSearchDefaultTextEn;
    }
    
    public String getSearchCollectionDefaultText1(boolean isSwedish) {
        return isSwedish ? simpleSearchCollectionDefaultTextSv1 : simpleSearchCollectionDefaultTextEn1;
    }

    public String getSearchCollectionDefaultText2(boolean isSwedish) {
        return isSwedish ? simpleSearchCollectionDefaultTextSv2 : simpleSearchCollectionDefaultTextEn2;
    }
       
    public String getNrmName(boolean isSwedeish) {
        return isSwedeish ? nrmSv : nrmEn;
    }

    public String getGnmName(boolean isSwedish) {
        return isSwedish ? gnmSv : gnmEn;
    }

    public String getInstitutionCode(String institutionName, boolean isSwedish) {
        return institutionName.equals(getNrmName(isSwedish)) ? nrmCode : gnmCode;
    }

    public String getMonthChartTitle(boolean isSwedish) {
        return isSwedish ? monthChartTitleSv : monthChartTitleEn;
    }

    public String getYearChartTitle(boolean isSwedish) {
        return isSwedish ? yearChartTitleSv : yearChartTitleEn;
    }

    public String getMonthChartXAxisLabel(boolean isSwedish) {
        return isSwedish ? monthChartAxisSv : monthChartAxisEn;
    }

    public String getYearChartXAxisLabel(boolean isSwedish) {
        return isSwedish ? yearChartAxisSv : yearXChartAxisEn;
    }

    public String getChartYAxisLabel(boolean isSwedish) {
        return isSwedish ? chartYaxisSv : chartYaxisEn;
    }

    public String getTotal(boolean isSwedish) {
        return isSwedish ? totalSv : totalEn;
    }

    public String getErrorReportTitle(boolean isSwedish) {
        return isSwedish ? errorReportTitleSv : errorReportTitleEn;
    }

    public String getRegNo(boolean isSweden) {
        return isSweden ? regNoSv : regNoEv;
    }

    public String getSpeciesName(boolean isSwedish) {
        return isSwedish ? speciesNameSv : speciesNameEn;
    }

    public String getTypeInformation(boolean isSwedish) {
        return isSwedish ? typeInformationSv : typeInformationEn;
    }

    public String getFamily(boolean isSwedish) {
        return isSwedish ? familySv : familyEn;
    }

    public String getCollectors(boolean isSwedish) {
        return isSwedish ? collectorSv : collectorEn;
    }

    public String getDate(boolean isSwedish) {
        return isSwedish ? dateSv : dateEn;
    }

    public String getLocality(boolean isSwedish) {
        return isSwedish ? localitySv : localityEn;
    }

    public String getCoordinate(boolean isSwedish) {
        return isSwedish ? coordinateSv : coordinatesEn;
    }

    public String getContinent(boolean isSwedish) {
        return isSwedish ? continentSv : continentEn;
    }

    public String getCountry(boolean isSwedish) {
        return isSwedish ? countrySv : countryEn;
    }

    public String getOtherInformation(boolean isSwedish) {
        return isSwedish ? otherInformationSv : otherInformationEn;
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

    public String getAllType(boolean isSwedish) {
        return isSwedish ? allTypeSv : allTypeEn;
    }

    public String getSelected(boolean isSwedish) {
        return isSwedish ? SELECTED_SV : SELECTED_EN;
    }

    public String getSelectMonth(boolean isSwedish) {
        return isSwedish ? SELECT_MONTH_SV : SELECT_MONTH_EN;
    }

    public String getFromDate(boolean isSwedish) {
        return isSwedish ? fromDateSv : fromDateEn;
    }

    public String getToDate(boolean isSwedish) {
        return isSwedish ? toDateSv : toDateEn;
    }

    public String getFrom(boolean isSwedish) {
        return isSwedish ? fromSv : fromEn;
    }

    public String getTo(boolean isSwedish) {
        return isSwedish ? toSv : toEn;
    }

    public String getSessionAttColSearchQry() {
        return sessionAttColSearchQry;
    }
    
    public String getImageSearchQuery() {
        return searchImageQuery;
    }
}
