package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-logs")
public class DailySymptomLogController {

    private final DailySymptomLogService dailySymptomLogService;

    public DailySymptomLogController(DailySymptomLogService dailySymptomLogService) {
        this.dailySymptomLogService = dailySymptomLogService;
    }

    @PostMapping
    public DailySymptomLog recordLog(@RequestBody DailySymptomLog log) {
        return dailySymptomLogService.recordSymptomLog(log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getLogsForPatient(@PathVariable Long patientId) {
        return dailySymptomLogService.getLogsForPatient(patientId);
    }

    @GetMapping("/patient/{patientId}/date/{date}")
    public DailySymptomLog getLogForDate(
            @PathVariable Long patientId,
            @PathVariable String date
    ) {
        return dailySymptomLogService.getLogForDate(
                patientId,
                LocalDate.parse(date)
        );
    }
}