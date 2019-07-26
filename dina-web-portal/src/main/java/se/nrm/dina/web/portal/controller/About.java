/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped; 
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.utils.AboutPageText;

/**
 *
 * @author idali
 */
@Named("about")
@ApplicationScoped
@Slf4j
public class About implements Serializable {
  
  @Inject
  private Languages language;
  
  private final AboutPageText about;
  
  public About() {
    about = AboutPageText.getInstance();
  }
  
  public String getWhatIsNaturarvText() { 
    log.info("getText....");
    return about.getWhatIsNaturarv(language.isIsSwedish()); 
  }
  
  public String getWhatIsNaurarvSubText() {
    return about.getWhatIsNaturarvSub(language.isIsSwedish());
  }
  
  public String getPartInfrastructureText() {
    return about.getInfrastrcutureText(language.isIsSwedish());
  }
  
  public String getPartInfrastructureSubText() {
    return about.getInfrastrcutureSubText(language.isIsSwedish());
  }
  
  public String getDevelopmentText() {
    return about.getDevelopmentText(language.isIsSwedish());
  }
  
  public String getFinancialSupportText() {
    return about.getFinancialSupportText(language.isIsSwedish());
  }
}
