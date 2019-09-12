/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;  
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;
import org.primefaces.model.map.LatLng;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@Slf4j
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
  public String coordinate;
  @Field
  public String determiner;
  @Field
  public double latitude;
  @Field
  public double longitude;
  @Field
  public String latitudeText;
  @Field
  public String longitudeText;
  @Field
  public String locality;
  @Field
  public String higherTx;
  @Field
  public String map;
  @Field
  public String morphbankId;
  @Field
  public String[] morphbankImageId;
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
  boolean imageExist;
  boolean displayMap;
  boolean displayImage; 

  private List<String> thumbs;
  private List<String> jpgs;
 
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
   
  public void setCatalogedDate(Date catalogedDate) {
    this.catalogedDate = catalogedDate;
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
 
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
 
  public String getCollectionName() {
    return collectionName;
  }
   
  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }
 
  public String getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(String collectionId) {
    this.collectionId = collectionId;
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
 
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
 
  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
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

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
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

  public boolean isImageExist() {
    return imageExist;
  }

  public void setImageExist(boolean imageExist) {
    this.imageExist = imageExist;
  }

  public boolean isDisplayMap() {
    return displayMap;
  }

  public void setDisplayMap(boolean displayMap) {
    this.displayMap = displayMap;
  }

  public boolean isDisplayImage() {
    return displayImage;
  }

  public void setDisplayImage(boolean displayImage) {
    this.displayImage = displayImage;
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
  
  public String getAllRemarkes() {
    return StringUtils.join(remarks, ", ");
  }
 
  public LatLng getLatLng() {
    return new LatLng(latitude, longitude);
  }
  
  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }

  public void setImages(String morphbankImageUrl) {  
    thumbs = new ArrayList<>();
    jpgs = new ArrayList<>();
    if (morphbankImageId != null) {
      Arrays.asList(morphbankImageId)
              .stream()
              .forEach(i -> {
                thumbs.add(HelpClass.getInstance().buildImagePath(i, CommonText.getInstance().getImageTypeThumb(), morphbankImageUrl));
                jpgs.add(HelpClass.getInstance().buildImagePath(i, CommonText.getInstance().getImageTypeJpg(), morphbankImageUrl));
              }); 
    }
  }
  
  public boolean isSingleMapLink() {
    return map != null;
  }
 
  public List<String> getThumbs() {
    return thumbs;
  }
  
  public List<String> getJpgs() {
    return jpgs;
  }
   
//  private String buildString(String id, String type) {
//    sb = new StringBuilder();
//    sb.append(morphbankImagePath);
//    sb.append(CommonText.getInstance().getImageQueryId());
//    sb.append(id);
//    sb.append(type);
//    return sb.toString();
//  }
 
  public String getCoordinateString() {

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
