
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository deviationRuleRepository;

    public DeviationRuleServiceImpl(DeviationRuleRepository deviationRuleRepository) {
        this.deviationRuleRepository = deviationRuleRepository;
    }

    @Override
    public DeviationRule createRule(DeviationRule rule) {
        if (rule.getThreshold() <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }
        return deviationRuleRepository.save(rule);
    }

    @Override
    public DeviationRule updateRule(Long id, DeviationRule rule) {

        DeviationRule existing = deviationRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        existing.setRuleCode(rule.getRuleCode());
        existing.setParameter(rule.getParameter());
        existing.setThreshold(rule.getThreshold());
        existing.setActive(rule.getActive());

        return deviationRuleRepository.save(existing);
    }

    @Override
    public List<DeviationRule> getAllRules() {
        return deviationRuleRepository.findAll();
    }

    @Override
    public List<DeviationRule> getActiveRules() {
        return deviationRuleRepository.findByActiveTrue();
    }

    @Override
    public Optional<DeviationRule> getRuleByCode(String ruleCode) {
        return deviationRuleRepository.findByRuleCode(ruleCode);
    }
}

package com.example.demo.service;

import com.example.demo.model.PatientProfile;

import java.util.List;
import java.util.Optional;

public interface PatientProfileService {

    PatientProfile createPatient(PatientProfile patient);

    PatientProfile updatePatient(Long id, PatientProfile patient);

    void updatePatientStatus(Long id, boolean active);

    Optional<PatientProfile> findByPatientId(String patientId);

    List<PatientProfile> getAllPatients();
}

package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileServiceImpl(PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

    @Override
    public PatientProfile createPatient(PatientProfile patient) {
        return patientProfileRepository.save(patient);
    }

    @Override
    public PatientProfile updatePatient(Long id, PatientProfile patient) {

        PatientProfile existing = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setFullName(patient.getFullName());
        existing.setAge(patient.getAge());
        existing.setEmail(patient.getEmail());
        existing.setSurgeryType(patient.getSurgeryType());

        return patientProfileRepository.save(existing);
    }

    @Override
    public void updatePatientStatus(Long id, boolean active) {
        PatientProfile patient = patientProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        patient.setActive(active);
        patientProfileRepository.save(patient);
    }

    @Override
    public Optional<PatientProfile> findByPatientId(String patientId) {
        return patientProfileRepository.findByPatientId(patientId);
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return patientProfileRepository.findAll();
    }
}