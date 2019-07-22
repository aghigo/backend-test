package dev.andreghigo.java.backendtest.dto;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Objects;

public final class AccountMoveReportDTO {
	private final String mostExpensiveCategory;
	private final Month mostExpensiveMonth;
	private final BigDecimal totalMoneySpent;
	private final BigDecimal totalMoneyReceived;
	private final BigDecimal totalMoneyAmount;
	
	public AccountMoveReportDTO(String mostExpensiveCategory, Month mostExpensiveMonth, BigDecimal totalMoneySpent, BigDecimal totalMoneyReceived, BigDecimal totalMoneyAmount) {
		super();
		this.mostExpensiveCategory = mostExpensiveCategory;
		this.mostExpensiveMonth = mostExpensiveMonth;
		this.totalMoneySpent = totalMoneySpent;
		this.totalMoneyReceived = totalMoneyReceived;
		this.totalMoneyAmount = totalMoneyAmount;
	}

	public String getMostExpensiveCategory() {
		return mostExpensiveCategory;
	}

	public Month getMostExpensiveMonth() {
		return mostExpensiveMonth;
	}

	public BigDecimal getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public BigDecimal getTotalMoneyReceived() {
		return totalMoneyReceived;
	}

	public BigDecimal getTotalMoneyAmount() {
		return totalMoneyAmount;
	}

	@Override
	public String toString() {
		return "AccountMoveReportDTO [mostExpensiveCategory=" + mostExpensiveCategory + ", mostExpensiveMonth=" + mostExpensiveMonth + ", totalMoneySpent=" + totalMoneySpent + ", totalMoneyReceived=" + totalMoneyReceived + ", totalMoneyAmount=" + totalMoneyAmount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mostExpensiveCategory, mostExpensiveMonth, totalMoneyAmount, totalMoneyReceived, totalMoneySpent);
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
		AccountMoveReportDTO other = (AccountMoveReportDTO) obj;
		return Objects.equals(mostExpensiveCategory, other.mostExpensiveCategory)
				&& Objects.equals(mostExpensiveMonth, other.mostExpensiveMonth)
				&& Objects.equals(totalMoneyAmount, other.totalMoneyAmount)
				&& Objects.equals(totalMoneyReceived, other.totalMoneyReceived)
				&& Objects.equals(totalMoneySpent, other.totalMoneySpent);
	}
}
