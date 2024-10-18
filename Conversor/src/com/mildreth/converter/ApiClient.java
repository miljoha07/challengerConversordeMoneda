package com.mildreth.converter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

// Clase ApiClient que maneja la comunicación con la API de tasas de cambio
public class ApiClient {
    // URL de la API, contiene la clave de API para acceder a los datos de tasas de cambio
    private static final String API_URL = "https://api.fastforex.io/fetch-all?api_key=d865dfc38b-cf4a5a2dd8-slfd7t";

    // Cliente HTTP que se utilizará para realizar las solicitudes a la API
    private HttpClient client;

    // Constructor de la clase ApiClient
    // Se encarga de inicializar el cliente HTTP al crear una instancia de esta clase
    public ApiClient() {
        // Inicializa el cliente HTTP para realizar solicitudes
        client = HttpClient.newHttpClient();
    }

    // Método para realizar la solicitud a la API y devolver la respuesta como cadena
    public String fetchExchangeRates() {
        try {
            // Configurando la solicitud con HttpRequest
            // Se crea un nuevo objeto HttpRequest para especificar los detalles de la solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    // Establece la URI de la solicitud con la URL de la API
                    .uri(new URI(API_URL))
                    // Define que la solicitud será de tipo GET
                    .GET()
                    // Construye el objeto HttpRequest
                    .build();

            // Enviando la solicitud y manejando la respuesta con HttpResponse
            // Se envía la solicitud y se recibe la respuesta en forma de cadena
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificando el código de estado para asegurarnos de que la solicitud fue exitosa
            if (response.statusCode() == 200) { // Comprueba si el código de estado es 200 (OK)
                return response.body(); // Devuelve el cuerpo de la respuesta (contenido de la API)
            } else { // Si la solicitud no fue exitosa
                // Muestra un mensaje de error con el código de estado y el cuerpo de la respuesta
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
                return null; // Devuelve null indicando que no se pudo obtener la respuesta
            }
        } catch (IOException | InterruptedException e) { // Captura excepciones de IO y de interrupción
            // Imprime el seguimiento de la pila de la excepción
            e.printStackTrace();
            return null; // Devuelve null indicando un error
        } catch (Exception e) { // Captura cualquier otra excepción
            // Muestra un mensaje de error con la descripción del problema
            System.out.println("Error: " + e.getMessage());
            return null; // Devuelve null indicando un error
        }
    }
}
