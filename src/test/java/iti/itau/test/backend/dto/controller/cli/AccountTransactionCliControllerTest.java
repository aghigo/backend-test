package iti.itau.test.backend.dto.controller.cli;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import iti.itau.test.backend.dto.controller.cli.AccountTransactionCliController;

public class AccountTransactionCliControllerTest {
    public static String newline = System.getProperty("line.separator", "\n");
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    private AccountTransactionCliController controller;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        this.controller = new AccountTransactionCliController();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    @Test
    public void printAccountTransactionSummaryResults() throws IOException {
        File file = new File("src/test/resources/account-transactions.log");
        
        this.controller.printAccountTransactionSummaryResults(file);
        
        StringBuilder expected = new StringBuilder();
        expected.append("Most spent category: hospedagem").append(newline);
        expected.append("Most spent month: MAY").append(newline);
        expected.append("Total spent: 3318,35").append(newline);
        expected.append("Total received: 528,75").append(newline);
        expected.append("Total balance: -2789,60").append(newline);
        
        assertEquals(expected.toString(), outContent.toString());
    }
}
