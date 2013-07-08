package alma.fr.strategychoicecomponents;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.strategiescomponents.IIdProviderStrategy;

import com.google.inject.Inject;

public class HashStrategyChoice implements IStrategyChoice {
	private HashMap<Positions, FakeListNode> spectrum = new HashMap<Positions, FakeListNode>();

	private Integer date = 0;

	final long seed = 123456789;
	
	final Random r = new Random(123456789);

	@Inject
	IBase base;

	private IIdProviderStrategy strategy1;
	private IIdProviderStrategy strategy2;

	@Inject
	public HashStrategyChoice(IBase base,
			@Strat1 IIdProviderStrategy strategy1,
			@Strat2 IIdProviderStrategy strategy2) {
		this.base = base;
		this.strategy1 = strategy1;
		this.strategy2 = strategy2;
	}

	/** add the new id in the structure **/
	public void add(Positions prev, Positions id, Positions next) {

		if (!spectrum.containsKey(prev)) {
			FakeListNode prevfln = new FakeListNode(null, date, id);
			spectrum.put(prev, prevfln);
		} else {
			spectrum.get(prev).setNext(id);
		}

		if (!spectrum.containsKey(next)) {
			FakeListNode nextfln = new FakeListNode(id, date, null);
			spectrum.put(next, nextfln);
		} else {
			spectrum.get(next).setPrev(id);
		}

		FakeListNode fln = new FakeListNode(prev, date, next);

		spectrum.put(id, fln);
	}

	public void del(Positions id) {
		FakeListNode fln = spectrum.get(id);
		if (fln.getPrev() != null) {
			spectrum.get(fln.getPrev()).setNext(fln.getNext());

		}
		if (fln.getNext() != null) {
			spectrum.get(fln.getNext()).setPrev(fln.getPrev());
		}
		spectrum.remove(id);
	}

	public Iterator<Positions> generateIdentifiers(Positions p, Positions q,
			Integer N, Replica rep) {

		// #1 count interval between p and q, until itz enough
		BigInteger interval = BigInteger.ZERO;
		int index = 0;
		while (BigInteger.valueOf(N).compareTo(interval) > 0) {
			// #1 a: obtain index value
			++index;
			// #1 b: obtain interval value
			interval = base.interval(p.getD(), q.getD(), index);
		}

		
		boolean idStrat = new Random(seed*index).nextBoolean();
		
		// #3 chose the strategy
		if (idStrat) {
			return strategy1.generateIdentifiers(p, q, N, rep, interval, index);
		} else {
			return strategy2.generateIdentifiers(p, q, N, rep, interval, index);
		}
	}

	public void incDate() {
		++date;
	}

	public HashMap<Positions, FakeListNode> getSpectrum() {
		return spectrum;
	}

}
