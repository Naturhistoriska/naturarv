package se.nrm.dina.web.portal;
  
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author idali
 */
public class Main {

  public static void main(String[] args) {
    try {
      System.out.println("Nykšpings kommun, central part of the town, river NykšpingsŒn under the bridge Repslagarbron, southern abutment");
      PrintWriter out = new PrintWriter(
              new OutputStreamWriter(System.out, "UTF-8"));
      out.println("Nykšpings kommun, central part of the town, river NykšpingsŒn under the bridge Repslagarbron, southern abutment");
      out.flush();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

  }
}
