/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrHelper {

  private static SolrHelper instance = null;
  private StringBuilder searchTextSb;
  private StringJoiner sj;

  private StringBuilder advanceSearchText;

  public static synchronized SolrHelper getInstance() {
    if (instance == null) {
      instance = new SolrHelper();
    }
    return instance;
  }

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
   
  public String buildSearchString(String value, String field, String content) {
    
    if(content.equals(CommonText.getInstance().getExact())) {
      return buildExactString(value, field);
    }
     
    if (value.contains("(") || value.contains(")") || value.contains(",")) {
      value = HelpClass.getInstance().replaceChars(value);
    } 
    return content.equals(CommonText.getInstance().getStartWith()) ?
            buildStartsWithString(value, field) : buildContainsString(value, field); 
  }


  private String buildExactString(String value, String field) {
    advanceSearchText = new StringBuilder();
    advanceSearchText.append(field);
    advanceSearchText.append(":\"");
    advanceSearchText.append(value);
    advanceSearchText.append("\""); 
    return advanceSearchText.toString();
  }

  private String buildStartsWithString(String value, String field) {
    if (value.contains("(") || value.contains(")") || value.contains(",")) {
      value = HelpClass.getInstance().replaceChars(value);
    }
    
    advanceSearchText = new StringBuilder();
    String[] strings = value.split(" ");

    advanceSearchText.append("+");
    advanceSearchText.append(field);
    advanceSearchText.append(":");
    advanceSearchText.append(strings[0]);
    advanceSearchText.append("* ");
    if (strings.length > 1) {
      for (int i = 1; i < strings.length; i++) {
        if (!strings[i].isEmpty()) {
          advanceSearchText.append("+");
          advanceSearchText.append(field);
          advanceSearchText.append(":*");
          advanceSearchText.append(strings[i]);
          advanceSearchText.append("* ");
        }
      }
    } 
    return advanceSearchText.toString().trim();
  }

  private String buildContainsString(String value, String field) {
    if (value.contains("(") || value.contains(")") || value.contains(",")) {
      value = HelpClass.getInstance().replaceChars(value);
    }
    
    advanceSearchText = new StringBuilder();
    String[] strings = value.split(" "); 
    Arrays.asList(strings).stream()
            .filter(s -> !s.isEmpty())
            .forEach(s -> {
              advanceSearchText.append("+");
              advanceSearchText.append(field);
              advanceSearchText.append(":*");
              advanceSearchText.append(s);
              advanceSearchText.append("* ");
            });
    
//    StringBuilder sb = new StringBuilder();
//    String[] strings = value.split(" ");
//    if (strings.length == 1) {
//      sb.append(field);
//      sb.append(":*");
//      sb.append(value);
//      sb.append("*");
//    } else {
//      sb.append("(");
//      for (String s : strings) {
//        if (!s.isEmpty()) {
//          sb.append("+");
//          sb.append(field);
//          sb.append(":*");
//          sb.append(s);
//          sb.append("* ");
//        }
//      } 
//    }
    return advanceSearchText.toString().trim();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
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
  private String buildSearchString(String field, String value) {
    searchTextSb = new StringBuilder();
    searchTextSb.append(field);
    searchTextSb.append(":");
    searchTextSb.append(value);
    return searchTextSb.toString();
  }

  public String buildFullSearchText(String text) {
    searchTextSb = new StringBuilder();

    if (text == null || text.isEmpty() || text.equals(CommonText.getInstance().getWildCard())) {
      return CommonText.getInstance().getWildSearchText();
    }

    String[] strings = StringUtils.split(text);
    Arrays.asList(strings)
            .stream()
            .filter(s -> !s.isEmpty())
            .forEach(s -> {
              searchTextSb.append("+");
              searchTextSb.append(CommonText.getInstance().getTextField());
              searchTextSb.append(":*");
              searchTextSb.append(s);
              searchTextSb.append("* ");
            });
    return searchTextSb.toString().trim();
  }

  public String buildDate(QueryData data) {
    String fromZoom = ":00Z";
    String toZoom = "Z";
    String key = "startDate";
    switch (data.getOperation()) {
      case "not":
        key = "-startDate";
        break;
      case "and":
        key = "+startDate";
        break;
    }
    LocalDateTime fromDate = data.getFromDate() == null
            ? null : HelpClass.getInstance().convertDateToLocalDateTime(data.getFromDate(), true, false);
    LocalDateTime toDate = data.getToDate() == null
            ? null : HelpClass.getInstance().convertDateToLocalDateTime(data.getToDate(), false, true);
    return HelpClass.getInstance().convertLocalDateTimeToString(fromDate, toDate, key, fromZoom, toZoom);
  }

  public String buildAdvanceSearchText(QueryData data) {
    log.info("buildAdvanceSearchText");
    String value = data.getValue();
//    if (value != null && !value.isEmpty()) {
//      value = replaceChars(value.trim());
//    } else {
//      value = "*";
//    }

    StringBuilder sb = new StringBuilder();
    switch (data.getOperation()) {
      case "not":
        sb.append("-");
        break;
      case "and":
        sb.append("+");
        break;
    }

    sb.append("(");
    sb.append(buildSearchString(value, data.getField(), data.getContent()));
    sb.append(")");
    return sb.toString().trim();
  }

  public String buildSearchCatalogedDateText(LocalDateTime date) {
    return HelpClass.getInstance().convertLocalDateTimeToString(date, null,
            CommonText.getInstance().getCatalogedDate(), ":00Z", null); 
  }

  public String buildImageOptionSearchText(String searchText, List<String> filters) {
    StringBuilder sb = new StringBuilder();

    sb.append("+(");
    sb.append(searchText);
    sb.append(") ");
    buildString(sb, filters);
    return sb.toString();
  }

  private void buildString(StringBuilder sb, List<String> selectedList) {
    if (!selectedList.isEmpty()) {
      sb.append(" +(");
      sb.append(CommonText.getInstance().getImageViewKey());
      sb.append(" (*");
      sj = new StringJoiner("* *");

      selectedList.stream()
              .forEach(v -> {
                sj.add(v);
              });
      sb.append(sj.toString());
      sb.append("*)) ");
    }
  }

  

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
