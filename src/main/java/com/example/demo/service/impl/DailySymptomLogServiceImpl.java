package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final DeviationRuleRepository deviationRuleRepository;
    private final ClinicalAlertRecordRepository alertRepository;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            DeviationRuleRepository deviationRuleRepository,
            ClinicalAlertRecordRepository alertRepository
    ) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.deviationRuleRepository = deviationRuleRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        String patientId = log.getPatient().getPatientId();

        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID: " + patientId));

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Log date cannot be in the future");
        }

        if (logRepository.findByPatientAndLogDate(patient, log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate daily symptom log");
        }

        log.setPatient(patient);
        DailySymptomLog savedLog = logRepository.save(log);

        List<DeviationRule> rules =
                deviationRuleRepository.findBySurgeryType(patient.getSurgeryType());

        for (DeviationRule rule : rules) {

            if (!Boolean.TRUE.equals(rule.getActive())) continue;

            boolean violated = false;

            switch (rule.getParameter().toLowerCase()) {

                case "pain":
                    violated = savedLog.getPainLevel() != null &&
                               savedLog.getPainLevel() > rule.getThreshold();
                    break;

                case "mobility":
                    violated = savedLog.getMobilityLevel() != null &&
                               savedLog.getMobilityLevel() < rule.getThreshold();
                    break;

                case "fatigue":
                    violated = savedLog.getFatigueLevel() != null &&
                               savedLog.getFatigueLevel() > rule.getThreshold();
                    break;
            }

            if (violated) {
                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patientId)
                        .logId(savedLog.getId())
                        .alertType(rule.getRuleCode())
                        .severity(rule.getSeverity())
                        .message("Deviation detected for " + rule.getParameter())
                        .resolved(false)
                        .build();

                alertRepository.save(alert);
            }
        }

        return savedLog;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(String patientId) {
        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));
        return logRepository.findByPatient(patient);
    }

    @Override
    public Optional<DailySymptomLog> getLogById(Long id) {
        return logRepository.findById(id);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log) {
        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
        log.setId(existing.getId());
        log.setPatient(existing.getPatient());
        return logRepository.save(log);
    }
}