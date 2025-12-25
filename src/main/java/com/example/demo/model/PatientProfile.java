
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String patientId;

    private String fullName;

    private Integer age;

    @Column(unique = true)
    private String email;

    private String surgeryType;

    @Builder.Default
    private Boolean active = true;

    private LocalDateTime createdAt;
}
