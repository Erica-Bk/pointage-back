package com.example.pointage.controller;

import com.example.pointage.model.Suplementaire;
import com.example.pointage.repository.SuplementaireRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suplementaire")
public class SuplementaireController {

    private final SuplementaireRepository suplementaireRepository;

    public SuplementaireController(SuplementaireRepository suplementaireRepository) {
        this.suplementaireRepository = suplementaireRepository;
    }

    @GetMapping
    public List<Suplementaire> getAllSuplementaires() {
        return suplementaireRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suplementaire> getSuplementaireById(@PathVariable Long id) {
        return suplementaireRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Suplementaire createSuplementaire(@RequestBody Suplementaire suplementaire) {
        return suplementaireRepository.save(suplementaire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suplementaire> updateSuplementaire(@PathVariable Long id, @RequestBody Suplementaire updated) {
        return suplementaireRepository.findById(id)
                .map(existing -> {
                    existing.setHeure(updated.getHeure());
                    return ResponseEntity.ok(suplementaireRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuplementaire(@PathVariable Long id) {
        if (!suplementaireRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        suplementaireRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}