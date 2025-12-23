package com.example.demo.service.impl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DailySymptomLog;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository logRepository;
    private final PatientProfileRepository patientRepository;

    public DailySymptomLogServiceImpl(
            DailySymptomLogRepository logRepository,
            PatientProfileRepository patientRepository) { 
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
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
        return logRepository.save(log);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(String patientId) {

        PatientProfile patient = patientRepository
                .findByPatientId(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID: " + patientId));

        return logRepository.findByPatient(patient);
    }

    @Override
    public Optional<DailySymptomLog> getLogById(Long id) {
        return logRepository.findById(id);
    }

    @Override
    public DailySymptomLog updateSymptomLog(Long id, DailySymptomLog log) {

        DailySymptomLog existingLog = logRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Symptom log not found with ID: " + id));

        log.setId(existingLog.getId());
        log.setPatient(existingLog.getPatient());
        log.setSubmittedAt(existingLog.getSubmittedAt());

        return logRepository.save(log);
    }
}