package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import infrastructure.TradeResult;
import infrastructure.Trader;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import gui.MainUI;
import strategy.Strategy_A;
import strategy.Strategy_B;
import strategy.Strategy_C;
import strategy.Strategy_D;

public class DataVisualizationCreator {

	private ArrayList<TradeResult> resultList;
	private ArrayList<Trader> traderList;
	private Trade trade;

	public DataVisualizationCreator(ArrayList<TradeResult> resultList, ArrayList<Trader> traderList) {
		this.traderList = traderList;
		trade = new Trade(traderList);
		this.resultList = trade.getResultList();
	}
	
	public void createCharts() {
//		createTextualOutput();
		createTableOutput();
//		createTimeSeries();
//		createScatter();
		createBar();
	}

	private void createTextualOutput() {
//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"structure.Broker Name", "Ticker List", "Strategy Name"}, 1);
//		JTable table = new JTable(dtm);
//		//table.setPreferredSize(new Dimension(600, 300));
//		dtm.e
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
//                "structure.Broker Actions",
//                TitledBorder.CENTER,
//                TitledBorder.TOP));
//		
//	
//		
//		scrollPane.setPreferredSize(new Dimension(800, 300));
//		table.setFillsViewportHeight(true);;
		
//		MainUI.getInstance().updateStats(scrollPane);
	}
	
	private void createTableOutput() {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		
		// Dummy data for demo purposes. These should come from actual fetcher
//		Object[][] data = {
//				{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"},
//				{"Trader-2", "Strategy-B", "BTC", "Sell", "200", "50.2","13-January-2022"},
//				{"Trader-3", "Strategy-C", "USDT", "Buy", "1000", "2.59","15-January-2022"},
//				{"Trader-1", "Strategy-A", "USDC", "Buy", "500", "150.3","16-January-2022"},
//				{"Trader-2", "Strategy-B", "ADA", "Sell", "200", "50.2","16-January-2022"},
//				{"Trader-3", "Strategy-C", "SOL", "Buy", "1000", "2.59","17-January-2022"},
//				{"Trader-1", "Strategy-A", "ONE", "Buy", "500", "150.3","17-January-2022"},
//				{"Trader-2", "Strategy-B", "MANA", "Sell", "200", "50.2","17-January-2022"},
//				{"Trader-3", "Strategy-C", "AVAX", "Buy", "1000", "2.59","19-January-2022"},
//				{"Trader-1", "Strategy-A", "LUNA", "Buy", "500", "150.3","19-January-2022"},
//				{"Trader-2", "Strategy-B", "FTM", "Sell", "200", "50.2","19-January-2022"},
//				{"Trader-3", "Strategy-C", "HNT", "Buy", "1000", "2.59","20-January-2022"},
//		};


		Object[][] data = trade.doTrade();
		

		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}

	private void createTimeSeries() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		//plot.mapDatasetToRangeAxis(2, 2);// 3rd dataset to 3rd y-axis
		
		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		MainUI.getInstance().updateStats(chartPanel);
	}
	
	private void createScatter() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		
		JFreeChart scatterChart = new JFreeChart("Daily Price Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

	private int frequency(ArrayList<TradeResult> list, String name) {
		int i = 0;

		for (TradeResult r : list) {
			if (r.trader.getName().equals(name)) {
				i++;
			}
		}

		return i;
	}
	
	private void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
////		Those are hard-coded values!!!!
////		You will have to come up with a proper datastructure to populate the BarChart with live data!
////		dataset.setValue(6, "Trader-1", "Strategy-A");
//		dataset.setValue(5, "Trader-2", "Strategy-B");
////		dataset.setValue(0, "Trader-3", "Strategy-E");
////		dataset.setValue(1, "Trader-4", "Strategy-C");
//		dataset.setValue(10, "Trader-5", "Strategy-D");

		for (Trader t : traderList) {
			System.out.print(frequency(resultList, t.getName()));
			System.out.print(t.getName());

			String strategyName;
			if (t.getStrategy().getClass().getName().equals("strategy.Strategy_A")) {
				strategyName = Strategy_A.getInstance().getName();
			}
			else if (t.getStrategy().getClass().getName().equals("strategy.Strategy_B")) {
				strategyName = Strategy_B.getInstance().getName();
			}
			else if (t.getStrategy().getClass().getName().equals("strategy.Strategy_C")) {
				strategyName = Strategy_C.getInstance().getName();
			}
			else {
				strategyName = Strategy_D.getInstance().getName();
			}

			System.out.print(strategyName);
			dataset.setValue(frequency(resultList, t.getName()), t.getName(), strategyName);
		}

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}
