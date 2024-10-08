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
    private final String pzCollection = "pz";
    private final String pb = "pb";
    private final String pbCollection = "pb";
    private final String geo = "geo";
    private final String qryDataset = "dataset";
    private final String paleontology = "paleontology"; 
    private final String pCollection = "p*"; 
    private final String zoo = "zoo";
    private final String zooCollection = "(e* 262144 655361 163840 ma fish herps va)";
    private final String vertebrate = "vertebrate";
    private final String vertebratCollection = "(ma fish herps)";
    private final String botany = "botany";
    private final String botanyCollection = "(vp fungi mosses algae)"; 
    private final String geoCollection = "(557057 753664 786432)";
    
    
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
          
        String uri = request.getRequestURI(); 
         
        String queryString = request.getQueryString();
        String collection = request.getParameter(qryCollection);  
        String dataset = null;
         
        if (uri == null || uri.equals(slash)) {
            if (!StringUtils.isBlank(collection)) {
                switch (collection) {
                    case pz:
                        dataset = request.getParameter(qryDataset);
                        collection = pzCollection;
                        break;
                    case pb: 
                        collection = pbCollection;
                        break;
                    case botany:
                        collection = botanyCollection;
                        break;
                    case paleontology:
                        collection = pCollection;
                        break; 
                    case zoo:
                        collection = zooCollection;
                        break;
                    case vertebrate:
                        collection = vertebratCollection;
                        break;
                    case geo:
                        collection = geoCollection;
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
    }

    public boolean isIsCollectionSearch() {
        return isCollectionSearch;
    }

}
