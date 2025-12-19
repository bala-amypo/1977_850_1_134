package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository repo;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }
}
