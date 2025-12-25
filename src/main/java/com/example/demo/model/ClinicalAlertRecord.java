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

    // 🔑 TEST EXPECTS THIS EXACT NAME
    private Long logId;

    private Long patientId;

    // 🔑 TEST EXPECTS alertType(String)
    private String alertType;

    // 🔑 TEST EXPECTS severity(String)
    private String severity;

    private String message;

    // 🔑 TEST EXPECTS getResolved()
    @Builder.Default
    private boolean resolved = false;

    private LocalDateTime createdAt;
}
