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
public class ImageModel { 
  public String catalogNumber;   
  public String collectionId;   
  public String morphbankId;    
  public String imageId;  
  public String txFullName;   
  public String morphBankView; 
  
  public ImageModel(String catalogNumber, String collectionId, String morphbankId,
            String imageId, String txFullName, String morphBaneView) {
    this.catalogNumber = catalogNumber;
    this.collectionId = collectionId;
    this.morphbankId = morphbankId;
    this.imageId = imageId;
    this.txFullName = txFullName;
    this.morphBankView = morphBaneView;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public String getCollectionId() {
    return collectionId;
  }

  public String getMorphbankId() {
    return morphbankId;
  }

  public String getImageId() {
    return imageId;
  }

  public String getTxFullName() {
    return txFullName;
  }

  public String getMorphBankView() {
    return morphBankView;
  }  
}
