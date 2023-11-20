package Utilities;

import base.ExtentTestManager;
import base.baseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Objects_util {

    private WebDriverWait wait;
    private String stepDesc;

    public Objects_util(WebDriver driver) {

        //wait = new WebDriverWait(driver, Duration.ofSeconds(5L), Duration.ofSeconds(50L));
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);

    }


    public void editBox(WebElement objElement, String input) {
        wait.until(ExpectedConditions.visibilityOf(objElement));

        if(objElement!=null){
            objElement.sendKeys(input);

        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    public void click(WebElement objElement) {
        wait.until(ExpectedConditions.visibilityOf(objElement));

        if(objElement!=null){
            objElement.click();

        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    public void exists(WebElement objElement){
        wait.until(ExpectedConditions.visibilityOf(objElement));

        if(objElement!=null){
            objElement.isDisplayed();

        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }

    }

    public String GetText(WebElement objElement){
        wait.until(ExpectedConditions.visibilityOf(objElement));

        if (objElement != null) {
            String actualRes = objElement.getText();
            return actualRes;

        } else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");
            return null;
        }
    }

}


