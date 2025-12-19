package com.example.demo.controller;

import com.example.demo.model.DeviationRule;
import com.example.demo.service.DeviationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deviation-rules")
@Tag(name = "Deviation Rules")
public class DeviationRuleController {

    private final DeviationRuleService deviationRuleService;

    public DeviationRuleController(DeviationRuleService deviationRuleService) {
        this.deviationRuleService = deviationRuleService;
    }

    @PostMapping
    public DeviationRule createRule(@RequestBody DeviationRule rule) {
        return deviationRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public DeviationRule updateRule(
            @PathVariable Long id,
            @RequestBody DeviationRule rule) {
        return deviationRuleService.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<DeviationRule> getActiveRules() {
        return deviationRuleService.getActiveRules();
    }

    @GetMapping
    public List<DeviationRule> getAllRules() {
        return deviationRuleService.getAllRules();
    }
}
