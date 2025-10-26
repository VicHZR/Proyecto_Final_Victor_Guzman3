package com.store.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Runner para ejecutar solo los escenarios fallidos.
 */
@CucumberOptions(
    features = "@target/failedrerun.txt",
    glue = {"com.store.stepDefinitions", "com.store.util"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/reports/cucumber-reports/cucumber.xml",
        "timeline:target/reports/cucumber-timeline",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/failedrerun.txt"
    },
    monochrome = true
)
public class TestRunnerFailed extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}