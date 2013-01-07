package alma.fr.data;

import java.math.BigInteger;

public class Position {

	BigInteger d;

	public Position() {
		d = new BigInteger("0");
	}

	public Position(BigInteger d) {
		this.d = d;
	}

	public BigInteger getD() {
		return d;
	}

	public void setD(BigInteger d) {
		this.d = d;
	}

	// implicit not exist
	public BigInteger getC() {
		return new BigInteger("-1");
	}

	public BigInteger getS() {
		return new BigInteger("-1");
	}
	
	@Override
	public String toString() {
		return "<d"+d.toString()+">";
	}

}
