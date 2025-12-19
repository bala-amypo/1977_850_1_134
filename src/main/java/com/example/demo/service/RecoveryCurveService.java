package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;

import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createRecoveryCurve(RecoveryCurveProfile profile);

    RecoveryCurveProfile getById(Long id);

    List<RecoveryCurveProfile> getAll();

    RecoveryCurveProfile update(Long id, RecoveryCurveProfile profile);

    void delete(Long id);
}
