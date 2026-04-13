import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 * CoffeeMenu class: Represents a menu of Coffee objects.
 *
 * This class manages a collection of coffees and provides methods to
 * add, delete, update, and retrieve coffee items. additional functionality includes
 * loading data from files and applying discounts.
 */
public class CoffeeMenu {
    // this ArrayList will store all the coffee objects in the menu
    public ArrayList<Coffee> coffees;

    // constructor initializes the coffees list so it’s not null
    public CoffeeMenu(){
        coffees = new ArrayList<>();
    }

    /**
     * Adds a coffee to the menu after checking it is valid and not a duplicate.
     *
     * @param coffee the Coffee object to add
     * @return true if successfully added, false otherwise
     */    public boolean addCoffee(Coffee coffee){
        // if the coffee object is null, don’t add it
        if (coffee == null) {
            return false;
        }
        else if(coffee.getDrinkID().length()!=7){
            return false;
        }
        // if the coffee already exists in the list, don’t add duplicate
        else if (coffees.contains(coffee)) {
            return false;
        } else {
            // otherwise add the coffee to the list
            coffees.add(coffee);
            coffees.sort(Comparator.comparing(Coffee::getDrinkID));
        }
        // return true if successfully added
        return true;
    }
    /**
     * Removes a coffee from the menu.
     *
     * @param coffee the Coffee to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean deleteCoffee(Coffee coffee){
        // if the coffee is null, nothing to delete
        if (coffee == null) {
            return false;
        } else if(!coffees.contains(coffee)){
            return false;
        } else {
            // remove the coffee from the list
            coffees.remove(coffee);
            return true;
        }
    }

    /**
     * Prints all coffees in the menu to the console.
     *
     * @return 0 if printed successfully, 1 if the menu is empty
     */    public int printMenu(){
        // if the list is empty, print message
        if (coffees.isEmpty()){
            System.out.println("Coffee Menu is empty.");
            return 1;
        } else {
            // loop through each coffee and print it
            for (Coffee c : coffees){
                System.out.println(c);
            }
        }
        // return 0 if menu printed successfully
        return 0;
    }

    /**
     * Updates the drink ID of a coffee.
     *
     * @param coffee the coffee to update
     * @param newDrinkID the new drink ID
     * @return true if updated successfully, false otherwise
     */
    public boolean updateDrinkID(Coffee coffee, String newDrinkID){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else if (newDrinkID.length()!=7){
            return false;
        } else {
            // set the new ID
            coffee.setDrinkID(newDrinkID);
            return true;
        }
    }

    /**
     * Updates the name of a coffee.
     *
     * @param coffee the coffee to update
     * @param newName the new name
     * @return true if updated successfully, false otherwise
     */
    public boolean updateName(Coffee coffee, String newName){
        // check if coffee is null
        if (coffee == null) {
            return false;
        }
        // check if new name is blank (invalid)
        else if (newName.isBlank()){
            return false;
        }else {
            // update the name
            coffee.setName(newName);
            return true;
        }
    }

    /**
     * Updates the size of a coffee.
     *
     * @param coffee the coffee to update
     * @param newSize the new size
     * @return true if updated successfully, false otherwise
     */
    public boolean updateSize(Coffee coffee, String newSize){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else if (newSize.isBlank()){
            return false;
        } else {
            // update the size
            coffee.setSize(newSize);
            return true;
        }
    }

    /**
     * Updates the price of a coffee.
     *
     * @param coffee the coffee to update
     * @param newPrice the new price
     * @return true if updated successfully, false otherwise
     */
    public boolean updatePrice(Coffee coffee, double newPrice){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else if (newPrice < 0){
            return false;
        } else {
            // update the price
            coffee.setPrice(newPrice);
            return true;
        }
    }

    /**
     * Updates the calorie count of a coffee.
     *
     * @param coffee the coffee to update
     * @param newCalories the new calorie value
     * @return true if updated successfully, false otherwise
     */
    public boolean updateCalories(Coffee coffee, int newCalories){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else if (newCalories<0){
            return false;
        }

        else {
            // update calories
            coffee.setCalories(newCalories);
            return true;
        }
    }

    /**
     * Updates whether a coffee contains dairy.
     *
     * @param coffee the coffee to update
     * @param newDairy the new dairy value
     * @return true if updated successfully, false otherwise
     */
    public boolean updateDairy(Coffee coffee, boolean newDairy){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else {
            // update dairy value (true/false)
            coffee.setDairy(newDairy);
            return true;
        }
    }

    /**
     * Updates whether a coffee is decaffeinated.
     *
     * @param coffee the coffee to update
     * @param newDecaf the new decaf value
     * @return true if updated successfully, false otherwise
     */
    public boolean updateDecaf(Coffee coffee, boolean newDecaf){
        // check if coffee is null
        if (coffee == null) {
            return false;
        } else {
            // update decaf value (true/false)
            coffee.setDecaf(newDecaf);
            return true;
        }
    }

