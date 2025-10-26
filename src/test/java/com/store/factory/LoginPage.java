package com.store.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "loginButton")
    WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(),'Registration completed successfully. You can now log in.')]")
    WebElement isSuccessMessage;

    public void login(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public boolean isSuccessLogin() {
        try {
            return isSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}