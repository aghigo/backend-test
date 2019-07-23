package iti.itau.test.backend.dto;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable DTO which holds the data about the result report summary of account transactions
 * 
 * @author andre.ghigo
 */
public final class AccountTransactionResultReportDTO {
    private final String mostSpentCategory;
    private final Month mostSpentMonth;
    private final BigDecimal totalReceived;
    private final BigDecimal totalSpent;
    private final BigDecimal accountBalance;
    private final List<AccountTransactionDTO> accountTransactions;
    
    public static AccountTransactionResultReportDTO of(String mostSpentCategory, Month mostSpentMonth, BigDecimal totalReceived, BigDecimal totalSpent, BigDecimal accountBalance, List<AccountTransactionDTO> accountTransactions) {
        return new AccountTransactionResultReportDTO(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
    }
    
    private AccountTransactionResultReportDTO(String mostSpentCategory, Month mostSpentMonth, BigDecimal totalReceived, BigDecimal totalSpent, BigDecimal accountBalance, List<AccountTransactionDTO> accountTransactions) {
        super();
        this.mostSpentCategory = mostSpentCategory;
        this.mostSpentMonth = mostSpentMonth;
        this.totalReceived = totalReceived;
        this.totalSpent = totalSpent;
        this.accountBalance = accountBalance;
        this.accountTransactions = Collections.unmodifiableList(new ArrayList<>(accountTransactions));
    }
    
    public String getMostSpentCategory() {
        return mostSpentCategory;
    }
    
    public Month getMostSpentMonth() {
        return mostSpentMonth;
    }
    
    public BigDecimal getTotalReceived() {
        return totalReceived;
    }
    
    public BigDecimal getTotalSpent() {
        return totalSpent;
    }
    
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
    
    public List<AccountTransactionDTO> getAccountTransactions() {
        return accountTransactions;
    }
    
    @Override
    public String toString() {
        return "AccountTransactionResultReportDTO [mostSpentCategory=" + mostSpentCategory + ", mostSpentMonth="
                + mostSpentMonth + ", totalReceived=" + totalReceived + ", totalSpent=" + totalSpent
                + ", accountBalance=" + accountBalance + ", accountTransactions=" + accountTransactions + "]";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountBalance, accountTransactions, mostSpentCategory, mostSpentMonth, totalReceived, totalSpent);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AccountTransactionResultReportDTO)) {
            return false;
        }
        AccountTransactionResultReportDTO other = (AccountTransactionResultReportDTO) obj;
        return Objects.equals(accountBalance, other.accountBalance)
                && Objects.equals(accountTransactions, other.accountTransactions)
                && Objects.equals(mostSpentCategory, other.mostSpentCategory)
                && Objects.equals(mostSpentMonth, other.mostSpentMonth)
                && Objects.equals(totalReceived, other.totalReceived) && Objects.equals(totalSpent, other.totalSpent);
    }
}
