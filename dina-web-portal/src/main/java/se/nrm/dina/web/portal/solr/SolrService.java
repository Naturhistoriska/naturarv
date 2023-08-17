package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.QueryData;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.model.SolrResult;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.SearchHelper;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrService implements Serializable {

    private SolrQuery query;
    private QueryResponse response;

    private List<String> autoCompleteList;
    private final String defaultSort;

    private String unAccentString;
    private final String text = "text";

    private final String collectionIdFilter = "collectionId:*";
    private final String collectionIdKey = "collectionId:";
    
    private String username;
    private String password;
    private QueryRequest request;

    @Inject
    @Solr
    private SolrClient client;

    @Inject
    private InitialProperties properties;

    public SolrService() {
        defaultSort = CommonText.getInstance().getCatalogedDate();
    }
    
    @PostConstruct
    public void init() {
        log.info("init from search...");
        username = properties.getUsername();
        password = properties.getPassword();  
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
//            response = client.query(query);
            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
        return new SolrResult((int) response.getResults().getNumFound(), 
                response.getBeans(SolrData.class));
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
        if (!filters.containsKey(collectionIdKey)) {
            query.addFilterQuery(collectionIdFilter);
        }

        try {
            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);
            
//            response = client.query(query);
//      log.info("response : {}", response.jsonStr());
            return new SolrResult((int) response.getResults().getNumFound(), 
                    response.getBeans(SolrData.class));
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Taxon autosuggestion search
     *
     * @param data
     * @return List
     */
    public List<String> autoCompleteTaxon(QueryData data) {
        log.info("autoCompleteTaxon : {}", data);

        final TermsFacetMap synonmyFacet = new TermsFacetMap(CommonText.getInstance().getSynonmy()).setLimit(50);
        final TermsFacetMap taxonNameFacet = new TermsFacetMap(CommonText.getInstance().getTaxonFullName()).setLimit(50);
        final JsonQueryRequest jsonQueryRequest = new JsonQueryRequest()
                .setQuery(SearchHelper.getInstance().buildSearchString(data))
                .withFacet(CommonText.getInstance().getTaxonFullName(), taxonNameFacet)
                .withFacet(CommonText.getInstance().getSynonmy(), synonmyFacet);

        try {
//            response = request.process(client); 
            jsonQueryRequest.setBasicAuthCredentials(username, password);
            response = jsonQueryRequest.process(client);
//      log.info("json: {}", response.jsonStr());
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
                    .filter(s -> s.toLowerCase().contains(data.getValue().toLowerCase()))
                    .collect(Collectors.toList()));
        }
        return autoCompleteList;
    }

    /**
     *
     * @param data
     * @return List
     */
    public List<String> autoComleteMultivalue(QueryData data) {
        log.info("text : {}", data);

        unAccentString = SearchHelper.getInstance().unAccent(data.getValue());

        String field = CommonText.getInstance().getSearchField(data.getField());
        final TermsFacetMap textFacet = new TermsFacetMap(field).setLimit(200);
        final JsonQueryRequest jsonQueryRequest = new JsonQueryRequest()
                .setQuery(SearchHelper.getInstance().buildSearchString(data))
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(field, textFacet);

        try {
            jsonQueryRequest.setBasicAuthCredentials(username, password);
            response = jsonQueryRequest.process(client);
//            response = jsonQueryRequest.process(client);
//      log.info("json: {}", response.jsonStr());
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
        }

        autoCompleteList = new ArrayList<>();
        NestableJsonFacet facet = response.getJsonFacetingResponse();
        BucketBasedJsonFacet textBucket = facet.getBucketBasedFacets(field);
        if (textBucket != null) {
            textBucket.getBuckets()
                    .stream()
                    .map(b -> (String) b.getVal())
                    .distinct()
                    .forEach(s -> {
                        if (SearchHelper.getInstance().unAccent(s).contains(unAccentString)) {
                            autoCompleteList.add(s);
                        }
                    });
        }
        return autoCompleteList;
    }

    /**
     * Autosuggestion for search
     *
     * @param data - QueryData
     * @return String
     */
    public List<String> autoComleteSearch(QueryData data) {
        log.info("autoComleteSearch: {}", data);

        String field = CommonText.getInstance().getSearchField(data.getField());
        field = field == null ? text : field;

        final TermsFacetMap termsFacet = new TermsFacetMap(field).setLimit(80);
        final JsonQueryRequest jsonQueryRequest = new JsonQueryRequest()
                .setQuery(SearchHelper.getInstance().buildSearchString(data))
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(field, termsFacet);

        try {
            jsonQueryRequest.setBasicAuthCredentials(username, password);
            response = jsonQueryRequest.process(client);
//            response = request.process(client);
//      log.info("json: {}", response.jsonStr());
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
}
