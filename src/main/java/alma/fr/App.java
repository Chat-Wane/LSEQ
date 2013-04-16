package alma.fr;

import java.math.BigInteger;
import java.util.List;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.documentgenerator.DocumentSimulator;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.modules.DoubleModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		// ID STRATEGIEZ
		try {
			// do what you want to do before sleeping
			Thread.currentThread().sleep(4000);// sleep for 1000 ms
			// do what you want to do after sleeptig
		} catch (InterruptedException ie) {
			// If this thread was intrrupted by nother thread
		}

		Float[] result;
		Injector injector;
		LogootEngine logootEngine;
		DocumentSimulator ds;

		/*********************************/

		// injector = Guice.createInjector(new WeissModule());
		// injector = Guice.createInjector(new GreedModule());
		injector = Guice.createInjector(new DoubleModule());
		// injector = Guice.createInjector(new GreedDoubleModule());
		// injector = Guice.createInjector(new GreedRandDoubleModule());
		// injector = Guice.createInjector(new EditingModule());

		logootEngine = injector.getInstance(LogootEngine.class);
		logootEngine.setReplica(new Replica());

		ds = new DocumentSimulator();

		while (DocumentSimulator.getNbLine() < 100000) {
			// 100
			ds.setNbPatch(100);
			ds.run(logootEngine);
			System.out.println("====== " + DocumentSimulator.getNbLine()
					+ " ======");
			result = avgAndMaxSize(logootEngine.getIdTable());
			System.out.println("WeissModule avg : " + result[0]);
			System.out.println("WeissModule max : " + result[1]);
			result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
					logootEngine.getBase());
			System.out.println("WeissModule avg (bit) : " + result[0]);
			System.out.println("WeissModule max (bit) : " + result[1]);
		}
		// 1000
		ds.setNbPatch(900);
		ds.run(logootEngine);
		System.out.println("====== " + DocumentSimulator.getNbLine()
				+ " ======");
		result = avgAndMaxSize(logootEngine.getIdTable());
		System.out.println("WeissModule avg : " + result[0]);
		System.out.println("WeissModule max : " + result[1]);
		result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
				logootEngine.getBase());
		System.out.println("WeissModule avg (bit) : " + result[0]);
		System.out.println("WeissModule max (bit) : " + result[1]);

		// 2000
		ds.setNbPatch(1000);
		ds.run(logootEngine);
		System.out.println("====== " + DocumentSimulator.getNbLine()
				+ " ======");
		result = avgAndMaxSize(logootEngine.getIdTable());
		System.out.println("WeissModule avg : " + result[0]);
		System.out.println("WeissModule max : " + result[1]);
		result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
				logootEngine.getBase());
		System.out.println("WeissModule avg (bit) : " + result[0]);
		System.out.println("WeissModule max (bit) : " + result[1]);

		// 3000
		ds.setNbPatch(1000);
		ds.run(logootEngine);
		System.out.println("====== " + DocumentSimulator.getNbLine()
				+ " ======");
		result = avgAndMaxSize(logootEngine.getIdTable());
		System.out.println("WeissModule avg : " + result[0]);
		System.out.println("WeissModule max : " + result[1]);
		result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
				logootEngine.getBase());
		System.out.println("WeissModule avg (bit) : " + result[0]);
		System.out.println("WeissModule max (bit) : " + result[1]);

		// 5000
		ds.setNbPatch(2000);
		ds.run(logootEngine);
		System.out.println("====== " + DocumentSimulator.getNbLine()
				+ " ======");
		result = avgAndMaxSize(logootEngine.getIdTable());
		System.out.println("WeissModule avg : " + result[0]);
		System.out.println("WeissModule max : " + result[1]);
		result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
				logootEngine.getBase());
		System.out.println("WeissModule avg (bit) : " + result[0]);
		System.out.println("WeissModule max (bit) : " + result[1]);

		// 10000
		ds.setNbPatch(5000);
		ds.run(logootEngine);
		System.out.println("====== " + DocumentSimulator.getNbLine()
				+ " ======");
		result = avgAndMaxSize(logootEngine.getIdTable());
		System.out.println("WeissModule avg : " + result[0]);
		System.out.println("WeissModule max : " + result[1]);
		result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
				logootEngine.getBase());
		System.out.println("WeissModule avg (bit) : " + result[0]);
		System.out.println("WeissModule max (bit) : " + result[1]);

		while (true) {
			ds.setNbPatch(10000);
			ds.run(logootEngine);
			System.out.println("====== " + DocumentSimulator.getNbLine()
					+ " ======");
			result = avgAndMaxSize(logootEngine.getIdTable());
			System.out.println("WeissModule avg : " + result[0]);
			System.out.println("WeissModule max : " + result[1]);
			result = avgAndMaxBitSizeWeiss(logootEngine.getIdTable(),
					logootEngine.getBase());
			System.out.println("WeissModule avg (bit) : " + result[0]);
			System.out.println("WeissModule max (bit) : " + result[1]);
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
