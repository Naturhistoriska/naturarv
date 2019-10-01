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
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import org.apache.solr.client.solrj.response.json.HeatmapJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet; 
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.GeoData;
import se.nrm.dina.web.portal.model.HeatmapData;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

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
//  private static final int MB_IMG_FATCH_SIZE = 15000;

  private static final String START_EM_TAG = "<em>";
  private static final String END_EM_TAG = "</em>";

  private List<String> autoCompleteList;

  @Inject
  private InitialProperties properties;

  @Inject
  @Solr
  private SolrClient client;

  public SolrService() {
  }

  public SolrResult searchWithFilter(String text, Map<String, String> filters, int start, int numPerPage) {
    log.info("searchWithFilter: {} -- {}", text, filters);

    query = new SolrQuery();
    query.setQuery(text);
    addSearchFilters(filters);
    query.setStart(start);
    query.setRows(numPerPage);
    query.setSort(CommonText.getInstance().getCatalogedDate(), SolrQuery.ORDER.desc);

    try {
      response = client.query(query);   
      return new SolrResult((int) response.getResults().getNumFound(), 
              response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  public SolrResult searchAll(int start, int numberPerPage) {
    log.info("searchAll: {} -- {} ", start, numberPerPage);

    query = new SolrQuery();
    query.setQuery(CommonText.getInstance().getWildSearchText());
    query.setStart(start);
    query.setRows(numberPerPage);
    query.setSort(CommonText.getInstance().getCatalogedDate(), SolrQuery.ORDER.asc);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
    return new SolrResult((int) response.getResults().getNumFound(), response.getBeans(SolrData.class));
  }

  /**
   * autoCompleteSearchAllField - auto complete search from default field
   *
   * @param input
   * @param content
   * @param field
   * @return
   */
  public List<String> autoCompleteSearchAllField(String input, String content, String field) {
    log.info("autoCompleteSearchAllField : {} -- {}", input, content);

    String searchText = SolrHelper.getInstance().buildSearchString(input, field, content);
    
    query = new SolrQuery(); 
    query.setQuery(searchText);
    query.setHighlight(true)
              .addHighlightField(field)
              .setHighlightSnippets(20)
              .setRows(500);
    try { 
      response = client.query(query);
      Map<String, Map<String, List<String>>> highlightSnippets = response.getHighlighting();
      List<String> results = new ArrayList<>();
      highlightSnippets.values()
              .stream()
              .collect(Collectors.toList())
              .stream()
              .forEach(v -> {
                v.values().stream()
                        .collect(Collectors.toList()).stream()
                        .forEach(l -> {
                          results.addAll(l);
                        });
              });
      return results.stream()
              .map(s -> StringUtils.substringBetween(s, START_EM_TAG, END_EM_TAG).trim())
              .distinct()
              .collect(Collectors.toList());
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return null;
  }

  public List<String> autoCompleteTaxon(String input, String content) { 
    log.info("autoCompleteTaxon");

    String searchText = SolrHelper.getInstance().buildSearchString(input,
                            CommonText.getInstance().getTxSearch(), content);

    final TermsFacetMap synonmyFacet = new TermsFacetMap(CommonText.getInstance().getSynonmy()).setLimit(20);
    final TermsFacetMap taxonNameFacet = new TermsFacetMap(CommonText.getInstance().getTaxonFullName()).setLimit(20);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .withFacet(CommonText.getInstance().getTaxonFullName(), taxonNameFacet)
            .withFacet(CommonText.getInstance().getSynonmy(), synonmyFacet);

    try {
      response = request.process(client); 
      log.info("json: {}", response.jsonStr()); 
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    
    autoCompleteList = new ArrayList<>();
    NestableJsonFacet facet = response.getJsonFacetingResponse();
    BucketBasedJsonFacet taxonBucket = facet.getBucketBasedFacets(CommonText.getInstance().getTaxonFullName());
    if (taxonBucket != null) {
      autoCompleteList.addAll(taxonBucket.getBuckets() 
              .stream()
              .map(b -> (String)b.getVal()) 
              .collect(Collectors.toList())); 
    } 
    
    log.info("data: {}", autoCompleteList);
    BucketBasedJsonFacet synonmyBucket = facet.getBucketBasedFacets(CommonText.getInstance().getSynonmy());
    if (taxonBucket != null) {
      autoCompleteList.addAll(synonmyBucket.getBuckets() 
              .stream()
              .map(b -> (String)b.getVal())
              .filter(s -> s.contains(input)) 
              .collect(Collectors.toList())); 
    } 
    log.info("data: {}", autoCompleteList);
    return autoCompleteList; 
  }
  
  
  public List<String> autoComleteSearch(String value, String content, 
          String field, String searchField) {
    log.info("autoComleteSearch: {} -- {}", field, searchField);
    String searchText = SolrHelper.getInstance().buildSearchString(value, searchField, content);
    log.info("searchText: {}", searchText);
    final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .withFacet(field, termsFacet);

    try {
      response = request.process(client);  
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }

    autoCompleteList = new ArrayList<>();
    NestableJsonFacet facet = response.getJsonFacetingResponse();
    BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(field);
    if (bucket != null) {
      autoCompleteList.addAll(
              bucket.getBuckets() 
                .stream()
                .map(b ->  (String)b.getVal())   
                .collect(Collectors.toList())); 
    }  
    return autoCompleteList;
  }
  
  public List<String> autoComleteMultivalue(String value, String content, String field) {
    String searchText = SolrHelper.getInstance().buildSearchString(value, field, content);
     
    final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .withFacet(field, termsFacet);

    try {
      response = request.process(client); 
      log.info("json: {}", response.jsonStr()); 
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }

    autoCompleteList = new ArrayList<>();
    NestableJsonFacet facet = response.getJsonFacetingResponse();
    BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(field);
    if (bucket != null) {
      autoCompleteList.addAll(
              bucket.getBuckets() 
                .stream()
                .map(b ->  (String)b.getVal())  
                .filter(s -> s.contains(value)) 
                .collect(Collectors.toList())); 
    }  
    return autoCompleteList;
  }
  
//  public List<String> autoCompleteAccession(String value, String content, String field) {
// 
//    String searchText = SolrHelper.getInstance().buildSearchString(value, field, content);
//     
//    final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
//    final JsonQueryRequest request = new JsonQueryRequest()
//            .setQuery(searchText)
//            .withFacet(field, termsFacet);
//
//    try {
//      response = request.process(client); 
//      log.info("json: {}", response.jsonStr()); 
//    } catch (SolrServerException | IOException ex) {
//      log.error(ex.getMessage());
//    }
//
//    autoCompleteList = new ArrayList<>();
//    NestableJsonFacet facet = response.getJsonFacetingResponse();
//    BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(field);
//    if (bucket != null) {
//      autoCompleteList.addAll(
//              bucket.getBuckets() 
//                .stream()
//                .map(b ->  (String)b.getVal())  
//                .filter(s -> s.contains(value)) 
//                .collect(Collectors.toList())); 
//    }  
//    return autoCompleteList;
//  }
  
  public List<String> autoCompleteSearch(String value, String field, String content) {
 
    String searchText = SolrHelper.getInstance().buildSearchString(value,field, content);
    final TermsFacetMap facet = new TermsFacetMap(field).setLimit(80);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .withFacet(field, facet);
        
    try {
      response = request.process(client); 
      log.info("json: {}", response.jsonStr()); 
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    autoCompleteList = new ArrayList<>();
    NestableJsonFacet jsonFacet = response.getJsonFacetingResponse();
    BucketBasedJsonFacet bucket = jsonFacet.getBucketBasedFacets(field);
    if (bucket != null) {
      autoCompleteList.addAll(
              bucket.getBuckets() 
                .stream()
                .map(b -> (String)b.getVal())   
                .collect(Collectors.toList())); 
    }  
    return autoCompleteList; 
  }
  
  



  

  

  public HeatmapData searchHeatmapWithFilter(String text, Map<String, String> filters,
          String regionQueryText, int gridLevel) {
    log.info("searchHeatmapWithFilter: {} -- {}", regionQueryText, gridLevel);

    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
            .setLimit(0)
            .withFilter(CommonText.getInstance().getGeoKey() + regionQueryText)
            .withFacet(CommonText.getInstance().getLocations(),
                    new HeatmapFacetMap(CommonText.getInstance().getGeopoint())
                            .setHeatmapFormat(HeatmapFacetMap.HeatmapFormat.INTS2D)
                            .setRegionQuery(regionQueryText)
                            .setGridLevel(gridLevel));

    filters.entrySet().stream().forEach(e -> {
      request.withFilter(e.getKey() + e.getValue());
    });

    if (!filters.containsKey(CommonText.getInstance().getMapKey())) {
      request.withFilter(CommonText.getInstance().getMapKey() + String.valueOf(true));
    }

    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr()); 
      int numFound = (int) response.getResults().getNumFound();
      log.info("numFound: {}", numFound);
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

        return new HeatmapData(numFound, rows, columns, minX, maxX, minY, maxY, list);
      }

    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return null;
  }

  public List<GeoData> searchSmallDataSet(String searchText, Map<String, String> filters, String regionText) {
    log.info("searchSmallDataSet: {} -- {}", filters, regionText);
    List<GeoData> list = new ArrayList<>();

    final TermsFacetMap coordinateFacet = new TermsFacetMap(CommonText.getInstance().getCoordinate()).setLimit(500);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .withFilter(CommonText.getInstance().getGeoKey() + regionText)
            .withFacet(CommonText.getInstance().getCoordinate(), coordinateFacet);
    filters.entrySet().stream().forEach(e -> {
      request.withFilter(e.getKey() + e.getValue());
    });

    if (!filters.containsKey(CommonText.getInstance().getMapKey())) {
      request.withFilter(CommonText.getInstance().getMapKey() + String.valueOf(true));
    }

    try {
      response = request.process(client);
      BucketBasedJsonFacet bucket = response.getJsonFacetingResponse()
              .getBucketBasedFacets(CommonText.getInstance().getCoordinate());

      if (bucket != null) {
        bucket.getBuckets().stream()
                .forEach(b -> {
                  int total = (int) b.getCount();
                  String value = (String) b.getVal();
                  List<SolrData> solrDataList = searchByCoordinate(searchText, filters, value);
                  if (total == 1) {
                    list.add(new GeoData(total, solrDataList.get(0)));
                  } else {
                    list.add(new GeoData(total, solrDataList));
                  }
                });
      }
      return list;
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    return null;
  }

  private List<SolrData> searchByCoordinate(String text, Map<String, String> filters, String coordinate) {

    query = new SolrQuery();
    query.setQuery(text);
    query.addFilterQuery(CommonText.getInstance().getCoordinateKey() + coordinate);
    addSearchFilters(filters);
    query.setRows(5000);
    try {
      return client.query(query).getBeans(SolrData.class);
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    return null;
  }

  public List<SolrData> searchSpatialData(String text, Map<String, String> filters, String regionQueryText) {
    try {
      query = new SolrQuery();
      query.setQuery(text);
      query.addFilterQuery("geo:" + regionQueryText);
      addSearchFilters(filters);
      query.setRows(10);

      return client.query(query).getBeans(SolrData.class);
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    return null;
  }

  public int getImageTotalCount(String searchText, Map<String, String> filters, List<String> filterList) {
    log.info("getImageTotalCount: {}", searchText);

    query = new SolrQuery();

    String selectedImageViewSearchText = SolrHelper.getInstance().buildImageOptionSearchText(searchText, filterList);
    query.setQuery(selectedImageViewSearchText);
    query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));

    addSearchFilters(filters);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    int totalCount = (int) response.getResults().getNumFound();
    return totalCount;
  }

  public List<ImageModel> getImageList(String searchText, int start, int numPerPage,
          Map<String, String> filters, List<String> filterList) {
    log.info("getImageList: {} -- {}", start, filterList);

    String imageThumbPath = properties.getMorphbankThumbPath();
    query = new SolrQuery();

    String selectedImageViewSearchText = SolrHelper.getInstance().buildImageOptionSearchText(searchText, filterList);
    log.info("imageView SearchTexgt: {}", selectedImageViewSearchText);
    query.setQuery(selectedImageViewSearchText)
            .addField(CommonText.getInstance().getMorphbankId())
            .addField(CommonText.getInstance().getImageView())
            .addField(CommonText.getInstance().getTaxonFullName())
            .addField(CommonText.getInstance().getCatalogNumber())
            .addField(CommonText.getInstance().getCollectionId())
            .setStart(start);

    query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));
    addSearchFilters(filters);
    query.setRows(numPerPage);
    List<ImageModel> images = new ArrayList();
    try {
      client.query(query).getResults()
              .stream()
              .forEach(d -> {
                ((List<String>) d.getFieldValue(CommonText.getInstance().getImageView())).stream()
                        .forEach(v -> {
                          String imageId = StringUtils.split(v, "/")[0];
                          String view = StringUtils.substringAfter(v, "/");
                          if (isMatchFilter(v, filterList)) {
                            images.add(new ImageModel((String) d.getFieldValue(CommonText.getInstance().getCatalogNumber()),
                                    (String) d.getFieldValue(CommonText.getInstance().getCollectionId()),
                                    (String) d.getFieldValue(CommonText.getInstance().getMorphbankId()),
                                    HelpClass.getInstance().buildImagePath(imageId, CommonText.getInstance().getImageTypeThumb(), imageThumbPath),
                                    (String) d.getFieldValue(CommonText.getInstance().getTaxonFullName()), view));
                          }
                        });
              });
      return images;
    } catch (IOException | SolrServerException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  private boolean isMatchFilter(String imageView, List<String> filters) {
    if (filters == null || filters.isEmpty()) {
      return true;
    }
    return filters.stream().anyMatch(v -> imageView.contains(v));
  }

  public SolrData getImagesByMorphbankId(String morphbankId) {
    String morphbankImagePath = properties.getMorphbankThumbPath();
    query = new SolrQuery();
    query.setQuery(CommonText.getInstance().getMorphbankIdKey() + morphbankId);
    try {
      response = client.query(query);
      List<SolrData> data = response.getBeans(SolrData.class);
      if (data != null && !data.isEmpty()) {
        SolrData solrData = data.get(0);
        solrData.setImages(morphbankImagePath);
        return solrData;
      }
      return null;
    } catch (IOException | SolrServerException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  public Map<String, Map<String, Integer>> getCollectionsMonthChartData(LocalDateTime startDate) {
    log.info("getCollectionsMonthChartData : {}", startDate);

    Map<String, Map<String, Integer>> collectionMonthsDataMap = new HashMap<>();

    final TermsFacetMap collectionNameFacet = new TermsFacetMap(
            CommonText.getInstance().getCollectionName()).setLimit(20);
    final TermsFacetMap catalogedMonthFacet = new TermsFacetMap(
            CommonText.getInstance().getCatalogedMonthString()).setLimit(20);

    collectionNameFacet.withSubFacet(CommonText.getInstance().getCatalogedMonth(), catalogedMonthFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(SolrHelper.getInstance().buildSearchCatalogedDateText(startDate))
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCollectionName(), collectionNameFacet);

    try {
      response = request.process(client);
      NestableJsonFacet facet = response.getJsonFacetingResponse();
      BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(CommonText.getInstance().getCollectionName());
      if (bucket != null) {
        bucket.getBuckets()
                .stream()
                .forEach(b -> {
                  Map<String, Integer> subMap
                          = b.getBucketBasedFacets(CommonText.getInstance().getCatalogedMonth())
                                  .getBuckets()
                                  .stream()
                                  .collect(Collectors.toMap(
                                          sub -> (String) sub.getVal(),
                                          sub -> (int) sub.getCount()));
                  collectionMonthsDataMap.put((String) b.getVal(), subMap);
                });
      }

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
      BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(CommonText.getInstance().getCollectionName());
      if (bucket != null) {
        bucket.getBuckets()
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
      }

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
                  int yearTotal = (int) buckets.get(i).getCount();
                  cumulateValue += yearTotal;
                  resultMap.put(String.valueOf(buckets.get(i).getVal()), cumulateValue);
                });
      }

    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
    }
    return resultMap;
  }

  public Map<String, Integer> getLastYearRegistedData(LocalDateTime startDate) {
    log.info("getLastYearRegistedData : {}", startDate);

    final TermsFacetMap catalogedMonthFacet = new TermsFacetMap(
            CommonText.getInstance().getCatalogedMonthString()).setLimit(20);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(SolrHelper.getInstance().buildSearchCatalogedDateText(startDate))
            .returnFields(CommonText.getInstance().getCollectionName())
            .withFacet(CommonText.getInstance().getCatalogedMonth(), catalogedMonthFacet);

    try {
      response = request.process(client);
      NestableJsonFacet facet = response.getJsonFacetingResponse();

      BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(CommonText.getInstance().getCatalogedMonth());
      return bucket != null
              ? bucket.getBuckets()
                      .stream()
                      .collect(Collectors.toMap(
                              b -> (String) b.getVal(),
                              b -> (int) b.getCount())) : null;
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

      BucketBasedJsonFacet bucket = facet.getBucketBasedFacets(CommonText.getInstance().getCollectionName());
      if (bucket != null) {
        bucket.getBuckets()
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
      }
      return new StatisticData((int) response.getResults().getNumFound(), totalDna,
              totaImage, totalMap, totalInSweden, totalType, collections);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }
    return new StatisticData();
  }

//  public SolrDocumentList searchAll(int start, int pageSize) {
//    log.info("searchAll: {} -- {}", start, pageSize);
//
//    int count = start + pageSize;
//    query = new SolrQuery();
//    query.set("q", "id:*");
//    query.setStart(start);
//    query.setRows(pageSize);
//    query.setSort("id", SolrQuery.ORDER.asc);
//
//    try {
//      response = client.query(query);
//      log.info("num: {}", response.getResults().getNumFound());
//      return response.getResults();
//    } catch (SolrServerException | IOException ex) {
//      log.error(ex.getMessage());
//    }
//    return null;
//  }
  private void addSearchFilters(Map<String, String> filterQueries) {

    if (filterQueries != null && !filterQueries.isEmpty()) {                                                // add filters into search
      filterQueries.entrySet()
              .stream()
              .forEach(e -> {
                query.addFilterQuery(e.getKey().trim() + e.getValue().trim());
              });
    }
  }

  private int getBucketsTotal(BucketBasedJsonFacet bucket) {
    return bucket != null
            ? bucket.getBuckets() != null && bucket.getBuckets().size() > 0
            ? (int) bucket.getBuckets().get(0).getCount() : 0 : 0;
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
//  public List<GeoData> searchGroupData(String text, Map<String, String> filters) {
//    log.info("searchHeatmapWithFilter: {} -- {}", text, filters);
//   
//    
//    List<GeoData> list = new ArrayList<>();
//    try { 
//      query = new SolrQuery();
//      query.setQuery(text); 
//      query.addFilterQuery("geopoint:*");
//      query.setParam("group", true).setParam("group.field", "locality");  
//      query.setRows(5000);
//      
//      response = client.query(query);
//
//      NamedList thisGroupInfo = (NamedList) ((NamedList) (response.getResponse()).get("grouped")).get("locality");
//      Number totalUngrouped = (Number) thisGroupInfo.get("matches");
//      log.info("totalUngrouped: {}", totalUngrouped); 
//      
//      List<Object> groupData = (List<Object>) thisGroupInfo.get("groups");
//      
//      groupData.stream() 
//              .map(o -> ((SolrDocumentList) ((NamedList) o).get("doclist")))
//              .forEach(d -> {
//                SolrDocument doc = d.get(0);  
//                list.add(new GeoData((int)d.getNumFound(),  
//                        (double) doc.getFieldValue("latitude"), 
//                        (double) doc.getFieldValue("longitude"), 
//                        (String) doc.getFieldValue("locality"))); 
//               
//              });
//    } catch (SolrServerException | IOException ex) {
//      log.warn(ex.getMessage());
//    } 
//    return list;
//  }
//  public List<GeoData> searchGroupData(String text, Map<String, String> filters) {
//    log.info("searchGroupData: {} -- {}", text, filters);
//
//    List<GeoData> list = new ArrayList<>();
//    try {
//      query = new SolrQuery();
//      query.setQuery(text);
//      query.addFilterQuery("geopoint:*");
//      query.setParam("group", true).setParam("group.field", "locality");
//      query.setRows(5000);
//
//      response = client.query(query);
//
//      NamedList thisGroupInfo = (NamedList) ((NamedList) (response.getResponse()).get("grouped")).get("locality");
//      Number totalUngrouped = (Number) thisGroupInfo.get("matches");
//      log.info("totalUngrouped: {}", totalUngrouped);
//
//      List<Object> groupData = (List<Object>) thisGroupInfo.get("groups");
//
//      groupData.stream()
//              .map(o -> ((SolrDocumentList) ((NamedList) o).get("doclist")))
//              .forEach(d -> {
//                SolrDocument doc = d.get(0);
//                list.add(new GeoData((int) d.getNumFound(),
//                        (double) doc.getFieldValue("latitude"),
//                        (double) doc.getFieldValue("longitude"),
//                        (String) doc.getFieldValue("locality")));
//
//              });
//    } catch (SolrServerException | IOException ex) {
//      log.warn(ex.getMessage());
//    }
//    return list;
//  }
}
