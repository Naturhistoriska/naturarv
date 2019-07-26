/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

/**
 *
 * @author idali
 */
public class FAQText {
  
  private static FAQText instance = null;

  public static synchronized FAQText getInstance() {
    if (instance == null) {
      instance = new FAQText();
    }
    return instance;
  }
}
