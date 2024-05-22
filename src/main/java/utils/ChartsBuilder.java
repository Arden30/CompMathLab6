package utils;

import model.Solution;
import model.Task;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class ChartsBuilder extends JFrame {
    public ChartsBuilder(Solution solution, Task task) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries pointsSeries = new XYSeries("Точное решение");
        for (double x = task.x0(); x <= task.xn(); x += solution.h()) {
            pointsSeries.add(x, task.equation().solution(x, task.x0(), task.y0()));
        }
        dataset.addSeries(pointsSeries);

        XYSeries solSeries = new XYSeries("Приближенное решение");
        for (int i = 0; i < solution.yList().size(); i++) {
            solSeries.add(solution.xList().get(i), solution.yList().get(i));
        }
        dataset.addSeries(solSeries);

        // Создание графика
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графики", "X", "Y",
                dataset
        );

        chart.getXYPlot().getDomainAxis().setRange(solution.xList().get(0) - 1, solution.xList().get(solution.xList().size() - 1) + 1);
        chart.getXYPlot().getRangeAxis().setRange(solution.yList().get(0) - 1, solution.yList().get(solution.yList().size() - 1) + 1);

        NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();

        xAxis.setTickUnit(new NumberTickUnit(1));
        yAxis.setTickUnit(new NumberTickUnit(1));

        // Настройка стилей линий осей координат
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainZeroBaselinePaint(Color.BLACK); // черный цвет для вертикальной оси 0
        plot.setRangeZeroBaselinePaint(Color.BLACK); // черный цвет для горизонтальной оси 0

        // Устанавливаем толщину линии для функций
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setStroke(new BasicStroke(2)); // Установите желаемую толщину линии здесь

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1100, 630));
        add(chartPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
