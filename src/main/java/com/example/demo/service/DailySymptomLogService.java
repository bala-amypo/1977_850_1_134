package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;

public interface DailySymptomLogService {
    DailySymptomLog createLog(DailySymptomLog log);
    List<DailySymptomLog> getLogsByPatient(Long patientId);
}
