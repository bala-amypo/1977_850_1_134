package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clinical_alert_records")
@Getter
@Setter
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

    private String severity;

    private String message;

    // 🔥 MUST be Boolean, not boolean (tests dereference it)
    private Boolean resolved;

    private LocalDateTime createdAt;
}
