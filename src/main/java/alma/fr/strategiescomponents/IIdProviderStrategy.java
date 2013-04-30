package alma.fr.strategiescomponents;

import java.math.BigInteger;
import java.util.Iterator;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;

public interface IIdProviderStrategy {

	/**
	 * Generate N identifier between p & q
	 * 
	 * @param p
	 *            previous identifier
	 * @param q
	 *            next identifier
	 * @param N
	 *            number of line inserted
	 * @param rep
	 *            replica informations to store
	 * @return list of unique identifiers which can be used in logoot
	 */
	public Iterator<Positions> generateIdentifiers(Positions p, Positions q,
			Integer N, Replica rep, BigInteger interval, int index);
}
