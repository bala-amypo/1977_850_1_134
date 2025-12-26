package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_symptom_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityLevel;

    private Integer fatigueLevel;

    private String additionalNotes;
}
