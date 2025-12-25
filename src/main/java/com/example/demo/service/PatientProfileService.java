package com.example.demo.service;

import com.example.demo.model.PatientProfile;

import java.util.List;

public interface PatientProfileService {

    PatientProfile createPatient(PatientProfile patient);

    PatientProfile updatePatient(Long id, PatientProfile patient);

    PatientProfile updatePatientStatus(Long id, boolean active);

    PatientProfile findByPatientId(String patientId);

    PatientProfile getPatientById(Long id);

    PatientProfile deactivatePatient(Long id);

    List<PatientProfile> getAllPatients();
}
