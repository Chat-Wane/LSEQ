package alma.fr;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alma.fr.data.Positions;
import alma.fr.documentgenerator.BeginningGenerator;
import alma.fr.documentgenerator.DocumentSimulator;
import alma.fr.documentgenerator.EndingGenerator;
import alma.fr.documentgenerator.VGenerator;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.modules.GreedRandDoubleModule;
import alma.fr.strategychoicecomponents.FakeListNode;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		Injector injector;
		LogootEngine logootEngine;
		DocumentSimulator ds;

		/*********************************/

		// injector = Guice.createInjector(new WeissModule());
		// injector = Guice.createInjector(new GreedModule());
		// injector = Guice.createInjector(new DoubleModule());
		// injector = Guice.createInjector(new GreedDoubleModule());
		injector = Guice.createInjector(new GreedRandDoubleModule());

		logootEngine = injector.getInstance(LogootEngine.class);
		logootEngine.setReplica(42); // whatever

		BeginningGenerator bg = new BeginningGenerator();
		EndingGenerator eg = new EndingGenerator();
		VGenerator vg = new VGenerator();
		ds = new DocumentSimulator(vg);

		while (true) {
			ds.setNbPatch(10000);
			ds.run(logootEngine);
			System.out.println("=====" + DocumentSimulator.getNbLine()
					+ "=====");

			Float[] results = avgAndMaxBitSize((ArrayList<Positions>) logootEngine
							.getIdTable());
			System.out.println("avg bitSize = "+ results[0]);
			System.out.println("max bitSize = "+ results[1]);

			if (DocumentSimulator.getNbLine() == 100000) {
//				for (int i=0;i<100000;++i){// print some idz
//					System.out.println(logootEngine
//					.getIdTable().get(i));
//				}
				ArrayList<Positions> idTable = (ArrayList<Positions>) logootEngine
						.getIdTable();
				HashMap<Positions, FakeListNode> spectrum = logootEngine
						.getStrategyChoice().getSpectrum();
				writeFile("3meow3", idTable, spectrum);
			}
		}

	}

	// / / / / ///// / / // / // / / // / / // / // / / /
	public static Float[] avgAndMaxBitSize(List<Positions> logootIdTable) {
		BigInteger[] tempResult = { BigInteger.ZERO, BigInteger.ZERO };
		Float[] result = { 0f, 0f };
		for (Positions p : logootIdTable) {
			tempResult[0] = tempResult[0].add(BigInteger.valueOf(p.getD()
					.bitLength()));

			if (p.getD().bitLength() > result[1]) {
				result[1] = (float) p.getD().bitLength();
			}
		}
		tempResult = tempResult[0].divideAndRemainder(BigInteger
				.valueOf(logootIdTable.size()));
		result[0] = tempResult[0].intValue() + (float) tempResult[1].intValue()
				/ logootIdTable.size();
		return result;
	}

	// / / // // / / // / // / / / /// / // / // // / //
	public static void writeFile(String name, ArrayList<Positions> idTable,
			HashMap<Positions, FakeListNode> spectrum) {
		String page = name.replace("/", "_").replace("\\", "_");
		try {
			PrintWriter out = new PrintWriter(new FileWriter(page + ".dat"));
			int docSize = idTable.size();
			for (int i = 0; i < docSize; ++i) {
				String lineS = new String();
				lineS = spectrum.get(idTable.get(i)).getDate() + " "
						+ idTable.get(i).getD().bitLength();
				out.println(lineS);
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
