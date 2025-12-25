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

    private Long logId;

    private Long patientId;

    private String alertType;

    private String message;

    @Builder.Default
    private boolean resolved = false;

    private LocalDateTime createdAt;


    public boolean getResolved() {
        return resolved;
    }


    public String getAlertType() {
        return alertType;
    }
}
