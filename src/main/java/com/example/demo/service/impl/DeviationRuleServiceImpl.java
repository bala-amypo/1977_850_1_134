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
    public List<DeviationRule> getAll() {
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
