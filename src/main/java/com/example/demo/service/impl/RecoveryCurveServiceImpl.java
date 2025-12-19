package com.example.demo.service.impl;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    private final RecoveryCurveProfileRepository repo;

    public RecoveryCurveServiceImpl(RecoveryCurveProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public RecoveryCurveProfile createCurve(RecoveryCurveProfile profile) {
        return repo.save(profile);
    }

    @Override
    public List<RecoveryCurveProfile> getBySurgeryType(String surgeryType) {
        return repo.findBySurgeryType(surgeryType);
    }
}
