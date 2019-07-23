package iti.itau.test.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.text.ParseException;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import iti.itau.test.backend.dto.AccountTransactionDTO;
import iti.itau.test.backend.dto.AccountTransactionResultReportDTO;

public class AccountTransactionServiceImplTest {
    private AccountTransactionService accountTransactionService;

    @Before
    public void setUp() {
        this.accountTransactionService = new AccountTransactionServiceImpl();
    }
    
    @Test
    public void parseLogFile_emptyFile() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/empty-file.log");

        // WHEN
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);
        
        assertTrue(accountTransactions.isEmpty());
    }
    
    @Test
    public void parseLogFile_fileNotFound() {
        // GIVEN
        File file = new File("src/test/resources/non-existing-file.log");

        // WHEN
        try {
            accountTransactionService.parseLogFile(file);
            fail("Should throw IOException");
        } catch (IOException e) {
            // THEN
            assertEquals(NoSuchFileException.class, e.getCause().getClass());
        }
    }

    @Test
    public void parseLogFile_invalidFileWithMissingFields() {
        // GIVEN
        File file = new File("src/test/resources/invalid-line.log");

        // WHEN
        try {
            accountTransactionService.parseLogFile(file);
            fail("Should throw IOException");
        } catch (IOException e) {
            // THEN
            assertEquals("Line should have at least 3 required fields: (Data, Descricao, Valor)", e.getMessage());
        }
    }

    @Test
    public void parseLogFile_invalidFileWithInvalidFieldValue() {
        // GIVEN
        File file = new File("src/test/resources/invalid-field.log");

        // WHEN
        try {
            accountTransactionService.parseLogFile(file);
            fail("Should throw IOException");
        } catch (IOException e) {
            // THEN
            assertEquals("Unparseable date: \"22xxxxxx-Mar\"", e.getMessage());
            assertEquals(ParseException.class, e.getCause().getClass());
        }
    }

    @Test
    public void parseLogFile() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");

        // WHEN
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // THEN
        assertEquals(30, accountTransactions.size());

        AccountTransactionDTO transaction = accountTransactions.get(0);
        assertEquals(MonthDay.of(Month.FEBRUARY, 17), transaction.getMonthDay());
        assertEquals("TAM SITE", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-430.49), transaction.getValue());
        assertEquals("viagem", transaction.getCategory());

        transaction = accountTransactions.get(1);
        assertEquals(MonthDay.of(Month.MARCH, 21), transaction.getMonthDay());
        assertEquals("INGRESSO.COM", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-159.53), transaction.getValue());
        assertEquals("diversao", transaction.getCategory());

        transaction = accountTransactions.get(2);
        assertEquals(MonthDay.of(Month.MARCH, 21), transaction.getMonthDay());
        assertEquals("NESPRESSO", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-55.9), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(3);
        assertEquals(MonthDay.of(Month.MARCH, 22), transaction.getMonthDay());
        assertEquals("IBIS PARQUE OLIMPICO", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-143.15), transaction.getValue());
        assertEquals("hospedagem", transaction.getCategory());

        transaction = accountTransactions.get(4);
        assertEquals(MonthDay.of(Month.MARCH, 27), transaction.getMonthDay());
        assertEquals("PONTO FRIOCOM", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-213.26), transaction.getValue());
        assertEquals("", transaction.getCategory());

        transaction = accountTransactions.get(5);
        assertEquals(MonthDay.of(Month.APRIL, 26), transaction.getMonthDay());
        assertEquals("LOJAS RENNER FL", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-59.95), transaction.getValue());
        assertEquals("Vestuario", transaction.getCategory());

        transaction = accountTransactions.get(6);
        assertEquals(MonthDay.of(Month.MAY, 21), transaction.getMonthDay());
        assertEquals("Antonio Coutinho", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(120.00), transaction.getValue());
        assertEquals("", transaction.getCategory());

        transaction = accountTransactions.get(7);
        assertEquals(MonthDay.of(Month.MAY, 21), transaction.getMonthDay());
        assertEquals("Camila Souza", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(35.00), transaction.getValue());
        assertEquals("", transaction.getCategory());

        transaction = accountTransactions.get(8);
        assertEquals(MonthDay.of(Month.MAY, 24), transaction.getMonthDay());
        assertEquals("Evino", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-62.43), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(9);
        assertEquals(MonthDay.of(Month.MAY, 24), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-6.66), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(10);
        assertEquals(MonthDay.of(Month.MAY, 25), transaction.getMonthDay());
        assertEquals("UATT", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-79.9), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(11);
        assertEquals(MonthDay.of(Month.MAY, 25), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-8.65), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(12);
        assertEquals(MonthDay.of(Month.MAY, 25), transaction.getMonthDay());
        assertEquals("COMETA TIETE", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-28.28), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(13);
        assertEquals(MonthDay.of(Month.MAY, 25), transaction.getMonthDay());
        assertEquals("Droga Raia", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-14.09), transaction.getValue());
        assertEquals("higiene", transaction.getCategory());

        transaction = accountTransactions.get(14);
        assertEquals(MonthDay.of(Month.MAY, 26), transaction.getMonthDay());
        assertEquals("SONDA SUPERMERCADO", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-41.89), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(15);
        assertEquals(MonthDay.of(Month.MAY, 26), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-9.39), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(16);
        assertEquals(MonthDay.of(Month.MAY, 27), transaction.getMonthDay());
        assertEquals("EBANX AIRBNB", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-1430.44), transaction.getValue());
        assertEquals("hospedagem", transaction.getCategory());

        transaction = accountTransactions.get(17);
        assertEquals(MonthDay.of(Month.MAY, 27), transaction.getMonthDay());
        assertEquals("Droga Raia", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-13.98), transaction.getValue());
        assertEquals("higiene", transaction.getCategory());

        transaction = accountTransactions.get(18);
        assertEquals(MonthDay.of(Month.MAY, 27), transaction.getMonthDay());
        assertEquals("ITUNES.COM/BILL", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-74.9), transaction.getValue());
        assertEquals("diversao", transaction.getCategory());

        transaction = accountTransactions.get(19);
        assertEquals(MonthDay.of(Month.MAY, 29), transaction.getMonthDay());
        assertEquals("Hirota", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-13.0), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(20);
        assertEquals(MonthDay.of(Month.MAY, 30), transaction.getMonthDay());
        assertEquals("HARU LANCHONETE", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-75.9), transaction.getValue());
        assertEquals("alimentacao", transaction.getCategory());

        transaction = accountTransactions.get(21);
        assertEquals(MonthDay.of(Month.JUNE, 1), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-7.04), transaction.getValue());
        assertEquals("TRANSPORTE", transaction.getCategory());

        transaction = accountTransactions.get(22);
        assertEquals(MonthDay.of(Month.JUNE, 2), transaction.getMonthDay());
        assertEquals("Jose Mota", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(35.0), transaction.getValue());
        assertEquals("", transaction.getCategory());

        transaction = accountTransactions.get(23);
        assertEquals(MonthDay.of(Month.JUNE, 2), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-6.09), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(24);
        assertEquals(MonthDay.of(Month.JUNE, 2), transaction.getMonthDay());
        assertEquals("RECARGAPAY BILH UNICO", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-10.0), transaction.getValue());
        assertEquals("transporte", transaction.getCategory());

        transaction = accountTransactions.get(25);
        assertEquals(MonthDay.of(Month.JUNE, 3), transaction.getMonthDay());
        assertEquals("ITUNES.COM/BILL", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-16.9), transaction.getValue());
        assertEquals("diversao", transaction.getCategory());

        transaction = accountTransactions.get(26);
        assertEquals(MonthDay.of(Month.JUNE, 5), transaction.getMonthDay());
        assertEquals("Uber Do Brasil Tecnolog", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-7.03), transaction.getValue());
        assertEquals("TRANSPORTE", transaction.getCategory());

        transaction = accountTransactions.get(27);
        assertEquals(MonthDay.of(Month.JUNE, 12), transaction.getMonthDay());
        assertEquals("ITUNES.COM/BILL", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-10.90), transaction.getValue());
        assertEquals("diversao", transaction.getCategory());

        transaction = accountTransactions.get(28);
        assertEquals(MonthDay.of(Month.JUNE, 20), transaction.getMonthDay());
        assertEquals("EBANX AIRBNB", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(-338.6), transaction.getValue());
        assertEquals("hospedagem", transaction.getCategory());

        transaction = accountTransactions.get(29);
        assertEquals(MonthDay.of(Month.JUNE, 20), transaction.getMonthDay());
        assertEquals("EBANX AIRBNB", transaction.getDescription());
        assertEquals(BigDecimal.valueOf(338.75), transaction.getValue());
        assertEquals("hospedagem", transaction.getCategory());
    }

    @Test
    public void getMostSpentCategory_withNoTransactions() throws IOException {
        // GIVEN
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();

        // WHEN
        Optional<String> mostSpentCategory = this.accountTransactionService.getMostSpentCategory(accountTransactions);

        // THEN
        assertFalse(mostSpentCategory.isPresent());
    }

    @Test
    public void getMostSpentCategory() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        Optional<String> mostSpentCategory = this.accountTransactionService.getMostSpentCategory(accountTransactions);

        // THEN
        assertTrue(mostSpentCategory.isPresent());
        assertEquals("hospedagem", mostSpentCategory.get().toLowerCase());
    }

    @Test
    public void getMostSpentMonth_withNoTransactions() throws IOException {
        // GIVEN
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();

        // WHEN
        Optional<Month> mostSpentMonth = this.accountTransactionService.getMostSpentMonth(accountTransactions);

        // THEN
        assertFalse(mostSpentMonth.isPresent());
    }

    @Test
    public void getMostSpentMonth() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        Optional<Month> mostSpentMonth = this.accountTransactionService.getMostSpentMonth(accountTransactions);

        // THEN
        assertTrue(mostSpentMonth.isPresent());
        assertEquals(Month.MAY, mostSpentMonth.get());
    }

    @Test
    public void getTotalSpent() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        BigDecimal totalSpent = this.accountTransactionService.getTotalSpent(accountTransactions);

        // THEN
        assertEquals(BigDecimal.valueOf(3318.35), totalSpent);
    }

    @Test
    public void getTotalReceived() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        BigDecimal totalReceived = this.accountTransactionService.getTotalReceived(accountTransactions);

        // THEN
        assertEquals(BigDecimal.valueOf(528.75), totalReceived);
    }

    @Test
    public void getAccountBalance() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        BigDecimal accountBalance = this.accountTransactionService.getAccountBalance(accountTransactions);

        // THEN
        assertEquals(BigDecimal.valueOf(-2789.60), accountBalance);
    }

    @Test
    public void getResultReport() throws IOException {
        // GIVEN
        File file = new File("src/test/resources/account-transactions.log");
        List<AccountTransactionDTO> accountTransactions = accountTransactionService.parseLogFile(file);

        // WHEN
        AccountTransactionResultReportDTO resultReport = this.accountTransactionService
                .getResultReport(accountTransactions);

        // THEN
        assertEquals(BigDecimal.valueOf(-2789.60), resultReport.getAccountBalance());
        assertEquals(accountTransactions, resultReport.getAccountTransactions());
        assertEquals("hospedagem", resultReport.getMostSpentCategory());
        assertEquals(Month.MAY, resultReport.getMostSpentMonth());
        assertEquals(BigDecimal.valueOf(528.75), resultReport.getTotalReceived());
        assertEquals(BigDecimal.valueOf(3318.35), resultReport.getTotalSpent());
    }
}
