package se.nrm.dina.web.portal.solr;

import java.io.IOException; 
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient; 
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.RangeFacetMap;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet; 
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.utils.CommonText; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrChartService implements Serializable  {
 
  private QueryResponse response; 
  private int cumulateValue; 
  private final int collectionFacetLimit = 100;
  
  private Map<String, Integer> collectionMonthsDataMap;

  @Inject
  @Solr
  private SolrClient client;
 
  public SolrChartService() {
  }
  
  public SolrChartService(SolrClient client) {
    this.client = client;
  }
 
   /**
   * Fetch records registered in last ten years with particular collection
   * @param startYear - int
   * @param endYear - int
   * @param collectionCode - String
   * @return Map<String, Integer>
   */
  public Map<String, Integer> getLastTenYearsRegistedData(int startYear, 
                      int endYear, String collectionCode) {  
    Map<String, Integer> collectionYearsDataMap = new LinkedHashMap<>();
 
    RangeFacetMap rangeFacet = new RangeFacetMap(
            CommonText.getInstance().getCatalogedYear(), startYear, endYear, 1);   
    final JsonQueryRequest request = new JsonQueryRequest() 
            .setQuery(collectionCode == null ?
                    CommonText.getInstance().getWildSearchText() :
                    CommonText.getInstance().getCollectionCodeKey()+ collectionCode)
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCatalogedYear(), rangeFacet); 
    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr()); 
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    
    NestableJsonFacet facet = response.getJsonFacetingResponse();
    int total = (int) facet.getCount();

    BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(
            CommonText.getInstance().getCatalogedYear());

    if (bucket != null) {
      List<BucketJsonFacet> buckets = bucket.getBuckets();
      int sum = buckets.stream()
              .mapToInt(b -> (int) b.getCount())
              .sum(); 
      cumulateValue = total - sum;
      IntStream.range(0, buckets.size())
              .forEach(i -> { 
                cumulateValue += (int) buckets.get(i).getCount();
                collectionYearsDataMap.put(String.valueOf(buckets.get(i).getVal()), cumulateValue);
              });
    } 
    return collectionYearsDataMap;  
  }

  /**
   * Fetch records registered last year with particular collection
   * 
   * @param searchDateRange - String 
   * @param collectionCode  - String
   * 
   * @return Map<String, Integer>
   */
  public Map<String, Integer> getLastYearRegistedData(String searchDateRange, 
          String collectionCode) {
    log.info("getLastYearRegistedData : {} -- {}", searchDateRange, collectionCode);
     
    collectionMonthsDataMap = new HashMap<>();  
    final TermsFacetMap catalogedMonthFacet
            = new TermsFacetMap(CommonText.getInstance()
                    .getCatalogedMonthString()).setLimit(collectionFacetLimit); 
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchDateRange) 
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCatalogedMonth(), catalogedMonthFacet);
    if(collectionCode != null) { 
      request.withFilter(CommonText.getInstance().getCollectionCodeKey() + collectionCode);
    }
    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return collectionMonthsDataMap;
    }
    
    BucketBasedJsonFacet bucket = response.getJsonFacetingResponse()
            .getBucketBasedFacets(CommonText.getInstance().getCatalogedMonth());
    return bucket != null
            ? bucket.getBuckets()
                    .stream()
                    .collect(Collectors.toMap(
                            b -> (String) b.getVal(),
                            b -> (int) b.getCount())) : collectionMonthsDataMap;    
  } 
}
