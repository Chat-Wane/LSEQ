package alma.fr.data;

import java.math.BigInteger;

public class DCPosition extends Position {

	BigInteger c;

	public DCPosition() {
		super();
		c = new BigInteger("0");
	}

	public DCPosition(BigInteger d, BigInteger c) {
		super(d);
		this.c = c;
	}

	@Override
	public BigInteger getC() {
		return c;
	}

	public void setC(BigInteger c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "<d" + d.toString() + " ; c" + c.toString() + ">";
	}
}