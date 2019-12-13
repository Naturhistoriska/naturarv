/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.lazy.datamodel;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.SortOrder;
import se.nrm.dina.web.portal.model.ImageModel;

/**
 *
 * @author idali
 */
public class ImageLazyDataModelTest {
  
  public ImageLazyDataModelTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of load method, of class ImageLazyDataModel.
   */
  @Test
  public void testLoad() {
    System.out.println("load");
    int first = 0;
    int pageSize = 0;
    String sortField = "";
    SortOrder sortOrder = null;
    Map<String, Object> filters = null;
    ImageLazyDataModel instance = null;
    List<ImageModel> expResult = null;
    List<ImageModel> result = instance.load(first, pageSize, sortField, sortOrder, filters);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
