import java.util.*;

// methods used by PetStore to search for pets in petList
public class PetStoreSearcher {

	// searches for pet by id
	public static Pet searchById(String id, HashMap<String, Pet> idMap) throws PetStoreException {
		if (!idMap.containsKey(id)) {
			throw new PetStoreException("Pet " + id + " is not in inventory.");
		}

		Pet p = idMap.get(id);
		return p;
	}

	// returns String of pets that match price
	public static String searchByPrice(ArrayList<Pet> petList, double price) throws PetStoreException {
		ArrayList<Pet> matches = priceMatch(petList, price);

		if (matches.isEmpty()) {
			throw new PetStoreException("No pets found in price range");
		}

		return PetStoreHelper.getListElements(matches);
	}

	// searches for pets by single price
	private static ArrayList<Pet> priceMatch(ArrayList<Pet> petList, double price) {
		// new lists created so petList isn't modified
		ArrayList<Pet> copiedList = new ArrayList<Pet>(petList);
		ArrayList<Pet> priceMatch = new ArrayList<Pet>();
		PetStoreSorter.sortArrayByPrice(copiedList);

		// adds each price match to list
		while (true) {
			// i = index of price match
			int i = binarySearch(copiedList, price);
			if (i != -1) {
				priceMatch.add(copiedList.get(i));
				copiedList.remove(i);
			} else {
				break; // no price matches left
			}
		}

		// returns pets that match price
		return priceMatch;
	}

	// searches for pets in price range
	public static String searchByPrice(ArrayList<Pet> petList, double startPrice, double endPrice)
			throws PetStoreException {

		if (startPrice > endPrice) {
			throw new PetStoreException("Invalid price range.");
		}

		ArrayList<Pet> range = new ArrayList<Pet>();

		// searches for pets between startPrice and endPrice
		for (Pet p : petList) {
			if ((p.getPrice() >= startPrice) && (p.getPrice() <= endPrice)) {
				range.add(p);
			}
		}

		// if no pets found
		if (range.isEmpty()) {
			throw new PetStoreException("No pets found in price range.");
		}

		PetStoreSorter.sortArrayByPrice(range);
		return PetStoreHelper.getListElements(range);
	}

	// binary search algorithm returns index of price match
	private static int binarySearch(ArrayList<Pet> list, double target) {
		int beginning = 0;
		int end = list.size() - 1;
		int mid;

		while (beginning <= end) {
			mid = (beginning + end) / 2;

			// increases mid if mid price is less than target
			if (list.get(mid).getPrice() < target) {
				beginning = mid + 1;
			}
			// lowers mid if mid price is more than target
			else if (list.get(mid).getPrice() > target) {
				end = mid - 1;
			}
			// if mid price is target
			else {
				return mid;
			}
		}

		// target price not found in list
		return -1;
	}

	// searches for pets by type
	public static String searchByType(String type, HashMap<String, ArrayList<Pet>> typeMap) throws PetStoreException {
		type = type.toUpperCase();
		
		if (!typeMap.containsKey(type)) {
			throw new PetStoreException("Invalid type of pet");
		}

		return PetStoreHelper.getListElements(typeMap.get(type));
	}
}