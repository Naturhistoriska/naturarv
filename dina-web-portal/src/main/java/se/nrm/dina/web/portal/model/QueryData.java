/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author idali
 */
public class QueryData implements Serializable {

  private String operattion;
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

  public QueryData() {
    
  }
  
  public QueryData(String operation, String content, String field, String value) {
    this.operattion = operation;
    this.content = content;
    this.field = field;
    this.value = value;
  }

  public String getOperattion() {
    return operattion;
  }

  public void setOperattion(String operattion) {
    this.operattion = operattion;
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
  
  
}
