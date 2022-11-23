package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.pages.ElementMethods;
import nz.co.tvnz.utilities.HelperUtility;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefs extends HelperUtility {
    //ElementMethods elementMethods;

    public LoginStepDefs(TestContext testContext) {
        super(testContext);
        //elementMethods = new ElementMethods(testContext);
    }

    @Given("I navigate to login page")
    public void iNavigateToLoginPage() {
        if(loginPageObjects.loginLink.isDisplayed()){
            loginPageObjects.loginClick();
        }
    }

    @When("I enter user name {string}")
    public void iEnterUserName(String email) {
        System.out.println("email"+ getScenarioContext().getScenarioContext("email"));
        loginPageObjects.email.sendKeys(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        System.out.println("password"+getScenarioContext().getScenarioContext("password"));
        loginPageObjects.password.sendKeys(password);
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
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
        waitForPageLoad();
        String ActualTitle = getDriver().getTitle();
        //System.out.println(ActualTitle);
        String expectedTitle = "Watch Shows, Movies & Live TV | Stream On | TVNZ+";
        waitForPageLoad();
        Assertions.assertEquals(expectedTitle,ActualTitle);
    }

    @And("username should display on home page")
    public void userNameShouldDisplayOnHomePage() {
        Assertions.assertTrue(loginPageObjects.currentProfileNameOnHome.isDisplayed());
        System.out.println("Login successful....");
    }
}
