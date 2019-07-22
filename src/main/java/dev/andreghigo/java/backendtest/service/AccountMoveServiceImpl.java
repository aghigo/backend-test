package dev.andreghigo.java.backendtest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.andreghigo.java.backendtest.dto.AccountMoveDTO;
import dev.andreghigo.java.backendtest.dto.AccountMoveReportDTO;

@Service
public class AccountMoveServiceImpl implements AccountMoveService {
	@Override
	public List<AccountMoveDTO> parseAccountMovesLogFile(File file) throws IOException, ParseException {
		try(InputStream inputStream = new FileInputStream(file)) {
			List<AccountMoveDTO> accountMoves = new ArrayList<>();
			
			// TODO Parse the file

			return Collections.unmodifiableList(accountMoves);
		}
	}

	@Override
	public Optional<String> getMostExpensiveCategory(List<AccountMoveDTO> accountMoves) {
		if(accountMoves.isEmpty()) {
			return Optional.empty();
		}
		
		Map<String, Double> totalOfEachCategory = new HashMap<>();
		accountMoves.stream().filter(t -> t.getValue().doubleValue() < 0).map(AccountMoveDTO::getCategory).distinct().forEach(category -> {
			totalOfEachCategory.put(category, accountMoves.stream().filter(x -> x.getCategory().equals(category)).mapToDouble(z -> z.getValue().doubleValue()).sum());
		});
		
		return Optional.ofNullable(totalOfEachCategory.entrySet().stream().filter(t -> t.getValue() < 0).max((c1, c2) -> c1.getValue().compareTo(c2.getValue())).get().getKey());
	}

	@Override
	public Optional<Month> getMostExpensiveMonth(List<AccountMoveDTO> accountMoves) {
		if(accountMoves.isEmpty()) {
			return Optional.empty();
		}
		
		// TODO replace the deprecated way of getting the month
		Map<Month, Double> totalOfEachMonth = new EnumMap<>();
		accountMoves.stream().filter(t -> t.getValue().doubleValue() < 0).map(a -> a.getDate().getMonth()).distinct().forEach(m -> {
			totalOfEachMonth.put(Month.of(m), accountMoves.stream().filter(x -> x.getDate().getMonth() == m).mapToDouble(z -> z.getValue().doubleValue()).sum());
		});
		
		return Optional.ofNullable(totalOfEachMonth.entrySet().stream().max((c1, c2) -> c1.getValue().compareTo(c2.getValue())).get().getKey());
	}

	@Override
	public BigDecimal getTotalMoneySpent(List<AccountMoveDTO> accountMoves) {
		return BigDecimal.valueOf(accountMoves.stream().filter(a -> a.getValue().doubleValue() < 0).mapToDouble(a -> a.getValue().doubleValue()).sum());
	}

	@Override
	public BigDecimal getTotalMoneyReceived(List<AccountMoveDTO> accountMoves) {
		return BigDecimal.valueOf(accountMoves.stream().filter(a -> a.getValue().doubleValue() > 0).mapToDouble(a -> a.getValue().doubleValue()).sum());
	}

	@Override
	public BigDecimal getTotalMoneyAmount(List<AccountMoveDTO> accountMoves) {
		return BigDecimal.valueOf(accountMoves.stream().mapToDouble(a -> a.getValue().doubleValue()).sum());
	}

	@Override
	public AccountMoveReportDTO getAccountMoveResultReport(List<AccountMoveDTO> accountMoves) {
		final String mostExpensiveCategory = this.getMostExpensiveCategory(accountMoves).orElse("");
		final Month mostExpensiveMonth = this.getMostExpensiveMonth(accountMoves).get();
		final BigDecimal totalMoneySpent  = this.getTotalMoneySpent(accountMoves);
		final BigDecimal totalMoneyReceived = this.getTotalMoneyReceived(accountMoves);
		final BigDecimal totalMoneyAmount = this.getTotalMoneyAmount(accountMoves);
		
		return new AccountMoveReportDTO(mostExpensiveCategory, mostExpensiveMonth, totalMoneySpent, totalMoneyReceived, totalMoneyAmount);
	}
}
