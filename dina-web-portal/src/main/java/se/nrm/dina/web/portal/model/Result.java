/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author idali
 */
public class Result implements Serializable {

//  private long numFound; 
//  private List<Record> records; 
  private String id;
  private String catalogNumber;
  private String fullName;
  
  
  public Result(String id, String catalogNumber, String fullName) {
    this.id = id;
    this.catalogNumber = catalogNumber;
    this.fullName = fullName;
  }

//  public long getNumFound() {
//    return numFound;
//  }
//
//  public List<Record> getRecords() {
//    return records;
//  }

  public String getId() {
    return id;
  }
  

  public String getCatalogNumber() {
    return catalogNumber;
  }

  public String getFullName() {
    return fullName;
  }
  
  
}
