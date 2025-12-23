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
import java.util.Optional;

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
            ClinicalAlertService clinicalAlertService) {

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

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Log date cannot be in the future");
        }

        if (logRepository.findByPatientAndLogDate(patient, log.getLogDate()).isPresent()) {
            throw new IllegalArgumentException("Duplicate daily symptom log");
        }

        log.setPatient(patient);
        DailySymptomLog savedLog = logRepository.save(log);


        List<DeviationRule> rules = deviationRuleRepository.findAll();

        for (DeviationRule rule : rules) {

            if (!rule.getActive()) continue;

            boolean violated = false;
            String message = "";

            if (rule.getPainThreshold() != null &&
                    savedLog.getPainLevel() > rule.getPainThreshold()) {

                violated = true;
                message = "Pain level exceeded threshold";
            }

            if (rule.getMobilityThreshold() != null &&
                    savedLog.getMobilityLevel() < rule.getMobilityThreshold()) {

                violated = true;
                message = "Mobility level dropped below threshold";
            }

            if (rule.getFatigueThreshold() != null &&
                    savedLog.getFatigueLevel() > rule.getFatigueThreshold()) {

                violated = true;
                message = "Fatigue level exceeded threshold";
            }

            if (violated) {
                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patient.getPatientId())
                        .logId(savedLog.getId())
                        .alertType("Deviation Rule Violation")
                        .severity(rule.getSeverity())
                        .message(message)
                        .resolved(false)
                        .build();

                clinicalAlertService.createAlert(alert);
            }
        }

        return savedLog;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(String patientId) {
        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

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