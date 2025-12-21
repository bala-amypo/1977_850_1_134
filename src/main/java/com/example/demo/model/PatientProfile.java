package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_profiles")
@Data
@Builder
@NoArgsConstructor    
@AllArgsConstructor
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

    private Boolean active; // active or discharged ?

    @CreationTimestamp
    private LocalDateTime createdAt;
}

