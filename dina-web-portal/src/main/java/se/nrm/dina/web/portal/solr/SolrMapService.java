package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Inject;
import javax.json.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException; 
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.request.json.TermsFacetMap;
import org.apache.solr.client.solrj.response.QueryResponse;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.json.JsonConverter;
import se.nrm.dina.web.portal.logic.solr.Solr;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrMapService implements Serializable {

    private final String geohash = "geohash";
    private final String geoKey = "geo:";
    private final String geo = "geo";
    private final String coordinate = "coordinate";
    private final String localityKey = "locality";

    @Inject
    @Solr
    private SolrClient client;

    @Inject
    private InitialProperties properties;

    private QueryResponse response; 

    public SolrMapService() {

    }

    public SolrMapService(SolrClient client) {
        this.client = client;
    }

    public JsonArray searchGeoHash(String text, String regionQueryText,
            Map<String, String> filters, String prefix) {
        log.info("searchGeoHash : {}", text);

        final TermsFacetMap coordinateFacet = new TermsFacetMap(coordinate).setLimit(8000);
        final TermsFacetMap localityFacet = new TermsFacetMap(localityKey).setLimit(100);

        final TermsFacetMap geoHashFacet = new TermsFacetMap(geohash)
                .setLimit(500)
                .setTermPrefix(prefix);
        coordinateFacet.withSubFacet(localityKey, localityFacet);

        geoHashFacet.withSubFacet(coordinate, coordinateFacet);
        final JsonQueryRequest request = new JsonQueryRequest()
                .setQuery(text)
                .returnFields(geo)
                .withFilter(geoKey + regionQueryText)
                .withFacet(geohash, geoHashFacet);

        SolrHelper.getInstance().addSearchFilters(request, filters);
        try { 
            request.setBasicAuthCredentials(properties.getUsername(), properties.getPassword());
            response = request.process(client); 
//      log.info("json: {}", response.jsonStr());
        } catch (SolrServerException | IOException ex) {
            log.warn(ex.getMessage());
            return null;
        }
        return JsonConverter.getInstance().buildResponseJson(response.jsonStr());
    }
}
