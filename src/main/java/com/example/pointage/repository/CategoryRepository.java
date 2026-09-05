package com.example.pointage.repository;

import com.example.pointage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByNameOrderByIdAsc(String name);
}
