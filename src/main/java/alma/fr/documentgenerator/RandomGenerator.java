package alma.fr.documentgenerator;

import java.util.ArrayList;
import java.util.Random;

import difflib.Chunk;
import difflib.Delta;
import difflib.InsertDelta;

public class RandomGenerator implements IDeltasGenerator {

	public ArrayList<Delta> getDeltas(Integer N) {
		ArrayList<Delta> deltas = new ArrayList<Delta>();
		Random r = new Random();
		for (int i = 0; i < N; ++i) {

			/* Create chunk of inserted line */
			ArrayList<String> insertContent = new ArrayList<String>();
			insertContent.add("" + i + " " + DocumentSimulator.nbLine);

			Integer pos = r.nextInt(DocumentSimulator.nbLine + 1);
			Chunk insertChunk = new Chunk(pos, insertContent);

			ArrayList<String> insertOriginal = new ArrayList<String>();
			Chunk originalChunk = new Chunk(pos, insertOriginal);

			Delta d = new InsertDelta(originalChunk, insertChunk);

			deltas.add(d);
		}
		return deltas;
	}

}
