package iti.itau.test.backend.controller.cli;

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
        
        StringBuilder output = new StringBuilder();
        output.append("Most spent category: ").append(result.getMostSpentCategory()).append(NEWLINE);
        output.append("Most spent month: ").append(result.getMostSpentMonth().name()).append(NEWLINE);
        output.append("Total spent: ").append(result.getTotalSpent().doubleValue()).append(NEWLINE);
        output.append("Total received: ").append(result.getTotalReceived().doubleValue()).append(NEWLINE);
        output.append("Total balance: ").append(result.getAccountBalance().doubleValue()).append(NEWLINE);
        
        System.out.print(output.toString());
    }
}
