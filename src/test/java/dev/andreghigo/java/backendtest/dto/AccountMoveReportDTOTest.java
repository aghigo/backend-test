package dev.andreghigo.java.backendtest.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Month;

import org.junit.Test;

public class AccountMoveReportDTOTest {
	@Test
	public void allArgsConstructor() {
		String mostExpensiveCategory = "viagem";
		Month mostExpensiveMonth = Month.JANUARY; 
		BigDecimal totalMoneySpent = BigDecimal.ZERO;
		BigDecimal totalMoneyReceived = BigDecimal.ZERO; 
		BigDecimal totalMoneyAmount = BigDecimal.ZERO;
		
		AccountMoveReportDTO dto = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		
		assertNotNull(dto);
		assertTrue(dto instanceof AccountMoveReportDTO);
	}
	
	@Test
	public void getters() {
		String mostExpensiveCategory = "viagem";
		Month mostExpensiveMonth = Month.JANUARY; 
		BigDecimal totalMoneySpent = BigDecimal.ZERO;
		BigDecimal totalMoneyReceived = BigDecimal.ZERO; 
		BigDecimal totalMoneyAmount = BigDecimal.ZERO;
		
		AccountMoveReportDTO dto = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		
		assertEquals(mostExpensiveCategory, dto.getMostExpensiveCategory());
		assertEquals(mostExpensiveMonth, dto.getMostExpensiveMonth());
		assertEquals(totalMoneySpent, dto.getTotalMoneySpent());
		assertEquals(totalMoneyReceived, dto.getTotalMoneyReceived());
		assertEquals(totalMoneyAmount, dto.getTotalMoneyAmount());
	}
	
	@Test
	public void testToString() {
		String mostExpensiveCategory = "viagem";
		Month mostExpensiveMonth = Month.JANUARY; 
		BigDecimal totalMoneySpent = BigDecimal.ZERO;
		BigDecimal totalMoneyReceived = BigDecimal.ZERO; 
		BigDecimal totalMoneyAmount = BigDecimal.ZERO;
		
		AccountMoveReportDTO dto = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		
		String expected = "AccountMoveReportDTO [mostExpensiveCategory=" + mostExpensiveCategory + ", mostExpensiveMonth="
				+ mostExpensiveMonth + ", totalMoneySpent=" + totalMoneySpent + ", totalMoneyReceived="
				+ totalMoneyReceived + ", totalMoneyAmount=" + totalMoneyAmount + "]";
		
		assertEquals(expected, dto.toString());
	}
	
	@Test
	public void testHashCode() {
		String mostExpensiveCategory = "viagem";
		Month mostExpensiveMonth = Month.JANUARY; 
		BigDecimal totalMoneySpent = BigDecimal.ZERO;
		BigDecimal totalMoneyReceived = BigDecimal.ZERO; 
		BigDecimal totalMoneyAmount = BigDecimal.ZERO;
		
		AccountMoveReportDTO dto = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		
		int expected = 1;
		
		assertEquals(expected, dto.hashCode());
	}
	
	@Test
	@SuppressWarnings("unlikely-arg-type")
	public void testEquals() {
		String mostExpensiveCategory = "viagem";
		Month mostExpensiveMonth = Month.JANUARY; 
		BigDecimal totalMoneySpent = BigDecimal.ZERO;
		BigDecimal totalMoneyReceived = BigDecimal.ZERO; 
		BigDecimal totalMoneyAmount = BigDecimal.ZERO;
		
		AccountMoveReportDTO dto = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		AccountMoveReportDTO dto1 = new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		AccountMoveReportDTO dto2 = new AccountMoveReportDTO("test", mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
		
		assertTrue(dto.equals(dto));
		assertTrue(dto.equals(dto1));
		
		assertFalse(dto.equals(null));
		assertFalse(dto.equals(dto2));
		assertFalse(dto.equals(new Exception()));
	}
}
