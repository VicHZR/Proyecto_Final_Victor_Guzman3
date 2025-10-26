package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[aria-label='Show/hide account menu']")
    WebElement accountButton;

    @FindBy(css = "button[aria-label='Go to login page']")
    WebElement loginMenuButton;

    @FindBy(css = "#newCustomerLink > a")
    WebElement notYetCustomerLink;

    @FindBy(css = "button[aria-label='Close Welcome Banner']")
    WebElement closeWelcomeBanner;

    @FindBy(css = "button[aria-label='Logout']")
    WebElement logoutButton;

    public void closeWelcomeBannerIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(closeWelcomeBanner)).click();
            System.out.println("✅ Banner de bienvenida cerrado.");
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a[aria-label='dismiss cookie message']"))).click();
            System.out.println("✅ Mensaje de cookies cerrado.");
        } catch (Exception e) {
            System.out.println("⚠️ No se encontró el banner de bienvenida/coolies o ya estaba cerrado.");
        }
    }

    public void openAccountMenu() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
        } catch (Exception e) {
            System.out.println("❌ No se pudo abrir el menú de cuenta: " + e.getMessage());
            throw e;
        }
    }

    public void goToLogin() {
        openAccountMenu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginMenuButton)).click();
    }

    public void goToRegister() {
        goToLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(notYetCustomerLink)).click();
    }

    public void logout() {
        openAccountMenu();
        logoutButton.click();
    }
} 