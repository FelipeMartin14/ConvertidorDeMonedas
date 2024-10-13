package com.convertidor.principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject; 

public class ExchangeRateAPI {
    
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/307465bc6d710822419b0405/latest/";

    public double convert(String fromCurrency, String toCurrency, double amount) {
        double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
        return amount * exchangeRate;
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            
            URL url = new URL(API_URL + fromCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error en la conexión: " + conn.getResponseCode());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsear la respuesta JSON para obtener la tasa de cambio
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

            // Obtener la tasa de cambio 
            double exchangeRate = conversionRates.getDouble(toCurrency);
            return exchangeRate;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0; 
        }
    }
}