/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j; 

/**
 *
 * @author idali
 */
@SessionScoped
@Named("paging")
@Slf4j
public class PagingNavigation implements Serializable {

  private int currentPage = 1;
  private int totalPages = 1;
  private int startPage = 1;
  private int endPage = 10;

  private int totalFound = 0;
  private int start = 0;
  private int end = 0;
  
  private final List<Integer> pages = new ArrayList<>();

  public PagingNavigation() {
  }

  public void calculateTotalPages(int totalFound, int numPerPage) {
    log.info("calculateTotalPages : {}", numPerPage);

    this.totalFound = totalFound;
    start = 1;
    end = numPerPage;
    startPage = 1;
    currentPage = 1;
    totalPages = 1;
    if (totalFound > numPerPage) {
      totalPages = totalFound / numPerPage;
      if (totalFound % numPerPage > 0) {
        totalPages++;
      }
    }
    endPage = totalPages < 10 ? totalPages : 10; 
    setPageNumber();
  }
   
  public void setNextPage(int numPerPage) {
    log.info("num per page : {}", numPerPage);
    
    start += numPerPage;
    currentPage++; 
    end = currentPage < totalPages ? end + numPerPage : totalFound; 
    setNextPageRange();
  }

  public void setPreviousPage(int numPerPage) {
    log.info("setPreviousPage: {}", numPerPage);

    start -= numPerPage;
    end = currentPage < totalPages ? end - numPerPage : start + numPerPage - 1;
    currentPage--;
    setPreviousPageRange();
  }

  public void setFirstPage(int numPerPage) {
    start = 1;
    end = numPerPage;
    currentPage = 1;
    startPage = 1; 
     
    endPage = totalPages < 10 ? totalPages : 10; 
    setPageNumber(); 
  }

  public void setLastPage(int numDisplay) { 
    start = numDisplay * (totalPages - 1) + 1;
    end = totalFound;
    endPage = totalPages;
    startPage = totalPages - 9;
    currentPage = totalPages;
    setPageNumber();
  }
  
  public void setPaging(int start, int numPerPage, int currentPage) { 
    this.start = start + 1;
    end = start + numPerPage;
    this.currentPage = currentPage;
    setPageRange();
  }
 
  private void setPageNumber() {
    log.info("setPageNumber: {} -- {}", startPage, endPage);
    
    pages.clear();
    IntStream.rangeClosed(startPage, endPage).forEach(i -> {
      pages.add(i);
    });
  }
  
  private void setPageRange() {
    if(currentPage > 6 || totalPages - currentPage < 5) { 
      startPage = currentPage - 5;
      endPage = currentPage + 4;
    }
    setPageNumber();
  }
  
  private void setNextPageRange() {

    int middlePage = (endPage - startPage) / 2 + startPage; 
    if (currentPage > middlePage) {
      if (endPage < totalPages) {
        startPage++;
        endPage++;
      }
    }
    setPageNumber();
  }

  private void setPreviousPageRange() {
    int middlePage = (endPage - startPage) / 2 + startPage;
    if (currentPage < middlePage + 1) {
      if (startPage > 1) {
        startPage--;
        endPage--;
      }
    }
    setPageNumber();
  }
  
  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  } 
  
  public int getTotalPages() {
    return totalPages;
  }
  
  public int getTotalFound() {
    return totalFound;
  }
  
  public List<Integer> getPages() {
    return pages;
  }
  
  public int getCurrentPage() {
    return currentPage;
  } 
}
