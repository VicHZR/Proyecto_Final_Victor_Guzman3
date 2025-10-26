package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalogPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductCatalogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void goToSearch() {
        driver.get("https://juice-shop.herokuapp.com/#/search");
    }

    public void setItemsPerPageTo36() {
        By select = By.xpath("//mat-select/..");
        By option36 = By.xpath("//mat-option[3]");
        wait.until(ExpectedConditions.elementToBeClickable(select)).click();
        wait.until(ExpectedConditions.elementToBeClickable(option36)).click();
    }

    /** Add to basket usando el XPath absoluto que nos pasaste */
    public void addToBasketByTileIndex(int tileIndex) {
        String xpBtn = String.format("/html/body/app-root/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/div[2]/mat-grid-list/div/mat-grid-tile[%d]/div/mat-card/div/div[2]/button", tileIndex);
        // Algunos casos requieren hacer clic en <span[2]/span> — añadimos fallback
        String xpLabel = xpBtn + "/span[2]/span";
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpLabel))).click();
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpBtn))).click();
        }
    }

    private List<String> getProductNamesOnPage() {
        By productNameElements = By.xpath("//mat-grid-list/*/*/*/*/*/*/div/*[1]");
        List<WebElement> elements = driver.findElements(productNameElements);
        return elements.stream().map(WebElement::getText).toList();
    }

    public List<Integer> getProductsIndex(List<String> productsToAdd) {
        List<String> allProducts = getProductNamesOnPage();
        List<Integer> productIndices = new ArrayList<>();

        for (String s : productsToAdd) {
            for (int i = 0; i < allProducts.size(); i++) {
                String text = allProducts.get(i);
                if (text == null) continue;
                if (text.trim().toLowerCase().contains(s.trim().toLowerCase())) {
                    productIndices.add(i + 1);
                }
            }
        }
        if (productIndices.size() != productsToAdd.size()) {
            throw new NoSuchElementException("❌ No se encontraron productos coincidentes para: " + productsToAdd);
        }
        return productIndices;
    }

    // Helpers específicos para tu flujo
    public void addApple()  { addToBasketByTileIndex(2); }   // Manzana
    public void addBanana() { addToBasketByTileIndex(3); }
    public void addTShirt() { addToBasketByTileIndex(25); }
    public void addFruitPress() { addToBasketByTileIndex(7); }
    public void addOrangeJuice(){ addToBasketByTileIndex(31); }
}
