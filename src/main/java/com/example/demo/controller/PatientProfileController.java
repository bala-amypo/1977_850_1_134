package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.service.PatientProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    // CREATE patient
    @PostMapping
    public PatientProfile createPatient(@RequestBody PatientProfile patientProfile) {
        return patientProfileService.createPatient(patientProfile);
    }

    // GET patient by ID
    @GetMapping("/{id}")
    public PatientProfile getPatientById(@PathVariable Long id) {
        return patientProfileService.getPatientById(id);
    }

    // GET all patients
    @GetMapping
    public List<PatientProfile> getAllPatients() {
        return patientProfileService.getAllPatients();
    }

    // UPDATE patient
    @PutMapping("/{id}")
    public PatientProfile updatePatient(
            @PathVariable Long id,
            @RequestBody PatientProfile patientProfile
    ) {
        return patientProfileService.updatePatient(id, patientProfile);
    }

    // DEACTIVATE patient (soft delete)
    @DeleteMapping("/{id}")
    public void deactivatePatient(@PathVariable Long id) {
        patientProfileService.deactivatePatient(id);
    }
}
