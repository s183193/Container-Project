

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class comparisonlinePlots extends plot implements observer {
	
	private ChartPanel chartPanel;
	private ContainerSelectionPanels csp;
	private TopMain topmain;
	
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	public comparisonlinePlots(String plottitle) {
		super(plottitle);
		ArrayList<Integer> e = new ArrayList<Integer>();
	JFreeChart xylineChart = ChartFactory.createXYLineChart("Changes in Container's Enviornment",
			"Time elapsed from Journey", "changes", create2Dataset(e,e,e), PlotOrientation.VERTICAL, true, true,
			false);

	ChartPanel chartPanel = new ChartPanel(xylineChart);
	chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
	


	//display();
	
	}
	public comparisonlinePlots(String plottitle, ArrayList<Integer> t, ArrayList<Integer> p, ArrayList<Integer> h, ContainerSelectionPanels csp, TopMain topmain) {
		super(plottitle);

		this.csp = csp;
		this.topmain = topmain;
	JFreeChart xylineChart = ChartFactory.createXYLineChart("Changes in Container's Enviornment",
			"Time elapsed from Journey", "changes", create2Dataset(t, p, h), PlotOrientation.VERTICAL, true, true,
			false);

	chartPanel = new ChartPanel(xylineChart);
	chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
	final XYPlot plot = xylineChart.getXYPlot();

	XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	renderer.setSeriesPaint(0, Color.RED);
	renderer.setSeriesPaint(1, Color.GREEN);
	renderer.setSeriesPaint(2, Color.YELLOW);
	renderer.setSeriesStroke(0, new BasicStroke(4.0f));
	renderer.setSeriesStroke(1, new BasicStroke(3.0f));
	renderer.setSeriesStroke(2, new BasicStroke(2.0f));
	plot.setRenderer(renderer);
	setContentPane(chartPanel);

//	display();
	
	}

	public void update(ArrayList<Integer> t, ArrayList<Integer> p, ArrayList<Integer> h) {
		JFreeChart xylineChart = ChartFactory.createXYLineChart("Changes in Container's Enviornment",
				"Time elapsed from Journey", "changes", create2Dataset(t, p, h), PlotOrientation.VERTICAL, true, true,
				false);

		chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		setContentPane(chartPanel);
		
		csp.updateAllPlots(topmain);
		
//	display();
	}
}
