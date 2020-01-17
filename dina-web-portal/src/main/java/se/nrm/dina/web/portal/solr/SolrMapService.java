package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.BucketJsonFacet;
import se.nrm.dina.web.portal.logic.solr.Solr; 
import se.nrm.dina.web.portal.model.GeoHashData;
import se.nrm.dina.web.portal.model.SolrData; 

/**
 *
 * @author idali
 */
@Slf4j
public class SolrMapService implements Serializable {

  private final String geohash = "geohash";
  private final String geohashKey = "geohash:";
  private final String geoKey = "geo:";
  private final String geo ="geo"; 
  private final String coordinate = "coordinate";
  private final String coordinateKey = "coordinate:";

  @Inject
  @Solr
  private SolrClient client;

  private QueryResponse response;
  private SolrQuery query;
  
  private TreeSet<Integer> set;
   
  public List<GeoHashData> searchGeoHash(String text, String regionQueryText,
          Map<String, String> filters, String prefix) {
    log.info("searchGeoHash : {}", text);
    
    set = new TreeSet<>();
    final TermsFacetMap coordinateFacet = new TermsFacetMap(coordinate).setLimit(1); 
    final TermsFacetMap geoFacet = new TermsFacetMap(geohash).setLimit(9); 
    final TermsFacetMap geoHashFacet = new TermsFacetMap(geohash).setLimit(500).setTermPrefix(prefix);
    geoHashFacet.withSubFacet(coordinate, coordinateFacet);
    geoHashFacet.withSubFacet(geo, geoFacet);
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
            .returnFields(coordinate)
            .withFilter(geoKey + regionQueryText)
            .withFacet(geohash, geoHashFacet);
     
    SolrHelper.getInstance().addSearchFilters(request, filters); 
    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());
    } catch (SolrServerException | IOException ex) {
      log.warn(ex.getMessage());
      return null;
    } 
    
    List<GeoHashData> list = new ArrayList<>();
    BucketBasedJsonFacet bucket = response.getJsonFacetingResponse()
            .getBucketBasedFacets(geohash);
 
    if(bucket != null) {
      bucket.getBuckets().stream()  
              .forEach(b -> {
                int count = (int) b.getCount();
                if(count > 1) {
                  set.add(count);
                } 
                BucketJsonFacet subBucket = b.getBucketBasedFacets(coordinate)
                        .getBuckets().stream()
                        .findFirst()
                        .get();  
                list.add(new GeoHashData(
                        (String) b.getVal(),  
                        (String) subBucket.getVal(), count, 
                        b.getBucketBasedFacets(geo).getBuckets()
                          .stream()
                          .map(v -> (String) v.getVal())
                          .collect(Collectors.toList()))); 
              });
    } 
    return list;
  }
  
  public TreeSet<Integer> getSet() {
    return set;
  }

  public Map<String, Integer> searchSmallDataSet(String searchText, Map<String, String> filters, String geohash) {
    log.info("searchSmallDataSet: {} -- {}", filters, geohash);
 
    final TermsFacetMap coordinateFacet = new TermsFacetMap(coordinate).setLimit(500);  
    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(searchText)
            .returnFields(coordinate)
            .withFilter(geohashKey + geohash) 
            .withFacet(coordinate, coordinateFacet);
     SolrHelper.getInstance().addSearchFilters(request, filters); 

    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
    BucketBasedJsonFacet bucket = response.getJsonFacetingResponse().getBucketBasedFacets(coordinate); 
    return bucket != null ? bucket.getBuckets()
                    .stream()
                    .collect(Collectors.toMap(
                            b -> (String) b.getVal(),
                            b -> (int) b.getCount())) : null;  
 
  }

  public List<SolrData> searchSpatialData(String text, Map<String, String> filters, String coordinates) {
    log.info("searchSpatialData: {}", coordinates);
    query = new SolrQuery();
    query.setQuery(text);
    query.addFilterQuery(coordinateKey + coordinates);
    SolrHelper.getInstance().addSearchFilters(query, filters);
    query.setRows(2000);
    try {
      return client.query(query).getBeans(SolrData.class);
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }
}
