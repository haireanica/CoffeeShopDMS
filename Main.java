import javax.swing.*;
/**
*Main class:
*Main entry point of the application.
*The primary function is to initialize GUI and launch main window of application
 */
public class Main {
    /**
     * Main method that starts the program.
     * It creates a GUI object, initializes a JFrame,
     * and passes the frame to the GUI to be set up and displayed.
     */
    public static void main(String[] args) {

        GUI gui = new GUI();
        JFrame frame = new JFrame();
        gui.startGUI(frame);
    }
}
