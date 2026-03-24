import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeMenu {
    // this ArrayList will store all the coffee objects in the menu
    public ArrayList<Coffee> coffees;

    // constructor initializes the coffees list so it’s not null
    public CoffeeMenu(){
        coffees = new ArrayList<>();
    }

    // method to add a coffee to the menu
    public boolean addCoffee(Coffee coffee){
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
        }
        // return true if successfully added
        return true;
    }

    // method to delete a coffee from the menu
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

    // method to print all coffees in the menu
    public int printMenu(){
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

    // update the drink ID of a coffee
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

    // update the name of a coffee
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

    // update the size of a coffee
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

    // update the price of a coffee
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

    // update the calories of a coffee
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

    // update if the coffee has dairy
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

    // update if the coffee is decaf
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

    // method to load coffees from a file
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

    // method to check if an ID already exists in the menu
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

    // returns the full list of coffees
    public List<Coffee> getMenu(){
        return coffees;
    }

    // method to apply a discount to a selected coffee
    public double onSale(Coffee selected, double discount) {
        // get the original price
        double originalPrice = selected.getPrice();
        if(originalPrice < 0){
            return 0.0;
        }

        // calculate new price after discount
        double newPrice = originalPrice * (1 - discount / 100);

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