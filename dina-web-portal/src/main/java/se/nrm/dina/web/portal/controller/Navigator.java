package se.nrm.dina.web.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author idali
 */
@Named("navigate")
@SessionScoped
@Slf4j
public class Navigator implements Serializable {

    private ExternalContext externalContext;

    private final String allCollectionsPath = "?collection=all";

    private final String homePath = "/faces/pages/home.xhtml";
    private final String collectionsPath = "/faces/pages/collections.xhtml";
    private final String partnerPath = "/faces/pages/partners.xhtml";
    private final String faqPath = "/faces/pages/faq.xhtml";
    private final String aboutPath = "/faces/pages/about.xhtml";
    private final String contactPath = "/faces/pages/contact.xhtml";

    private final String resultsPath = "/faces/pages/results.xhtml";
    private final String resultsPathWithQueries = "/faces/pages/collectionresults.xhtml?";
    private final String noResultsPath = "/faces/pages/noResult.xhtml";
    private final String noResultsPathWithQueries = "/faces/pages/noResult.xhtml?";
    private final String errorReportPath = "/faces/pages/errorReport.xhtml";

    private final String home = "home";
    private final String collections = "collections";
    private final String partners = "partners";
    private final String faq = "faq";
    private final String about = "about";
    private final String contact = "contact";
    
    private boolean isCollectionSearch;

    private boolean isHomeView;

    private HttpServletRequest req;

    private StringBuilder resultPathSb;

    @Inject
    private StyleBean style;

    public Navigator() {
        log.info("Navigator");
        isHomeView = true;
    }

    public Navigator(StyleBean style) {
        this.style = style;
        isHomeView = true;
        isCollectionSearch = false;
    }

    public void gotoAllCollection() {

        log.info("gotoAllCollection");
        isHomeView = false;
        isCollectionSearch = true;
        redirectPage(allCollectionsPath);  
    }

    public void gotoHome() {
        log.info("gotoHome");
        style.setTabStyle(home);

        if (isResultView()) {
            isHomeView = true; 
            isCollectionSearch = false;
            redirectPage(homePath);
        } else {
            redirectPage(isHomeView ? homePath : resultsPath);
        }

    }

    public void gotoResults(String queries) {
        log.info("gotoResults : {} -- {}", resultsPathWithQueries, queries);

        isHomeView = false;
        style.setTabStyle(home);

        resultPathSb = new StringBuilder();
        resultPathSb.append(resultsPathWithQueries);
        resultPathSb.append(queries);
        isCollectionSearch = true;
        redirectPage(resultPathSb.toString().trim()); 
    }

    public void gotoResults() {
        log.info("gotoResults : {} -- {}", resultsPath);

        isHomeView = false;
        isCollectionSearch = false;
        style.setTabStyle(home);
        redirectPage(resultsPath);
    }

    public void gotoNoResults(String queries) {
        log.info("queries : {}", queries);
        style.setTabStyle(home);
        resultPathSb = new StringBuilder();
        resultPathSb.append(noResultsPathWithQueries);
        resultPathSb.append(queries);
        isCollectionSearch = true;
        redirectPage(resultPathSb.toString().trim());
    }

    public void gotoNoResults() {
        log.info("gotoNoResults");

        style.setTabStyle(home);
        isCollectionSearch = false;
        redirectPage(noResultsPath);
    }

    public void gotoCollections() {
        log.info("gotoCollections");

        style.setTabStyle(collections);
        isCollectionSearch = false;
        redirectPage(collectionsPath);
    }

    public void gotoPartners() {
        log.info("gotoPartners");

        style.setTabStyle(partners);
        isCollectionSearch = false;
        redirectPage(partnerPath);
    }

    public void gotoFAQ() {
        log.info("gotoFAQ");

        style.setTabStyle(faq);
        isCollectionSearch = false;
        redirectPage(faqPath);
    }

    public void gotoAbout() {
        log.info("gotoAbout");

        style.setTabStyle(about);
        isCollectionSearch = false;
        redirectPage(aboutPath);
    }

    public void gotoContactPage() {
        log.info("gotoContactPage");

        style.setTabStyle(contact);
        isCollectionSearch = false;
        redirectPage(contactPath);
    }

    public void gotoErrorReportPage() {
        log.info("gotoErrorReportPage");

        style.setTabStyle(home);
        redirectPage(errorReportPath);
    }

    public boolean isShowSearchPanel() {
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();

        if (url.contains(collectionsPath)) {
            return false;
        }
        if (url.contains(aboutPath)) {
            return false;
        }
        if (url.contains(contactPath)) {
            return false;
        }
        if (url.contains(faqPath)) {
            return false;
        }
        return !url.contains(partnerPath);
    }

    public boolean isResultView() {
        req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        return url.contains("results");
    }

    public boolean isIsCollectionSearch() {
        return isCollectionSearch;
    }
     
    private void redirectPage(String path) {

        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        log.info("path... {} -- {}", externalContext.getRequestContextPath(), path);
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + path);
        } catch (IOException ex) {
        }
    }
}
