package alma.fr;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import alma.fr.data.Positions;
import alma.fr.documentgenerator.WikipediaDocument;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.modules.GreedRandDoubleModule;
import alma.fr.strategychoicecomponents.IStrategyChoice;
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
		// injector = Guice.createInjector(new GreedDoubleModule());
		injector = Guice.createInjector(new GreedRandDoubleModule());

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
			result = avgAndMaxBitSize(logootEngine.getIdTable());
			System.out.println("EditingModule avg (bit) : " + result[0]);
			System.out.println("EditingModule max (bit) : " + result[1]);

			IStrategyChoice wsc = logootEngine.getStrategyChoice();
			Positions first = logootEngine.getIdTable().get(1);
			int i = 1;

			ArrayList<String> curveNames = new ArrayList<String>();
			curveNames.add("Id-bitLength (m=" + df.format(result[0]) + ")");

			ArrayList<Integer> dateList = new ArrayList<Integer>();
			ArrayList<Integer> longueurList = new ArrayList<Integer>();

			while (wsc.getSpectrum().get(first).getNext() != null) {
				longueurList.add(logootEngine.getIdTable().get(i).getD()
						.bitLength());

				dateList.add(wsc.getSpectrum().get(first).getDate());

				first = wsc.getSpectrum().get(first).getNext();
				++i;
			}

			longueurList.add(logootEngine.getIdTable().get(i).getD()
					.bitLength());

			dateList.add(wsc.getSpectrum().get(first).getDate());

			MultipleChartView cv = new MultipleChartView();
			cv.getLengths().add(longueurList);
			cv.setDatas(dateList);

			cv.setPageName(pageName);
			cv.setOpName(pageName);
			cv.setLegend(curveNames);
			cv.run();
			// cv.setVisible(true);

			cv.export();
			// cv.writeFile();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Float[] avgAndMaxBitSize(List<Positions> logootIdTable) {
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			Integer tempsum = 0;

			tempsum += p.getD().bitLength();

			result[0] += (float) tempsum / logootIdTable.size();

			if (tempsum > result[1]) {
				result[1] = (float) tempsum;
			}
		}
		return result;
	}

}
