package com.example.demo.repository;

import com.example.demo.model.RecoveryCurveProfile;

import java.util.List;

public interface RecoveryCurveProfileRepository {

    RecoveryCurveProfile save(RecoveryCurveProfile curve);

    List<RecoveryCurveProfile> findBySurgeryTypeOrderByDayNumberAsc(String surgeryType);

    List<RecoveryCurveProfile> findAll();
}
