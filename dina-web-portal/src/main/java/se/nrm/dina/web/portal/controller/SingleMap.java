package se.nrm.dina.web.portal.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@Named("map")
@SessionScoped
@Slf4j
public class SingleMap implements Serializable {

  private static final String GMAP_MARKER_PATH = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
  private MapModel singleModal;

  public SingleMap() {
    singleModal = new DefaultMapModel();
  }

  public MapModel getSingledModel(SolrData data) {

    log.info("getSingledModel: {}", data);

    singleModal = new DefaultMapModel();
    if (data != null) { 
      singleModal.addOverlay(new Marker(data.getLatLng(), data.getLocality(), null, GMAP_MARKER_PATH));
    }
    return singleModal;
  }
  
  public void onSingleMarkerSelect(OverlaySelectEvent event) {
    log.info("onSingleMarkerSelect");
  } 
}
