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
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
public class SolrHelper {

  private static SolrHelper instance = null;
  private StringBuilder searchCatalogedDateSb;
  private StringBuilder searchTextSb;
  private StringJoiner sj;

  private StringBuilder advanceSearchText;

  public static synchronized SolrHelper getInstance() {
    if (instance == null) {
      instance = new SolrHelper();
    }
    return instance;
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
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

  public String buildAdvanceFullTextSearchtring(String value, String content, String field) {

    if (value.equals(CommonText.getInstance().getWildSearchText())) {
      return buildFullTextSearch(value, field);
    }
    return buildFullTextSearchString(value, field, content);
  }

  private String buildFullTextSearchString(String value, String field, String content) {
    advanceSearchText = new StringBuilder();
    switch (content) {
      case "exact":
        advanceSearchText.append(buildExactString(value, field));
        break;
      case "startswith":
        advanceSearchText.append(buildStartsWithString(value, field, false));
        break;
      default:
        advanceSearchText.append(buildContainsString(value, field, false));
        break;
    }
    return advanceSearchText.toString();
  }

  private String buildExactString(String value, String field) {
    advanceSearchText = new StringBuilder();
    advanceSearchText.append(field);
    advanceSearchText.append(":\"");
    advanceSearchText.append(value);
    advanceSearchText.append("\"");
    return advanceSearchText.toString();
  }

  private String buildFullTextSearch(String value, String field) {
    searchTextSb = new StringBuilder();
    searchTextSb.append(field);
    searchTextSb.append(":");
    searchTextSb.append(value);
    return searchTextSb.toString();
  }

  public String buildSearchCatalogedDateText(LocalDateTime date) {
    searchCatalogedDateSb = new StringBuilder();
    searchCatalogedDateSb.append("catalogedDate:[");
    searchCatalogedDateSb.append(date);
    searchCatalogedDateSb.append(":00Z TO *]");
    return searchCatalogedDateSb.toString();
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

  public String buildString(String value, String field, String content) {

    if (value.contains("(") || value.contains(")") || value.contains(",")) {
      value = HelpClass.getInstance().replaceChars(value);
    }

    StringBuilder sb = new StringBuilder();
    switch (content) {
      case "exact":
        sb.append(buildExactString(value, field));
        break;
      case "startswith":
        sb.append(buildStartsWithString(value, field, false));
        break;
      default:
        sb.append(buildContainsString(value, field, false));
        break;
    }
    return sb.toString().trim();
  }

  private String buildContainsString(String value, String field, boolean boost) {
    StringBuilder sb = new StringBuilder();
    String[] strings = value.split(" ");
    if (strings.length == 1) {
      sb.append(field);
      sb.append(":*");
      sb.append(value);
      sb.append(boost ? "*^2" : "*");
    } else {
      sb.append("(");
      for (String s : strings) {
        if (!s.isEmpty()) {
          sb.append("+");
          sb.append(field);
          sb.append(":*");
          sb.append(s);
          sb.append(boost ? "*^2 " : "* ");
        }
      }
      sb.append(")");
    }
    return sb.toString().trim();
  }

  private String buildStartsWithString(String value, String field, boolean boost) {
    StringBuilder sb = new StringBuilder();
    String[] strings = value.split(" ");

    if (strings.length > 1) {
      sb.append("(+");
    }
    sb.append(field);
    sb.append(":");
    sb.append(strings[0]);
    sb.append(boost ? "*^2 " : "* ");

    for (int i = 1; i < strings.length; i++) {
      if (!strings[i].isEmpty()) {
        sb.append("+");
        sb.append(field);
        sb.append(":*");
        sb.append(strings[i]);
        sb.append(boost ? "*^2 " : "* ");
      }
    }
    if (strings.length > 1) {
      sb.append(")");
    }
    return sb.toString().trim();
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
