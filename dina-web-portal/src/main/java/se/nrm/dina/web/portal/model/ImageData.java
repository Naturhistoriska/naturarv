/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author idali
 */
public class ImageData {
  
  @Field
  public String catalogNumber;  
  @Field
  public String collectionId;    
  
  @Field
  public String morphbankId;   
  @Field
  public String[] morphbankImageId;    
  @Field
  public String txFullName;  
  @Field
  public String[] morphBankView; 
   
  public ImageData() {
    
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public String getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
  }

  public String getMorphbankId() {
    return morphbankId;
  }

  public void setMorphbankId(String morphbankId) {
    this.morphbankId = morphbankId;
  }

  public String[] getMorphbankImageId() {
    return morphbankImageId;
  }

  public void setMorphbankImageId(String[] morphbankImageId) {
    this.morphbankImageId = morphbankImageId;
  }
   
  public String getTxFullName() {
    return txFullName;
  }

  public void setTxFullName(String txFullName) {
    this.txFullName = txFullName;
  }

  public String[] getMorphBankView() {
    return morphBankView;
  }

  public void setMorphBankView(String[] morphBankView) {
    this.morphBankView = morphBankView;
  }
  
  
  
}
