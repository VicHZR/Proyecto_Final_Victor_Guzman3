# 🔒 Advanced Security-Oriented Web UI Automation Framework

<p align="left">
  <a href="https://oracle.com">
    <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" />
  </a>
  <a href="https://selenium.dev">
    <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" />
  </a>
  <a href="https://cucumber.io">
    <img src="https://img.shields.io/badge/Cucumber-BDD-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" />
  </a>
  <a href="https://testng.org">
    <img src="https://img.shields.io/badge/TestNG-DD0031?style=for-the-badge&logo=testng&logoColor=white" />
  </a>
</p>

---

## 🚀 Overview

A production-grade, business-critical **End-to-End Test Automation Framework** designed to execute regression and transactional logic validation over the **OWASP Juice Shop** application ecosystem.

Built with **Java 21**, **Selenium WebDriver**, and **Cucumber BDD (Gherkin)**, this repository implements advanced software architecture standards like the **Page Object Model (POM) with PageFactory encapsulation**. It features dual-engine graphical reporting and multi-threaded logging built to track system behaviors under high-security validation constraints.

🎯 Focus: **Cybersecurity platform behavior validation, robust web element synchronization, comprehensive trace logs, and advanced enterprise test reporting.**

---

## 🎯 Key Technical Features

- 🏗️ **PageFactory Optimization Pattern:** Enhanced UI component localization utilizing lazy-loading `@FindBy` annotations to avoid dynamic DOM rendering errors.
- 🥒 **Behavior-Driven Development (BDD):** Structured regression suites mapped cleanly through user-centric Gherkin features to bridge technical and operational gaps.
- 🛠️ **Dual Reporting Architectures:** Native integration of **Allure Reports** for comprehensive trend analytics alongside **Extent Reports** for clean runtime failure tracking.
- 📜 **Enterprise Trace Logging:** Thread-safe tracing matrices powered by **Log4j** to monitor systemic step execution parameters and exceptions cleanly.

---

## 🏗️ Project Architecture Layout

The testing engine splits framework infrastructure layer dependencies under strict clean code paradigms:

```text
FinalProject_Victor_Guzman/
│
├── src/
│   └── test/
│       ├── java/
│       │   └── com.store/
│       │       ├── factory/            # POM Classes encapsulated with PageFactory initialization
│       │       ├── runners/            # TestRunner execution matrices & Failed Rerun scripts
│       │       ├── stepDefinitions/    # Step mapping blocks connecting Gherkin specifications to Java code
│       │       └── util/               # Automation base configurations, explicit Hooks, and property Readers
│       │
│       └── resources/
│           ├── features/               # Plain-text functional BDD scenarios (.feature files)
│           └── config.properties       # Environment operational variable declarations
│
├── pom.xml                             # Apache Maven dependency manifest (Java 21 baseline)
└── README.md                           # Core technical documentation
```

---

## 📊 Impact (CV-Level Highlights)

- 📈 **Highly Scalable DOM Interaction:** Reduced template script breakdown thresholds by encapsulating web elements into specialized PageFactory pattern classes.
- ⚡ **Zero-Friction Flakiness Mitigation:** Engineered dynamic explicit element polling hooks inside base utility models, dropping timing failure rates down to near zero.
- 🛡️ **Audit-Ready Validation Framework:** Deployed comprehensive run reporting dashboards (Allure + Extent Reports) providing bulletproof validation trails for operational compliance audits.
- 🔍 **Granular Debugging Turnover:** Drastically slashed post-execution debugging lifecycles by incorporating automated contextual Log4j trace monitors.

---

## ⚙️ Installation & Test Execution Guide

### 1. Prerequisites
Ensure your automation environment hosts the following baseline tools:
- **Java SE Development Kit (JDK 21)**
- **Apache Maven 3.8+**
- Active Google Chrome browser installation with compatible system webdrivers.

### 2. Project Bootstrapping
```bash
git clone https://github.com
cd Proyecto_Final_Victor_Guzman3
```

### 3. Resolve Dependencies & Compile Workspace
```bash
mvn clean install -DskipTests
```

### 4. Execute the Functional Suite
Run the active automation check suites from your command line terminal shell:
```bash
mvn test
```

---

## 🔐 Automation Best Practices Applied

- **Clean Decoupled Configurations:** Operational variables and target application URLs remain fully abstracted out of main classes into independent `.properties` parameters.
- **Fail-Safe Script Re-Runs:** Incorporates independent fallback test runner classes configured strictly to retry failed executions, eliminating environment-induced false negatives.
- **System Memory Optimization:** WebDriver sessions tear down elegantly inside global post-execution blocks to protect processing memory heaps.

---

## 👨‍💻 Author

**Victor Guzmán**  
*Computational Scientist | Backend Engineer | Software Quality & Test Automation Specialist*  
- 🔗 **LinkedIn:** [https://linkedin.com](https://www.linkedin.com/in/victor-h-guzm%C3%A1n-a19361187/)
