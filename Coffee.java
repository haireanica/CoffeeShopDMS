/**
 * Coffee class: Represents a Coffee object in the Coffee Shop system.
 * This class stores information about a coffee drink, including its ID number,
 * name, size, calorie content, price, dairy contents, and caffeine status.
 */
public class Coffee {

    private String DrinkID;
    private String Name;
    private String Size;
    private int Calories;
    private double Price;
    private boolean Dairy;
    private boolean Decaf;

    /**
     * Constructs a Coffee object with all attributes initialized.
     *
     * @param DrinkID unique identifier for the drink
     * @param Name name of the coffee drink
     * @param Size size of the drink (Small, Medium, Large)
     * @param Calories number of calories in the drink
     * @param Price price of the drink
     * @param Dairy true if the drink contains dairy, false otherwise
     * @param Decaf true if the drink is decaffeinated, false otherwise
     */
    public Coffee (String DrinkID, String Name, String Size, int Calories, double Price, boolean Dairy, boolean Decaf) {
        this.DrinkID = DrinkID;
        this.Name = Name;
        this.Size = Size;
        this.Calories = Calories;
        this.Price = Price;
        this.Dairy = Dairy;
        this.Decaf = Decaf;
    }

    /**
     * Gets the drink ID.
     * @return the drink ID
     */
    public String getDrinkID() {return DrinkID;}
    /**
     * Sets the drink ID.
     * @param DrinkID the new drink ID
     */
    public void setDrinkID(String DrinkID) {
        this.DrinkID = DrinkID;
    }

    /**
     * Gets the name of the coffee.
     * @return the coffee name
     */
    public String getName() {return Name;}
    /**
     * Sets the name of the coffee.
     * @param Name the new coffee name
     */
    public void setName(String Name) {
        this.Name = Name;
    }


    /**
     * Gets the size of the coffee.
     * @return the size
     */
    public String getSize() {
        return Size;
    }

    /**
     * Sets the size of the coffee.
     * @param Size the new size
     */
    public void setSize(String Size) {
        this.Size = Size;
    }

    /**
     * Gets the calorie count.
     * @return the number of calories
     */
    public int getCalories() {
        return Calories;
    }

    /**
     * Sets the calorie count.
     * @param Calories the new calorie value
     */
    public void setCalories(int Calories) {
        this.Calories = Calories;
    }

    /**
     * Gets the price of the coffee.
     * @return the price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Sets the price of the coffee.
     * @param Price the new price
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }
    /**
     * Checks if the drink contains dairy.
     * @return true if it contains dairy, false otherwise
     */
    public boolean getDairy() {
        return Dairy;
    }

    /**
     * Sets whether the drink contains dairy.
     * @param Dairy true if it contains dairy
     */
    public void setDairy(boolean Dairy) {
        this.Dairy = Dairy;
    }

    /**
     * Checks if the drink is decaffeinated.
     * @return true if decaf, false otherwise
     */
    public boolean getDecaf() {
        return Decaf;
    }

    /**
     * Sets whether the drink is decaffeinated.
     * @param Decaf true if decaf
     */
    public void setDecaf(boolean Decaf) {
        this.Decaf = Decaf;
    }

    /**
     * Generates a formatted string containing all attributes of the Coffee object,
     * including ID, name, size, calories, price, and dietary information.
     *
     * @return a detailed string representation of this Coffee
     */
    @Override
    public String toString() {
        return "DrinkID: " + DrinkID + " | Name: " + Name + " |  Size: " + Size + " | Calories: " + Calories + " | Price: $" + String.format("%.2f", Price)  + " | Dairy: " + Dairy + " | Decaf: " + Decaf;
    }

}
