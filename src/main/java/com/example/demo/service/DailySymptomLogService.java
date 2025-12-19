package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;

import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog createLog(DailySymptomLog log);

    DailySymptomLog getById(Long id);

    List<DailySymptomLog> getAll();

    void delete(Long id);
}
