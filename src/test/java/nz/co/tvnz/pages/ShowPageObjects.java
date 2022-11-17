package nz.co.tvnz.pages;

import nz.co.tvnz.libraries.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShowPageObjects extends DriverFactory{
//    @FindBy(how = How.ID, using = "Bel-Air")
//    public WebElement show;
    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'QuickInfo-overview')])[1]")
    private WebElement topPickFirstShowTile;
    public void topPickFirstShowTileClick(){
        moveToElement(topPickFirstShowTile);
        waitForElementToClick(topPickFirstShowTile);
        topPickFirstShowTile.click();
    }
    public void showClick(String showName){
        WebElement show = getDriver().findElement(By.id(showName));
        moveToElement(show);
        waitForElementToClick(show);
        show.click();
    }

    @FindBy(how = How.XPATH, using = "//div[@role='dialog']")
    public WebElement overlay;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='OK, got it']")
    public WebElement overlayButton;
    public void overlayButtonClick(){
        overlayButton.click();
    }

    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Episodes']")
    private WebElement episodeTab;
    public void episodeTabClick(){
        waitForElementToClick(episodeTab);
        episodeTab.click();
    }
    @FindBy(how = How.XPATH, using = "//a[@class='Button--scod Button Button--primary ember-view']")
    private WebElement playVideoSmartWatchButton;
    public void playVideoSmartWatchButtonClick(){
        waitForElementToClick(playVideoSmartWatchButton);
        playVideoSmartWatchButton.click();
    }

    @FindBy(how = How.XPATH, using = "//button[@title='Pause']")
    public WebElement playerPauseButton;

    @FindBy(how = How.XPATH, using = " //div[@class='Hero-image']")
    public WebElement showHeroImage;

}
