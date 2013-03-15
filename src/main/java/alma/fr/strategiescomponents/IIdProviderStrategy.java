package alma.fr.strategiescomponents;

import java.util.BitSet;
import java.util.Iterator;

import javax.swing.text.Position;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;

public interface IIdProviderStrategy {

	/**
	 * Generate N identifier between p & q
	 * 
	 * @param p previous identifier
	 * @param q next identifier
	 * @param N number of line inserted
	 * @param rep replica informations to store
	 * @return list of unique identifiers which can be used in logoot
	 */
	public Iterator<Positions> generateIdentifiers(Positions p, Position q,
			Integer N, Replica rep, BitSet interval, int index);
}
