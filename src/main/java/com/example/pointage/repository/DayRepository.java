package com.example.pointage.repository;

import com.example.pointage.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findFirstByNameOrderByIdAsc(String name);
}
