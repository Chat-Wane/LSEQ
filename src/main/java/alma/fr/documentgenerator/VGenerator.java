package alma.fr.documentgenerator;

import java.util.ArrayList;

import difflib.Chunk;
import difflib.Delta;
import difflib.InsertDelta;

public class VGenerator implements IDeltasGenerator {

	public ArrayList<Delta> getDeltas(Integer N) {

		ArrayList<Delta> deltas = new ArrayList<Delta>();

		// insert in queue
		if (DocumentSimulator.nbLine % 2 == 0) {
			ArrayList<String> insertContentEnd = new ArrayList<String>();
			for (int i = 0; i < N; ++i) {
				insertContentEnd.add("" + i + " " + DocumentSimulator.nbLine);
			}

			Chunk insertChunkEnd = new Chunk(DocumentSimulator.nbLine,
					insertContentEnd);
			ArrayList<String> insertOriginalEnd = new ArrayList<String>();
			Chunk originalChunkEnd = new Chunk(DocumentSimulator.nbLine,
					insertOriginalEnd);

			Delta dEnd = new InsertDelta(originalChunkEnd, insertChunkEnd);

			deltas.add(dEnd);
		} else {
			// Insert in front
			ArrayList<String> insertContentBegin = new ArrayList<String>();
			for (int i = 0; i < N; ++i) {
				insertContentBegin.add("" + i + " " + DocumentSimulator.nbLine);
			}
			Chunk insertChunkBegin = new Chunk(0, insertContentBegin); // at
																		// beginning

			ArrayList<String> insertOriginalBegin = new ArrayList<String>();
			Chunk originalChunkBegin = new Chunk(0, insertOriginalBegin);

			Delta dBegin = new InsertDelta(originalChunkBegin, insertChunkBegin);

			deltas.add(dBegin);
		}

		return deltas;
	}

}
