/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;
 
import java.util.Map; 
import lombok.extern.slf4j.Slf4j; 
import org.apache.solr.client.solrj.SolrQuery; 
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrHelper {

  private static SolrHelper instance = null; 
  
  public static SolrHelper getInstance(){
    synchronized (SolrHelper.class) {
      if(instance == null){
        instance = new SolrHelper();
      }
    }
    return instance;
  } 
  
  /**
   * Get total count of bucket
   * 
   * @param bucket - BucketBasedJsonFacet
   * 
   * @return int
   */
  public int getBucketsTotal(BucketBasedJsonFacet bucket) {
    if(bucket == null) {
      return 0;
    }
    if(bucket.getBuckets() == null || bucket.getBuckets().isEmpty()) {
      return 0;
    }
    return (int)bucket.getBuckets().get(0).getCount();
  }
  
  /**
   * Add filters into solr query
   * 
   * @param query - solrQuery
   * @param filterQueries - Map<String, String>
   */
  public void addSearchFilters(SolrQuery query, Map<String, String> filterQueries) { 
    if (filterQueries != null && !filterQueries.isEmpty()) {                                                // add filters into search
      filterQueries.entrySet()
              .stream()
              .forEach(e -> {
                query.addFilterQuery(e.getKey().trim() + e.getValue().trim());
              });
    }
  }
  
  public void addSearchFilters(JsonQueryRequest request, Map<String, String> filterQuerues) {
    if (filterQuerues != null) {
      filterQuerues.entrySet()
              .stream()
              .forEach(e -> {
                request.withFilter(e.getKey().trim() + e.getValue().trim());
              });
    }
  }
   
//  /**
//   * Build search string
//   * 
//   * @param value - String. Text to search for
//   * @param field - String. Solr index field
//   * @param content - String. An condition either 'AND', 'OR', or 'EXACT'
//   * 
//   * @return String
//   */
//  public String buildSearchString(String value, String field, String content) {
//
//    value = value == null ? CommonText.getInstance().getWildCard() : value; 
//    if (content.equals(CommonText.getInstance().getExact())) {
//      return SearchStringBuilder.gebuildExactString(value, field);
//    }
//
//    if (value.contains("(") || value.contains(")") || value.contains(",")) {
//      value = HelpClass.getInstance().replaceChars(value);
//    }
//    return content.equals(CommonText.getInstance().getStartWith())
//            ? buildStartsWithString(value, field) : buildContainsString(value, field);
//  }
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //  public String buildSearchCatalogedDateText(LocalDateTime date) {
//    return DateHelper.getInstance().convertLocalDateTimeToString(date, null,
//            CommonText.getInstance().getCatalogedDate(), ":00Z", null);
//  }
  
  
  
  
  
  
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  

//  
//  private String buildExactString(String value, String field) {
//    advanceSearchText = new StringBuilder();
//    advanceSearchText.append(field);
//    advanceSearchText.append(":\"");
//    advanceSearchText.append(value);
//    advanceSearchText.append("\"");
//    return advanceSearchText.toString();
//  }

  

  



//  private String buildCommonNameSearchText(QueryData data) { 
//
//    String value = HelpClass.getInstance().resetValue(data.getValue());
// 
//    commonNameSb = new StringBuilder();
//    switch (data.getOperation()) {
//      case "not":
//        commonNameSb.append("-");
//        break;
//      case "and":
//        commonNameSb.append("+");
//        break;
//    }
//    commonNameSb.append("(");
//    commonNameSb.append(buildExactString(value, CommonText.getInstance().getCommonName()));
// 
//    switch (data.getContent()) {
//      case "startswith":
//        commonNameSb.append(" ");
//        commonNameSb.append(buildStartsWithString(value, CommonText.getInstance().getCommonName()));
//        break;
//      case "exact":
//        commonNameSb.append(" ");
//        commonNameSb.append(buildExactString(value, CommonText.getInstance().getCommonName()));
//        break; 
//      default:
//        commonNameSb.append(buildContainsString(value, CommonText.getInstance().getCommonName()));
//        break;
//    }
//    commonNameSb.append(")");
//    return commonNameSb.toString().trim();
//  }

//  private String buildFullTextSearchText(QueryData data) {
//    log.info("buildFullTextSearchText");
//
//    StringBuilder sb = new StringBuilder(); 
//    switch (data.getOperation()) {
//      case "and":
//        sb.append("+");
//        break;
//      case "not":
//        sb.append("-");
//        break;
//    }
//    sb.append("("); 
//    String value = HelpClass.getInstance().resetValue(data.getValue()); 
//    switch (data.getContent()) {
//      case "exact":
//        sb.append(buildExactString(value, CommonText.getInstance().getTextField()));
//        break;
//      case "startswith":
//        sb.append(buildStartsWithString(value, CommonText.getInstance().getTextField()));
//        break;
//      default:
//        sb.append(buildContainsString(value, CommonText.getInstance().getTextField()));
//        break;
//    }
//    sb.append(")");
//    return sb.toString().trim();
//  }
  
//  private String buildFullTextSearchText(String value) { 
//      StringBuilder sb = new StringBuilder();
//      sb.append("(");
//      sb.append(buildContainsString(value, CommonText.getInstance().getTxSearch()));
//      sb.append(") ");
//      sb.append("(");
//      sb.append(buildStartsWithString(value, CommonText.getInstance().getTxSearch()));
//      sb.append(") ");
//      sb.append("(");
//      sb.append(buildContainsString(value, CommonText.getInstance().getTxSearch()));
//      sb.append(")");
//      return sb.toString().trim(); 
//  }

//  private String buildDeterminationSearch(QueryData data) {
//    String value = HelpClass.getInstance().resetValue(data.getValue());
// 
//    StringBuilder sb = new StringBuilder();
//    switch (data.getOperation()) {
//      case "not":
//        sb.append("-");
//        break;
//      case "and":
//        sb.append("+");
//        break;
//    }
//    sb.append("(");
// 
//    switch (data.getContent()) {
//      case "exact":
//        sb.append(buildExactString(value, CommonText.getInstance().getTxSearch()));
//        break;
//      case "startswith":
//        sb.append(buildStartsWithString(value, CommonText.getInstance().getTxSearch()));
//        break;
//      default:
//        sb.append(buildContainsString(value, CommonText.getInstance().getTxSearch()));
//        break;
//    }
//    sb.append(")");
//    return sb.toString().trim();
//  }


//  private String buildClassificationSearch(QueryData data) { 
//    log.info("buildClassificationSearch : {}", data.getValue());
//
//    String value = HelpClass.getInstance().resetValue(data.getValue());
//  
//    classificationSb = new StringBuilder();
//    switch (data.getOperation()) {
//      case "not":
//        classificationSb.append("-");
//        break;
//      case "and":
//        classificationSb.append("+");
//        break;
//    }
//    classificationSb.append("(");
// 
//    StringBuilder tsb = new StringBuilder();
//    StringBuilder hsb = new StringBuilder();
//
//    tsb.append("(");
//    hsb.append("(");
//
//    switch (data.getContent()) {
//      case "exact":
//        tsb.append(buildExactString(value, CommonText.getInstance().getTxSearch()));
//        hsb.append(buildExactString(value, CommonText.getInstance().getHighTaxa()));
//        break;
//      case "startswith":
//        tsb.append(buildStartsWithString(value, CommonText.getInstance().getTxSearch()));
//        hsb.append(buildStartsWithString(value, CommonText.getInstance().getHighTaxa()));
//        break;
//      default:
//        tsb.append(buildContainsString(value, CommonText.getInstance().getTxSearch()));
//        hsb.append(buildContainsString(value, CommonText.getInstance().getHighTaxa()));
//        break;
//    }
//    tsb.append(")");
//    hsb.append(")");
//    classificationSb.append(tsb.toString().trim());
//    classificationSb.append(" ");
//    classificationSb.append(hsb.toString().trim());
//    classificationSb.append(")");
//
//    return classificationSb.toString().trim();
//  }


//
// 

//  
//  /**
//   * Method build a search string for advance search
//   * @param value
//   * @param content
//   * @param field
//   * @return 
//   */
//  public String buildAdvanceFullTextSearchtring(String value, String content, String field) {
//
//    if (value.equals(CommonText.getInstance().getWildSearchText())) {
//      return buildSearchString(field, value);
//    }
//    return buildFullTextSearchString(value, field, content);
//  }
//  
//  private String buildSearchString(String field, String value) {
//    searchTextSb = new StringBuilder();
//    searchTextSb.append(field);
//    searchTextSb.append(":");
//    searchTextSb.append(value);
//    return searchTextSb.toString();
//  }


 

//  public String buildAdvanceSearchText(QueryData data) {
//    log.info("buildAdvanceSearchText");
//    String value = data.getValue();
////    if (value != null && !value.isEmpty()) {
////      value = replaceChars(value.trim());
////    } else {
////      value = "*";
////    }
//
//    StringBuilder sb = new StringBuilder();
//    switch (data.getOperation()) {
//      case "not":
//        sb.append("-");
//        break;
//      case "and":
//        sb.append("+");
//        break;
//    }
//
//    sb.append("(");
//    sb.append(buildSearchString(value, data.getField(), data.getContent()));
//    sb.append(")");
//    return sb.toString().trim();
//  }

//  public String buildSearchCatalogedDateText(LocalDateTime date) {
//    return DateHelper.getInstance().convertLocalDateTimeToString(date, null,
//            CommonText.getInstance().getCatalogedDate(), ":00Z", null);
//  }

  
 
  
  
  
//  public String buildAdvanceFullTextSearchString(String value, String field, String content) {
//    log.info("buildAdvanceFullTextSearchString: {} -- {}", value, field);
//    switch (content) {
//      case "exact":
//        return buildExactString(value, field);
//      case "startswith":
//        return buildStartsWithString(value, field);
//      default:
//        return buildContainsString(value, field); 
//    } 
//  }
  
  
  
  
//  private String buildExactString(String value, String field, boolean boost) {  
//    StringBuilder sb = new StringBuilder();
//
//    String[] strings = value.split(" ");
//    if (strings.length > 1) {
//      sb.append("(");
//      for (String s : strings) {
//        if (!s.isEmpty()) {
//          sb.append("+");
//          appendField(field, s, boost, sb);
//          sb.append( " ");
//        }
//      }
//      sb.append(")");
//    } else {
//      appendField(field, value, boost, sb);
//    }
//    return sb.toString().trim();
//  }
//  private void appendField(String field, String value, boolean boost, StringBuilder sb) {
//    sb.append(field);
//    sb.append(":");
//    sb.append(value);
//    sb.append(boost ? "^2" : ""); 
//  }
}
