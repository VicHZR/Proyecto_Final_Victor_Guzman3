package com.store.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Base {
    // ✅ ThreadLocal para manejar WebDriver en ejecución paralela
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // ✅ Objeto para leer el archivo de configuración
    public static Properties prop = new Properties();

    // ✅ Tiempos de espera configurables
    public int TIME_OUT;
    public int TIME_EXPECTED_CONDITIONS;
    public boolean HEADLESS;
    public boolean iSCI;

    // ✅ Constructor: carga el archivo config.properties y tiempos de espera
    public Base() {
        iSCI = System.getenv("CI") != null && System.getenv("CI").equalsIgnoreCase("true");
        String configPath = "./src/test/resources/config/";
        if (iSCI) {
            configPath += "config.ci.properties";
        } else {
            configPath += "config.properties";
        }

        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);

            // ✅ Leer tiempos desde config.properties
            TIME_OUT = Integer.parseInt(prop.getProperty("implicit.wait", "10"));
            TIME_EXPECTED_CONDITIONS = Integer.parseInt(prop.getProperty("explicit.wait", "15"));
            HEADLESS = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ No se pudo cargar el archivo config.properties");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error al leer tiempos de espera: " + e.getMessage());
            TIME_OUT = 10;
            TIME_EXPECTED_CONDITIONS = 15;
            HEADLESS = false;
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        if (HEADLESS) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--force-device-scale-factor=1");
        } else {
            options.addArguments("--start-maximized");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        return options;
    }

    // Base.java
    public WebDriver loadDriver() {
        // ✅ Si ya hay driver en este hilo, no crear otro (evita abrir más ventanas)
        if (returnDriver() != null) {
            return returnDriver();
        }

        String browser = prop.getProperty("browser", "chrome");

        switch (browser) {
            case "chrome":
                ChromeOptions options = getChromeOptions();

                // Crear el driver
                driver.set(new ChromeDriver(options));

                // Headless moderno ignora setSize(), pero lo dejamos por si se ejecuta en modo normal
                if (!HEADLESS) {
                    driver.get().manage().window().maximize();
                }
                break;
            case "firefox":
                FirefoxOptions firefoxOptionss = new FirefoxOptions();
                firefoxOptionss.addArguments("-private");
                if (HEADLESS) {
                    firefoxOptionss.addArguments("-headless");
                }
                driver.set(new FirefoxDriver(firefoxOptionss));
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            default:
                System.out.println("❌ Navegador no soportado en config.properties: " + browser);
                throw new RuntimeException("Navegador no válido en config.properties");
        }

        if (returnDriver() == null) {
            throw new RuntimeException("El WebDriver no fue inicializado correctamente.");
        }

        // Configuración inicial (solo una vez)
        returnDriver().manage().deleteAllCookies();
        returnDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
        returnDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_EXPECTED_CONDITIONS));

        // Navegar a la URL base solo al crear el driver
        String baseUrl = prop.getProperty("base.url");
        System.out.println("🌐 Navegando a la URL base: " + baseUrl);
        returnDriver().get(baseUrl);
        return returnDriver();
    }

    // ✅ Devuelve el WebDriver del hilo actual
    public static synchronized WebDriver returnDriver() {
        return driver.get();
    }

    // ✅ Cierra y elimina el WebDriver del hilo actual
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}