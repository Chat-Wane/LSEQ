package alma.fr;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.documentgenerator.BeginningGenerator;
import alma.fr.documentgenerator.DocumentSimulator;
import alma.fr.documentgenerator.EndingGenerator;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.Replica;
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
//		// ID STRATEGIEZ
//		try {
//			Thread.currentThread();
//			// do what you want to do before sleeping
//			Thread.sleep(10000);// sleep for 1000 ms
//			// do what you want to do after sleeptig
//		} catch (InterruptedException ie) {
//			// If this thread was intrrupted by nother thread
//		}

		Injector injector;
		LogootEngine logootEngine;
		DocumentSimulator ds;

		/*********************************/

		// injector = Guice.createInjector(new WeissModule());
		// injector = Guice.createInjector(new GreedModule());
		// injector = Guice.createInjector(new DoubleModule());
		//injector = Guice.createInjector(new GreedDoubleModule());
		 injector = Guice.createInjector(new GreedRandDoubleModule());

		logootEngine = injector.getInstance(LogootEngine.class);
		logootEngine.setReplica(new Replica());

		BeginningGenerator bg = new BeginningGenerator();
		EndingGenerator eg = new EndingGenerator();
		ds = new DocumentSimulator(eg);

		while (true) {
			ds.setNbPatch(10000);
			ds.run(logootEngine);
			System.out.println(DocumentSimulator.getNbLine());
 
			if (DocumentSimulator.getNbLine() == 10000) {
				ArrayList<Positions> idTable = (ArrayList<Positions>) logootEngine
						.getIdTable();
				HashMap<Positions, FakeListNode> spectrum = logootEngine
						.getStrategyChoice().getSpectrum();
				writeFile("3meow3", idTable, spectrum);
			}
		}

	}

	// / / / / ///// / / // / // / / // / / // / // / / /
	public static Float[] avgAndMaxBitSize(List<Positions> logootIdTable,
			IBase base) {
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
