package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class BasketPage {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openCart() {
        // Tus variantes de "Your Basket" en navbar
        By basketBtn1 = By.xpath("/html/body/app-root/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[4]");
        By basketBtn2 = By.xpath("/html/body/app-root/mat-sidenav-container/mat-sidenav-content/app-navbar/mat-toolbar/mat-toolbar-row/button[4]/span[2]/span[1]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(basketBtn1)).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(basketBtn2)).click();
        }
        wait.until(ExpectedConditions.urlContains("/basket"));
    }

    public void clickCheckout() {
        // Tus variantes del botón checkout
        By chk1 = By.cssSelector("#checkoutButton > span.mdc-button__label");
        By chk2 = By.cssSelector("#checkoutButton > span.mat-mdc-button-touch-target");
        By chk3 = By.cssSelector("#checkoutButton");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(chk1)).click();
        } catch (Exception e1) {
            try { wait.until(ExpectedConditions.elementToBeClickable(chk2)).click(); }
            catch (Exception e2) { wait.until(ExpectedConditions.elementToBeClickable(chk3)).click(); }
        }
    }
}