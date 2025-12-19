package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.service.PatientProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
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
        return patientProfileService.getPatientById(id);
    }

    @GetMapping
    public List<PatientProfile> getAll() {
        return patientProfileService.getAllPatients();
    }
}
