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

    @Column(nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String surgeryType;

    @Column(nullable = false)
    private String parameter;

    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private Boolean active;
}
