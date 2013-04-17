package alma.fr.documentgenerator;

import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.MyPatch;

public class DocumentSimulator {

	static int nbLine = 0;

	private int nbPatch = 100;

	IDeltasGenerator deltaGenerator = new BeginningGenerator(); // default

	public DocumentSimulator(IDeltasGenerator generator) {
		nbLine = 0;
		deltaGenerator = generator;
	}

	public void setDeltaGenerator(IDeltasGenerator deltaGenerator) {
		this.deltaGenerator = deltaGenerator;
	}
	
	public void run(LogootEngine logootEngine) {

		for (int i = 0; i < nbPatch; ++i) {
			MyPatch patch = logootEngine.generatePatch(deltaGenerator
					.getDeltas(1));
			logootEngine.deliver(patch);
			++nbLine;

			// System.out.println(i);
		}

	}

	public void setNbPatch(int nbPatch) {
		this.nbPatch = nbPatch;
	}

	public static int getNbLine() {
		return nbLine;
	}
}
