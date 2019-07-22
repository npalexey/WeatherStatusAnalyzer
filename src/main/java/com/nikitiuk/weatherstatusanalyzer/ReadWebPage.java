package com.nikitiuk.weatherstatusanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ReadWebPage {
    private static Logger logger =  LogManager.getLogger(ReadWebPage.class);

    public static Map<String, Double> retrieveWeatherStatus(String urlString){
        Map<String, Double> weatherStatusValues = new HashMap<>();
        BufferedReader in = null;
        try {
            URL url = new URL(urlString);
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                matchComponentsOfWeatherStatus(inputLine, weatherStatusValues);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (weatherStatusValues.isEmpty()){
            logger.info("No weather status was retrieved, might be either a connection error, or a retrieving process error. Map is empty");
        }
        return weatherStatusValues;
    }

    private static void matchComponentsOfWeatherStatus(String inputLineFromSite, Map<String, Double> weatherStatusValues){
        if (!inputLineFromSite.matches("(\\s+)(Feels Like:|Barometer:|Dewpoint:|Humidity:|Visibility:|<span class=\"warm txt).*")) {
            return;
        }
        String formatted = inputLineFromSite
                .replaceAll("\\s+|<strong>|</strong>|<br />|;|</span>|&deg|°C|%|km|hPa|\\+", "")
                .replaceAll("<spanclass=\"warmtxt-xxlarge\">", "Temperature:");
        //logger.info(formatted);
        if(formatted.matches("(FeelsLike).*")){
            weatherStatusValues.put("Feels", Double.parseDouble(formatted.replaceAll("FeelsLike:", "")));
        } else if(formatted.matches("(Barometer).*")){
            weatherStatusValues.put("Barometer", Double.parseDouble(formatted.replaceAll("Barometer:", "")));
        } else if(formatted.matches("(Dewpoint).*")){
            weatherStatusValues.put("Dewpoint", Double.parseDouble(formatted.replaceAll("Dewpoint:", "")));
        } else if(formatted.matches("(Humidity).*")){
            weatherStatusValues.put("Humidity", Double.parseDouble(formatted.replaceAll("Humidity:", "")));
        } else if(formatted.matches("(Visibility).*")){
            weatherStatusValues.put("Visibility", Double.parseDouble(formatted.replaceAll("Visibility:", "")));
        } else if(formatted.matches("(Temperature).*")){
            weatherStatusValues.put("Temperature", Double.parseDouble(formatted.replaceAll("Temperature:", "")));
        }
    }
}