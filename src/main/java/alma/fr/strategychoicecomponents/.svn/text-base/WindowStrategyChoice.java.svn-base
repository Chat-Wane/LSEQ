package alma.fr.strategychoicecomponents;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;

import alma.fr.data.Positions;
import alma.fr.logootenginecomponents.Replica;
import alma.fr.ponderator.IPonderator;
import alma.fr.strategiescomponents.IIdProviderStrategy;

import com.google.inject.Inject;

public class WindowStrategyChoice implements IStrategyChoice {

	private HashMap<Positions, FakeListNode> spectrum = new HashMap<Positions, FakeListNode>();

	private Integer date = 0;

	private Integer window = 10; // ??? FIXME : dunno yet, arbitrary

	private IIdProviderStrategy beginningStrategy;
	private IIdProviderStrategy endingStrategy;

	private IPonderator datePonderator;

	@Inject
	public WindowStrategyChoice(@Strat1 IIdProviderStrategy beginningStrategy,
			@Strat2 IIdProviderStrategy endingStrategy,
			IPonderator datePonderator) {
		this.beginningStrategy = beginningStrategy; // boundary +
		this.endingStrategy = endingStrategy; // boundary -
		this.datePonderator = datePonderator;
	}

	public Iterator<Positions> generateLineIdentifiers(Positions p,
			Positions q, Integer N, Replica rep) {

		Iterator<Positions> idz = caseStudy(p, q).generateLineIdentifiers(p, q,
				N, rep);

		return idz;
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

	private IIdProviderStrategy caseStudy(Positions p, Positions q) {
		// Insert at teh End
		if ((!spectrum.containsKey(p)) || spectrum.get(q).getNext() == null) { // First
			// coaz
			// moar
			// frequent
			return beginningStrategy;
		}

		// Insert at Start
		if ((!spectrum.containsKey(q)) || spectrum.get(p).getPrev() == null) {
			return endingStrategy;
		}

		// Derive
		ArrayDeque<Integer> windowIdz = new ArrayDeque<Integer>();

		// <-- p
		Positions posIt = p;
		Integer maxDate = 0;
		Boolean hasPrev = true;
		for (int i = 0; i < window / 2; ++i) {
			if (hasPrev) {
				if (spectrum.get(posIt).getPrev() != null) {
					maxDate = Math.max(maxDate, spectrum.get(posIt).getDate());
					windowIdz.addFirst(spectrum.get(posIt).getDate());
					posIt = spectrum.get(posIt).getPrev();
				} else {
					maxDate = Math.max(maxDate, spectrum.get(posIt).getDate());
					windowIdz.addFirst(spectrum.get(posIt).getDate());
					hasPrev = false;
				}
			}
		}

		// q -->
		posIt = q;
		Boolean hasNext = true;
		for (int i = 0; i < window / 2; ++i) {
			if (hasNext) {
				if (spectrum.get(posIt).getNext() != null) {
					maxDate = Math.max(maxDate, spectrum.get(posIt).getDate());
					windowIdz.addLast(spectrum.get(posIt).getDate());
					posIt = spectrum.get(posIt).getNext();
				} else {
					maxDate = Math.max(maxDate, spectrum.get(posIt).getDate());
					windowIdz.addLast(spectrum.get(posIt).getDate());
					hasNext = false;
				}
			}
		}

		// Processing derive
		Float d = derivee(windowIdz, maxDate);
		if (d <= 0.3 && d >= -0.3) {// [-0.5 ; 0.5] // FIXME WHY DIZ VALUEZ
			// TODO
			// // Variance
			// // Entropy
			// System.out.println("AAvzevikbz");
			// System.out.println("d=0");
			return beginningStrategy;
		} else {

			if (d < 0) { // ]-INF ; -0.5[
				// System.out.println("d<0");
				return endingStrategy;
			} else { // ] 0.5 ; +INF [
				// System.out.println("d>0");
				return beginningStrategy;
			}
		}

	}

	private Float derivee(ArrayDeque<Integer> values, Integer max) {
		if (values.size() == 0) {
			return 0f;
		}
		if (values.size() == 2) {
			return (float) values.getFirst();
		}
		float avg = 0f;

		System.out.println(values);
		Integer size = values.size();
		for (int i = 0; i < size - 1; ++i) {

			double valI = values.pollFirst();

			if ((values.peekFirst() > 0.7 * date)) {

				double tempAvg = (Math.min(valI, values.peekFirst()) / max)
						* Math.abs(size / 2 - (Math.abs(size / 2 - i)))
						* ((-datePonderator.getPonderation(valI) + datePonderator
								.getPonderation((double) values.peekFirst())) / (size - 1));

				System.out.println(i + " = " + tempAvg);

				avg += tempAvg;
			}
		}

		System.out.println(avg);
		return avg;
	}

	public void incDate() {
		++date;
	}

	public void setSpectrum(HashMap<Positions, FakeListNode> spectrum) {
		this.spectrum = spectrum;
	}

	public HashMap<Positions, FakeListNode> getSpectrum() {
		return spectrum;
	}

}
