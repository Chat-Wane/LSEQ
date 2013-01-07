package alma.fr.data;

import java.math.BigInteger;

public class DSCPosition extends Position {

	BigInteger s;
	BigInteger c;

	public DSCPosition() {
		super();
		s = new BigInteger("0");
		c = new BigInteger("0");
	}

	public DSCPosition(BigInteger d, BigInteger s, BigInteger c) {
		super(d);
		this.s = s;
		this.c = c;
	}

	public void setS(BigInteger s) {
		this.s = s;
	}

	public void setC(BigInteger c) {
		this.c = c;
	}

	@Override
	public BigInteger getS() {
		return s;
	}

	@Override
	public BigInteger getC() {
		return c;
	}

	@Override
	public String toString() {
		return "<d" + d.toString() + " ; s" + s.toString() + " ; c" + c.toString()
				+ ">";
	}
}
