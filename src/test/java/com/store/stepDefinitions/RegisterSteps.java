package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.factory.RegisterPage;
import com.store.util.Base;
import io.cucumber.java.en.*;

public class RegisterSteps {

    Base base = new Base();
    HomePage homePage;
    RegisterPage registerPage;

    @Given("el usuario abre la aplicación para registrarse")
    public void abrirAplicacionRegistro() {
        base.loadDriver();
        homePage = new HomePage(Base.returnDriver());
        homePage.closeWelcomeBannerIfPresent();
    }

    @When("el usuario navega a la página de registro")
    public void navegarRegistro() {
        homePage.goToRegister();
        registerPage = new RegisterPage(Base.returnDriver());
    }

    @When("el usuario ingresa el correo {string}, la contraseña {string} y la respuesta {string}")
    public void ingresarDatosRegistro(String email, String password, String respuesta) {
        registerPage.register(email, password, respuesta);
    }
}