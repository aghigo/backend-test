package iti.itau.test.backend.application;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import iti.itau.test.backend.application.Application;

public class ApplicationTest {
    public static String newline = System.getProperty("line.separator", "\n");
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    @Test
    public void main_passingNoParameters() {
        Application.main(new String[] {});
        
        assertEquals("", outContent.toString());
        assertEquals("File path not provided" + newline, errContent.toString());
    }
    
    @Test
    public void main_fileNotFound() {
        Application.main(new String[] { "src/test/resources/non-existing-file.log" });
        
        assertEquals("", outContent.toString());
    }
    
    @Test
    public void main_invalidFile_requiredFieldMissing() {
        Application.main(new String[] { "src/test/resources/invalid-line.log" });
        
        assertEquals("", outContent.toString());
        assertEquals("Line should have at least 3 required fields: (Data, Descricao, Valor)" + newline, errContent.toString());
    }
    
    @Test
    public void main_invalidFile_invalidValueInField() {
        Application.main(new String[] { "src/test/resources/invalid-field.log" });
        
        assertEquals("", outContent.toString());
        assertEquals("Unparseable date: \"22xxxxxx-Mar\"" + newline, errContent.toString());
    }
    
    @Test
    public void main_success() {
        Application.main(new String[] { "src/test/resources/account-transactions.log" });
        
        StringBuilder expected = new StringBuilder();
        expected.append("Most spent category: hospedagem" + newline);
        expected.append("Most spent month: MAY" + newline);
        expected.append("Total spent: 3318,35" + newline);
        expected.append("Total received: 528,75" + newline);
        expected.append("Total balance: -2789,60" + newline);
        
        assertEquals(expected.toString(), outContent.toString());
    }
}
