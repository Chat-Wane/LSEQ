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
		BigInteger prev;
		if (prevBitLength < bitBaseSum) { // Add 0
			prev = p.shiftLeft(bitBaseSum - prevBitLength);
		} else {
			prev = p.shiftRight(prevBitLength - bitBaseSum);
		}

		// #1b: on next digit
		BigInteger next;
		if (nextBitLength < bitBaseSum) { // Add 0
			next = q.shiftLeft(bitBaseSum - nextBitLength);
		} else {
			next = q.shiftRight(nextBitLength - bitBaseSum);
		}

		return next.subtract(prev).subtract(BigInteger.ONE);
	}
}
