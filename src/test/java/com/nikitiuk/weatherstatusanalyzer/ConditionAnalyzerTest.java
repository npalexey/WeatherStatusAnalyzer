package com.nikitiuk.weatherstatusanalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ConditionAnalyzerTest {
    private static Logger logger = null;

    @BeforeAll
    public static void setLogger() throws Exception
    {
        logger = LogManager.getLogger(ConditionAnalyzer.class);
    }
    @Test
    public void compareCurrentWeatherWithTemplatesTest() {
        Map<String, Double> weatherStatus = new HashMap<>();

        weatherStatus.put("Temperature", -12.0);
        weatherStatus.put("Visibility", 0.4);
        weatherStatus.put("Dewpoint", -4.0);
        weatherStatus.put("Barometer", 1015.0);
        weatherStatus.put("Feels", -15.0);
        weatherStatus.put("Humidity", 33.0);
        assertEquals(ConditionOption.SNOWBLIND, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Temperature", -44.0);
        assertEquals(ConditionOption.STAYATHOME, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Temperature", 25.0);
        weatherStatus.replace("Barometer", 1070.0);
        assertEquals(ConditionOption.HEADACHEFORSURE, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Barometer", 1025.0);
        weatherStatus.replace("Dewpoint", 24.0);
        assertEquals(ConditionOption.YOUAREGOINGTOGETWET, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Dewpoint", 0.0);
        weatherStatus.replace("Humidity", 30.0);
        assertEquals(ConditionOption.DOSOMEFISHING, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Humidity", 33.0);
        weatherStatus.replace("Feels", 25.0);
        assertEquals(ConditionOption.GOODFORAWALK, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));

        weatherStatus.replace("Feels", 30.0);
        assertEquals(ConditionOption.ITSSIMPLYOK, ConditionAnalyzer.compareCurrentWeatherWithTemplates(weatherStatus));
    }
}