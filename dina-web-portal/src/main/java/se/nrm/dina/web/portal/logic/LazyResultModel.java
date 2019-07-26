/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import se.nrm.dina.web.portal.model.Result;
import se.nrm.dina.web.portal.solr.SolrService;

/**
 *
 * @author idali
 */
public class LazyResultModel extends LazyDataModel<Result> {
  
  @Inject
  private SolrService solr;
  
  public List<Result> load(int first, int pageSize, String sortField, SortOrder sortOrder) {
    List<Result> data = new ArrayList();
    
    return data;
  }
}
