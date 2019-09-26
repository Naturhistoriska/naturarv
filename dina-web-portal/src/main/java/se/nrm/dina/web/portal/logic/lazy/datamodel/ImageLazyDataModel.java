/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.lazy.datamodel;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;  
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel; 
import org.primefaces.model.SortOrder; 
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
@Slf4j
public class ImageLazyDataModel extends LazyDataModel<ImageModel> {
 
  private final SolrService solr;
  
  private final String searchText;
  private final Map<String, String> filterMap;
  private final List<String> filterList;
  private List<ImageModel> datasource;

  public ImageLazyDataModel(SolrService solr, Map<String, String> filterMap, 
                            List<String> filterList, String searchText, int totalCount) { 
    log.info("ImageLazyDataModel: {} -- {}", filterMap, filterList + " -- " + searchText);
    datasource = new ArrayList<>();
    this.solr = solr;
    this.searchText = searchText;
    this.filterMap = filterMap;
    this.filterList = filterList;
    this.setRowCount(totalCount);
  }

  
//  @Override
//  public ImageModel getRowData(String rowKey) {
//        for(ImageModel model : datasource) {
//            if(model.getImageId().equals(rowKey))
//                return model;
//        }
// 
//        return null;
//    }
// 
//    @Override
//    public Object getRowKey(ImageModel model) {
//        return model.getImageId();
//    }
 
  
  @Override
  public List<ImageModel> load(int first, int pageSize, String sortField, 
          SortOrder sortOrder, Map<String, Object> filters) {
    log.info("filterMap: {} -- {}", filterList, searchText + " --- " + filterMap );
    
    List<ImageModel> data = solr.getImageList(searchText, first, pageSize, filterMap, filterList);
    datasource = data;
    if(filterList != null && !filterList.isEmpty() || filterMap != null && !filterMap.isEmpty()) {
      this.setRowCount(solr.getImageTotalCount(searchText, filterMap, filterList));
    } 
    return datasource;
  }
}
