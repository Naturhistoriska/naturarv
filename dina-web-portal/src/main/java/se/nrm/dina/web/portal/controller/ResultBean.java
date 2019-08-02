/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;
 
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.logic.LazyResultModel;
import se.nrm.dina.web.portal.model.Result;
import se.nrm.dina.web.portal.solr.SolrService;


/**
 *
 * @author idali
 */
@Named("result")
@ViewScoped
@Slf4j
public class ResultBean implements Serializable {
  
  private LazyDataModel<Result> model; 
  private List<Result> resultList = null;
  private List<Result> filteredResultList;
  private Result result = null; 
  
  @Inject
  private SolrService solr;
    
  public ResultBean() { 
  }
  
  @PostConstruct
  public void init() {
    log.info("resultBean init");
    getInitialResult();
    model = new LazyResultModel(solr, resultList);
  }

  public List<Result> getResultList() {
    return resultList;
  }
  
  public void setResultList(List<Result> resultList) {
    this.resultList = resultList;
  }

  public List<Result> getFilteredResultList() {
    return filteredResultList;
  }

  public void setFilteredResultList(List<Result> filteredResultList) {
    this.filteredResultList = filteredResultList;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
  
  
  
  public LazyDataModel<Result> getModel() { 
    return model;
  }

  public void setLazy(LazyDataModel<Result> model) {
    this.model = model;
  }  
  
  public void getInitialResult() {
    resultList = solr.searchAllResults(0);
  }
}
