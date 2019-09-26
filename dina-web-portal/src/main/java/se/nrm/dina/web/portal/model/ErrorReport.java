/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

/**
 *
 * @author idali
 */
public class ErrorReport {
  
  private boolean catalogNumber;
  private boolean specimenName;
  private boolean family;
  private boolean typeinfo;
  private boolean collector;
  private boolean date;
  private boolean locality;
  private boolean continent;
  private boolean country;
  private boolean coordinate;
  private boolean otherinfo;
  private boolean determinater;

  private String errorDesc; 
  private String reportorsEmail; 
  
  public ErrorReport() {
    
  }

  public boolean isCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(boolean catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public boolean isSpecimenName() {
    return specimenName;
  }

  public void setSpecimenName(boolean specimenName) {
    this.specimenName = specimenName;
  }

  public boolean isFamily() {
    return family;
  }

  public void setFamily(boolean family) {
    this.family = family;
  }

  public boolean isTypeinfo() {
    return typeinfo;
  }

  public void setTypeinfo(boolean typeinfo) {
    this.typeinfo = typeinfo;
  }

  public boolean isCollector() {
    return collector;
  }

  public void setCollector(boolean collector) {
    this.collector = collector;
  }

  public boolean isDate() {
    return date;
  }

  public void setDate(boolean date) {
    this.date = date;
  }

  public boolean isLocality() {
    return locality;
  }

  public void setLocality(boolean locality) {
    this.locality = locality;
  }

  public boolean isContinent() {
    return continent;
  }

  public void setContinent(boolean continent) {
    this.continent = continent;
  }

  public boolean isCountry() {
    return country;
  }

  public void setCountry(boolean country) {
    this.country = country;
  }

  public boolean isCoordinate() {
    return coordinate;
  }

  public void setCoordinate(boolean coordinate) {
    this.coordinate = coordinate;
  }

  public boolean isOtherinfo() {
    return otherinfo;
  }

  public void setOtherinfo(boolean otherinfo) {
    this.otherinfo = otherinfo;
  }

  public boolean isDeterminater() {
    return determinater;
  }

  public void setDeterminater(boolean determinater) {
    this.determinater = determinater;
  }

  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  public String getReportorsEmail() {
    return reportorsEmail;
  }

  public void setReportorsEmail(String reportorsEmail) {
    this.reportorsEmail = reportorsEmail;
  } 
}
