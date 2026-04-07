import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    //global variable to set styles
    Font titleFont = new Font("Cinzel Decorative", Font.BOLD, 45);
    Font textFont = new Font("Gill Sans", Font.PLAIN, 15);
    Font buttonFont = new Font("Gill Sans", Font.PLAIN, 20);
    Font tableTopFont = new Font("Gill Sans", Font.PLAIN, 22);
    Font tableFont = new Font("Gill Sans", Font.PLAIN, 16);


    Color backgroundColor = new Color(40, 16, 113);
    Color textColor = new Color(5, 5, 5);
    Color buttonTextColor = new Color(255, 215, 0);
    Color popupTitle = (new Color(255, 215, 0));
    Dimension buttonSize = new Dimension(120, 50);
    Color popupColor= new Color(63, 35, 152);
    Dimension smallButtonSize = new Dimension(120, 50);

    //global variables to capture and manipulate data
    CoffeeMenu coffeeMenu = new CoffeeMenu();
    JFrame frame = new JFrame();
    DefaultTableModel model;
    JTable coffeeTable;
    CoffeeDB coffeeDB = new CoffeeDB();
    Connection connection;

    //gridBagConstraint Object
    GridBagConstraints c = new GridBagConstraints();

    //method to reset constraints
    public GridBagConstraints cReset(GridBagConstraints c){
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipady = 0;
        c.ipadx = 0;
        return c;
    }

    //method to create JTable with coffee object data
    public JTable makeTable(){

        coffeeTable = new JTable();
        coffeeTable.getTableHeader().setFont(tableTopFont);
        coffeeTable.getTableHeader().setForeground(backgroundColor);
        coffeeTable.setRowHeight(20);
        coffeeTable.setFont(tableFont);
        coffeeTable.setShowGrid(true);
        coffeeTable.setGridColor(Color.GRAY);
        coffeeTable.setForeground(backgroundColor);
        coffeeTable.setBackground(new Color(195, 176, 251, 255));
        if(connection != null) {
            coffeeTable.setModel(coffeeDB.selectCoffeeTable());
        }
        return coffeeTable;
    }

    //method to update/refresh table
    public void refreshTable() {
        if(connection != null) {
            //getting table from database
            coffeeTable.setModel(coffeeDB.selectCoffeeTable());
            coffeeTable.revalidate();
            coffeeTable.repaint();
        }
    }

    //method to create add coffee popup window
    public void addPopup(JFrame parentFrame){
        JDialog addDialog = new JDialog(parentFrame, "Add Coffee", true);
        addDialog.setSize(500,400);
        addDialog.setBackground(backgroundColor);
        addDialog.setLocationRelativeTo(parentFrame);
        addDialog.setLayout(new GridBagLayout());

        //panels of popup add dialog
        JPanel addTopPanel = new JPanel();
        addTopPanel.setLayout(new GridBagLayout());
        addTopPanel.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        addDialog.add(addTopPanel,c);
        cReset(c);

        JPanel addForm = new JPanel();
        addForm.setLayout(new GridBagLayout());
        addForm.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.70;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        addDialog.add(addForm,c);
        cReset(c);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        addDialog.add(bottomPanel,c);
        cReset(c);

        //labels and text fields for add coffee
        JLabel titleLabel = new JLabel("Build A Brew\uD83D\uDD28");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(popupTitle);
        addTopPanel.add(titleLabel);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);


        JLabel updateID = new JLabel("Enter 7 digit ID");
        updateID.setForeground(Color.WHITE);
        updateID.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(updateID, c);
        cReset(c);

        JTextField updateIDField = new JTextField(10);
        updateIDField.setHorizontalAlignment(JTextField.LEFT);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(updateIDField,c);
        cReset(c);

        JLabel addName = new JLabel("Enter name of coffee");
        addName.setForeground(Color.WHITE);
        addName.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addName, c);
        cReset(c);

        JTextField nameField = new JTextField(10);
        nameField.setHorizontalAlignment(JTextField.LEFT);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(nameField,c);
        cReset(c);

        JLabel addCalories = new JLabel("Enter calories");
        addCalories.setForeground(Color.WHITE);
        addCalories.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addCalories, c);
        cReset(c);

        JTextField caloriesField = new JTextField(10);
        caloriesField.setHorizontalAlignment(JTextField.LEFT);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(caloriesField,c);
        cReset(c);

        JLabel addPrice = new JLabel("Enter price");
        addPrice.setForeground(Color.WHITE);
        addPrice.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addPrice, c);
        cReset(c);

        JTextField priceField = new JTextField(10);
        priceField.setHorizontalAlignment(JTextField.LEFT);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(priceField,c);
        cReset(c);

        JLabel addSize = new JLabel("Select size");
        addSize.setForeground(Color.WHITE);
        addSize.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addSize, c);
        cReset(c);

        JComboBox sizeBox = new JComboBox(new String[] {"Select size","Small", "Medium", "Large"});
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(sizeBox,c);
        cReset(c);

        JLabel addDairy = new JLabel("Contains dairy?");
        addDairy.setForeground(Color.WHITE);
        addDairy.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addDairy, c);
        cReset(c);

        JCheckBox dairyBox = new JCheckBox("YES");
        dairyBox.setForeground(Color.WHITE);
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(dairyBox,c);
        cReset(c);

        JLabel addDecaf = new JLabel("Is it decaf?");
        addDecaf.setForeground(Color.WHITE);
        addDecaf.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(addDecaf, c);
        cReset(c);

        JCheckBox decafBox = new JCheckBox("YES");
        decafBox.setForeground(Color.WHITE);
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        addForm.add(decafBox,c);
        cReset(c);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 0;

        bottomPanel.add(errorLabel,c);
        //buttons for Add popup form
        JButton addButton = new JButton("Add Coffee");
        c.gridx = 0;
        c.gridy = 1;
        bottomPanel.add(addButton, c);
        cReset(c);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = updateIDField.getText();
                String calories = caloriesField.getText();
                String price = priceField.getText();
                errorLabel.setText(" ");
                if(connection == null){
                    errorLabel.setText("No connection Database ");
                }
                else if (updateIDField.getText().isBlank()){
                    errorLabel.setText("ID cannot be blank");
                }
                else if(updateIDField.getText().length()!=7){
                    errorLabel.setText("ID must be 7 digits");
                }
                else if (!input.matches("\\d+")){
                    errorLabel.setText("ID must be all numbers, and positive");
                }
                else if(coffeeDB.isDuplicate(input)){
                    errorLabel.setText("Coffee already exists");
                }
                else if (nameField.getText().isBlank()){
                    errorLabel.setText("Name cannot be blank");
                }
                else if(sizeBox.getSelectedIndex()==0){
                    errorLabel.setText("Size cannot be empty");
                }
                else if(caloriesField.getText().isBlank()) {
                    errorLabel.setText("Calories cannot be empty");
                }
                else if (!calories.matches("\\d+")){
                    errorLabel.setText("calories must be all numbers, and positive");
                }
                else if (priceField.getText().isBlank()) {
                    errorLabel.setText("Price cannot be blank");
                }
                else if (!price.matches("\\d+(\\.\\d+)?")){
                    errorLabel.setText("price must be all numbers, and positive");
                }
                else {

                    Coffee coffee = new Coffee(
                            updateIDField.getText(),
                            nameField.getText(),
                            sizeBox.getSelectedItem().toString(),
                            Integer.parseInt(caloriesField.getText()),
                            Double.parseDouble(priceField.getText()),
                            dairyBox.isSelected(),
                            decafBox.isSelected()
                    );
                   coffeeDB.addRow(coffee);
                    refreshTable();
                    addDialog.dispose();
                }
            }
        });

        JButton clear = new JButton("Clear");
        c.gridx = 1;
        c.gridy = 1;
        bottomPanel.add(clear, c);
        cReset(c);
        clear.addActionListener(e -> {
            updateIDField.setText("");
            nameField.setText("");
            caloriesField.setText("");
            priceField.setText("");

            sizeBox.setSelectedIndex(0);

            dairyBox.setSelected(false);
            decafBox.setSelected(false);
        });

        JButton exit = new JButton("Exit");
        c.gridx = 2;
        c.gridy = 1;
        bottomPanel.add(exit, c);
        cReset(c);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog.dispose();
            }
        });

        addDialog.setVisible(true);
    }
    //method to create update popup window
    public void updatePopup(JFrame parentFrame,Coffee coffee) {
        JDialog updateDialog = new JDialog(parentFrame, "Update Coffee", true);
        updateDialog.setSize(500,400);
        updateDialog.setBackground(backgroundColor);
        updateDialog.setLocationRelativeTo(parentFrame);
        updateDialog.setLayout(new GridBagLayout());

        //panels of popup add dialog
        JPanel updateTopPanel = new JPanel();
        updateTopPanel.setLayout(new GridBagLayout());
        updateTopPanel.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateTopPanel,c);
        cReset(c);

        JPanel updateForm = new JPanel();
        updateForm.setLayout(new GridBagLayout());
        updateForm.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.70;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateForm,c);
        cReset(c);

        JPanel updateBottomPanel = new JPanel();
        updateBottomPanel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateBottomPanel,c);
        cReset(c);

        //labels and text fields for add coffee
        JLabel titleLabel = new JLabel("ReBuild A Brew\uD83D\uDD28");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(popupTitle);
        updateTopPanel.add(titleLabel);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);


        JLabel updateID = new JLabel("Enter 7 digit ID");
        updateID.setForeground(Color.WHITE);
        updateID.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(updateID, c);
        cReset(c);
        JTextField updateIDField = new JTextField(10);
        updateIDField.setHorizontalAlignment(JTextField.LEFT);
        updateIDField.setText(coffee.getDrinkID());
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(updateIDField,c);
        cReset(c);

        JLabel addName = new JLabel("Enter name of coffee");
        addName.setForeground(Color.WHITE);
        addName.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addName, c);
        cReset(c);

        JTextField nameField = new JTextField(10);
        nameField.setHorizontalAlignment(JTextField.LEFT);
        nameField.setText(coffee.getName());
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(nameField,c);
        cReset(c);

        JLabel addCalories = new JLabel("Enter calories");
        addCalories.setForeground(Color.WHITE);
        addCalories.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addCalories, c);
        cReset(c);

        JTextField caloriesField = new JTextField(10);
        caloriesField.setHorizontalAlignment(JTextField.LEFT);
        caloriesField.setText(String.valueOf(coffee.getCalories()));
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(caloriesField,c);
        cReset(c);

        JLabel addPrice = new JLabel("Enter price");
        addPrice.setForeground(Color.WHITE);
        addPrice.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addPrice, c);
        cReset(c);

        JTextField priceField = new JTextField(10);
        priceField.setHorizontalAlignment(JTextField.LEFT);
        priceField.setText(String.valueOf(coffee.getPrice()));
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(priceField,c);
        cReset(c);

        JLabel addSize = new JLabel("Select size");
        addSize.setForeground(Color.WHITE);
        addSize.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addSize, c);
        cReset(c);

        JComboBox sizeBox = new JComboBox(new String[] {"Select size","Small", "Medium", "Large"});
        sizeBox.setSelectedItem(coffee.getSize());
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(sizeBox,c);
        cReset(c);

        JLabel addDairy = new JLabel("Contains dairy?");
        addDairy.setForeground(Color.WHITE);
        addDairy.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addDairy, c);
        cReset(c);

        JCheckBox dairyBox = new JCheckBox("YES");
        dairyBox.setForeground(Color.WHITE);
        dairyBox.setSelected(coffee.getDairy());
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(dairyBox,c);
        cReset(c);

        JLabel addDecaf = new JLabel("Is it decaf?");
        addDecaf.setForeground(Color.WHITE);
        addDecaf.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addDecaf, c);
        cReset(c);

        JCheckBox decafBox = new JCheckBox("YES");
        decafBox.setForeground(Color.WHITE);
        decafBox.setSelected(coffee.getDecaf());
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(decafBox,c);
        cReset(c);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 0;

        updateBottomPanel.add(errorLabel,c);

        //buttons for update popup form
        JButton addButton = new JButton("Update Coffee");
        c.gridx = 0;
        c.gridy = 1;
        updateBottomPanel.add(addButton, c);
        cReset(c);
        //action listener for update coffee button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //casting inputs to string for validation
                String input = updateIDField.getText();
                String calories = caloriesField.getText();
                String price = priceField.getText();
                //row selected from table
                int row = coffeeTable.getSelectedRow();
                //delete coffee from database
                coffeeDB.deleteRow(coffee.getDrinkID());
                //initially invisible error label to change what message is displayed to user
                errorLabel.setText(" ");
                //validation error messages
                if (updateIDField.getText().isBlank()){
                    errorLabel.setText("ID cannot be blank");
                }
                else if(updateIDField.getText().length()!=7){
                    errorLabel.setText("ID must be 7 digits");
                }
                else if (!input.matches("\\d+")){
                    errorLabel.setText("ID must be all numbers, and positive");
                }
                else if(coffeeDB.isDuplicate(input)){
                    errorLabel.setText("Coffee already exists");
                }
                else if (nameField.getText().isBlank()){
                    errorLabel.setText("Name cannot be blank");
                }
                else if(sizeBox.getSelectedIndex()==0){
                    errorLabel.setText("Size cannot be empty");
                }
                else if(caloriesField.getText().isBlank()) {
                    errorLabel.setText("Calories cannot be empty");
                }
                else if (!calories.matches("\\d+")){
                    errorLabel.setText("calories must be all numbers, and positive");
                }
                else if (priceField.getText().isBlank()) {
                    errorLabel.setText("Price cannot be blank");
                }
                else if (!price.matches("\\d+(\\.\\d+)?")){
                    errorLabel.setText("price must be all numbers, and positive");
                }
                //if all validation passes update fields with input data
                else {
                    coffee.setDrinkID(updateIDField.getText());
                    coffee.setName(nameField.getText());
                    coffee.setSize(sizeBox.getSelectedItem().toString());
                    coffee.setCalories(Integer.parseInt(caloriesField.getText()));
                    coffee.setPrice(Double.parseDouble(priceField.getText()));
                    coffee.setDairy(dairyBox.isSelected());
                    coffee.setDecaf(decafBox.isSelected());
                    coffeeDB.addRow(coffee);
                    refreshTable();
                    updateDialog.dispose();
                }
            }});
        //button to clear all fields back to selected
        JButton clear = new JButton("Clear");
        c.gridx = 1;
        c.gridy = 1;
        updateBottomPanel.add(clear, c);
        cReset(c);
        clear.addActionListener(e -> {
            updateIDField.setText(coffee.getDrinkID());
            nameField.setText(coffee.getName());
            caloriesField.setText(String.valueOf(coffee.getCalories()));
            priceField.setText(String.valueOf(coffee.getPrice()));
            if(coffee.getSize().equals("Small")){
                sizeBox.setSelectedIndex(1);
            }else if(coffee.getSize().equals("Medium")){
                sizeBox.setSelectedIndex(2);
            } else if(coffee.getSize().equals("Lƒarge")){
                sizeBox.setSelectedIndex(3);
            }
            if (coffee.getDairy()){
                dairyBox.setSelected(true);
            } else {
                dairyBox.setSelected(false);
            }
            if(coffee.getDecaf()){
                decafBox.setSelected(true);
            } else {
                decafBox.setSelected(false);
            }
        });
        //exit button to close popup
        JButton exit = new JButton("Exit");
        c.gridx = 2;
        c.gridy = 1;
        updateBottomPanel.add(exit, c);
        cReset(c);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDialog.dispose();
            }
        });

        updateDialog.setVisible(true);
    }


    //method to create sale popup window
    public void salePopup(JFrame parentFrame,Coffee coffee) {
        JDialog updateDialog = new JDialog(parentFrame, "Coffee Sale", true);
        updateDialog.setSize(500, 400);
        updateDialog.setBackground(popupColor);
        updateDialog.setLocationRelativeTo(parentFrame);
        updateDialog.setLayout(new GridBagLayout());

        //panels of popup add dialog
        JPanel updateTopPanel = new JPanel();
        updateTopPanel.setLayout(new GridBagLayout());
        updateTopPanel.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateTopPanel, c);
        cReset(c);

        JPanel updateForm = new JPanel();
        updateForm.setLayout(new GridBagLayout());
        updateForm.setBackground(popupColor);
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.70;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateForm, c);
        cReset(c);

        JPanel updateBottomPanel = new JPanel();
        updateBottomPanel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        updateDialog.add(updateBottomPanel, c);
        cReset(c);

        JLabel titleLabel = new JLabel("Coffee Sale\uD83C\uDFF7\uFE0F");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(popupTitle);
        updateTopPanel.add(titleLabel);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel addPrice = new JLabel("Current price($)");
        addPrice.setForeground(Color.WHITE);
        addPrice.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addPrice, c);
        cReset(c);

        JTextField priceField = new JTextField(10);
        priceField.setHorizontalAlignment(JTextField.LEFT);
        priceField.setText(String.valueOf(coffee.getPrice()));
        priceField.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(priceField,c);
        cReset(c);
        //label for slider to adjust % off sale
        JLabel salePercent = new JLabel("Select Percent Off");
        salePercent.setForeground(Color.WHITE);
        salePercent.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(salePercent, c);
        cReset(c);
        //JSlider for user ease, set to maximum of 50% off
        JSlider saleSlider = new JSlider(0, 50, 0);
        saleSlider.setMajorTickSpacing(10);
        saleSlider.setMinorTickSpacing(5);
        saleSlider.setSnapToTicks(true);
        saleSlider.setPaintTicks(true);
        saleSlider.setPaintLabels(true);
        saleSlider.setBackground(Color.WHITE);
        saleSlider.setForeground(Color.WHITE);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(saleSlider, c);
        cReset(c);

        //Label to display selected percentage off clearly
        JLabel percentLabel = new JLabel("0% OFF");
        percentLabel.setForeground(Color.WHITE);
        c.gridx = 2;
        c.gridy = 2;
        updateForm.add(percentLabel, c);
        cReset(c);



        JLabel addNewPrice = new JLabel("New Price($)");
        addNewPrice.setForeground(Color.WHITE);
        addNewPrice.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(addNewPrice, c);
        cReset(c);

        //textField that updates new price as sale is adjusted
        JTextField newPriceField = new JTextField(10);
        newPriceField.setHorizontalAlignment(JTextField.LEFT);
        newPriceField.setText(String.valueOf(coffee.getPrice()));
        newPriceField.setEditable(false);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        updateForm.add(newPriceField,c);
        cReset(c);

        double originalPrice = coffee.getPrice();
        //action listener for slider to update new price
        saleSlider.addChangeListener(e -> {
            int percent = saleSlider.getValue();
            percentLabel.setText(percent + "% OFF");
            double newPrice = originalPrice * (1 - ((double) percent/ 100.0));

            newPriceField.setText(String.format("%.2f", newPrice));
        });

        //button to apply sale and update price of coffee
        JButton saleButton = new JButton("Set Sale");
        c.gridx = 0;
        c.gridy = 0;
        updateBottomPanel.add(saleButton, c);
        cReset(c);
        saleButton.addActionListener(e -> {
            int percent = saleSlider.getValue();

          //  double newPrice = originalPrice * (1 - (percent / 100.0));

           coffeeDB.dbSale(coffee,percent);

            reload(parentFrame);
            updateDialog.dispose();
        });

        //button to close popup
        JButton exit = new JButton("Exit");
        c.gridx = 2;
        c.gridy = 0;
        updateBottomPanel.add(exit, c);
        cReset(c);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDialog.dispose();
            }
        });
        updateDialog.setVisible(true);
    }




    //method to create file loader popup window
    public void openFilePopup(JFrame parentFrame) {

        // Create the dialog
        JDialog fileDialog = new JDialog(parentFrame, "File Selection", true);
        fileDialog.setSize(500, 400);
        fileDialog.setLocationRelativeTo(parentFrame);

        // Create the file chooser
        JFileChooser fileChooser = new JFileChooser();
        //filtering to only allow text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only '.db' files allowed", "db");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        //action listener to handle approve/cancel
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if user clicks open
                if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                    //get selected file
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.toString();
                    connection = coffeeDB.connect(filePath);
                    //reload GUI to show table
                    refreshTable();
                    //close popup
                    fileDialog.dispose();
                    //if user clicks cancel
                } else if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                    //close popup
                    fileDialog.dispose();
                } else {
                    System.out.println("Error loading file");
                }
            }
        });
        // Add the JFileChooser to the dialog
        fileDialog.add(fileChooser);
        fileDialog.setVisible(true);
    }

    //method to start GUI and create JFrame of main window and adjust settings
    public void startGUI(JFrame frame) {
        frame.setTitle("Coffee Menu Management System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(startScreen(frame));
        refreshTable();
        frame.setVisible(true);
    }

    //method to 'reload' startScreen
    public void reload(JFrame frame) {
        frame.setContentPane(startScreen(frame));
        frame.revalidate();
        frame.repaint();
    }
    //Main screen/panel of GUI
    public Container startScreen(JFrame frame){

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(backgroundColor);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        c.gridy = 0;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(topPanel,c);
        cReset(c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(new JLabel(" "), c);
        buttonPanel.setBackground(backgroundColor);
        c.gridy = 1;
        c.weighty = 0.10;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(buttonPanel,c);
        cReset(c);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setBackground(Color.white);
        c.gridy = 2;
        c.weighty = 0.60;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 50, 0, 50);
        panel.add(tablePanel,c);
        cReset(c);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(backgroundColor);
        c.gridy = 3;
        c.weighty = 0.15;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(bottomPanel,c);
        cReset(c);

        //Title Label ⋆˙⟡ ⟡˙⋆
        JLabel titleLabel = new JLabel(" ⋆˙⛥ Coffee⋆Menu⋆Wizard ⛥˙⋆");
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setFont(titleFont);
        titleLabel.setBackground(backgroundColor);
        topPanel.setBackground(backgroundColor);
        topPanel.add(titleLabel);

        //Buttons for CRUD Operations + Custom method
        JButton addButton = new JButton("➕ Add");
        addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(buttonSize));
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(addButton,c);
        cReset(c);
        addButton.addActionListener(click-> {addPopup(frame);});

        JButton deleteButton = new JButton("― Delete");
        deleteButton.setFont(buttonFont);
        deleteButton.setPreferredSize(new Dimension(buttonSize));
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(deleteButton,c);
        cReset(c);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = coffeeTable.getSelectedRow();
                if (row != -1) {
                    String coffeeID = coffeeTable.getValueAt(row, 0).toString();
                    coffeeDB.deleteRow(coffeeID);
                }
                refreshTable();
            }
        });
        JButton updateButton = new JButton("✎ Update");
        updateButton.setFont(buttonFont);
        updateButton.setPreferredSize(new Dimension(buttonSize));
        c.gridx = 2;
        c.gridy = 0;
        buttonPanel.add(updateButton,c);
        cReset(c);


        JButton saleButton = new JButton("\uD83D\uDCB2Sale");
        saleButton.setFont(buttonFont);
        saleButton.setPreferredSize(new Dimension(buttonSize));
        c.gridx = 3;
        c.gridy = 0;
        buttonPanel.add(saleButton,c);
        cReset(c);

        //JTable to display system data to user
        coffeeTable = makeTable();
        JScrollPane scrollPane = new JScrollPane(coffeeTable);
        c.fill = GridBagConstraints.BOTH;
        tablePanel.add(scrollPane, c);
        cReset(c);


        updateButton.addActionListener(e -> {
            int selectedRow = coffeeTable.getSelectedRow();

            if (selectedRow != -1) {

                // Get ID from table (column 0)
                String id = coffeeTable.getValueAt(selectedRow, 0).toString();

                // Find matching Coffee object
              Coffee selectedCoffee = coffeeDB.getCoffee(id);

                // Open popup with selected coffee
                if (selectedCoffee != null) {
                    updatePopup(frame, selectedCoffee);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row first.");
            }
        });

        saleButton.addActionListener(e -> {
            int selectedRow = coffeeTable.getSelectedRow();

            if (selectedRow != -1) {

                // Get ID from table (column 0)
                String id = coffeeTable.getValueAt(selectedRow, 0).toString();

                // Find matching Coffee object
                Coffee selectedCoffee = coffeeDB.getCoffee(id);
                // Open popup with selected coffee
                if (selectedCoffee != null) {
                    salePopup(frame, selectedCoffee);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row first.");
            }
        });





        //bottomPanel buttons (Load File & Exit)
        JButton loadFileButton = new JButton("\uD83D\uDCC1 Connect Database ");
        loadFileButton.setPreferredSize(new Dimension(220, 50));
        loadFileButton.setFont(buttonFont);
        c.gridx = 0;
        c.gridy = 0;
        bottomPanel.add(loadFileButton,c);
        cReset(c);
        //Action Listener to open popup to select .txt file

        loadFileButton.addActionListener(click -> openFilePopup(frame));



        //SPACERS
        JLabel spacer2 = new JLabel("");
        c.gridx = 1;
        c.gridy = 0;
        bottomPanel.add(spacer2,c);
        JLabel spacer1 = new JLabel("");
        c.gridx = 2;
        c.gridy = 0;
        bottomPanel.add(spacer1,c);
        cReset(c);



        JButton exitButton = new JButton("Exit ➜");
        c.gridx = 3;
        c.gridy = 0;
        exitButton.setPreferredSize(buttonSize);
        exitButton.setFont(buttonFont);
        bottomPanel.add(exitButton,c);
        cReset(c);

        //Action listener to allow user to close GUI
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Goodbye");
                System.exit(0);
            }
        });

        return panel;
    }
}


