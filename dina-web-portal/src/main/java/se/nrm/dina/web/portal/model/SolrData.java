/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author idali
 */
public class SolrData {

  @Field
  public String accessionNumber;
  @Field
  public String[] accessionRemarks;
  @Field
  public String[] author;
  @Field
  public String catalogNumber;
  @Field
  public Date catalogedDate;
  @Field
  public String[] collector;
  @Field
  public Date startDate;
  @Field
  public String collectionId;
  @Field
  public String collectionName;
  @Field
  public String continent;
  @Field
  public String country;
  @Field
  public String determiner;
  
  @Field
  public String latitudeText;
  @Field
  public String longitudeText;

  @Field
  public String locality;
  @Field
  public String higherTx;
  @Field
  public String morphbankId;
  @Field
  public String[] remarks;
  @Field
  public String species;
  @Field
  public String stationFieldNumber;
  @Field
  public String[] synonym;
  @Field
  public String typeStatus;
  @Field
  public String txFullName;

  boolean selected = false;

  public SolrData() {
  }

  public String getAccessionNumber() {
    return accessionNumber;
  }

  public void setAccessionNumber(String accessionNumber) {
    this.accessionNumber = accessionNumber;
  }

  public String[] getAccessionRemarks() {
    return accessionRemarks;
  }

  public void setAccessionRemarks(String[] accessionRemarks) {
    this.accessionRemarks = accessionRemarks;
  }

  public String[] getAuthor() {
    return author;
  }

  public void setAuthor(String[] author) {
    this.author = author;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public Date getCatalogedDate() {
    return catalogedDate;
  }

  public String[] getCollector() {
    return collector;
  }

  public void setCollector(String[] collector) {
    this.collector = collector;
  }

  public String getDeterminer() {
    return determiner;
  }

  public void setDeterminer(String determiner) {
    this.determiner = determiner;
  }

  
  public void setCatalogedDate(Date catalogedDate) {
    this.catalogedDate = catalogedDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public String getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getLatitudeText() {
    return latitudeText;
  }

  public void setLatitudeText(String latitudeText) {
    this.latitudeText = latitudeText;
  }

  public String getLongitudeText() {
    return longitudeText;
  }

  public void setLongitudeText(String longitudeText) {
    this.longitudeText = longitudeText;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getHigherTx() {
    return higherTx;
  }

  public void setHigherTx(String higherTx) {
    this.higherTx = higherTx;
  }

  public String getMorphbankId() {
    return morphbankId;
  }

  public void setMorphbankId(String morphbankId) {
    this.morphbankId = morphbankId;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String[] getRemarks() {
    return remarks;
  }

  public void setRemarks(String[] remarks) {
    this.remarks = remarks;
  }
  
  

  public String getStationFieldNumber() {
    return stationFieldNumber;
  }

  public void setStationFieldNumber(String stationFieldNumber) {
    this.stationFieldNumber = stationFieldNumber;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String[] getSynonym() {
    return synonym;
  }

  public void setSynonym(String[] synonym) {
    this.synonym = synonym;
  }

  public String getTypeStatus() {
    return typeStatus;
  }

  public void setTypeStatus(String typeStatus) {
    this.typeStatus = typeStatus;
  }

  public String getTxFullName() {
    return txFullName;
  }

  public void setTxFullName(String txFullName) {
    this.txFullName = txFullName;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getAuthors() {
    return StringUtils.join(author, ", ");
  }

  public String getSynonyms() {
    return StringUtils.join(synonym, ", ");
  }
  
  public String getCollectors() {
    return StringUtils.join(collector, ", ");
  }
  
//  public String getJoinRemarks() {
//    return StringUtils.join(remarks, ", ");
//  }
//  
//  public String getJoinedAccessionRemarks() {
//    return StringUtils.join(accessionRemarks, ", ");
//  }

  public String getCoordinate() {

    if (latitudeText != null && latitudeText.length() > 0) {
      if (longitudeText != null && longitudeText.length() > 0) {
        return latitudeText + " --- " + longitudeText;
      } else {
        return latitudeText;
      }
    } else {
      if (longitudeText != null && longitudeText.length() > 0) {
        return longitudeText;
      }
      return null;
    }
  }
}
