package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_symptom_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private LocalDate logDate;

    private int painLevel;

    private int mobilityLevel;

    private int fatigueLevel;

    private String additionalNotes;
}