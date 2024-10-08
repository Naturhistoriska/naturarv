package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.model.SolrData;
import se.nrm.dina.web.portal.solr.SolrImageService;

/**
 *
 * @author idali
 */
@Named("switch")
@SessionScoped
@Slf4j
public class ImageSwitcher implements Serializable {

    private final String leftBlacket = "[";
    private final String rightBlacket = "]";
    private final String emptyString = "";
    private final String imageCss = "largeImage";
    private final String largeImageKboCss = "largeKboImage";
    private final String largeImageFboCss = "largeFboImage";
    private final String largeImagePalCss = "largePalImage";
    private final String maxSize = "stor";
    private final String largeSize = "large";
    private final String mediumSize = "medium";

    private String catalogNumber;
    private String scientificName;
    private String mbid;
    private List<String> thumbs;
    private List<String> jpgs;
    private String collection;

    private String photogarphy;
    private List<String> associatedMedias;

    private String imageId;
//
//    private final String imageIdKey = "imageId";

    private SolrData data;

    @Inject
    private SolrImageService solr;

    public ImageSwitcher() {
    }

    public ImageSwitcher(SolrImageService solr) {
        this.solr = solr;
    }

    /**
     * imageSwitch
     *
     * @param imageId
     */
//    public void imageSwitch() {
//        log.info("imageSwitch");
//
//        map = FacesContext.getCurrentInstance()
//                .getExternalContext().getRequestParameterMap();
//
//        imageId = map.get(imageIdKey);
//        log.info("image id : {}", imageId);
//        data = solr.getImagesById(imageId);
//        catalogNumber = data.getCatalogNumber();
//        scientificName = data.getTxFullName();
//        jpgs = data.getJpgs();
//        this.collection = data.getCollectionId();
//    }
    public void imageSwitch(String imageId) {
        log.info("imageSwitch : {}", imageId);

        this.imageId = imageId;
        data = solr.getImagesById(imageId);
        catalogNumber = data.getCatalogNumber();
        scientificName = data.getTxFullName();
        this.jpgs = data.getJpgs();
        this.collection = data.getCollectionId();
    }

    /**
     *
     * @param data - SolrData
     */
    public void imageSwitch(SolrData data) {
//        log.info("imageSwitch : {}", data);

        this.data = data;
        if (data.isCommonCollection()) {
            this.mbid = data.getMorphbankId();
        }
        this.thumbs = data.getThumbs();
        this.jpgs = data.getJpgs();
        this.catalogNumber = data.getCatalogNumber();
        this.scientificName = data.getTxFullName();
        this.collection = data.getCollectionId();
    }

    public String getMbid() {
        return mbid;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getScientificName() {
        return scientificName;
    }

    public List<String> getThumbs() {
        return thumbs;
    }

    public List<String> getJpgs() {
//        log.info("jpgs : {}", jpgs);
        return jpgs;
    }

    public String getPhotogarphy(String thumb) {
//        log.info("what is thumb : {} -- {}", thumb, data);

        if (data != null) {
            if (data.isEvCollection() || data.isPbCollection()  || data.isFishCollection()) {
                return emptyString;
            } else { 
                if (data.getAssociatedMedia() != null && thumb != null) { 
                    associatedMedias = Arrays.asList(data.getAssociatedMedia());
                    photogarphy = associatedMedias.stream()
                            .filter(a -> thumb.contains(
                            StringUtils.substringBetween(a, leftBlacket, rightBlacket)))
                            .findFirst().get();
                    if (photogarphy != null) {
                        return leftBlacket + 
                                StringUtils.substringBefore(photogarphy, leftBlacket).trim()
                                + rightBlacket;
                    } 
                }
            }
        }
        return emptyString;
    }

    public String imageCss(String path) {
//        log.info("imagePath : {}", path);
        if (path.contains(maxSize)) {
            return largeImageKboCss;
        } else if (path.contains(largeSize)) {
            return largeImageFboCss;
        } else if (path.contains(mediumSize)) {
            return largeImagePalCss;
        }
        return imageCss;
    }

    public int getWidth() {
        log.info("getWidth : {}", collection);
        return 980; 
    }

    public int getHeight() {
        return 800; 
    }
}
