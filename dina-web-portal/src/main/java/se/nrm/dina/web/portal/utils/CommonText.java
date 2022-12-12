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

    private final String emptyString = "";
    private final String emptySpace = " ";

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

    private static final String ALL_TYPE_EN = "All type";
    private static final String ALL_TYPE_SV = "alla typer";

    private static final String FROM_DATE_EN = "[From date] ";
    private static final String FROM_DATE_SV = "[från datum] ";

    private static final String TO_DATE_EN = " [To date] ";
    private static final String TO_DATE_SV = " [till datum] ";

    private static final String FROM_EN = " [Form] ";
    private static final String FROM_SV = " [från] ";
    private static final String TO_EN = " [To] ";
    private static final String TO_SV = " [till] ";

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
    private static final String DNA_KEY = "dna:";
    private final String inSwedenKey = "inSweden:";
    private final String collectionCodeKey = "collectionId:";
    private final String highTxKey = "higherTx:";
    private final String highTxKeyNotContain = "-higherTx:";

    private static final String ID_KEY = "id:";
    private final String mapKey = "map:";
    private static final String COORDINATE_KEY = "coordinate:";

    private static final String IMAGE_VIEW_KEY = "morphBankView:";
    private static final String MORPHBANK_ID_KEY = "morphbankId:";
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
    private final String catalogedDate = "catalogedDate";
    private static final String CREATED_DATE = "createdDate";
    private static final String IMAGE_VIEW = "morphBankView";
    private static final String IMAGE_ID = "morphbankImageId";
    private static final String MORPHBANK_ID = "morphbankId";
    private static final String SYNONYM = "synonym";
    private static final String AUTHOR = "author";
    private static final String COMMON_NAME = "commonName";
    private static final String COLLECTOR = "collector";
    private static final String HIGH_TAXA = "higherTx";
    private static final String LOCALITY = "locality";
    private static final String DETERMINER = "determiner";
    private static final String TYPESTATUS = "typeStatus";

    private static final String TX_SEARCH = "tx";

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

    private static final String IMAGE_TYPE_THUMB = "&imgType=thumbs";
    private static final String IMAGE_QUERY_ID = "?id=";
    private static final String IMAGE_TYPE_JPG = "&imgType=jpg";

    private static final String SV = "sv";
    private static final String EN = "en";

    private static final Map<String, String> FIELD_NAME_MAP = new HashMap<>();
    private static final Map<String, String> SEARCH_FIELD_MAP = new HashMap<>();
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

    public String getSearchField(String key) {
        return SEARCH_FIELD_MAP.get(key);
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
        return HIGH_TAXA;
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
        return ID_KEY;
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
        return IMAGE_VIEW_KEY;
    }

    public String getImageKey() {
        return imageKey;
    }

    public String getDNAKey() {
        return DNA_KEY;
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
        return IMAGE_TYPE_THUMB;
    }

    public String getImageTypeJpg() {
        return IMAGE_TYPE_JPG;
    }

    public String getImageQueryId() {
        return IMAGE_QUERY_ID;
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

    public String getAllType(boolean isSwedish) {
        return isSwedish ? ALL_TYPE_SV : ALL_TYPE_EN;
    }

    public String getSelected(boolean isSwedish) {
        return isSwedish ? SELECTED_SV : SELECTED_EN;
    }

    public String getSelectMonth(boolean isSwedish) {
        return isSwedish ? SELECT_MONTH_SV : SELECT_MONTH_EN;
    }

    public String getFromDate(boolean isSwedish) {
        return isSwedish ? FROM_DATE_SV : FROM_DATE_EN;
    }

    public String getToDate(boolean isSwedish) {
        return isSwedish ? TO_DATE_SV : TO_DATE_EN;
    }

    public String getFrom(boolean isSwedish) {
        return isSwedish ? FROM_SV : FROM_EN;
    }

    public String getTo(boolean isSwedish) {
        return isSwedish ? TO_SV : TO_EN;
    }

    public String getSessionAttColSearchQry() {
        return sessionAttColSearchQry;
    }
}
