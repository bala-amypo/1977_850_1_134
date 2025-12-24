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
        return patientProfileRepository.save(patient);
    }

    @Override
    public PatientProfile updatePatient(Long id, PatientProfile patient) {

        PatientProfile existing = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setFullName(patient.getFullName());
        existing.setAge(patient.getAge());
        existing.setEmail(patient.getEmail());
        existing.setSurgeryType(patient.getSurgeryType());

        return patientProfileRepository.save(existing);
    }

    @Override
    public void updatePatientStatus(Long id, boolean active) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        patient.setActive(active);
        patientProfileRepository.save(patient);
    }

    @Override
    public Optional<PatientProfile> findByPatientId(String patientId) {
        return patientProfileRepository.findByPatientId(patientId);
    }

    @Override
    public Optional<PatientProfile> getPatientById(Long id) {
        return patientProfileRepository.findById(id);
    }

    @Override
    public void deactivatePatient(Long id) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        patient.setActive(false);
        patientProfileRepository.save(patient);
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }
}
 