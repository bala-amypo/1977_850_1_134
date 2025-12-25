
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;
    private final RecoveryCurveService recoveryCurveService;
    private final DeviationRuleService deviationRuleService;
    private final ClinicalAlertService clinicalAlertService;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository,
            RecoveryCurveService recoveryCurveService,
            DeviationRuleService deviationRuleService,
            ClinicalAlertService clinicalAlertService
    ) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.recoveryCurveService = recoveryCurveService;
        this.deviationRuleService = deviationRuleService;
        this.clinicalAlertService = clinicalAlertService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        PatientProfile patient = patientRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        log.setLogDate(LocalDate.now());
        DailySymptomLog savedLog = logRepository.save(log);

        List<DeviationRule> rules = deviationRuleService.getActiveRules();

        for (DeviationRule rule : rules) {

            ClinicalAlertRecord alert = new ClinicalAlertRecord();
            alert.setPatientId(patient.getId());
            alert.setRuleCode(rule.getRuleCode());
            alert.setResolved(false);
            alert.setCreatedAt(LocalDateTime.now());

            clinicalAlertService.createAlert(alert);
        }

        return savedLog;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return logRepository.findByPatientId(patientId);
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
}
