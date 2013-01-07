package alma.fr;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.documentgenerator.WikipediaDocument;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.modules.GreedDoubleModule;
import alma.fr.strategychoicecomponents.RoundRobinStrategyChoice;
import alma.fr.view.MultipleChartView;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class RunWiki {

	public String prefix = "en";
	public String pageName = "";
	public Integer nRev = 200;
	public DecimalFormat df = new DecimalFormat("#.##");

	public void run() {

		Float[] result;
		Injector injector;
		LogootEngine logootEngine;
		WikipediaDocument wd = new WikipediaDocument();

		/*********************************/

		// injector = Guice.createInjector(new WeissModule());
		// injector = Guice.createInjector(new GreedModule());
		// injector = Guice.createInjector(new DoubleModule());
		 injector = Guice.createInjector(new GreedDoubleModule());
		//injector = Guice.createInjector(new EditingModule());

		logootEngine = injector.getInstance(LogootEngine.class);
		logootEngine.setReplica(new Replica());

		URL url;

		try {
			url = new URL(
					"http://"
							+ prefix
							+ ".wikipedia.org/w/api.php?action=query&prop=revisions&titles="
							+ pageName
							+ "&rvprop=timestamp|ids|user|comment|content&rvdir=newer&format=xml&rvlimit="
							+ nRev);

			wd.setUrl(url);
			wd.run(logootEngine);

			System.out.println("==== " + logootEngine.getIdTable().size()
					+ " ====");
			result = avgAndMaxSize(logootEngine.getIdTable());
			System.out.println("EditingModule avg : " + result[0]);
			System.out.println("EditingModule max : " + result[1]);
			result = avgAndMaxBitSizeDouble(logootEngine.getIdTable(),
					logootEngine.getBase());
			System.out.println("EditingModule avg (bit) : " + result[0]);
			System.out.println("EditingModule max (bit) : " + result[1]);

			RoundRobinStrategyChoice wsc = (RoundRobinStrategyChoice) logootEngine
					.getStrategyChoice();
			Positions first = logootEngine.getIdTable().get(1);
			int i = 1;

			ArrayList<String> curveNames = new ArrayList<String>();
			result = avgAndMaxSize(logootEngine.getIdTable());
			curveNames.add("Id-length (m=" + df.format(result[0]) + ")");
			result = avgAndMaxBitSizeDouble(logootEngine.getIdTable(),
					logootEngine.getBase());
			curveNames.add("Id-bitLength (m=" +  df.format(result[0]) + ")");

			ArrayList<Integer> dateList = new ArrayList<Integer>();
			ArrayList<Integer> longueurList = new ArrayList<Integer>();
			ArrayList<Integer> longueurBitList = new ArrayList<Integer>();

			while (wsc.getSpectrum().get(first).getNext() != null) {
				longueurList.add(logootEngine.getIdTable().get(i).size());

				Integer tempsum = 0;
				for (int j = 0; j < first.size(); ++j) {
					tempsum += logootEngine.getBase().getBase(j).bitLength();
				}
				longueurBitList.add(tempsum);

				dateList.add(wsc.getSpectrum().get(first).getDate());

				first = wsc.getSpectrum().get(first).getNext();
				++i;
			}

			longueurList.add(logootEngine.getIdTable().get(i).size());

			Integer tempsum = 0;
			for (int j = 0; j < first.size(); ++j) {
				tempsum += logootEngine.getBase().getBase(j).bitLength();
			}
			longueurBitList.add(tempsum);

			dateList.add(wsc.getSpectrum().get(first).getDate());

			MultipleChartView cv = new MultipleChartView();
			cv.getLengths().add(longueurList);
			cv.getLengths().add(longueurBitList);
			cv.setDatas(dateList);

			cv.setPageName(pageName);
			cv.setOpName(pageName);
			cv.setLegend(curveNames);
			cv.run();
			//cv.setVisible(true);
			
			cv.export();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	public static Float[] avgAndMaxSize(List<Positions> logootIdTable) {
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			result[0] += (float) p.size() / logootIdTable.size();

			if (p.size() > result[1]) {
				result[1] = (float) p.size();
			}
		}
		return result;
	}

	public static Float[] avgAndMaxBitSizeWeiss(List<Positions> logootIdTable) {
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			result[0] += (float) (10 * p.size()) / logootIdTable.size();

			if (10 * p.size() > result[1]) {
				result[1] = (float) 10 * p.size();
			}
		}
		return result;
	}

	public static Float[] avgAndMaxBitSizeDouble(List<Positions> logootIdTable,
			IBase base) {
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			Integer tempsum = 0;
			for (int i = 0; i < p.size(); ++i) {
				tempsum += base.getBase(i).bitLength();
			}
			result[0] += (float) tempsum / logootIdTable.size();

			if (tempsum > result[1]) {
				result[1] = (float) tempsum;
			}
		}
		return result;
	}

}
