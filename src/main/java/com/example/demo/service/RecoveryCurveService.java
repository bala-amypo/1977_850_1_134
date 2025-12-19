package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;
import java.util.List;

public interface RecoveryCurveService {
    RecoveryCurveProfile createCurve(RecoveryCurveProfile curve);
    List<RecoveryCurveProfile> getAll();
    List<RecoveryCurveProfile> getBySurgeryType(String surgeryType);
}
