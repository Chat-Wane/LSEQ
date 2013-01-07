package alma.fr.basecomponents;

import java.math.BigInteger;
import java.util.ArrayList;

import com.google.inject.Inject;

public class BaseDouble implements IBase {

	private BigInteger baseBase;

	@Inject
	public BaseDouble(@Basebase BigInteger baseBase) {
		this.baseBase = baseBase;
	}

	public BigInteger getBase(Integer depth) {
		return baseBase.multiply(new BigInteger("2").pow(depth));
	}

	public BigInteger getBaseBase() {
		return baseBase;
	}

	public void setBaseBase(BigInteger baseBase) {
		this.baseBase = baseBase;
	}

	public BigInteger count(ArrayList<BigInteger> r, Integer index) {
		BigInteger sum = new BigInteger("0");

		BigInteger value;
		for (int i = 0; i < index - 1; ++i) {

			if (i < r.size()) {
				value = r.get(i);
			} else {
				value = new BigInteger("0");
			}

			sum = sum.add(value).multiply(getBase(i + 2));
		}
		if (index - 1 < r.size()) {
			value = r.get(index - 1);
		} else {
			value = new BigInteger("0");
		}

		sum = sum.add(value);

		return sum;
	}

	public void sub(ArrayList<BigInteger> r, BigInteger value) {
		Integer i = r.size() - 1;

		BigInteger reste = value.divide(getBase(i + 1));
		BigInteger tempVal = r.get(i).subtract(
				(value.divideAndRemainder(getBase(i + 1))[1]));
		if (tempVal.compareTo(new BigInteger("0")) == -1) { // tempVal<0
			tempVal = getBase(i + 1).add(tempVal);
			reste = reste.add(new BigInteger("1")); // ++
		}
		r.set(i, tempVal);
		while (i > 0 && !(reste.compareTo(new BigInteger("1")) == -1)) {
			--i;
			tempVal = r.get(i).subtract(
					(reste.divideAndRemainder(getBase(i + 1))[1]));
			reste = reste.divide(getBase(i + 1));
			if (tempVal.compareTo(new BigInteger("0")) == -1) { // tempval<0
				tempVal = getBase(i + 1).add(tempVal);
				reste = reste.add(new BigInteger("1")); // ++
			}
			r.set(i, tempVal);
		}
	}

	public void add(ArrayList<BigInteger> r, BigInteger value) {
		Integer i = r.size() - 1;
		BigInteger reste = (r.get(i).add(value)).divide(getBase(i + 1));
		r.set(i, (r.get(i).add(value)).divideAndRemainder(getBase(i + 1))[1]);
		while (i > 0 && !(reste.compareTo(new BigInteger("1")) == -1)) { // reste
			// >= 1
			--i;
			BigInteger tempValue = r.get(i);
			r
					.set(i, (r.get(i).add(reste))
							.divideAndRemainder(getBase(i + 1))[1]);
			reste = (tempValue.add(reste)).divide(getBase(i + 1));
		}
	}
}
