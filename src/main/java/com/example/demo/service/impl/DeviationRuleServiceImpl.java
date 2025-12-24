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

        if (rule.getThresholdDeviation() <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }

        return deviationRuleRepository.save(rule);
    }

    @Override
    public DeviationRule updateRule(Long id, DeviationRule rule) {

        DeviationRule existing = deviationRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        existing.setThresholdDeviation(rule.getThresholdDeviation());
        existing.setActive(rule.getActive());
        existing.setSurgeryType(rule.getSurgeryType());

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
