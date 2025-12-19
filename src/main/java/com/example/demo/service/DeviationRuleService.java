package com.example.demo.service;

import com.example.demo.model.DeviationRule;

import java.util.List;
import java.util.Optional;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    Optional<DeviationRule> getRuleByCode(String ruleCode);

    List<DeviationRule> getRulesBySurgery(String surgeryType);

    List<DeviationRule> getActiveRules();

    List<DeviationRule> getAllRules();

    DeviationRule updateRule(Long id, DeviationRule rule);
}