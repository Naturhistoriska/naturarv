package se.nrm.dina.web.portal.controller;
 
import java.io.Serializable; 
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
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
    private String catalogNumber;
    private String scientificName;
    private String mbid;
    private List<String> thumbs;
    private List<String> jpgs;
    
    private String photogarphy;
    private String url;
    private List<String> associatedMedias;
     
    
    private Map<String, String> map;
    
    private String imageId;
    
    private final String imageIdKey = "imageId"; 
    
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
     */
    public void imageSwitch() {
        log.info("imageSwitch");
         
        
        map =FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        
        log.info("map : {}", map); 
        
        imageId = map.get(imageIdKey);
        log.info("imageId : {}", imageId);
        
        data = solr.getImagesById(imageId);        
        catalogNumber = data.getCatalogNumber();
        scientificName = data.getTxFullName();
        jpgs = data.getJpgs();
    }

    /**
     *
     * @param data - SolrData
     */
    public void imageSwitch(SolrData data) {
        log.info("imageSwitch : {}", data);
        
        this.data = data;
        if(data.isCommonCollection()) {
            this.mbid = data.getMorphbankId(); 
//            this.thumbs = data.getThumbs();
//            this.jpgs = data.getJpgs();  
        }  
        this.thumbs = data.getThumbs(); 
        this.jpgs = data.getJpgs();  
        this.catalogNumber = data.getCatalogNumber();
        this.scientificName = data.getTxFullName();   
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
        return jpgs;
    }    

    public String getPhotogarphy(String thumb) {
        log.info("what is thumb : {} -- {}", thumb, data );
         
        if(data.getAssociatedMedia() != null) {
            associatedMedias = Arrays.asList(data.getAssociatedMedia());
            photogarphy = associatedMedias.stream()
                .filter(a -> thumb.contains(StringUtils.substringBetween(a,leftBlacket,rightBlacket)))
                .findFirst().get();
            if(photogarphy != null) {
                return StringUtils.substringBefore(photogarphy, leftBlacket).trim();
            }
        }
        return emptyString;  
    }
     
}
