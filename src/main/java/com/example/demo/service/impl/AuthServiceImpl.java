package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;

    public AuthServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void register(RegisterRequest request) {
        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(UserRole.valueOf(request.getRole()))
                .build();

        appUserRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // No security/JWT logic as per mam's instruction
        return new AuthResponse("DUMMY_TOKEN");
    }
}


⸻

2️⃣ ClinicalAlertServiceImpl.java

package com.example.demo.service.impl;

import com.example.demo.model.ClinicalAlertRecord;
import com.example.demo.repository.ClinicalAlertRecordRepository;
import com.example.demo.service.ClinicalAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalAlertServiceImpl implements ClinicalAlertService {

    private final ClinicalAlertRecordRepository repository;

    public ClinicalAlertServiceImpl(ClinicalAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClinicalAlertRecord> getAlertsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}


⸻

3️⃣ DailySymptomLogServiceImpl.java

package com.example.demo.service.impl;

import com.example.demo.model.DailySymptomLog;
import com.example.demo.repository.DailySymptomLogRepository;
import com.example.demo.service.DailySymptomLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailySymptomLogServiceImpl implements DailySymptomLogService {

    private final DailySymptomLogRepository repository;

    public DailySymptomLogServiceImpl(DailySymptomLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public DailySymptomLog createLog(DailySymptomLog log) {
        return repository.save(log);
    }

    @Override
    public List<DailySymptomLog> getLogsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}


⸻

4️⃣ DeviationRuleServiceImpl.java

package com.example.demo.service.impl;

import com.example.demo.model.DeviationRule;
import com.example.demo.repository.DeviationRuleRepository;
import com.example.demo.service.DeviationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviationRuleServiceImpl implements DeviationRuleService {

    private final DeviationRuleRepository repository;

    public DeviationRuleServiceImpl(DeviationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviationRule createRule(DeviationRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<DeviationRule> getAllRules() {
        return repository.findAll();
    }
}


⸻

5️⃣ PatientProfileServiceImpl.java

package com.example.demo.service.impl;

import com.example.demo.model.PatientProfile;
import com.example.demo.repository.PatientProfileRepository;
import com.example.demo.service.PatientProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientProfileRepository repository;

    public PatientProfileServiceImpl(PatientProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientProfile createPatient(PatientProfile patient) {
        return repository.save(patient);
    }

    @Override
    public PatientProfile getPatientById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PatientProfile> getAllPatients() {
        return repository.findAll();
    }
}


⸻

6️⃣ RecoveryCurveServiceImpl.java

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
    public RecoveryCurveProfile createCurve(RecoveryCurveProfile profile) {
        return repository.save(profile);
    }

    @Override
    public List<RecoveryCurveProfile> getBySurgeryType(String surgeryType) {
        return repository.findBySurgeryType(surgeryType);
    }

    @Override
    public List<RecoveryCurveProfile> getAll() {
        return repository.findAll();
    }
}


⸻

✅ WHAT YOU SHOULD DO NOW
	1.	Paste all 6 impl files
	2.	Click Save
	3.	Run Verify

If any error appears, send only the error text, not screenshots — I’ll fix it line-by-line.

You’re now exactly at first-review ready state 💯