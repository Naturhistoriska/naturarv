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
public class Record {
  
  private String catalogNum;
  private String collectionName; 
  private String scientificName;
  private String highTaxon;
  
  public Record() {
    
  }

  public String getCatalogNum() {
    return catalogNum;
  }

  public void setCatalogNum(String catalogNum) {
    this.catalogNum = catalogNum;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public String getHighTaxon() {
    return highTaxon;
  }

  public void setHighTaxon(String highTaxon) {
    this.highTaxon = highTaxon;
  }
  
  
  
  
}
