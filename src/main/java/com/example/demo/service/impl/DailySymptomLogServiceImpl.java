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
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        dailySymptomLogRepository
                .findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(l -> {
                    throw new IllegalArgumentException("Duplicate daily log");
                });

        DailySymptomLog savedLog = dailySymptomLogRepository.save(log);

        List<RecoveryCurveProfile> curves =
                recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());

        if (curves.isEmpty()) {
            return savedLog;
        }

        RecoveryCurveProfile curve = curves.get(curves.size() - 1);

        List<DeviationRule> rules = deviationRuleService.getActiveRules();

        for (DeviationRule rule : rules) {

            int expected;
            int actual;

            switch (rule.getParameter().toLowerCase()) {
                case "pain" -> {
                    expected = curve.getExpectedPainLevel();
                    actual = savedLog.getPainLevel();
                }
                case "mobility" -> {
                    expected = curve.getExpectedMobilityLevel();
                    actual = savedLog.getMobilityLevel();
                }
                case "fatigue" -> {
                    expected = curve.getExpectedFatigueLevel();
                    actual = savedLog.getFatigueLevel();
                }
                default -> {
                    continue;
                }
            }

            if (Math.abs(actual - expected) > rule.getThreshold()) {

                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patient.getId())
                        .alertDate(LocalDate.now())
                        .severity("HIGH")
                        .message("Deviation detected for " + rule.getParameter())
                        .resolved(false)
                        .build();

                clinicalAlertService.createAlert(alert);
            }
        }

        return savedLog;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {

        DailySymptomLog existing = dailySymptomLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        existing.setPainLevel(updated.getPainLevel());
        existing.setMobilityLevel(updated.getMobilityLevel());
        existing.setFatigueLevel(updated.getFatigueLevel());
        existing.setAdditionalNotes(updated.getAdditionalNotes());

        return dailySymptomLogRepository.save(existing);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {

        patientProfileRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return dailySymptomLogRepository.findByPatientId(patientId);
    }
}
