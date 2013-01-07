package alma.fr.documentgenerator;

import java.util.ArrayList;

import difflib.Chunk;
import difflib.Delta;
import difflib.InsertDelta;

/**
 * insert N lines in the beginning of doc
 * 
 */
public class BeginningGenerator implements IDeltasGenerator {

	public ArrayList<Delta> getDeltas(Integer N) {

		ArrayList<Delta> deltas = new ArrayList<Delta>();

		/* Create chunk of inserted line */
		ArrayList<String> insertContent = new ArrayList<String>();
		for (int i = 0; i < N; ++i) {
			//insertContent.add("" + i + " " + DocumentSimulator.nbLine);
			insertContent.add(""+ DocumentSimulator.nbLine);
		}
		Chunk insertChunk = new Chunk(0, insertContent); // at beginning

		ArrayList<String> insertOriginal = new ArrayList<String>();
		Chunk originalChunk = new Chunk(0, insertOriginal);

		Delta d = new InsertDelta(originalChunk, insertChunk);

		deltas.add(d);
		return deltas;
	}

}
