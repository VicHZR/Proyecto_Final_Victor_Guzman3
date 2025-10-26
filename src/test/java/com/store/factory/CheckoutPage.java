package com.store.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object para realizar el proceso de checkout completo.
 */
public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[aria-label='Show the shopping cart']")
    WebElement cartButton;

    @FindBy(id = "checkoutButton")
    WebElement checkoutButton;

    @FindBy(css = "#mat-radio-42-input")
    WebElement addressOption;

    @FindBy(css = "#card app-address mat-card div button span.mdc-button__label > span")
    WebElement addressContinueButton;

    @FindBy(css = "#mat-expansion-panel-header-0 span.mat-content mat-panel-description")
    WebElement paymentOption;

    @FindBy(css = "body app-root mat-sidenav-container mat-sidenav-content app-payment mat-card div div:nth-child(9) button.mdc-button span.mdc-button__label > span")
    WebElement paymentContinueButton;

    @FindBy(css = "body app-root mat-sidenav-container mat-sidenav-content app-payment mat-card div div:nth-child(9) button.mdc-button span.mdc-button__label > span")
    WebElement placeOrderButton;

    public void completeCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addressContinueButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(paymentOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(paymentContinueButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
}