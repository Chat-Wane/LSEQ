package alma.fr;

import java.math.BigInteger;
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
import alma.fr.modules.DoubleModule;
import alma.fr.modules.GreedDoubleModule;
import alma.fr.strategychoicecomponents.IStrategyChoice;
import alma.fr.view.MultipleChartView;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App2 {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");

		Float[] result;
		Injector injector;
		Injector injector2;

		LogootEngine logootEngine;
		LogootEngine logootEngine2;
		WikipediaDocument wd = new WikipediaDocument();
		WikipediaDocument wd2 = new WikipediaDocument();

		/*********************************/

		// injector = Guice.createInjector(new WeissModule());
		// injector = Guice.createInjector(new GreedModule());
		// injector = Guice.createInjector(new DoubleModule());
		// injector = Guice.createInjector(new GreedDoubleModule());
		injector = Guice.createInjector(new DoubleModule());
		injector2 = Guice.createInjector(new GreedDoubleModule());

		logootEngine = injector.getInstance(LogootEngine.class);
		logootEngine2 = injector2.getInstance(LogootEngine.class);

		logootEngine.setReplica(new Replica());
		logootEngine2.setReplica(new Replica());

		String prefix = "en";
		// String pageName = "Nicolas_Sarkozy";
		String pageName = "Template_talk:Did_you_know";
		//String pageName="Liste_des_bureaux_de_poste_français_classés_par_oblitération_Petits_Chiffres";
		//String pageName="Résultats_des_recensements_linguistiques_des_communes_à_facilités_linguistiques";
		Integer nRev = 200;
		URL url;

		/*
		 * System.setProperty("http.proxyHost",
		 * "proxy.ensinfo.sciences.univ-nantes.prive");
		 * System.setProperty("http.proxyPort", "3128");
		 */
		System.setProperty("http.proxyHost", "192.168.50.2");
		System.setProperty("http.proxyPort", "3128");

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

			IStrategyChoice wsc = logootEngine.getStrategyChoice();
			Positions first = logootEngine.getIdTable().get(1);
			int i = 1;

			ArrayList<String> curveNames = new ArrayList<String>();
			result = avgAndMaxSize(logootEngine.getIdTable());
//			curveNames.add("Id-length (m=" + df.format(result[0]) + ")");
			result = avgAndMaxBitSizeDouble(logootEngine.getIdTable(),
					logootEngine.getBase());
			curveNames.add("Weiss-bitLength (m=" + result[0] + ")");

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
			//cv.getLengths().add(longueurList);
			cv.getLengths().add(longueurBitList);
			cv.setDatas(dateList);

			// ////////////////////
			// 2nd Logoot engine to compare

			wd2.setUrl(url);
			wd2.run(logootEngine2);

			System.out.println("==== " + logootEngine2.getIdTable().size()
					+ " ====");
			result = avgAndMaxSize(logootEngine2.getIdTable());
			System.out.println("EditingModule avg : " + result[0]);
			System.out.println("EditingModule max : " + result[1]);
			result = avgAndMaxBitSizeDouble(logootEngine2.getIdTable(),
					logootEngine2.getBase());
			System.out.println("EditingModule avg (bit) : " + result[0]);
			System.out.println("EditingModule max (bit) : " + result[1]);

			wsc = logootEngine2.getStrategyChoice();
			first = logootEngine2.getIdTable().get(1);
			i = 1;

			result = avgAndMaxSize(logootEngine2.getIdTable());
			//curveNames.add("Id-length (m=" + df.format(result[0]) + ")");
			result = avgAndMaxBitSizeDouble(logootEngine2.getIdTable(),
					logootEngine2.getBase());
			curveNames.add("Double-bitLength (m=" + result[0] + ")");

			dateList = new ArrayList<Integer>();
			longueurList = new ArrayList<Integer>();
			longueurBitList = new ArrayList<Integer>();

			while (wsc.getSpectrum().get(first).getNext() != null) {
				longueurList.add(logootEngine2.getIdTable().get(i).size());

				tempsum = 0;
				for (int j = 0; j < first.size(); ++j) {
					tempsum += logootEngine2.getBase().getBase(j).bitLength();
				}
				longueurBitList.add(tempsum);

				dateList.add(wsc.getSpectrum().get(first).getDate());

				first = wsc.getSpectrum().get(first).getNext();
				++i;
			}

			longueurList.add(logootEngine2.getIdTable().get(i).size());

			tempsum = 0;
			for (int j = 0; j < first.size(); ++j) {
				tempsum += logootEngine2.getBase().getBase(j).bitLength();
			}
			longueurBitList.add(tempsum);

			//dateList.add(wsc.getSpectrum().get(first).getDate());

			//cv.getLengths().add(longueurList);
			cv.getLengths().add(longueurBitList);
			//cv.setDatas(dateList);

			cv.setOpName(pageName);
			cv.setLegend(curveNames);
			cv.setNote("base : 2^64 (Weiss), 2^(4+depth) (Double);\n" +
					"strategy : boundary+ (both);\n" +
					"boundary : 1,000,000 (Weiss), 10 (Double).");
			
			cv.run();
			//cv.export();
			cv.setVisible(true);
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Float[] avgAndMaxSize(List<Positions> logootIdTable) {
		BigInteger[] tempResult = { new BigInteger("0"), new BigInteger("0") };
		Float[] result = { 0f, 0f };

		for (Positions p : logootIdTable) {
			tempResult[0] = tempResult[0].add(new BigInteger("" + p.size()));

			if (p.size() > result[1]) {
				result[1] = (float) p.size();
			}
		}
		tempResult = tempResult[0].divideAndRemainder(new BigInteger(""
				+ logootIdTable.size()));
		result[0] = tempResult[0].intValue() + (float) tempResult[1].intValue()
				/ logootIdTable.size();
		return result;
	}

	public static Float[] avgAndMaxBitSizeWeiss(List<Positions> logootIdTable,
			IBase base) {
		BigInteger[] tempResult = { new BigInteger("0"), new BigInteger("0") };
		Float[] result = { 0f, 0f };

		for (Positions p : logootIdTable) {
			tempResult[0] = tempResult[0].add(new BigInteger(""
					+ (base.getBase(1).bitLength() - 1) * p.size()));

			if ((base.getBase(1).bitLength() - 1) * p.size() > result[1]) {
				result[1] = (float) (base.getBase(1).bitLength() - 1)
						* p.size();
			}
		}
		tempResult = tempResult[0].divideAndRemainder(new BigInteger(""
				+ logootIdTable.size()));
		result[0] = tempResult[0].intValue() + (float) tempResult[1].intValue()
				/ logootIdTable.size();
		return result;
	}

	public static Float[] avgAndMaxBitSizeDouble(List<Positions> logootIdTable,
			IBase base) {
		BigInteger[] tempResult = { new BigInteger("0"), new BigInteger("0") };
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			Integer tempsum = 0;
			for (int i = 0; i < p.size(); ++i) {
				tempsum += base.getBase(i).bitLength();
			}
			tempResult[0] = tempResult[0].add(new BigInteger("" + tempsum));

			if (tempsum > result[1]) {
				result[1] = (float) tempsum;
			}
		}
		tempResult = tempResult[0].divideAndRemainder(new BigInteger(""
				+ logootIdTable.size()));
		result[0] = tempResult[0].intValue() + (float) tempResult[1].intValue()
				/ logootIdTable.size();
		return result;
	}

}
