package br.fatec.finance.dto;

import br.fatec.finance.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String description,
        BigDecimal amount,
        TransactionType type,
        LocalDate transactionDate,
        LocalDateTime createdAt,
        UUID userId,
        String userName,
        UUID categoryId,
        String categoryName
) {
}