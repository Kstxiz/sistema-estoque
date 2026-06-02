package br.fatec.finance.controller;

import br.fatec.finance.dto.TransactionResponse;
import br.fatec.finance.entity.Transaction;
import br.fatec.finance.enums.TransactionType;
import br.fatec.finance.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionResponse> findWithFilters(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return transactionService.findWithFilters(
                userId,
                type,
                categoryId,
                startDate,
                endDate
        );
    }

    @PostMapping
    public Transaction create(@RequestBody CreateTransactionRequest request) {
        return transactionService.create(
                request.userId(),
                request.categoryId(),
                request.description(),
                request.amount(),
                request.type(),
                request.transactionDate()
        );
    }

    public record CreateTransactionRequest(
            UUID userId,
            UUID categoryId,
            String description,
            BigDecimal amount,
            TransactionType type,
            LocalDate transactionDate
    ) {
    }
}