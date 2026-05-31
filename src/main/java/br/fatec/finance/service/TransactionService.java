package br.fatec.finance.service;

import br.fatec.finance.entity.Category;
import br.fatec.finance.entity.Transaction;
import br.fatec.finance.entity.User;
import br.fatec.finance.enums.TransactionType;
import br.fatec.finance.repository.CategoryRepository;
import br.fatec.finance.repository.TransactionRepository;
import br.fatec.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction create(
            UUID userId,
            UUID categoryId,
            String description,
            BigDecimal amount,
            TransactionType type,
            LocalDate transactionDate
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!category.getUser().getId().equals(userId)) {
            throw new RuntimeException("Categoria não pertence ao usuário informado");
        }

        if (!category.getType().name().equals(type.name())) {
            throw new RuntimeException("Tipo da categoria não corresponde ao tipo da transação");
        }

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .user(user)
                .category(category)
                .description(description)
                .amount(amount)
                .type(type)
                .transactionDate(transactionDate)
                .createdAt(LocalDateTime.now())
                .build();

        return transactionRepository.save(transaction);
    }
}