

⸻

4️⃣ DeviationRuleServiceImpl.java




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



⸻

✅ WHAT YOU SHOULD DO NOW
	1.	Paste all 6 impl files
	2.	Click Save
	3.	Run Verify

If any error appears, send only the error text, not screenshots — I’ll fix it line-by-line.

You’re now exactly at first-review ready state 💯