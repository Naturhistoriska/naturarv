package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.logic.config.InitialProperties;

/**
 *
 * @author idali
 */
@Named("contact")
@ApplicationScoped
@Slf4j
public class ContactView implements Serializable {
  
  private String supportMail;
  private String supportPhone;
  
  @Inject
  private InitialProperties properties;
  
  public ContactView() {
    
  }
  
  public ContactView(InitialProperties properties) {
    this.properties = properties;
  }
  
  /**
   * Initialize data after class constructed
   */
  @PostConstruct
  public void init() {
    log.info("init");
    supportMail = properties.getTeamSupportMail();
    supportPhone = properties.getSupportPhone();
  }

  public String getSupportMail() {
    return supportMail;
  }

  public void setSupportMail(String supportMail) {
    this.supportMail = supportMail;
  }

  public String getSupportPhone() {
    return supportPhone;
  }

  public void setSupportPhone(String supportPhone) {
    this.supportPhone = supportPhone;
  } 
}
