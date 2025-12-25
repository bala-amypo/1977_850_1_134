package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        existing.setFullName(patient.getFullName());
        existing.setAge(patient.getAge());
        existing.setEmail(patient.getEmail());
        existing.setSurgeryType(patient.getSurgeryType());

        return patientProfileRepository.save(existing);
    }

    @Override
    public PatientProfile updatePatientStatus(Long id, boolean active) {

        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        patient.setActive(active);
        return patientProfileRepository.save(patient);
    }

    @Override
    public PatientProfile findByPatientId(String patientId) {

        return patientProfileRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public PatientProfile getPatientById(Long id) {

        return patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public PatientProfile deactivatePatient(Long id) {

        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        patient.setActive(false);
        return patientProfileRepository.save(patient);
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }
}
