package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.ClinicalAlertRecordRepository;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final ClinicalAlertRecordRepository alertRepository;
    private final RecoveryCurveService recoveryCurveService;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            ClinicalAlertRecordRepository alertRepository,
            RecoveryCurveService recoveryCurveService
    ) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.alertRepository = alertRepository;
        this.recoveryCurveService = recoveryCurveService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        // 🔑 Long → String conversion (VERY IMPORTANT)
        String patientId = String.valueOf(log.getPatientId());

        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        DailySymptomLog savedLog = logRepository.save(log);

        ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                .logId(savedLog.getId())
                .patientId(patient.getId())
                .alertType("SYMPTOM_DEVIATION")
                .message("Symptom deviation detected")
                .createdAt(LocalDateTime.now())
                .resolved(false)
                .build();

        alertRepository.save(alert);

        return savedLog;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log) {

        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        existing.setPainLevel(log.getPainLevel());
        existing.setMobilityLevel(log.getMobilityLevel());
        existing.setFatigueLevel(log.getFatigueLevel());
        existing.setAdditionalNotes(log.getAdditionalNotes());

        return logRepository.save(existing);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return logRepository.findByPatientId(patientId);
    }
}
   