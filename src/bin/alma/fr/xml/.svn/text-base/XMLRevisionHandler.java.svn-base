/**
 WikimediaRevisionsExtractor
 Copyright (C) 2008  Stéphane Weiss

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package alma.fr.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLRevisionHandler extends DefaultHandler {

	ArrayList<Revision> revisions = new ArrayList<Revision>();
	Revision current;
	StringBuffer sb;
	
	
	public XMLRevisionHandler() {
	}
	
	public XMLRevisionHandler(ArrayList<Revision> revs) {
		revisions = revs;
	}
	

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		try{
		super.characters(ch, start, length);
		sb.append(new String(ch,start,length));
		}
		catch(SAXException s){
			sb.append(s.getException());
		}
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(qName.equals("rev")){
			if (!(sb.toString().length()==0)){ // Delete empty rev :  <rev />
			current.setContent(sb.toString());
			revisions.add(current);
			}
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("rev")) {
			current=new Revision();
			current.setUser(attributes.getValue("user"));
			current.setTimestamp(attributes.getValue("timestamp"));
			current.setId(Integer.parseInt(attributes.getValue("revid")));
			sb=new StringBuffer();
		}

	}

	public ArrayList<Revision> getRevisions() {
		return revisions;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("FATAL ERROR: XML not well-formed");
		super.fatalError(e);
	}

	
	
}
