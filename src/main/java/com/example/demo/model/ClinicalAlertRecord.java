package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime; 

@Entity
@Table(name = "clinical_alert_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private String ruleCode;

    private String message;

    @Builder.Default
    private boolean resolved = false;

    public Long getLogId() {
        return id;
    }
    private LocalDateTime createdAt;
}
