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
