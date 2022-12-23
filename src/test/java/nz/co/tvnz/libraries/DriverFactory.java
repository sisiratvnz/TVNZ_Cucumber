package nz.co.tvnz.libraries;

import nz.co.tvnz.pages.*;
import nz.co.tvnz.stepdefs.Hooks;
import nz.co.tvnz.stepdefs.ShowStepDefs;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

public class DriverFactory {
    private static WebDriver driver;
    static protected LoginPageObjects loginPageObjects;
    static protected RegistrationPageObjects registrationPageObjects;
    static protected HomePageObjects homePageObjects;
    static protected ShowPageObjects showPageObjects;

    private static ScenarioContext scenarioContext;

    public ScenarioContext getScenarioContext(){
        return scenarioContext;
    }

    public DriverFactory(TestContext testContext) {
        if(scenarioContext==null) {
            scenarioContext = testContext.getScenarioContext();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void initiateDriver(String browser){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                fail("Unknown browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    private void initializePages(){
        loginPageObjects = PageFactory.initElements(getDriver(), LoginPageObjects.class);
        registrationPageObjects = PageFactory.initElements(getDriver(), RegistrationPageObjects.class);
        homePageObjects = PageFactory.initElements(getDriver(), HomePageObjects.class);
        showPageObjects = PageFactory.initElements(getDriver(), ShowPageObjects.class);
    }

    protected void setUp(String browser){
        initiateDriver(browser);
        initializePages();
    }
    protected void tearDown(){
        driver.close();
        driver.quit();
    }
}
