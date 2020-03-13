import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

// reverse of InventoryWriter
public class InventoryReader {

	// reads xml file and returns list with created Pet objects
	public ArrayList<Pet> getPetList(String path, Set<String> validTypes) throws PetStoreException {

		File xml = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		ArrayList<Pet> petList = new ArrayList<Pet>();
		
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(Pet.getRootTag());

			// converts elements to Pet objects
			// adds pets to ArrayList
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Pet p = this.getPet(nodeList.item(i));
				
				if (!validTypes.contains(p.getType())) {
					System.out.println(p.getType() + " is not a valid pet. Please check valid pet types.");
				}
				else {
					petList.add(p);
				}	
			}

		} catch (SAXException | ParserConfigurationException | IOException e) {
			
			throw new PetStoreException("XML file is invalid");
		}

		return petList;
	}

	// gets element nodes and sets them as fields in new Pet object
	private Pet getPet(Node node) {
		Pet p = new Pet();

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			p.setId(this.getTagValue(Pet.getIdTag(), e));
			p.setType(this.getTagValue(Pet.getTypeTag(), e));
			p.setPrice(Double.parseDouble(this.getTagValue(Pet.getPriceTag(), e)));
		}

		return p;
	}

	// used to get node values to set as fields in Pet object
	private String getTagValue(String tag, Element e) {
		NodeList nodeList = e.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);

		return node.getNodeValue();
	}
}