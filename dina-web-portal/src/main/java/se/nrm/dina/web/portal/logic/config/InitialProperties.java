/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.config;

import java.io.Serializable; 
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject; 
import lombok.extern.slf4j.Slf4j;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

/**
 *
 * @author idali
 */  
@ApplicationScoped
@Slf4j
public class InitialProperties implements Serializable {
  
  private final static String CONFIG_INITIALLISING_ERROR = "Property not initialised";
 
  private String solrPath;

  public InitialProperties() {
  }

  @Inject
  public InitialProperties(@ConfigurationValue("swarm.solr.path") String solrPath) { 
    this.solrPath = solrPath;
    log.info("test injection : {} ", solrPath);
  }
  
  public String getSolrPath() {
    if (solrPath == null) {
      throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
    }
    return solrPath;
  } 
} 