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

  private long numFound; 
  private List<Record> records; 
  
  public Result(int numFound, List<Record> records) {
    this.numFound = numFound;
    this.records = records;
  }

  public long getNumFound() {
    return numFound;
  }

  public List<Record> getRecords() {
    return records;
  }
  
  
}
