package com.example.demo.service;

import com.example.demo.model.ClinicalAlertRecord;
import java.util.List;

public interface ClinicalAlertService {
    ClinicalAlertRecord createAlert(ClinicalAlertRecord alert);
    List<ClinicalAlertRecord> getAlertsByPatient(Long patientId);
}
