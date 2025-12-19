

# 1️⃣ `UserRole.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

public enum UserRole {
    ADMIN,
    CLINICIAN
}
```

---

# 2️⃣ `AppUser.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
```

---

# 3️⃣ `PatientProfile.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String patientId;

    private String fullName;

    private Integer age;

    @Column(unique = true, nullable = false)
    private String email;

    private String surgeryType;

    private Boolean active;

    private LocalDateTime createdAt;
}
```

---

# 4️⃣ `RecoveryCurveProfile.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoveryCurveProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surgeryType;

    private Integer dayNumber;

    private Integer maxPainLevel;

    private Integer minMobilityScore;
}
```

---

# 5️⃣ `DailySymptomLog.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailySymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private LocalDate logDate;

    private Integer painLevel;

    private Integer mobilityScore;

    private Integer fatigueLevel;
}
```

---

# 6️⃣ `DeviationRule.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surgeryType;

    private String ruleCode;

    private Integer threshold;

    private Boolean active;
}
```

---

# 7️⃣ `ClinicalAlertRecord.java`

📁 `com.example.demo.model`

```java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicalAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long logId;

    private String alertType;

    private Boolean resolved;

    private LocalDateTime alertDate;
}
