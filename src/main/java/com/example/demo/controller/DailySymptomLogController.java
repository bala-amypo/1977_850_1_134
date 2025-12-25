package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptoms")
public class DailySymptomLogController {

    private final DailySymptomLogService dailySymptomLogService;

    public DailySymptomLogController(DailySymptomLogService dailySymptomLogService) {
        this.dailySymptomLogService = dailySymptomLogService;
    }

    @PostMapping
    public DailySymptomLog record(@RequestBody DailySymptomLog log) {
        return dailySymptomLogService.recordSymptomLog(log);
    }

    @PutMapping("/{id}")
    public DailySymptomLog update(
            @PathVariable Long id,
            @RequestBody DailySymptomLog log
    ) {
        return dailySymptomLogService.updateSymptomLog(id, log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getByPatient(@PathVariable Long patientId) {
        return dailySymptomLogService.getLogsByPatient(patientId);
    }
}
   