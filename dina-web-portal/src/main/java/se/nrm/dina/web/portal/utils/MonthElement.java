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
public enum MonthElement {
  JANUARY("Jan", "jan"),
  FEBRUARY("Feb", "feb"),
  MARCH("Mar", "mar"),
  APRIL("Apr", "apr"),
  MAY("May", "maj"),
  JUNE("Jun", "jun"),
  JULY("Jul", "jul"),
  AUGUST("Aug", "aug"),
  SEPTEMBER("Sep", "sep"),
  OCTOBER("Oct", "okt"),
  NOVEMBER("Nov", "nov"),
  DECEMBER("Dec", "dec");
  
  public final String shortNameEn, shortNameSv; 
  private static final Map<String, String> BY_SHORT_NAME_EN = new HashMap<>();
  private static final Map<String, String> BY_SHORT_NAME_SV = new HashMap<>();
  private static final Map<String, String> SV_TO_EN = new HashMap<>();
  private static final Map<String, String> EN_TO_SV = new HashMap<>();
   
  static {
    for (MonthElement e : values()) {
      BY_SHORT_NAME_EN.put(e.name(), e.shortNameEn);
      BY_SHORT_NAME_SV.put(e.name(), e.shortNameSv);
      SV_TO_EN.put(e.shortNameSv, e.shortNameEn);
      EN_TO_SV.put(e.shortNameEn, e.shortNameSv);
    }
  }

  private MonthElement(String shortNameEn, String shortNameSv) {
    this.shortNameEn = shortNameEn;
    this.shortNameSv = shortNameSv;
  }
   
  public static String valueOfShortName(String month, boolean isSwedish) {
    return isSwedish ? BY_SHORT_NAME_SV.get(month) : BY_SHORT_NAME_EN.get(month);
  } 
  
  public static String changeLanguage(String month, boolean isSwedish) {
    return isSwedish ? EN_TO_SV.get(month) : SV_TO_EN.get(month);
  }
} 