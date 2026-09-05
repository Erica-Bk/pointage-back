package com.example.pointage.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hours_per_week", nullable = false)
    private Integer hoursPerWeek;

    @Column(name = "weekly_salary", nullable = false)
    private BigDecimal weeklySalary;

    @Column(name = "taux", nullable = false)
    private BigDecimal taux;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public BigDecimal getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(BigDecimal weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public BigDecimal getTaux() {
        return taux;
    }

    public void setTaux(BigDecimal taux) {
        this.taux = taux;
    }
}
