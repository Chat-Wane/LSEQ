package alma.fr.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WikipediaQuery {

	/*
	 * Connect to wikipedia & extract some data
	 */
	public static ArrayList<Revision> Query(URL url) {

		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setRequestMethod("GET");
			con.connect();
			con.getResponseMessage();
			BufferedReader br = new BufferedReader(new InputStreamReader(con
					.getInputStream()));

			XMLRevisionReader reader = new XMLRevisionReader(br);
			con.disconnect();
			return reader.getRevisions();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
