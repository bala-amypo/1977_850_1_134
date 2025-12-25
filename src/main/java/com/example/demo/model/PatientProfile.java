
// =======================
// PatientProfile.java
// =======================
package com.example.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfile {

    private Long id;
    private String patientId;
    private String fullName;
    private Integer age;
    private String email;
    private String surgeryType;

    @Builder.Default
    private Boolean active = true;

    private LocalDateTime createdAt;
}
