package iti.itau.test.backend.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Month;
import java.time.MonthDay;

import org.junit.Test;

import iti.itau.test.backend.dto.AccountTransactionDTO;

public class AccountTransactionDTOTest {
    @Test
    public void of() {
        MonthDay monthDay = MonthDay.of(Month.JANUARY, 1);
        String description = "Uber Do Brasil Tecnolog"; 
        BigDecimal value = BigDecimal.valueOf(99.99);
        String category = "transporte";
        AccountTransactionDTO dto = AccountTransactionDTO.of(monthDay, description, value, category);
        
        assertEquals(monthDay, dto.getMonthDay());
        assertEquals(description, dto.getDescription());
        assertEquals(value, dto.getValue());
        assertEquals(category, dto.getCategory());
    }
    
    @Test
    public void testToString() {
        MonthDay monthDay = MonthDay.of(Month.JANUARY, 1);
        String description = "Uber Do Brasil Tecnolog"; 
        BigDecimal value = BigDecimal.valueOf(99.99);
        String category = "transporte";
        AccountTransactionDTO dto = AccountTransactionDTO.of(monthDay, description, value, category);
        
        final String expected = "AccountTransactionDTO [monthDay=" + monthDay + ", description=" + description + ", value=" + value + ", category=" + category + "]";
        
        assertEquals(expected, dto.toString());
    }
    
    @Test
    public void testHashCode() {
        MonthDay monthDay = MonthDay.of(Month.JANUARY, 1);
        String description = "Uber Do Brasil Tecnolog"; 
        BigDecimal value = BigDecimal.valueOf(99.99);
        String category = "transporte";
        AccountTransactionDTO dto = AccountTransactionDTO.of(monthDay, description, value, category);
        
        final int expected = -823253456;
        
        assertEquals(expected, dto.hashCode());
    }
    
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals() {
        MonthDay monthDay = MonthDay.of(Month.JANUARY, 1);
        String description = "Uber Do Brasil Tecnolog"; 
        BigDecimal value = BigDecimal.valueOf(99.99);
        String category = "transporte";
        AccountTransactionDTO dto = AccountTransactionDTO.of(monthDay, description, value, category);
        AccountTransactionDTO dto2 = AccountTransactionDTO.of(monthDay, description, value, category);
        AccountTransactionDTO dto3 = AccountTransactionDTO.of(monthDay, description, value, "hospedagem");
        
        
        assertTrue(dto.equals(dto));
        assertTrue(dto.equals(dto2));
        
        assertFalse(dto.equals(null));
        assertFalse(dto.equals(dto3));
        assertFalse(dto.equals(new Exception()));
    }
    
    
}
