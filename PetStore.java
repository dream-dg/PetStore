import java.io.*;
import java.util.*;

public class PetStore {

	// fields
	private ArrayList<Pet> petList;
	private HashMap<String, Pet> idMap;
	private HashMap<String, ArrayList<Pet>> typeMap;
	private final HashSet<String> VALID_PET_TYPES;

	// constructor
	public PetStore() {
		this.petList = new ArrayList<Pet>();
		this.idMap = new HashMap<String, Pet>();
		this.typeMap = new HashMap<String, ArrayList<Pet>>();
		this.VALID_PET_TYPES = new HashSet<String>();
		initValidPets();
		initMaps();
	}

	// adds valid pet types to set
	private void initValidPets() {
		VALID_PET_TYPES.add("HAMSTER");
		VALID_PET_TYPES.add("BUNNY");
		VALID_PET_TYPES.add("LIZARD");
		VALID_PET_TYPES.add("GUINEA_PIG");
		VALID_PET_TYPES.add("BIRD");
		VALID_PET_TYPES.add("GERBIL");
		VALID_PET_TYPES.add("CAT");
		VALID_PET_TYPES.add("DOG");
		VALID_PET_TYPES.add("RAT");
		VALID_PET_TYPES.add("CHINCHILLA");
		VALID_PET_TYPES.add("FISH");
	}

	// initializes maps
	private void initMaps() {
		PetStoreHelper.mapIds(idMap, petList);
		PetStoreHelper.mapTypes(typeMap, petList);
	}

	// get valid pet types
	public String getValidPetTypes() {
		return PetStoreHelper.getValidPetTypes(VALID_PET_TYPES);
	}

	// adds new Pet object
	public void createPet(String id, String type, double price) throws PetStoreException {
		id = id.toUpperCase();
		type = type.toUpperCase();

		if (!VALID_PET_TYPES.contains(type)) {
			throw new PetStoreException(type + " is not a valid pet. Please check valid pet types.");
		} else if (idMap.containsKey(id)) {
			throw new PetStoreException("Pet " + id + " already exists. Please read inventory.");
		}

		Pet p = new Pet();
		p.setId(id);
		p.setType(type);
		p.setPrice(price);

		petList.add(p);
		initMaps();
	}

	// reads inventory ArrayList
	public String readPet() {
		return PetStoreHelper.getListElements(petList);
	}

	// updates price of Pet object
	public void updatePet(String id, double price) throws PetStoreException {
		if (idMap.containsKey(id)) {
			Pet p = idMap.get(id);
			p.setPrice(price);
		} else {
			throw new PetStoreException("Pet " + id + " is not in inventory.");
		}
	}

	// removes pet from inventory (pet sold)
	public void deletePet(String id) throws PetStoreException {
		if (idMap.containsKey(id)) {
			Pet p = idMap.get(id);
			petList.remove(p);
			initMaps();

		} else {
			throw new PetStoreException("Pet " + id + " is not in inventory.");
		}
	}

	// returns string with number of each type of pet
	public String countTypes() {
		return PetStoreHelper.countTypes(typeMap);
	}

	// searches for pet by id
	public String searchById(String id) throws PetStoreException {
		return PetStoreSearcher.searchById(id, idMap).toString();
	}

	// searches for pets with a specific price
	public String searchByPrice(double price) throws PetStoreException {
		return PetStoreSearcher.searchByPrice(petList, price);
	}

	// searches for pets in price range
	public String searchByPrice(double startPrice, double endPrice) throws PetStoreException {
		return PetStoreSearcher.searchByPrice(petList, startPrice, endPrice);
	}

	// searches for pets by type
	public String searchByType(String type) throws PetStoreException {
		return PetStoreSearcher.searchByType(type, typeMap);
	}

	// sorts pets by id
	public String sortById() {
		return PetStoreSorter.sortById(petList);
	}

	// sorts pets by price
	public String sortByPrice() {
		return PetStoreSorter.sortByPrice(petList);
	}

	// saves inventory as new file
	public void saveToTextFile(String fileName) throws IOException {
		PetStoreHelper.saveToTextFile(petList, fileName);
	}

	// saves inventory as new file
	public void saveToXMLFile(String fileName) throws PetStoreException {
		PetStoreHelper.saveToXMLFile(petList, fileName);
	}

	// reads petList from an xml file
	public void readFromXMLFile(String fileName) throws PetStoreException {
		this.petList = PetStoreHelper.readFromXMLFile(fileName, this.VALID_PET_TYPES);
		initMaps();
	}

	// processes user's command
	public String processCommand(String command) throws PetStoreException {
		return CommandProcessor.run(this, command);
	}
}