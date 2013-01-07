package alma.fr.data;

import java.math.BigInteger;
import java.util.ArrayList;

import alma.fr.logootenginecomponents.Replica;

public class Positions extends ArrayList<Position> implements
		Comparable<Positions> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compareTo(Positions o) {
		BigInteger newThisClock = new BigInteger("-1");
		BigInteger newOClock = new BigInteger("-1");
		BigInteger newThisSource = new BigInteger("-1");
		BigInteger newOSource = new BigInteger("-1");
		int comp;

		if (size() > 0 && o.size() > 0) {
			for (int i = 0; i < Math.min(size(), o.size()); i++) {
				comp = get(i).getD().compareTo(o.get(i).getD());
				if (comp != 0) {
					return comp;
				}
				// Store s & c
				if (get(i).getC().compareTo(new BigInteger("-1")) != 0) { // C!=-1
					newThisClock = get(i).getC();
				} else {
					newThisClock = newThisClock.add(new BigInteger("1")); // C++
				}
				if (o.get(i).getC().compareTo(new BigInteger("-1")) != 0) { // o.C!=-1
					newOClock = o.get(i).getC();
				} else {
					newOClock = newOClock.add(new BigInteger("1")); // o.C++
				}
				if (get(i).getS().compareTo(new BigInteger("-1")) != 0) { // S!=-1
					newThisSource = get(i).getS();
				}
				if (o.get(i).getS().compareTo(new BigInteger("-1")) != 0) { // S!=-1
					newOSource = o.get(i).getS();
				}
			}
			if (size() > o.size()) {
				return 1;
			} else if (size() < o.size()) {
				return -1;
			}
			// compare last term; d is already compared
			comp = newThisSource.compareTo(newOSource);
			if (comp != 0) { // C != o.C
				return comp;
			} else {
				comp = newThisClock.compareTo(newOClock);
				if (comp != 0) { // C != o.C
					return comp;
				}
			}
		}

		return 0;
	}

	/**
	 * Construction of identifier described by Weiss in the Logoot algorithm
	 * Compression feature iz added
	 * 
	 * @param r
	 * @param p
	 * @param q
	 * @param rep
	 */
	public void constructIdentifier(ArrayList<BigInteger> r, Positions p,
			Positions q, Replica rep) {

		BigInteger copyClock = new BigInteger("-1");
		BigInteger copySource = new BigInteger("-1");
		boolean changeClock = false;
		boolean changeSource = false;

		for (int i = 0; i < r.size(); ++i) {
			Position tempPosition;
			BigInteger d = r.get(i);
			// tempPosition.setD(d);
			changeClock = false;
			changeSource = false;
			if (i < p.size() && d.equals(p.get(i).getD())) {
				tempPosition = p.get(i);
			} else if (i < q.size() && d == q.get(i).getD()) {
				tempPosition = q.get(i);
			} else {
				DSCPosition tempDSCPosition = new DSCPosition();

				tempDSCPosition.setS(rep.getId());
				rep.setClock(rep.getClock().add(new BigInteger("1")));
				tempDSCPosition.setC(rep.getClock());
				tempPosition = tempDSCPosition;
			}

			if (tempPosition.getS().compareTo(new BigInteger("-1")) != 0
					&& tempPosition.getS().compareTo(copySource) != 0) { // s!=-1
				// &&
				// newSource!=copySource
				copySource = tempPosition.getS(); // nouvelle source
				changeSource = true;
			}
			if (tempPosition.getC().compareTo(new BigInteger("-1")) != 0
					&& tempPosition.getC().compareTo(
							copyClock.add(new BigInteger("1"))) != 0) { // s!=-1
				// &&
				// newclock!=copyclock+1
				copyClock = tempPosition.getC(); // nouvelle clock
				changeClock = true;
			} else {
				copyClock = copyClock.add(new BigInteger("1"));
			}

			Position position;
			// Create the new compressed position
			if (!changeSource && !changeClock) {
				position = new Position(d);
			} else if (!changeSource && changeClock) {
				position = new DCPosition(d, copyClock);
			} else if (changeSource && !changeClock) {
				position = new DSPosition(d, copySource);
			} else {
				position = new DSCPosition(d, copySource, copyClock);
			}

			add(position);
		}

	}

	/**
	 * Function which drop the replicas informations and build the list of
	 * index-digits
	 * 
	 * @param index
	 * @return
	 */
	public ArrayList<BigInteger> prefix(Integer index) {
		ArrayList<BigInteger> prefixes = new ArrayList<BigInteger>();
		for (int i = 0; i < index; ++i) {
			if (i < size()) {
				prefixes.add(get(i).getD());
			} else {
				prefixes.add(new BigInteger("0"));
			}
		}
		return prefixes;
	}

	public int bitSizeWithSR() {
		int sum = 0;
		for (Position pos : this) {
			if (pos instanceof DCPosition) {
				sum += 4 * 8; // 4 bytes clock
			}
			if (pos instanceof DSCPosition) {
				sum += 12 * 8; // 8 + 4 bytes
			}
			if (pos instanceof DSPosition) {
				sum += 8 * 8; // 8 bytes source
			}

			sum += pos.getD().bitLength() + 2; // 2 bit to indicate which type
			// it iz
			// sum += pos.getD().bitLength() + 12 * 8;
		}
		return sum;
	}

}
