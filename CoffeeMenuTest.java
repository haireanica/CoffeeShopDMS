//Fail
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeMenuTest {
    Coffee coffee1;
    Coffee coffee2;
    Coffee coffee3;
    CoffeeMenu coffeeMenu;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        //Dummy coffee objects for testing
        coffee1 = new Coffee("1234567", "Con Pana", "medium", 250, 3.00, true, false);
        coffee2 = new Coffee("123", "Latte", "large", 320, -4.25, true, false);
        //Dummy coffee menu
        coffeeMenu = new CoffeeMenu();
    }

    @org.junit.jupiter.api.Test
    void addCoffee() {
        assertEquals(true, coffeeMenu.addCoffee(coffee1), "Error adding coffee.");
        //bad coffee object invalid ID
        assertEquals(true, coffeeMenu.addCoffee(coffee2), "Error adding coffee.");

    }
    @org.junit.jupiter.api.Test
    void removeCoffee() {
        coffeeMenu.addCoffee(coffee1);
        //coffeeMenu.addCoffee(coffee2);
        assertEquals(true, coffeeMenu.deleteCoffee(coffee1), "Error removing coffee.");
        //bad coffee object (coffee already deleted does not exist)
        assertEquals(true, coffeeMenu.deleteCoffee(coffee1), "Error removing coffee.");

    }
    @org.junit.jupiter.api.Test
    void updateDrinkID() {
        coffeeMenu.addCoffee(coffee1);
        //coffeeMenu.addCoffee(coffee2);
        assertEquals(true, coffeeMenu.updateDrinkID(coffee1, "7654321"), "Error updating coffee ID.");
        //bad coffee object(drink id too short)
        assertEquals(true, coffeeMenu.updateDrinkID(coffee2, "765"), "Error updating coffee ID.");


    }
    @org.junit.jupiter.api.Test
    void updateName() {
        coffeeMenu.addCoffee(coffee1);
        //coffeeMenu.addCoffee(coffee2);
        assertEquals(true, coffeeMenu.updateName(coffee1, "Latte"), "Error updating coffee name.");
        //bad coffee object(blank name)
        assertEquals(true, coffeeMenu.updateName(coffee2, ""), "Error updating coffee name.");

    }
    @org.junit.jupiter.api.Test
    void updateCalories() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee2);

        assertEquals(true, coffeeMenu.updateCalories(coffee1, 275),  "Error updating coffee calories.");
        //bad coffee object(negative calories)
        assertEquals(true, coffeeMenu.updateCalories(coffee2, -2),  "Error updating coffee calories.");

    }
    @org.junit.jupiter.api.Test
    void updatePrice() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee2);

        assertEquals(true, coffeeMenu.updatePrice(coffee1, 4.25),   "Error updating coffee price.");
        //bad coffee object(negative price)
        assertEquals(true, coffeeMenu.updatePrice(coffee2, -1),   "Error updating coffee price.");

    }
    @org.junit.jupiter.api.Test
    void updateDairy() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee3);

        assertEquals(true, coffeeMenu.updateDairy(coffee1, false),    "Error updating coffee dairy.");
        //bad coffee object(empty dairy attribute)
        assertEquals(true, coffeeMenu.updateDairy(coffee3, false),    "Error updating coffee dairy.");

    }
    @org.junit.jupiter.api.Test
    void updateDecaf() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee3);
        assertEquals(true, coffeeMenu.updateDecaf(coffee1, false),     "Error updating coffee decaf.");
        //bad coffee object(empty decaf attribute)
        assertEquals(true, coffeeMenu.updateDecaf(coffee3, false),     "Error updating coffee decaf.");

    }
    @org.junit.jupiter.api.Test
    void updateSize() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee2);
        assertEquals(true, coffeeMenu.updateSize(coffee1, "small"), "Error updating coffee size.");
        //bad coffee object(blank size)
        assertEquals(true, coffeeMenu.updateSize(coffee2, ""), "Error updating coffee size.");


    }
    @org.junit.jupiter.api.Test
    void onSale() {
        coffeeMenu.addCoffee(coffee1);
        coffeeMenu.addCoffee(coffee2);
        assertEquals((coffee1.getPrice()*(0.90)), coffeeMenu.onSale(coffee1, 10), "Error discounting coffee price.");
        //bad coffee object(negative price)
        assertEquals((coffee2.getPrice()*(0.90)), coffeeMenu.onSale(coffee2, 10), "Error discounting coffee price.");
    }
    @org.junit.jupiter.api.Test
    void fileLoader() {
        List<String> fileLines = new ArrayList<>();
        //String content = "1000003-Latte-Large-200-5.25-yes-no\n1000004-Mocha-Medium-250-5.75-yes-no";
        String file = "testfile.txt";

        //file with missing token
        String content = "1000003-Latte-Large-200-5.25-yes-no\nMocha-Medium-250-5.75-yes-no";
        try {
            // Write the string content to the file, creating the file if it doesn't exist
            Files.writeString(Path.of(file), content);
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
        assertEquals(2, coffeeMenu.fileLoader(file, fileLines, coffeeMenu),
                "Error. Could not open and read file correctly.");

    }
}


