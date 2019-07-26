/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

/**
 *
 * @author idali
 */
public class CommonText {

  private static final String SIMPLE_SEARCH_DEFAULT_TEXT_EN = "Search collections (species, genus, family, collectors, location, etc.)";
  private static final String SIMPLE_SEARCH_DEFAULT_TEXT_SV = "Sök i samlingar (art, släkte, familj, insamlare, plats etc.)";

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
  
  private static final String LOCALE = "locale";
  private static final String STATISTIC = "statistic";
  private static final String MONTH_CHART_DATA = "monthChartData";
  private static final String YEAR_CHART_DATA = "yearChartData";
  private static final String COLLECTIONS_MONTH_CHART_DATA = "collectionsMonthChartData";
  private static final String COLLECTIONS_YEAR_CHART_DATA = "collectionsYearChartData";
  

  private static CommonText instance = null;

  public static synchronized CommonText getInstance() {
    if (instance == null) {
      instance = new CommonText();
    }
    return instance;
  }


  
  public String getLocale() {
    return LOCALE;
  }
  
  public String getStatistic() {
    return STATISTIC;
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
  
  public String getSearchDefaultText(boolean isSwedish) {
    return isSwedish ? SIMPLE_SEARCH_DEFAULT_TEXT_SV : SIMPLE_SEARCH_DEFAULT_TEXT_EN;
  }

  public String getNrmName(boolean isSwedeish) {
    return isSwedeish ? NRM_SV : NRM_EN;
  }

  public String getGnmName(boolean isSwedish) {
    return isSwedish ? GNM_SV : GNM_EN;
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