package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}

package com.example.demo.service;

import com.example.demo.model.PatientProfile;
import java.util.List;

public interface PatientProfileService {

    PatientProfile createPatient(PatientProfile patient);

    PatientProfile getPatientById(Long id);

    List<PatientProfile> getAllPatients();
}


// =======================
// DailySymptomLogService.java
// =======================
package com.example.demo.service;

import com.example.demo.model.DailySymptomLog;
import java.util.List;

public interface DailySymptomLogService {

    DailySymptomLog createLog(DailySymptomLog log);

    List<DailySymptomLog> getLogsByPatient(Long patientId);
}


// =======================
// RecoveryCurveService.java
// =======================
package com.example.demo.service;

import com.example.demo.model.RecoveryCurveProfile;
import java.util.List;

public interface RecoveryCurveService {

    RecoveryCurveProfile createCurve(RecoveryCurveProfile profile);

    List<RecoveryCurveProfile> getBySurgeryType(String surgeryType);

    List<RecoveryCurveProfile> getAll();
}


// =======================
// DeviationRuleService.java
// =======================
package com.example.demo.service;

import com.example.demo.model.DeviationRule;
import java.util.List;

public interface DeviationRuleService {

    DeviationRule createRule(DeviationRule rule);

    List<DeviationRule> getAllRules();
}


// =======================
// ClinicalAlertService.java
// =======================
package com.example.demo.service;

import com.example.demo.model.ClinicalAlertRecord;
import java.util.List;

public interface ClinicalAlertService {

    ClinicalAlertRecord createAlert(ClinicalAlertRecord alert);

    ClinicalAlertRecord getById(Long id);

    List<ClinicalAlertRecord> getAll();

    List<ClinicalAlertRecord> getAlertsByPatient(Long patientId);

    void delete(Long id);
}