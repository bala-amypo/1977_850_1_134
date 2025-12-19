
package com.example.demo.service.impl;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository repository;

    public DeviationRuleServiceImpl(DeviationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviationRule createRule(DeviationRule rule) {
        return repository.save(rule);
    }

    @Override
    public DeviationRule getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DeviationRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public DeviationRule update(Long id, DeviationRule rule) {
        rule.setId(id);
        return repository.save(rule);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.service;

import com.example.demo.model.DeviationRule;

import java.util.List;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    DeviationRule getById(Long id);

    List<DeviationRule> getAllRules();

    DeviationRule update(Long id, DeviationRule rule);

    void delete(Long id);
}

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

package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;

import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog createLog(DailySymptomLog log);

    DailySymptomLog getById(Long id);

    List<DailySymptomLog> getAll();

    List<DailySymptomLog> getLogsByPatient(Long patientId);

    void delete(Long id);
}

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
    public ClinicalAlertRecord createAlert(ClinicalAlertRecord alert) {
        return repository.save(alert);
    }

    @Override
    public ClinicalAlertRecord getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ClinicalAlertRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ClinicalAlertRecord> getAlertsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}


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