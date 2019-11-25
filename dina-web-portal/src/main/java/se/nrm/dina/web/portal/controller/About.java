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
    return about.getWhatIsNaturarv(language.isSwedish()); 
  }
  
  public String getWhatIsNaurarvSubText() {
    return about.getWhatIsNaturarvSub(language.isSwedish());
  }
  
  public String getPartInfrastructureText() {
    return about.getInfrastrcutureText(language.isSwedish());
  }
  
  public String getPartInfrastructureSubText() {
    return about.getInfrastrcutureSubText(language.isSwedish());
  }
  
  public String getDevelopmentText() {
    return about.getDevelopmentText(language.isSwedish());
  }
  
  public String getFinancialSupportText() {
    return about.getFinancialSupportText(language.isSwedish());
  }
}
