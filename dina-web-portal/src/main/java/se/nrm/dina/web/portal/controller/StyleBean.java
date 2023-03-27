package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@SessionScoped
@Named("style")
@Slf4j
public class StyleBean implements Serializable {

    private String svLink;
    private String enLink; 
    private String swedishLink;
    private String englishLink;
    
    private final String home = "home";
    private final String collections = "collections";

    private final String current = "current";
    private final String activelink = "activelink";
    private final String inactivelink = "inactivelink";

    private String tabStart;
    private String tabCollections;

    public StyleBean() {
        svLink = activelink;  
        swedishLink = current;
        tabStart = current;
    }

    public String getEnLink() {
        return enLink;
    }

    public String getSvLink() {
        return svLink;
    }

    public String getSwdishLink() {
        return swedishLink;
    }

    public String getEnglishLink() {
        return englishLink;
    }
    
    

    public String getTabStart() {
        return tabStart;
    }

    public String getTabCollections() {
        return tabCollections;
    }

    public void setTabStyle(String tab) { 
        tabStart = null;
        tabCollections = null;
        
        switch (tab) {
            case home:
                tabStart = current; 
                break;
            case collections:
                tabCollections = current;
                break;
            default:
                tabStart = current;
        }
    }

    public void setLanguageStyle(String locale) {

        svLink = inactivelink;
        enLink = inactivelink;

        if (locale.equals(CommonText.getInstance().getSv())) {
            svLink = activelink;
        } else {
            enLink = activelink;
        }
    }
    
    public void setTopManuLanguageStyle(String locale) {

        swedishLink = null;
        englishLink = null;

        if (locale.equals(CommonText.getInstance().getSv())) {
            swedishLink = current;
        } else {
            englishLink = current;
        }
    }
}
