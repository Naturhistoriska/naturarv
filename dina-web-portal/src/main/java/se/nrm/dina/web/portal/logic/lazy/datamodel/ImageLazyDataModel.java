
package se.nrm.dina.web.portal.logic.lazy.datamodel;
  
import java.util.List;
import java.util.Map;   
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel; 
import org.primefaces.model.SortOrder; 
import se.nrm.dina.web.portal.model.ImageModel;
import se.nrm.dina.web.portal.solr.SolrImageService; 
import se.nrm.dina.web.portal.utils.SearchHelper; 

/**
 *
 * @author idali
 */
@Slf4j
public class ImageLazyDataModel extends LazyDataModel<ImageModel> {
  
  private final SolrImageService solr; 
  private final String searchText;
  private final Map<String, String> filterMap;
  private final List<String> filterList; 
  private String searchImageQueryText;
    
  public ImageLazyDataModel(SolrImageService solr, Map<String, String> filterMap, 
                            List<String> filterList, String searchText, int totalCount) { 
    log.info("ImageLazyDataModel: {} -- {}", filterMap, filterList + " -- " + searchText); 
    this.solr = solr;
    this.searchText = searchText;
    this.filterMap = filterMap;
    this.filterList = filterList;
    this.setRowCount(totalCount); 
    searchImageQueryText = SearchHelper.getInstance().buildImageOptionSearchText(searchText, filterList);
    log.info("searchImageQueryText : {}", searchImageQueryText);
  }

  @Override
  public List<ImageModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    log.info("filterMap: {} -- {}", filterList, searchText + " --- " + filterMap );
      
    searchImageQueryText = SearchHelper.getInstance().buildImageOptionSearchText(searchText, filterList);
    if(filterList != null && !filterList.isEmpty() || filterMap != null && !filterMap.isEmpty()) { 
      this.setRowCount(solr.getImageTotalCount(searchImageQueryText, filterMap)); 
    } 
    return solr.getImageList(searchImageQueryText, first, pageSize, filterMap, filterList); 
  } 
}
