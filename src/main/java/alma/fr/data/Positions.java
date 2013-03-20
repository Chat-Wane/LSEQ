package alma.fr.data;

import java.math.BigInteger;

import sun.misc.Cleaner;

import alma.fr.logootenginecomponents.Replica;

public class Positions implements Comparable<Positions> {

	private final BigInteger d;
	private final Integer s;
	private final Integer c;

	public Positions(BigInteger r, int bitSize, Replica rep) {
		d = r.setBit(bitSize); // set the departure bit to 1
		s = rep.getId();
		c = rep.getClock();
	}

	public BigInteger getD() {
		return d;
	}

	public Integer getC() {
		return c;
	}

	public Integer getS() {
		return s;
	}

	public int compareTo(Positions o) {
		// #1 truncate
		int myBitLength = d.bitLength();
		int otBitLength = o.d.bitLength();

		int difBitLength = myBitLength - otBitLength;

		BigInteger other;
		BigInteger mine;
		if (difBitLength > 0) { // mine > other (in size)
			other = o.d;
			mine = d.shiftRight(difBitLength);
		} else {
			other = o.d.shiftRight(-difBitLength);
			mine = d;
		}
		// #2 compare digit
		int comp = mine.compareTo(other);
		if (comp != 0) {
			return comp;
		}

		// #3 compare s and c
		comp = s.compareTo(o.s);
		if (comp != 0) { // s != o.s
			return comp;
		} else {
			comp = c.compareTo(o.c);
			if (comp != 0) { // C != o.C
				return comp;
			}
		}

		// #4 compare size
		if (myBitLength > otBitLength) {
			return 1;
		} else if (myBitLength < otBitLength) {
			return -1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return "<d" + d.toString() + "; s " + s + "; c " + c.toString()
				+ ">";
	}

}
