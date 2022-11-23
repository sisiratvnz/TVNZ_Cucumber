package nz.co.tvnz.pages;

import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPageObjects{

    //Registration
    @FindBy(how = How.ID, using = "email")
    public WebElement email;
    @FindBy(how = How.ID, using = "password")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//span[text()='Sign up now']")
    private WebElement signUpNowLink;

    public void setSignUpNowClick(){
        signUpNowLink.click();
    }

    @FindBy(how = How.ID, using = "firstName")
    public WebElement firstName;
    @FindBy(how = How.ID, using = "lastName")
    public WebElement lastName;

    @FindBy(how = How.XPATH, using = "//div[@name='yearOfBirth']")
    private WebElement yearOfBirthListBox;
    public void yearOfBirthClick(){
        yearOfBirthListBox.click();
    }

//    public void setYearOfBirth(String yearOfBirth){
//        getDriver().findElement(By.xpath("//div[.='" + yearOfBirth+ "']")).click();
//    }

    @FindBy(how = How.XPATH, using = "//div[@name='gender']")
    private WebElement gender;
    public void genderClick(){
        gender.click();
    }

//    public void setGender(String rGender){
//        waitForPageLoad();
//        getDriver().findElement(By.xpath("//div[@id='gender']/div[.='" + rGender+ "']")).click();
//    }

    @FindBy(how = How.XPATH, using = "//label[@for='houseRules']//span")
    private WebElement houseRules;
    public void houseRulesClick(){
        houseRules.click();
    }
    @FindBy(how = How.XPATH, using = "//label[@for='latestNews']/div/div")
    private WebElement latestNews;
    public void latestNewsClick(){
        latestNews.click();
    }

    @FindBy(how = How.XPATH, using = "//button[.='Sign Me Up']")
    private WebElement signMeUp;
    public void signMeUpClick(){
        signMeUp.click();
    }

    @FindBy(how = How.XPATH, using = "//button[.='No thanks']")
    private WebElement noThanksButton;
    public void noThanksButtonClick(){
        noThanksButton.click();
    }

    @FindBy(how = How.XPATH, using = "//button[@class='scod-button--primary']")
    private WebElement skipForNowButton;
    public void skipForNowButtonClick(){
        skipForNowButton.click();
    }

    @FindBy(how = How.XPATH, using = "//button[.='Back to TVNZ']")
    private WebElement backToTVNZ;
    public void backToTVNZClick(){
        backToTVNZ.click();
    }

    @FindBy(how = How.XPATH, using = "//span[@class='User-current-profile-name']")
    public WebElement currentProfileNameOnHome;

}
