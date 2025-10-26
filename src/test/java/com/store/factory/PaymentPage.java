package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public static class CardData {
        public final String name, number, month, year;
        public CardData(String name, String number, String month, String year) {
            this.name = name; this.number = number; this.month = month; this.year = year;
        }
    }

    public void openSavedPaymentMethods() {
        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.xpath("//button[@aria-label='Show Orders and Payment Menu']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Go to saved payment methods page']")).click();
    }
    public void openPaymentShop() {
        driver.get("https://juice-shop.herokuapp.com/#/payment/shop");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-card")));
    }

    public int countSavedCards() {
        return driver.findElements(By.xpath("//mat-table/mat-row")).size();
    }

    public void expandAddNewCardForm() {
        // Header del panel: tu doc pone ...-header-1; dejo fallback -0
        By headerTitle   = By.cssSelector("#mat-expansion-panel-header-1 > span.mat-content > mat-panel-title, #mat-expansion-panel-header-0 > span.mat-content > mat-panel-title");
        By headerDesc    = By.cssSelector("#mat-expansion-panel-header-1 > span.mat-content > mat-panel-description, #mat-expansion-panel-header-0 > span.mat-content > mat-panel-description");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(headerTitle)).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(headerDesc)).click();
        }
    }

    public void addNewCard(CardData data) {
       // Inputs según tu doc
        type(By.xpath("//input[@type='text' and @aria-required='true']"), data.name);
        type(By.xpath("//input[@type='number']"), data.number);

        // Mes (select nativo)
        List<WebElement> selectElements = driver.findElements(By.tagName("select"));

        Select select = new Select(selectElements.get(0)); // Primer select para mes
        select.selectByVisibleText(data.month);

        select = new Select(selectElements.get(1)); // Segundo select para año
        select.selectByVisibleText(data.year);

        // Submit
        By submit = By.cssSelector("#submitButton > span.mdc-button__label, #submitButton");
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();

        // Espera a que regrese al listado/estado estable
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/payment/shop"),
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-table"))
        ));
    }

    public void selectFirstCardAndContinue() {
        // Primer método por primera fila de la tabla
        By firstCard = By.xpath("(//mat-row/mat-cell/mat-radio-button)[1]");
        wait.until(ExpectedConditions.elementToBeClickable(firstCard)).click();

        // Continue (por clase que usas en tu doc)
        By continueBtn = By.cssSelector("app-payment .btn.nextButton, app-payment .btn.nextButton span.mdc-button__label");
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void placeOrderAndPay() {
        // Order Summary: botón Place your order and pay (tus selectores)
        By place1 = By.cssSelector("#checkoutButton > span.mdc-button__label");
        By place2 = By.cssSelector("#checkoutButton > span.mat-mdc-button-touch-target");
        By place3 = By.cssSelector("#checkoutButton");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(place1)).click();
        } catch (Exception e1) {
            try { wait.until(ExpectedConditions.elementToBeClickable(place2)).click(); }
            catch (Exception e2) { wait.until(ExpectedConditions.elementToBeClickable(place3)).click(); }
        }
    }

    private void type(By locator, String value) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(value);
    }
}