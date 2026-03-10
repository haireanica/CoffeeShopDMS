# CoffeeShopDMS
Java Coffee Menu Management System – Add, remove, update, and manage coffee drinks in a console-based application with file import, sales functionality and data validation.


This Java console program lets users manage a coffee menu easily. Users can add coffee drinks one by one, import multiple drinks from a .txt file, remove drinks, list all drinks, update existing drinks, and put drinks on sale. Each coffee has a unique 7-digit Drink ID, a name, a size, calories, a price, and yes/no indicators for whether it contains dairy or is decaf. The program checks all inputs to make sure they are correct. Drink IDs must be numbers and not repeat, sizes must be small, medium, or large, and yes/no fields are required for dairy and decaf.

The import feature reads from a properly formatted text file where each line follows this structure: DrinkID-Name-Size-Calories-Price-Dairy-Decaf. Any invalid lines or duplicate IDs are skipped automatically. Users can update a drink by entering its Drink ID, and they can change any attribute while keeping others the same. The sale feature lets users apply a discount percentage to a drink’s price.
