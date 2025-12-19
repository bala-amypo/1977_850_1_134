package com.example.demo.service;

import com.example.demo.model.ClinicalAlertRecord;

import java.util.List;

public interface ClinicalAlertService {

    ClinicalAlertRecord createAlert(ClinicalAlertRecord alert);

    ClinicalAlertRecord getById(Long id);

    List<ClinicalAlertRecord> getAll();

    List<ClinicalAlertRecord> getAlertsByPatient(Long patientId);

    void delete(Long id);
    }