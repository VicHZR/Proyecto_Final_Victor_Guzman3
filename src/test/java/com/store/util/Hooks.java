package com.store.util;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Hooks {
    Base base = new Base(); // ✅ Instancia de Base para inicializar el driver
    WebDriver driver;

    // ✅ Se ejecuta antes de cada escenario
    @Before
    public void setUp() {
        driver = base.loadDriver(); // Inicializa el navegador
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = Base.returnDriver();
        if (driver == null) {
            System.out.println("⚠️ WebDriver no estaba inicializado al finalizar el escenario.");
            return;
        }
        if (!scenario.isFailed()) {
            Base.quitDriver();
            return;
        }
        try {
            // Adjunta a Cucumber/Allure
            attachScreenshot(scenario, driver);
        } catch (Exception e) {
            System.out.println("❌ Error al capturar el screenshot: " + e.getMessage());
        } finally {
            Base.quitDriver();
        }
    }

    public void attachScreenshot(Scenario scenario, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "screenshot");
    }
}