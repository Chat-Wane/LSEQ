package alma.fr.basecomponents;

import java.util.BitSet;

public interface IBase {

	/**
	 * function giving the common base of the logoot engine at a certain
	 * id-depth
	 * 
	 * @param depth
	 * @return base
	 */
	BitSet getBase(Integer depth);

	/**
	 * The number of bit used at a given depth
	 * 
	 * @param depth
	 * @return bit number
	 */
	Integer getBitBase(Integer depth);

	/**
	 * the number of bit at depth 1
	 * 
	 * @return bit number
	 */
	Integer getBaseBase();

	/**
	 * Substract value to r
	 * 
	 * @param r
	 * @param value
	 */
	void sub(BitSet r, BitSet value);

	/**
	 * Add value to r
	 * 
	 * @param r
	 * @param value
	 */
	void add(BitSet r, BitSet value);

	/**
	 * Process the interval (i.e. number of id possible) between p and q, p <_id
	 * q.
	 * 
	 * @param p
	 *            previous digit
	 * @param q
	 *            next digit
	 * @param index
	 *            depth of processing
	 * @param previousValue
	 *            value processed at depth-1, start to BigInteger.ZERO
	 * @return the interval at given depth
	 */
	BitSet interval(BitSet p, BitSet q, Integer index, BitSet previousValue);

}
