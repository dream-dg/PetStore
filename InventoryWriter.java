import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

// reverse of InventoryReader
public class InventoryWriter {

	// writes xml file with petList
	public void writeFile(String root, ArrayList<Pet> petList, String path) throws PetStoreException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// creates PETS root element
			Element rootElement = doc.createElement(root);

			// append each child element to root element
			doc.appendChild(rootElement);
			for (Pet p : petList) {
				rootElement.appendChild(getPet(doc, p));
			}

			// data written to file
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File(path));
			transformer.transform(source, file);

		} catch (ParserConfigurationException | TransformerException e) {
			throw new PetStoreException("Save to XML file failed");
		}
	}

	private Node getPet(Document doc, Pet p) {
		Element e = doc.createElement(Pet.getRootTag());

		// creates elements with fields of Pet object
		e.appendChild(getPetElements(doc, e, Pet.getIdTag(), p.getId()));
		e.appendChild(getPetElements(doc, e, Pet.getTypeTag(), p.getType()));
		e.appendChild(getPetElements(doc, e, Pet.getPriceTag(), p.getPrice() + ""));

		return e;
	}

	// used by getPet to add to add text nodes
	private Node getPetElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}