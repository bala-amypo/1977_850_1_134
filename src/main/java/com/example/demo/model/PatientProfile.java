package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(unique = true, nullable = false)
    private String patientId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer age;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String surgeryType;

    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}


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


package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "daily_symptom_logs",
    uniqueConstraints = @UniqueConstraint(columnNames = {"patient_id", "logDate"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private LocalDate logDate;

    @Column(nullable = false)
    private Integer painLevel;

    @Column(nullable = false)
    private Integer mobilityLevel;

    @Column(nullable = false)
    private Integer fatigueLevel;

    @Lob
    private String additionalNotes;

    @CreationTimestamp
    private LocalDateTime submittedAt;
}


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

    @Column(nullable = false)
    private String surgeryType;

    @Column(nullable = false)
    private String parameter;

    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private Boolean active = true;
}

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









