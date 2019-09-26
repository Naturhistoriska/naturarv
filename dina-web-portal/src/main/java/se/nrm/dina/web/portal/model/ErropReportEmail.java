/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
public class ErropReportEmail {

  private static final String NORMAL_FONT = "#000000;";
  private static final String RED_FONT = "red;"; 
  private static final String END_DIV_TAG = "</div>";
  private static final String TAG_BR = "<br />";
  private static final String TAG_FIELD_START = "<div style=\"float: left;  width: 800px;\">";
  private static final String TAG_REPORT_START = "<div style=\"background-color: #AAD11C; border: 2px solid #000000;overflow: auto; padding: 10px 0; text-align: center; width: 800px;  \">";
  private static final String TAG_REPORT_TITLE = "<div style=\"font-size: 1.2em;  font-weight: bold; \">";
  
  private static final String TAG_FIELD_TITLE_START = "<div style=\"display: inline; float: left; font-size: 1em; font-weight: bold;  padding: 0 15px 5px 10px; width: 15%;\">";
  private static final String TAG_COLOR_START = "<div style=\"display: inline; color: ";
  private static final String TAG_END = " \">";
  
  public String createErrorReport(SolrData data, ErrorReport error, boolean isSwedish) {

    StringBuilder sb = new StringBuilder();

    if (data != null) {
      sb.append(TAG_REPORT_START);
      sb.append(TAG_REPORT_TITLE);
      sb.append(CommonText.getInstance().getErrorReportTitle(isSwedish));
      sb.append(END_DIV_TAG); 

      sb.append("<div style=\"background-color: ivory; border-top: 1px solid #000000; float: left; font-weight: normal; margin-top: 10px; padding: 10px 0 0 0; text-align: left; \">");
      sb.append("<div style=\" border-bottom: 1px solid #000000; float: left; margin-bottom: 10px; width: 800px;\">");

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getRegNo(isSwedish));
      sb.append(END_DIV_TAG);  
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isCatalogNumber()));
      sb.append(TAG_END);
      sb.append(error.isCatalogNumber() ? " * " : "");
      sb.append(data.getCatalogNumber());
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getSpeciesName(isSwedish));
      sb.append(END_DIV_TAG);  
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isSpecimenName()));
      sb.append(TAG_END);
      sb.append(error.isSpecimenName() ? " * " : "");
      sb.append(data.getTxFullName() != null ? data.getTxFullName() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getTypeInformation(isSwedish));
      sb.append(END_DIV_TAG); 
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isTypeinfo()));
      sb.append(TAG_END);
      sb.append(error.isTypeinfo() ? " * " : "");
      sb.append(data.getTypeStatus() != null ? data.getTypeStatus() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getFamily(isSwedish));
      sb.append(END_DIV_TAG);  
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isFamily()));
      sb.append(TAG_END);
      sb.append(error.isFamily() ? " * " : "");
      sb.append(data.getHigherTx()!= null ? data.getHigherTx() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getCollectors(isSwedish));
      sb.append(END_DIV_TAG);   
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isCollector()));
      sb.append(TAG_END);
      sb.append(error.isCollector() ? " * " : "");
      sb.append(data.getCollector() != null ? data.getCollector() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getDate(isSwedish));
      sb.append(END_DIV_TAG);    
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isDate()));
      sb.append(TAG_END);
      sb.append(error.isDate() ? " * " : "");
      sb.append(data.getStartDate() != null ? data.getStartDate() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getLocality(isSwedish));
      sb.append(END_DIV_TAG);  
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isLocality()));
      sb.append(TAG_END);
      sb.append(error.isLocality() ? " * " : "");
      sb.append(data.getLocality() != null ? data.getLocality() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getContinent(isSwedish));
      sb.append(END_DIV_TAG);   
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isContinent()));
      sb.append(TAG_END);
      sb.append(error.isContinent() ? " * " : "");
      sb.append(data.getContinent() != null ? data.getContinent() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getCountry(isSwedish));
      sb.append(END_DIV_TAG);   
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isCountry()));
      sb.append(TAG_END);
      sb.append(error.isCountry() ? " * " : "");
      sb.append(data.getCountry() != null ? data.getCountry() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getCoordinate(isSwedish));
      sb.append(END_DIV_TAG);   
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isCoordinate()));
      sb.append(TAG_END);
      sb.append(error.isCoordinate() ? " * " : "");
      sb.append(data.getCoordinateString() != null ? data.getCoordinateString() : ""); 
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getOtherInformation(isSwedish));
      sb.append(END_DIV_TAG);    
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isOtherinfo()));
      sb.append(TAG_END);
      sb.append(error.isOtherinfo() ? " * " : "");
      sb.append(data.getAllRemarkes() != null ? data.getAllRemarkes(): "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      sb.append(TAG_FIELD_START);
      sb.append(TAG_FIELD_TITLE_START);
      sb.append(CommonText.getInstance().getDeterminer(isSwedish));
      sb.append(END_DIV_TAG);   
      sb.append(TAG_COLOR_START);
      sb.append(getFontColor(error.isDeterminater()));
      sb.append(TAG_END);
      sb.append(error.isDeterminater() ? " * " : "");
      sb.append(data.getDeterminer() != null ? data.getDeterminer() : "");
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG); 
      
      sb.append(END_DIV_TAG);
      
      sb.append("<div style=\"display: block; float: left; font-size: 1em; font-weight: bold; padding: 0 15px 15px 10px; width: 700px;\">");
      sb.append(CommonText.getInstance().getDescription(isSwedish));
      sb.append(END_DIV_TAG);  
      sb.append("<div style=\"float: left; width: 700px;\">");
      sb.append("<div style=\"float: left; width: 700px; padding-left: 10px; padding-bottom: 10px; \">");
      sb.append(error.getErrorDesc());
      sb.append(END_DIV_TAG);
      sb.append(END_DIV_TAG);

      if (error.getReportorsEmail() != null && !error.getReportorsEmail().isEmpty()) {

        sb.append(TAG_FIELD_START);
        sb.append(TAG_BR);
        sb.append(TAG_BR);
        sb.append(TAG_FIELD_TITLE_START);
        sb.append(CommonText.getInstance().getReportBy(isSwedish));
        sb.append(END_DIV_TAG); 
        sb.append("<div style=\"display: inline;  \">");
        sb.append(error.getReportorsEmail());
        sb.append(END_DIV_TAG);
        sb.append(END_DIV_TAG);
        sb.append(END_DIV_TAG); 
      }

      sb.append(END_DIV_TAG); 
      sb.append(END_DIV_TAG);
    }
    return sb.toString();
  }

  private String getFontColor(boolean isError) {
    return (isError) ? RED_FONT : NORMAL_FONT;
  }
}
