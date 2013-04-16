package alma.fr.view;



import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

public class MultipleChartView extends ChartView {

	private ArrayList<ArrayList<Integer>> lengths = new ArrayList<ArrayList<Integer>>();

	private ArrayList<String> legend = new ArrayList<String>();

	private String note = new String();
	
	public void setLegend(ArrayList<String> legend) {
		this.legend = legend;
	}

	public ArrayList<String> getLegend() {
		return legend;
	}
	
	public String getNote() {
		return note;
	}

	public ArrayList<ArrayList<Integer>> getLengths() {
		return lengths;
	}

	public void setLengths(ArrayList<ArrayList<Integer>> lengths) {
		this.lengths = lengths;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public void run() {

		DefaultTableXYDataset dataset = new DefaultTableXYDataset();
		XYSeries serie = new XYSeries("insert", true, false);
		for (int ligne = 0; ligne < getDatas().size(); ++ligne) {
			serie.add(ligne, getDatas().get(ligne));
		}
		dataset.addSeries(serie);
		NumberAxis Laxis = new NumberAxis("n°revision");
		XYBarRenderer revBarRenderer = new XYBarRenderer();
		XYPlot revPlot = new XYPlot();
		revPlot.setDataset(dataset);
		revPlot.setRangeAxis(Laxis);
		revPlot.setRenderer(revBarRenderer);

		XYSeriesCollection xySerieCollectiondataset = new XYSeriesCollection();
		XYLineAndShapeRenderer lengthRenderer = new XYLineAndShapeRenderer();
		int i = -1;
		int iName = 0;
		for (ArrayList<Integer> lengthSerie : lengths) {
			serie = new XYSeries(legend.get(iName));
			++iName;
			lengthRenderer.setSeriesShapesVisible(++i, false);
			// PROMPT LENGTHS DATA

			for (int ligne = 0; ligne < lengthSerie.size(); ++ligne) {
				serie.add(ligne, lengthSerie.get(ligne));
			}
			xySerieCollectiondataset.addSeries(serie);
		}
		NumberAxis Raxis = new NumberAxis("length");

		XYPlot lengthPlot = new XYPlot();
		lengthPlot.setDataset(xySerieCollectiondataset);
		lengthPlot.setRangeAxis(Raxis);
		lengthRenderer.setSeriesPaint(2, Color.MAGENTA);
		lengthPlot.setRenderer(lengthRenderer);

		CombinedDomainXYPlot mainPlot = new CombinedDomainXYPlot();
		mainPlot.add(revPlot);
		mainPlot.add(lengthPlot);

		setChart(new JFreeChart("Spectrum of " + getOpName(), null, mainPlot,
				false));

		final LegendItemCollection legendItemsOld = mainPlot.getLegendItems();

		LegendItemSource source = new LegendItemSource() {
			public LegendItemCollection getLegendItems() {
				LegendItemCollection lic = new LegendItemCollection();
				int itemCount = legendItemsOld.getItemCount();
				for (int i = 0; i < itemCount; i++) {
					lic.add(legendItemsOld.get(i));
				}
				return lic;
			}
		};

		getChart().addLegend(new LegendTitle(source));
		getChart().getLegend().setPosition(RectangleEdge.TOP);
		getChart().getLegend().getItemContainer().getBlocks();

		if (!note.isEmpty()){
		TextTitle legendText = new TextTitle("Notes : \n"+note);
		legendText.setPosition(RectangleEdge.BOTTOM);
		getChart().addSubtitle(legendText);
		}

		
		setFrame(new ChartFrame(getPageName(), getChart()));
	}

	@Override
	public void export() {
		try {
			String page = getPageName().replace("/", "_").replace("\\", "_");
			ChartUtilities.saveChartAsPNG(new File(page + ".png"), getChart(),
					1024, 768);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
