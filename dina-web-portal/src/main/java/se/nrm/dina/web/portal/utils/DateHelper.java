package se.nrm.dina.web.portal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth; 
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author idali
 */
public class DateHelper {
   
  private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); 
  private static DateHelper instance = null;
  
  public static DateHelper getInstance(){
    synchronized (DateHelper.class) {
      if(instance == null){
        instance = new DateHelper();
      }
    }
    return instance;
  } 
 
  
  /**
   * Get today's YearMonth
   * @return 
   */
  public YearMonth getYearMonthOfToday() {
    return YearMonth.from(LocalDate.now());
  } 
  
  public int getYearOfToday() { 
    return YearMonth.from(LocalDate.now()).getYear();
  } 
   
  public int getMonthOfToday() {
    return YearMonth.from(LocalDate.now()).getMonth().getValue();
  }
  
  /**
   * Get day of the year by given month and day
   * 
   * @param month - int.
   * @param day - int. day of the month
   * @return int
   */
  public int getDayOfYear(int month, int day) {   
    return LocalDate.of(2000, month, day).getDayOfYear(); 
  }
    
  /**
   * Convert java.util.Date to String
   * @param date
   * @return String
   */
  public String dateToString(Date date) { 
    return date == null ? null : DATE_FORMAT.format(date);
  }

  /**
   * Convert String to java.util.Date
   * @param strDate
   * @return java.util.Date
   */
  public Date stringToDate(String strDate) {
    try {
      return DATE_FORMAT.parse(strDate);
    } catch (ParseException e) { 
      return null;
    }
  }

  /**
   * Convert java.util.Date to LocalDate
   * @param date - java.util.Date
   * @return LocalDate
   */
  public LocalDate convertDateToLocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  /**
   * Convert java.util.Date to LocalDateTime
   * @param date - java.util.Date
   * @return LocalDateTime
   */
  public LocalDateTime convertDateToLocalDateTime(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /**
   * Convert java.util.Date to LocalDateTime with time
   * @param date -java.util.Date
   * @param isStartDay - boolean, set time at start of day
   * @param isEndDay - boolean, set time at end of day
   * @return LocalDateTime
   */
  public LocalDateTime convertDateToLocalDateTime(Date date, boolean isStartDay, boolean isEndDay) {
    if(date == null) {
      return null;
    }
    if (isStartDay) {
      return convertDateToLocalDate(date).atStartOfDay();
    }
    if (isEndDay) {
      return convertDateToLocalDate(date).atTime(23, 59, 59);
    }
    return convertDateToLocalDateTime(date);
  }
  
   

  /**
   * Build string with start date and end date
   * @param fromDate
   * @param toDate
   * @param field
   * @param fromZoom
   * @param toZoom
   * @return 
   */
  public String convertLocalDateTimeToString(LocalDateTime fromDate,
          LocalDateTime toDate, String field, String fromZoom, String toZoom) {
    StringBuilder sb = new StringBuilder();
    sb.append(field);
    sb.append(":[");
    if (fromDate != null) {
      sb.append(fromDate);
      sb.append(fromZoom);
    } else {
      sb.append(CommonText.getInstance().getWildCard());
    }
    sb.append(" TO ");
    if (toDate != null) {
      sb.append(toDate);
      sb.append(toZoom);
    } else {
      sb.append(CommonText.getInstance().getWildCard());
    }
    sb.append("]");
    return sb.toString();
  }
}
