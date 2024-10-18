package com.mildreth.converter; // Paquete que contiene las clases del conversor de moneda

import java.util.Scanner; // Importamos Scanner para leer la entrada del usuario
import com.google.gson.JsonObject; // Importamos JsonObject de Gson para manejar respuestas JSON

public class CurrencyConverterApp {
    public static void main(String[] args) {
        // Inicializamos las clases que gestionarán las diferentes tareas
        ApiClient apiClient = new ApiClient(); // Crea una instancia de ApiClient para manejar las solicitudes a la API
        JsonParserUtil jsonParser = new JsonParserUtil(); // Crea una instancia de JsonParserUtil para analizar JSON
        ConversionService conversionService = new ConversionService(); // Crea una instancia de ConversionService para realizar conversiones

        // Obtenemos las tasas de cambio desde la API
        String response = apiClient.fetchExchangeRates(); // Llama al método para obtener tasas de cambio
        if (response == null) { // Verificamos si la respuesta es nula (indica un error)
            System.out.println("Error fetching exchange rates. Exiting..."); // Mensaje de error
            return; // Salimos del programa si hubo un error
        }

        // Parseamos la respuesta para obtener las tasas de cambio
        JsonObject jsonObject = jsonParser.parseResponse(response); // Convierte la respuesta JSON en un objeto
        JsonObject rates = jsonParser.getExchangeRates(jsonObject); // Extrae las tasas de cambio del objeto JSON

        // Interfaz con el usuario
        Scanner scanner = new Scanner(System.in); // Inicializamos Scanner para leer la entrada del usuario
        System.out.println("Bienvenido al Conversor de Monedas!"); // Mensaje de bienvenida

        while (true) { // Bucle infinito para mostrar el menú hasta que el usuario decida salir
            // Presentamos opciones al usuario
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Convertir moneda"); // Opción para convertir moneda
            System.out.println("2. Mostrar historial de conversiones"); // Opción para mostrar historial
            System.out.println("3. Salir"); // Opción para salir
            int choice = scanner.nextInt(); // Leemos la opción elegida por el usuario

            switch (choice) { // Estructura de control para manejar la opción seleccionada
                case 1 -> { // Opción para convertir moneda
                    System.out.println("Ingrese la cantidad:"); // Solicita al usuario que ingrese la cantidad
                    double amount = scanner.nextDouble(); // Lee la cantidad ingresada
                    System.out.println("Ingrese la moneda de origen :"); // Solicita la moneda de origen
                    String fromCurrency = scanner.next().toUpperCase(); // Lee y convierte la moneda a mayúsculas
                    System.out.println("Ingrese la moneda de destino :"); // Solicita la moneda de destino
                    String toCurrency = scanner.next().toUpperCase(); // Lee y convierte la moneda a mayúsculas

                    // Realiza la conversión utilizando el servicio de conversión
                    double convertedAmount = conversionService.convert(amount, fromCurrency, toCurrency, rates);
                    if (convertedAmount != -1) { // Verifica si la conversión fue exitosa
                        // Muestra el monto convertido
                        System.out.printf("Monto convertido: %.2f %s\n", convertedAmount, toCurrency);
                        // Agrega la conversión al historial
                        conversionService.addToHistory(amount, fromCurrency, convertedAmount, toCurrency);
                    } else { // Si la conversión falla
                        System.out.println("No se pudo realizar la conversión. Verifique las monedas ingresadas."); // Mensaje de error
                    }
                }
                case 2 -> { // Opción para mostrar historial de conversiones
                    System.out.println("Historial de conversiones:"); // Mensaje
                    conversionService.showHistory(); // Llama al método para mostrar el historial
                }
                case 3 -> { // Opción para salir
                    System.out.println("Gracias por preferirnos."); // Mensaje de despedida
                    scanner.close(); // Cierra el escáner
                    return; // Sale del programa
                }
                default -> System.out.println("Opción no válida. Intente de nuevo."); // Mensaje para opción no válida
            }
        }
    }
}

