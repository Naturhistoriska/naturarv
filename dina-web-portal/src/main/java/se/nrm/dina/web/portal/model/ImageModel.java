package se.nrm.dina.web.portal.model;

/**
 *
 * @author idali
 */
public class ImageModel {

    public String id;
    public String catalogNumber;
    public String collectionId;
    public String morphbankId;
    public String imageId;
    public String txFullName;
    public String morphBankView;
    public String associatedMedia;
    public String photographer;

    public ImageModel(String id, String catalogNumber, String collectionId, 
            String imageId, String txFullName, String photographer) {
        this.id = id;
        this.catalogNumber = catalogNumber;
        this.collectionId = collectionId;
        this.imageId = imageId;
        this.txFullName = txFullName;
        this.photographer = photographer;

    }

    public ImageModel(String id, String catalogNumber, String collectionId, 
            String morphbankId, String imageId, String txFullName, 
            String morphBankView) {
        this.id = id;
        this.catalogNumber = catalogNumber;
        this.collectionId = collectionId;
        this.morphbankId = morphbankId;
        this.imageId = imageId;
        this.txFullName = txFullName;
        this.morphBankView = morphBankView;
    }

    public String getId() {
        return id;
    }
     
    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getMorphbankId() {
        return morphbankId;
    }

    public String getImageId() {
        return imageId;
    }

    public String getTxFullName() {
        return txFullName;
    }

    public String getMorphBankView() {
        return morphBankView;
    }

    public String getAssociatedMedia() {
        return associatedMedia;
    }

    public String getPhotographer() {
        return photographer;
    }

}
