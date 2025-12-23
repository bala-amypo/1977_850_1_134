package com.example.demo.controller;
import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.service.ClinicalAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController     
@RequestMapping("/api/alerts") 
@Tag(name = "Clinical Alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService clinicalAlertService;

    public ClinicalAlertController(ClinicalAlertService clinicalAlertService) {
        this.clinicalAlertService = clinicalAlertService;
    }
    @PostMapping
    public ClinicalAlertRecord createAlert(@RequestBody ClinicalAlertRecord alert) {
        return clinicalAlertService.createAlert(alert);
    }
    @PutMapping("/{id}/resolve")
    public ClinicalAlertRecord resolveAlert(@PathVariable Long id) {
        return clinicalAlertService.resolveAlert(id);
    }
    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlertRecord> getAlertsByPatient(@PathVariable Long patientId) {
        return clinicalAlertService.getAlertsByPatient(patientId);
    }
    @GetMapping("/{id}")
    public ClinicalAlertRecord getAlertById(@PathVariable Long id) {
        return clinicalAlertService.getAlertById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
    @GetMapping
    public List<ClinicalAlertRecord> getAllAlerts() {
        return clinicalAlertService.getAllAlerts();
    }
}
