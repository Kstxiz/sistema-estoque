package br.fatec.finance.repository;

import br.fatec.finance.entity.Category;
import br.fatec.finance.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByUserId(UUID userId);

    List<Category> findByUserIdAndType(UUID userId, CategoryType type);

    boolean existsByNameAndUserId(String name, UUID userId);
}