package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
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
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrChartService implements Serializable {

    private QueryResponse response;
    private int cumulateValue;
    private final int collectionFacetLimit = 100;
    private final String pbCollectionInvertebrates = "+collectionId:pb -higherTx:Chordata*";
    private final String pbCollectionVertebrates = "+collectionId:pb +higherTx:Chordata*";
    private final String pbVertebrates = "higherTx:Chordata*";
    private final String pbInvertebrates = "-higherTx:Chordata*";

    private final String vertebrates = "vertebrates";
    private final String invertebrates = "invertebrates";

    private Map<String, Integer> collectionMonthsDataMap;
    
    private BucketBasedJsonFacet monthBucket;
    private String username; 
    private String password;
    
    private NestableJsonFacet facet;
    private BucketBasedJsonFacet bucket;
    

    @Inject
    @Solr
    private SolrClient client;
    
    @Inject
    private InitialProperties properties;

    
//    private HttpSolrClient server;
    
    public SolrChartService() {
    }

    public SolrChartService(SolrClient client) {
        this.client = client; 
    }

    @PostConstruct
    public void init() {
        log.info("init from search...");
        username = properties.getUsername();
        password = properties.getPassword();
    }

    /**
     * Fetch records registered in last ten years with particular collection
     *
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
                .setQuery(collectionCode == null
                        ? CommonText.getInstance().getWildSearchText()
                        : CommonText.getInstance().getCollectionCodeKey() + collectionCode)
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(CommonText.getInstance().getCatalogedYear(), rangeFacet);
        
        request.setBasicAuthCredentials(username, password);
        try {
            response = request.process(client);
//      log.info("json: {}", response.jsonStr()); 
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
        }

        facet = response.getJsonFacetingResponse(); 
        bucket = facet.getBucketBasedFacets(
                CommonText.getInstance().getCatalogedYear());

        if (bucket != null) {
            List<BucketJsonFacet> buckets = bucket.getBuckets();
            int sum = buckets.stream()
                    .mapToInt(b -> (int) b.getCount())
                    .sum();
            cumulateValue = (int) facet.getCount() - sum;
            IntStream.range(0, buckets.size())
                    .forEach(i -> {
                        cumulateValue += (int) buckets.get(i).getCount();
                        collectionYearsDataMap.put(String.valueOf(buckets.get(i).getVal()), cumulateValue);
                    });
        }
        return collectionYearsDataMap;
    }
    
    public Map<String, Integer> getLastTenYearsRegistedDataWithDataset(int startYear,
            int endYear, String collectionCode, String dataset) {
        Map<String, Integer> collectionYearsDataMap = new LinkedHashMap<>();

        RangeFacetMap rangeFacet = new RangeFacetMap(
                CommonText.getInstance().getCatalogedYear(), startYear, endYear, 1);
        final JsonQueryRequest request = new JsonQueryRequest()
                .setQuery(CommonText.getInstance().getCollectionCodeKey() + collectionCode)
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(CommonText.getInstance().getCatalogedYear(), rangeFacet);
        request.setBasicAuthCredentials(username, password);
        if(dataset != null) {
            if(dataset.equals(vertebrates)) {
                request.withFilter(pbVertebrates);
            } else if(dataset.equals(invertebrates)) {
                request.withFilter(pbInvertebrates);
            }
        }
        try { 
            response = request.process(client);
//      log.info("json: {}", response.jsonStr()); 
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
        }

        facet = response.getJsonFacetingResponse();
//        int total = (int) facet.getCount();

        bucket = facet.getBucketBasedFacets(
                CommonText.getInstance().getCatalogedYear());

        if (bucket != null) {
            List<BucketJsonFacet> buckets = bucket.getBuckets();
            int sum = buckets.stream()
                    .mapToInt(b -> (int) b.getCount())
                    .sum();
            cumulateValue =  (int) facet.getCount() - sum;
            IntStream.range(0, buckets.size())
                    .forEach(i -> {
                        cumulateValue += (int) buckets.get(i).getCount();
                        collectionYearsDataMap.put(String.valueOf(buckets.get(i).getVal()), cumulateValue);
                    });
        }
        return collectionYearsDataMap;
    }
    
    
    

    public Map<String, Integer> getLastYearRegistedDataWithDataset(String searchDateRange,
            String collectionCode, String dataset) {
        log.info("getLastYearRegistedDataWithDataset : {} -- {}", searchDateRange, dataset);

        collectionMonthsDataMap = new HashMap<>();
        final TermsFacetMap catalogedMonthFacet
                = new TermsFacetMap(CommonText.getInstance()
                        .getCatalogedMonthString()).setLimit(collectionFacetLimit);
        final JsonQueryRequest request = new JsonQueryRequest()
                .setQuery(searchDateRange)
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(CommonText.getInstance().getCatalogedMonth(), catalogedMonthFacet);
 
        request.setBasicAuthCredentials(username, password);
        if (dataset != null) {
            if (dataset.equals(vertebrates)) {
                request.withFilter(pbCollectionVertebrates);
            } else if (dataset.equals(invertebrates)) {
                request.withFilter(pbCollectionInvertebrates);
            }  
        } else {
            request.withFilter(CommonText.getInstance().getCollectionCodeKey() + collectionCode);
        }

        try {
            response = request.process(client);
//      log.info("json: {}", response.jsonStr());
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
            return collectionMonthsDataMap;
        }

        bucket = response.getJsonFacetingResponse()
                .getBucketBasedFacets(CommonText.getInstance().getCatalogedMonth());
        return bucket != null
                ? bucket.getBuckets()
                        .stream()
                        .collect(Collectors.toMap(
                                b -> (String) b.getVal(),
                                b -> (int) b.getCount())) : collectionMonthsDataMap;
    }

    /**
     * Fetch records registered last year with particular collection
     *
     * @param searchDateRange - String
     * @param collectionCode - String
     *
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getLastYearRegistedData(String searchDateRange, String collectionCode) {
        log.info("getLastYearRegistedData : {} -- {}", searchDateRange, collectionCode);

        collectionMonthsDataMap = new HashMap<>();
        final TermsFacetMap catalogedMonthFacet
                = new TermsFacetMap(CommonText.getInstance()
                        .getCatalogedMonthString()).setLimit(collectionFacetLimit);
        final JsonQueryRequest request = new JsonQueryRequest()
                .setQuery(searchDateRange)
                .returnFields(CommonText.getInstance().getCollectionName())
                .withFacet(CommonText.getInstance().getCatalogedMonth(), catalogedMonthFacet);
 
        request.setBasicAuthCredentials(username, password);
        if (collectionCode != null) {
            request.withFilter(CommonText.getInstance().getCollectionCodeKey() + collectionCode);
        }

        try {
            response = request.process(client);
//      log.info("json: {}", response.jsonStr());
        } catch (SolrServerException | IOException ex) {
            log.error(ex.getMessage());
            return collectionMonthsDataMap;
        }

        monthBucket = response.getJsonFacetingResponse()
                .getBucketBasedFacets(CommonText.getInstance().getCatalogedMonth());
        return monthBucket != null
                ? monthBucket.getBuckets()
                        .stream()
                        .collect(Collectors.toMap(
                                b -> (String) b.getVal(),
                                b -> (int) b.getCount())) : collectionMonthsDataMap;
    }
}
