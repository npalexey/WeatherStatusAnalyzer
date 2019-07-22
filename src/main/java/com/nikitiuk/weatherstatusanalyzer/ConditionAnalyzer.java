package com.nikitiuk.weatherstatusanalyzer;

import com.nikitiuk.booleanparsercontraption.service.ServiceStarter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public class ConditionAnalyzer {
    private static Logger logger =  LogManager.getLogger(ReadWebPage.class);

    public static void checkConditionsForTodayAndDisplayTheBestOption(Map<String, Double> weatherStatus){
        //logger.info(ServiceStarter.parseBoolExpr("(Visibility > 5 && Feels > 25) && (Barometer < 1100 || Temperature > 25)", weatherStatus));
        switch (compareCurrentWeatherWithTemplates(weatherStatus)){
            case STAYATHOME: logger.info("Better Stay At Home, Seems Horrible Outside"); break;
            case HEADACHEFORSURE: logger.info("Your Head Is Going To Feel Really Bad"); break;
            case SNOWBLIND: logger.info("You Won't See Anything, Better Use Public Transport"); break;
            case YOUAREGOINGTOGETWET: logger.info("It's Raining, Man"); break;
            case DOSOMEFISHING: logger.info("That's Some Good Opportunity For Fishing"); break;
            case GOODFORAWALK: logger.info("Go For A Walk, It's Nice Outside"); break;
            case ITSSIMPLYOK: logger.info("The Weather Is Simply Ok"); break;
        }
    }

    public static ConditionOption compareCurrentWeatherWithTemplates(Map<String, Double> weatherStatus){
        if(ServiceStarter.parseBoolExpr("Temperature > 35 || Temperature < -20", weatherStatus)){
            return ConditionOption.STAYATHOME;
        } else if (ServiceStarter.parseBoolExpr("Barometer > 1040 || Barometer < 975", weatherStatus)){
            return ConditionOption.HEADACHEFORSURE;
        } else if (ServiceStarter.parseBoolExpr("Visibility < 1 && Temperature < -5", weatherStatus)){
            return ConditionOption.SNOWBLIND;
        } else if (ServiceStarter.parseBoolExpr("(Dewpoint + 3) >= Temperature", weatherStatus)){
            return ConditionOption.YOUAREGOINGTOGETWET;
        } else if (ServiceStarter.parseBoolExpr("(Temperature > 20 && (Dewpoint + 10) < Temperature) && (Humidity <= 30 && Barometer <= 1025)", weatherStatus)){
            return ConditionOption.DOSOMEFISHING;
        } else if (ServiceStarter.parseBoolExpr("(Feels > 20 && Feels < 27) && Humidity < 37", weatherStatus)){
            return ConditionOption.GOODFORAWALK;
        } else {
            return ConditionOption.ITSSIMPLYOK;
        }
    }
}
