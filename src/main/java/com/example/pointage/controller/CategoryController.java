package com.example.pointage.controller;

import com.example.pointage.model.Category;
import com.example.pointage.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        return categoryRepository.findFirstByNameOrderByIdAsc(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PostMapping("/find-or-create")
    public Category findOrCreateCategory(@RequestBody Category category) {
        return categoryRepository.findFirstByNameOrderByIdAsc(category.getName())
                .orElseGet(() -> categoryRepository.save(category));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updated) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setHoursPerWeek(updated.getHoursPerWeek());
                    existing.setWeeklySalary(updated.getWeeklySalary());
                    existing.setTaux(updated.getTaux());
                    return ResponseEntity.ok(categoryRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updated) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    if (updated.getName() != null) {
                        existing.setName(updated.getName());
                    }
                    if (updated.getHoursPerWeek() != null) {
                        existing.setHoursPerWeek(updated.getHoursPerWeek());
                    }
                    if (updated.getWeeklySalary() != null) {
                        existing.setWeeklySalary(updated.getWeeklySalary());
                    }
                    if (updated.getTaux() != null) {
                        existing.setTaux(updated.getTaux());
                    }
                    return ResponseEntity.ok(categoryRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
