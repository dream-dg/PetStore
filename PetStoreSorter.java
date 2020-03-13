import java.util.*;

//methods used by PetStore to sort pets in petList
public class PetStoreSorter {
	
	// uses quick sort to sort pets by id
	public static String sortById(ArrayList<Pet> petList) {
		ArrayList<Pet> copiedList = new ArrayList<Pet>(petList);
		return PetStoreHelper.getListElements(quickSort(copiedList));
	}

	// uses quick sort algorithm to sort pets by id
	private static ArrayList<Pet> quickSort(ArrayList<Pet> petList) {
		int n = petList.size();
		
		if (n <= 1) {
	        return petList;
	    }
	   
	    ArrayList<Pet> less = new ArrayList<Pet>();
	    ArrayList<Pet> more = new ArrayList<Pet>();
	    Pet p = petList.get(n - 1); 
	    
	    for (int i = 0; i < n - 1; i++) {
	    	// compare ids of pets
	    	if ((petList.get(i).getId().compareTo(p.getId()) < 0)) 
	            less.add(petList.get(i));    
	        else
	            more.add(petList.get(i));   
	    }

	    less = quickSort(less);
	    more = quickSort(more);

	    less.add(p);
	    less.addAll(more);

	    return less;
	}

	public static String sortByPrice(ArrayList<Pet> petList) {
		ArrayList<Pet> newList = new ArrayList<Pet>(petList);
		sortArrayByPrice(newList);
		return PetStoreHelper.getListElements(newList);
	}

	public static void sortArrayByPrice(ArrayList<Pet> list) {
		// uses pet compareTo method
		Collections.sort(list);
	}
	
}