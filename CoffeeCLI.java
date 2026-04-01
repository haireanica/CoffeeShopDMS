import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class CoffeeCLI {
    // ArrayList to store Coffee objects
    CoffeeMenu coffeeMenu = new CoffeeMenu();

    //Scanner to read input for user
    Scanner scanner = new Scanner(System.in);

    //Method to display menu to user
    public int displayMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("\t***PLEASE SELECT A MENU OPTION BY NUMBER***");
        System.out.println("---------------------------------------------------");
        System.out.println("1. ADD COFFEE MANUALLY");
        System.out.println("2. ADD COFFEE(S) FROM .TXT FILE");
        System.out.println("3. REMOVE COFFEE FROM SYSTEM");
        System.out.println("4. LIST ALL COFFEES");
        System.out.println("5. PUT COFFEE DRINK ON SALE");
        System.out.println("6. UPDATE COFFEE DRINK ALREADY IN SYSTEM");
        System.out.println("7. EXIT MENU");
        return 0;
    }
    //menu method with methods to correspond to display menu
    public int Menu() {
        //variable to store user choice input
        int choice = 0;

        do {
            displayMenu();
            System.out.print("MAKE YOUR SELECTION: ");
            String input = scanner.nextLine().trim();

            try {
                //converting user input to integer
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 7.");
                continue;
            }

            //switch cases to handle each menu option
            switch (choice) {
                case 1:
                    addCoffeeCLI();// add coffee manually to system
                    break;
                case 2:
                    addCoffeeFileCLI();//read .txt file
                    break;
                case 3:
                    removeCoffeeCLI();// remove coffee from system
                    break;
                case 4:
                    ListCoffeesCLI(); //list all current coffees in system
                    break;
                case 5:
                    onSaleCLI();//change price of drink to sale price
                    break;
                case 6:
                    updateCoffeeCLI();//update coffee object attribute data
                    break;
                case 7:
                    System.out.println("Exiting Menu....");//quit menu/program
                    break;
                default:
                    System.out.println("Invalid choice.");//default case for invalid data
                    break;
            }
        }
        while (choice != 7);
        return 0;
    }


    //Method to add Coffee manually
    public int addCoffeeCLI() {
        String DrinkID;
        String Name;
        String Size;
        int Calories;
        double Price;
        boolean Dairy;
        boolean Decaf;

        //while loop to validate DrinkID
        while (true) {
            System.out.println("Please enter a 7-Digit Drink ID: ");
            DrinkID = scanner.nextLine().trim();
            //check length is correct
            if (DrinkID.length() != 7) {
                System.out.println("Drink ID must exactly be 7 digits.");
                continue;
            }
            //check if all numbers
            boolean allDigits = true;
            for (char c : DrinkID.toCharArray()) {
                if (!Character.isDigit(c)) {
                    allDigits = false;
                    break;
                }
            }

            if (!allDigits) {
                System.out.println("Drink ID must contain only digits.");
                continue;
            }
            //check for duplicate ID
            if (coffeeMenu.CheckID(DrinkID)) {
                System.out.println("Drink ID already exists.");
                continue;
            }

            break;
        }

        //loop to validate name input from user
        while (true) {
            System.out.println("Please enter a Drink Name: ");
            Name = scanner.nextLine().trim();
            if (!Name.isBlank())
                break;
            System.out.println("Drink Name cannot be blank.");
        }

        // loop to validate size
        while (true) {
            System.out.println("Please enter a Drink Size (small, medium, or large): ");
            Size = scanner.nextLine().toLowerCase().trim();
            //check if no input for size
            if (Size.isBlank()) {
                System.out.println("Drink Size cannot be blank.");
                continue;
            }
            //check if valid size
            if (!Size.equals("small") && !Size.equals("medium") && !Size.equals("large")) {
                System.out.println("Drink Size must be either 'small', 'medium', or 'large'.");
                continue;
            }

            break;
        }
        // loop to validate calories
        while (true) {
            System.out.println("Enter Calories: ");
            String input = scanner.nextLine().trim();
            //check for input
            if (input.isBlank()) {
                System.out.println("Calories cannot be blank.");
                continue;
            }

            try {
                //parsing to int in order to check if numerical and type casting to be compatible with constructor
                Calories = Integer.parseInt(input);
                if (Calories <= 0) {
                    System.out.println("Calories must be greater than 0.");
                    continue;
                }
                break; // valid integer
                //exception handling to prevent crash
            } catch (NumberFormatException e) {
                System.out.println("Calories must be a valid integer.");
            }
        }

        while (true) {
            System.out.println("Enter Price: ");
            String input = scanner.nextLine().trim();
            input = input.replace("$","");
            //check if blank
            if (input.isBlank()) {
                System.out.println("Price cannot be blank.");
                continue;
            }

            try {
                //convert to double to check for number and make compatible with constructor
                Price = Double.parseDouble(input);

                if (Price <= 0) {
                    System.out.println("Price cannot be negative or 0.");
                    continue;
                }

                break; // valid input

            } catch (NumberFormatException e) {
                System.out.println("Price must be a valid number.");
            }
        }
        while (true) {
            System.out.println("Does the drink contain Dairy? ('yes' or 'no'): ");
            String input = scanner.nextLine().trim().toLowerCase();
            //not allowing black input
            if (input.isBlank()) {
                System.out.println("Dairy input cannot be blank.Must be 'yes' or 'no'.");
                continue;
            }//ensure yes or no input
            if (input.equals("yes")) {
                Dairy = true;
                break;
            }
            if (input.equals("no")) {
                Dairy = false;
                break;
            } else {
                System.out.println("Invalid input. must be either 'yes' or 'no'.");
            }
        }
        while (true) {
            System.out.println("Does the Drink contain Caffeine? ('yes' or 'no'): ");
            String input = scanner.nextLine().trim().toLowerCase();
            //not allowing blank input
            if (input.isBlank()) {
                System.out.println("Caffeine input cannot be blank.");
                continue;
            }//ensure yes or no input
            if (input.equals("yes")) {
                Decaf = false;
                break;
            }
            if (input.equals("no")) {
                Decaf = true;
                break;
            } else {//invalid input loop again
                System.out.println("Invalid input. must be either 'yes' or 'no'.");
            }
        }
        //add validated coffee object to arraylist
        Coffee newCoffee = new Coffee(DrinkID, Name, Size, Calories, Price, Dairy, Decaf);
        coffeeMenu.addCoffee(newCoffee);
        //display info to user
        System.out.println("Coffee added successfully!");
        System.out.println("Coffee added:");
        System.out.println(newCoffee + "\n\n");
        ListCoffeesCLI();
        return 0;
    }


    //method to add coffee(s) from text file and add to system/list
    public int addCoffeeFileCLI() {
        while (true) {
            System.out.println("Please enter filepath of file to import (.txt) or 'Q' to quit: ");
            String filePath = scanner.nextLine().trim();

            if (filePath.equalsIgnoreCase("Q")) {
                System.out.println("File import cancelled...");
                break;
            }
            //arraylist to store lines of txt file data
            List<String> fileLines = new ArrayList<>();

            coffeeMenu.fileLoader(filePath, fileLines, coffeeMenu);

            System.out.println("File import completed.");
            break; // exit after successful import
        }

        ListCoffeesCLI();
        return 0;// show all coffees
    }


    // method to remove coffee by DrinkID number
    public int removeCoffeeCLI() {
        while (true) {
            System.out.print("To delete a coffee, please enter valid 7-Digit Drink ID #, OR if you would like to exit enter 'Q': ");
            String drinkID = scanner.nextLine().trim();

            if (drinkID.equalsIgnoreCase("Q")) {
                System.out.println("Quitting removal process");
                break; // exit removal loop
            }

            Coffee toRemove = null; // variable to store the coffee to remove

            // Search for coffee with matching ID
            for (Coffee c : coffeeMenu.getMenu()) {
                if (c.getDrinkID().equals(drinkID)) {
                    toRemove = c;
                    break;
                }
            }
            // Remove coffee if found
            if (toRemove != null) {
                System.out.println("Coffee has been removed successfully.");
                System.out.println("Coffee Removed: " + toRemove);
                coffeeMenu.deleteCoffee(toRemove);
                break; // exit loop after removal
            } else {
                System.out.println("Drink ID does not exist. Please try again.");
            }
            // Show updated list after removal

        }
        System.out.println("\n\n");
        ListCoffeesCLI();
        return 0;
    }

    //custom method to calculate drink sale price and update object price
    public int onSaleCLI () {
        if (coffeeMenu.getMenu().isEmpty()) {
            System.out.println("No coffees in the system to put on sale.");
            return 1;
        }

        System.out.print("Enter the 7-digit Drink ID of the coffee to put on sale: ");
        String drinkID = scanner.nextLine().trim();

        Coffee selected = null;
        for (Coffee c : coffeeMenu.getMenu()) {
            if (c.getDrinkID().equals(drinkID)) {
                selected = c;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Drink ID not found. Cannot apply sale.");
            return 1;
        }
        double discount = 0;
        while (true) {
            System.out.print("Enter discount percentage amount (e.g., '20' for 20% off): ");
            String input = scanner.nextLine().trim();
            input = input.replace("%","");
            try {
                discount = Double.parseDouble(input);
                if (discount <= 0 || discount >= 100) {
                    System.out.println("Discount must be greater than 0 and less than 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the discount.");
            }
        }

        coffeeMenu.onSale(selected, discount);

        ListCoffeesCLI();
        return 0;
    }


    // method to select coffee drink by DrinkID and update attribute data
    public int updateCoffeeCLI() {
        if (coffeeMenu.getMenu().isEmpty()) {
            System.out.println("No coffees in the system to update.");
            return 1;
        }

        System.out.print("Enter the 7-digit Drink ID of the coffee to update: ");
        String drinkID = scanner.nextLine().trim();

        Coffee selected = null;
        for (Coffee c : coffeeMenu.getMenu()) {
            if (c.getDrinkID().equals(drinkID)) {
                selected = c;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Drink ID not found. Cannot update.");
            return 1;
        }
        System.out.println("Updating coffee: " + selected);

        // Update Name
        System.out.print("Enter new Name (or press Enter to keep current: " + selected.getName() + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty())
            coffeeMenu.updateName(selected, name);

        // Update Size
        while (true) {
            System.out.print("Enter new Size (small, medium, large) or press Enter to keep current: " + selected.getSize() + ": ");
            String size = scanner.nextLine().trim().toLowerCase();
            if (size.isEmpty()) break;
            if (size.equals("small") || size.equals("medium") || size.equals("large")) {
                coffeeMenu.updateSize(selected, size);
                break;
            } else {
                System.out.println("Invalid size. Must be small, medium, or large.");
            }
        }

        // Update Calories
        while (true) {
            System.out.print("Enter new Calories or press Enter to keep current: " + selected.getCalories() + ": ");
            String cal = scanner.nextLine().trim();
            if (cal.isEmpty()) break;
            try {
                int calories = Integer.parseInt(cal);
                if (calories > 0) {
                    coffeeMenu.updateCalories(selected, calories);
                    break;
                } else {
                    System.out.println("Calories must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        // Update Price
        while (true) {
            System.out.print("Enter new Price or press Enter to keep current: $" + selected.getPrice() + ": ");
            String pr = scanner.nextLine().trim();
            if (pr.isEmpty()) break;
            try {
                double price = Double.parseDouble(pr);
                if (price >= 0) {
                    coffeeMenu.updatePrice(selected, price);
                    break;
                } else {
                    System.out.println("Price cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        // Update Dairy
        while (true) {
            System.out.print("Does the drink contain Dairy? (yes/no) or press Enter to keep current: " + selected.getDairy() + ": ");
            String dairy = scanner.nextLine().trim().toLowerCase();
            if (dairy.isEmpty()) break;
            if (dairy.equals("yes")) {
                coffeeMenu.updateDairy(selected, true);
                break;
            } else if (dairy.equals("no")) {
                coffeeMenu.updateDairy(selected, false);
                break;
            } else {
                System.out.println("Invalid input. Must be yes or no.");
            }
        }

        // Update Decaf
        while (true) {
            System.out.print("Is the drink Decaf? (yes/no) or press Enter to keep current: " + selected.getDecaf() + ": ");
            String decaf = scanner.nextLine().trim().toLowerCase();
            if (decaf.isEmpty()) break;
            if (decaf.equals("yes")) {
                coffeeMenu.updateDecaf(selected, true);
                break;
            } else if (decaf.equals("no")) {
                coffeeMenu.updateDecaf(selected, false);
                break;
            } else {
                System.out.println("Invalid input. Must be yes or no.");
            }
        }
        //display updated info to user
        System.out.println("Coffee updated successfully!");
        System.out.println(selected);
        System.out.println("\n\n");
        ListCoffeesCLI();
        return 0;
    }
    //method to print/display all coffees to console
    public int ListCoffeesCLI() {
        System.out.println("----------ALL COFFEES CURRENTLY IN SYSTEM----------");
        coffeeMenu.printMenu();
        return 0;
    }
}



























