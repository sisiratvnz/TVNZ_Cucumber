package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs{
    WebDriver driver;

    @Given("I navigate to login page")
    public void iNavigateToLoginPage() {
        driver = Hooks.driver;
        //driver.findElement(By.linkText("Login")).click();
        Hooks.registerAndLoginPageObjects.loginClick();

    }

    @When("I enter user name {string}")
    public void iEnterUserName(String email) {
        //driver.findElement(By.id("email")).sendKeys(email);
        Hooks.registerAndLoginPageObjects.email.sendKeys(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        //driver.findElement(By.id("password")).sendKeys(password);
        Hooks.registerAndLoginPageObjects.password.sendKeys(password);
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
        //driver.findElement(By.xpath("//span[.='Login']/..")).click();
        Hooks.registerAndLoginPageObjects.submitButtonClick();
    }

    @Then("User should be login to tvnz app")
    public void userShouldBeLoginToTvnzApp() {
        Hooks.registerAndLoginPageObjects.mainProfileDisplayed();
        Hooks.registerAndLoginPageObjects.mainProfileClick();
    }

    @And("username should display on home page")
    public void userNameShouldDisplayOnHomePage() {
        Hooks.registerAndLoginPageObjects.currentProfileNameDisplayed();
    }
}
