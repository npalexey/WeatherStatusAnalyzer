package com.nikitiuk.weatherstatusanalyzer;

import com.nikitiuk.booleanparsercontraption.service.ServiceStarter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;


public class WeatherChecker {
    private static Logger logger =  LogManager.getLogger(ReadWebPage.class);
    public static void main(String[] args) {
        String urlText = "https://www.foreca.com/Ukraine/Odesa";
        Map<String, Double> weatherStatus = ReadWebPage.retrieveWeatherStatus(urlText);
        logger.info(weatherStatus.toString());
        logger.info(ServiceStarter.parseBoolExpr("(Visibility > 5 && Feels > 25) && (Barometer < 1100 || Temperature > 25)", weatherStatus));
    }
}
