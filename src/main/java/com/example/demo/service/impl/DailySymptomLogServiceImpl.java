
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.ClinicalAlertRecordRepository;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final ClinicalAlertRecordRepository alertRepository;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            ClinicalAlertRecordRepository alertRepository
    ) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        PatientProfile patient = patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        DailySymptomLog savedLog = logRepository.save(log);

        ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                .logId(savedLog.getId())
                .patientId(patient.getId())
                .alertType("SYMPTOM_DEVIATION")
                .message("Deviation detected in daily symptom log")
                .resolved(false)
                .createdAt(LocalDateTime.now())
                .build();

        alertRepository.save(alert);

        return savedLog;
    }

    @Override
    public List<DailySymptomLog> getLogsForPatient(Long patientId) {
        return logRepository.findByPatientId(patientId);
    }

    @Override
    public DailySymptomLog getLogForDate(Long patientId, LocalDate date) {
        return logRepository.findByPatientIdAndLogDate(patientId, date)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }
}