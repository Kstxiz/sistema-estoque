package br.fatec.finance.service;

import br.fatec.finance.dto.CategoryResponse;
import br.fatec.finance.entity.Category;
import br.fatec.finance.entity.User;
import br.fatec.finance.enums.CategoryType;
import br.fatec.finance.repository.CategoryRepository;
import br.fatec.finance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryResponse create(UUID userId, String name, CategoryType type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (categoryRepository.existsByNameAndUserId(name, userId)) {
            throw new RuntimeException("Categoria já cadastrada para este usuário");
        }

        Category category = Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .type(type)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        Category savedCategory = categoryRepository.save(category);

        return toResponse(savedCategory);
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType(),
                category.getCreatedAt(),
                category.getUser().getId(),
                category.getUser().getName()
        );
    }
}