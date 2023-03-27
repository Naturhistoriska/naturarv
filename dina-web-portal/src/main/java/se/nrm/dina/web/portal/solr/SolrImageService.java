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
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
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
    
    private final String collectionVaculaPlants = "vp";
    private final String kboDataset = "kbo";
    private final String fboDataset = "fbo";
     
    private final String mini = "mini"; 
    private final String thumb = "tumme2";
    
    private final String idKey = "id";
    
    private String dataset;
    
    private final String slash = "/";
    private final String leftBlacket = "[";
    private final String rightBlacket = "]";
    
    private List<ImageModel> images;
    private List<String> morphViewList;
    
    private List<SolrDocument> morphDocuments;
    private List<SolrDocument> botDocuments;
    private List<String> associatedMediaList;
    
    private String id;
    private String view;
    private String imageId;
    private String morphbankId;
    private String morphImagePath;
    private String botImagePath; 
    private String photographer;
    private String catalogNumber;
    private String collectionId;
    private String taxonFullName;
    private ImageModel imageModel;
    
    private List<SolrData> imageDatas;
    private SolrData imageData;
    
    private String filterThumb;
    
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
    
    public int getImageTotalCount(String searchQueryText, Map<String, String> filters) {
        log.info("getImageTotalCount: {}", searchQueryText);
        
        query = new SolrQuery();        
        query.setQuery(searchQueryText);
        query.addFilterQuery(CommonText.getInstance().getImageSearchQuery());
//        query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));
        
        SolrHelper.getInstance().addSearchFilters(query, filters);
        try {
            response = client.query(query);
//            log.info("json: {}", response.jsonStr());
        } catch (SolrServerException | IOException ex) {            
            log.error(ex.getMessage());
            return 0;
        }        
        return (int) response.getResults().getNumFound();        
    }
    
    public List<ImageModel> getImageList(String searchQueryText, int start, int numPerPage,
            Map<String, String> filters, List<String> filterList) {
        log.info("getImageList: {} -- {}", start, filterList);
        
        String imageThumbPath = properties.getMorphbankThumbPath();        
        query = new SolrQuery();        
        query.setQuery(searchQueryText)
                .addField(CommonText.getInstance().getId())
                .addField(CommonText.getInstance().getMorphbankId())
                .addField(CommonText.getInstance().getAssociatedMediaKey())
                .addField(CommonText.getInstance().getImageView())
                .addField(CommonText.getInstance().getTaxonFullName())
                .addField(CommonText.getInstance().getCatalogNumber())
                .addField(CommonText.getInstance().getCollectionId())
                .addFilterQuery(CommonText.getInstance().getImageSearchQuery())
//                .addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true))
                .setStart(start)
                .setRows(numPerPage);
        
        SolrHelper.getInstance().addSearchFilters(query, filters);        
        images = new ArrayList();
        try {            
            SolrDocumentList documents = client.query(query).getResults();  
            
            
            morphDocuments = documents.stream()
                    .filter(d -> d.getFieldValue(CommonText.getInstance().getImageView()) != null)
                    .collect(Collectors.toList());
                    
                    
            botDocuments = documents.stream()
                    .filter(d -> d.getFieldValue(CommonText.getInstance().getAssociatedMediaKey()) != null)
                    .collect(Collectors.toList());   
            
            log.info("total images : {} -- {}", morphDocuments.size(), botDocuments.size());
            
            
            if(botDocuments != null && !botDocuments.isEmpty()) { 
                botDocuments.stream()
                        .forEach(d -> {
                            id = (String) d.getFieldValue(idKey); 
                            associatedMediaList = (List<String>) d.getFieldValue(CommonText.getInstance().getAssociatedMediaKey());
                            catalogNumber = (String) d.getFieldValue(CommonText.getInstance().getCatalogNumber());
                            collectionId = (String) d.getFieldValue(CommonText.getInstance().getCollectionId());
                            taxonFullName = (String) d.getFieldValue(CommonText.getInstance().getTaxonFullName());
                  
                            if(collectionId.equals(collectionVaculaPlants)) {
                                dataset = fboDataset;
                                filterThumb = mini;
                            } else {
                                dataset = kboDataset;
                                filterThumb = thumb;
                            }
                            
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(filterThumb))
                                    .forEach(a -> {  
                                        photographer = StringUtils.substringBefore(a, leftBlacket).trim();
                                        botImagePath = HelpClass.getInstance()
                                                .buildBotImagePath(StringUtils.substringBetween(a, leftBlacket, rightBlacket), 
                                                        dataset, imageThumbPath); 
                                        imageModel = new ImageModel(id, catalogNumber, collectionId,botImagePath,
                                                taxonFullName, photographer);
                                        images.add(imageModel);
                                    }); 
                        });
            }
            
            if(morphDocuments != null && !morphDocuments.isEmpty()) {
                morphDocuments.stream()
                        .forEach(d -> { 
                            id = (String) d.getFieldValue(idKey); 
                            catalogNumber = (String) d.getFieldValue(CommonText.getInstance().getCatalogNumber());
                            collectionId = (String) d.getFieldValue(CommonText.getInstance().getCollectionId());
                            morphViewList = (List<String>) d.getFieldValue(CommonText.getInstance().getImageView());
                            taxonFullName = (String) d.getFieldValue(CommonText.getInstance().getTaxonFullName()); 
                            morphbankId = (String) d.getFieldValue(CommonText.getInstance().getMorphbankId());
                            morphViewList.stream()
                                    .forEach(v -> {
                                        imageId = StringUtils.split(v, slash)[0];
                                        view = StringUtils.substringAfter(v, slash);
                                        morphImagePath = HelpClass.getInstance().buildImagePath(imageId,
                                                        CommonText.getInstance().getImageTypeThumb(), 
                                                        imageThumbPath); 
                                        
                                        imageModel = new ImageModel(id, catalogNumber, collectionId, morphbankId, 
                                                morphImagePath, taxonFullName, view);
                                        images.add(imageModel);
                                    }); 
                        });
            }
            
                    
//            documents.stream()
//                    .forEach(d -> {                        
//                        ((List<String>) d.getFieldValue(CommonText.getInstance().getImageView())).stream()
//                                .forEach(v -> {                                    
//                                    String imageId = StringUtils.split(v, slash)[0];
//                                    String view = StringUtils.substringAfter(v, slash);
//                                    if (isMatchFilter(v, filterList)) {
//                                        images.add(new ImageModel((String) d.getFieldValue(CommonText.getInstance().getCatalogNumber()),
//                                                (String) d.getFieldValue(CommonText.getInstance().getCollectionId()),
//                                                (String) d.getFieldValue(CommonText.getInstance().getMorphbankId()),
//                                                HelpClass.getInstance().buildImagePath(imageId,
//                                                        CommonText.getInstance().getImageTypeThumb(), imageThumbPath),
//                                                (String) d.getFieldValue(CommonText.getInstance().getTaxonFullName()), view));
//                                    }
//                                });
//                    });
            return images;
        } catch (IOException | SolrServerException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Fetch records by morphbankId
     *
     * @param morphbankId - String
     * @param isMorph
     * @return SolrData
     */
    public SolrData getImagesByMorphbankId(String morphbankId, boolean isMorph) {
        log.info("getImagesByMorphbankId : {}", morphbankId);
        String morphbankImagePath = properties.getMorphbankThumbPath();
        query = new SolrQuery();
        
        if(isMorph) {
            query.setQuery(CommonText.getInstance().getMorphbankIdKey() + morphbankId);
        } else {
            query.setQuery(CommonText.getInstance().getCnKey() + morphbankId);
        }
        
        try {
            response = client.query(query);
            imageDatas = response.getBeans(SolrData.class);
            if (imageDatas != null && !imageDatas.isEmpty()) {
                imageData = imageDatas.get(0);
                imageData.setImages(morphbankImagePath);
                return imageData;
            }
            return null;
        } catch (IOException | SolrServerException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }
    
    public SolrData getImagesById(String id) {
        log.info("getImagesById : {}", id);
        String morphbankImagePath = properties.getMorphbankThumbPath();
        query = new SolrQuery();
        
        query.setQuery(CommonText.getInstance().getIdKey() + id); 
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
    
//    private boolean isMatchFilter(String imageView, List<String> filters) {
//        if (filters == null || filters.isEmpty()) {
//            return true;
//        }
//        return filters.stream().anyMatch(v -> imageView.contains(v));
//    }    
}
