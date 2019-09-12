/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author idali
 */
public class HelpClass {

  private static HelpClass instance = null; 
  private StringBuilder resultHeaderSummarySb;
  private StringBuilder imagePathSb;
  
  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  
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
    if(totalResult == 0) {
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
    if(date == null) {
      return null;
    }
    return dateFormat.format(date);
  }
  
}
