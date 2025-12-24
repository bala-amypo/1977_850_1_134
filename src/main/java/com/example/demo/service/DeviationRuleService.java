package com.example.demo.service;

import com.example.demo.model.DeviationRule;

import java.util.List;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    DeviationRule updateRule(Long id, DeviationRule rule);

    List<DeviationRule> getAllRules();

    List<DeviationRule> getActiveRules();
}
  