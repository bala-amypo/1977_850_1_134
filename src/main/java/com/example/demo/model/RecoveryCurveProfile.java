package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recovery_curve_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surgeryType;

    @Column(nullable = false)
    private Integer dayNumber;

    @Column(nullable = false)
    private Integer expectedPainLevel;

    @Column(nullable = false)
    private Integer expectedMobilityLevel;

    @Column(nullable = false)
    private Integer expectedFatigueLevel;
}
