package com.example.pointage.controller;

import com.example.pointage.model.Day;
import com.example.pointage.repository.DayRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {

    private final DayRepository dayRepository;

    public DayController(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @GetMapping
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Day> getDayByName(@PathVariable String name) {
        return dayRepository.findFirstByNameOrderByIdAsc(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
