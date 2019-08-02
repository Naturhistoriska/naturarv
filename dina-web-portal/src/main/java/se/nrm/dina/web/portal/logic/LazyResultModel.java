/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map; 
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import se.nrm.dina.web.portal.model.Result;
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
@Slf4j
public class LazyResultModel extends LazyDataModel<Result> {

  private List<Result> datasource;

  private SolrService solr;

  public LazyResultModel(SolrService solr, List<Result> datasource) {
    this.solr = solr;
    this.datasource = datasource;
  }

  public LazyResultModel(List<Result> datasource) {
    this.datasource = datasource;
  }

  @Override
  public Result getRowData(String rowKey) {
    for (Result result : datasource) {
      if (result.getId().equals(rowKey)) {
        return result;
      }
    }
    return null;
  }

  @Override
  public Object getRowKey(Result result) {
    return result.getId();
  }

  @Override
  public List<Result> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    List<Result> data = new ArrayList<>();

    log.info("solr: {} -- {}", first, pageSize);

    SolrDocumentList list = solr.searchAll(first, pageSize);
    log.info("size: {}", list.size());
    for (SolrDocument doc : list) {
      datasource.add(new Result((String) doc.getFieldValue("id"),
              (String) doc.getFieldValue("catalogNumber"),
              (String) doc.getFieldValue("txFullName")));
    }

    for (Result result : datasource) {
      boolean match = true;
//      log.info("filt: {}", filters);
//      if (filters != null) {
//        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
//          try {
//            String filterProperty = it.next();
//            Object filterValue = filters.get(filterProperty);
//            String fieldValue = String.valueOf(result.getClass().getField(filterProperty).get(result));
//
//            if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
//              match = true;
//            } else {
//              match = false;
//              break;
//            }
//          } catch (Exception e) {
//            match = false;
//          }
//        }
//      }

      if (match) {
        data.add(result);
      }
    }

    
    //sort
//    if (sortField != null) {
//      Collections.sort(data, new LazySorter(sortField, sortOrder));
//    }
    //rowCount
    int dataSize = datasource.size(); 
    int rowCount = dataSize;
    this.setRowCount(rowCount);
//    return datasource;
    //paginate
    if (dataSize > pageSize) {
      try {
        return data.subList(first, first + pageSize);
      } catch (IndexOutOfBoundsException e) {
        return data.subList(first, first + (dataSize % pageSize));
      }
    } else {
      return data;
    }
  }

}
