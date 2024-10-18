# Conversor de Monedas.
Este proyecto es un conversor de monedas desarrollado en Java, utilizando la API de FastForex para obtener tasas de cambio. El conversor permite a los usuarios 
convertir montos entre diferentes monedas y ver el historial de conversiones.

## Estructura del Proyecto
El proyecto está dividido en varias clases para facilitar su mantenimiento y comprensión:
1. **CurrencyConverterApp**: Clase principal que gestiona la interacción con el usuario y coordina las otras clases.
2. **ApiClient**: Maneja la comunicación con la API de tasas de cambio.
3. **JsonParserUtil**: Analiza las respuestas JSON usando la biblioteca Gson.
4. **ConversionService**: Realiza la lógica de conversión y mantiene un historial de conversiones.
   

