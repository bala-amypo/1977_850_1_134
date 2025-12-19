package com.example.demo.service.impl;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.repository.RecoveryCurveProfileRepository;
import com.example.demo.service.RecoveryCurveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryCurveServiceImpl implements RecoveryCurveService {

    private final RecoveryCurveProfileRepository repository;

    public RecoveryCurveServiceImpl(RecoveryCurveProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecoveryCurveProfile createRecoveryCurve(RecoveryCurveProfile profile) {
        return repository.save(profile);
    }

    @Override
    public RecoveryCurveProfile getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RecoveryCurveProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public RecoveryCurveProfile update(Long id, RecoveryCurveProfile profile) {
        profile.setId(id);
        return repository.save(profile);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
