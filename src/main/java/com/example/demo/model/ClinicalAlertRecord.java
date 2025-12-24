
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "clinical_alert_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long logId;

    @Column(nullable = false)
    private String alertType;

    @Column(nullable = false)
    private String severity;

    @Lob
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean resolved = false;

    @CreationTimestamp
    private LocalDate alertDate;
}









