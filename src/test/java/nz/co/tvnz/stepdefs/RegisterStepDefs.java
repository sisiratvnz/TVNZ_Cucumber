package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tvnz.libraries.DriverFactory;
import nz.co.tvnz.libraries.ScenarioContext;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.pages.ElementMethods;
import nz.co.tvnz.utilities.HelperUtility;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RegisterStepDefs extends HelperUtility {
    static String registerEmail = null;
    static String registerPassword = null;

    ElementMethods elementMethods;

    public RegisterStepDefs(TestContext testContext) {
        super(testContext);
        elementMethods = new ElementMethods(testContext);
    }

    //Generate email address randomly
    public String randomAlphabeticString(int stringLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random =  new Random();
        return random.ints(leftLimit,rightLimit+1).limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

    //Generate birth year
    public String generateBirthYear(int min, int max){
        return String.valueOf((int)Math.floor(Math.random()*(max-min+1)+min));
    }

    //Generate random gender
    public String randomGender(){
        List<String> genders = Arrays.asList("Male","Female","Diverse");
        return genders.get((int)(Math.random() * genders.size()));
    }

    @When("I click on Sign up now")
    public void iClickOnSignUpNow() {
        registrationPageObjects.setSignUpNowClick();
    }

    @And("I fill registration details")
    public void iFillRegistrationDetails() {
        registerEmail = randomAlphabeticString(3)+"@grr.la";
        registerPassword = randomAlphabeticString(8);
        registrationPageObjects.email.sendKeys(registerEmail);
        registrationPageObjects.password.sendKeys(registerPassword);
        registrationPageObjects.firstName.sendKeys(randomAlphabeticString(3));
        registrationPageObjects.lastName.sendKeys(randomAlphabeticString(3));
        registrationPageObjects.yearOfBirthClick();
        elementMethods.setYearOfBirth(generateBirthYear(1923,2009));
        registrationPageObjects.genderClick();
        elementMethods.setGender(randomGender());

        getScenarioContext().setScenarioContext("email",registerEmail);
        getScenarioContext().setScenarioContext("password",registerPassword);
    }

    @And("I select House Rules check and Email Me about check boxes")
    public void iSelectHouseRulesCheckAndEmailMeAboutCheckBoxes() {
        registrationPageObjects.houseRulesClick();
        registrationPageObjects.latestNewsClick();
    }

    @And("I click SIGN ME UP button")
    public void iClickSIGNMEUPButton() {
        registrationPageObjects.signMeUpClick();
    }

    @Then("I can see new user get registered into TVNZ+")
    public void iCanSeeNewUserGetRegisteredIntoTVNZ() {
        registrationPageObjects.noThanksButtonClick();
        registrationPageObjects.skipForNowButtonClick();
        registrationPageObjects.backToTVNZClick();
        Assertions.assertTrue(registrationPageObjects.currentProfileNameOnHome.isDisplayed());
        System.out.println("Registration successful....");
    }
}
