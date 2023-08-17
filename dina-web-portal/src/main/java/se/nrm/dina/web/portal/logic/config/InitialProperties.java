package se.nrm.dina.web.portal.logic.config;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

/**
 *
 * @author idali
 */
@ApplicationScoped
@Slf4j
public class InitialProperties implements Serializable {

    private final static String CONFIG_INITIALLISING_ERROR = "Property not initialised";

    private String solrPath;
    private String solrCore;
    private String morphbankThumbPath;
    private String mapKey;
    private String captchaPublicKey;
    private String captchaPrivateKey;
    private String supportMail;
    private String mailHost;
    private String mailHostName;
    private String teamSupportMail;
    private String supportPhone;
    private String username;
    private String password;

    public InitialProperties() {
    }

    @Inject
    public InitialProperties(@ConfigurationValue("swarm.solr.path") String solrPath,
            @ConfigurationValue("swarm.solr.core") String solrCore,
            @ConfigurationValue("swarm.morphbank.thumbs") String morphbankThumbPath,
            @ConfigurationValue("swarm.map.key") String mapKey,
            @ConfigurationValue("swarm.captcha.public") String captchaPublicKey,
            @ConfigurationValue("swarm.captcha.private") String captchaPrivateKey,
            @ConfigurationValue("swarm.mail.host") String mailHost,
            @ConfigurationValue("swarm.mail.hostname") String mailHostName,
            @ConfigurationValue("swarm.mail.support") String supportMail,
            @ConfigurationValue("swarm.support.mail") String teamsupportMail,
            @ConfigurationValue("swarm.support.phone") String supportPhone,
            @ConfigurationValue("swarm.solr.username") String username,
            @ConfigurationValue("swarm.solr.password") String password) {
        this.solrPath = solrPath;
        this.solrCore = solrCore;
        this.morphbankThumbPath = morphbankThumbPath;
        this.mapKey = mapKey;
        this.captchaPrivateKey = captchaPrivateKey;
        this.captchaPublicKey = captchaPublicKey;
        this.mailHost = mailHost;
        this.mailHostName = mailHostName;
        this.supportMail = supportMail;
        this.teamSupportMail = teamsupportMail;
        this.supportPhone = supportPhone;
        this.username = username;
        this.password = password;
        
        log.info("username and password : {} -- {}", username, password);
    }

    public String getSolrPath() {
        if (solrPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return solrPath;
    }

    public String getSolrURL() {
        if (solrPath == null || solrCore == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return solrPath + "/" + solrCore;
    }

    public String getMorphbankThumbPath() {
        if (morphbankThumbPath == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return morphbankThumbPath;
    }

    public String getMapKey() {
        if (mapKey == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return mapKey;
    }

    public String getCaptchaPublicKey() {
        if (captchaPublicKey == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return captchaPublicKey;
    }

    public String getCaptchaPrivateKey() {
        if (captchaPrivateKey == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return captchaPrivateKey;
    }

    public String getMailHost() {
        if (mailHost == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return mailHost;
    }

    public String getMailHostName() {
        if (mailHostName == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return mailHostName;
    }

    public String getSupportMail() {
        if (supportMail == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return supportMail;
    }

    public String getTeamSupportMail() {
        if (teamSupportMail == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return teamSupportMail;
    }

    public String getSupportPhone() {
        if (supportPhone == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return supportPhone;
    }
    
    public String getUsername() {
        if (username == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return username;
    }
    
    public String getPassword() {
        if (password == null) {
            throw new RuntimeException(CONFIG_INITIALLISING_ERROR);
        }
        return password;
    }
}
