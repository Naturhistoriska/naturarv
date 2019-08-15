/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.lazy.datamodel;

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
  private final Map<String, String> filterMap;

  public ImageLazyDataModel(SolrService solr, Map<String, String> filterMap) { 
    this.solr = solr;
    this.filterMap = filterMap;
    this.setRowCount(solr.getImageTotalCount(filterMap));
  }

  @Override
  public List<ImageModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    log.info("filterMap: {}", filterMap);
    List<ImageModel> data = solr.getImageList(first, pageSize, filterMap);

    log.info("filters: {}", filterMap);

//    if (filters != null && filters.size() > 0) {
//      //otherwise it will still show all page links; pages at end will be empty
//      this.setRowCount(DataService.INSTANCE.getFilteredRowCount(filters));
//    }
    return data;
  }
}
