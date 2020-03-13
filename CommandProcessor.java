import java.io.IOException;

public class CommandProcessor {

	public static String run(PetStore p, String command) throws PetStoreException {
		command = command.toLowerCase();

		// commands that have variables
		if (command.contains("[") && command.contains("]")) {
			String input = command.substring(command.indexOf("[") + 1, command.indexOf("]"));
			String[] variables = input.split(" ");

			// add pet []
			if (command.contains("add new pet ")) {
				if (variables.length != 3 || !PetStoreHelper.isValidId(variables[1])) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				p.createPet(variables[1], variables[0], PetStoreHelper.parsePrice(variables[2]));
				return "Pet successfully added!";
			}
			// update price []
			else if (command.contains("update price ")) {
				if (variables.length != 2 || !PetStoreHelper.isValidId(variables[0])) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				p.updatePet(variables[0].toUpperCase(), PetStoreHelper.parsePrice(variables[1]));
				return "Price successfully updated!";
			}
			// buy pet []
			else if (command.contains("buy pet ")) {
				if (variables.length != 1 || !PetStoreHelper.isValidId(variables[0])) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				p.deletePet(variables[0].toUpperCase());
				return "Pet successfully bought!";
			}
			// search by id []
			else if (command.contains("search by id ")) {
				if (variables.length != 1 || !PetStoreHelper.isValidId(variables[0])) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				return p.searchById(variables[0].toUpperCase());
			}
			// search by price range []
			else if (command.contains("search by price range ")) {
				if (variables.length != 3) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				return p.searchByPrice(PetStoreHelper.parsePrice(variables[0]),
						PetStoreHelper.parsePrice(variables[2]));
			}
			// search by price []
			else if (command.contains("search by price ")) {
				if (variables.length != 1) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				return p.searchByPrice(PetStoreHelper.parsePrice(variables[0]));
			}
			// search by type []
			else if (command.contains("search by type ")) {
				if (variables.length != 1) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				return p.searchByType(variables[0]);
			}
			// save to text file []
			else if (command.contains("save to text file ")) {
				if (variables.length != 1) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				try {
					p.saveToTextFile(variables[0]);
				} catch (IOException e) {
					return "Unable to save as file.";
				}

				return "File saved.";
			}
			// save to xml file []
			else if (command.contains("save to xml file ")) {
				if (variables.length != 1) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				p.saveToXMLFile(variables[0]);

				return "File saved.";
			}
			// initialize from xml file []
			else if (command.contains("initialize from xml file ")) {
				if (variables.length != 1) {
					throw new PetStoreException("Supplied variables are invalid.");
				}

				p.readFromXMLFile(variables[0]);

				return "File loaded.";
			}
		} else {
			// read inventory
			if (command.equals("read inventory")) {
				return p.readPet();
			}
			// inventory count
			else if (command.equals("get inventory count")) {
				return p.countTypes();
			}
			// sort by id
			else if (command.equals("sort by id")) {
				return p.sortById();
			}
			// sort by price
			else if (command.equals("sort by price")) {
				return p.sortByPrice();
			}
			// get valid pet types
			else if (command.equals("get valid pet types")) {
				return p.getValidPetTypes();
			}

		}
		return "Invalid command. Please try again.";
	}
}