/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;    
import java.time.LocalDateTime; 
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;   
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.RangeFacetMap;
import org.apache.solr.client.solrj.request.json.TermsFacetMap; 
import org.apache.solr.client.solrj.response.QueryResponse; 
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet; 
import org.apache.solr.common.SolrDocumentList;  
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.ImageData; 
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.model.StatisticData; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrService implements Serializable {

  private SolrQuery query;
  private QueryResponse response; 

  private SolrDocumentList docList;
  
  private int cumulateValue; 
  private int collectionCumulateValue; 
  private static final int MB_IMG_FATCH_SIZE = 15000;
 
//  private SolrResult result;
    
  @Inject
  @Solr
  private SolrClient client;

  public SolrService() {
  }
  
  public SolrResult searchAll(String queryText, int start, int numberPerPage) {
    log.info("searchAll: {} -- {} ", start, numberPerPage );
     
    query = new SolrQuery();
    query.set("q", queryText); 
    query.setStart(start);
    query.setRows(numberPerPage);
    query.setSort("id", SolrQuery.ORDER.asc);
    
    try {      
      response = client.query(query);      
      log.info("num: {}", response.getResults().getNumFound());
         
      return new SolrResult((int)response.getResults().getNumFound(), start, response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {      
      log.error(ex.getMessage());
      return null;
    } 
  }
  
  public SolrResult searchWithFilter(String text, List<String> filters, int start, int numPerPage) {
    log.info("searchWithFilter: {}", filters);
    
    query = new SolrQuery();
    query.setQuery(text); 
    filters.stream().forEach(f -> {
      query.addFilterQuery(f);
    });  
    query.setStart(start);
    query.setRows(numPerPage);
    query.setSort("id", SolrQuery.ORDER.asc);
    
    try {    
     response = client.query(query);      
      log.info("num: {}", response.getResults().getNumFound());
         
      return new SolrResult((int)response.getResults().getNumFound(), start, response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {      
      log.error(ex.getMessage());
      return null;
    } 
  }
   
  public List<List<ImageData>> searchImages(String input, Map<String, String> filterMap, int start) {

    query = new SolrQuery();
    query.setQuery(input)
            .addField("morphbankId")
            .addField("morphBankView")
            .addField("txFullName")
            .addField("catalogNumber")
            .addField("collectionId");

    addSearchFilters(filterMap);                        // add search query into solr 

    query.setRows(MB_IMG_FATCH_SIZE);
    try {
      response = client.query(query);    
      return createSubList(response.getBeans(ImageData.class)); 
    } catch (IOException | SolrServerException ex) {
      log.error(ex.getMessage());
    }
    return null;
  }
  
  
  private List<List<ImageData>> createSubList(List<ImageData> imgList) {

    List<List<ImageData>> mbids = new ArrayList<>();

    final int sizeOfList = imgList.size();
    final int breakApart = 15;
    for (int i = 0; i < sizeOfList; i += breakApart) {
      mbids.add(new ArrayList<>(
              imgList.subList(i, Math.min(sizeOfList, i + breakApart)))
      );
    }
    return mbids;
  } 
  
  private void addSearchFilters(Map<String, String> filterQueries) {

    if (filterQueries != null && !filterQueries.isEmpty()) {                                                // add filters into search
      filterQueries.entrySet()
              .stream()
              .forEach(x -> {
                query.addFilterQuery(x.getKey().trim() + x.getValue().trim());
              });
    } 
  }
  
  
  
  public Map<String, Map<String, Integer>> getCollectionsMonthChartData(LocalDateTime startDate) {
    log.info("getCollectionsMonthChartData : {}", startDate); 
    
    Map<String, Map<String, Integer>> collectionMonthsDataMap = new HashMap<>();
     
    StringBuilder sb = new StringBuilder();
    sb.append("catalogedDate:[");
    sb.append(startDate);
    sb.append(":00Z TO *]");
      
    final TermsFacetMap collectionNameFacet = new TermsFacetMap("collectionName").setLimit(20);
    final TermsFacetMap catalogedMonthFacet = new TermsFacetMap("catalogedMonthString").setLimit(20);  
     
    collectionNameFacet.withSubFacet("catalogedMonth", catalogedMonthFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(sb.toString())
            .returnFields("collectionName") 
            .withFacet("collectionName", collectionNameFacet);
     
    try {
      response = request.process(client);     
      NestableJsonFacet facet = response.getJsonFacetingResponse(); 
      facet.getBucketBasedFacets("collectionName")
              .getBuckets()
              .stream()
              .forEach(b -> {
                Map<String, Integer> subMap
                        = b.getBucketBasedFacets("catalogedMonth")
                                .getBuckets()
                                .stream()
                                .collect(Collectors.toMap(
                                        sub -> (String) sub.getVal(),
                                        sub -> (int) sub.getCount()));
                collectionMonthsDataMap.put((String) b.getVal(), subMap);
              });
       
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }     
    return collectionMonthsDataMap;
  }

  public Map<String, Map<String, Integer>> getCollectionsYearData(int startYear, int endYear) {

    log.info("getCollectionsYearData : {} -- {}", startYear, endYear);

    Map<String, Map<String, Integer>> collectionYearsDataMap = new HashMap<>();

    final TermsFacetMap collectionNameFacet = new TermsFacetMap("collectionName").setLimit(20);
    RangeFacetMap rangeFacet = new RangeFacetMap("catalogedYear", startYear, endYear, 1);
    collectionNameFacet.withSubFacet("catalogedYear", rangeFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery("*:*")
            .returnFields("collectionName")
            .withFacet("collectionName", collectionNameFacet);
    try {
      response = request.process(client);
      log.info("json: {}", response.jsonStr());
      
      NestableJsonFacet facet = response.getJsonFacetingResponse(); 
      facet.getBucketBasedFacets("collectionName")
              .getBuckets()
              .forEach(b -> {
                int collectionTotal = (int) b.getCount();
                
                List<BucketJsonFacet> buckets = b.getBucketBasedFacets("catalogedYear").getBuckets();
                int sum = buckets.stream().mapToInt(sub -> (int) sub.getCount()).sum();

                collectionCumulateValue = collectionTotal - sum;
                
                Map<String, Integer> subMap = new LinkedHashMap<>();  
                IntStream.range(0, buckets.size())
                        .forEach(i -> {
                          int yearTotal = (int) buckets.get(i).getCount();
                          collectionCumulateValue += yearTotal;
                          subMap.put(String.valueOf(buckets.get(i).getVal()), collectionCumulateValue);
                        });
                collectionYearsDataMap.put((String)b.getVal(), subMap);
              });
      log.info("collections map: {}", collectionYearsDataMap);
    } catch (SolrServerException | IOException ex) {
    }
    return collectionYearsDataMap;
  }

  public Map<String, Integer> getLastTenYearsRegistedData(int startYear, int endYear) {
    log.info("getLastTenYearsRegistedData : {} -- {}", startYear, endYear);

    Map<String, Integer> resultMap = new LinkedHashMap<>();
    RangeFacetMap rangeFacet = new RangeFacetMap("catalogedYear", startYear, endYear, 1); 
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery("*:*") 
            .returnFields("collectionName")  
            .withFacet("catalogedYear", rangeFacet);
 
    try {
      response = request.process(client); 
      NestableJsonFacet facet = response.getJsonFacetingResponse();
      int total = (int) facet.getCount();
      List<BucketJsonFacet> buckets = facet.getBucketBasedFacets("catalogedYear").getBuckets();
               
      int sum = buckets.stream()
              .mapToInt(b -> (int)b.getCount())
              .sum(); 
                  
      cumulateValue = total - sum;
      
      IntStream.range(0, buckets.size())  
              .forEach(i -> { 
                int yearTotal = (int) buckets.get(i).getCount();
                cumulateValue += yearTotal;
                resultMap.put(String.valueOf(buckets.get(i).getVal()), cumulateValue);        
              }); 
      
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    return resultMap;
  }

  public Map<String, Integer> getLastYearRegistedData(LocalDateTime startDate) {
    log.info("getLastYearRegistedData : {}", startDate); 
     
    StringBuilder sb = new StringBuilder();
    sb.append("catalogedDate:[");
    sb.append(startDate);
    sb.append(":00Z TO *]");
    
    final TermsFacetMap catalogedMonthFacet = new TermsFacetMap("catalogedMonthString").setLimit(20);  
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(sb.toString())
            .returnFields("collectionName")   
            .withFacet("catalogedMonth", catalogedMonthFacet);

    try {
      response = request.process(client);   
      NestableJsonFacet facet = response.getJsonFacetingResponse();
        
      return facet.getBucketBasedFacets("catalogedMonth")
              .getBuckets()
              .stream()   
              .collect(Collectors.toMap( 
                      b -> (String)b.getVal(), 
                      b -> (int)b.getCount())); 
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }   
    return null;  
  }
  
  public StatisticData getStatisticData() {
    log.info("getStatisticData");
    List<CollectionData> collections = new ArrayList<>();

    final TermsFacetMap collectionNameFacet = new TermsFacetMap("collectionName").setLimit(20);
    final TermsFacetMap collectionIdFacet = new TermsFacetMap("collectionId").setLimit(1);
    final TermsFacetMap dnaFacet = new TermsFacetMap("dna").setLimit(2);
    final TermsFacetMap mapFacet = new TermsFacetMap("map").setLimit(1);
    final TermsFacetMap imageFacet = new TermsFacetMap("image").setLimit(1);
    final TermsFacetMap inSwedenFacet = new TermsFacetMap("inSweden").setLimit(1);
    final TermsFacetMap typeFacet = new TermsFacetMap("typeStatus").setLimit(100);
    collectionNameFacet.withSubFacet("collectionId", collectionIdFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery("*:*")
            .returnFields("collectionName", "collectionId")
            .withFacet("dna", dnaFacet)
            .withFacet("image", imageFacet)
            .withFacet("map", mapFacet)
            .withFacet("sweden", inSwedenFacet)
            .withFacet("type", typeFacet)
            .withFacet("collectionName", collectionNameFacet);

    try {
      response = request.process(client); 
//      log.info("json: {}", response.jsonStr());
      
      NestableJsonFacet facet = response.getJsonFacetingResponse();
      int totalDna = (int) facet.getBucketBasedFacets("dna")
              .getBuckets().get(0).getCount();

      int totaImage = (int) facet.getBucketBasedFacets("image")
              .getBuckets().get(0).getCount();

      int totalMap = (int) facet.getBucketBasedFacets("map")
              .getBuckets().get(0).getCount();

      int totalInSweden = (int) facet.getBucketBasedFacets("sweden")
              .getBuckets().get(0).getCount();

      int totalType =  facet.getBucketBasedFacets("type")
              .getBuckets()
              .stream()
              .mapToInt(b -> (int)b.getCount())
              .sum(); 
      
      facet.getBucketBasedFacets("collectionName")
              .getBuckets()
              .stream() 
              .forEach(b -> {  
                b.getBucketBasedFacets("collectionId")
                        .getBuckets()
                        .stream()
                        .forEach(sb -> { 
                          collections.add(new CollectionData(
                                  String.valueOf(sb.getVal()), 
                                  String.valueOf(b.getVal()), 
                                  (int) b.getCount()));
                        });
                
              });
      return new StatisticData((int)response.getResults().getNumFound(), totalDna, 
              totaImage, totalMap, totalInSweden, totalType, collections);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    } 
    return new StatisticData();
  }
  
  public SolrDocumentList searchAll(int start, int pageSize) {
    log.info("searchAll: {} -- {}", start, pageSize);
      
    int count = start + pageSize;
    query = new SolrQuery();
    query.set("q", "id:*");
    query.setStart(start);
    query.setRows(100); 
    query.setSort("id", SolrQuery.ORDER.asc);

    try {  
      response = client.query(query); 
      log.info("num: {}", response.getResults().getNumFound());
      return response.getResults(); 
    } catch (SolrServerException | IOException ex) { 
      log.error(ex.getMessage());
    }
    return null;
  }
  
  public String simpleSearch(String text) {
    log.info("simpleSearch: {}", text);
    
    try { 
      query = new SolrQuery();
      query.set("q", "taxonName:" + text);
      response = client.query(query);

      docList = response.getResults();

      docList.forEach((doc) -> {
        log.info("data: {}", doc.getFieldValueMap());
      });
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return null;
  } 

}
