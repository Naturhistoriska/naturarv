/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.model;

import java.util.List;

/**
 *
 * @author idali
 */
public class SolrResult {
  
  private final int totalFound;
  private final List<SolrData> solrData;
  
  private final int start; 
  
  public SolrResult(int totalFound, int start, List<SolrData> solrData) {
    this.totalFound = totalFound;
    this.solrData = solrData;
    this.start = start;
  }

  public int getTotalFound() {
    return totalFound;
  }

  public int getStart() {
    return start;
  }
   
  public List<SolrData> getSolrData() {
    return solrData;
  } 
}
