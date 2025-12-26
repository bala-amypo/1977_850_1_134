package com.example.demo.controller;

import com.example.demo.model.DeviationRule;
import com.example.demo.service.DeviationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deviation-rules")
@Tag(name = "Deviation Rules")
public class DeviationRuleController {

    private final DeviationRuleService deviationRuleService;

    public DeviationRuleController(DeviationRuleService deviationRuleService) {
        this.deviationRuleService = deviationRuleService;
    }

    @PostMapping
    public ResponseEntity<DeviationRule> create(@RequestBody DeviationRule rule) {
        return ResponseEntity.ok(deviationRuleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviationRule> update(@PathVariable Long id, @RequestBody DeviationRule rule) {
        return ResponseEntity.ok(deviationRuleService.updateRule(id, rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<DeviationRule>> active() {
        return ResponseEntity.ok(deviationRuleService.getActiveRules());
    }

    @GetMapping("/code/{ruleCode}")
    public ResponseEntity<Optional<DeviationRule>> byCode(@PathVariable String ruleCode) {
        return ResponseEntity.ok(deviationRuleService.getRuleByCode(ruleCode));
    }

    @GetMapping
    public ResponseEntity<List<DeviationRule>> all() {
        return ResponseEntity.ok(deviationRuleService.getAllRules());
    }
}
