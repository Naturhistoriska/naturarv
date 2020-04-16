package se.nrm.dina.web.portal.logic.mail;

import java.io.Serializable;
import java.util.Properties;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.web.portal.logic.config.InitialProperties;
import se.nrm.dina.web.portal.model.ErropReportEmail;
import se.nrm.dina.web.portal.model.ErrorReport;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@Slf4j
public class MailHandler implements Serializable {

  @Inject
  private InitialProperties properties;

  private static final String MAIL_SUBJECT_EN = "Error report (naturarv)";
  private static final String MAIL_SUBJECT_SV = "Felrapport (naturarv)";
  private static final String MAIL_CONTENT = "text/html; charset=ISO-8859-1";
 
  public MailHandler() {

  }

  public MailHandler(InitialProperties properties) {
    this.properties = properties;
  }

  public void sendMail(SolrData data, ErrorReport error, boolean isSwedish) {
 
    ErropReportEmail report = new ErropReportEmail();
    Properties props = new Properties();
    props.put(properties.getMailHostName(), properties.getMailHost());

    Session session = Session.getInstance(props, null);
    session.setDebug(true);
    Message message = new MimeMessage(session);

    try { 
      InternetAddress emailAddress = new InternetAddress(properties.getSupportMail());
      message.setSubject(isSwedish ? MAIL_SUBJECT_SV : MAIL_SUBJECT_EN);
      message.addRecipient(Message.RecipientType.TO, emailAddress);

      message.setContent(report.createErrorReport(data, error, isSwedish), MAIL_CONTENT);
      Transport.send(message);
    } catch (MessagingException ex) {
      log.error(ex.getMessage());
    } 
  } 
}
