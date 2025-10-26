package com.store.stepDefinitions;

import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import com.store.util.Base;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Step Definitions para el flujo de login.
 */
public class LoginSteps {
    Base base = new Base();
    LoginPage loginPage;
    HomePage homePage;

    @When("el usuario navega a la página de login")
    public void navegarLogin() {
        homePage = new HomePage(Base.returnDriver());
        homePage.goToLogin();
        loginPage = new LoginPage(Base.returnDriver());
    }

    @When("el usuario ingresa el correo {string} y la contraseña {string}")
    public void ingresarCredenciales(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("el usuario debería iniciar sesión exitosamente")
    public void validarLoginExitoso() {
        homePage.openAccountMenu();
        boolean isLoggedIn = Base.returnDriver()
                .findElements(By.cssSelector("button[aria-label='Logout']")).size() > 0;
        Assert.assertTrue(isLoggedIn, "❌ El usuario no ha iniciado sesión correctamente.");
    }

    @Then("el usuario debería ser registrado exitosamente")
    public void validarRegistroExitoso() {
        loginPage = new LoginPage(Base.returnDriver());
        boolean isLoginVisible = loginPage.isSuccessLogin();
        Assert.assertTrue(isLoginVisible, "❌ El usuario no fue redirigido correctamente después del registro.");
    }
}