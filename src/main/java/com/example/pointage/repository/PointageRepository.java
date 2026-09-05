package com.example.pointage.repository;

import com.example.pointage.model.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointageRepository extends JpaRepository<Pointage, Long> {
    List<Pointage> findByEmployeeId(Long employeeId);

    Optional<Pointage> findFirstByEmployeeIdAndDayOrderByIdAsc(
            Long employeeId,
            Integer day
    );
}
