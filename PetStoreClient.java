import java.util.Scanner;

//driver program
public class PetStoreClient {
	public static void main(String[] args) {
		
		PetStore happyPets = new PetStore();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Happy Pets pet store!");

		while (true) {
			System.out.println();
			System.out.println("Enter a command or type \"help\" to the get list of commands.");
			String command = scan.nextLine();
			System.out.println();

			if (command.equalsIgnoreCase("quit")) {
				scan.close();
				break;

			} else if (command.equalsIgnoreCase("help")) {
				System.out.println(PetStoreHelper.getUserCommands());

			} else {
				try {
					System.out.println(happyPets.processCommand(command));

				} catch (PetStoreException e) {
					System.err.println(e);
				}
			}
		}
		
		System.out.println("Thank you for visting Happy Pets!");
	}
}