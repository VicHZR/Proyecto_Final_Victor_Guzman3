package com.store.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "emailControl")
    WebElement emailInput;

    @FindBy(id = "passwordControl")
    WebElement passwordInput;

    @FindBy(id = "repeatPasswordControl")
    WebElement repeatPasswordInput;

    @FindBy(css = "mat-select[name='securityQuestion']")
    WebElement securityQuestionDropdown;

    @FindBy(xpath = "//span[contains(text(),\"Mother's maiden name?\")]")
    WebElement securityQuestionOption;

    @FindBy(id = "securityAnswerControl")
    WebElement securityAnswerInput;

    @FindBy(id = "registerButton")
    WebElement registerButton;

    public void register(String email, String password, String answer) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        passwordInput.sendKeys(password);
        repeatPasswordInput.sendKeys(password);
        securityQuestionDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(securityQuestionOption)).click();
        securityAnswerInput.sendKeys(answer);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }
}