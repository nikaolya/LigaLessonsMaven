package homework7;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Warehouse myWarehouse = new Warehouse(3,10);

    public static void main(String[] args) throws IOException {

        // Now these values are set in the contractor, but they can be set manually as well.
   /*     System.out.println("Welcome to the Warehouse managing program.");
        System.out.print("\nPlease enter the desired Warehouse capacity: ");
        myWarehouse.setCapacity(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Please enter the desired maximum amount of each stored item: ");
        myWarehouse.setMaxItemsAmount(scanner.nextInt());
        scanner.nextLine();*/

        System.out.println("\nThe capacity of the warehouse is " + myWarehouse.getCapacity() +".");
        System.out.println("The maximum amount of each item to store is " + myWarehouse.getMaxItemsAmount() + ".");

        String action = "help";
        printInstructions();
        while (!(action.equals("quit"))) {
            System.out.print("\nEnter your desired action: ");
            action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "help":
                    printInstructions();
                    break;
                case "print":
                    myWarehouse.printStoredItems();
                    break;
                case "export":
                    myWarehouse.exportStoredItems();
                    break;
                case "add":
                    addNewItems();
                    break;
                case "take out":
                    takeOutSomeItems();
                    break;
                case "remove":
                    removeItem();
                    break;
                case "search":
                    searchItem();
                    break;
                case "quit":
                    System.out.println("The Warehouse managing program is finished.");
                    action = "quit";
                    break;
                default:
                    System.out.println("Illegal command. Please try again.");
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\nHelp: Please type");
        System.out.println("\t \"help\" - To print action options;");
        System.out.println("\t \"print\" - To print the list of the items in the warehouse;");
        System.out.println("\t \"export\" - To export the list of the items in the warehouse in xlsx format;");
        System.out.println("\t \"add\" - To add an item to the warehouse;");
        System.out.println("\t \"take out\" - To modify an item in the warehouse;");
        System.out.println("\t \"remove\" - To remove an item from the warehouse;");
        System.out.println("\t \"search\" - To search for an item in the warehouse;");
        System.out.println("\t \"quit\" - To quit the program.");
    }

    public static void addNewItems(){
        System.out.print("Item to add: ");
        String newItemTitle = scanner.nextLine();
        System.out.print(capitalized(newItemTitle) + " amount to add: ");
        int newItemAmount = scanner.nextInt();
        scanner.nextLine();
        myWarehouse.addNewItems(newItemTitle, newItemAmount);
    }

    public static void takeOutSomeItems(){
        System.out.print("Item to take out: ");
        String takeOutItemTitle = scanner.nextLine();
        System.out.print(capitalized(takeOutItemTitle) + " amount to take out: ");
        int ItemToTakeOutAmount = scanner.nextInt();
        scanner.nextLine();
        myWarehouse.takeOutSomeItems(takeOutItemTitle, ItemToTakeOutAmount);
    }

    public static boolean searchItem(){
        System.out.print("Item to look for: ");
        String itemToSearch = scanner.nextLine();

        return myWarehouse.searchItem(itemToSearch);
    }

    public static void removeItem(){
        System.out.print("Item to remove: ");
        String itemToRemove = scanner.nextLine();
        myWarehouse.removeItem(itemToRemove);
    }

    public static String capitalized (String str){
        str = str.toLowerCase();
        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        return cap;
    }

}


