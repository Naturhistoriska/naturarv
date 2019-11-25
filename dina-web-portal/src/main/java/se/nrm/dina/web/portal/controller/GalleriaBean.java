package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.ArrayList; 
import java.util.List;
import java.util.Map; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel; 
import se.nrm.dina.web.portal.logic.lazy.datamodel.ImageLazyDataModel;
import se.nrm.dina.web.portal.logic.utils.GalleriaHelper;
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrImageService; 

/**
 *
 * @author idali
 */
@Named("galleria")
@SessionScoped
@Slf4j
public class GalleriaBean implements Serializable {

  private final int PARTS_COUNT = 17;
  private final int VIEW_COUNT = 6; 
     
  private final List<String> filters; 

  private boolean isAllViews = false;
  private boolean isAllParts = false;
  private boolean isAllSexes = false;
  private boolean isAllStages = false;

  private List<String> viewList;
  private List<String> partsList;
  private List<String> sexList;
  private List<String> stageList;
   
  @Inject
  private SolrImageService solr;
 
  ImageLazyDataModel dataModel;

  public GalleriaBean() {
    viewList = new ArrayList<>();
    partsList = new ArrayList<>();
    sexList = new ArrayList<>();
    stageList = new ArrayList<>(); 
    filters = new ArrayList<>(); 
  }
 
  public void setImageView(int totalImages, String searchText, Map<String, String> filterMap) {  
    dataModel = new ImageLazyDataModel(solr, filterMap, filters, searchText, totalImages); 
  }

  public LazyDataModel<ImageModel> getModel() {
    return dataModel;
  }

  public void selectViews() {
    log.info("selectViews: {}", viewList);

    if (isAllViews) {
      isAllViews = false;
      GalleriaHelper.getInstance().setSelectedList(viewList);
    } else { 
      if (GalleriaHelper.getInstance().hasAll(viewList, VIEW_COUNT)) {
        isAllViews = true;
        GalleriaHelper.getInstance().setAllViewList(viewList);
      }
    }  
    GalleriaHelper.getInstance().viewOptionChanged(filters, viewList, partsList, sexList, stageList);
  }

  public void selectParts() {
    log.info("selectParts: {}", partsList);
 
    if (isAllParts) {
      isAllParts = false;
      GalleriaHelper.getInstance().setSelectedList(partsList);
    } else { 
      if (GalleriaHelper.getInstance().hasAll(partsList, PARTS_COUNT)) {
        isAllParts = true;
        GalleriaHelper.getInstance().setAllPartsList(partsList);
      }
    } 
    GalleriaHelper.getInstance().viewOptionChanged(filters, viewList, partsList, sexList, stageList);
  }

  public void selectSexes() {
    log.info("selectSexes;: {}", sexList);
  
    if (isAllSexes) {
      isAllSexes = false;
      GalleriaHelper.getInstance().setSelectedList(sexList);
    } else {
      if (GalleriaHelper.getInstance().hasAll(sexList, 2)) {
        isAllSexes = true;
        GalleriaHelper.getInstance().setAllSexList(sexList);
      }
    } 
    GalleriaHelper.getInstance().viewOptionChanged(filters, viewList, partsList, sexList, stageList);
  }

  public void selectStages() {
    log.info("selectStages: {}", stageList);  
    if (isAllStages) {
      isAllStages = false;
      GalleriaHelper.getInstance().setSelectedList(stageList);
    } else {
      if (GalleriaHelper.getInstance().hasAll(stageList, 2)) {
        isAllStages = true; 
        GalleriaHelper.getInstance().setAllStagesList(stageList);
      }
    } 
    GalleriaHelper.getInstance().viewOptionChanged(filters, viewList, partsList, sexList, stageList);
  }

  public List<String> getViewList() {
    return viewList;
  }

  public void setViewList(List<String> viewList) {
    this.viewList = viewList;
  }

  public List<String> getPartsList() {
    return partsList;
  }

  public void setPartsList(List<String> partsList) {
    this.partsList = partsList;
  }

  public List<String> getSexList() {
    return sexList;
  }

  public void setSexList(List<String> sexList) {
    this.sexList = sexList;
  }

  public List<String> getStageList() {
    return stageList;
  }

  public void setStageList(List<String> stageList) {
    this.stageList = stageList;
  } 
}
