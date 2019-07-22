package dev.andreghigo.java.backendtest.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class AccountMoveLogDTOTest {
	@Test
	public void allArgsConstructor() {
		Date date = new Date();
		String description = "TAM SITE";
		BigDecimal value = BigDecimal.valueOf(-430.49);
		String category = "viagem";
		AccountMoveDTO dto = new AccountMoveDTO(date, description, value, category);

		assertNotNull(dto);
		assertTrue(dto instanceof AccountMoveDTO);
	}

	@Test
	public void getters() {
		Date date = new Date();
		String description = "TAM SITE";
		BigDecimal value = BigDecimal.valueOf(-430.49);
		String category = "viagem";
		AccountMoveDTO dto = new AccountMoveDTO(date, description, value, category);

		assertEquals(date, dto.getDate());
		assertEquals(description, dto.getDescription());
		assertEquals(value, dto.getValue());
		assertEquals(category, dto.getCategory());
	}

	@Test
	public void testToString() {
		Date date = new Date();
		String description = "TAM SITE";
		BigDecimal value = BigDecimal.valueOf(-430.49);
		String category = "viagem";
		AccountMoveDTO dto = new AccountMoveDTO(date, description, value, category);

		String expected = "AccountMoveLogDTO [date=" + date + ", description=" + description + ", value=" + value + ", category=" + category + "]";
		
		assertEquals(expected, dto.toString());
	}

	@Test
	public void testHashCode() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dateFormat.parse("21-07-2019");
		String description = "TAM SITE";
		BigDecimal value = BigDecimal.valueOf(-430.49);
		String category = "viagem";
		AccountMoveDTO dto = new AccountMoveDTO(date, description, value, category);

		int expected = 908344144;
		
		assertEquals(expected, dto.hashCode());
	}

	@Test
	@SuppressWarnings("unlikely-arg-type")
	public void testEquals() {
		Date date = new Date();
		String description = "TAM SITE";
		BigDecimal value = BigDecimal.valueOf(-430.49);
		String category = "viagem";
		AccountMoveDTO dto = new AccountMoveDTO(date, description, value, category);
		AccountMoveDTO dto2 = new AccountMoveDTO(date, description, value, category);
		AccountMoveDTO dto3 = new AccountMoveDTO(date, description, value, "test");
		
		assertTrue(dto.equals(dto));
		assertTrue(dto.equals(dto2));
		
		assertFalse(dto.equals(null));
		assertFalse(dto.equals(dto3));
		assertFalse(dto.equals(new Exception()));		
	}
}
