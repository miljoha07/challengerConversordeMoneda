package com.mildreth.converter;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

// Clase que maneja la lógica de conversión de monedas y el historial de conversiones
public class ConversionService {
    // Lista para almacenar el historial de conversiones
    private List<String> conversionHistory;

    // Constructor de la clase, inicializa la lista de historial
    public ConversionService() {
        conversionHistory = new ArrayList<>(); // Crea una nueva ArrayList para almacenar conversiones
    }

    // Método para realizar la conversión entre dos monedas
    // Recibe el monto a convertir, la moneda de origen, la moneda de destino y las tasas de cambio
    public double convert(double amount, String fromCurrency, String toCurrency, JsonObject rates) {
        try {
            // Obtiene la tasa de la moneda de origen
            double fromRate = rates.get(fromCurrency).getAsDouble();
            // Obtiene la tasa de la moneda de destino
            double toRate = rates.get(toCurrency).getAsDouble();
            // Calcula el monto convertido utilizando la fórmula:
            // (monto / tasa de origen) * tasa de destino
            return (amount / fromRate) * toRate;
        } catch (Exception e) { // Captura cualquier excepción que ocurra
            System.out.println("Error en la conversión: " + e.getMessage()); // Muestra un mensaje de error
            return -1; // Devuelve -1 para indicar que la conversión falló
        }
    }

    // Método para añadir conversiones al historial
    // Recibe el monto original, la moneda de origen, el monto convertido y la moneda de destino
    public void addToHistory(double amount, String fromCurrency, double convertedAmount, String toCurrency) {
        // Formatea una cadena con la conversión realizada
        String entry = String.format("%.2f %s -> %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
        // Añade la entrada al historial de conversiones
        conversionHistory.add(entry);
    }

    // Método para mostrar el historial de conversiones
    public void showHistory() {
        // Verifica si el historial está vacío
        if (conversionHistory.isEmpty()) {
            System.out.println("No hay conversiones registradas."); // Mensaje informativo
        } else {
            // Imprime cada entrada del historial en la consola
            conversionHistory.forEach(System.out::println);
        }
    }
}

