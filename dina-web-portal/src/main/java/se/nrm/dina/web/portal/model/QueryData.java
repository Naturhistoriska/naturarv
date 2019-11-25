package se.nrm.dina.web.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author idali
 */
public class QueryData implements Serializable {

  private String operation;
  private String content;
  private String field;
  private String value;
  private Date fromDate;
  private Date toDate;
  private int startMonth = 0;
  private int endMonth = 0;
  private int stMon = 1;
  private int endMon = 12;
  private int startDay = 1;
  private int endDay = 1;
  private boolean isSearchAllType;

  public QueryData() {
    
  }
  
  public QueryData(String operation, String content, String field, String value, boolean isSearchAllType) {
    this.operation = operation;
    this.content = content;
    this.field = field;
    this.value = value;
    this.isSearchAllType = isSearchAllType;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }
 
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public int getStartMonth() {
    return startMonth;
  }

  public void setStartMonth(int startMonth) {
    this.startMonth = startMonth;
  }

  public int getEndMonth() {
    return endMonth;
  }

  public void setEndMonth(int endMonth) {
    this.endMonth = endMonth;
  }

  public int getStMon() {
    return stMon;
  }

  public void setStMon(int stMon) {
    this.stMon = stMon;
  }

  public int getEndMon() {
    return endMon;
  }

  public void setEndMon(int endMon) {
    this.endMon = endMon;
  }

  public int getStartDay() {
    return startDay;
  }

  public void setStartDay(int startDay) {
    this.startDay = startDay;
  }

  public int getEndDay() {
    return endDay;
  }

  public void setEndDay(int endDay) {
    this.endDay = endDay;
  }
  
  public boolean isAppendValue() {
    if(field.equals("date")) {
      return fromDate != null || toDate != null;
    }
    if(field.equals("season")) {
      return startMonth != 0 || endMonth != 0;
    }
     
    if(field.equals("ts")) {
      return isSearchAllType || value != null && !value.isEmpty();
    }
    
    return value != null && !value.isEmpty(); 
  }

  public boolean isIsSearchAllType() {
    return isSearchAllType;
  }

  public void setIsSearchAllType(boolean isSearchAllType) {
    this.isSearchAllType = isSearchAllType;
  }
   
  
  @Override
  public String toString() {
    return operation + " " + content + " " + field + " " + value; 
  }
}
