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
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.HeatmapFacetMap;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.RangeFacetMap;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.HeatmapJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import org.apache.solr.common.SolrDocumentList;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.HeatmapData;
import se.nrm.dina.web.portal.model.ImageData;
import se.nrm.dina.web.portal.model.ImageModel; 
import se.nrm.dina.web.portal.model.MapData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrService implements Serializable {

  private SolrQuery query;
  private QueryResponse response;

  private int cumulateValue;
  private int collectionCumulateValue;
  private static final int MB_IMG_FATCH_SIZE = 15000;

//  private StringBuilder imageViewSB;
//  private SolrResult result;
  @Inject
  @Solr
  private SolrClient client;

  public SolrService() {
  }

  public HeatmapData searchHeatmapWithFilter(String text, Map<String, String> filters, 
                        String regionQueryText, int gridLevel) {

    log.info("searchHeatmapWithFilter: {} -- {}", text, regionQueryText);
    log.info("searchHeatmapWithFilter:filters:  {}", filters);
    
  
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text) 
            .setLimit(0)
//            .returnFields("collectionName")
//            .withStatFacet(text, text)
            .withFacet(CommonText.getInstance().getLocations(), 
                    new HeatmapFacetMap(CommonText.getInstance().getGeopointKey())
                      .setHeatmapFormat(HeatmapFacetMap.HeatmapFormat.INTS2D) 
                      .setRegionQuery(regionQueryText)
                      .setGridLevel(gridLevel));

    if (!filters.containsKey(CommonText.getInstance().getMapKey())) {
      filters.put(CommonText.getInstance().getMapKey(), "*");
    }
    filters.entrySet().stream().forEach(e -> {
      request.withFilter(e.getKey() + e.getValue());
    });

    try {
      response = request.process(client);
      log.info("json: {}", response.jsonStr());
//      List<MapData> mapData = response.getBeans(MapData.class);
//      log.info("mapData: {}", mapData);
      int numFound = (int) response.getResults().getNumFound();

      log.info("total: {}", numFound);

      NestableJsonFacet facet = response.getJsonFacetingResponse();
      HeatmapJsonFacet heatmapFacet = facet.getHeatmapFacetByName("locations");

      if (heatmapFacet != null) {
        List<List<Integer>> list = heatmapFacet.getCountGrid();
        int columns = heatmapFacet.getNumColumns();
        int rows = heatmapFacet.getNumRows();
        double minX = heatmapFacet.getMinX();
        double maxX = heatmapFacet.getMaxX();
        double minY = heatmapFacet.getMinY();
        double maxY = heatmapFacet.getMaxY();

        log.info("x - y : {} -- {}", minX + " -- " + maxX, minY + " -- " + maxY);
        return new HeatmapData(numFound, rows, columns, minX, maxX, minY, maxY, list);
      }

    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return null;
  }

  public SolrResult searchWithFilter(String text, Map<String, String> filters, int start, int numPerPage) {
    log.info("searchWithFilter: {} -- {}", text, filters);

    query = new SolrQuery();
    query.setQuery(text);
    addSearchFilters(filters);
    query.setStart(start);
    query.setRows(numPerPage);
    query.setSort(CommonText.getInstance().getId(), SolrQuery.ORDER.asc);

    try {
      response = client.query(query);
      log.info("num: {}", response.getResults().getNumFound());

      return new SolrResult((int) response.getResults().getNumFound(), start, response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  public int getImageTotalCount(String searchText, Map<String, String> filters) {
    log.info("getImageTotalCount: {}", searchText);

    query = new SolrQuery();
    query.setQuery(searchText);
    query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));

    addSearchFilters(filters);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    int totalCount = (int) response.getResults().getNumFound();

    log.info("totalCount: {}", totalCount);
    return totalCount;
  }

  public List<ImageModel> getImageList(String searchText, int start, int numPerPage,
          Map<String, String> filters, List<String> filterList) {
    log.info("getImageList: {}", start);
    query = new SolrQuery();
    query.setQuery(searchText)
            .addField("morphbankId")
            .addField("morphBankView")
            .addField("morphbankImageId")
            .addField("txFullName")
            .addField("catalogNumber")
            .addField("collectionId")
            .setStart(start)
            .setRows(numPerPage);

    query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));
    addSearchFilters(filters);
    query.setRows(numPerPage);
    List<ImageModel> images = new ArrayList();
    try {
      client.query(query).getResults()
              .stream()
              .forEach(d -> {
                ((List<String>) d.getFieldValue("morphBankView")).stream()
                        .forEach(v -> {
                          String imageId = StringUtils.split(v, "/")[0];
                          String view = StringUtils.substringAfter(v, "/");

                          if (filterList != null && !filterList.isEmpty()) {
                            boolean isMatch = filterList.stream()
                                    .anyMatch(f -> v.contains(f));
                            if (isMatch) {
                              images.add(new ImageModel((String) d.getFieldValue("catalogNumber"),
                                      (String) d.getFieldValue("collectionId"),
                                      (String) d.getFieldValue("morphbankId"),
                                      imageId,
                                      (String) d.getFieldValue("txFullName"),
                                      view));
                            }
                          } else {
                            images.add(new ImageModel((String) d.getFieldValue("catalogNumber"),
                                    (String) d.getFieldValue("collectionId"),
                                    (String) d.getFieldValue("morphbankId"),
                                    imageId,
                                    (String) d.getFieldValue("txFullName"),
                                    view));
                          }
                        });
              });

//    client.query(query).getResults()
//              .stream()
//              .forEach(d -> {  
//                ((List<String>) d.getFieldValue("morphBankView")).stream()
//                        .filter(v -> filterList.stream()
//                                .anyMatch(f -> StringUtils.containsIgnoreCase(v, f)))
//                        .forEach(v -> { 
//                          String imageId = StringUtils.split(v, "/")[0];
//                          String view = StringUtils.substringAfter(v, "/");
//                          images.add(new ImageModel((String) d.getFieldValue("catalogNumber"),
//                                            (String) d.getFieldValue("collectionId"),
//                                            (String) d.getFieldValue("morphbankId"),
//                                            imageId,
//                                            (String) d.getFieldValue("txFullName"),
//                                            view));
//                        });
//              }); 
      return images;
    } catch (IOException | SolrServerException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  public List<List<ImageData>> searchImages(String searchText, Map<String, String> filterMap, int start) {

    query = new SolrQuery();
    query.setQuery(searchText)
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

    final TermsFacetMap collectionNameFacet = new TermsFacetMap(
            CommonText.getInstance().getCollectionName()).setLimit(20);
    RangeFacetMap rangeFacet = new RangeFacetMap(CommonText.getInstance().getCatalogedYear(),
            startYear, endYear, 1);
    collectionNameFacet.withSubFacet(CommonText.getInstance().getCatalogedYear(), rangeFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(CommonText.getInstance().getWildSearchText())
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCollectionName(), collectionNameFacet);
    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());

      NestableJsonFacet facet = response.getJsonFacetingResponse();
      facet.getBucketBasedFacets(CommonText.getInstance().getCollectionName())
              .getBuckets()
              .forEach(b -> {
                int collectionTotal = (int) b.getCount();

                List<BucketJsonFacet> buckets = b.getBucketBasedFacets(
                        CommonText.getInstance().getCatalogedYear()).getBuckets();
                int sum = buckets.stream().mapToInt(sub -> (int) sub.getCount()).sum();

                collectionCumulateValue = collectionTotal - sum;

                Map<String, Integer> subMap = new LinkedHashMap<>();
                IntStream.range(0, buckets.size())
                        .forEach(i -> {
                          int yearTotal = (int) buckets.get(i).getCount();
                          collectionCumulateValue += yearTotal;
                          subMap.put(String.valueOf(buckets.get(i).getVal()), collectionCumulateValue);
                        });
                collectionYearsDataMap.put((String) b.getVal(), subMap);
              });
    } catch (SolrServerException | IOException ex) {
    }
    return collectionYearsDataMap;
  }

  public Map<String, Integer> getLastTenYearsRegistedData(int startYear, int endYear) {
    log.info("getLastTenYearsRegistedData : {} -- {}", startYear, endYear);

    Map<String, Integer> resultMap = new LinkedHashMap<>();
    RangeFacetMap rangeFacet = new RangeFacetMap(
            CommonText.getInstance().getCatalogedYear(), startYear, endYear, 1);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(CommonText.getInstance().getWildSearchText())
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCatalogedYear(), rangeFacet);

    try {
      response = request.process(client);
      NestableJsonFacet facet = response.getJsonFacetingResponse();
      int total = (int) facet.getCount();
      List<BucketJsonFacet> buckets = facet.getBucketBasedFacets(
              CommonText.getInstance().getCatalogedYear()).getBuckets();

      int sum = buckets.stream()
              .mapToInt(b -> (int) b.getCount())
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
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet("catalogedMonth", catalogedMonthFacet);

    try {
      response = request.process(client);
      NestableJsonFacet facet = response.getJsonFacetingResponse();

      return facet.getBucketBasedFacets("catalogedMonth")
              .getBuckets()
              .stream()
              .collect(Collectors.toMap(
                      b -> (String) b.getVal(),
                      b -> (int) b.getCount()));
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return null;
  }

  public StatisticData getStatisticData(String text, Map<String, String> filters) {
    log.info("getStatisticData: {} -- {}", text, filters);
    List<CollectionData> collections = new ArrayList<>();

    final TermsFacetMap collectionNameFacet = new TermsFacetMap(
            CommonText.getInstance().getCollectionName()).setLimit(20);
    final TermsFacetMap collectionIdFacet = new TermsFacetMap(CommonText.getInstance().getCollectionId()).setLimit(1);
    final TermsFacetMap dnaFacet = new TermsFacetMap("dna").setLimit(1);
    final TermsFacetMap mapFacet = new TermsFacetMap("map").setLimit(1);
    final TermsFacetMap imageFacet = new TermsFacetMap("image").setLimit(1);
    final TermsFacetMap inSwedenFacet = new TermsFacetMap("inSweden").setLimit(1);
    final TermsFacetMap typeFacet = new TermsFacetMap("isType").setLimit(1);
    collectionNameFacet.withSubFacet("collectionId", collectionIdFacet);

    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
            .returnFields(CommonText.getInstance().getCollectionName(), CommonText.getInstance().getCollectionId())
            .withFacet("dna", dnaFacet)
            .withFacet("image", imageFacet)
            .withFacet("map", mapFacet)
            .withFacet("sweden", inSwedenFacet)
            .withFacet("type", typeFacet)
            .withFacet(CommonText.getInstance().getCollectionName(), collectionNameFacet);

    if (filters != null && !filters.isEmpty()) {
      filters.entrySet().stream().forEach(e -> {
        request.withFilter(e.getKey() + e.getValue());
      });
    }

    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());

      NestableJsonFacet facet = response.getJsonFacetingResponse();

      int totalDna = getBucketsTotal(facet.getBucketBasedFacets("dna"));
      int totaImage = getBucketsTotal(facet.getBucketBasedFacets("image"));
      int totalMap = getBucketsTotal(facet.getBucketBasedFacets("map"));
      int totalInSweden = getBucketsTotal(facet.getBucketBasedFacets("sweden"));
      int totalType = getBucketsTotal(facet.getBucketBasedFacets("type"));

      facet.getBucketBasedFacets(CommonText.getInstance().getCollectionName())
              .getBuckets()
              .stream()
              .forEach(b -> {
                b.getBucketBasedFacets(CommonText.getInstance().getCollectionId())
                        .getBuckets()
                        .stream()
                        .forEach(sb -> {
                          collections.add(new CollectionData(
                                  String.valueOf(sb.getVal()),
                                  String.valueOf(b.getVal()),
                                  (int) b.getCount()));
                        });

              });
      return new StatisticData((int) response.getResults().getNumFound(), totalDna,
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

  public SolrResult searchAll(String queryText, int start, int numberPerPage) {
    log.info("searchAll: {} -- {} ", start, numberPerPage);

    query = new SolrQuery();
    query.set("q", queryText);
    query.setStart(start);
    query.setRows(numberPerPage);
    query.setSort("id", SolrQuery.ORDER.asc);

    try {
      response = client.query(query);
      log.info("num: {}", response.getResults().getNumFound());

      return new SolrResult((int) response.getResults().getNumFound(), start, response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  private void addSearchFilters(Map<String, String> filterQueries) {

    if (filterQueries != null && !filterQueries.isEmpty()) {                                                // add filters into search
      filterQueries.entrySet()
              .stream()
              .forEach(e -> {
                query.addFilterQuery(e.getKey().trim() + e.getValue().trim());
              });
    }
  }

//  private void addSearchFilters(String key, List<String> filterList) {
//    
//    if(filterList != null && !filterList.isEmpty()) {
//      filterList.stream()
//              .forEach(f -> { 
//                imageViewSB = new StringBuilder(); 
//                imageViewSB.append(key);
//                imageViewSB.append(CommonText.getInstance().getWildCard());
//                imageViewSB.append(f);
//                imageViewSB.append(CommonText.getInstance().getWildCard());
//                query.addFilterQuery(imageViewSB.toString()); 
//              });
//    }
//  }
  private int getBucketsTotal(BucketBasedJsonFacet facet) {
    return facet.getBuckets() != null && facet.getBuckets().size() > 0
            ? (int) facet.getBuckets().get(0).getCount() : 0;
  }

//  private void addFilters(JsonQueryRequest request, Map<String, String> map) {
//    if (map != null && !map.isEmpty()) {                                                // add filters into search
//      map.entrySet()
//              .stream()
//              .forEach(x -> {
//                request.withFilter(x.getKey().trim() +  x.getValue().trim());
//              });
//    }
//  }
//   public StatisticData getStatisticDataWithQuery(String text, Map<String, String> filters) {
//    log.info("getStatisticDataWithQuery: {} -- {}", text, filters);
//    List<CollectionData> collections = new ArrayList<>();
//
//    final TermsFacetMap collectionNameFacet = new TermsFacetMap("collectionName").setLimit(20);
//    final TermsFacetMap collectionIdFacet = new TermsFacetMap("collectionId").setLimit(1);
//    final TermsFacetMap dnaFacet = new TermsFacetMap("dna").setLimit(1);
//    final TermsFacetMap mapFacet = new TermsFacetMap("map").setLimit(1);
//    final TermsFacetMap imageFacet = new TermsFacetMap("image").setLimit(1);
//    final TermsFacetMap inSwedenFacet = new TermsFacetMap("inSweden").setLimit(1);
//    final TermsFacetMap typeFacet = new TermsFacetMap("isType").setLimit(1);
//    collectionNameFacet.withSubFacet("collectionId", collectionIdFacet);
//  
//    final JsonQueryRequest request = new JsonQueryRequest()
//            .setQuery(text)  
//            .returnFields("collectionName", "collectionId")
//            .withFacet("dna", dnaFacet)
//            .withFacet("image", imageFacet)
//            .withFacet("map", mapFacet)
//            .withFacet("sweden", inSwedenFacet)
//            .withFacet("type", typeFacet)
//            .withFacet("collectionName", collectionNameFacet);
//    
//    filters.entrySet().stream().forEach(e -> {
//       request.withFilter(e.getKey() + e.getValue()); 
//    });
//      
//    try {
//      response = request.process(client); 
////      log.info("json: {}", response.jsonStr());
//      
//      NestableJsonFacet facet = response.getJsonFacetingResponse();
//       
//      int totalDna = getBucketsTotal(facet.getBucketBasedFacets("dna")); 
//      int totaImage = getBucketsTotal(facet.getBucketBasedFacets("image")); 
//      int totalMap = getBucketsTotal(facet.getBucketBasedFacets("map")); 
//      int totalInSweden = getBucketsTotal(facet.getBucketBasedFacets("sweden")); 
//      int totalType = getBucketsTotal(facet.getBucketBasedFacets("type"));
// 
//      facet.getBucketBasedFacets("collectionName")
//              .getBuckets()
//              .stream() 
//              .forEach(b -> {  
//                b.getBucketBasedFacets("collectionId")
//                        .getBuckets()
//                        .stream()
//                        .forEach(sb -> { 
//                          collections.add(new CollectionData(
//                                  String.valueOf(sb.getVal()), 
//                                  String.valueOf(b.getVal()), 
//                                  (int) b.getCount()));
//                        });
//                
//              });
//      return new StatisticData((int)response.getResults().getNumFound(), totalDna, 
//              totaImage, totalMap, totalInSweden, totalType, collections);
//    } catch (SolrServerException | IOException ex) {
//      log.error(ex.getMessage());
//    } 
//    return new StatisticData();
//  }
//  
//  public SolrResult simpleSearch(String text, Map<String, String> filters) {
//    log.info("simpleSearch: {}", text);
//    
//    query = new SolrQuery(); 
//    query.setQuery(text);
//    addSearchFilters(filters);
//    try {   
//      response = client.query(query);    
//      return new SolrResult((int)response.getResults().getNumFound(), 0, response.getBeans(SolrData.class)); 
//    } catch (SolrServerException | IOException ex) {
//      log.error(ex.getMessage());
//      return null;
//    } 
//  } 
}
