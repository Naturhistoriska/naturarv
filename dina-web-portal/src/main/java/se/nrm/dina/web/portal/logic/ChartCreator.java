package se.nrm.dina.web.portal.logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.stream.IntStream; 
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import se.nrm.dina.web.portal.logic.utils.ChartHelper;
import se.nrm.dina.web.portal.utils.CommonText;

/**
 *
 * @author idali
 */
@Slf4j
public class ChartCreator implements Serializable {
  
  /**
   * Create a month chart 
   * @param dataMap - Map<String, Integer>
   * @param startDate - LocalDateTime
   * @param isSwedish - boolean
   * @return BarChartModel
   */
  public BarChartModel createMonthChart(Map<String, Integer> dataMap, 
          LocalDateTime startDate, boolean isSwedish) {  
    log.info("createMonthChart : {}", isSwedish);
    
    BarChartModel model = new BarChartModel();
    ChartSeries series = new ChartSeries();
    IntStream.range(0, 12)
            .forEach(x -> {
              Month month = startDate.plusMonths(x).getMonth(); 
              series.set(ChartHelper.getInstance().buildSeriesLabel(month, startDate.getYear(), isSwedish),
                      dataMap.get(month.name()) == null ? 0 : dataMap.get(month.name()));
            });

    model.addSeries(series);
    model.setTitle(CommonText.getInstance().getMonthChartTitle(isSwedish));
    ChartHelper.getInstance().addOptions(model, true, isSwedish);
    return model;
  }
   
  /**
   * Create a year chart
   * @param model - BarChartModel
   * @param dataMap - Map<String, Integer>
   * @param isSwedish - boolean
   */
  public void createYearChart(BarChartModel model, Map<String, Integer> dataMap, boolean isSwedish) {
    ChartSeries series = new ChartSeries();
    dataMap.entrySet().stream().forEach(e -> {
      series.set(e.getKey(), e.getValue());
    });

    model.addSeries(series);
    model.setTitle(CommonText.getInstance().getYearChartTitle(isSwedish));
    ChartHelper.getInstance().addOptions(model, false, isSwedish);
  }
}
