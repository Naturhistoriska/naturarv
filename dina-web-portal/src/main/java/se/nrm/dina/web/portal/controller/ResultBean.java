/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;
 
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import se.nrm.dina.web.portal.logic.LazyResultModel;
import se.nrm.dina.web.portal.model.Result;


/**
 *
 * @author idali
 */
@Named("result")
@ViewScoped
public class ResultBean implements Serializable {
  
  private LazyDataModel<Result> lazy;
  
  public void init() {
    lazy = new LazyResultModel();
  }
}
