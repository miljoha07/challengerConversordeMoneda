package com.mildreth.converter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

// Clase JsonParserUtil que se encarga de analizar respuestas JSON usando la biblioteca Gson
public class JsonParserUtil {
    // Instancia de Gson para realizar el análisis de JSON
    private Gson gson;

    // Constructor de la clase JsonParserUtil
    // Se encarga de inicializar la instancia de Gson al crear una nueva instancia de esta clase
    public JsonParserUtil() {
        gson = new Gson(); // Inicializa el objeto Gson para el análisis de JSON
    }

    // Método para parsear la respuesta JSON y devolver un JsonObject
    // Recibe una cadena JSON y la convierte en un objeto JsonObject
    public JsonObject parseResponse(String jsonResponse) {
        // Convierte la cadena JSON a un JsonObject usando la instancia de Gson
        return gson.fromJson(jsonResponse, JsonObject.class);
    }

    // Método para extraer las tasas de cambio de la respuesta JSON
    // Recibe un JsonObject y devuelve otro JsonObject que contiene solo las tasas de cambio
    public JsonObject getExchangeRates(JsonObject jsonObject) {
        // Extrae el objeto "results" que contiene las tasas de cambio del JsonObject proporcionado
        return jsonObject.getAsJsonObject("results");
    }
}
