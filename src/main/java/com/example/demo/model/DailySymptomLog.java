package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_symptom_logs", indexes = {
        @Index(name = "idx_patient_date", columnList = "patientId,logDate", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailySymptomLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId; // FK reference to PatientProfile.id

    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityLevel;

    private Integer fatigueLevel;

    private String additionalNotes;
}
