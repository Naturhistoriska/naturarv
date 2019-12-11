package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;  
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.HelpClass;

/**
 *
 * @author idali
 */
@SessionScoped
@Named
@Slf4j
public class Languages implements Serializable {

  private String locale = "sv";
  private final String english = "English";
  private final String swedish = "Svenska";
  
  private final List<String> updateList; 
  private HttpSession session;  
  
  @Inject
  private ChartView chart;
  
  @Inject
  private SearchBean search;
  
  @Inject
  private StatisticBean statistic;
 
  @Inject
  private StyleBean style;

  public Languages() {
    log.info("Languages");
    
    updateList = new ArrayList<>();
    updateList.add("headerForm:header");
    updateList.add("topMenuForm:topmenupanel");
    updateList.add("searchForm:searchPanel");
    updateList.add("footerPanel"); 
    updateList.add("mainPanel"); 
     
    session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    session.setAttribute(CommonText.getInstance().getLocale(), locale);
  }
  
  public Languages(ChartView chart, SearchBean search, StatisticBean statistic, StyleBean style) {
    this.chart = chart;
    this.search = search;
    this.statistic = statistic;
    this.style = style;
    
    updateList = new ArrayList<>();
    updateList.add("headerForm:header");
    updateList.add("topMenuForm:topmenupanel");
    updateList.add("searchForm:searchPanel");
    updateList.add("footerPanel"); 
    updateList.add("mainPanel"); 
  }

  public String getLocale() {
    return locale;
  }

  public void changeLanguage(String locale) { 
    log.info("changeLanguage - locale: {}", locale); 
    
    if(!this.locale.equals(locale)) {
      style.setLanguageStyle(locale);
      this.locale = locale;
      session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      session.setAttribute(CommonText.getInstance().getLocale(), locale);
      chart.changeLanguage(isSwedish()); 
      search.changeLanguage(isSwedish());
      statistic.changeLanguage(isSwedish());
      HelpClass.getInstance().updateView(updateList);
    } 
  }

  public String getLanguage() {
    return locale.equals(CommonText.getInstance().getSv()) ? english : swedish;
  }

  public boolean isSwedish() {
    return locale.equals(CommonText.getInstance().getSv());
  }
}
