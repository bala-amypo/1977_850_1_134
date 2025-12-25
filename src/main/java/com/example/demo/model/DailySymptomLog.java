package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_symptom_logs",
       uniqueConstraints = @UniqueConstraint(columnNames = {"patientId", "logDate"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityLevel;

    private Integer fatigueLevel;

    private String additionalNotes;

    private LocalDateTime submittedAt;
}
