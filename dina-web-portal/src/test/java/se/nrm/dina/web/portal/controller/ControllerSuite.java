package se.nrm.dina.web.portal.controller;

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
@Suite.SuiteClasses({
  se.nrm.dina.web.portal.controller.CollectionsViewTest.class, 
  se.nrm.dina.web.portal.controller.ErrorReportBeanTest.class, 
  se.nrm.dina.web.portal.controller.LanguagesTest.class, 
  se.nrm.dina.web.portal.controller.PagingNavigationTest.class, 
  se.nrm.dina.web.portal.controller.ContactViewTest.class, 
  se.nrm.dina.web.portal.controller.ResultHeaderTest.class,  
  se.nrm.dina.web.portal.controller.ErrorBeanTest.class,  
  se.nrm.dina.web.portal.controller.ChartViewTest.class,  
  se.nrm.dina.web.portal.controller.GalleriaBeanTest.class, 
  se.nrm.dina.web.portal.controller.AboutTest.class, 
  se.nrm.dina.web.portal.controller.StatisticBeanTest.class, 
  se.nrm.dina.web.portal.controller.NavigatorTest.class, 
  se.nrm.dina.web.portal.controller.SearchBeanTest.class, 
  se.nrm.dina.web.portal.controller.StyleBeanTest.class, 
  se.nrm.dina.web.portal.controller.IdleMonitorControllerTest.class, 
  se.nrm.dina.web.portal.controller.ImageSwitcherTest.class})
public class ControllerSuite {

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
