package alma.fr.strategiescomponents;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.strategiescomponents.boundary.IBoundary;

import com.google.inject.Inject;

public class BeginningBoundaryIdProvider implements IIdProviderStrategy {

	private Random rand = new Random();

	@Inject
	private IBoundary boundary;

	@Inject
	public BeginningBoundaryIdProvider(IBoundary boundary) {
		this.boundary = boundary;
	}

	public Iterator<Positions> generateIdentifiers(Positions p, Positions q,
			Integer N, Integer rep, BigInteger interval, int index) {
		ArrayList<Positions> positions = new ArrayList<Positions>();

		// #0 process the interval for random
		BigInteger step = interval.divide(BigInteger.valueOf(N));
		step = (step.min(boundary.getBoundary(index))).max(BigInteger
				.valueOf(1));

		// #1 Truncate tail or add bits
		int prevBitCount = p.getD().bitLength() - 1;
		int diffBitCount = prevBitCount - LogootEngine.base.getSumBit(index);

		BigInteger r = p.getD().shiftRight(diffBitCount);

		// #2 create position by adding a random value; N times
		for (int j = 0; j < N; ++j) {
			BigInteger randomInt;

			// Random
			if (!(step.compareTo(BigInteger.valueOf(1)) == 1)) { // step <= 1
				randomInt = BigInteger.valueOf(1);
			} else {
				do {
					randomInt = new BigInteger(step.subtract(
							BigInteger.valueOf(1)).bitLength(), rand);
				} while (randomInt.compareTo(step.subtract(BigInteger
						.valueOf(1))) >= 0);
				randomInt = randomInt.add(BigInteger.valueOf(1));
			}
			// // Construct
			BigInteger newR = LogootEngine.base.add(r, randomInt);
			Positions tempPositions = new Positions(newR,
					LogootEngine.base.getSumBit(index),index, rep);
			positions.add(tempPositions);
			r = LogootEngine.base.add(r, step);
		}

		return positions.iterator();
	}

}
