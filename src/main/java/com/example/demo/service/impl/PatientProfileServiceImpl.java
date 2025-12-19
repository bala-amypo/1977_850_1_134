package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileServiceImpl(PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

    @Override
    public PatientProfile createPatient(PatientProfile patient) {
        if (patientProfileRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        patient.setActive(true);
        return patientProfileRepository.save(patient);
    }

    @Override
    public Optional<PatientProfile> getPatientById(Long id) {
        return Optional.ofNullable(
                patientProfileRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"))
        );
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }

    @Override
    public PatientProfile updatePatientStatus(Long id, boolean active) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patient.setActive(active);
        return patientProfileRepository.save(patient);
    }

    @Override
    public Optional<PatientProfile> findByPatientId(String patientId) {
        return patientProfileRepository.findByPatientId(patientId);
    }
}