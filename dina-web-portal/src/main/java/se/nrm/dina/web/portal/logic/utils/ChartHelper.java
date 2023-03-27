package se.nrm.dina.web.portal.logic.utils;

import java.time.Month;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import se.nrm.dina.web.portal.utils.CommonText;
import se.nrm.dina.web.portal.utils.DateHelper;
import se.nrm.dina.web.portal.utils.MonthElement;

/**
 *
 * @author idali
 */
@Slf4j
public class ChartHelper {

    private final String emptySpace = " ";

    private static ChartHelper instance = null;
    private static final CommonText COMMON = CommonText.getInstance();
    
    private StringBuilder seriesLabelSb;
    private int year;
    
    private Axis xAxis;
    private Axis yAxis;
    
    private ChartSeries newSeries;

    public static ChartHelper getInstance() {
        synchronized (DateHelper.class) {
            if (instance == null) {
                instance = new ChartHelper();
            }
        }
        return instance;
    }

    /**
     * Build labels for month chart series
     *
     * @param month - Month
     * @param yearOfStartDate - int
     * @param isSwedish - boolean
     *
     * @return String
     */
    public String buildSeriesLabel(Month month, int yearOfStartDate, boolean isSwedish) {
        year = month.getValue() <= DateHelper.getInstance().getMonthOfToday()
                ? DateHelper.getInstance().getYearOfToday() : yearOfStartDate;
        seriesLabelSb = new StringBuilder();
        seriesLabelSb.append(MonthElement.valueOfShortName(month.name(), isSwedish));
        seriesLabelSb.append(emptySpace);
        seriesLabelSb.append(year);

        return seriesLabelSb.toString();
    }

    /**
     * Add chart options
     *
     * @param model - BarChartModel
     * @param isMonth - boolean
     * @param isSwedish - boolean
     */
    public void addOptions(BarChartModel model, boolean isMonth, boolean isSwedish) {
        model.setShowPointLabels(true);
        model.setShowDatatip(false);

        xAxis = model.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
        xAxis.setLabel(isMonth
                ? CommonText.getInstance().getMonthChartXAxisLabel(isSwedish)
                : CommonText.getInstance().getYearChartXAxisLabel(isSwedish));

        yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel(COMMON.getChartYAxisLabel(isSwedish));
    }

    /**
     * Change month chart language
     *
     * @param chart - BarChartModel
     * @param isSwedish - boolean
     * @return - BarChartModel
     */
    public BarChartModel changeMonthChartLanguage(BarChartModel chart, boolean isSwedish) {
        log.info("changeMonthChartLanguage : {}", isSwedish);

        newSeries = new ChartSeries();
        chart.getSeries().get(0)
                .getData()
                .entrySet()
                .stream()
                .forEach(e -> { 
                    newSeries.set(MonthElement.changeMonthLanguageWithYear((String) e.getKey(), isSwedish),
                            e.getValue());
                });

        chart = new BarChartModel();
        chart.addSeries(newSeries);
        chart.setTitle(CommonText.getInstance().getMonthChartTitle(isSwedish));
        addOptions(chart, true, isSwedish);
        return chart;
    }

    /**
     * Change year chart language
     *
     * @param chart - BarChartModel
     * @param isSwedish - boolean
     * @return - BarChartModel
     */
    public BarChartModel changeYearChartLanguage(BarChartModel chart, boolean isSwedish) {
        chart.setTitle(CommonText.getInstance().getYearChartTitle(isSwedish));
        addOptions(chart, false, isSwedish);
        return chart;
    }
}
