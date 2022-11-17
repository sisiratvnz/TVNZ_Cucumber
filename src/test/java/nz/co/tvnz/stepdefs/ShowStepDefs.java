package nz.co.tvnz.stepdefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.interactions.Actions;

public class ShowStepDefs extends DriverFactory {

//    @Given("I click on a show {string}")
//    public void iClickOnAShowTile(String showName) {
//        showPageObjects.showClick(showName);
//    }


    @Given("I click on a show tile")
    public void iClickOnAShowTile() {
        showPageObjects.topPickFirstShowTileClick();
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
