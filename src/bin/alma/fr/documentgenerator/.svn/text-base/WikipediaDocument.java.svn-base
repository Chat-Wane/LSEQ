package alma.fr.documentgenerator;

import java.net.URL;
import java.util.ArrayList;

import alma.fr.logootenginecomponents.LogootEngine;
import alma.fr.logootenginecomponents.MyPatch;
import alma.fr.xml.Revision;
import alma.fr.xml.WikipediaQuery;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import difflib.myers.MyersDiff;

public class WikipediaDocument {

	private URL url;

	private ArrayList<String> doc = new ArrayList<String>();

	public void run(LogootEngine logootEngine) {

		ArrayList<Revision> revisions = WikipediaQuery.Query(url);
		MyersDiff diffAlgorithm = new MyersDiff();

		for (int i = 0; i < revisions.size(); ++i) {

			Patch tempPatch = DiffUtils.diff(doc,
					revisions.get(i).getContent(), diffAlgorithm);

			doc = revisions.get(i).getContent();
			
			ArrayList<Delta> deltas = new ArrayList<Delta>(tempPatch
					.getDeltas());

			MyPatch myPatch = logootEngine.generatePatch(deltas);
			logootEngine.deliver(myPatch);

		}

	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

}
