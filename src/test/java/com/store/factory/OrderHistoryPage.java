package com.store.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OrderHistoryPage {
    WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int countOrders() {
        List<WebElement> orderCards = driver.findElements(By.xpath("//*[text()='Order ID']"));
        return orderCards.size();
    }
}