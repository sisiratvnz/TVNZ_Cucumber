package nz.co.tvnz.stepdefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.interactions.Actions;

public class ShowStepDefs extends DriverFactory {
//    @Given("User login to tvnz application")
//    public void UserLoginToTVNZApplication(){
//        if(loginPageObjects.loginLink.isDisplayed()){
//            loginPageObjects.loginClick();
//            loginPageObjects.email.sendKeys("k10@grr.la");
//            loginPageObjects.password.sendKeys("11111111");
//            loginPageObjects.submitButtonClick();
//            loginPageObjects.mainProfileClick();
//            if(showPageObjects.overlay.isDisplayed()) {
//                Actions builder = new Actions(getDriver());
//                builder.moveToElement(showPageObjects.overlay).build().perform();
//                showPageObjects.overlayButtonClick();
//            }
//        }
//
//    }

    @Given("I click on a show {string}")
    public void iClickOnAShowTile(String showName) {
        showPageObjects.showClick(showName);
    }

    @Then("I can see show page loading")
    public void iCanSeeShowPageLoading() {
        Assertions.assertTrue(showPageObjects.showHeroImage.isDisplayed());
        showPageObjects.episodeTabClick();
    }

    @When("I click on play episode Smart Watch button")
    public void iClickOnPlayEpisodeSmartWatchButton() {
        showPageObjects.playVideoSmartWatchButtonClick();
    }

    @Then("I can see episode playing")
    public void iCanSeeEpisodePlaying() {
        moveToElement(showPageObjects.playerPauseButton);
        waitForElement();
        Assertions.assertTrue(showPageObjects.playerPauseButton.isDisplayed());
        System.out.println("Video playback successful....");
    }
}
