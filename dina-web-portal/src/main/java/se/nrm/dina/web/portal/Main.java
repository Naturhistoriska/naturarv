/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal;
 
import java.text.SimpleDateFormat;
import java.time.LocalDate; 
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author idali
 */
public class Main {

  private final static SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

  public static void main(String[] args) {
    LocalDateTime localDate = LocalDate.now().atStartOfDay();
    System.out.println("LocalDate = " + localDate);
 
    Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

//    
//    String strNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
//            
    dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    String date = dateFormatUTC.format(d);
    System.out.println("se.nrm.dina.web.portal.Main.main() --- " + date);

//    startDate = YearMonth.from(LocalDate.now().minusMonths(12)).atDay(1); 
  }
}
