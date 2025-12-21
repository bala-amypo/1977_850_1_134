package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_symptom_logs",
       uniqueConstraints = @UniqueConstraint(columnNames = {"patient_id", "logDate"}))
@Data
@Builder    
@NoArgsConstructor     
@AllArgsConstructor
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityLevel;

    private Integer fatigueLevel;

    @Lob // reduces character limit
    private String additionalNotes;

    @CreationTimestamp
    private LocalDateTime submittedAt;
}
