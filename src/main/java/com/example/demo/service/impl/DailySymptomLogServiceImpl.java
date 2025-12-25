package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

       
        if (log.getLogDate() == null || log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid log date");
        }

        logRepository.findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Duplicate log");
                });

        DailySymptomLog saved = logRepository.save(log);

        // Fetch recovery curve (for validation / reference)
        recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());


        deviationRuleService.getActiveRules().forEach(rule -> {
            if (saved.getPainLevel() > rule.getThreshold()) {
                ClinicalAlertRecord alert = new ClinicalAlertRecord();
                alert.setPatientId(saved.getPatientId());
                alert.setRuleCode(rule.getRuleCode());
                alert.setResolved(false);
                clinicalAlertService.createAlert(alert);
            }
        });

        return saved;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return logRepository.findByPatientId(patientId);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {
        DailySymptomLog existing = logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        existing.setPainLevel(updated.getPainLevel());
        existing.setMobilityLevel(updated.getMobilityLevel());
        existing.setFatigueLevel(updated.getFatigueLevel());
        existing.setLogDate(updated.getLogDate());

        return logRepository.save(existing);
    }
}