package com.example.demo.controller;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recovery-curves")
public class RecoveryCurveController {

    private final RecoveryCurveService recoveryCurveService;

    public RecoveryCurveController(RecoveryCurveService recoveryCurveService) {
        this.recoveryCurveService = recoveryCurveService;
    }

    @PostMapping
    public RecoveryCurveProfile create(@RequestBody RecoveryCurveProfile curve) {
        return recoveryCurveService.createCurveEntry(curve);
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<RecoveryCurveProfile> getBySurgery(@PathVariable String surgeryType) {
        return recoveryCurveService.getCurveForSurgery(surgeryType);
    }

    @GetMapping
    public List<RecoveryCurveProfile> getAll() {
        return recoveryCurveService.getAllCurves();
    }
}


package com.example.demo.controller;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symptom-logs")
public class DailySymptomLogController {

    private final DailySymptomLogService dailySymptomLogService;

    public DailySymptomLogController(DailySymptomLogService dailySymptomLogService) {
        this.dailySymptomLogService = dailySymptomLogService;
    }

    @PostMapping
    public DailySymptomLog create(@RequestBody DailySymptomLog log) {
        return dailySymptomLogService.recordSymptomLog(log);
    }

    @PutMapping("/{id}")
    public DailySymptomLog update(@PathVariable Long id,
                                  @RequestBody DailySymptomLog log) {
        return dailySymptomLogService.updateSymptomLog(id, log);
    }

    @GetMapping("/patient/{patientId}")
    public List<DailySymptomLog> getByPatient(@PathVariable Long patientId) {
        return dailySymptomLogService.getLogsByPatient(patientId);
    }
}


// =======================
// DeviationRuleController.java
// =======================
package com.example.demo.controller;

import com.example.demo.model.DeviationRule;
import com.example.demo.service.DeviationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deviation-rules")
public class DeviationRuleController {

    private final DeviationRuleService deviationRuleService;

    public DeviationRuleController(DeviationRuleService deviationRuleService) {
        this.deviationRuleService = deviationRuleService;
    }

    @PostMapping
    public DeviationRule create(@RequestBody DeviationRule rule) {
        return deviationRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public DeviationRule update(@PathVariable Long id,
                                @RequestBody DeviationRule rule) {
        return deviationRuleService.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<DeviationRule> getActive() {
        return deviationRuleService.getActiveRules();
    }

    @GetMapping
    public List<DeviationRule> getAll() {
        return deviationRuleService.getAllRules();
    }
}


// =======================
// ClinicalAlertController.java
// =======================
package com.example.demo.controller;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class ClinicalAlertController {

    private final ClinicalAlertService clinicalAlertService;

    public ClinicalAlertController(ClinicalAlertService clinicalAlertService) {
        this.clinicalAlertService = clinicalAlertService;
    }

    @PostMapping
    public ClinicalAlertRecord create(@RequestBody ClinicalAlertRecord alert) {
        return clinicalAlertService.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public ClinicalAlertRecord resolve(@PathVariable Long id) {
        return clinicalAlertService.resolveAlert(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<ClinicalAlertRecord> getByPatient(@PathVariable Long patientId) {
        return clinicalAlertService.getAlertsByPatient(patientId);
    }

    @GetMapping
    public List<ClinicalAlertRecord> getAll() {
        return clinicalAlertService.getAllAlerts();
    }
}