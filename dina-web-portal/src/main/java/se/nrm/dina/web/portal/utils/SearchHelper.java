package se.nrm.dina.web.portal.utils;

import java.nio.charset.Charset;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
public class SearchHelper {

  private final String plusSign = "+";
  private final String minusSign = "-";
  private final String preWildCard = ":*";
  private final String postWildCard = "* ";
  private final String querySeparator = ":";
  private final String comma = ",";
   
  private final String taxonSearch = "ftx";
  private final String startDateKey = "startDate";
  private final String notStartDateKey = "-startDate";
  private final String andStartDateKey = "+startDate";
  private final String totalCount = "Total count: ";
  private final String total = "Total: ";
  private final String dashDash = " -- ";

  private final String fromZoom = ":00Z";
  private final String toZoom = "Z";

  private final String dayOfTheYear = "dayOfTheYear:";
  private final String squareStart = "[";
  private final String squareEnd = "]";
  private final String to = " TO ";
  private final String startBlock = "(";
  private final String endBlock = ")";
  private final String newLine = "\n";
  private final String tab = "\t";
  
  private final String usAscii = "US-ASCII";
  private final String inCombiningDiacriticalMarks = "\\p{InCombiningDiacriticalMarks}+";

  private StringBuilder searchTextSb;
  private StringBuilder advanceSearchText;
  private StringBuilder sessionSb;
  private StringBuilder classificationSb;
  private StringBuilder determinationSb;
  private StringBuilder imageSb;
  private StringBuilder dateRangeSb; 
  private StringBuilder searchRegionSb;
  private StringBuilder markTitleSb;

  private static SearchHelper instance = null;

  public static SearchHelper getInstance() {
    synchronized (SearchHelper.class) {
      if (instance == null) {
        instance = new SearchHelper();
      }
    }
    return instance;
  }

