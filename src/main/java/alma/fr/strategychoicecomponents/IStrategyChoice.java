package alma.fr.strategychoicecomponents;

import java.util.HashMap;
import java.util.Iterator;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;

public interface IStrategyChoice {

	/**
	 * Function which will defer the creation of identifiers to a bunch of
	 * IdProviders
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
	public Iterator<Positions> generateIdentifiers(Positions p,
			Positions q, Integer N, Integer rep);

	/**
	 * Add data to the strategy choice
	 * 
	 * @param p
	 *            previous Id
	 * @param id
	 *            inserted
	 * @param q
	 *            next Id
	 */
	public void add(Positions p, Positions id, Positions q);

	
	public void del(Positions id);
	
	public void incDate();
	
	public HashMap<Positions, FakeListNode> getSpectrum();
}
