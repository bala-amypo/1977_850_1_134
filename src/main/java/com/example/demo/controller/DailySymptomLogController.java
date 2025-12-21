package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public DailySymptomLog recordLog(@RequestBody DailySymptomLog log) {
        return dailySymptomLogService.recordSymptomLog(log);
    }

    @PutMapping("/{id}")
    public DailySymptomLog updateLog(
            @PathVariable Long id,
            @RequestBody DailySymptomLog log) {
        return dailySymptomLogService.updateSymptomLog(id, log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getLogsByPatient(@PathVariable Long patientId) {
        return dailySymptomLogService.getLogsByPatient(patientId);
    }

    @GetMapping("/{id}")
    public DailySymptomLog getLogById(@PathVariable Long id) {
        return dailySymptomLogService.getLogById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
} 
