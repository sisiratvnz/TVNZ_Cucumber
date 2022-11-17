package nz.co.tvnz.pages;

import nz.co.tvnz.libraries.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageObjects extends DriverFactory {
    @FindBy(how = How.LINK_TEXT, using = "Login")
    public WebElement loginLink;
    public void loginClick(){
        loginLink.click();
    }
    @FindBy(how = How.ID, using = "email")
    public WebElement email;
    @FindBy(how = How.ID, using = "password")
    public WebElement password;
    @FindBy(how = How.XPATH, using = "//span[.='Login']/..")
    private WebElement submitButton;
    public void submitButtonClick(){
        submitButton.click();
    }
    @FindBy(how = How.XPATH, using = "(//div[@class='profile-avatar__image-container'])[1]")
    private WebElement mainProfileIcon;
    public void mainProfileDisplayed(){
        waitForElement();
        Assertions.assertTrue(mainProfileIcon.isDisplayed());
    }
    public void mainProfileClick(){
        mainProfileIcon.click();
    }

    @FindBy(how = How.XPATH, using = "//span[@class='User-current-profile-name']")
    public WebElement currentProfileNameOnHome;
}
