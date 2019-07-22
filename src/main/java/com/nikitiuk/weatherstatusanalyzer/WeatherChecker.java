package com.nikitiuk.weatherstatusanalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import static com.nikitiuk.weatherstatusanalyzer.ConditionAnalyzer.*;

public class WeatherChecker {
    private static Logger logger =  LogManager.getLogger(WebPageReader.class);

    public static void main(String[] args) {
        String urlText = "https://www.foreca.com/Ukraine/Odesa";
        Map<String, Double> weatherStatus = WebPageReader.retrieveWeatherStatus(urlText);
        //logger.info(weatherStatus.toString());
        checkConditionsForTodayAndDisplayTheBestOption(weatherStatus);
    }
}
