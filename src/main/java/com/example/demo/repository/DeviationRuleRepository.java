
package com.example.demo.repository;

import com.example.demo.model.DeviationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviationRuleRepository extends JpaRepository<DeviationRule, Long> {

    List<DeviationRule> findByActiveTrue();

    Optional<DeviationRule> findByRuleCode(String ruleCode);
}

package com.example.demo.service;

import com.example.demo.model.DeviationRule;

import java.util.List;
import java.util.Optional;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    DeviationRule updateRule(Long id, DeviationRule rule);

    List<DeviationRule> getAllRules();

    List<DeviationRule> getActiveRules();

    Optional<DeviationRule> getRuleByCode(String ruleCode);
}
