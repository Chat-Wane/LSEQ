package alma.fr.data;

import java.math.BigInteger;

import alma.fr.basecomponents.IBase;

import com.google.inject.Inject;

public class Positions implements Comparable<Positions> {

	@Inject
	private static IBase base;

	private final BigInteger d; // position
	private final Integer s; // source

	// used in case of equality due to concurrence
	private final Integer b; // biase
	private final boolean[] dim; // change dim or not

	public Positions(BigInteger r, int bitSize, int size, int s, IBase base) {
		this.d = r.setBit(bitSize); // set the departure bit to 1. Thus the 0 in
		// front won't be automatically truncated by
		// BigInteger
		this.s = s;
		this.b = 0;
		this.dim = new boolean[size];
		Positions.base = base;
	}

	public BigInteger getD() {
		return d;
	}

	public int getB() {
		return b;
	}

	public boolean[] getDim() {
		return dim;
	}

	public boolean atDim(int index) {
		if (dim.length > index) {
			return dim[index];
		} else {
			return false; // def value
		}
	}

	public Integer getS() {
		return s;
	}

	public int compareTo(Positions o) {
		// #1 diff on dimension
		// extract the common root for both id
		int i = 0;
		while ((dim.length > i || o.dim.length > i) && atDim(i) == o.atDim(i)) {
			++i;
		}

		// #2 truncate or extend until getSumBit(i)
		int nbBitToCompare = base.getSumBit(i + 1) + 1; // +1: for first bit of
														// idz

		// #2a truncate
		int myBitLength = d.bitLength();
		int otBitLength = o.d.bitLength();

		BigInteger mine = d.shiftLeft(nbBitToCompare - myBitLength);
		BigInteger other = o.d.shiftLeft(nbBitToCompare - otBitLength);

		// #3 compare digit
		int comp = mine.compareTo(other);
		if (comp != 0) {
			return comp;
		}
		// else common roots are equal

		// #4 compare bitset
		if (atDim(i + 1)) { // true
			return -1;
		} else if (o.atDim(i + 1)) {
			return 1;
		}
		// else two == 0 cuz i> length

		// #5 compare biase
		comp = ((Integer) (s + b)).compareTo(o.s + o.b);
		if (comp != 0) { // s != o.s
			return comp;
		} else {
			// #6 compare source alone
			return s.compareTo(o.s);
		}

	}

	@Override
	public String toString() {
		return "<d" + d.toString() + "; s " + s + "; b " + b.toString()
				+ "; dim " + dim.toString() + ">";
	}
}
