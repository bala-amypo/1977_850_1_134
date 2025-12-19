package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;

public interface DailySymptomLogService {
    List<DailySymptomLog> getLogsByPatient(Long patientId);
}
