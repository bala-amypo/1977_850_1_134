

// =======================
// DailySymptomLog.java
// =======================
package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySymptomLog {

    private Long id;
    private Long patientId;
    private LocalDate logDate;
    private Integer painLevel;
    private Integer mobilityLevel;
    private Integer fatigueLevel;
    private String additionalNotes;
    private LocalDateTime submittedAt;
}

