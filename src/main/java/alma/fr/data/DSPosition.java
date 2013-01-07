package alma.fr.data;

import java.math.BigInteger;

public class DSPosition extends Position {

	BigInteger s;

	public DSPosition() {
		super();
		s = new BigInteger("0");
	}

	public DSPosition(BigInteger d, BigInteger s) {
		super(d);
		this.s = s;
	}

	public void setS(BigInteger s) {
		this.s = s;
	}

	@Override
	public BigInteger getS() {
		return s;
	}

	@Override
	public String toString() {
		return "<d" + d.toString() + " ; s" + s.toString() + ">";
	}
}
