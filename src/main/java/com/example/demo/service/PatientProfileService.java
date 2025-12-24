package com.example.demo.service;

import com.example.demo.model.PatientProfile;

import java.util.List;
import java.util.Optional;

public interface PatientProfileService {

    PatientProfile createPatient(PatientProfile patient);

    PatientProfile updatePatient(Long id, PatientProfile patient);

    void updatePatientStatus(Long id, boolean active);

    Optional<PatientProfile> findByPatientId(String patientId);

    Optional<PatientProfile> getPatientById(Long id);

    void deactivatePatient(Long id);

    List<PatientProfile> getAllPatients();
} 