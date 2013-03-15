package alma.fr.basecomponents;

import java.util.ArrayList;
import java.util.BitSet;

import com.google.inject.Inject;

public class BaseDouble implements IBase {

	private final Integer baseBase;

	private ArrayList<BitSet> bases;

	@Inject
	public BaseDouble(@Basebase Integer baseBase) {
		this.baseBase = baseBase;
	}

	public BitSet getBase(Integer depth) {
		while (bases.size() < depth) {
			bases.add(new BitSet(baseBase + bases.size()));
			bases.get(bases.size() - 1).set(0, baseBase + bases.size() - 1);
		}
		return bases.get(depth);
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
