package com.example.pointage.controller;

import com.example.pointage.model.Pointage;
import com.example.pointage.repository.PointageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    private final PointageRepository pointageRepository;

    public PointageController(PointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    @GetMapping
    public List<Pointage> getAllPointages() {
        return pointageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable Long id) {
        return pointageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public List<Pointage> getPointagesByEmployee(
            @PathVariable Long employeeId) {

        return pointageRepository.findByEmployeeId(employeeId);
    }

    @GetMapping("/search")
    public ResponseEntity<Pointage> findEntry(
            @RequestParam Long employeeId,
            @RequestParam Integer day) {

        return pointageRepository
                .findFirstByEmployeeIdAndDayOrderByIdAsc(employeeId, day)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pointage createPointage(@RequestBody Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pointage> updatePointage(
            @PathVariable Long id,
            @RequestBody Pointage updated) {

        return pointageRepository.findById(id)
                .map(existing -> {
                    existing.setEmployee(updated.getEmployee());
                    existing.setHoursWorked(updated.getHoursWorked());
                    existing.setDay(updated.getDay());

                    return ResponseEntity.ok(
                            pointageRepository.save(existing)
                    );
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointage(@PathVariable Long id) {

        if (!pointageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pointageRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}