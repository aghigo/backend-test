package iti.itau.test.backend.application;
import java.io.File;
import java.io.IOException;

import iti.itau.test.backend.controller.cli.AccountTransactionCliController;

public class Application {
    private AccountTransactionCliController controller;
    
    Application() {
        super();
        this.controller = new AccountTransactionCliController();
    }
    
    public void printAccountTransactionSummaryResults(File file) throws IOException {
        this.controller.printAccountTransactionSummaryResults(file);
    }
    
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("File path not provided");
        } else {
            String filePath = args[0];
            File file = new File(filePath);
            Application application = new Application();
            try {
                application.printAccountTransactionSummaryResults(file);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
