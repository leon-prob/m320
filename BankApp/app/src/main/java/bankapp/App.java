// AI assistance was used for design pattern suggestions.
// and implementation guidance. All final code has been reviewed and manually implemented.
package bankapp;

import bankapp.cli.BankCliController;

public class App {

    public static void main(String[] args) {
        try {
            BankCliController controller = new BankCliController();
            controller.run();
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
