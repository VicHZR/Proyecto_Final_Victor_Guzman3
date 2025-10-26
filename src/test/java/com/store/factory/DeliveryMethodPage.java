package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class DeliveryMethodPage {
    WebDriver driver;
    WebDriverWait wait;

    public DeliveryMethodPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void selectFirstDeliveryAndContinue() {
        // Opción 1 (tus selectores)
        By firstRadio = By.xpath("(//mat-row/mat-cell/mat-radio-button)[1]");
        wait.until(ExpectedConditions.elementToBeClickable(firstRadio)).click();

        // Continue (XPaths de tu doc)
        By continueBtn = By.xpath("/html/body/app-root/mat-sidenav-container/mat-sidenav-content/app-delivery-method/mat-card/div/div[4]/button[2]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        } catch (Exception e) {
            By contLabel = By.xpath("/html/body/app-root/mat-sidenav-container/mat-sidenav-content/app-delivery-method/mat-card/div/div[4]/button[2]/span[2]");
            wait.until(ExpectedConditions.elementToBeClickable(contLabel)).click();
        }
    }
}