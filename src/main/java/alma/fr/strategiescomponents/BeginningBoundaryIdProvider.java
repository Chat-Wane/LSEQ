package alma.fr.strategiescomponents;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

import javax.swing.text.Position;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.strategiescomponents.boundary.IBoundary;

import com.google.inject.Inject;

public class BeginningBoundaryIdProvider implements IIdProviderStrategy {

	@Inject
	private IBase base;

	@Inject
	private IBoundary boundary;

	@Inject
	public BeginningBoundaryIdProvider(IBase base, IBoundary boundary) {
		this.base = base;
		this.boundary = boundary;
	}

	public Iterator<Positions> generateIdentifiers(Positions p, Position q,
			Integer N, Replica rep, BitSet interval, int index) {

		ArrayList<Positions> positions = new ArrayList<Positions>();

		//#0 process the interval for random
		// BigInteger step = interval.divide(BigInteger.valueOf(N));
		// step = (step.min(boundary.getBoundary(index))).max(BigInteger
		// .valueOf(1));

		//#1 Truncate tail
		BitSet r = (BitSet) p;
		Integer size = p.size();
		// while (size > index) {
		// r = r.shiftRight(base.getBitBase(size));
		// --size;
		// }

		//while (size <= index) {
		// r = r.multiply(base.getBase(size));
		// ++size;
		// }

		//#2 create position by adding a random value; N times
		// for (int j = 0; j < N; ++j) {
		//
		// BigInteger randomInt;
		//
		// // Random
		// if (!(step.compareTo(BigInteger.valueOf(1)) == 1)) { // step <= 1
		// randomInt = BigInteger.valueOf(1);
		// } else {
		// do {
		// randomInt = new BigInteger(step.subtract(
		// BigInteger.valueOf(1)).bitLength(), rand);
		//
		// } while (randomInt.compareTo(step.subtract(BigInteger
		// .valueOf(1))) >= 0);
		// randomInt = randomInt.add(BigInteger.valueOf(1));
		// }
		// // Construct
		// BigInteger newR = base.add(r, randomInt);
		// rep.setMyClock(rep.getClock() + 1);
		// Positions tempPositions = new Positions(newR, rep);
		// positions.add(tempPositions);
		//
		// r = BaseDouble.add(r, index, index, step);
		// }

		return positions.iterator();
	}

}
