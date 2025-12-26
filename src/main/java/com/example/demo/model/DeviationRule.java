package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deviation_rules", indexes = {
        @Index(name = "idx_rule_code", columnList = "ruleCode", unique = true)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleCode;

    private String parameter; // PAIN, MOBILITY, etc

    private Integer threshold;

    private String severity;

    @Builder.Default
    private Boolean active = true;

    private String surgeryType; // for surgery specific rules
}
