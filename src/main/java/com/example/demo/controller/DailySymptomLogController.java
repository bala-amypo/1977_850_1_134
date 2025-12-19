package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptom-logs")
public class DailySymptomLogController {

    private final DailySymptomLogService dailySymptomLogService;

    public DailySymptomLogController(DailySymptomLogService dailySymptomLogService) {
        this.dailySymptomLogService = dailySymptomLogService;
    }

    @PostMapping
    public DailySymptomLog create(@RequestBody DailySymptomLog log) {
        return dailySymptomLogService.createLog(log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getByPatient(@PathVariable Long patientId) {
        return dailySymptomLogService.getLogsByPatient(patientId);
    }
}
