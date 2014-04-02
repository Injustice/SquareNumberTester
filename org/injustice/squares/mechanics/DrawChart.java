package org.injustice.squares.mechanics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Azmat on 30/03/2014.
 */
public class DrawChart extends ApplicationFrame {
    private ConcurrentHashMap<Integer, Long> timeTaken;
    private Computer computer;
    private final ApplicationFrame frame = this;
    private ConcurrentHashMap<Integer, Integer> attempts;

    public DrawChart(ConcurrentHashMap<Integer, Long> timeTaken, Computer computer, ConcurrentHashMap<Integer, Integer> attempts) {
        this("Results");
        this.attempts = attempts;
        this.computer = computer;
        this.timeTaken = timeTaken;
        CategoryDataset set = createDataset();
        JFreeChart chart = createChart(set);
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(500, 270));
        setContentPane(panel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private DrawChart(String title) {
        super(title);
    }

    private CategoryDataset createDataset() {
        String series = "Results (attempts)";
        DefaultCategoryDataset set = new DefaultCategoryDataset();
        System.out.println(timeTaken.size());
        for (int i = 1; i <= timeTaken.size(); i++) {
            String appended = "";
            if (attempts.get(i) != 1) {
                appended += "(" + attempts.get(i) + ")";
            }
            set.addValue(timeTaken.get(i), series, Integer.toString(i) + appended);
        }
        set.addValue(computer.computeHighest(), series, "Highest");
        set.addValue(computer.computeLowest(), series, "Lowest");
        set.addValue(computer.computeAverage(), series, "Average");
        return set;
    }

    private JFreeChart createChart(CategoryDataset set) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Results",
                "Data",
                "Time (ms)",
                set,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0)
        );
        return chart;
    }

    public ApplicationFrame getFrame() {
        return frame;
    }
}
