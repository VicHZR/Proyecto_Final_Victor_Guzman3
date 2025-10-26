package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.util.Base;
import io.cucumber.java.en.Given;

/**
 * Step común para abrir la aplicación.
 */
public class CommonSteps {
    Base base = new Base();
    HomePage homePage;

    @Given("el usuario abre la aplicación")
    public void abrirAplicacion() {
        base.loadDriver();
        homePage = new HomePage(Base.returnDriver());
        homePage.closeWelcomeBannerIfPresent();
    }
}