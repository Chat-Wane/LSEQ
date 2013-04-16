package alma.fr.logootenginecomponents;

import java.util.ArrayList;

import difflib.Delta;

public interface ILogootEngine {

	/** integrate remote or local changes to the system **/
	void deliver(MyPatch patch);

	/** generate patch from local changes **/
	MyPatch generatePatch(ArrayList<Delta> deltas); // PASUR

}
