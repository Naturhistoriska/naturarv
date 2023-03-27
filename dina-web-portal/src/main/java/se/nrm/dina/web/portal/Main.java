package se.nrm.dina.web.portal;
  
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author idali
 */
public class Main {

  public static void main(String[] args) throws IOException {
      
       String filePath = "/Users/idali/temp/csv/bot/vascular_plants/naturarvkollekt_del3.csv";
      
      File csvOutputFile = new File("/Users/idali/temp/csv/bot/vascular_plants/test.csv");
      
      File file = new File(filePath);
      System.out.println("hi");
      
      Iterator<String> iterator = Files.lines(file.toPath()).iterator(); 
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] values = line.split(";"); 
            String number = values[0].trim();
            System.out.println("length : " + number.length());
            if(number != null && number.length() > 0) {
                System.out.println("value : " + values[0]); 
                try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                    pw.print(line);
                }
            }
 
        }
     

      
      
      
//    try {
//      System.out.println("Nykšpings kommun, central part of the town, river NykšpingsŒn under the bridge Repslagarbron, southern abutment");
//      PrintWriter out = new PrintWriter(
//              new OutputStreamWriter(System.out, "UTF-8"));
//      out.println("Nykšpings kommun, central part of the town, river NykšpingsŒn under the bridge Repslagarbron, southern abutment");
//      out.flush();
//    } catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    }

  }
}
