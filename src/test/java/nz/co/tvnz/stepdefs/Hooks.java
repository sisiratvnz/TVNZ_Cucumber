package nz.co.tvnz.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import nz.co.tvnz.configs.GlobalPropertyConfig;
import nz.co.tvnz.libraries.DriverFactory;

public class Hooks extends DriverFactory {

    @Before
    public void driverSetup(){
        setUp(System.getProperty("browser")==null?GlobalPropertyConfig.getGlobalProperties().getProperty("1"):System.getProperty("browser"));
        getDriver().get(GlobalPropertyConfig.getURL()==null?"https://www.tvnz.co.nz":GlobalPropertyConfig.getURL());
        System.out.println("Site loading successful....");
    }
    @After
    public void closeBrowser(){
        tearDown();
    }
}
