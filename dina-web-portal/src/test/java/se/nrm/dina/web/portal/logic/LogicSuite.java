package se.nrm.dina.web.portal.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author idali
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({se.nrm.dina.web.portal.logic.ChartCreatorTest.class, 
  se.nrm.dina.web.portal.logic.utils.UtilsSuite.class, 
  se.nrm.dina.web.portal.logic.config.ConfigSuite.class, 
  se.nrm.dina.web.portal.logic.mail.MailSuite.class, 
  se.nrm.dina.web.portal.logic.errorhandler.ErrorhandlerSuite.class,   
  se.nrm.dina.web.portal.logic.lazy.LazySuite.class, 
  se.nrm.dina.web.portal.logic.solr.SolrSuite.class})
public class LogicSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }
  
}
