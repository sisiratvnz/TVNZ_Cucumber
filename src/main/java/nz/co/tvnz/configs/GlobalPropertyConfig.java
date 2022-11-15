package nz.co.tvnz.configs;

import java.sql.DriverManager;
import java.util.Properties;

public class GlobalPropertyConfig {
    protected static String url = null;
    public static Properties getGlobalProperties() {
        return PropertiesHandler.loadProperties("src/main/resources/properties/global.properties");
    }
    public static String getURL(){
        switch (System.getProperty("env")==null?getGlobalProperties().getProperty("env"):System.getProperty("env")){
            case "prod":
                url = getGlobalProperties().getProperty("produrl");
                break;
            case "stage":
                url = getGlobalProperties().getProperty("stageurl");
                break;
            case "test":
                url = getGlobalProperties().getProperty("testurl");
                break;
            case "dev":
                url = getGlobalProperties().getProperty("devurl");
                break;
            case "other":
                url = getGlobalProperties().getProperty("otherurl");
                break;
            default:
                url = getGlobalProperties().getProperty("localhost");
        }
        return  url;
    }
}
