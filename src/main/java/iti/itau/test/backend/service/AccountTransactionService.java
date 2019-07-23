package iti.itau.test.backend.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import iti.itau.test.backend.dto.AccountTransactionDTO;
import iti.itau.test.backend.dto.AccountTransactionResultReportDTO;

/**
 * Service with business logic for account transactions
 * 
 * @author andre.ghigo
 */
public interface AccountTransactionService {
    /**
     * Parses a .log file in order to extract data about account transactions such as month, description, value and category
     * 
     * @param file file to be parsed
     * 
     * @return list of account transactions extracted from the file
     * 
     * @throws IOException in case of an I/O operation failure during file parsing
     */
    List<AccountTransactionDTO> parseLogFile(File file) throws IOException;
    
    /**
     * Gets the most spent category name of a given account transactions
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return {@code Optional} with the category name (empty name for expenses without category), or empty {@code Optional} if there are no transactions
     */
    Optional<String> getMostSpentCategory(List<AccountTransactionDTO> accountTransactions);
    
    /**
     * Gets the most spent category month of a given account transactions
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return {@code Optional} with the month, or empty {@code Optional} if there are no transactions
     */
    Optional<Month> getMostSpentMonth(List<AccountTransactionDTO> accountTransactions);
    
    /**
     * Gets the total of money spent from a given account transaction list
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return total of money spent
     */
    BigDecimal getTotalSpent(List<AccountTransactionDTO> accountTransactions);
    
    /**
     * Gets the total of money received from a given account transaction list
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return total of money received
     */
    BigDecimal getTotalReceived(List<AccountTransactionDTO> accountTransactions);
    
    /**
     * Gets the account balance from a given account transaction list
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return the account balance value
     */
    BigDecimal getAccountBalance(List<AccountTransactionDTO> accountTransactions);
    
    /**
     * Gets a report with summary results from of given transaction list such as:
     * 
     * <p> most spent category
     * <p> most spent month
     * <p> total of money spent
     * <p> total of money received
     * <p> account balance
     * 
     * @param accountTransactions list of account transactions
     * 
     * @return instance with the result report
     */
    AccountTransactionResultReportDTO getResultReport(List<AccountTransactionDTO> accountTransactions);
}
