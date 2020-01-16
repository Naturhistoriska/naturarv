package se.nrm.dina.web.portal.utils;

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
@Suite.SuiteClasses({se.nrm.dina.web.portal.utils.DateHelperTest.class, 
  se.nrm.dina.web.portal.utils.CommonTextTest.class,   
  se.nrm.dina.web.portal.utils.HelpClassTest.class, 
  se.nrm.dina.web.portal.utils.MonthElementTest.class})
public class UtilsSuite {

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
