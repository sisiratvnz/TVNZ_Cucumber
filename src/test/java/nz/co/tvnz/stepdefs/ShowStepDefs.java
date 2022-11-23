package nz.co.tvnz.stepdefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;
import org.junit.jupiter.api.Assertions;

public class ShowStepDefs extends HelperUtility {
    public ShowStepDefs(TestContext testContext) {
        super(testContext);
    }

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
        waitForElementToClick(showPageObjects.episodeTab);
        showPageObjects.episodeTab.click();
    }

    @When("I click on play episode Smart Watch button")
    public void iClickOnPlayEpisodeSmartWatchButton() {
        waitForElementToClick(showPageObjects.playVideoSmartWatchButton);
        showPageObjects.playVideoSmartWatchButton.click();
    }

    @Then("I can see episode playing")
    public void iCanSeeEpisodePlaying() {
        moveToElement(showPageObjects.playerPauseButton);
        waitForPageLoad();
        Assertions.assertTrue(showPageObjects.playerPauseButton.isDisplayed());
        System.out.println("Video playback successful....");
    }

}
