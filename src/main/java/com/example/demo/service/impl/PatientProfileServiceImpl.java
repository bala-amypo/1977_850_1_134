package com.example.demo.service.impl;

import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository repository;

    public PatientProfileServiceImpl(PatientProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientProfile createPatient(PatientProfile patientProfile) {
        return repository.save(patientProfile);
    }

    @Override
    public PatientProfile getPatientById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return repository.findAll();
    }

    @Override
    public PatientProfile updatePatient(Long id, PatientProfile patientProfile) {
        patientProfile.setId(id);
        return repository.save(patientProfile);
    }

    @Override
    public void deletePatient(Long id) {
        repository.deleteById(id);
    }
}
