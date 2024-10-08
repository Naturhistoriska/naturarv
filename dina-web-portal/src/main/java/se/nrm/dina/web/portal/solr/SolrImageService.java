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
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
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
    private final String palDataset = "pal";
    private final String collectionPz = "pz";
    private final String collectionEt = "et";
    private final String collectionEv = "ev";
    private final String collectionFish = "fish";
    private final String collectionHerps = "herps";

    private final String mini = "mini";
    private final String thumb2 = "tumme2";
    private final String thumb = "thumb";

    private final String idKey = "id";

    private String dataset;

    private final String slash = "/";
    private final String leftBlacket = "[";
    private final String rightBlacket = "]";
    private final String emptyString = "";

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
    private String username;
    private String password;
    
    private String imageThumbPath;
    private String imagePath;

    private QueryRequest request;

    private List<SolrData> imageDatas;
    private SolrData imageData;

    private String filterThumb;

    private SolrDocumentList documents;

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

    @PostConstruct
    public void init() {
        log.info("init from search...");
        username = properties.getUsername();
        password = properties.getPassword();
        imageThumbPath = properties.getMorphbankThumbPath();
    }

    public int getImageTotalCount(String searchQueryText, Map<String, String> filters) {
        log.info("getImageTotalCount: {}", searchQueryText);

        query = new SolrQuery();
        query.setQuery(searchQueryText);
        query.addFilterQuery(CommonText.getInstance().getImageSearchQuery());
//        query.addFilterQuery(CommonText.getInstance().getImageKey() + String.valueOf(true));

        SolrHelper.getInstance().addSearchFilters(query, filters);
        try {
            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);
//            response = client.query(query);
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
            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);

//            final SolrDocumentList documentList = response.getResults();
//            SolrDocumentList documents = client.query(query).getResults();  
            documents = response.getResults();

            morphDocuments = documents.stream()
                    .filter(d -> d.getFieldValue(CommonText.getInstance().getImageView()) != null)
                    .collect(Collectors.toList());

            botDocuments = documents.stream()
                    .filter(d -> d.getFieldValue(CommonText.getInstance().getAssociatedMediaKey()) != null)
                    .collect(Collectors.toList());

            log.info("total images : {} -- {}", morphDocuments.size(), botDocuments.size());

            if (botDocuments != null && !botDocuments.isEmpty()) {
                botDocuments.stream()
                        .forEach(d -> {
                            id = (String) d.getFieldValue(idKey);
                            associatedMediaList = (List<String>) d.getFieldValue(
                                    CommonText.getInstance().getAssociatedMediaKey());
                            catalogNumber = (String) d.getFieldValue(CommonText.getInstance().getCatalogNumber());
                            collectionId = (String) d.getFieldValue(CommonText.getInstance().getCollectionId());
                            taxonFullName = (String) d.getFieldValue(CommonText.getInstance().getTaxonFullName());

                    switch (collectionId) {
                        case collectionPz:
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(thumb))
                                    .forEach(a -> {
                                        botImagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(a, palDataset, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, collectionId, botImagePath,
                                                taxonFullName, emptyString);
                                        images.add(imageModel);
                                    });
                            break;
                        case collectionEt: 
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(thumb))
                                    .forEach(a -> {
                                        imagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(a, collectionEt, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, collectionId, imagePath,
                                                taxonFullName, emptyString);
                                        images.add(imageModel);
                                    });
                            break;
                        case collectionEv: 
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(thumb))
                                    .forEach(a -> {
                                        imagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(a, collectionEv, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, collectionId, imagePath,
                                                taxonFullName, emptyString);
                                        images.add(imageModel);
                                    });
                            break;
                        case collectionFish:
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(thumb))
                                    .forEach(a -> {
                                        imagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(a, 
                                                        collectionFish, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, 
                                                collectionId,  imagePath,
                                                taxonFullName, emptyString);
                                        images.add(imageModel);
                                    });
                            break;
                        case collectionHerps:
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(thumb))
                                    .forEach(a -> {
                                        imagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(a, 
                                                        collectionFish, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, 
                                                collectionId,  imagePath,
                                                taxonFullName, emptyString);
                                        images.add(imageModel);
                                    });
                            break;
                        default:
                            if (collectionId.equals(collectionVaculaPlants)) {
                                dataset = fboDataset;
                                filterThumb = mini;
                            } else {
                                dataset = kboDataset;
                                filterThumb = thumb2;
                            }
                            associatedMediaList.stream()
                                    .filter(a -> a.contains(filterThumb))
                                    .forEach(a -> {
                                        photographer = StringUtils.substringBefore(a, leftBlacket).trim();
                                        botImagePath = HelpClass.getInstance()
                                                .buildImagePathWithDataset(StringUtils.substringBetween(a, leftBlacket, rightBlacket),
                                                        dataset, imageThumbPath);
                                        imageModel = new ImageModel(id, catalogNumber, collectionId, botImagePath,
                                                taxonFullName, photographer);
                                        images.add(imageModel);
                                    });
                            break;
                    }

                        });
            }

            if (morphDocuments != null && !morphDocuments.isEmpty()) {
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
 
            log.info("images : {}", images.size());
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
//        String morphbankImagePath = properties.getMorphbankThumbPath();
        query = new SolrQuery();

        if (isMorph) {
            query.setQuery(CommonText.getInstance().getMorphbankIdKey() + morphbankId);
        } else {
            query.setQuery(CommonText.getInstance().getCnKey() + morphbankId);
        }

        try {
//            response = client.query(query);

            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);
            imageDatas = response.getBeans(SolrData.class);
            if (imageDatas != null && !imageDatas.isEmpty()) {
                imageData = imageDatas.get(0);
                imageData.setImages(imageThumbPath);
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
        query = new SolrQuery();

        query.setQuery(CommonText.getInstance().getIdKey() + id);
        try {
//            response = client.query(query);

            request = new QueryRequest(query);
            request.setBasicAuthCredentials(username, password);
            response = request.process(client);
            List<SolrData> data = response.getBeans(SolrData.class);
            if (data != null && !data.isEmpty()) {
                SolrData solrData = data.get(0);
                solrData.setImages();
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
