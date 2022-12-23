package nz.co.tvnz.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import nz.co.tvnz.configs.GlobalPropertyConfig;
import nz.co.tvnz.libraries.DriverFactory;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;

import java.util.Scanner;

public class Hooks extends HelperUtility {

    public static Scenario myScenario;

    public Hooks(TestContext testContext){
        super(testContext);
    }

    @Before
    public void driverSetup(Scenario scenario){
        System.out.println(System.getProperty("browser"));
        setUp(System.getProperty("browser")==null? GlobalPropertyConfig.getGlobalProperties().getProperty("1"):System.getProperty("browser"));
        getDriver().get(GlobalPropertyConfig.getURL()==null?"https://www.tvnz.co.nz":GlobalPropertyConfig.getURL());
        System.out.println("Site loading successful....");
        myScenario = scenario;
        //getScenarioContext().setScenarioContext("abc", 1233);
    }
    @After
    public void closeBrowser(Scenario scenario){
        if(scenario.isFailed()){
            getScreenShot();
        }
//        getScreenShot();
        tearDown();
    }
}
