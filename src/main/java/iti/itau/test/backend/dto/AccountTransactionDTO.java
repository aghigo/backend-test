package iti.itau.test.backend.dto;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.Objects;

/**
 * Immutable DTO which holds the data about an account transaction
 * 
 * @author andre.ghigo
 */
public final class AccountTransactionDTO {
    private final MonthDay monthDay;
    private final String description;
    private final BigDecimal value;
    private final String category;
    
    public static AccountTransactionDTO of(MonthDay monthDay, String description, BigDecimal value, String category) {
        return new AccountTransactionDTO(monthDay, description, value, category);
    }
    
    private AccountTransactionDTO(MonthDay monthDay, String description, BigDecimal value, String category) {
        super();
        this.monthDay = monthDay;
        this.description = description;
        this.value = value;
        this.category = category;
    }
    
    public MonthDay getMonthDay() {
        return monthDay;
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
        return "AccountTransactionDTO [monthDay=" + monthDay + ", description=" + description + ", value=" + value + ", category=" + category + "]";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(category, description, value, monthDay);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AccountTransactionDTO)) {
            return false;
        }
        AccountTransactionDTO other = (AccountTransactionDTO) obj;
        return Objects.equals(category, other.category) && Objects.equals(description, other.description)
                && Objects.equals(value, other.value) && Objects.equals(monthDay, other.monthDay);
    }
}
