package com.store.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddressPage {
    WebDriver driver;
    WebDriverWait wait;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public static class AddressData {
        public final String country, name, mobile, zip, address, city, state;
        public AddressData(String country, String name, String mobile, String zip, String address, String city, String state) {
            this.country = country; this.name = name; this.mobile = mobile; this.zip = zip;
            this.address = address; this.city = city; this.state = state;
        }

        @Override
        public String toString() {
            return String.format("AddressData[country=%s, name=%s, mobile=%s, zip=%s, address=%s, city=%s, state=%s]",
                country, name, mobile, zip, address, city, state);
        }
    }

    // Navegación directa (como acordamos)
    public void openSavedAddresses() {
        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.xpath("//button[@aria-label='Show Orders and Payment Menu']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Go to saved address page']")).click();
    }
    public void openCreateAddress() {
        driver.findElement(By.xpath("//button[@aria-label ='Add a new address' ]")).click();
    }
    public void openSelectAddress() {
        driver.get("https://juice-shop.herokuapp.com/#/address/select");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-card")));
    }

    public int countSavedAddresses() {
        return driver.findElements(By.xpath("//mat-table/mat-row")).size();
    }

    public void clickAddNewAddressFromSelect() {
        // Botón Add New Address en /#/address/select (tus selectores)
        By addBtn = By.xpath("//button[@aria-label ='Add a new address' ]");
        By addBtnTouch = By.xpath("//*[@id='card']/app-address/mat-card/div/div/button/span[4]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtnTouch)).click();
        }
    }

    // Create address con tus IDs exactos
    public void fillAndSubmitAddress(AddressData d) {
        System.out.println("Rellenando dirección con: " +d.toString());

        type(By.xpath("//input[@placeholder='Please provide a country.']"), d.country);
        type(By.xpath("//input[@placeholder='Please provide a name.']"), d.name);
        type(By.xpath("//input[@placeholder='Please provide a mobile number.']"), d.mobile);
        type(By.xpath("//input[@placeholder='Please provide a ZIP code.']"), d.zip);
        type(By.id("address"), d.address);
        type(By.xpath("//input[@placeholder='Please provide a city.']"), d.city);
        type(By.xpath("//input[@placeholder='Please provide a state.']"), d.state);

        // Submit
        By submit1 = By.cssSelector("#submitButton > span.mdc-button__label");
        By submit2 = By.cssSelector("#submitButton");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submit1)).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(submit2)).click();
        }
        // Esperar redirección
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/address/select"),
            ExpectedConditions.urlContains("/address/saved")
        ));
    }

    public void selectSecondAddressAndContinue() {
        // Segundo radio button
        By secondRadio = By.xpath("(//mat-row/mat-cell/mat-radio-button)[2]");
        wait.until(ExpectedConditions.elementToBeClickable(secondRadio)).click();

        // Continue (varias variantes de tus selectores)
        By cont1 = By.xpath("//*[@id='card']/app-address/mat-card/div/button");
        By cont2 = By.xpath("//*[@id='card']/app-address/mat-card/div/button/span[2]");
        By cont3 = By.xpath("//*[@id='card']/app-address/mat-card/div/button/span[4]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cont1)).click();
        } catch (Exception e1) {
            try { wait.until(ExpectedConditions.elementToBeClickable(cont2)).click(); }
            catch (Exception e2) { wait.until(ExpectedConditions.elementToBeClickable(cont3)).click(); }
        }
    }

    private void type(By locator, String value) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(value);
    }
}