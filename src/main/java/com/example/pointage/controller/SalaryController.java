package com.example.pointage.controller;

import com.example.pointage.model.Salary;
import com.example.pointage.repository.SalaryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryRepository salaryRepository;

    public SalaryController(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @GetMapping
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
        return salaryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public List<Salary> getSalariesByEmployee(@PathVariable Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }

    @PostMapping
    public Salary createSalary(@RequestBody Salary salary) {
        return salaryRepository.save(salary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary updated) {
        return salaryRepository.findById(id)
                .map(existing -> {
                    existing.setEmployee(updated.getEmployee());
                    existing.setNormalHour(updated.getNormalHour());
                    existing.setBaseSalary(updated.getBaseSalary());
                    return ResponseEntity.ok(salaryRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        if (!salaryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        salaryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
