package nz.co.tvnz.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.pages.ElementMethods;
import nz.co.tvnz.utilities.HelperUtility;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;


import java.util.List;




public class LoginStepDefs extends HelperUtility {
    //ElementMethods elementMethods;
    private static final Logger logger = LogManager.getLogger(LoginStepDefs.class);

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
        String password1 = getScenarioContext().getScenarioContext("password") == null?password:getScenarioContext().getScenarioContext("password").toString();
        //System.out.println("password"+getScenarioContext().getScenarioContext("password"));
        /*
        have to pass the above password1 in order to get the login with same username and password
         */
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
//        if(showPageObjects.overlay.isDisplayed()) {
//            moveToElement(showPageObjects.overlay);
//            showPageObjects.overlayButtonClick();
//        }
        waitForPageLoad();
        String ActualTitle = getDriver().getTitle();
        //System.out.println(ActualTitle);
        //String expectedTitle = "Watch Shows, Movies & Live TV | Stream On | TVNZ+";
        //waitForPageLoad();
        //Assertions.assertEquals(expectedTitle,ActualTitle);
    }

    @And("username should display on home page")
    public void userNameShouldDisplayOnHomePage(DataTable dataTable) {
        List<List<String>> tableValues = dataTable.asLists();
        for (List<String> abc : tableValues ) {
            for (String ba : abc) {
                System.out.println(ba);
            }
        }
        Assertions.assertTrue(loginPageObjects.currentProfileNameOnHome.isDisplayed());
        System.out.println("Login successful....");
        logger.debug("test log message");
        logger.debug(loginPageObjects.currentProfileNameOnHome.getText());
    }
}
