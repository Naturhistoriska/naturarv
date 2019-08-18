/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

/**
 *
 * @author idali
 */
public class MapData {
  
     
    @Field
    String id; 
    @Field
    String geo; 
    @Field  
    String catalogNumber; 
    @Field
    String txFullName;  
    @Field
    Date startDate; 
    @Field
    String locality; 
    @Field
    Double latitude; 
    @Field 
    Double longitude; 
    @Field
    String latitudeText; 
    @Field
    String longitudeText;
    
    
    //    @Field("geopoint")
//    String geopoint;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGeo() {
    return geo;
  }

  public void setGeo(String geo) {
    this.geo = geo;
  }

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public void setCatalogNumber(String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  public String getTxFullName() {
    return txFullName;
  }

  public void setTxFullName(String txFullName) {
    this.txFullName = txFullName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
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
    
    
}
