
// =======================
// DeviationRule.java
// =======================
package com.example.demo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviationRule {

    private Long id;
    private String ruleCode;
    private String surgeryType;
    private String parameter;
    private Integer threshold;
    private String severity;

    @Builder.Default
    private Boolean active = true;
}


