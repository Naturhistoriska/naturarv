package se.nrm.dina.web.portal.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author idali
 */
public class StatisticData implements Serializable {

  private int total;                                                          // total number of registed specimens in all collections
  private int totalDnas;
  private int totalImages;
  private int totalMaps;
  private int totalInSweden;
  private int totalType;
  private List<CollectionData> collections;                                   // list of collections in all institutions

  public StatisticData() {

  }
  
  public StatisticData(int total, int totalDnas, int totalImages, int totalMaps, 
                       int totalInSweden, int totalType, List<CollectionData> collections) {
    this.total = total;
    this.totalDnas = totalDnas;
    this.totalImages = totalImages;
    this.totalMaps = totalMaps; 
    this.totalInSweden = totalInSweden; 
    this.totalType = totalType;
    this.collections = collections;
  }

  public int getTotal() {
    return total;
  }

  public int getTotalDnas() {
    return totalDnas;
  }

  public int getTotalImages() {
    return totalImages;
  }

  public int getTotalMaps() {
    return totalMaps;
  }

  public int getTotalInSweden() {
    return totalInSweden;
  }

  public int getTotalType() {
    return totalType;
  } 
  
  public List<CollectionData> getCollections() {
    return collections;
  }

}
