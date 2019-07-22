package dev.andreghigo.java.backendtest.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import dev.andreghigo.java.backendtest.dto.AccountMoveDTO;
import dev.andreghigo.java.backendtest.dto.AccountMoveReportDTO;

public class AccountMoveServiceImplTest {
	private AccountMoveService service;
	
	@Before
	public void setUp() {
		this.service = new AccountMoveServiceImpl();
	}
	
	@Test
	public void parseAccountMovesLogFile() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		
		// WHEN
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// THEN
		assertEquals(30, accountMoves.size());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM", new Locale("en"));
		
		assertEquals("17-Feb", dateFormat.format(accountMoves.get(0).getDate()));
		assertEquals("TAM SITE", accountMoves.get(0).getDescription());
		assertEquals(BigDecimal.valueOf(-430.49), accountMoves.get(0).getValue());
		assertEquals("viagem", accountMoves.get(0).getCategory());
	}
	
	@Test
	public void getMostExpensiveCategory() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		String category = this.service.getMostExpensiveCategory(accountMoves).get();
		
		// THEN
		assertEquals("hospedagem", category);
	}
	
	@Test
	public void getMostExpensiveMonth() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		Month month = this.service.getMostExpensiveMonth(accountMoves).get();
		
		// THEN
		assertEquals(Month.MAY, month);
	}
	
	@Test
	public void getTotalMoneySpent() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		BigDecimal totalMoneySpent = this.service.getTotalMoneySpent(accountMoves);
		
		// THEN
		assertEquals(BigDecimal.valueOf(-3318.35), totalMoneySpent);
	}
	
	@Test
	public void getTotalMoneyReceived() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		BigDecimal totalMoneySpent = this.service.getTotalMoneySpent(accountMoves);
		
		// THEN
		assertEquals(BigDecimal.valueOf(528.75), totalMoneySpent);		
	}
	
	@Test
	public void getTotalMoneyAmount() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		BigDecimal totalMoneyAmount = this.service.getTotalMoneyAmount(accountMoves);
		
		// THEN
		assertEquals(BigDecimal.valueOf(-2789.60), totalMoneyAmount);		
	}
	
	@Test
	public void getAccountMoveResultReport() throws IOException, ParseException {
		// GIVEN
		File file = new File("src/test/resources/account-moves.log");
		List<AccountMoveDTO> accountMoves = this.service.parseAccountMovesLogFile(file);
		
		// WHEN
		AccountMoveReportDTO report = this.service.getAccountMoveResultReport(accountMoves);
		
		// THEN
		assertEquals("hospedagem", report.getMostExpensiveCategory());
		assertEquals(Month.MAY, report.getMostExpensiveMonth());
		assertEquals(-2789.60, report.getTotalMoneyAmount());
		assertEquals(528.75, report.getTotalMoneyReceived());
		assertEquals(-2789.60, report.getTotalMoneySpent());
	}
}


