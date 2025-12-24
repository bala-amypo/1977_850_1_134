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
    public PatientProfile getPatientById(Long id) {
        return patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }

    @Override
    public PatientProfile updatePatient(Long id, PatientProfile updated) {

        PatientProfile existing = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setFullName(updated.getFullName());
        existing.setAge(updated.getAge());
        existing.setEmail(updated.getEmail());
        existing.setSurgeryType(updated.getSurgeryType());
        existing.setActive(updated.getActive());

        return patientProfileRepository.save(existing);
    }

    @Override
    public void deactivatePatient(Long id) {

        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        patient.setActive(false);
        patientProfileRepository.save(patient);
    }
}
 