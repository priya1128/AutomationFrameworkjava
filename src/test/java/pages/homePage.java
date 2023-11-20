package pages;

import base.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static base.Listener.log;

public class homePage {
    private WebDriverWait wait;

    public homePage(WebDriver driver) {
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10L), Duration.ofSeconds(50L));
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[contains(text(),'Health Monitor : ')]")
    private WebElement homeTitle;

    @FindBy(xpath="//div[@id='welcomeimage']")
    private WebElement imgWelcome;

/*
    public boolean isDisplayed1() {
        boolean isTrue = wait.until(ExpectedConditions.visibilityOf(imgWelcome)).isDisplayed();
        return isTrue;

    }

    public String getText() {
        String title = wait.until(ExpectedConditions.visibilityOf(homeTitle)).getText();
        return title;

    }

    public void isDisplayed() {
        boolean isTrue = wait.until(ExpectedConditions.visibilityOf(imgWelcome)).isDisplayed();
        return isTrue;
        if(imgWelcome!=null) {
            System.out.println("Object "+imgWelcome+"is not present");
            ExtentTestManager.getTest().log(Status.INFO, "Verify WelcomeImage");

        } else {
            ExtentTestManager.getTest().log(Status.FAIL, "WelcomeImage is shown");
        }

    }

    public void getText(String strExp_Res) {
        //String title = wait.until(ExpectedConditions.visibilityOf(homeTitle)).getText();
        //return title;
        wait.until(ExpectedConditions.visibilityOf(homeTitle));
        if (homeTitle != null) {
            String title = homeTitle.getText();
            Assert.assertTrue(title.contains(strExp_Res));
            ExtentTestManager.getTest().log(Status.INFO, "Verify Health Monitor text");
        } else {
            System.out.println("Object " + homeTitle + "is not present");
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");
        }
    } */

    public void healthMonitor_GetText(String strExp_Res){
        String res = GetText(homeTitle);
        Assert.assertTrue(res.contains(strExp_Res));
        ExtentTestManager.getTest().log(Status.PASS, "Verify Health Monitor text");

    }

    public void welImg_isPresent(){
        exists(imgWelcome);
        ExtentTestManager.getTest().log(Status.PASS, "Verify Welcome Image");

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
            String actualRes = homeTitle.getText();
            return actualRes;
            //Assert.assertTrue(title.contains(strExp_Res));
            //ExtentTestManager.getTest().log(Status.INFO, "Verify Health Monitor text");
        } else {
            System.out.println("Object " + homeTitle + "is not present");
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");
            return null;
        }
    }

}
