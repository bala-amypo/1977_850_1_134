package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clinical_alert_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicalAlertRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long logId;

    private String alertType;

    private String severity;

    private String message;

    @Builder.Default
    private Boolean resolved = false;
}
