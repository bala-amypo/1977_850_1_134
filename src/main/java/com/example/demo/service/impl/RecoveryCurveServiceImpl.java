package com.example.demo.service.impl;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    private final RecoveryCurveProfileRepository recoveryCurveProfileRepository;

    public RecoveryCurveServiceImpl(RecoveryCurveProfileRepository recoveryCurveProfileRepository) {
        this.recoveryCurveProfileRepository = recoveryCurveProfileRepository;
    }

    @Override
    public RecoveryCurveProfile createCurveEntry(RecoveryCurveProfile entry) {
        return recoveryCurveProfileRepository.save(entry);
    }

    @Override
    public List<RecoveryCurveProfile> getCurveForSurgery(String surgeryType) {
        return recoveryCurveProfileRepository.findBySurgeryTypeOrderByDayNumberAsc(surgeryType);
    }

    @Override
    public List<RecoveryCurveProfile> getAllCurves() {
        return recoveryCurveProfileRepository.findAll();
    }

    @Override
    public Optional<RecoveryCurveProfile> getCurveByDayAndSurgery(String surgeryType, Integer dayNumber) {
        return recoveryCurveProfileRepository.findAll()
                .stream()
                .filter(c -> c.getSurgeryType().equals(surgeryType)
                        && c.getDayNumber().equals(dayNumber))
                .findFirst();
    }
}
