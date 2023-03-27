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

    private BarChartModel model;
    private ChartSeries monthSeries;
    private ChartSeries yearSeries;
    
    private Month month;
    private String monthName;
    
    /**
     * Create a month chart
     *
     * @param dataMap - Map<String, Integer>
     * @param startDate - LocalDateTime
     * @param isSwedish - boolean
     * @return BarChartModel
     */
    public BarChartModel createMonthChart(Map<String, Integer> dataMap,
            LocalDateTime startDate, boolean isSwedish) {
        log.info("createMonthChart : {}", isSwedish);

        model = new BarChartModel();
        monthSeries = new ChartSeries();
        IntStream.range(0, 12)
                .forEach((int x) -> { 
                    month = startDate.plusMonths(x).getMonth(); 
                    monthSeries.set(ChartHelper.getInstance().
                            buildSeriesLabel(month, startDate.getYear(), isSwedish),
                            getMonthName(dataMap, month));
                });

        model.addSeries(monthSeries);
        model.setTitle(CommonText.getInstance().getMonthChartTitle(isSwedish));
        ChartHelper.getInstance().addOptions(model, true, isSwedish);
        return model;
    }
    
    private int getMonthName(Map<String, Integer> dataMap, Month month) {
        monthName = month.name();
        if(dataMap.containsKey(monthName)) {
            return dataMap.get(monthName);
        }
        return 0;
    }

    /**
     * Create a year chart
     *
     * @param model - BarChartModel
     * @param dataMap - Map<String, Integer>
     * @param isSwedish - boolean
     */
    public void createYearChart(BarChartModel model, Map<String, Integer> dataMap, boolean isSwedish) {
        yearSeries = new ChartSeries();
        dataMap.entrySet().stream().forEach(e -> {
            yearSeries.set(e.getKey(), e.getValue());
        });

        model.addSeries(yearSeries);
        model.setTitle(CommonText.getInstance().getYearChartTitle(isSwedish));
        ChartHelper.getInstance().addOptions(model, false, isSwedish);
    }
}
