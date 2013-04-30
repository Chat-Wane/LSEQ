package alma.fr.data;

import java.math.BigInteger;
import java.util.ArrayList;

import alma.fr.logootenginecomponents.LogootEngine;

/**
 * 
 * A Logoot identifier as it iz in Weiss Thesis & logoot undo at TPDS
 * 
 */
public class Positions implements Comparable<Positions> {

	private final BigInteger d; // position
	private final ArrayList<Integer> s; // sources
	private final ArrayList<Integer> c; // clocks

	// used in case of equality due to concurrence

	public Positions(BigInteger r, int bitSize, ArrayList<Integer> s,
			ArrayList<Integer> c) {
		this.d = r.setBit(bitSize); // set the departure bit to 1. Thus the 0 in
		// front won't be automatically truncated by
		// BigInteger
		this.s = s;
		this.c = c;
	}

	public BigInteger getD() {
		return d;
	}

	public ArrayList<Integer> getS() {
		return s;
	}

	public ArrayList<Integer> getC() {
		return c;
	}

	public int compareTo(Positions o) {
		Integer mineSize = getC().size();
		Integer otheSize = o.getC().size();
		// #1 Compare the list of <d,s,c>
		for (int i = 0; i < Math.min(mineSize, otheSize); ++i) {
			// can stop before the end of for loop wiz return
			int sum = LogootEngine.base.getSumBit(i + 1) + 1;
			// #1a truncate mine
			BigInteger mine = getD().shiftRight(getD().bitLength() - sum);
			// #1b truncate other
			BigInteger other = o.getD().shiftRight(o.getD().bitLength() - sum);
			// #2 Compare digit part
			int comp = mine.compareTo(other); // DIGIT
			if (comp != 0) {
				return comp;
			}
			comp = getS().get(i).compareTo(o.getS().get(i)); // SOURCE
			if (comp != 0) {
				return comp;
			}
			comp = getC().get(i).compareTo(o.getC().get(i)); // CLOCK
			if (comp != 0) {
				return comp;
			}

		}

		// #2 Compare size of lists
		return mineSize.compareTo(otheSize);

	}

	public String reNumberInDec() {
		String result = "<< ";
		int maxNbBit = d.bitLength();
		for (int i = 0; i < c.size(); ++i) {
			int nbBits = LogootEngine.base.getSumBit(i + 1) + 1; // 1 = first
																	// bit of d
			BigInteger tempDigit = d.shiftRight(maxNbBit - nbBits);
			BigInteger maskedDigit = tempDigit.and(BigInteger.valueOf(2)
					.pow(LogootEngine.base.getBitBase(i + 1))
					.subtract(BigInteger.ONE));
			result = result + maskedDigit.toString() + " ";
		}
		result = result + ">>";
		return result;
	}

	@Override
	public String toString() {
		return "<d" + reNumberInDec() + "; s " + s.toString() + "; c "
				+ c.toString() + ">";
	}
}
