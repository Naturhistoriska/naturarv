/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class PagingNavigationTest {
  
  public PagingNavigationTest() {
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
   * Test of calculateTotalPages method, of class PagingNavigation.
   */
//  @Test
  public void testCalculateTotalPages() {
    System.out.println("calculateTotalPages");
    int totalFound = 0;
    int numPerPage = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.calculateTotalPages(totalFound, numPerPage);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setNextPage method, of class PagingNavigation.
   */
//  @Test
  public void testSetNextPage() {
    System.out.println("setNextPage");
    int numPerPage = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.setNextPage(numPerPage);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPreviousPage method, of class PagingNavigation.
   */
//  @Test
  public void testSetPreviousPage() {
    System.out.println("setPreviousPage");
    int numPerPage = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.setPreviousPage(numPerPage);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFirstPage method, of class PagingNavigation.
   */
//  @Test
  public void testSetFirstPage() {
    System.out.println("setFirstPage");
    int numPerPage = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.setFirstPage(numPerPage);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLastPage method, of class PagingNavigation.
   */
//  @Test
  public void testSetLastPage() {
    System.out.println("setLastPage");
    int numDisplay = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.setLastPage(numDisplay);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPaging method, of class PagingNavigation.
   */
//  @Test
  public void testSetPaging() {
    System.out.println("setPaging");
    int start = 0;
    int numPerPage = 0;
    int currentPage = 0;
    PagingNavigation instance = new PagingNavigation();
    instance.setPaging(start, numPerPage, currentPage);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getStart method, of class PagingNavigation.
   */
//  @Test
  public void testGetStart() {
    System.out.println("getStart");
    PagingNavigation instance = new PagingNavigation();
    int expResult = 0;
    int result = instance.getStart();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEnd method, of class PagingNavigation.
   */
//  @Test
  public void testGetEnd() {
    System.out.println("getEnd");
    PagingNavigation instance = new PagingNavigation();
    int expResult = 0;
    int result = instance.getEnd();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalPages method, of class PagingNavigation.
   */
//  @Test
  public void testGetTotalPages() {
    System.out.println("getTotalPages");
    PagingNavigation instance = new PagingNavigation();
    int expResult = 0;
    int result = instance.getTotalPages();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getTotalFound method, of class PagingNavigation.
   */
//  @Test
  public void testGetTotalFound() {
    System.out.println("getTotalFound");
    PagingNavigation instance = new PagingNavigation();
    int expResult = 0;
    int result = instance.getTotalFound();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPages method, of class PagingNavigation.
   */
//  @Test
  public void testGetPages() {
    System.out.println("getPages");
    PagingNavigation instance = new PagingNavigation();
    List<Integer> expResult = null;
    List<Integer> result = instance.getPages();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCurrentPage method, of class PagingNavigation.
   */
//  @Test
  public void testGetCurrentPage() {
    System.out.println("getCurrentPage");
    PagingNavigation instance = new PagingNavigation();
    int expResult = 0;
    int result = instance.getCurrentPage();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
