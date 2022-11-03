package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.json.BucketBasedJsonFacet;
import org.apache.solr.client.solrj.response.json.NestableJsonFacet;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrStatisticService implements Serializable {

  private QueryResponse response;
  private final int collectionFacetLimit = 100;

  @Inject
  @Solr
  private SolrClient client;

  public SolrStatisticService() {
  }

  public StatisticData getStatisticData(String text, Map<String, String> filters) {
    log.info("getStatisticData: {} -- {}", text, filters);

    List<CollectionData> collections = new ArrayList<>();

    final TermsFacetMap collectionNameFacet = new TermsFacetMap(
            CommonText.getInstance().getCollectionName()).setLimit(collectionFacetLimit);
    final TermsFacetMap collectionIdFacet = new TermsFacetMap(CommonText.getInstance()
                        .getCollectionId()).setLimit(1);
    final TermsFacetMap dnaFacet = new TermsFacetMap(CommonText.getInstance()
                        .getDna()).setLimit(1);
    final TermsFacetMap mapFacet = new TermsFacetMap(CommonText.getInstance()   
                        .getMap()).setLimit(1);
    final TermsFacetMap imageFacet = new TermsFacetMap(CommonText.getInstance().getImage()).setLimit(1);
    final TermsFacetMap inSwedenFacet = new TermsFacetMap(CommonText.getInstance().getInSweden()).setLimit(1);
    final TermsFacetMap typeFacet = new TermsFacetMap(CommonText.getInstance().getIsType()).setLimit(1);
    collectionNameFacet.withSubFacet(CommonText.getInstance().getCollectionId(), collectionIdFacet);

    final JsonQueryRequest request = new JsonQueryRequest()
            .setQuery(text)
            .returnFields(CommonText.getInstance().getCollectionName(), 
                    CommonText.getInstance().getCollectionId())
            .withFacet(CommonText.getInstance().getDna(), dnaFacet)
            .withFacet(CommonText.getInstance().getImage(), imageFacet)
            .withFacet(CommonText.getInstance().getMap(), mapFacet)
            .withFacet(CommonText.getInstance().getSweden(), inSwedenFacet)
            .withFacet(CommonText.getInstance().getType(), typeFacet)
            .withFacet(CommonText.getInstance().getCollectionName(), collectionNameFacet);

    if (filters != null && !filters.isEmpty()) {
      filters.entrySet().stream().forEach(e -> {
        request.withFilter(e.getKey() + e.getValue());
      });
    }

    try {
      response = request.process(client);
//      log.info("json: {}", response.jsonStr());
    } catch (SolrServerException | IOException ex) {
      log.error(ex.getMessage());
      return null;
    }
    NestableJsonFacet facet = response.getJsonFacetingResponse();

    int totalDna = SolrHelper.getInstance()
            .getBucketsTotal(facet.getBucketBasedFacets(CommonText.getInstance().getDna()));
    int totaImage = SolrHelper.getInstance()
            .getBucketsTotal(facet.getBucketBasedFacets(CommonText.getInstance().getImage()));
    int totalMap = SolrHelper.getInstance()
            .getBucketsTotal(facet.getBucketBasedFacets(CommonText.getInstance().getMap()));
    int totalInSweden = SolrHelper.getInstance()
            .getBucketsTotal(facet.getBucketBasedFacets(CommonText.getInstance().getSweden()));
    int totalType = SolrHelper.getInstance()
            .getBucketsTotal(facet.getBucketBasedFacets(CommonText.getInstance().getType()));

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
  }

}
