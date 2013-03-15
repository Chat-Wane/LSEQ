package alma.fr.basecomponents;

import java.util.BitSet;

import com.google.inject.Inject;

public class BaseSimple implements IBase {

	private final Integer baseBase;

	private final BitSet base;

	@Inject
	public BaseSimple(@Basebase Integer baseBase) {
		this.baseBase = baseBase;
		this.base = new BitSet(baseBase);
		this.base.set(0, baseBase);
	}

	public BitSet getBase(Integer depth) {
		return base;
	}

	public Integer getBitBase(Integer depth) {
		return baseBase;
	}

	public Integer getBaseBase() {
		return baseBase;
	}

	public void sub(BitSet r, BitSet value) {

	}

	public void add(BitSet r, BitSet value) {

	}

	public BitSet interval(BitSet p, BitSet q, Integer index,
			BitSet previousValue) {
		// TODO Auto-generated method stub
		return null;
	}
}
