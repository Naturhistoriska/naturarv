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
import se.nrm.dina.web.portal.utils.FQAPageText;

/**
 *
 * @author idali
 */
@Named("fqa")
@ApplicationScoped
@Slf4j
public class FQAPage implements Serializable {

  @Inject
  private Languages language;
  
  private final FQAPageText fqa; 
  
  public FQAPage() {
    fqa = FQAPageText.getInstance();
  }
  
  public String getWhatIsNaturarvText() {
    return fqa.getWhatIsNaturarvText(language.isSwedish());
  }
  
  public String getWhoIsResponsibleText1() {
    return fqa.getWhoIsResponsible1(language.isSwedish());
  }

  public String getWhoIsResponsibleText2() {
    return fqa.getWhoIsResponsible2(language.isSwedish());
  }

  public String getWhatInformationText1() {
    return fqa.getWhatInformation1(language.isSwedish());
  }

  public String getWhatInformationText2() {
    return fqa.getWhatInformation2(language.isSwedish());
  }

  public String getWhatInformationText3() {
    return fqa.getWhatInformation3(language.isSwedish());
  }

  public String getWhatSourceText1() {
    return fqa.getWhatSource1(language.isSwedish());
  }

  public String getWhatSourceText2() {
    return fqa.getWhatSource2(language.isSwedish());
  }

  public String getTrustText() {
    return fqa.getTrust(language.isSwedish());
  }

  public String getHowOftenText() {
    return fqa.getHowOfter(language.isSwedish());
  }

  public String getHowContactText() {
    return fqa.getHowContact(language.isSwedish());
  }
}
