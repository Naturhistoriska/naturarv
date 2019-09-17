/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;

/**
 *
 * @author idali
 */
public class HelpClass {

  private static HelpClass instance = null;
  private StringBuilder resultHeaderSummarySb;
  private StringBuilder imagePathSb;

  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static synchronized HelpClass getInstance() {
    if (instance == null) {
      instance = new HelpClass();
    }
    return instance;
  }

  public String buildResultHeaderSummaryForMapView(int totalResult, boolean isSwedish) {

    resultHeaderSummarySb = new StringBuilder();
    resultHeaderSummarySb.append("(");
    resultHeaderSummarySb.append(totalResult);
    resultHeaderSummarySb.append(" ");
    resultHeaderSummarySb.append(CommonText.getInstance().getHits(isSwedish));
    resultHeaderSummarySb.append(")");
    return resultHeaderSummarySb.toString();
  }

  public String buildResultHeaderSummaryForResultView(int totalResult, boolean isSwedish) {
    if (totalResult == 0) {
      return buildEmptyString();
    }
    resultHeaderSummarySb = new StringBuilder();
    resultHeaderSummarySb.append(CommonText.getInstance().getSelected(isSwedish));
    resultHeaderSummarySb.append(" ");
    resultHeaderSummarySb.append(totalResult);
    return resultHeaderSummarySb.toString();
  }

  public String buildImagePath(String id, String type, String morphbankImagePath) {
    imagePathSb = new StringBuilder();
    imagePathSb.append(morphbankImagePath);
    imagePathSb.append(CommonText.getInstance().getImageQueryId());
    imagePathSb.append(id);
    imagePathSb.append(type);
    return imagePathSb.toString();
  }

  public String buildEmptyString() {
    return " ";
  }

  public String dateToString(Date date) {
    if (date == null) {
      return null;
    }
    return dateFormat.format(date);
  }

  public String replaceChars(String value) {
    String s = value.replaceAll("[\\[\\](),]", " ");
    return s.trim();
  }

  public String getTodaysDateInString() {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public List getDayList(int month) { 
    List dropDayList = new ArrayList(); 
    for (int i = 1; i <= getNumberOfDaysInMonth(month); i++) {
      dropDayList.add(new SelectItem(String.valueOf(i), String.valueOf(i)));
    }
    return dropDayList;
  }

  public int getNumberOfDaysInMonth(int numberOfMonth) {
    switch (numberOfMonth) {
      case 1:
        return 31;
      case 2:
        return 29;
      case 3:
        return 31;
      case 4:
        return 30;
      case 5:
        return 31;
      case 6:
        return 30;
      case 7:
        return 31;
      case 8:
        return 31;
      case 9:
        return 30;
      case 10:
        return 31;
      case 11:
        return 30;
      case 12:
        return 31;
      default:
        return 31;
    }
  }
   
  public void updateView(String viewId) {
    PrimeFaces.current().ajax().update(viewId);
  } 
}
