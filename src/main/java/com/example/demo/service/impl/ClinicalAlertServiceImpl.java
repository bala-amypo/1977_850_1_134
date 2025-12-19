package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.repository.ClinicalAlertRecordRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRecordRepository repository;

    public ClinicalAlertServiceImpl(ClinicalAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClinicalAlertRecord> getAlertsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
