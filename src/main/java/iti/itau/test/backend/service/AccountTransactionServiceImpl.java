package iti.itau.test.backend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import iti.itau.test.backend.dto.AccountTransactionDTO;
import iti.itau.test.backend.dto.AccountTransactionResultReportDTO;

public class AccountTransactionServiceImpl implements AccountTransactionService {
    @Override
    public List<AccountTransactionDTO> parseLogFile(File file) throws IOException {
        List<AccountTransactionDTO> accountTransactions = new ArrayList<>();
        
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM", Locale.ENGLISH);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            final int totalRequiredFields = 3;

            List<String> lines = bufferedReader.lines().skip(1).collect(Collectors.toList());
            for(String line : lines) {
                String[] fields = line.split("\\s{2,}");
                if(fields.length < totalRequiredFields) {
                    throw new IOException("Line should have at least " + totalRequiredFields + " required fields: (Data, Descricao, Valor)");
                }
                
                Date date = dateFormat.parse(fields[0].trim());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                Month month = Month.of(calendar.get(Calendar.MONTH) + 1);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                MonthDay monthDay = MonthDay.of(month, dayOfMonth);
                
                String description = fields[1].trim();
                
                BigDecimal value = BigDecimal.valueOf(decimalFormat.parse(fields[2].trim()).doubleValue());
                
                String category = (fields.length < 4) ? "" : fields[3].trim();
                
                AccountTransactionDTO accountTransaction = AccountTransactionDTO.of(monthDay, description, value, category);
                
                accountTransactions.add(accountTransaction);
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
        
        return accountTransactions;
    }

    @Override
    public Optional<String> getMostSpentCategory(List<AccountTransactionDTO> accountTransactions) {
        Map<String, BigDecimal> totalPerMonth = new HashMap<>();
        
        accountTransactions.stream().map(AccountTransactionDTO::getCategory).distinct().forEach(category -> {
            BigDecimal totalSpentOnMonth = BigDecimal.valueOf(accountTransactions.stream().filter(t -> t.getValue().doubleValue() < 0).filter(t -> t.getCategory().equalsIgnoreCase(category)).mapToDouble(t -> t.getValue().doubleValue() * -1).sum());
            totalPerMonth.put(category, totalSpentOnMonth);
        });
        
        Optional<Entry<String, BigDecimal>> max = totalPerMonth.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        if(max.isPresent()) {
            return Optional.ofNullable(max.get().getKey());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Month> getMostSpentMonth(List<AccountTransactionDTO> accountTransactions) {
        EnumMap<Month, BigDecimal> totalPerMonth = new EnumMap<>(Month.class);
        
        accountTransactions.stream().map(t -> t.getMonthDay().getMonth()).distinct().forEach(month -> {
            BigDecimal totalSpentOnMonth = BigDecimal.valueOf(accountTransactions.stream().filter(t -> t.getValue().doubleValue() < 0).filter(t -> t.getMonthDay().getMonth().equals(month)).mapToDouble(t -> t.getValue().doubleValue() * -1).sum());
            totalPerMonth.put(month, totalSpentOnMonth);
        });
        
        Optional<Entry<Month, BigDecimal>> max = totalPerMonth.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        if(max.isPresent()) {
            return Optional.ofNullable(max.get().getKey());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public BigDecimal getTotalSpent(List<AccountTransactionDTO> accountTransactions) {
        return BigDecimal.valueOf(accountTransactions.stream().filter(a -> a.getValue().doubleValue() < 0).mapToDouble(t -> t.getValue().doubleValue() * -1).sum());
    }

    @Override
    public BigDecimal getTotalReceived(List<AccountTransactionDTO> accountTransactions) {
        return BigDecimal.valueOf(accountTransactions.stream().filter(a -> a.getValue().doubleValue() > 0).mapToDouble(t -> t.getValue().doubleValue()).sum());
    }

    @Override
    public BigDecimal getAccountBalance(List<AccountTransactionDTO> accountTransactions) {
        return BigDecimal.valueOf(accountTransactions.stream().mapToDouble(t -> t.getValue().doubleValue()).sum());
    }

    @Override
    public AccountTransactionResultReportDTO getResultReport(List<AccountTransactionDTO> accountTransactions) {
        String mostSpentCategory = this.getMostSpentCategory(accountTransactions).orElse(null);
        Month mostSpentMonth = this.getMostSpentMonth(accountTransactions).orElse(null);
        BigDecimal totalReceived = this.getTotalReceived(accountTransactions);
        BigDecimal totalSpent = this.getTotalSpent(accountTransactions);
        BigDecimal accountBalance = this.getAccountBalance(accountTransactions);
        
        return AccountTransactionResultReportDTO.of(mostSpentCategory, mostSpentMonth, totalReceived, totalSpent, accountBalance, accountTransactions);
    }
}
