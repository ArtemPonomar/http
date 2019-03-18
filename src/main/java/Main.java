import entytis.Pet;
import entytis.Status;
import http_requests.HttpController;
import util.PetGenerator;

import java.util.Scanner;

public class Main {
    private static PetGenerator petGenerator = new PetGenerator();
    private static HttpController httpController = new HttpController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Add new pet to the store / update an existing pet");
            System.out.println("2. Get pet by ID");
            System.out.println("3. Update pet with ID");
            System.out.println("4. Delete pet with ID");

            for (String input = "E"; !input.equals("back"); ) {
                System.out.println("\nEnter 'back' to return to main menu or enter task number.");
                input = scanner.next();
                switch (input) {
                    case "1":
                        executeTask1(scanner);
                        break;
                    case "2":
                        executeTask2(scanner);
                        break;
                    case "3":
                        executeTask3(scanner);
                        break;
                    case "4":
                        executeTask4(scanner);
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
            }
            System.out.println("Want to continue? (yes/no)");
        } while (!scanner.next().equals("no"));
    }

    private static void executeTask1(Scanner scanner) {
        System.out.println("1. Add new pet to the store / update an existing pet");
        System.out.println("Enter the ID and name of pet: (10 Dogo)");
        Pet pet = petGenerator.generatePet(Integer.parseInt(scanner.next()), scanner.next());
        System.out.println("Waiting for response ...");
        System.out.println(httpController.postPet(pet));
    }

    private static void executeTask2(Scanner scanner) {
        System.out.println("2. Get pet by ID");
        System.out.println("Enter the ID of pet: (10)");
        int id = Integer.parseInt(scanner.next());
        System.out.println("Waiting for response ...");
        System.out.println(httpController.getPetByID(id));
    }

    private static void executeTask3(Scanner scanner) {
        System.out.println("3. Update pet with ID");
        System.out.println("Enter the ID of pet, new name and new status: (10 Dogo2 sold/available/pending)");
        System.out.println(httpController.updatePetWithID(Integer.parseInt(scanner.next()), scanner.next(), Status.valueOf(scanner.next())));
        System.out.println("Waiting for response ...");
    }

    private static void executeTask4(Scanner scanner) {
        System.out.println("4. Delete pet with ID");
        System.out.println("Enter the ID of pet to delete: (10)");
        int id = Integer.parseInt(scanner.next());
        System.out.println("Waiting for response ...");
        httpController.deletePetWithID(id);
    }
}
