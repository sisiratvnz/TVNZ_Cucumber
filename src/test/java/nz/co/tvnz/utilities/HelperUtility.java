package nz.co.tvnz.utilities;

import nz.co.tvnz.configs.GlobalPropertyConfig;
import nz.co.tvnz.dbconnection.ConnectionManager;
import nz.co.tvnz.libraries.DriverFactory;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.stepdefs.Hooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

public class HelperUtility extends DriverFactory {

    static Logger logger = LogManager.getLogger(HelperUtility.class);
    public HelperUtility(TestContext testContext) {
        super(testContext);
    }

    public static void waitForPageLoad(){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void waitForElementToClick(WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitInVisibilityOfElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void moveToElement(WebElement element){
        Actions builder = new Actions(getDriver());
        builder.moveToElement(element).build().perform();
    }

    public void navigateToURL(String url){
        getDriver().navigate().to(url);
    }
    public void moveBackPage(){
        getDriver().navigate().back();
    }
    public void moveForwardPage(){
        getDriver().navigate().forward();
    }

    protected static void getScreenShot(){
        try {
            waitForPageLoad();
            byte[] screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
            Hooks.myScenario.attach(screenshot,"image/png",Hooks.myScenario.getName());
        }catch (WebDriverException DontSupportScreenShot){
            System.err.println(DontSupportScreenShot.getMessage());
        }
    }

    public void mouseHoverJScript(WebElement HoverElement) {
        try {
            if (isElementPresent(HoverElement)) {
                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) getDriver()).executeScript(mouseOverScript,
                    HoverElement);
        } else {
            System.out.println("Element was not visible to hover " + "\n");
        }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + HoverElement
                    + "is not attached to the page document"
                    + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + HoverElement + " was not found in DOM"
                    + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while hovering"
                    + Arrays.toString(e.getStackTrace()));
        }
    }

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            flag = false;
        }
        return flag;
    }

    public Connection connection;
    public Connection setConnection(){
        switch (GlobalPropertyConfig.getGlobalProperties().getProperty("database")){
            case "postgresql":
                String userName = GlobalPropertyConfig.getGlobalProperties().getProperty("dbuser");
                String password = GlobalPropertyConfig.getGlobalProperties().getProperty("dbpass");
                String url = GlobalPropertyConfig.getGlobalProperties().getProperty("dburl");
                try {
                    connection = DriverManager.getConnection(url, userName, password);
                    if (connection != null) {
                        logger.debug("Connected to the database!");
                    } else {
                        logger.debug("Failed to make connection...");
                    }
                }
                catch (SQLException e){
                    logger.debug(e.getMessage());
                }
                catch (Exception e){
                    logger.debug(e.getStackTrace());
                }
                break;
            case "mysql":
                String mUser = GlobalPropertyConfig.getGlobalProperties().getProperty("mdbuser");
                String mPassword = GlobalPropertyConfig.getGlobalProperties().getProperty("mdbpass");
                String mUrl = GlobalPropertyConfig.getGlobalProperties().getProperty("mdburl");
                try {
                    connection = DriverManager.getConnection(mUrl, mUser, mPassword);
                    if (connection != null) {
                        logger.debug("Connected to the database!");
                    } else {
                        logger.debug("Failed to make connection...");
                    }
                }
                catch (SQLException e){
                    logger.debug(e.getMessage());
                }
                catch (Exception e){
                    logger.debug(e.getStackTrace());
                }
            break;
            default:
                fail("Unknown database");
        }
        return connection;
    }

    public void closeConnection() throws SQLException{
        try {
            connection.close();
        }
        catch (SQLException e){
            logger.debug(e.getMessage());
        }
        catch (Exception e){
            logger.debug(e.getStackTrace());
        }
    }
}