    /**
     * Loads coffee data from a file and adds valid entries to the menu.
     * Handles file errors internally if the file cannot be read.
     * handles NumberFormatException errors internally if data is invalid
     *
     * @param filepath the path to the file
     * @param lines list to store file lines
     * @param coffeeMenu the menu to add coffees to
     * @return the number of coffees successfully added
     *
     */
    public int fileLoader(String filepath, List<String> lines, CoffeeMenu coffeeMenu) {
        // keeps track of how many valid coffees were added
        int newCoffees = 0;

        try {
            // create scanner to read file
            Scanner fileScanner = new Scanner(new File(filepath));

            // read each line and store it in the list
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine().trim());
            }

            // close scanner after reading file
            fileScanner.close();

            // loop through each line from the file
            for (String line : lines) {
                // split the line using "-" as delimiter
                String[] parts = line.split("-");

                // if the line doesn’t have exactly 7 parts, skip it
                if (parts.length != 7) continue;

                try {
                    // extract and clean each field
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String size = parts[2].trim().toLowerCase();
                    String caloriesStr = parts[3].trim();
                    String priceStr = parts[4].trim();
                    String dairyStr = parts[5].trim().toLowerCase();
                    String decafStr = parts[6].trim().toLowerCase();

                    // check if ID already exists to avoid duplicates
                    if (coffeeMenu.CheckID(id)) {
                        System.out.println("Skipping line (duplicate ID): " + line);
                        continue;
                    }

                    // try to convert calories from string to int
                    int calories;
                    try {
                        calories = Integer.parseInt(caloriesStr);

                        // make sure calories is positive
                        if (calories <= 0) {
                            System.out.println("Skipping line (invalid calories): " + line);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        // if conversion fails, skip line
                        System.out.println("Skipping line (non-numeric calories): " + line);
                        continue;
                    }

                    // try to convert price from string to double
                    double price;
                    try {
                        price = Double.parseDouble(priceStr);

                        // make sure price is not negative
                        if (price < 0) {
                            System.out.println("Skipping line (invalid price): " + line);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        // if conversion fails, skip line
                        System.out.println("Skipping line (non-numeric price): " + line);
                        continue;
                    }

                    // convert dairy string to boolean
                    boolean dairy;
                    if (dairyStr.equals("yes")) dairy = true;
                    else if (dairyStr.equals("no")) dairy = false;
                    else {
                        // invalid input, skip line
                        System.out.println("Skipping line (invalid Dairy): " + line);
                        continue;
                    }

                    // convert decaf string to boolean
                    boolean decaf;
                    if (decafStr.equals("yes")) decaf = true;
                    else if (decafStr.equals("no")) decaf = false;
                    else {
                        // invalid input, skip line
                        System.out.println("Skipping line (invalid Decaf): " + line);
                        continue;
                    }

                    // create new Coffee object and add it to the menu
                    coffeeMenu.addCoffee(new Coffee(id, name, size, calories, price, dairy, decaf));

                    // increase count of successfully added coffees
                    newCoffees++;

                } catch (Exception e) {
                    // if anything unexpected happens, skip this line
                    continue;
                }
            }
        } catch (Exception e) {
            // catch error if file cannot be read
            System.out.println("Error reading file: " + e.getMessage());
        }

        // return total number of coffees added
        return newCoffees;
    }

    /**
     * Checks if a coffee ID already exists in the menu.
     *
     * @param id the ID to check
     * @return true if the ID exists, false otherwise
     */
    public boolean CheckID(String id) {
        // loop through all coffees
        for (Coffee c : coffees) {
            // if a matching ID is found, return true
            if (c.getDrinkID().equals(id)) {
                return true; // ID already exists
            }
        }
        // return false if ID is not found
        return false;
    }

    /**
     * Returns the list of coffees in the menu.
     *
     * @return the list of coffees
     */
    public List<Coffee> getMenu(){
        return coffees;
    }

    /**
     * Applies a discount to a coffee and updates its price.
     *
     * @param selected the coffee to apply the discount to
     * @param discount the discount percentage
     * @return the new price after discount
     */
    public double onSale(Coffee selected, double discount) {
        // get the original price
        double originalPrice = selected.getPrice();
        if(originalPrice < 0){
            return 0.0;
        }

        // calculate new price after discount
        double newPrice = originalPrice * (1 - (discount / 100.0));

        // update the coffee price
        selected.setPrice(newPrice);

        // print out the price change
        System.out.printf("Sale applied! Original Price: $%.2f | New Price: $%.2f%n", originalPrice, newPrice);

        // print updated coffee info
        System.out.println("Updated coffee: " + selected + "\n\n");

        // return the new price
        return newPrice;
    }

}


