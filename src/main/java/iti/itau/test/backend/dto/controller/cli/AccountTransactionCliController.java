package iti.itau.test.backend.dto.controller.cli;

import java.io.File;
import java.io.IOException;
import java.util.List;

import iti.itau.test.backend.dto.AccountTransactionDTO;
import iti.itau.test.backend.dto.AccountTransactionResultReportDTO;
import iti.itau.test.backend.service.AccountTransactionService;
import iti.itau.test.backend.service.AccountTransactionServiceImpl;

public class AccountTransactionCliController {
    public static final String NEWLINE = System.getProperty("line.separator", "\n");
    
    private AccountTransactionService service;
   
    public AccountTransactionCliController() {
        super();
        this.service = new AccountTransactionServiceImpl();
    }
    
    public void printAccountTransactionSummaryResults(File file) throws IOException {
        List<AccountTransactionDTO> accountTransactions = this.service.parseLogFile(file);
        AccountTransactionResultReportDTO result = this.service.getResultReport(accountTransactions);
        
        System.out.printf("Most spent category: %s%s", result.getMostSpentCategory(), NEWLINE);
        System.out.printf("Most spent month: %s%s", result.getMostSpentMonth().name(), NEWLINE);
        System.out.printf("Total spent: %.2f%s", result.getTotalSpent().doubleValue(), NEWLINE);
        System.out.printf("Total received: %.2f%s", result.getTotalReceived().doubleValue(), NEWLINE);
        System.out.printf("Total balance: %.2f%s", result.getAccountBalance(), NEWLINE);
    }
}
