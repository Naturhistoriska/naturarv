package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.model.CollectionData;
import se.nrm.dina.web.portal.model.CollectionGroupData;
import se.nrm.dina.web.portal.model.StatisticData;
import se.nrm.dina.web.portal.solr.SolrStatisticService;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("statistic")
@Slf4j
public class StatisticBean implements Serializable {
    
    private final String smtpListCollectionId = "655361";
    private final String smtpObjCollectionId = "262144";
    private final String entomologyCollectionId = "163840";
    private final String evCollectionId = "ev";
    private final String etCollectionId = "et";
    private final String mammalsCollections = "ma";
    private final String birdCollections = "va";
    private final String fishCollectionId = "fish";
    private final String herpsCollectionId = "herps";            
    
    private final String fungiCollectionId = "fungi";    
    private final String mossesCollectionId = "mosses";    
    private final String vpCollectionId = "vp";    
    private final String algaeCollectionId = "algae";    
    
    private final String mineralCollection = "557057";
    private final String nodulesCollection = "786432";
    private final String isotopeCollection = "753664";
    
    private final String pzCollection = "pz";
    private final String pbCollection = "pb"; 
    
    private final String zooKey = "zoo";
    private final String botanyKey = "botany";
    private final String geoKey = "geo";
    private final String paleoKey = "paleo";
       
    private int zooTotal;
    private int botanyTotal;
    private int geoTotal;
    private int paleoTotal;
  
        
    private StatisticData data;
    private StatisticData filteredData;
 
    
    @Inject
    private SolrStatisticService solr;

    public StatisticBean() {
    }

    public StatisticBean(SolrStatisticService solr) {
        this.solr = solr;
    }

    @PostConstruct
    public void init() {
        log.info("StatisticData.init");
        data = solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null);
    }

    public void resetData(String text, Map<String, String> queries) {
        filteredData = solr.getStatisticData(text, queries);
    }

    public void resetAllData() {
        if (data == null) {
            data = solr.getStatisticData(CommonText.getInstance().getWildSearchText(), null);
        }
        filteredData = data;
    }

    public List<CollectionData> getFilteredCollections() {
        return filteredData == null ? getCollections() : filteredData.getCollections();
    }

    public List<CollectionData> getCollections() {
        if (data == null) {
            resetAllData();
        }
        return data.getCollections();
    }

    public int getTotalRecords() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotal();
    }

    public int getFilteredTotalDnas() {
        return filteredData == null ? getTotalDnas() : filteredData.getTotalDnas();
    }

    public int getTotalDnas() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotalDnas();
    }

    public int getFilteredTotalImages() {
        return filteredData == null ? getTotalImages() : filteredData.getTotalImages();
    }

    public int getTotalImages() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotalImages();
    }

    public int getFilteredTotalMaps() {
        return filteredData == null ? getTotalMaps() : filteredData.getTotalMaps();
    }

    public int getTotalMaps() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotalMaps();
    }

    public int getFilteredTotalInSweden() {
        return filteredData == null ? getTotalInSweden() : filteredData.getTotalInSweden();
    }

    public int getTotalInSweden() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotalInSweden();
    }

    public int getFilteredTotalType() {
        return filteredData == null ? getTotalType() : filteredData.getTotalType();
    }

    public int getTotalType() {
        if (data == null) {
            resetAllData();
        }
        return data.getTotalType();
    }
    
    public List<CollectionGroupData> getCollecctionGrouup() {
        Map<String, Integer> collectionGroup = new HashMap();

        getCollections().stream()
                .forEach(c -> {
                    String collectionId = c.getCode();
                    int total = c.getTotal();
                    if (isZooCollection(collectionId)) {
                        log.info("collectionId : {} -- {}", collectionId, total);
                        if(collectionGroup.containsKey(zooKey)) {
                            zooTotal = collectionGroup.get(zooKey) + total;
                        } else {
                            zooTotal = total;
                        } 
                        collectionGroup.put(zooKey, zooTotal); 
                    } else if(isBotanyCollection(collectionId)) {
                        if(collectionGroup.containsKey(botanyKey)) {
                            botanyTotal = collectionGroup.get(botanyKey) + total;
                        } else {
                            botanyTotal = total;
                        } 
                        collectionGroup.put(botanyKey, botanyTotal); 
                    } else if(isGeoCollection(collectionId)) {
                        if(collectionGroup.containsKey(geoKey)) {
                            geoTotal = collectionGroup.get(geoKey) + total;
                        } else {
                            geoTotal = total;
                        } 
                        collectionGroup.put(geoKey, geoTotal); 
                    } else if(isPaleoCollection(collectionId)) {
                        if(collectionGroup.containsKey(paleoKey)) {
                            paleoTotal = collectionGroup.get(paleoKey) + total;
                        } else {
                            paleoTotal = total;
                        } 
                        collectionGroup.put(paleoKey, paleoTotal); 
                    }
                }); 
        
        log.info("map : {}", collectionGroup);
        List<CollectionGroupData> group = new ArrayList();
        if(collectionGroup.containsKey(zooKey)) {
            group.add(new CollectionGroupData(zooKey,
                    CommonText.getInstance().getGroupNameEn(zooKey), 
                    CommonText.getInstance().getGroupNameSv(zooKey), 
                    zooTotal)); 
        } 
        if(collectionGroup.containsKey(botanyKey)) {
            group.add(new CollectionGroupData(botanyKey,
                    CommonText.getInstance().getGroupNameEn(botanyKey), 
                    CommonText.getInstance().getGroupNameSv(botanyKey), 
                    botanyTotal)); 
        } 
        if(collectionGroup.containsKey(paleoKey)) {
            group.add(new CollectionGroupData(paleoKey,
                    CommonText.getInstance().getGroupNameEn(paleoKey), 
                    CommonText.getInstance().getGroupNameSv(paleoKey), 
                    paleoTotal)); 
        } 
        if(collectionGroup.containsKey(geoKey)) {
            group.add(new CollectionGroupData(geoKey,
                    CommonText.getInstance().getGroupNameEn(geoKey), 
                    CommonText.getInstance().getGroupNameSv(geoKey), 
                    geoTotal)); 
        }
        log.info("group : {}", group);
        return group;
//        return collectionGroup;
        
    }
    
    private boolean isPaleoCollection(String collectionCode) {
        switch (collectionCode) {
            case pzCollection:
                return true;
            case pbCollection:
                return true; 
            default:
                break;
        }
        return false;
    }
    
    private boolean isGeoCollection(String collectionCode) {
        switch (collectionCode) {
            case mineralCollection:
                return true;
            case nodulesCollection:
                return true;
            case isotopeCollection:
                return true; 
            default:
                break;
        }
        return false;
    }
    
    private boolean isBotanyCollection(String collectionCode) {
        switch (collectionCode) {
            case vpCollectionId:
                return true;
            case mossesCollectionId:
                return true;
            case fungiCollectionId:
                return true;
            case algaeCollectionId:
                return true; 
            default:
                break;
        }
        return false;
    }
    
    private boolean isZooCollection(String collectionCode) {
        switch (collectionCode) {
            case smtpListCollectionId:
                return true;
            case smtpObjCollectionId:
                return true;
            case entomologyCollectionId:
                return true;
            case evCollectionId:
                return true;
            case etCollectionId:
                return true;
            case mammalsCollections:
                return true;
            case birdCollections:
                return true;
            case fishCollectionId:
                return true;
            case herpsCollectionId:
                return true; 
            default:
                break;
        }
        return false;
    }
}
