package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;

import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createCurveEntry(RecoveryCurveProfile entry);

    List<RecoveryCurveProfile> getCurveForSurgery(String surgeryType);

    List<RecoveryCurveProfile> getAllCurves();
}
