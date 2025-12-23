package com.example.demo.controller;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.service.RecoveryCurveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;        

@RestController
@RequestMapping("/api/recovery-curves")
@Tag(name = "Recovery Curves")
public class RecoveryCurveController {   

    private final RecoveryCurveService recoveryCurveService;

    public RecoveryCurveController(RecoveryCurveService recoveryCurveService) {
        this.recoveryCurveService = recoveryCurveService;
    }

    @PostMapping
    public RecoveryCurveProfile createCurve(@RequestBody RecoveryCurveProfile curve) {
        return recoveryCurveService.createCurveEntry(curve);
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<RecoveryCurveProfile> getCurveForSurgery(@PathVariable String surgeryType) {
        return recoveryCurveService.getCurveForSurgery(surgeryType);
    }

    @GetMapping
    public List<RecoveryCurveProfile> getAllCurves() {
        return recoveryCurveService.getAllCurves();
    }
}

