package alma.fr.strategiescomponents;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

	public Iterator<Positions> generateLineIdentifiers(Positions p,
			Positions q, Integer N, Replica rep) {

		ArrayList<Positions> positions = new ArrayList<Positions>();

		ArrayList<BigInteger> qprefix = q.prefix(q.size());
		ArrayList<BigInteger> pprefix = p.prefix(p.size());

		Integer index = 0;
		BigInteger interval = new BigInteger("0");
		BigInteger nBigInteger = new BigInteger(N.toString());
		while (interval.compareTo(nBigInteger) == -1) {
			++index;

			interval = base.count(qprefix, index)
					.subtract(base.count(pprefix, index))
					.subtract(new BigInteger("1"));
		}

		BigInteger step = interval.divide(nBigInteger);
		step = step.min(boundary.getBoundary(index));
		step = step.max(new BigInteger("1"));

		ArrayList<BigInteger> r = p.prefix(index);
		Random rand = new Random();
		for (int j = 0; j < N; ++j) {
			Positions tempPositions = new Positions();
			BigInteger randomInt;

			if (!(step.compareTo(new BigInteger("1")) == 1)) { // step <= 1
				randomInt = new BigInteger("1");
			} else {
				do {
					randomInt = new BigInteger(step.subtract(
							new BigInteger("1")).bitLength(), rand);

				} while (randomInt
						.compareTo(step.subtract(new BigInteger("1"))) >= 0);
				randomInt = randomInt.add(new BigInteger("1"));
			}

			base.add(r, randomInt);

			tempPositions.constructIdentifier(r, p, q, rep);
			positions.add(tempPositions);

			base.add(r, step.subtract(randomInt));
		}

		return positions.iterator();
	}

}
