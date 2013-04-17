package alma.fr.logootenginecomponents;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import alma.fr.basecomponents.IBase;
import alma.fr.data.Positions;
import alma.fr.strategychoicecomponents.IStrategyChoice;

import com.google.inject.Inject;

import difflib.Delta;

public class LogootEngine implements ILogootEngine {

	private ArrayList<Positions> idTable;
	private ArrayList<String> doc;

	@Inject
	private IBase base;

	private Integer replica;

	@Inject
	private IStrategyChoice strategyChoice;

	/**
	 * Initialize Logoot data & document
	 */
	@Inject
	public LogootEngine(IBase base, IStrategyChoice strategyChoice) {
		this.base = base;
		this.strategyChoice = strategyChoice;

		Positions first = new Positions(BigInteger.ZERO, base.getBaseBase(), 1,
				-666, base);

		Positions last = new Positions(BigInteger.valueOf(2)
				.pow(this.base.getBaseBase()).subtract(BigInteger.ONE),
				base.getBaseBase(), 1, 666, base);

		idTable = new ArrayList<Positions>();
		idTable.add(first);
		idTable.add(last);

		doc = new ArrayList<String>();
	}

	public void deliver(MyPatch patch) {

		boolean one_insert = false;

		for (MyDelta delta : patch) {
			Integer position;

			// System.out.println(idTable);
			// System.out.println("==========");
			// System.out.println(delta.getId());
			switch (delta.getType()) {
			case INSERT:
				one_insert = true;
				position = -Collections.binarySearch(idTable, delta.getId()) - 1;
				doc.add(position - 1, delta.getContent());
				idTable.add(position, delta.getId());
				strategyChoice.add(idTable.get(position - 1),
						idTable.get(position), idTable.get(position + 1));
				break;

			case DELETE:

				position = Collections.binarySearch(idTable, delta.getId());
				if (position > 0) {
					doc.remove(position - 1);
					idTable.remove((int) position);
					strategyChoice.del(delta.getId());
				} else {
					System.out.println("MIAOU MIAOU");
				}
				break;
			default: // NOTHING
				break;
			}

		}
		if (one_insert) { // At least of insert
			strategyChoice.incDate();
		}
	}

	public MyPatch generatePatch(ArrayList<Delta> deltas) {

		MyPatch patch = new MyPatch();

		for (int j = 0; j < deltas.size(); ++j) {
			// foreach delta

			Iterator<Positions> ids;
			switch (deltas.get(j).getType()) {
			case INSERT:

				ids = insert(deltas.get(j));
				for (int k = 0; k < deltas.get(j).getRevised().getLines()
						.size(); ++k) {// foreach line inserted

					MyDelta md = new MyDelta(Operation.INSERT, ids.next(),
							deltas.get(j).getRevised().getLines().get(k)
									.toString());
					patch.add(md);

				}

				break;
			case CHANGE:
				// foreach line changed (<=> delete & insert )
				for (int k = 0; k < deltas.get(j).getOriginal().getLines()
						.size(); ++k) { // deleted lines
					MyDelta md = new MyDelta(Operation.DELETE,
							idTable.get(deltas.get(j).getOriginal()
									.getPosition()
									+ k + 1), "");
					patch.add(md);
				}

				ids = insert(deltas.get(j));
				for (int k = 0; k < deltas.get(j).getRevised().getLines()
						.size(); ++k) { // inserted line
					MyDelta md = new MyDelta(Operation.INSERT, ids.next(),
							deltas.get(j).getRevised().getLines().get(k)
									.toString());
					patch.add(md);
				}
				break;
			case DELETE:

				for (int k = 0; k < deltas.get(j).getOriginal().getLines()
						.size(); ++k) {
					MyDelta md = new MyDelta(Operation.DELETE,
							idTable.get(deltas.get(j).getOriginal()
									.getPosition()
									+ k + 1), "");
					patch.add(md);
				}
				break;
			default: // NOTHING

			}

		}
		return patch;
	}

	public void setDoc(ArrayList<String> doc) {
		this.doc = doc;
	}

	public void setIdTable(ArrayList<Positions> idTable) {
		this.idTable = idTable;
	}

	public void setReplica(Integer replica) {
		this.replica = replica;
	}

	public void setBase(IBase base) {
		this.base = base;
	}

	public void setStrategyChoice(IStrategyChoice strategyChoice) {
		this.strategyChoice = strategyChoice;
	}

	public IStrategyChoice getStrategyChoice() {
		return strategyChoice;
	}

	public IBase getBase() {
		return base;
	}

	public Integer getReplica() {
		return replica;
	}

	public ArrayList<String> getDoc() {
		return doc;
	}

	public List<Positions> getIdTable() {
		return idTable;
	}

	/*
	 * Factorize code for an insert operation
	 */
	private Iterator<Positions> insert(Delta delta) {

		Positions previous = idTable.get(delta.getOriginal().getPosition());
		Positions next = idTable.get(delta.getOriginal().getPosition()
				+ delta.getOriginal().getLines().size() + 1);

		return strategyChoice.generateIdentifiers(previous, next, delta
				.getRevised().getLines().size(), replica);
	}
}
