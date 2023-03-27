package se.nrm.dina.web.portal.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author idali
 */
@Named
@RequestScoped
public class ErrorBean {

    public void throwError() {
        throw new RuntimeException("throwing new error");
    }
}
