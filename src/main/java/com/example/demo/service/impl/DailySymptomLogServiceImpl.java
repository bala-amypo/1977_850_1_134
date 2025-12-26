package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.ClinicalAlertService;
import com.example.demo.service.DailySymptomLogService;
import com.example.demo.service.RecoveryCurveService;
import com.example.demo.service.DeviationRuleService;
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

    public DailySymptomLogServiceImpl(DailySymptomLogRepository dailySymptomLogRepository,
                                      PatientProfileRepository patientProfileRepository,
                                      RecoveryCurveService recoveryCurveService,
                                      DeviationRuleService deviationRuleService,
                                      ClinicalAlertService clinicalAlertService) {
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
        if (log.getLogDate() != null && log.getLogDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Log cannot have future date");
        }
        dailySymptomLogRepository.findByPatientIdAndLogDate(log.getPatientId(), log.getLogDate())
                .ifPresent(existing -> { throw new IllegalArgumentException("Duplicate daily log"); });

        DailySymptomLog saved = dailySymptomLogRepository.save(log);

        // Conceptual alerting: if pain level is higher than expected by threshold, create alert
        List<RecoveryCurveProfile> curve = recoveryCurveService.getCurveForSurgery(patient.getSurgeryType());
        curve.stream()
                .filter(c -> c.getDayNumber() != null && log.getLogDate() != null)
                .filter(c -> c.getDayNumber().equals(log.getLogDate().getDayOfMonth())) // simplistic mapping for concept
                .findFirst()
                .ifPresent(c -> {
                    if (log.getPainLevel() != null && c.getExpectedPainLevel() != null) {
                        int delta = log.getPainLevel() - c.getExpectedPainLevel();
                        // any positive delta triggers an alert conceptually
                        if (delta > 0) {
                            ClinicalAlertRecord alert = ClinicalAlertRecord.builder()
                                    .patientId(log.getPatientId())
                                    .logId(saved.getId())
                                    .alertType("PAIN_HIGH")
                                    .severity("MEDIUM")
                                    .message("Pain spike detected")
                                    .build();
                            clinicalAlertService.createAlert(alert);
                        }
                    }
                });

        return saved;
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog updated) {
        DailySymptomLog existing = dailySymptomLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Daily log not found"));
        // preserve patient id
        updated.setId(existing.getId());
        updated.setPatientId(existing.getPatientId());
        return dailySymptomLogRepository.save(updated);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        patientProfileRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return dailySymptomLogRepository.findByPatientId(patientId);
    }
}
