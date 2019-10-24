/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.logic.utils;

import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Stream;

/**
 *
 * @author idali
 */
@Slf4j
public class GalleriaHelper {

  private final static String ALL = "all";
  private final static String LABEL = "Label";
  private final static String DORSAL = "Dorsal";
  private final static String VENTRAL = "Ventral";
  private final static String LATERAL = "Lateral";
  private final static String FRONTAL = "Frontal";
  private final static String CAUDAL = "Caudal";

  private final static String ABDOMEN = "Abdomen";
  private final static String FACE = "Face";
  private final static String GENITALIA = "Genitalia";
  private final static String WINGS = "Wings";
  private final static String HEAD = "Head";
  private final static String LEGS = "Legs";
  private final static String LOBE = "Lobe";
  private final static String PRONOTUM = "Pronotum";
  private final static String VARI = "Vari";
  private final static String MESONOTUM = "Mesonotum";
  private final static String POSTERIOR = "Posterior";
  private final static String PALPS = "Palps";
  private final static String TARSI = "Tarsi";
  private final static String LABRUM = "Labrum";
  private final static String NOTUM = "Notum";
  private final static String MOUTH = "Mouth";
  private final static String CHELICERAE = "Chelicerae";

  private final static String MALE = "/male/";
  private final static String FEMALE = "female";

  private final static String LARVA = "larva";
  private final static String ADULT = "adult";

  private static GalleriaHelper instance = null;

  public static synchronized GalleriaHelper getInstance() {
    if (instance == null) {
      instance = new GalleriaHelper();
    }
    return instance;
  }

  /**
   * 
   * @param viewList 
   */
  public void setAllViewList(List<String> viewList) {
    viewList.clear();
    viewList.add(ALL);
    viewList.add(LABEL);
    viewList.add(DORSAL);
    viewList.add(VENTRAL);
    viewList.add(LATERAL);
    viewList.add(FRONTAL);
    viewList.add(CAUDAL);
  }

  /**
   * 
   * @param partsList 
   */
  public void setAllPartsList(List<String> partsList) {
    partsList.clear();
    partsList.add(ALL);
    partsList.add(ABDOMEN);
    partsList.add(FACE);
    partsList.add(GENITALIA);
    partsList.add(WINGS);
    partsList.add(HEAD);
    partsList.add(LEGS);
    partsList.add(LOBE);
    partsList.add(PRONOTUM);
    partsList.add(VARI);
    partsList.add(MESONOTUM);
    partsList.add(GENITALIA);
    partsList.add(POSTERIOR);
    partsList.add(PALPS);
    partsList.add(TARSI);
    partsList.add(LABRUM);
    partsList.add(NOTUM);
    partsList.add(MOUTH);
    partsList.add(CHELICERAE);
  }

  /**
   * 
   * @param sexList 
   */
  public void setAllSexList(List<String> sexList) {
    sexList.clear();
    sexList.add(ALL);
    sexList.add(MALE);
    sexList.add(FEMALE);
  }

  /**
   * 
   * @param stagesList 
   */
  public void setAllStagesList(List<String> stagesList) {
    stagesList.clear();
    stagesList.add(ALL);
    stagesList.add(LARVA);
    stagesList.add(ADULT);
  }

  public void viewOptionChanged(List<String> selectedViews, List<String> viewList,
          List<String> partsList, List<String> sexList, List<String> stagesList) {

    boolean isDefaultView = (viewList.isEmpty() && partsList.isEmpty() 
                              && sexList.isEmpty() && stagesList.isEmpty());
    selectedViews.clear();
    if (!isDefaultView) {
      Stream.of(viewList, partsList, sexList, stagesList)
              .flatMap(x -> x.stream())
              .filter(pNotAll)
              .forEach(x -> {
                selectedViews.add(x);
              });
    }
  }

  public void setSelectedList(List<String> list) {
    if (list.contains(ALL))  {
      list.remove(ALL);
    } else {
      list.clear();
    }
  }

  public boolean hasAll(List<String> list, int count) {
    return list.contains(ALL) ? true : list.size() == count;
  }

  Predicate<String> pNotAll = v -> !v.equals(ALL);
}
