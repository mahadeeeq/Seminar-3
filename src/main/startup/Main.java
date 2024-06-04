package startup;

import controller.Controller;
import integration.ExternalSystemCreator;
import integration.Printer;
import view.View;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    
    /**
     * The main method used to start the application.
     * 
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        ExternalSystemCreator creator = new ExternalSystemCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        View view = new View(contr);
        view.runFakeExecution();
    }
}
