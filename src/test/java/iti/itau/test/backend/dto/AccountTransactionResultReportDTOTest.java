package iti.itau.test.backend.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import iti.itau.test.backend.dto.AccountTransactionDTO;
import iti.itau.test.backend.dto.AccountTransactionResultReportDTO;

public class AccountTransactionResultReportDTOTest {
    @Test
    public void of() {
        String mostSpentCategory = "hospedagem"; 
        Month mostSpentMonth = Month.JANUARY; 
        BigDecimal totalReceived = BigDecimal.ZERO; 
        BigDecimal totalSpent = BigDecimal.ZERO; 
        BigDecimal accountBalance = BigDecimal.ZERO;
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();
        
        AccountTransactionResultReportDTO dto = AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        
        assertEquals(mostSpentCategory, dto.getMostSpentCategory());
        assertEquals(mostSpentMonth, dto.getMostSpentMonth());
        assertEquals(totalReceived, dto.getTotalReceived());
        assertEquals(totalSpent, dto.getTotalSpent());
        assertEquals(accountBalance, dto.getAccountBalance());
        assertEquals(accountTransactions, dto.getAccountTransactions());
    }
    
    @Test
    public void testToString() {
        String mostSpentCategory = "hospedagem"; 
        Month mostSpentMonth = Month.JANUARY; 
        BigDecimal totalReceived = BigDecimal.ZERO; 
        BigDecimal totalSpent = BigDecimal.ZERO; 
        BigDecimal accountBalance = BigDecimal.ZERO;
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();
        
        AccountTransactionResultReportDTO dto = AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        
        final String expected = "AccountTransactionResultReportDTO [mostSpentCategory=" + mostSpentCategory + ", mostSpentMonth="
                + mostSpentMonth + ", totalReceived=" + totalReceived + ", totalSpent=" + totalSpent
                + ", accountBalance=" + accountBalance + ", accountTransactions=" + accountTransactions + "]";
                
        assertEquals(expected, dto.toString());
    }
    
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        String mostSpentCategory = "hospedagem"; 
        Month mostSpentMonth = Month.JANUARY; 
        BigDecimal totalReceived = BigDecimal.ZERO; 
        BigDecimal totalSpent = BigDecimal.ZERO; 
        BigDecimal accountBalance = BigDecimal.ZERO;
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();
        
        AccountTransactionResultReportDTO dto = AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        AccountTransactionResultReportDTO dto2 = AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        AccountTransactionResultReportDTO dto3 = AccountTransactionResultReportDTO.of("", mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        
        assertTrue(dto.equals(dto));
        assertTrue(dto.equals(dto2));
        
        assertFalse(dto.equals(null));
        assertFalse(dto.equals(dto3));
        assertFalse(dto.equals(new Exception()));
    }
    
    @Test
    public void testHashCode() {
        String mostSpentCategory = "hospedagem"; 
        Month mostSpentMonth = Month.JANUARY; 
        BigDecimal totalReceived = BigDecimal.ZERO; 
        BigDecimal totalSpent = BigDecimal.ZERO; 
        BigDecimal accountBalance = BigDecimal.ZERO;
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();
        
        AccountTransactionResultReportDTO dto = AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
        
        final int expected = Objects.hash(accountBalance, accountTransactions, mostSpentCategory, mostSpentMonth, totalReceived, totalSpent);
                
        assertEquals(expected, dto.hashCode());
    }
}
