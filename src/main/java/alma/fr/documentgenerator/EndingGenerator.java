package alma.fr.documentgenerator;

import java.util.ArrayList;

import difflib.Chunk;
import difflib.Delta;
import difflib.InsertDelta;

public class EndingGenerator implements IDeltasGenerator {

	public ArrayList<Delta> getDeltas(Integer N) {
		ArrayList<Delta> deltas = new ArrayList<Delta>();

		/* Create chunk of inserted line */
		ArrayList<String> insertContent = new ArrayList<String>();
		for (int i = 0; i < N; ++i) {
			insertContent.add("" + i + " " + DocumentSimulator.nbLine);
		}

		Chunk insertChunk = new Chunk(DocumentSimulator.nbLine, insertContent);

		ArrayList<String> insertOriginal = new ArrayList<String>();
		Chunk originalChunk = new Chunk(DocumentSimulator.nbLine,
				insertOriginal);

		Delta d = new InsertDelta(originalChunk, insertChunk);

		deltas.add(d);

		return deltas;
	}

}
