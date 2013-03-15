package alma.fr.logootenginecomponents;


public class Replica {

	private Integer id; // Id of replica
	private Integer clock; // clock of replica

	public Replica() {
		id = 0;
		clock = 0;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClock() {
		return clock;
	}

	public Integer getId() {
		return id;
	}

}
