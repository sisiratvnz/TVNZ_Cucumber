package nz.co.tvnz.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import nz.co.tvnz.pages.RegisterAndLoginPageObjects;
import nz.co.tvnz.pages.NewsPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    public static NewsPageObjects newsPageObjects;
    public static RegisterAndLoginPageObjects registerAndLoginPageObjects;
    @Before
    public void driverSetup(){
        driver = ChromeDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.tvnz.co.nz");
        driver.manage().window().maximize();
        System.out.println("Site loading successful....");
        newsPageObjects = PageFactory.initElements(driver, NewsPageObjects.class);
        registerAndLoginPageObjects = PageFactory.initElements(driver, RegisterAndLoginPageObjects.class);
    }
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
