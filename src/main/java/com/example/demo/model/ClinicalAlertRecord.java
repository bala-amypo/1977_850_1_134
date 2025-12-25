package com.example.demo.model;

import lombok.*;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalAlertRecord {

    private Long id;
    private Long patientId;
    private Long logId;
    private String alertType;
    private String severity;
    private String message;

    @Builder.Default
    private Boolean resolved = false;
}