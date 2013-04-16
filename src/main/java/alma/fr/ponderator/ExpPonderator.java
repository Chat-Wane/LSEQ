package alma.fr.ponderator;

public class ExpPonderator implements IPonderator {

	public Double getPonderation(Double value) {
		return Math.exp(value);
	}
}
