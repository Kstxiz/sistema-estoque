package br.fatec.finance.controller;

import br.fatec.finance.dto.CategoryResponse;
import br.fatec.finance.entity.Category;
import br.fatec.finance.enums.CategoryType;
import br.fatec.finance.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return categoryService.create(
                request.userId(),
                request.name(),
                request.type()
        );
    }

    public record CreateCategoryRequest(
            UUID userId,
            String name,
            CategoryType type
    ) {
    }
}