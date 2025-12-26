package com.example.demo.controller;

import com.example.demo.model.PatientProfile;
import com.example.demo.service.PatientProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patients")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @PostMapping
    public ResponseEntity<PatientProfile> create(@RequestBody PatientProfile profile) {
        return ResponseEntity.ok(patientProfileService.createPatient(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientProfileService.getPatientById(id));
    }

    @GetMapping
    public ResponseEntity<List<PatientProfile>> getAll() {
        return ResponseEntity.ok(patientProfileService.getAllPatients());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PatientProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(patientProfileService.updatePatientStatus(id, active));
    }

    @GetMapping("/lookup/{patientId}")
    public ResponseEntity<Optional<PatientProfile>> findByPatientId(@PathVariable String patientId) {
        return ResponseEntity.ok(patientProfileService.findByPatientId(patientId));
    }
}
