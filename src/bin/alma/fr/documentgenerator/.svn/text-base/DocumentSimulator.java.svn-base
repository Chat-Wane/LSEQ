package alma.fr.documentgenerator;

import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.MyPatch;

public class DocumentSimulator {

	static int nbLine = 0;

	private int nbPatch = 100;

	public DocumentSimulator() {
		nbLine = 0;
	}

	public void run(LogootEngine logootEngine) {

		IDeltasGenerator deltaGenerator = new BeginningGenerator();

		for (int i = 0; i < nbPatch; ++i) {
			MyPatch patch = logootEngine.generatePatch(deltaGenerator
					.getDeltas(1));
			logootEngine.deliver(patch);
			++nbLine;
			
//System.out.println(i);
		}

	}

	public void setNbPatch(int nbPatch) {
		this.nbPatch = nbPatch;
	}

	public static int getNbLine() {
		return nbLine;
	}
}
