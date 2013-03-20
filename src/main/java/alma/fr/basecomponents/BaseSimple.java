package alma.fr.basecomponents;

import java.math.BigInteger;

import com.google.inject.Inject;

public class BaseSimple implements IBase {

	private final Integer baseBase;

	@Inject
	public BaseSimple(@Basebase Integer baseBase) {
		this.baseBase = baseBase;
	}

	public Integer getBitBase(Integer depth) {
		return baseBase;
	}

	public Integer getSumBit(Integer depth) {
		return baseBase * depth;
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
		BigInteger result = BigInteger.ZERO;

		// #1 truncate or add
		// #1a: on previous digit
		BigInteger prev;
		if (prevBitLength < bitBaseSum) { // Add 0 and +1 to result
			prev = p.shiftLeft(bitBaseSum - prevBitLength);
			//result = BigInteger.ONE;
		} else {
			prev = p.shiftRight(prevBitLength - bitBaseSum);
		}

		// #1b: on next digit
		BigInteger next;
		if (nextBitLength < bitBaseSum) { // Add 1 and +1 to result
			next = q.shiftLeft(bitBaseSum - nextBitLength);
			next = next.add(BigInteger.valueOf(2)
					.pow(bitBaseSum - nextBitLength).subtract(BigInteger.ONE));
			result = result.add(BigInteger.ONE);
		} else {
			next = q.shiftRight(nextBitLength - bitBaseSum);
		}

		result = result.add(next.subtract(prev).subtract(BigInteger.ONE));

		return result;
	}
}
