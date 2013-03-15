package alma.fr.strategiescomponents;

import java.util.BitSet;
import java.util.Iterator;

import javax.swing.text.Position;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.strategiescomponents.boundary.IBoundary;

import com.google.inject.Inject;

public class EndingBoundaryIdProvider implements IIdProviderStrategy {

	@Inject
	private IBase base;

	@Inject
	private IBoundary boundary;

	@Inject
	public EndingBoundaryIdProvider(IBase base, IBoundary boundary) {
		this.base = base;
		this.boundary = boundary;
	}

	public Iterator<Positions> generateIdentifiers(Positions p, Position q,
			Integer N, Replica rep, BitSet interval, int index) {
		return null;
	}

}
