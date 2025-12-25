package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;

import java.time.LocalDate;
import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog recordSymptomLog(DailySymptomLog log);

    List<DailySymptomLog> getLogsForPatient(Long patientId);

    DailySymptomLog getLogForDate(Long patientId, LocalDate date);
}
