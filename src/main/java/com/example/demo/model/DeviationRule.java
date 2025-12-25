package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deviation_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REQUIRED BY TESTS
    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String surgeryType;

    // REQUIRED BY TESTS
    @Column(nullable = false)
    private String parameter;

    // REQUIRED BY TESTS
    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private Boolean active;
}