  /**
   * Map diacritical marks to US-ASCII
   *
   * @param string
   * @return String
   */
  public String unAccent(String string) {
    if (Charset.forName(usAscii).newEncoder().canEncode(string)) {
      return string.toLowerCase();
    }
    String temp = Normalizer.normalize(string, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile(inCombiningDiacriticalMarks);
    
    return pattern.matcher(temp).replaceAll("").toLowerCase();
  }

  /**
   * Build a search string
   *
   * @param text
   * @return String
   */
  public String buildFullSearchText(String text) {
    searchTextSb = new StringBuilder();

    // if text is null or empty string, or text is wildcard search with wildcard
    if (text == null || text.isEmpty()) {
      return CommonText.getInstance().getWildSearchText();
    }
    if (text.equals(CommonText.getInstance().getWildCard())) {
      return CommonText.getInstance().getWildSearchText();
    }

    Arrays.asList(StringUtils.split(text))
            .stream()
            .filter(s -> !s.isEmpty())
            .forEach(s -> {
              searchTextSb.append(plusSign);
              searchTextSb.append(CommonText.getInstance().getTextSearch());
              searchTextSb.append(preWildCard);
              searchTextSb.append(s);
              searchTextSb.append(postWildCard);
            });
    return searchTextSb.toString().trim();
  } 
  
  /**
   * Build search string
   *
   * @param data - QueryData
   * @return String
   */
  public String buildSearchString(QueryData data) {
    String value = HelpClass.getInstance().resetValue(data.getValue());
    if (value == null || value.equals(CommonText.getInstance().getWildCard())) {
      return buildWildCardSearch(data.getField());
    }

    String field = data.getField();
    if (field.contains(taxonSearch)) {
      field = CommonText.getInstance().getTxSearch();
    }
    String method = data.getContent();
    if (method.equals(CommonText.getInstance().getExact())) {
      return buildExactSearch(value, field);
    }
    return method.equals(CommonText.getInstance().getStartWith())
            ? buildStartsWithSearch(value, field) : buildContainsTextSearch(value, field);
  }

  private String buildWildCardSearch(String field) {
    advanceSearchText = new StringBuilder();
    advanceSearchText.append(field);
    advanceSearchText.append(querySeparator);
    advanceSearchText.append(CommonText.getInstance().getWildCard());
    return advanceSearchText.toString();
  }

  /**
   * Build search string for exact search
   *
   * @param text - Text to search
   * @param field - Solr index field
   * @return String
   */
  public String buildExactSearch(String text, String field) {
    advanceSearchText = new StringBuilder();
    advanceSearchText.append(field);
    advanceSearchText.append(querySeparator);
    advanceSearchText.append("\"");
    advanceSearchText.append(text);
    advanceSearchText.append("\"");
    return advanceSearchText.toString();
  }

  /**
   * Build search string for start with search
   *
   * @param text - Text for search
   * @param field - Solr index field
   * @return String
   */
  public String buildStartsWithSearch(String text, String field) {
    advanceSearchText = new StringBuilder();

    text = HelpClass.getInstance().replaceChars(text);
    String[] strings = text.split(CommonText.getInstance().getEmptySpace());

    advanceSearchText.append(plusSign);
    advanceSearchText.append(field);
    advanceSearchText.append(querySeparator);
    advanceSearchText.append(strings[0]);
    advanceSearchText.append(postWildCard);

    if (!text.equals(CommonText.getInstance().getWildCard())) {
      IntStream.range(1, strings.length)
              .forEach(i -> {
                if (!strings[i].isEmpty()) {
                  advanceSearchText.append(plusSign);
                  advanceSearchText.append(field);
                  advanceSearchText.append(preWildCard);
                  advanceSearchText.append(strings[i]);
                  advanceSearchText.append(postWildCard);
                }
              });
    }
    return advanceSearchText.toString().trim();
  }

  /**
   * Build search string for contains text search
   *
   * @param text - String. Text to search
   * @param field - String. Solr index field
   * @return String
   */
  public String buildContainsTextSearch(String text, String field) {

    text = HelpClass.getInstance().replaceChars(text);
    advanceSearchText = new StringBuilder();
    Arrays.asList(text.split(CommonText.getInstance().getEmptySpace())).stream()
            .filter(s -> !s.isEmpty())
            .forEach(s -> {
              advanceSearchText.append(plusSign);
              advanceSearchText.append(field);
              advanceSearchText.append(preWildCard);
              advanceSearchText.append(s);
              advanceSearchText.append(postWildCard);
            });
    return advanceSearchText.toString().trim();
  }

  public String buildQueryDataString(QueryData data) {
    String field = data.getField();
    switch (field) {
      case "date":
        return buildDate(data);
      case "season":
        return buildSeason(data);
      case "ftx":
        return buildClassificationSearch(data);
      case "eftx":
        return buildDeterminationSearch(data);
      default:
        return buildAdvanceSearchText(data);
    }
  }

  private String buildAdvanceSearchText(QueryData data) {
    StringBuilder sb = new StringBuilder();
    switch (data.getOperation()) {
      case "not":
        sb.append(minusSign);
        break;
      case "and":
        sb.append(plusSign);
        break;
    }

    sb.append(startBlock);
    sb.append(buildSearchString(data));
    sb.append(endBlock);
    return sb.toString().trim();
  }

  private String buildDeterminationSearch(QueryData data) {  
    determinationSb = new StringBuilder();
    switch (data.getOperation()) {
      case "not":
        determinationSb.append(minusSign);
        break;
      case "and":
        determinationSb.append(plusSign);
        break;
    }
    determinationSb.append(startBlock);
    determinationSb.append(buildSearchString(data));

    determinationSb.append(endBlock);
    return determinationSb.toString().trim();
  }

  private String buildClassificationSearch(QueryData data) {
    String value = HelpClass.getInstance().resetValue(data.getValue());

    classificationSb = new StringBuilder();
    switch (data.getOperation()) {
      case "not":
        classificationSb.append(minusSign);
        break;
      case "and":
        classificationSb.append(plusSign);
        break;
    }
    classificationSb.append(startBlock);

    StringBuilder tsb = new StringBuilder();
    StringBuilder hsb = new StringBuilder();

    tsb.append(startBlock);
    hsb.append(startBlock);

    switch (data.getContent()) {
      case "exact":
        tsb.append(buildExactSearch(value, CommonText.getInstance().getTxSearch()));
        hsb.append(buildExactSearch(value, CommonText.getInstance().getHighTaxa()));
        break;
      case "startswith":
        tsb.append(buildStartsWithSearch(value, CommonText.getInstance().getTxSearch()));
        hsb.append(buildStartsWithSearch(value, CommonText.getInstance().getHighTaxa()));
        break;
      default:
        tsb.append(buildContainsTextSearch(value, CommonText.getInstance().getTxSearch()));
        hsb.append(buildContainsTextSearch(value, CommonText.getInstance().getHighTaxa()));
        break;
    }
    tsb.append(endBlock);
    hsb.append(endBlock);
    classificationSb.append(tsb.toString().trim());
    classificationSb.append(CommonText.getInstance().getEmptySpace());
    classificationSb.append(hsb.toString().trim());
    classificationSb.append(endBlock);

    return classificationSb.toString().trim();
  }

  private String buildSeason(QueryData bean) {
    int startMonth = bean.getStartMonth() == 0 ? 1 : bean.getStartMonth();
    int endMonth = bean.getEndMonth() == 0 ? 12 : bean.getEndMonth();
    int fromDayOfYear = DateHelper.getInstance().getDayOfYear(startMonth, bean.getStartDay());
    int toDayOfYear = DateHelper.getInstance().getDayOfYear(endMonth, bean.getEndDay());

    sessionSb = new StringBuilder();
    switch (bean.getOperation()) {
      case "not":
        sessionSb.append(minusSign);
        break;
      case "and":
        sessionSb.append(plusSign);
        break;
    }
    sessionSb.append(dayOfTheYear);
    sessionSb.append(squareStart);
    sessionSb.append(fromDayOfYear);
    sessionSb.append(to);
    sessionSb.append(toDayOfYear);
    sessionSb.append(squareEnd);

    return sessionSb.toString().trim();
  }

  private String buildDate(QueryData data) {
    String key = startDateKey;
    switch (data.getOperation()) {
      case "not":
        key = notStartDateKey;
        break;
      case "and":
        key = andStartDateKey;
        break;
    }
    return DateHelper.getInstance().convertLocalDateTimeToString(
            DateHelper.getInstance().convertDateToLocalDateTime(data.getFromDate(), true, false),
            DateHelper.getInstance().convertDateToLocalDateTime(data.getToDate(), false, true),
            key, fromZoom, toZoom);
  }

  /**
   * Build string with start date to now
   *
   * @param fromDate - LocalDateTime
   * @param toDate - LocalDateTime
   *
   * @return String
   */
  public String buildSearchDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
    dateRangeSb = new StringBuilder();
    dateRangeSb.append(CommonText.getInstance().getCatalogedDate());
    dateRangeSb.append(querySeparator);
    dateRangeSb.append(squareStart);
    dateRangeSb.append(fromDate == null ? CommonText.getInstance().getWildCard() : fromDate);
    dateRangeSb.append(fromZoom);
    dateRangeSb.append(to);
    dateRangeSb.append(toDate == null ? CommonText.getInstance().getWildCard() : toDate);
    dateRangeSb.append(squareEnd);
    return dateRangeSb.toString();
  }

