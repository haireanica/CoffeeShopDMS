public class Coffee {
    //Attributes
    private String DrinkID;
    private String Name;
    private String Size;
    private int Calories;
    private double Price;
    private boolean Dairy;
    private boolean Decaf;

    //Constructor
    public Coffee (String DrinkID, String Name, String Size, int Calories, double Price, boolean Dairy, boolean Decaf) {
        this.DrinkID = DrinkID;
        this.Name = Name;
        this.Size = Size;
        this.Calories = Calories;
        this.Price = Price;
        this.Dairy = Dairy;
        this.Decaf = Decaf;
    }
    //Getters and Setters
    public String getDrinkID() {
        return DrinkID;
    }
    public void setDrinkID(String DrinkID) {
        this.DrinkID = DrinkID;
    }


    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }


    public String getSize() {
        return Size;
    }
    public void setSize(String Size) {
        this.Size = Size;
    }


    public int getCalories() {
        return Calories;
    }
    public void setCalories(int Calories) {
        this.Calories = Calories;
    }


    public double getPrice() {
        return Price;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }


    public boolean getDairy() {
        return Dairy;
    }
    public void setDairy(boolean Dairy) {
        this.Dairy = Dairy;
    }


    public boolean getDecaf() {
        return Decaf;
    }
    public void setDecaf(boolean Decaf) {
        this.Decaf = Decaf;
    }

    @Override
    public String toString() {
        return "DrinkID: " + DrinkID + " | Name: " + Name + " |  Size: " + Size + " | Calories: " + Calories + " | Price: $" + String.format("%.2f", Price)  + " | Dairy: " + Dairy + " | Decaf: " + Decaf;
    }

}
