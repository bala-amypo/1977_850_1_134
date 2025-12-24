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

    @PostMapping
    public PatientProfile createPatient(@RequestBody PatientProfile patient) {
        return patientProfileService.createPatient(patient);
    }

    @GetMapping("/{id}")
    public PatientProfile getPatient(@PathVariable Long id) {
        return patientProfileService.getPatientById(id);
    }

    @GetMapping
    public List<PatientProfile> getAllPatients() {
        return patientProfileService.getAllPatients();
    }

    @PutMapping("/{id}/status")
    public PatientProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return patientProfileService.updatePatientStatus(id, active);
    }

    @GetMapping("/lookup/{patientId}")
    public PatientProfile lookupByPatientId(@PathVariable String patientId) {
        return patientProfileService.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}


