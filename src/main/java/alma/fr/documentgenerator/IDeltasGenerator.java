package alma.fr.documentgenerator;

import java.util.ArrayList;

import difflib.Delta;

/**
 * 
 * Generate fake insertion or deletion in a fictive document
 * 
 */
public interface IDeltasGenerator {

	ArrayList<Delta> getDeltas(Integer N);

}
