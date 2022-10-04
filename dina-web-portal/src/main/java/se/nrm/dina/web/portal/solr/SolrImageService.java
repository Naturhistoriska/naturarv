package se.nrm.dina.web.portal.solr;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.logic.solr.Solr;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrImageService implements Serializable {
  
  private SolrQuery query;
  private QueryResponse response;
  
  private final String slash = "/";
  
  @Inject
  private InitialProperties properties;

  @Inject
  @Solr
  private SolrClient client;
  
  public SolrImageService() {
  }
   
  public SolrImageService(SolrClient client, InitialProperties properties) {
    this.client = client;
    this.properties = properties;
  }
  
  public int getImageTotalCount(String searchQueryText, Map<String, String> filters ) {
    log.info("getImageTotalCount: {}", searchQueryText);
 
    query = new SolrQuery();   
    query.setQuery(searchQueryText);
    query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));

    SolrHelper.getInstance().addSearchFilters(query, filters);
    try {
      response = client.query(query);
    } catch (SolrServerException | IOException ex) { 
      log.error(ex.getMessage());
      return 0;
    } 
    return (int) response.getResults().getNumFound(); 
  }
   
  public List<ImageModel> getImageList(String searchQueryText, int start, int numPerPage, 
                                        Map<String, String> filters, List<String> filterList ) {
    log.info("getImageList: {} -- {}", start, filterList);
 
    String imageThumbPath = properties.getMorphbankThumbPath();  
    query = new SolrQuery(); 
    query.setQuery(searchQueryText)
            .addField(CommonText.getInstance().getMorphbankId())
            .addField(CommonText.getInstance().getImageView())
            .addField(CommonText.getInstance().getTaxonFullName())
            .addField(CommonText.getInstance().getCatalogNumber())
            .addField(CommonText.getInstance().getCollectionId())
            .addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true))
            .setStart(start)
            .setRows(numPerPage);
 
    
    SolrHelper.getInstance().addSearchFilters(query, filters);  
    List<ImageModel> images = new ArrayList();
    try {  
      SolrDocumentList documents = client.query(query).getResults(); 
      documents.stream()
              .forEach(d -> { 
                ((List<String>) d.getFieldValue(CommonText.getInstance().getImageView())).stream()
                        .forEach(v -> { 
                          String imageId = StringUtils.split(v, slash)[0];
                          String view = StringUtils.substringAfter(v, slash);
                          if (isMatchFilter(v, filterList)) {
                            images.add(new ImageModel((String) d.getFieldValue(CommonText.getInstance().getCatalogNumber()),
                                    (String) d.getFieldValue(CommonText.getInstance().getCollectionId()),
                                    (String) d.getFieldValue(CommonText.getInstance().getMorphbankId()),
                                    HelpClass.getInstance().buildImagePath(imageId, 
                                            CommonText.getInstance().getImageTypeThumb(), imageThumbPath),
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
    
   /**
    * Fetch records by morphbankId
    * @param morphbankId - String
    * @return SolrData
    */
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
 
  private boolean isMatchFilter(String imageView, List<String> filters) {
    if (filters == null || filters.isEmpty()) {
      return true;
    }
    return filters.stream().anyMatch(v -> imageView.contains(v));
  } 
}
