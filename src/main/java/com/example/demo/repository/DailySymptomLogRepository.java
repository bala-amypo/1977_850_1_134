package com.example.demo.repository;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailySymptomLogRepository extends JpaRepository<DailySymptomLog, Long> {

    List<DailySymptomLog> findByPatient(PatientProfile patient);

    Optional<DailySymptomLog> findByPatientAndLogDate(
            PatientProfile patient, LocalDate logDate);
}           