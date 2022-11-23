package nz.co.tvnz.pages;

import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;
import org.openqa.selenium.By;

public class ElementMethods extends HelperUtility {

    public ElementMethods(TestContext testContext) {
        super(testContext);
    }


    public void setYearOfBirth(String yearOfBirth){
        getDriver().findElement(By.xpath("//div[.='" + yearOfBirth+ "']")).click();
    }


    public void setGender(String rGender){
        waitForPageLoad();
        getDriver().findElement(By.xpath("//div[@id='gender']/div[.='" + rGender+ "']")).click();
    }
}
