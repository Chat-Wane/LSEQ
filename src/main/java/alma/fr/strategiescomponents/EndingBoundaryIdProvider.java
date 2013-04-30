package alma.fr.strategiescomponents;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.strategiescomponents.boundary.IBoundary;

import com.google.inject.Inject;

/** boundary- **/
public class EndingBoundaryIdProvider implements IIdProviderStrategy {

	private Random rand = new Random();

	@Inject
	private IBoundary boundary;

	@Inject
	public EndingBoundaryIdProvider(IBoundary boundary) {
		this.boundary = boundary;
	}

	public Iterator<Positions> generateIdentifiers(Positions p, Positions q,
			Integer N, Replica rep, BigInteger interval, int index) {
		ArrayList<Positions> positions = new ArrayList<Positions>();

		// #0 process the interval for random
		BigInteger step = interval.divide(BigInteger.valueOf(N));

		step = (step.min(boundary.getBoundary(index))).max(BigInteger
				.valueOf(1));

		int prevBitLength = p.getD().bitLength() - 1;
		int nextBitLength = q.getD().bitLength() - 1;

		int bitBaseSum = LogootEngine.base.getSumBit(index);

		// #1 truncate or add
		// #1a: on previous digit
		// if (prevBitLength < bitBaseSum): Add 0
		// if (prevBitLength > bitBaseSum): truncate
		BigInteger prev = p.getD().shiftLeft(bitBaseSum - prevBitLength);
		// #2a: on next digit
		BigInteger next = q.getD().shiftLeft(bitBaseSum - nextBitLength);

		// #2b: compute particular case: p>=q at depth "index"
		if (next.compareTo(prev) < 0) {
			// #1c: search the common root & add one
			int i = 1;
			int sumBitI = LogootEngine.base.getSumBit(i);
			while (prev.shiftRight(prev.bitLength() - 1 - sumBitI).equals(
					next.shiftRight(next.bitLength() - 1 - sumBitI))
					&& (next.bitLength() - 1 - sumBitI >= 0)) {
				++i;
				sumBitI = LogootEngine.base.getSumBit(i);
			} // the common root is defined until a depth of i-1
				// #1d: get the common root and add 1
			next = (next.shiftRight(next.bitLength() - 1
					- LogootEngine.base.getSumBit(i - 1))).add(BigInteger.ONE);
			// #1e: append some 0
			next = next.shiftLeft(bitBaseSum - next.bitLength() + 1);
		}

		BigInteger r = next;

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
			BigInteger newR = LogootEngine.base.sub(r, randomInt);
			rep.setClock(rep.getClock() + 1);
			ArrayList<Integer> s = getS(newR, index, p, q, rep);
			ArrayList<Integer> c = getC(newR, index, p, q, rep);
			Positions tempPositions = new Positions(newR,
					LogootEngine.base.getSumBit(index), s, c);
			positions.add(tempPositions);
			r = LogootEngine.base.sub(r, step);
		}
		Collections.reverse(positions);
		return positions.iterator();
	}

	public ArrayList<Integer> getS(BigInteger r, Integer index, Positions p,
			Positions q, Replica rep) {
		ArrayList<Integer> sources = new ArrayList<Integer>();
		BigInteger tempR = r.setBit(LogootEngine.base.getSumBit(index));
		int bitLength = tempR.bitLength() - 1;

		for (int i = 0; i < index; ++i) {
			// #1 truncate the r to get the i th value
			int sumBit = LogootEngine.base.getSumBit(i + 1);
			BigInteger mask = BigInteger.valueOf(2)
					.pow(LogootEngine.base.getBitBase(i + 1))
					.subtract(BigInteger.ONE);
			BigInteger valR = tempR.shiftRight(bitLength - sumBit).and(mask); // bitLength-sumBit
																				// >=0
			// #2 truncate previous value the same way
			BigInteger valP = p.getD()
					.shiftRight(p.getD().bitLength() - 1 - sumBit).and(mask);
			if (p.getC().size() > i && valR.equals(valP)) { // copy p site
				sources.add(p.getS().get(i));
			} else {
				BigInteger valQ = q.getD()
						.shiftRight(q.getD().bitLength() - 1 - sumBit)
						.and(mask);
				if (q.getC().size() > i && valR.equals(valQ)) { // copy q site
					sources.add(q.getS().get(i));
				} else { // copy our own source
					sources.add(new Integer(rep.getId()));
				}
			}
		}
		return sources;
	}

	public ArrayList<Integer> getC(BigInteger r, Integer index, Positions p,
			Positions q, Replica rep) {
		ArrayList<Integer> clocks = new ArrayList<Integer>();
		BigInteger tempR = r.setBit(LogootEngine.base.getSumBit(index));
		int bitLength = tempR.bitLength() - 1;

		for (int i = 0; i < index; ++i) {
			// #1 truncate the r to get the i th value
			int sumBit = LogootEngine.base.getSumBit(i + 1);
			BigInteger mask = BigInteger.valueOf(2)
					.pow(LogootEngine.base.getBitBase(i + 1))
					.subtract(BigInteger.ONE);
			BigInteger valR = tempR.shiftRight(bitLength - sumBit).and(mask); // bitLength-sumBit
																				// >=0
			// #2 truncate previous value the same way
			BigInteger valP = p.getD()
					.shiftRight(p.getD().bitLength() - 1 - sumBit).and(mask);
			// if valP = 0, it means that size is lower, 0 is not allowed by
			// allocating strategy, so no need for further processing dan
			// compare valuez
			if (p.getC().size() > i && valR.equals(valP)) { // copy p site
				clocks.add(p.getC().get(i));
			} else {
				BigInteger valQ = q.getD()
						.shiftRight(q.getD().bitLength() - 1 - sumBit)
						.and(mask);
				if (q.getC().size() > i && valR.equals(valQ)) { // copy q site
					clocks.add(q.getC().get(i));
				} else { // copy our own source
					clocks.add(new Integer(rep.getClock()));
				}
			}
		}
		return clocks;
	}
}
