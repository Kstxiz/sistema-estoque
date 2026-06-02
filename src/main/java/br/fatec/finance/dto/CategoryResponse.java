package br.fatec.finance.dto;

import br.fatec.finance.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        CategoryType type,
        LocalDateTime createdAt,
        UUID userId,
        String userName
) {
}