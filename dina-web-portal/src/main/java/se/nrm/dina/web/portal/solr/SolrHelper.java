/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;

import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
public class SolrHelper {
  
  private static SolrHelper instance = null;

  public static synchronized SolrHelper getInstance() {
    if (instance == null) {
      instance = new SolrHelper();
    }
    return instance;
  }
  
  public String buildSearchText(String field, String text) {
    StringBuilder sb = new StringBuilder();
    
    if(text == null || text.isEmpty()) {
      return CommonText.getInstance().getWildSearchText();
    }
    
    String[] strings = StringUtils.split(text);
    if(strings.length == 1) {
      sb.append(field);
      sb.append(":*");
      sb.append(text);
      sb.append("*");
    } else {
      sb.append("(");
      for(String s : strings) {
        if(!s.trim().isEmpty()) {
          sb.append("+");
          sb.append(field);
          sb.append(":*");
          sb.append(text);
          sb.append("*");
        } 
      }
      sb.append(")");
    }
    return sb.toString();
  }
}
