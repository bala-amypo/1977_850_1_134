package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository dailySymptomLogRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final RecoveryCurveService recoveryCurveService;
    private final DeviationRuleService deviationRuleService;
    private final ClinicalAlertService clinicalAlertService;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository dailySymptomLogRepository,
            PatientProfileRepository patientProfileRepository,
            RecoveryCurveService recoveryCurveService,
            DeviationRuleService deviationRuleService,
            ClinicalAlertService clinicalAlertService
    ) {
        this.dailySymptomLogRepository = dailySymptomLogRepository;
        this.patientProfileRepository = patientProfileRepository;
        this.recoveryCurveService = recoveryCurveService;
        this.deviationRuleService = deviationRuleService;
        this.clinicalAlertService = clinicalAlertService;
    }

    @Override
    public DailySymptomLog recordSymptomLog(DailySymptomLog log) {

        PatientProfile patient = patientProfileRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        dailySymptomLogRepository
                .findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Duplicate daily log");
                });

        DailySymptomLog saved = dailySymptomLogRepository.save(log);

        List<RecoveryCurveProfile> curves =
                recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());

        List<DeviationRule> rules = deviationRuleService.getActiveRules();

        for (DeviationRule rule : rules) {

            if ("PAIN".equals(rule.getParameter())
                    && saved.getPainLevel() != null
                    && !curves.isEmpty()
                    && saved.getPainLevel() >
                       curves.get(0).getExpectedPainLevel() + rule.getThreshold()) {

                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patient.getId())
                        .logId(saved.getId())
                        .alertType("PAIN_SPIKE")
                        .severity(rule.getSeverity())
                        .message("Pain deviation detected")
                        .createdAt(LocalDateTime.now())
                        .build();

                clinicalAlertService.createAlert(alert);
            }
        }

        return saved;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {

        DailySymptomLog existing = dailySymptomLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        patientProfileRepository.findById(existing.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setPainLevel(updated.getPainLevel());
        existing.setMobilityLevel(updated.getMobilityLevel());
        existing.setFatigueLevel(updated.getFatigueLevel());
        existing.setAdditionalNotes(updated.getAdditionalNotes());

        return dailySymptomLogRepository.save(existing);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {

        patientProfileRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        return dailySymptomLogRepository.findByPatientId(patientId);
    }
}
