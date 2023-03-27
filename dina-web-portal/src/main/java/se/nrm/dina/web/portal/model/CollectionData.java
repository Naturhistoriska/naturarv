package se.nrm.dina.web.portal.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
public class CollectionData implements Serializable {

  private String code;
  private String name; 
  private int total;
  
  private final String dots = "...";

  public CollectionData() {

  }

  public CollectionData(String code, String name, int total) {
    this.code = code;
    this.name = name;
    this.total = total;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
 
  public int getTotal() {
    return total;
  }
  
  public String getSwedishName() { 
      return CommonText.getInstance().getCollectionSwedishName(name);
  }

  public String getShortName() {
    if(name.trim().length() <= 20) {
      return name;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(StringUtils.substring(name, 0, 19));
    sb.append(dots);
    return sb.toString(); 
  } 
  
  @Override
  public String toString() {
    return code + " : " + name + " : " + total;
  }

}
