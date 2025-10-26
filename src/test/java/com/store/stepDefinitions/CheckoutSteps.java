package com.store.stepDefinitions;

import com.github.javafaker.Faker;
import com.store.factory.*;
import com.store.util.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CheckoutSteps {

    Base base = new Base();
    HomePage homePage;
    ProductCatalogPage catalog;
    BasketPage basketPage;
    AddressPage addressPage;
    DeliveryMethodPage deliveryPage;
    PaymentPage paymentPage;
    OrderHistoryPage orderHistoryPage;

    private Faker faker = new Faker();

    // ---------- Helpers ----------
    private WebDriver driver() { return Base.returnDriver(); }
    // ⬇️ Renombrado para evitar conflicto con Object.wait()
    private WebDriverWait wdWait() { return new WebDriverWait(driver(), Duration.ofSeconds(20)); }
    private JavascriptExecutor js() { return (JavascriptExecutor) driver(); }

    private void initPages() {
        homePage         = new HomePage(driver());
        catalog          = new ProductCatalogPage(driver());
        basketPage       = new BasketPage(driver());
        addressPage      = new AddressPage(driver());
        deliveryPage     = new DeliveryMethodPage(driver());
        paymentPage      = new PaymentPage(driver());
        orderHistoryPage = new OrderHistoryPage(driver());
    }
    private void waitForPageReady() {
        wdWait().until(d -> "complete".equals(js().executeScript("return document.readyState")));
    }
    private void closeWelcomeIfAny() {
        try {
            wdWait().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Close Welcome Banner']"))).click();
        } catch (Exception ignore) {}
    }
    private void closeCookieIfAny() {
        try {
            wdWait().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='dismiss cookie message']"))).click();
        } catch (Exception ignore) {}
    }
    private void closeOverlays() {
        closeWelcomeIfAny();
        closeCookieIfAny();
    }

    // ---------- Background ----------
    @Given("que el usuario ha iniciado sesión en su cuenta")
    public void validarSesionActiva() {
        initPages();
        System.out.println("✅ Sesión iniciada correctamente.");
    }

    // ---------- SETUP: 2 direcciones y 2 tarjetas ----------
    @When("el usuario asegura que existen dos direcciones de envío")
    public void asegurarDosDirecciones() {
        initPages();

        addressPage.openSavedAddresses();

        //entrada de formulario
        int actuales = addressPage.countSavedAddresses();
        System.out.println("Direcciones actuales: " + actuales);

        while (actuales != 2) {
            AddressPage.AddressData addr = new AddressPage.AddressData(
                    faker.country().name(), faker.name().fullName(), faker.numerify("#########"), faker.numerify("########"), faker.address().streetAddress(), faker.address().city(), faker.address().state()
            );
            addressPage.openCreateAddress();
            addressPage.fillAndSubmitAddress(addr);
            addressPage.openSavedAddresses();
            actuales = addressPage.countSavedAddresses();
            wdWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mdc-card")));
            System.out.println("Direcciones actuales: " + actuales);
        }
    }

    @When("el usuario asegura que existen dos métodos de pago")
    public void asegurarDosMetodosPago() throws InterruptedException {
        initPages();

        paymentPage.openSavedPaymentMethods();

        int actuales = paymentPage.countSavedCards();
        System.out.println("Opciones de pago actuales: " + actuales);
        if (actuales == 2) return;

        if (actuales > 2) {
            System.out.println("⚠️ Hay más de dos métodos de pago guardados. No se eliminarán.");
            return;
        }

        paymentPage.expandAddNewCardForm();
        while (actuales != 2) {
            PaymentPage.CardData card = new PaymentPage.CardData(faker.name().fullName(),faker.numerify("################"), String.valueOf(faker.number().numberBetween(1,12)), String.valueOf(faker.number().numberBetween(2080, 2099)));
            paymentPage.addNewCard(card);
            Thread.sleep(1000); // Pequeña espera para evitar problemas de timing
            actuales = paymentPage.countSavedCards();
            System.out.println("Opciones de pago actuales: " + actuales);
        }
    }

    @Then("el sistema deja preparado el entorno de compra")
    public void entornoPreparado() {
        Assert.assertTrue(addressPage.countSavedAddresses() >= 2, "❌ No hay al menos dos direcciones.");
        Assert.assertTrue(paymentPage.countSavedCards() >= 2, "❌ No hay al menos dos métodos de pago.");
    }

 // ... dentro de CheckoutSteps.java

    @When("el usuario agrega los productos {string}, {string} y {string} al carrito")
    public void agregarProductosPedido1(String p1, String p2, String p3) {
        initPages();
        catalog.setItemsPerPageTo36();

        List<String> productsToAdd = List.of(p1, p2, p3);
        List<Integer> productsIndex = catalog.getProductsIndex(productsToAdd);

        for (Integer index : productsIndex) {
            catalog.addToBasketByTileIndex(index);
        }
    }

    @When("el usuario agrega los productos {string} y {string} al carrito")
    public void agregarProductosPedido2(String p1, String p2) {
        initPages();
        catalog.setItemsPerPageTo36();
        List<String> productsToAdd = List.of(p1, p2);
        List<Integer> productsIndex = catalog.getProductsIndex(productsToAdd);

        for (Integer index : productsIndex) {
            catalog.addToBasketByTileIndex(index);
        }
    }

    // ---------- Carrito ----------
    @When("el usuario accede al carrito")
    public void accederAlCarrito() {
        initPages();
        basketPage.openCart();
    }

    // ---------- Checkout ----------
    @When("el usuario realiza el proceso de checkout usando la segunda dirección y el primer método de pago")
    public void realizarCheckout() {
        initPages();

        basketPage.clickCheckout();

        // Dirección: segunda
        addressPage.selectSecondAddressAndContinue();

        // Delivery: opción 1
        deliveryPage.selectFirstDeliveryAndContinue();

        // Pago: primer método
        paymentPage.selectFirstCardAndContinue();

        // Confirmar y pagar
        paymentPage.placeOrderAndPay();
    }

    @Then("el sistema debe mostrar una confirmación de pedido")
    public void confirmarPedido() {
        wdWait().until(ExpectedConditions.urlContains("order-completion"));
        Assert.assertTrue(driver().getCurrentUrl().contains("order-completion"),
                "❌ No se mostró la confirmación de pedido.");
    }

    // ---------- Historial ----------
    @When("el usuario accede al historial de órdenes")
    public void accederHistorial() {
        initPages();
        homePage.openAccountMenu();
        // Orders & Payment
        wdWait().until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#mat-menu-panel-0 > div > button:nth-child(2) > span > span"))).click();
        // Order History
        wdWait().until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#mat-menu-panel-3 > div > button:nth-child(1) > span > span"))).click();
    }

    @Then("el sistema debe mostrar las órdenes realizadas")
    public void validarHistorial() {
        wdWait().until(ExpectedConditions.urlContains("order-history"));
        Assert.assertTrue(orderHistoryPage.countOrders() >= 2,
                "❌ No se encontraron al menos dos órdenes en el historial.");
    }
}
