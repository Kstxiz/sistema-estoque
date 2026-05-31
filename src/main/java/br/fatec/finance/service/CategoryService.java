package br.fatec.finance.service;

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

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(UUID userId, String name, CategoryType type) {
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

        return categoryRepository.save(category);
    }
}