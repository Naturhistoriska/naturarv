package se.nrm.dina.web.portal.model;

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
@Suite.SuiteClasses({se.nrm.dina.web.portal.model.ErropReportEmailTest.class, 
  se.nrm.dina.web.portal.model.StatisticDataTest.class, 
  se.nrm.dina.web.portal.model.QueryDataTest.class, 
  se.nrm.dina.web.portal.model.ErrorReportTest.class, 
  se.nrm.dina.web.portal.model.SolrResultTest.class, 
  se.nrm.dina.web.portal.model.CollectionDataTest.class,   
  se.nrm.dina.web.portal.model.RectangleDataTest.class, 
  se.nrm.dina.web.portal.model.SolrDataTest.class, 
  se.nrm.dina.web.portal.model.ImageModelTest.class, 
  se.nrm.dina.web.portal.model.GeoHashDataTest.class}) 
public class ModelSuite {

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
