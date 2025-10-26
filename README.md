# FinalProject - Victor Hugo Guzman Prieto

## 📌 Información Personal

- **Nombre:** Victor Hugo Guzman Prieto
- **Correo:** [victorguzpri@gmail.com]
- **Curso:** Test Automation using Selenium & Cucumber BDD
- **Proyecto:** Automatización de pruebas para OWASP Juice Shop

---

## 🚀 Tecnologías Utilizadas

- Java 21
- Selenium WebDriver
- Cucumber BDD (Gherkin)
- TestNG
- Page Object Model (POM) + PageFactory
- Extent Reports
- Allure Reports
- Log4j (para logs)
- Maven
- Eclipse IDE

---

## 📁 Estructura del Proyecto

```plaintext
FinalProject_Victor_Guzman/
├── src/
│   └── test/
│       ├── java/
│       │   └── com.store/
│       │       ├── factory/              # Clases POM (LoginPage, RegisterPage, etc.)
│       │       ├── runners/              # TestRunner.java y TestRunnerFailed.java
│       │       ├── stepDefinitions/      # LoginSteps.java, RegisterSteps.java, etc.
│       │       └── util/                 # Base.java, Hooks.java, Reader.java
│       └── resources/
│           ├── features/                 # Archivos .feature (login.feature, register.feature)
│           └── config.properties         # Configuración del entorno
├── extent.properties
├── allure.properties
├── cucumber.properties
├── pom.xml
└── README.md
```
---

## ⚙️ Configuración Inicial

1. **Clonar el repositorio:**

```bash
git clone https://github.com/VicHZR/FinalProject_Victor_Guzman.git
cd FinalProject_Victor_Guzman
```