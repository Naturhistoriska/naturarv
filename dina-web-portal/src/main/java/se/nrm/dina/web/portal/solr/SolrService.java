/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet; 
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import se.nrm.dina.web.portal.logic.solr.Solr; 
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult; 
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrService implements Serializable {

  private SolrQuery query;
  private QueryResponse response;

  private static final String START_EM_TAG = "<em>";
  private static final String END_EM_TAG = "</em>";

  private List<String> autoCompleteList;
  private final String defaultSort;

  @Inject
  @Solr
  private SolrClient client;

  public SolrService() {
    defaultSort = CommonText.getInstance().getCatalogedDate();
  }

  /**
   * Search all the records without any filters, sorted by cataloged date
   *
   * @param start
   * @param numberPerPage
   * @return SolrResult
   */
  public SolrResult searchAll(int start, int numberPerPage) {
    log.info("searchAll: {} -- {} ", start, numberPerPage);

    query = new SolrQuery();
    query.setQuery(CommonText.getInstance().getWildSearchText())
            .setStart(start)
            .setRows(numberPerPage)
            .setSort(defaultSort, SolrQuery.ORDER.desc);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
    return new SolrResult((int) response.getResults().getNumFound(), response.getBeans(SolrData.class));
  }

  /**
   * Search records from solr
   *
   * @param text - String. Search text
   * @param filters - Map<String, String> search condition
   * @param start - int. Start search record
   * @param numPerPage - int. Number of records to return
   * @param sort - String
   * @param sortAsc - boolean. Check if sort asc
   * @return
   */
  public SolrResult searchWithFilter(String text, Map<String, String> filters,
          int start, int numPerPage, String sort, boolean sortAsc) {
    log.info("searchWithFilter: {} -- {}", text, filters);

    query = new SolrQuery();
    query.setQuery(text);
    query.setStart(start);
    query.setRows(numPerPage);
    query.setSort(sort, sortAsc ? SolrQuery.ORDER.asc : SolrQuery.ORDER.desc);
    SolrHelper.getInstance().addSearchFilters(query, filters);
    try {
      response = client.query(query);
      return new SolrResult((int) response.getResults().getNumFound(), response.getBeans(SolrData.class));
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

  /**
   * Taxon autosuggestion search
   *
   * @param text - String. Text for search
   *
   * @return List
   */
  public List<String> autoCompleteTaxon(String text) {
    log.info("autoCompleteTaxon : {}", text);

    final TermsFacetMap synonmyFacet = new TermsFacetMap(CommonText.getInstance().getSynonmy()).setLimit(50);
    final TermsFacetMap taxonNameFacet = new TermsFacetMap(CommonText.getInstance().getTaxonFullName()).setLimit(50);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
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
              .map(b -> (String) b.getVal())
              .collect(Collectors.toList()));
    }
    BucketBasedJsonFacet synonmyBucket = facet.getBucketBasedFacets(CommonText.getInstance().getSynonmy());
    if (synonmyBucket != null) {
      autoCompleteList.addAll(synonmyBucket.getBuckets()
              .stream()
              .map(b -> (String) b.getVal())
              .filter(s -> s.toLowerCase().contains(text.toLowerCase()))
              .collect(Collectors.toList()));
    }
    return autoCompleteList;
  }

  /**
   *
   * Autosuggestion from text field
   *
   * @param text - String. Text for search
   * @param field - String. Solr index field
   * @return List
   */
  public List<String> autoCompleteSearchAllField(String text, String field) {
    log.info("autoCompleteSearchAllField : {} -- {}", text, field);

    query = new SolrQuery();
    query.setQuery(text);
    query.setHighlight(true)
            .addHighlightField(field)
            .setHighlightSnippets(20)
            .setRows(500);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
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
            .collect(Collectors.toList()).stream()
            .collect(Collectors.toList());

  }
  
  /**
   * Autosuggestion for search
   * 
   * @param text - String. Text for search 
   * @param field - Solr index field
   * @return String
   */
  public List<String> autoComleteSearch(String text, String field ) {
    log.info("autoComleteSearch: {} -- {}", field, text);
      
    final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
            .withFacet(field, termsFacet);

    try {
      response = request.process(client);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
    }

    autoCompleteList = new ArrayList<>(); 
    BucketBasedJsonFacet bucket = response.getJsonFacetingResponse().getBucketBasedFacets(field);
    if (bucket != null) {
      autoCompleteList.addAll(
              bucket.getBuckets()
                      .stream()
                      .map(b -> (String) b.getVal())
                      .collect(Collectors.toList()));
    }
    return autoCompleteList;
  }
  
  public List<String> autoComleteMultivalue(String searchText, String value, String field) { 
    log.info("autoComleteMultivalue : {} -- {}", searchText, field);
    final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .returnFields(field)
            .withFacet(field, termsFacet);

    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());
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
                      .map(b -> (String) b.getVal())
                      .distinct()
                      .filter(s -> s.toLowerCase().contains(value.toLowerCase()))
                      .collect(Collectors.toList()));
    }
    return autoCompleteList;
  } 
}
