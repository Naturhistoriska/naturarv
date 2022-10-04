package se.nrm.dina.web.portal.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author idali
 */
public enum MonthElement {
  

   
  JANUARY(1, "Jan", "jan", "January", "Januari"),
  FEBRUARY(2, "Feb", "feb", "February", "Februari"),
  MARCH(3, "Mar", "mar", "March", "Mars"),
  APRIL(4, "Apr", "apr", "April", "April"),
  MAY(5, "May", "maj", "May", "Maj"),
  JUNE(6, "Jun", "jun", "June", "Juni"),
  JULY(7, "Jul", "jul", "July", "Juli"),
  AUGUST(8, "Aug", "aug", "Augst", "Augusti"),
  SEPTEMBER(9, "Sep", "sep", "September", "September"),
  OCTOBER(10, "Oct", "okt", "October", "Oktober"),
  NOVEMBER(11, "Nov", "nov", "November", "November"),
  DECEMBER(12, "Dec", "dec", "December", "December");
 
  public final String shortNameEn, shortNameSv, nameEn, nameSv;
  public final Integer numOfMonth;
 
  private static StringBuilder monthYearSb;

  private static final Map<String, String> BY_SHORT_NAME_EN = new HashMap<>();
  private static final Map<String, String> BY_SHORT_NAME_SV = new HashMap<>();
  private static final Map<String, String> BY_NAME_EN = new HashMap<>();
  private static final Map<String, String> BY_NAME_SV = new HashMap<>();
  private static final Map<String, String> SV_TO_EN = new HashMap<>();
  private static final Map<String, String> EN_TO_SV = new HashMap<>();
  private static final Map<Integer, String> BY_NUMBER_OF_MONTH_EN = new HashMap<>();
  private static final Map<Integer, String> BY_NUMBER_OF_MONTH_SV = new HashMap<>();

  static {
    for (MonthElement e : values()) {
      BY_SHORT_NAME_EN.put(e.name(), e.shortNameEn);
      BY_SHORT_NAME_SV.put(e.name(), e.shortNameSv);
      BY_NAME_EN.put(e.name(), e.nameEn);
      BY_NAME_SV.put(e.name(), e.nameSv);
      BY_NUMBER_OF_MONTH_EN.put(e.numOfMonth, e.nameEn);
      BY_NUMBER_OF_MONTH_SV.put(e.numOfMonth, e.nameSv);
      SV_TO_EN.put(e.shortNameSv, e.shortNameEn);
      EN_TO_SV.put(e.shortNameEn, e.shortNameSv);
    }
  }

  private MonthElement(int numOfMonth, String shortNameEn, String shortNameSv, String nameEn, String nameSv) {
    this.numOfMonth = numOfMonth;
    this.shortNameEn = shortNameEn;
    this.shortNameSv = shortNameSv;
    this.nameEn = nameEn;
    this.nameSv = nameSv;
  }

  public static String valueOfShortName(String month, boolean isSwedish) {
    return isSwedish ? BY_SHORT_NAME_SV.get(month) : BY_SHORT_NAME_EN.get(month);
  }

  public static String valueOfName(String month, boolean isSwedish) {
    return isSwedish ? BY_NAME_SV.get(month) : BY_NAME_EN.get(month);
  }

  public static String valueOfNameByNumberOfMonth(Integer numberOfMonth, boolean isSwedish) {
    return isSwedish ? BY_NUMBER_OF_MONTH_SV.get(numberOfMonth) : BY_NUMBER_OF_MONTH_EN.get(numberOfMonth);
  }

  public static String changeLanguage(String month, boolean isSwedish) {
    return isSwedish ? EN_TO_SV.get(month) : SV_TO_EN.get(month);
  }

  public static String changeMonthLanguageWithYear(String value, boolean isSwedish) {
    String[] labels = StringUtils.split((String) value, CommonText.getInstance().getEmptySpace());
    monthYearSb = new StringBuilder();
    monthYearSb.append(changeLanguage(labels[0], isSwedish))
            .append(CommonText.getInstance().getEmptySpace())
            .append(labels[1]);
    return monthYearSb.toString();
  }
}
