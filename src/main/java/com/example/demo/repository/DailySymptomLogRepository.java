package com.example.demo.repository;

import com.example.demo.model.DailySymptomLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailySymptomLogRepository extends JpaRepository<DailySymptomLog, Long> {

    List<DailySymptomLog> findByPatientId(Long patientId);

    Optional<DailySymptomLog> findByPatientIdAndLogDate(Long patientId, LocalDate logDate);
}

package com.example.demo.repository;

import com.example.demo.model.DeviationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviationRuleRepository extends JpaRepository<DeviationRule, Long> {

    Optional<DeviationRule> findByRuleCode(String ruleCode);

    List<DeviationRule> findByActiveTrue();
}

// =======================
// ClinicalAlertRecordRepository.java
// =======================
package com.example.demo.repository;

import com.example.demo.model.ClinicalAlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalAlertRecordRepository extends JpaRepository<ClinicalAlertRecord, Long> {

    List<ClinicalAlertRecord> findByPatientId(Long patientId);
}