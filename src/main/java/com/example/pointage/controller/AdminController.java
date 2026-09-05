package com.example.pointage.controller;

import com.example.pointage.repository.CategoryRepository;
import com.example.pointage.repository.EmployeeRepository;
import com.example.pointage.repository.PointageRepository;
import com.example.pointage.repository.SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final SalaryRepository salaryRepository;
    private final PointageRepository pointageRepository;

    public AdminController(EmployeeRepository employeeRepository,
                            CategoryRepository categoryRepository,
                            SalaryRepository salaryRepository,
                            PointageRepository pointageRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
        this.salaryRepository = salaryRepository;
        this.pointageRepository = pointageRepository;
    }

    @DeleteMapping("/reset")
    @Transactional
    public ResponseEntity<Void> resetAll() {
        pointageRepository.deleteAllInBatch();
        salaryRepository.deleteAllInBatch();
        employeeRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        return ResponseEntity.noContent().build();
    }
}
