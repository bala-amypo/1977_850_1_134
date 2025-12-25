package com.example.demo.repository;

import com.example.demo.model.DailySymptomLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailySymptomLogRepository {

    Optional<DailySymptomLog> findById(Long id);

    List<DailySymptomLog> findByPatientId(Long patientId);

    Optional<DailySymptomLog> findByPatientIdAndLogDate(Long patientId, LocalDate logDate);

    DailySymptomLog save(DailySymptomLog log);
}
