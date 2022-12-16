package se.nrm.dina.web.portal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;
import org.primefaces.model.map.LatLng;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.DateHelper;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@Slf4j
public class SolrData {

    @Field
    public String[] additionalDet;
    @Field
    public String accessionNumber;
    @Field
    public String[] accessionRemarks;
    @Field
    public String[] author;
    @Field
    public String catalogNumber;
    @Field
    public Date catalogedDate;
    @Field
    public String[] collector;
    @Field
    public Date startDate;
    @Field
    public String collectionId;
    @Field
    public String collectionName;
    @Field
    public String continent;
    @Field
    public String country;
    @Field
    public String county;
    @Field
    public String state;
    @Field
    public String coordinate;
    @Field
    public String determiner;
    @Field
    public Date determinedDate;
    @Field
    public double latitude;
    @Field
    public double longitude;
    @Field
    public String latitudeText;
    @Field
    public String longitudeText;
    @Field
    public String locality;
    @Field
    public String higherTx;
    @Field
    public boolean map;
    @Field
    public String periodMax;
    @Field
    public String periodMin;
    @Field
    public String epochMax;
    @Field
    public String epochMin;
    @Field
    public String stageMax;
    @Field
    public String stageMin;
    @Field
    public String preservation;
    @Field
    public String lithostratigraphic;
    @Field
    public String site;
    @Field
    public String morphbankId;
    @Field
    public String[] morphbankImageId;
    @Field
    public String[] prepration;
    @Field
    public String[] prepCount;
    @Field
    public String[] remarks;
    @Field
    public String[] colremarks;
    @Field
    public String serie;
    @Field
    public String species;
    @Field
    public String genus;
    @Field
    public String stationFieldNumber;
    @Field
    public String[] synonym;
    @Field
    public String[] synonymAuthor;
    @Field
    public String typeStatus;
    @Field
    public String txFullName;
    @Field
    public String[] taxa;

    @Field
    public String specificEpithet;
    @Field
    public String environment;
    @Field
    public String maxDepthInMeters;
    @Field
    public String minDepthInMeters;
    @Field
    public String oceanOrSea;
    @Field
    public String habitat;
    @Field
    public String individualCount;
     

    boolean selected = false;
    boolean imageExist;
    boolean displayMap;
    boolean displayImage;
    boolean openRemark = false;

    private final String closeArrow = "hidearrow.gif";
    private final String openArrow = "downarrow.gif";
    private final String mineralCode = "557057";
    private final String collectionPb = "pb";
    private final String collectionPz = "pz"; 
    private final String collectionEVmain = "ev";
    private final String collectionEVType = "et";
    private final String slash = "/";
    private final String upArrowBtn = "hidearrow.git";
    private final String downArrowBtn = "downarrow.gif";
    private final String comma = ", ";
    private final String semicolon = "; ";
    private final String newLine = "\n";
    private final String emptySpace = " ";
    private final String emptyString = "";
    private final String coordinatesSeparate = " --- "; 
    
    private final String pbIconName = "pb.png";
    private final String pzEvIconName = "pz.png";
    private final String pzInEvIconName = "ev.png";
    
    private final String chordata = "Chordata";
     
    private List<String> thumbs;
    private List<String> jpgs;

    private StringBuilder exportRemarksSb;
    private StringBuilder minAgeSb;
    private StringBuilder maxAgeSb;
    private StringBuilder taxonSb;
    private StringBuilder coordinatesSb; 
    
    private final String degreeSign = "°";
    private final String north = "N";
    private final String east = "E";

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String[] getAccessionRemarks() {
        return accessionRemarks;
    }

