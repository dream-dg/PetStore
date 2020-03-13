import java.io.*;
import java.util.*;
import java.util.regex.*;

//common processes
public class PetStoreHelper {

	// returns string with number of each type of pet
	public static String countTypes(HashMap<String, ArrayList<Pet>> typeMap) {
		String s = "";

		// key = type of pet, value = ArrayList of pets
		// count = size of ArrayList
		for (Map.Entry<String, ArrayList<Pet>> entry : typeMap.entrySet()) {
			s += entry.getKey() + ": " + entry.getValue().size() + "\n";
		}

		return s;
	}

	// returns String of elements in list
	public static String getListElements(ArrayList<Pet> list) {
		String s = "";
		for (Pet p : list) {
			s += p + "\n";
		}

		return s;
	}

	// returns String of valid pet types
	public static String getValidPetTypes(HashSet<String> set) {
		String s = "Valid pet types:  ";
		for (String n : set) {
			s += n + "  ";
		}

		return s;
	}

	// puts pets in map where key = id, value = Pet
	public static void mapIds(HashMap<String, Pet> idMap, ArrayList<Pet> petList) {
		idMap.clear();

		for (Pet p : petList) {
			idMap.put(p.getId(), p);
		}
	}

	// maps pets in map where key = type of pet, value = ArrayList of pets
	public static void mapTypes(HashMap<String, ArrayList<Pet>> typeMap, ArrayList<Pet> petList) {
		typeMap.clear();

		for (Pet p : petList) {
			String type = p.getType();

			if (typeMap.containsKey(type)) {
				ArrayList<Pet> pets = typeMap.get(type);
				pets.add(p);
			} else {
				ArrayList<Pet> pets = new ArrayList<Pet>();
				pets.add(p);
				typeMap.put(type, pets);
			}
		}
	}

	// help = list of commands
	public static String getUserCommands() {
		String s = "Examples of user commands:\n";
		
		s += "Initialize from XML file [inventory.xml]\n" 
				+ "Add new pet [hamster HM3 $12.99]\n"
				+ "Read inventory\n"
				+ "Get inventory count\n"
				+ "Get valid pet types\n"
				+ "Update price [HM1 $11.99]\n"
				+ "Buy pet [HM2]\n"
				+ "Search by id [GP2]\n"
				+ "Search by price [$19.99]\n"
				+ "Search by price range [$10.99 - $19.99]\n"
				+ "Search by type [hamster]\n"
				+ "Sort by id\n"
				+ "Sort by price\n"
				+ "Save to text file [example.txt]\n"
				+ "Save to XML file [example.xml]\n"
				+ "Help\n"
				+ "Quit\n";

		s += "\n*Commands are case insensitive\n"
				+ "**Variables must be between brackets and follow order in examples\n"
				+ "***Pets IDs must contain 2 letters and 1 number (ex. BU2)";

		return s;
	}

	// parses price from String to Double
	public static double parsePrice(String price) throws PetStoreException {
		if (!price.contains("$")) {
			throw new PetStoreException("Supplied variable (price) is invalid");
		}

		return Double.parseDouble(price.substring(price.indexOf("$") + 1));
	}

	// checks if id is in a form such as HM1
	public static boolean isValidId(String id) {
		String regex = "[A-Za-z]{2}[0-9]{1}";
		return Pattern.matches(regex, id);
	}

	// saves sorted petList to a file (name specified by client)
	public static void saveToTextFile(ArrayList<Pet> petList, String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(printFormatted(petList));
		writer.close();
	}

	// returns formatted String of sorted petList
	private static String printFormatted(ArrayList<Pet> petList) {
		PetStoreSorter.sortById(petList);
		String s = "ID\tPet\tPrice\n";

		for (Pet p : petList) {
			s += p.getType() + "\t" + p.getId() + "\t" + p.getPrice() + "\n";
		}

		return s;
	}

	// reads petList from an xml file
	public static ArrayList<Pet> readFromXMLFile(String fileName, Set<String> validTypes) throws PetStoreException {
		InventoryReader reader = new InventoryReader();
		ArrayList<Pet> petList;
		petList = reader.getPetList(fileName, validTypes);

		return petList;
	}

	// saves current/updated petList to an xml file
	public static void saveToXMLFile(ArrayList<Pet> petList, String fileName) throws PetStoreException {
		InventoryWriter writer = new InventoryWriter();
		writer.writeFile(Pet.getRootCollectionTag(), petList, fileName);
	}
}