package alma.fr.basecomponents;

import java.math.BigInteger;

import com.google.inject.Inject;

public class BaseDouble implements IBase {

	private final Integer baseBase;

	@Inject
	public BaseDouble(@Basebase Integer baseBase) {
		this.baseBase = baseBase;
	}

	public Integer getBitBase(Integer depth) {
		return baseBase + depth - 1;
	}

	public Integer getSumBit(Integer depth) {
		int n = getBitBase(depth);
		int m = baseBase - 1;
		return (n * (n + 1)) / 2 - (m * (m + 1) / 2);
	}

	public Integer getBaseBase() {
		return baseBase;
	}

	public BigInteger sub(BigInteger r, BigInteger value) {
		return r.subtract(value);
	}

	public BigInteger add(BigInteger r, BigInteger value) {
		return r.add(value);
	}

	public BigInteger interval(BigInteger p, BigInteger q, Integer index) {
		int prevBitLength = p.bitLength() - 1;
		int nextBitLength = q.bitLength() - 1;

		int bitBaseSum = getSumBit(index);

		// #1 truncate or add
		// #1a: on previous digit
		// if (prevBitLength < bitBaseSum): Add 0
		// if (prevBitLength > bitBaseSum): truncate
		BigInteger prev = p.shiftLeft(bitBaseSum - prevBitLength);
		// #2a: on next digit
		BigInteger next = q.shiftLeft(bitBaseSum - nextBitLength);

		// #2b: compute particular case: p>=q at depth "index"
		if (next.compareTo(prev) < 0) {
			// #1c: search the common root & add one
			int i = 1;
			int sumBitI = getSumBit(i);
			while (prev.shiftRight(prev.bitLength() - 1 - sumBitI).equals(
					next.shiftRight(next.bitLength() - 1 - sumBitI))
					&& (next.bitLength() - 1 - sumBitI >= 0)) {
				++i;
				sumBitI = getSumBit(i);
			} // the common root is defined until a depth of i-1
				// #1d: get the common root and add 1
			next = (next.shiftRight(next.bitLength() - 1 - getSumBit(i - 1)))
					.add(BigInteger.ONE);
			// #1e: append some 0
			next = next.shiftLeft(bitBaseSum - next.bitLength() + 1);
		}
		return next.subtract(prev).subtract(BigInteger.ONE);
	}
}
