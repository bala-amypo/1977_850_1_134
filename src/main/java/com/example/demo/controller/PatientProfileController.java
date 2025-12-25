package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
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
    public PatientProfile create(@RequestBody PatientProfile patient) {
        return patientProfileService.createPatient(patient);
    }

    @GetMapping("/{id}")
    public PatientProfile getById(@PathVariable Long id) {
        return patientProfileService.getPatientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @GetMapping("/lookup/{patientId}")
    public PatientProfile getByPatientId(@PathVariable String patientId) {
        return patientProfileService.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PutMapping("/{id}")
    public PatientProfile update(@PathVariable Long id,
                                 @RequestBody PatientProfile patient) {
        return patientProfileService.updatePatient(id, patient);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestParam boolean active) {
        patientProfileService.updatePatientStatus(id, active);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        patientProfileService.deactivatePatient(id);
    }

    @GetMapping
    public List<PatientProfile> getAll() {
        return patientProfileService.getAllPatients();
    }
}
