package com.example.demo.service.impl;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository repo;

    public DeviationRuleServiceImpl(DeviationRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DeviationRule> getAllRules() {
        return repo.findAll();
    }
}
