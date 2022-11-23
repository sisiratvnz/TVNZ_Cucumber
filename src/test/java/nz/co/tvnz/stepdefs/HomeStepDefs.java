package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.DriverFactory;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;

public class HomeStepDefs extends HelperUtility {

    public HomeStepDefs(TestContext testContext) {
        super(testContext);
    }

    @Given("I login into TVNZ+ successfully")
    public void iLoginIntoTVNZSuccessfully() throws InterruptedException {
        if(loginPageObjects.loginLink.isDisplayed()){
            loginPageObjects.loginClick();
            loginPageObjects.email.sendKeys("k10@grr.la");
            loginPageObjects.password.sendKeys("11111111");
            loginPageObjects.submitButtonClick();
            loginPageObjects.mainProfileClick();
            Thread.sleep(2000);
            if(showPageObjects.overlay.isDisplayed()) {
                moveToElement(showPageObjects.overlay);
                showPageObjects.overlayButtonClick();
            }
        }
    }
    @Then("I can see Top Picks belt")
    public void iCanSeeTopPicksBelt() {
        waitForPageLoad();
        homePageObjects.findAndPrintTopPicks();
    }

}
