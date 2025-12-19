package com.example.demo.controller;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recovery-curves")
public class RecoveryCurveController {

    private final RecoveryCurveService recoveryCurveService;

    public RecoveryCurveController(RecoveryCurveService recoveryCurveService) {
        this.recoveryCurveService = recoveryCurveService;
    }

    @PostMapping
    public RecoveryCurveProfile create(@RequestBody RecoveryCurveProfile profile) {
        return recoveryCurveService.createCurve(profile);
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<RecoveryCurveProfile> getBySurgery(@PathVariable String surgeryType) {
        return recoveryCurveService.getBySurgeryType(surgeryType);
    }

    @GetMapping
    public List<RecoveryCurveProfile> getAll() {
        return recoveryCurveService.getAll();
    }
}
