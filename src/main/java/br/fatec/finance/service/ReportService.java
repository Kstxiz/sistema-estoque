package br.fatec.finance.service;

import br.fatec.finance.dto.MonthlySummaryResponse;
import br.fatec.finance.entity.Transaction;
import br.fatec.finance.enums.TransactionType;
import br.fatec.finance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TransactionRepository transactionRepository;

    public MonthlySummaryResponse getMonthlySummary(UUID userId) {

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new MonthlySummaryResponse(
                totalIncome,
                totalExpense,
                balance
        );
    }
}