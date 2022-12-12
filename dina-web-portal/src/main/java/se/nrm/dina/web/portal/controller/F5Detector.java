package se.nrm.dina.web.portal.controller;

import java.io.Serializable; 
import javax.enterprise.context.SessionScoped; 
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import se.nrm.dina.web.portal.utils.CommonText; 

/**
 *
 * @author idali
 */
@SessionScoped
@Named("f5Detector")
@Slf4j
public class F5Detector implements Serializable {
 
    private final String qryCollection = "collection";
    private boolean isCollectionSearch;
    private final String pz = "pz";
    private final String qryDataset = "dataset";
    private final String paleobiology = "paleobiology";
    private final String pCollection = "p*";
    private final String all = "all";
    private final String allCollection = "*";
    
    private final String collectionUri = "/faces/pages/collectionresults.xhtml"; 
    
    private final String slash = "/"; 
    @Inject
    private SearchBean search;

    public void checkF5() {
        log.info("checkF5");

        isCollectionSearch = false;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
         
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI(); 
         
        
        log.info("url and uri : {} -- {}", url, uri);
        log.info("pathInfo : {}", request.getQueryString());
        String queryString = request.getQueryString();
        String collection = request.getParameter(qryCollection);  
        String dataset = null;
        
        log.info("collection : {}", collection);

        if (uri == null || uri.equals(slash)) {
            if (!StringUtils.isBlank(collection)) {
                switch (collection) {
                    case pz:
                        dataset = request.getParameter(qryDataset);
                        break;
                    case paleobiology:
                        collection = pCollection;
                        break;
                    case all:
                        collection = allCollection;
                        break;
                    default:
                        break;
                }
                search.searchCollectionWithQuery(collection, dataset, queryString);
                isCollectionSearch = true;
            }
        } else if(uri.equals(collectionUri)) {
            isCollectionSearch = true;
        }  
        log.info("url : {} -- {}", collection, isCollectionSearch); 
    }

    public boolean isIsCollectionSearch() {
        return isCollectionSearch;
    }

}
