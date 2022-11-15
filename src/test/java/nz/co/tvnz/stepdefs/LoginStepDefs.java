package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.configs.GlobalPropertyConfig;
import nz.co.tvnz.libraries.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LoginStepDefs extends DriverFactory {

    @Given("I navigate to login page")
    public void iNavigateToLoginPage() {
        if(loginPageObjects.loginLink.isDisplayed()){
            loginPageObjects.loginClick();
        }
    }

    @When("I enter user name {string}")
    public void iEnterUserName(String email) {
        //driver.findElement(By.id("email")).sendKeys(email);
        loginPageObjects.email.sendKeys(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        //driver.findElement(By.id("password")).sendKeys(password);
        loginPageObjects.password.sendKeys(password);
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
        //driver.findElement(By.xpath("//span[.='Login']/..")).click();
        loginPageObjects.submitButtonClick();
    }

    @Then("User should be login to tvnz app")
    public void userShouldBeLoginToTvnzApp() throws InterruptedException {
        loginPageObjects.mainProfileDisplayed();
        loginPageObjects.mainProfileClick();
        Thread.sleep(2000);
        if(showPageObjects.overlay.isDisplayed()) {
            moveToElement(showPageObjects.overlay);
            showPageObjects.overlayButtonClick();
        }
        String ActualTitle = getDriver().getTitle();
        //System.out.println(ActualTitle);
        String expectedTitle = "Watch Shows, Movies & Live TV | Stream On | TVNZ+";
        Assertions.assertEquals(expectedTitle,ActualTitle);
    }

    @And("username should display on home page")
    public void userNameShouldDisplayOnHomePage() {
        Assertions.assertTrue(loginPageObjects.currentProfileNameOnHome.isDisplayed());
        System.out.println("Login successful....");
    }
}
