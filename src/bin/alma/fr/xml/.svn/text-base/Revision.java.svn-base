package alma.fr.xml;

import java.util.ArrayList;

public class Revision {

	private ArrayList<String> content = new ArrayList<String>();

	// META DATA
	// userName
	private String user = "";
	// timeStamp
	private String timestamp = "";
	// revId
	private int id = 0;

	public void setUser(String user) {
		this.user = user;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Revision() {
	}

	public Revision(String content) {
		String temp[] = content.split("\n");
		for (int i = 0; i < temp.length; ++i) {
			this.content.add(temp[i]);
		}
	}

	public ArrayList<String> getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content.clear();
		String temp[] = content.split("\n");
		for (int i = 0; i < temp.length; ++i) {
			this.content.add(temp[i]);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Revision) {
			Revision rev = (Revision) obj;
			return this.content.equals(rev.content);
		}
		return false;
	}

	public int size() {
		return content.size();
	}

}
