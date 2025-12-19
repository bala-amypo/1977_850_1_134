package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository repository;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public DailySymptomLog createLog(DailySymptomLog log) {
        return repository.save(log);
    }

    @Override
    public DailySymptomLog getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DailySymptomLog> getAll() {
        return repository.findAll();
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}