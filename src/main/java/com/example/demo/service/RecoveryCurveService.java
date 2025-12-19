package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;
import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createCurve(RecoveryCurveProfile profile);

    List<RecoveryCurveProfile> getBySurgeryType(String surgeryType);

    List<RecoveryCurveProfile> getAll();
}