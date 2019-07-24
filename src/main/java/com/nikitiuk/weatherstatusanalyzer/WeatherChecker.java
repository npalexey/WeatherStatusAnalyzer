package com.nikitiuk.weatherstatusanalyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import static com.nikitiuk.weatherstatusanalyzer.ConditionAnalyzer.*;

public class WeatherChecker {
    private static final Logger logger =  LoggerFactory.getLogger(WebPageReader.class);

    public static void main(String[] args) {
        String urlText = "https://www.foreca.com/Ukraine/Odesa?quick_units=metric";
        Map<String, Double> weatherStatus = WebPageReader.retrieveWeatherStatus(urlText);
        //logger.info(weatherStatus.toString());
        checkConditionsForTodayAndDisplayTheBestOption(weatherStatus);
    }
}
