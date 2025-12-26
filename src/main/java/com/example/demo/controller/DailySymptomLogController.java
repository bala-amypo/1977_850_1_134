package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
@Tag(name = "Daily Symptom Logs")
public class DailySymptomLogController {

    private final DailySymptomLogService dailySymptomLogService;

    public DailySymptomLogController(DailySymptomLogService dailySymptomLogService) {
        this.dailySymptomLogService = dailySymptomLogService;
    }

    @PostMapping
    public ResponseEntity<DailySymptomLog> create(@RequestBody DailySymptomLog log) {
        return ResponseEntity.ok(dailySymptomLogService.recordSymptomLog(log));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailySymptomLog> update(@PathVariable Long id, @RequestBody DailySymptomLog updated) {
        return ResponseEntity.ok(dailySymptomLogService.updateSymptomLog(id, updated));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DailySymptomLog>> byPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(dailySymptomLogService.getLogsByPatient(patientId));
    }
}
