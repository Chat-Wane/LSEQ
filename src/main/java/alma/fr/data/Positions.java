package alma.fr.data;

import java.math.BigInteger;
import java.util.ArrayList;

import alma.fr.logootenginecomponents.Replica;

public class Positions extends ArrayList<BigInteger> implements
		Comparable<Positions> {

	private BigInteger s;
	private BigInteger c;

	private static final long serialVersionUID = 1L;

	public BigInteger getC() {
		return c;
	}
	
	public BigInteger getS() {
		return s;
	}
	
	public void setC(BigInteger c) {
		this.c = c;
	}
	
	public void setS(BigInteger s) {
		this.s = s;
	}
	
	public int compareTo(Positions o) {
		int minSize = Math.min(size(), o.size());

		for (int i = 0; i < minSize; i++) {
			int comp = get(i).compareTo(o.get(i));
			if (comp != 0) {
				return comp;
			}
		}

		// compare last term; d is already compared
		int comp = s.compareTo(o.s);
		if (comp != 0) { // C != o.C
			return comp;
		} else {
			comp = c.compareTo(o.c);
			if (comp != 0) { // C != o.C
				return comp;
			}
		}

		if (size() > o.size()) {
			return 1;
		} else if (size() < o.size()) {
			return -1;
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

		addAll(r);

		s = rep.getId();
		c = rep.getClock();

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
				prefixes.add(get(i));
			} else {
				prefixes.add(BigInteger.ZERO);
			}
		}
		return prefixes;
	}

	public int bitSizeWithSR() {
		int sum = 0;
		for (BigInteger pos : this) {
			sum += pos.bitLength() + 2; // 2 bit to indicate which type
			// it iz
			// sum += pos.getD().bitLength() + 12 * 8;
		}
		return sum;
	}
	

	@Override
	public String toString() {
		return "<d" + this.toString() + "; s "+ s+ "; c " + c.toString() + ">";
	}

}
