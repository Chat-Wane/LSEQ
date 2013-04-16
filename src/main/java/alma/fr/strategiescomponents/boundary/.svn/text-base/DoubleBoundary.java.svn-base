package alma.fr.strategiescomponents.boundary;

import java.math.BigInteger;

import alma.fr.basecomponents.IBase;

import com.google.inject.Inject;

public class DoubleBoundary implements IBoundary {

	public IBase base;

	public BigInteger value; // percent of the current base

	@Inject
	public DoubleBoundary(@BoundaryValue BigInteger value) {
		this.value = value;
	}

	public BigInteger getBoundary(Integer depth) {
		return base.getBase(depth).multiply(value)
				.divide(new BigInteger("100"));

	}

}
