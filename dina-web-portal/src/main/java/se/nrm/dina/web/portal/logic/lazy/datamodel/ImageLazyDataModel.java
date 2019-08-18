/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.lazy.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map; 
import static java.util.stream.Collectors.toList;
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
  
  private final StringBuilder searchText;
  private final Map<String, String> filterMap;
  private final List<String> filterList;

  public ImageLazyDataModel(SolrService solr, Map<String, String> filterMap, 
                            List<String> filterList, StringBuilder searchText) { 
    log.info("ImageLazyDataModel: {} -- {}", filterMap, filterList + " -- " + searchText);
    this.solr = solr;
    this.searchText = searchText;
    this.filterMap = filterMap;
    this.filterList = filterList;
    this.setRowCount(solr.getImageTotalCount(searchText.toString(), filterMap));
  }

  @Override
  public List<ImageModel> load(int first, int pageSize, String sortField, 
          SortOrder sortOrder, Map<String, Object> filters) {
    log.info("filterMap: {} -- {}", filterList, searchText.toString());
    
    List<ImageModel> data = solr.getImageList(searchText.toString(), first, pageSize, filterMap, filterList);

    if(filterList != null && !filterList.isEmpty()) {
      this.setRowCount(solr.getImageTotalCount(searchText.toString(), filterMap));
    } 
    return data;
  }
}
