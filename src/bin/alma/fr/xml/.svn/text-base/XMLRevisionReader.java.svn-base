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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLRevisionReader {

	ArrayList<Revision> rev=new ArrayList<Revision>();

	public XMLRevisionReader(BufferedReader br) {
		XMLReader xr;
		try {
			xr = XMLReaderFactory.createXMLReader();
			try {
				xr.setFeature("http://xml.org/sax/features/namespace-prefixes",
						true);
			} catch (SAXException e) {
				System.err.println("Cannot activate validation.");
			}

			XMLRevisionHandler handler = new XMLRevisionHandler();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
			InputSource is = new InputSource(br);
			xr.parse(is);
			rev = handler.getRevisions();

		} catch (SAXException e) {
			System.out.println("Sax execption");
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception");
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO execption");
			e.printStackTrace();
		}
	}

	public ArrayList<Revision> getRevisions() {
		return rev;
	}

}
