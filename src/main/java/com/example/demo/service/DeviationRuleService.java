package com.example.demo.service;

import com.example.demo.model.DeviationRule;

import java.util.List;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    List<DeviationRule> getRulesBySurgery(String surgeryType);

    List<DeviationRule> getAllRules();
}
