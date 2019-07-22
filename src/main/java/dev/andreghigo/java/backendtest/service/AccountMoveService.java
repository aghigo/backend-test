package dev.andreghigo.java.backendtest.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.andreghigo.java.backendtest.dto.AccountMoveDTO;
import dev.andreghigo.java.backendtest.dto.AccountMoveReportDTO;

@Service
public interface AccountMoveService {
	/**
	 * Parses the account moves .log file containing data of each move such as date, description, value and category.
	 * 
	 * @param file the file to be parsed
	 * 
	 * @return list of account moves
	 * 
	 * @throws IOException if fails to read the file
	 * 
	 * @throws ParseException if fails to parse the data
	 */
	List<AccountMoveDTO> parseAccountMovesLogFile(File file) throws IOException, ParseException;
	
	/**
	 * Gets the category the client most spent
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the name category name, if any
	 */
	Optional<String> getMostExpensiveCategory(List<AccountMoveDTO> accountMoves);
	
	/**
	 * Gets the month the client most spent
	 * 
	 * @see {@code Month}
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the month, if any
	 */
	Optional<Month> getMostExpensiveMonth(List<AccountMoveDTO> accountMoves);
	
	/**
	 * Gets the total of money spent
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the total spent
	 */
	BigDecimal getTotalMoneySpent(List<AccountMoveDTO> accountMoves);
	
	/**
	 * Gets the total of money received
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the total received
	 */
	BigDecimal getTotalMoneyReceived(List<AccountMoveDTO> accountMoves);
	
	/**
	 * Gets the total of money amount
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the total amount
	 */
	BigDecimal getTotalMoneyAmount(List<AccountMoveDTO> accountMoves);
	
	/**
	 * Gets the a result report of the account moves
	 * 
	 * @param accountMoves list of account moves
	 * 
	 * @return the result of the report
	 */
	AccountMoveReportDTO getAccountMoveResultReport(List<AccountMoveDTO> accountMoves);
}
