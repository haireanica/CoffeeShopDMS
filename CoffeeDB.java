import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * CoffeeDB class: Handles all database operations for the Coffee Menu Management System.
 * This class manages the connection to the SQLite database and provides
 * methods to perform CRUD operations such as inserting, deleting, retrieving,
 * and updating coffee data records.
 */
public class CoffeeDB {
    // url variable to store database filepath
    String url;
    //connection object to connect gui with database
    Connection connection;

    /**
     * Establishes a connection to the SQLite database using the given file path.
     * handles SQLException errors internally if connection fails
     * @param dbFilePath the file path to the database
     * @return the database connection object
     */
    public Connection connect(String dbFilePath) {
        //database filepath
        url = "jdbc:sqlite:" + dbFilePath;
        try {
            //try to form connection with database
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Database Connection successful.");
            }
        } catch (SQLException e) {
            System.err.println("Connection Error: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the current database connection.
     *handles SQLException errors internally if disconnection fails
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from DB.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all coffee records from the database and formats them
     * into a DefaultTableModel for display in a JTable.
     *
     * @return a table model containing coffee data
     */
    public DefaultTableModel selectCoffeeTable() {
        //query to select all from table and order by ID number
        String query = "SELECT * FROM Coffees ORDER BY DrinkID ASC";
        //naming columns in table model
        String[] columns = {"ID", "Name", "Size", "Calories", "Price($)", "Dairy", "Decaf"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            //making table uneditable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //create statement object to preform query on db and store in results set
        // then get the object data for that row and add to table model.
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("DrinkID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Size"),
                        resultSet.getInt("Calories"),
                        resultSet.getDouble("Price"),
                        resultSet.getBoolean("Dairy"),
                        resultSet.getBoolean("Decaf")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    /**
     * Deletes a coffee record from the database based on its ID.
     *
     * @param drinkID the ID of the coffee to delete
     */    public void deleteRow(String drinkID) {
        //sql query to delete object from table
        String toDelete = "DELETE FROM Coffees WHERE DrinkID = " + drinkID;
        try {
            //executing query on database
            Statement statement = connection.createStatement();
            statement.executeUpdate(toDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a coffee with the given ID already exists in the database.
     *handles SQLException errors internally if validation fails
     * @param drinkID the ID to check
     * @return true if the ID exists, false otherwise
     */    public boolean isDuplicate(String drinkID) {
        //sql query to grab object from db
        String toCheck = "SELECT * FROM Coffees WHERE DrinkID = " + drinkID;
        //result set object to store queried information
        ResultSet resultSet = null;
        try {
            //creating message to send to db
            Statement statement = connection.createStatement();
            //executing message
            resultSet = statement.executeQuery(toCheck);
            //if result set is able to preform query that means ID exists and is a duplicate
            if (resultSet.next()) {
                return true;
                //if not, ID doesn't exist yet
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Inserts a new coffee record into the database.
     *handles SQLException errors internally if insertion fails
     * @param coffee the Coffee object containing data to insert
     */    public void addRow(Coffee coffee) {
        //getting data from object and assigning to variables
        String DrinkID = coffee.getDrinkID();
        String Name = coffee.getName();
        String Size = coffee.getSize();
        int Calories = coffee.getCalories();
        double Price = coffee.getPrice();
        boolean Dairy = coffee.getDairy();
        boolean Decaf = coffee.getDecaf();
        //sql insert statement to add coffee to database table  row
        String insert = "INSERT INTO Coffees (DrinkID, Name, Size, Calories, Price, Dairy, Decaf) VALUES(?,?,?,?,?,?,?)";
        try {
            //inserting data into database table
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, DrinkID);
            statement.setString(2, Name);
            statement.setString(3, Size);
            statement.setInt(4, Calories);
            statement.setDouble(5, Price);
            statement.setBoolean(6, Dairy);
            statement.setBoolean(7, Decaf);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Retrieves a Coffee object from the database based on its ID.
     *handles SQLException errors internally if query fails
     * @param DrinkID the ID of the coffee to retrieve
     * @return the corresponding Coffee object, or null if not found
     */    public Coffee getCoffee(String DrinkID) {
        Coffee coffee =  null;
        //query to retrieve data
        String query = "SELECT * FROM Coffees WHERE DrinkID = " + DrinkID;
        try{
            //executing query and adding data from database table columns to coffee object
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                coffee = new Coffee (
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getDouble(5),
                                resultSet.getBoolean(6),
                                resultSet.getBoolean(7));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return coffee;
    }

    /**
     * Applies a percentage discount to a coffee's price in the database.
     *handles SQLException errors internally if query fails
     * @param coffee the coffee to update
     * @param percent the discount percentage to apply
     */    public void dbSale(Coffee coffee,double percent) {
        double originalPrice = coffee.getPrice();
        // calculate new price after discount
        //calculating new price after sale applied
        double newPrice = originalPrice * (1 - (percent / 100.0));
        //query to update price to apply sale
        String query = "UPDATE Coffees SET Price = " + newPrice + " WHERE DrinkID = " + coffee.getDrinkID();
        try{
            //execute query
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


