package se.nrm.dina.web.portal.model;

import java.util.List;

/**
 *
 * @author idali
 */
public class SolrResult {
  
  private final int totalFound;
  private final List<SolrData> solrData;
   
  public SolrResult(int totalFound, List<SolrData> solrData) {
    this.totalFound = totalFound;
    this.solrData = solrData; 
  }

  public int getTotalFound() {
    return totalFound;
  }
 
  public List<SolrData> getSolrData() {
    return solrData;
  } 
}
