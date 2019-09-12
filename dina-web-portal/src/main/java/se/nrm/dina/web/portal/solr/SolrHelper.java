/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
public class SolrHelper {

  private static SolrHelper instance = null;
  private StringBuilder searchCatalogedDateSb;
  private StringBuilder searchTextSb;
  private StringJoiner sj;

  public static synchronized SolrHelper getInstance() {
    if (instance == null) {
      instance = new SolrHelper();
    }
    return instance;
  }

  public String buildSearchText(String field, String text) {
    searchTextSb = new StringBuilder();

    if (text == null || text.isEmpty()) {
      return CommonText.getInstance().getWildSearchText();
    }

    String[] strings = StringUtils.split(text);
    if (strings.length == 1) {
      searchTextSb.append(field);
      searchTextSb.append(":*");
      searchTextSb.append(text);
      searchTextSb.append("*");
    } else {
      searchTextSb.append("(");
      for (String s : strings) {
        if (!s.trim().isEmpty()) {
          searchTextSb.append("+");
          searchTextSb.append(field);
          searchTextSb.append(":*");
          searchTextSb.append(text);
          searchTextSb.append("*");
        }
      }
      searchTextSb.append(")");
    }
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
}