    public void setAccessionRemarks(String[] accessionRemarks) {
        this.accessionRemarks = accessionRemarks;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public Date getCatalogedDate() {
        return catalogedDate;
    }

    public void setCatalogedDate(Date catalogedDate) {
        this.catalogedDate = catalogedDate;
    }

    public String[] getCollector() {
        return collector;
    }

    public void setCollector(String[] collector) {
        this.collector = collector;
    }

    public String getDeterminer() {
        return determiner;
    }

    public void setDeterminer(String determiner) {
        this.determiner = determiner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLatitudeText() {
        return latitudeText;
    }

    public void setLatitudeText(String latitudeText) {
        this.latitudeText = latitudeText;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLongitudeText() {
        return longitudeText;
    }

    public void setLongitudeText(String longitudeText) {
        this.longitudeText = longitudeText;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getHigherTx() {
        return higherTx;
    }

    public void setHigherTx(String higherTx) {
        this.higherTx = higherTx;
    }

    public boolean isMap() {
        return map;
    }

    public void setMap(boolean map) {
        this.map = map;
    }

    public String getMorphbankId() {
        return morphbankId;
    }

    public void setMorphbankId(String morphbankId) {
        this.morphbankId = morphbankId;
    }

    public String[] getMorphbankImageId() {
        return morphbankImageId;
    }

    public void setMorphbankImageId(String[] morphbankImageId) {
        this.morphbankImageId = morphbankImageId;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getPrepration() {
        return prepration;
    }

    public void setPrepration(String[] prepration) {
        this.prepration = prepration;
    }

    public String[] getColremarks() {
        return colremarks;
    }

    public void setColremarks(String[] colremarks) {
        this.colremarks = colremarks;
    }

    public String[] getRemarks() {
        return remarks;
    }

    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getStationFieldNumber() {
        return stationFieldNumber;
    }

    public void setStationFieldNumber(String stationFieldNumber) {
        this.stationFieldNumber = stationFieldNumber;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String[] getSynonym() {
        return synonym;
    }

    public void setSynonym(String[] synonym) {
        this.synonym = synonym;
    }

    public String getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
    }

    public String getTxFullName() {   
        if (isPbCollection() && !StringUtils.isBlank(genus)) {
            taxonSb = new StringBuilder();
            taxonSb.append(genus);
            taxonSb.append(emptySpace);  
            taxonSb.append(txFullName); 
            return taxonSb.toString().trim();
        }
        return txFullName;
    }

    public void setTxFullName(String txFullName) {
        this.txFullName = txFullName;
    }

    public String[] getTaxa() {
        return taxa;
    }

    public void setTaxa(String[] taxa) {
        this.taxa = taxa;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isImageExist() {
        return imageExist;
    }

    public void setImageExist(boolean imageExist) {
        this.imageExist = imageExist;
    }

    public boolean isDisplayMap() {
        return displayMap;
    }

    public void setDisplayMap(boolean displayMap) {
        this.displayMap = displayMap;
    }

    public boolean isDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(boolean displayImage) {
        this.displayImage = displayImage;
    }

    public String getAuthors() { 
        return author == null ? emptyString : StringUtils.join(author, comma);
    }

    public boolean isOpenRemark() {
        return openRemark;
    }

    public void setOpenRemark(boolean openRemark) {
        this.openRemark = openRemark;
    }

    public String getRemarkBtn() {
        return openRemark ? upArrowBtn : downArrowBtn;
    }

    public String getSynonymAuthors() {
        return StringUtils.join(synonymAuthor, comma);
    }

    public String getCollectors() {
        return StringUtils.join(collector, comma);
    }

    public String getTaxaList() {
        return StringUtils.join(taxa, semicolon);
    }

    public String getRemarksString() {
        if(remarks != null) {
            return String.join(comma, remarks);
        }
        return null;
    }
    
    public List<String> getAllRemarkes() {
        List<String> remarkList = new ArrayList();
        if (collectionId != null && collectionId.equals(mineralCode)) {
            if (remarks != null) {
                Arrays.asList(remarks).stream()
                        .forEach(r -> {
                            remarkList.addAll(Arrays.asList(r.split(newLine)));
                        });
            }
        } else {
            if (colremarks != null) {
                Arrays.asList(colremarks).stream()
                        .forEach(r -> {
                            remarkList.addAll(Arrays.asList(r.split(newLine)));
                        });
            } 
        }
        return remarkList;
    }

    public String getExportRemarks() {
        exportRemarksSb = new StringBuilder();
        if (collectionId != null && collectionId.equals(mineralCode)) {
            if (remarks != null) {
                Arrays.asList(remarks)
                        .stream()
                        .forEach(r -> {
                            Arrays.asList(r.split(newLine))
                                    .stream()
                                    .forEach(s -> {
                                        exportRemarksSb.append(s.trim());
                                        exportRemarksSb.append(emptySpace);
                                    });
                        });
            }
        } else {
            if (colremarks != null) {
                Arrays.asList(colremarks)
                        .stream()
                        .forEach(r -> {
                            Arrays.asList(r.split(newLine))
                                    .stream()
                                    .forEach(s -> {
                                        exportRemarksSb.append(s.trim());
                                        exportRemarksSb.append(emptySpace);
                                    });
                        });
            }
        }
        return exportRemarksSb.toString().trim();
    }

    public String getGeopointText() {
        StringBuilder sb = new StringBuilder();
        sb.append(latitudeText);
        sb.append(emptySpace);
        sb.append(longitudeText);
        return sb.toString();
    }

    public String getAllCoAttrRemarkes() {
        return StringUtils.join(colremarks, comma);
    }

    public String[] getPrepCount() {
        return prepCount;
    }

    public void setPrepCount(String[] prepCount) {
        this.prepCount = prepCount;
    }

    public String getAllPreparations() {
        return StringUtils.join(prepration, comma);
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String[] getAdditionalDet() {
        return additionalDet;
    }

    public void setAdditionalDet(String[] additionalDet) {
        this.additionalDet = additionalDet;
    }

    public void setImages(String morphbankImageUrl) {
        thumbs = new ArrayList<>();
        jpgs = new ArrayList<>();
        if (morphbankImageId != null) {
            Arrays.asList(morphbankImageId)
                    .stream()
                    .forEach(i -> {
                        thumbs.add(HelpClass.getInstance()
                                .buildImagePath(i, CommonText.getInstance()
                                        .getImageTypeThumb(), morphbankImageUrl));
                        jpgs.add(HelpClass.getInstance()
                                .buildImagePath(i, CommonText.getInstance()
                                        .getImageTypeJpg(), morphbankImageUrl));
                    });
        }
    }

    public boolean isOpenMap() {
        return map && !displayMap;
    }

    public boolean isSingleMapLink() {
        return map;
    }

    public List<String> getThumbs() {
        return thumbs;
    }

    public List<String> getJpgs() {
        return jpgs;
    }

    public boolean isMineral() {
        return collectionId != null ? collectionId.equals(mineralCode) : false;
    }

    public int getTotalPreparations() {
        return prepration != null ? prepration.length : 0;
    }

    public String getDetermination() {
        return determinedDate == null ? determiner
                : determiner + comma + DateHelper.getInstance().dateToString(determinedDate);
    }

    public String getPeriodMax() {
        return periodMax;
    }

    public void setPeriodMax(String periodMax) {
        this.periodMax = periodMax;
    }

    public String getPeriodMin() {
        return periodMin;
    }

    public void setPeriodMin(String periodMin) {
        this.periodMin = periodMin;
    }

    public String getEpochMax() {
        return epochMax;
    }

    public void setEpochMax(String epochMax) {
        this.epochMax = epochMax;
    }

    public String getEpochMin() {
        return epochMin;
    }

    public void setEpochMin(String epochMin) {
        this.epochMin = epochMin;
    }

    public String getStageMax() {
        return stageMax;
    }

    public void setStageMax(String stageMax) {
        this.stageMax = stageMax;
    }

    public String getStageMin() {
        return stageMin;
    }

    public void setStageMin(String stageMin) {
        this.stageMin = stageMin;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
    }

    public String getLithostratigraphic() {
        return lithostratigraphic;
    }

    public void setLithostratigraphic(String lithostratigraphic) {
        this.lithostratigraphic = lithostratigraphic;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSpecificEpithet() {
        return specificEpithet;
    }

    public void setSpecificEpithet(String specificEpithet) {
        this.specificEpithet = specificEpithet;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMaxDepthInMeters() {
        return maxDepthInMeters;
    }

    public void setMaxDepthInMeters(String maxDepthInMeters) {
        this.maxDepthInMeters = maxDepthInMeters;
    }

    public String getMinDepthInMeters() {
        return minDepthInMeters;
    }

    public void setMinDepthInMeters(String minDepthInMeters) {
        this.minDepthInMeters = minDepthInMeters;
    }

    public String getOceanOrSea() {
        return oceanOrSea;
    }

    public void setOceanOrSea(String oceanOrSea) {
        this.oceanOrSea = oceanOrSea;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getIndividualCount() {
        return individualCount;
    }

    public void setIndividualCount(String individualCount) {
        this.individualCount = individualCount;
    }

    public boolean isPbCollection() {
        return collectionId != null && (collectionId.equals(collectionPb) 
                || collectionId.equals(collectionPz));
    }

    public boolean isEvCollection() {
//        return collectionId.startsWith(collectionEv);
        if (collectionId != null) {
            return collectionId.equals(collectionEVmain)
                    || collectionId.equals(collectionEVType);
        }
        return false;
    }
    
    public boolean isCommonCollection() {
        return !isPbCollection() && !isEvCollection() && !isMineral();
    }

    public void openCloseRemarks() {
        openRemark = !openRemark;
    }

    public String getOpenCloseRemarkImg() {
        return openRemark ? openArrow : closeArrow;
    }

    public String getAllAdditionalDeterminations() {
        return StringUtils.join(additionalDet, semicolon);
    }

    public String getPrepCountList() {
        return StringUtils.join(prepCount, semicolon);
    }

    public String getMinAge() {
        minAgeSb = new StringBuilder();
        if (!StringUtils.isBlank(periodMin)) {
            minAgeSb.append(periodMin);
        }
        if (!StringUtils.isBlank(epochMin)) {
            minAgeSb.append(slash);
            minAgeSb.append(epochMin);
        }
        if (!StringUtils.isBlank(stageMin)) {
            minAgeSb.append(slash);
            minAgeSb.append(stageMin);
        }
        return minAgeSb.toString().trim();
    }

    public String getMaxAge() {
        maxAgeSb = new StringBuilder();
        if (!StringUtils.isBlank(periodMax)) {
            maxAgeSb.append(periodMax);
        }
        if (!StringUtils.isBlank(epochMax)) {
            maxAgeSb.append(slash);
            maxAgeSb.append(epochMax);
        }
        if (!StringUtils.isBlank(stageMax)) {
            maxAgeSb.append(slash);
            maxAgeSb.append(stageMax);
        }
        return maxAgeSb.toString().trim();
    }

    public String getCoordinateString() {
        coordinatesSb = new StringBuilder();
        if (latitudeText != null && latitudeText.length() > 0) {
            if (longitudeText != null && longitudeText.length() > 0) {
                coordinatesSb.append(latitude);
                coordinatesSb.append(degreeSign);
                coordinatesSb.append(north);
                coordinatesSb.append(coordinatesSeparate);
                coordinatesSb.append(longitude);
                coordinatesSb.append(degreeSign);
                coordinatesSb.append(east);
                return coordinatesSb.toString().trim();
            }  
        }  
        return null; 
    }
    
    
 
    public String getTaxon() {
        taxonSb = new StringBuilder();
        
        if (isPbCollection() && !StringUtils.isBlank(genus)) {
            taxonSb.append(genus);
            taxonSb.append(emptySpace);
        }
        taxonSb.append(txFullName);
        taxonSb.append(emptySpace);
        taxonSb.append(getAuthors());
        return taxonSb.toString().trim();
    } 
    
    public boolean isPaleoBotanyCollection() {
        return collectionId.equals(collectionPb);
    }
    
    public String getIconName() {
        switch (collectionId) {
            case collectionPb:
                return pbIconName;
            case collectionPz:
                return higherTx != null && higherTx.contains(chordata) 
                        ? pzEvIconName :   pzInEvIconName;
            default:
                return collectionId + ".png"; 
        }
    }
}
