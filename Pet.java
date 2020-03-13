public class Pet implements Comparable<Pet> {

	// fields
	private String id;
	private String type;
	private double price;

	// static methods that return element tags
	public static String getRootCollectionTag() {
		return "PETS";
	}

	public static String getRootTag() {
		return "PET";
	}

	public static String getIdTag() {
		return "id";
	}

	public static String getTypeTag() {
		return "type";
	}

	public static String getPriceTag() {
		return "price";
	}

	// accessors of Pet fields
	public String getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public double getPrice() {
		return this.price;
	}

	// mutators of Pet fields
	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// toString method invoked when Pet is printed
	public String toString() {
		return this.type + " " + this.id + ": $" + this.price;
	}

	// used to compare Pet objects by price
	public int compareTo(Pet other) {
		Double thisPrice = (Double) price;
		return thisPrice.compareTo((Double) other.getPrice());
	}
}