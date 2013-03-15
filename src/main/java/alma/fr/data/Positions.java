package alma.fr.data;

import java.util.BitSet;

import alma.fr.logootenginecomponents.Replica;

public class Positions extends BitSet implements Comparable<Positions> {

	private Integer s;
	private Integer c;

	private static final long serialVersionUID = 1L;

	public Positions(BitSet r, Replica rep) {
		super(r.size());
		this.or(r);

		s = rep.getId();
		c = rep.getClock();

	}

	public Integer getC() {
		return c;
	}

	public Integer getS() {
		return s;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	public void setS(Integer s) {
		this.s = s;
	}

	public int compareTo(Positions o) {
		// #1 truncate
		// #2 compare digit

		// #3 compare s and c
		int comp = s.compareTo(o.s);
		if (comp != 0) { // C != o.C
			return comp;
		} else {
			comp = c.compareTo(o.c);
			if (comp != 0) { // C != o.C
				return comp;
			}
		}

		// #4 compare size
		if (size() > o.size()) {
			return 1;
		} else if (size() < o.size()) {
			return -1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return "<d" + this.toString() + "; s " + s + "; c " + c.toString()
				+ ">";
	}

}