  /**
   * Build search string for image option search
   *
   * @param text - String. Search text
   * @param filters - List. Search filter
   * @return String
   */
  public String buildImageOptionSearchText(String text, List<String> filters) {
    imageSb = new StringBuilder();
    imageSb.append(plusSign);
    imageSb.append(startBlock);
    imageSb.append(text);
    imageSb.append(endBlock);
    if (filters == null || filters.isEmpty()) {
      return imageSb.toString().trim();
    }

    imageSb.append(CommonText.getInstance().getEmptySpace());
    imageSb.append(plusSign);
    imageSb.append(startBlock);
    imageSb.append(CommonText.getInstance().getImageViewKey());
    imageSb.append(CommonText.getInstance().getEmptySpace());
    imageSb.append(startBlock);
    imageSb.append(CommonText.getInstance().getWildCard());

    filters.stream()
            .forEach(v -> {
              imageSb.append(CommonText.getInstance().getWildCard());
              imageSb.append(v);
              imageSb.append(CommonText.getInstance().getWildCard());
              imageSb.append(CommonText.getInstance().getEmptySpace());
            });
    imageSb.append(endBlock);
    imageSb.append(endBlock);
    imageSb.append(CommonText.getInstance().getEmptySpace());

    return imageSb.toString().trim();
  }

  /**
   * Build search region
   *
   * @param north
   * @param south
   * @param east
   * @param west
   *
   * @return String
   */
  public String buildSearchRegion(double north, double south, double east, double west) {
    searchRegionSb = new StringBuilder();
    searchRegionSb.append(squareStart);
    searchRegionSb.append(south);
    searchRegionSb.append(comma);
    searchRegionSb.append(west);
    searchRegionSb.append(to);
    searchRegionSb.append(north);
    searchRegionSb.append(comma);
    searchRegionSb.append(east);
    searchRegionSb.append(squareEnd);
    return searchRegionSb.toString();
  }

  /**
   * Build title for marker
   *
   * @param data - SolrData
   * @param count - int
   *
   * @return - String
   */
  public String buildMakerTitle(SolrData data, int count) {
    markTitleSb = new StringBuilder();
    markTitleSb.append(data.getTxFullName());
    markTitleSb.append(newLine);
    markTitleSb.append(data.getLocality());
    markTitleSb.append(newLine);
    markTitleSb.append(data.getLatitudeText());
    markTitleSb.append(dashDash);
    markTitleSb.append(data.getLongitudeText());
    if (count > 1) {
      markTitleSb.append(newLine);
      markTitleSb.append(total);
      markTitleSb.append(count);
    }
    return markTitleSb.toString().trim();
  }

  /**
   * Build text for multiple data
   *
   * @param count - int
   * @param solrDataList - List<>
   *
   * @return String
   */
  public String buildMultipleDataText(int count, List<SolrData> solrDataList) {

    SolrData solrData = solrDataList.get(0);
    StringBuilder mapInfoSb = new StringBuilder();
    mapInfoSb.append(totalCount);
    mapInfoSb.append(solrDataList.size());
    mapInfoSb.append(newLine);
    mapInfoSb.append(solrData.getLocality());
    mapInfoSb.append(newLine);
    mapInfoSb.append(solrData.getLatitudeText());
    mapInfoSb.append(dashDash);
    mapInfoSb.append(solrData.getLongitude());
    mapInfoSb.append(newLine);
    mapInfoSb.append(newLine);

    int index = count > 10 ? 10 : count - 1;

    IntStream.range(0, index)
            .forEach(i -> {
              SolrData data = solrDataList.get(i);
              mapInfoSb.append(data.getCatalogNumber());
              mapInfoSb.append(tab);
              mapInfoSb.append(data.getTxFullName());
              mapInfoSb.append(newLine);
            });

    return mapInfoSb.toString();
  }
}
