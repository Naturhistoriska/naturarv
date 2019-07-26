/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.solr;

import java.io.IOException; 
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import se.nrm.dina.web.portal.logic.config.InitialProperties;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Startup
@Slf4j
public class SolrClientProducer {

  private SolrClient client;

  @Inject
  private InitialProperties properties;

  public SolrClientProducer() {

  }

  public SolrClientProducer(InitialProperties properties, SolrClient client) {
    this.properties = properties;
    this.client = client;
  }
 
  @PostConstruct
  public void init() {
    log.info("init"); 
     
    client = new HttpSolrClient.Builder(properties.getSolrPath() + "/nrm").build();
  }

  /**
   *
   * Produce CDI KeycloakClient
   *
   * @return Keycloak
   */
  @Produces
  @Solr
  public SolrClient getSolrClient() {
    return client;
  }

  /**
   * Close SolrClient when bean is destroyed
   */
  @PreDestroy
  public void preDestroy() {
    try {
      log.info("preDestroy - keyclokClient is closed");
      client.close();
    } catch (IOException ex) { 
    }
  }
}
