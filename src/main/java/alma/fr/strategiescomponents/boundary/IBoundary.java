package alma.fr.strategiescomponents.boundary;

import java.math.BigInteger;

public interface IBoundary {

	/**
	 * Return the boundary value at the specified depth. 
	 * @param depth
	 * @return
	 */
	BigInteger getBoundary(Integer depth);
	
	
}
