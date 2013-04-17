package alma.fr.strategiescomponents;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.strategiescomponents.boundary.IBoundary;

import com.google.inject.Inject;

public class EndingBoundaryIdProvider implements IIdProviderStrategy {

	private Random rand = new Random();

	@Inject
	private IBase base;

	@Inject
	private IBoundary boundary;

	@Inject
	public EndingBoundaryIdProvider(IBase base, IBoundary boundary) {
		this.base = base;
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
		int nextBitCount = q.getD().bitLength() - 1;
		int diffBitCount = nextBitCount - base.getSumBit(index);

		BigInteger r = q.getD().shiftRight(diffBitCount);

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
			BigInteger newR = base.sub(r, randomInt);
			Positions tempPositions = new Positions(newR,
					base.getSumBit(index), index, rep, base);
			positions.add(tempPositions);
			r = base.sub(r, step);
		}

		Collections.reverse(positions);
		return positions.iterator();
	}

}
