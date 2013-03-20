package alma.fr.basecomponents;

import java.math.BigInteger;

public interface IBase {

	/**
	 * Return the number of bit to a depth
	 * 
	 * @param depth
	 * @return bit number
	 */
	public Integer getSumBit(Integer depth);

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
	 * @return r-value
	 */
	BigInteger sub(BigInteger r, BigInteger value);

	/**
	 * Add value to r
	 * 
	 * @param r
	 * @param value
	 * @return r+value
	 */
	BigInteger add(BigInteger r, BigInteger value);

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
	 * @return the interval at given depth
	 */
	BigInteger interval(BigInteger p, BigInteger q, Integer index);

}
