// DAILYSYMPTOMLOG IMPL
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.service.DeviationRuleService;
import com.example.demo.service.RecoveryCurveService;
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

        // 1. Validate patient
        PatientProfile patient = patientProfileRepository.findById(log.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // 2. Future date validation
        if (log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("future date");
        }

        // 3. Duplicate log check
        if (dailySymptomLogRepository
                .findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .isPresent()) {
            throw new IllegalArgumentException("Duplicate daily log");
        }

        // 4. Save log
        DailySymptomLog savedLog = dailySymptomLogRepository.save(log);

        // 5. Get recovery curve
        List<RecoveryCurveProfile> curves =
                recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());

        if (curves.isEmpty()) {
            return savedLog;
        }

        RecoveryCurveProfile curve = curves.get(curves.size() - 1);

        // 6. Get active deviation rules
        List<DeviationRule> rules = deviationRuleService.getActiveRules();

        // 7. Compare deviation (simplified & entity-safe)
        for (DeviationRule rule : rules) {

            if (!rule.getActive()) {
                continue;
            }

            int actual =
                    log.getPainLevel()
                    + log.getMobilityLevel()
                    + log.getFatigueLevel();

            int expected =
                    curve.getExpectedPainLevel()
                    + curve.getExpectedMobilityLevel()
                    + curve.getExpectedFatigueLevel();

            if (Math.abs(actual - expected) > rule.getThresholdDeviation()) {

                ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                        .patientId(patient.getId())
                        .logId(savedLog.getId())
                        .message("Deviation detected")
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
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

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
