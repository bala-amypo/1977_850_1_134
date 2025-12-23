package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.DeviationRule;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final DeviationRuleRepository deviationRuleRepository;
    private final ClinicalAlertService clinicalAlertService;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            DeviationRuleRepository deviationRuleRepository,
            ClinicalAlertService clinicalAlertService
    ) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.deviationRuleRepository = deviationRuleRepository;
        this.clinicalAlertService = clinicalAlertService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        String patientId = log.getPatient().getPatientId();

        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID: " + patientId));

        // Future date validation
        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Log date cannot be in the future");
        }

        // Duplicate log validation
        if (logRepository.findByPatientAndLogDate(patient, log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate daily symptom log");
        }

        // Save daily symptom log
        log.setPatient(patient);
        DailySymptomLog savedLog = logRepository.save(log);

        // Fetch active deviation rules for surgery type
        List<DeviationRule> rules =
                deviationRuleRepository.findBySurgeryTypeAndActiveTrue(
                        patient.getSurgeryType()
                );

        // Check rule violations
        for (DeviationRule rule : rules) {

            boolean violated = false;

            switch (rule.getParameter()) {

                case "painLevel":
                    violated = savedLog.getPainLevel() != null &&
                               savedLog.getPainLevel() > rule.getThreshold();
                    break;

                case "mobilityLevel":
                    violated = savedLog.getMobilityLevel() != null &&
                               savedLog.getMobilityLevel() < rule.getThreshold();
                    break;

                case "fatigueLevel":
                    violated = savedLog.getFatigueLevel() != null &&
                               savedLog.getFatigueLevel() > rule.getThreshold();
                    break;
            }   

            // Auto-generate clinical alert
            if (violated) {
                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patient.getId())
                        .logId(savedLog.getId())
                        .alertType(rule.getRuleCode())
                        .severity(rule.getSeverity())
                        .message("Deviation detected for " + rule.getParameter())
                        .resolved(false)
                        .build();

                clinicalAlertService.createAlert(alert);
            }
        }

        return savedLog;
    }
}