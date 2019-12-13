package se.nrm.dina.web.portal.controller;

import java.util.List;
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author idali
 */
public class PagingNavigationTest {
  
  private PagingNavigation instance;
  
  public PagingNavigationTest() {
  }
 
  @Before
  public void setUp() {
    instance = new PagingNavigation();
  }
  
  @After
  public void tearDown() {
    instance = null;
  }

  /**
   * Test of calculateTotalPages method, of class PagingNavigation.
   */
  @Test
  public void testCalculateTotalPages1() {
    System.out.println("calculateTotalPages");
    int totalFound = 288;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    List pages = instance.getPages();
    int pageSize = pages.size();
    
    assertEquals(instance.getTotalPages(), 29); 
    assertEquals(instance.getEnd(), 10); 
    assertEquals(pageSize, 10);
    assertEquals(pages.get(0), 1);
    assertEquals(pages.get(pageSize - 1), 10);
  }
  
  @Test
  public void testCalculateTotalPages2() {
    System.out.println("calculateTotalPages");
    int totalFound = 16;
    int numPerPage = 25; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    assertEquals(instance.getTotalPages(), 1);
    assertEquals(instance.getEnd(), 25);
    assertEquals(instance.getPages().size(), 1);
  }
  
  @Test
  public void testCalculateTotalPages3() {
    System.out.println("calculateTotalPages");
    int totalFound = 88;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    List pages = instance.getPages();
    int pageSize = pages.size();
    
    assertEquals(instance.getTotalPages(), 9);
    assertEquals(instance.getEnd(), 10);
    assertEquals(pageSize, 9);
    assertEquals(pages.get(0), 1);
    assertEquals(pages.get(pageSize - 1), 9);
  }

  /**
   * Test of setNextPage method, of class PagingNavigation.
   */
  @Test
  public void testSetNextPage1() {
    System.out.println("se  NextPage");
    int numPerPage = 10; 
    int totalFound = 288; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    instance.setNextPage(numPerPage); 
     
    assertEquals(instance.getStart(), 11);
    assertEquals(2, instance.getCurrentPage()); 
    assertEquals(instance.getEnd(), 20); 
  }
  
  @Test
  public void testSetNextPage2() {
    System.out.println("se  NextPage");
    int numPerPage = 10; 
    int totalFound = 18; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    instance.setNextPage(numPerPage); 
    assertEquals(instance.getStart(), 11);
    assertEquals(2, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 18);
  }

  /**
   * Test of setPreviousPage method, of class PagingNavigation.
   */
  @Test
  public void testSetPreviousPage1() {
    System.out.println("setPreviousPage");
   
    int totalFound = 88;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    instance.setNextPage(numPerPage);
    instance.setNextPage(numPerPage);
    
    instance.setPreviousPage(numPerPage); 
    
    assertEquals(11, instance.getStart());
    assertEquals(2, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 20);
  }

  @Test
  public void testSetPreviousPage2() {
    System.out.println("setPreviousPage");
   
    int numPerPage = 10; 
    int totalFound = 28; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    instance.setNextPage(numPerPage);
    instance.setNextPage(numPerPage);
    
    instance.setPreviousPage(numPerPage); 
    
    assertEquals(11, instance.getStart());
    assertEquals(2, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 20);
  }
  
  /**
   * Test of setFirstPage method, of class PagingNavigation.
   */
  @Test
  public void testSetFirstPage() {
    System.out.println("setFirstPage");
    int numPerPage = 10; 
    int totalFound = 28; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    instance.setFirstPage(numPerPage); 
     
    assertEquals(1, instance.getStart());
    assertEquals(1, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 10);
  }

  /**
   * Test of setLastPage method, of class PagingNavigation.
   */
  @Test
  public void testSetLastPage1() {
    System.out.println("setLastPage");
    int totalFound = 288;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    instance.setLastPage(numPerPage); 
    assertEquals(281, instance.getStart());
    assertEquals(29, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 288);
    
  }
  
  @Test
  public void testSetLastPage2() {
    System.out.println("setLastPage");
    int totalFound = 89;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    instance.setLastPage(numPerPage); 
    assertEquals(81, instance.getStart());
    assertEquals(9, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 89); 
  }

  /**
   * Test of setPaging method, of class PagingNavigation.
   */
  @Test
  public void testSetPaging1() {
    System.out.println("setPaging");
    
    int totalFound = 288;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
     
    int start = 50; 
    int currentPage = 5; 
    instance.setPaging(start, numPerPage, currentPage); 
    assertEquals(51, instance.getStart());
    assertEquals(5, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 60);
    assertEquals(instance.getPages().size(), 10);
  }
  
  @Test
  public void testSetPaging2() {
    System.out.println("setPaging");
    
    int totalFound = 66;
    int numPerPage = 10; 
    instance.calculateTotalPages(totalFound, numPerPage); 
    
    
    int start = 50; 
    int currentPage = 5; 
    instance.setPaging(start, numPerPage, currentPage); 
    assertEquals(51, instance.getStart());
    assertEquals(5, instance.getCurrentPage());
    assertEquals(instance.getEnd(), 60);
    assertEquals(instance.getPages().size(), 7);
  }

  /**
   * Test of getStart method, of class PagingNavigation.
   */
  @Test
  public void testGetStart() {
    System.out.println("getStart"); 
    int expResult = 0;
    int result = instance.getStart();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getEnd method, of class PagingNavigation.
   */
  @Test
  public void testGetEnd() {
    System.out.println("getEnd"); 
    int expResult = 0;
    int result = instance.getEnd();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTotalPages method, of class PagingNavigation.
   */
  @Test
  public void testGetTotalPages() {
    System.out.println("getTotalPages"); 
    int expResult = 1;
    int result = instance.getTotalPages();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getTotalFound method, of class PagingNavigation.
   */
  @Test
  public void testGetTotalFound() {
    System.out.println("getTotalFound"); 
    int expResult = 0;
    int result = instance.getTotalFound();
    assertEquals(expResult, result); 
  }

  /**
   * Test of getPages method, of class PagingNavigation.
   */
  @Test
  public void testGetPages() {
    System.out.println("getPages");  
    List<Integer> result = instance.getPages();
    assertNotNull(result); 
  }

  /**
   * Test of getCurrentPage method, of class PagingNavigation.
   */
  @Test
  public void testGetCurrentPage() {
    System.out.println("getCurrentPage"); 
    int expResult = 1;
    int result = instance.getCurrentPage();
    assertEquals(expResult, result); 
  }
  
}
