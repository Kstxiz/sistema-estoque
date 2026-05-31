package br.fatec.finance.repository;

import br.fatec.finance.entity.Transaction;
import br.fatec.finance.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByUserId(UUID userId);

    List<Transaction> findByUserIdAndType(UUID userId, TransactionType type);

    List<Transaction> findByUserIdAndTransactionDateBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Transaction> findByUserIdAndCategoryId(UUID userId, UUID categoryId);
}