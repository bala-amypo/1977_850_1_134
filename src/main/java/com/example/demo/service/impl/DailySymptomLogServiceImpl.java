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
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repository.findByPatientProfileId(patientId);
    }
}

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
    public List<DeviationRule> getAllRules() {
        return repository.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    private final RecoveryCurveProfileRepository repository;

    public RecoveryCurveServiceImpl(RecoveryCurveProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecoveryCurveProfile createCurve(RecoveryCurveProfile curve) {
        return repository.save(curve);
    }

    @Override
    public List<RecoveryCurveProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public List<RecoveryCurveProfile> getBySurgeryType(String surgeryType) {
        return repository.findBySurgeryType(surgeryType);
    }
}
