package alma.fr.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;

public class ChartView {

	private ArrayList<Integer> datas = new ArrayList<Integer>();
	private String pageName = "";
	private String opName = "";
	private JFreeChart chart;
	private ChartFrame frame;
	
	public String getOpName() {
		return opName;
	}
	
	public String getPageName() {
		return pageName;
	}

	public ArrayList<Integer> getDatas() {
		return datas;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public void setDatas(ArrayList<Integer> datas) {
		this.datas = datas;
	}


	public void run() {
		DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		XYSeries serie = new XYSeries("insert operation", true, false);

		for (int ligne = 0; ligne < datas.size(); ++ligne) {
			serie.add(ligne, datas.get(ligne));
		}
		
		dataset.removeAllSeries();
		dataset.addSeries(serie);
		
		setChart(ChartFactory.createHistogram("Spectrum of " + opName
				, "n° line", "revision", dataset,
				PlotOrientation.VERTICAL, true, true, true));

		setFrame(new ChartFrame(pageName, getChart()));



	}
	
	public void export() {
		try {
			String page = pageName.replace("/", "_").replace("\\", "_");
			ChartUtilities.saveChartAsPNG(new File(page+".png"), getChart(), 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setVisible(boolean v){
		getFrame().pack();
		getFrame().setVisible(v);
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setFrame(ChartFrame frame) {
		this.frame = frame;
	}

	public ChartFrame getFrame() {
		return frame;
	}
}
