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

import com.example.demo.model.RecoveryCurveProfile;

import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createCurve(RecoveryCurveProfile profile);

    RecoveryCurveProfile getById(Long id);

    RecoveryCurveProfile getBySurgeryType(String surgeryType);

    List<RecoveryCurveProfile> getAll();

    RecoveryCurveProfile update(Long id, RecoveryCurveProfile profile);

    void delete(Long id);
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
    public RecoveryCurveProfile createCurve(RecoveryCurveProfile profile) {
        return repository.save(profile);
    }

    @Override
    public RecoveryCurveProfile getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RecoveryCurveProfile getBySurgeryType(String surgeryType) {
        return repository.findBySurgeryType(surgeryType);
    }

    @Override
    public List<RecoveryCurveProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public RecoveryCurveProfile update(Long id, RecoveryCurveProfile profile) {
        profile.setId(id);
        return repository.save(profile);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(AuthRequest request) {
        AuthResponse response = new AuthResponse();
        response.setToken("dummy-jwt-token");
        return response;
    }

    @Override
    public void register(RegisterRequest request) {
        // no-op for first review
    }
}
