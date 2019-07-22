package dev.andreghigo.java.backendtest.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public final class AccountMoveDTO {
	private final Date date;
	private final String description;
	private final BigDecimal value;
	private final String category;
	
	public AccountMoveDTO(Date date, String description, BigDecimal value, String category) {
		super();
		this.date = date;
		this.description = description;
		this.value = value;
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "AccountMoveLogDTO [date=" + date + ", description=" + description + ", value=" + value + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, date, description, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccountMoveDTO other = (AccountMoveDTO) obj;
		return Objects.equals(category, other.category) && Objects.equals(date, other.date)&& Objects.equals(description, other.description) && Objects.equals(value, other.value);
	}
}
