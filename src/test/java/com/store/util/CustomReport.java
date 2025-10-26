package com.store.util;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;

/**
 * Plugin personalizado para Cucumber que imprime mensajes al inicio y fin de la ejecución.
 * Este plugin es solo un ejemplo básico y puede extenderse para generar reportes más avanzados.
 */
public class CustomReport implements Plugin, EventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, event -> {
            System.out.println("🚀 Inicio de ejecución de pruebas Cucumber.");
        });

        publisher.registerHandlerFor(TestRunFinished.class, event -> {
            System.out.println("✅ Fin de ejecución de pruebas Cucumber.");
        });
    }
}