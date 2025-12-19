package com.example.demo.service;

import com.example.demo.model.PatientProfile;

import java.util.List;

public interface PatientProfileService {

    PatientProfile createPatient(PatientProfile patientProfile);

    PatientProfile getPatientById(Long id);

    List<PatientProfile> getAllPatients();

    PatientProfile updatePatient(Long id, PatientProfile patientProfile);

    void deletePatient(Long id);
}
