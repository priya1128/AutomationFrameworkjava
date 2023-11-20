package pages;

import Utilities.Objects_util;
import base.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class loginPage {
    private WebDriverWait wait;
    private String stepDesc;

    private WebDriver driver;
    Objects_util RO;

    public loginPage(WebDriver driver) {

        //wait = new WebDriverWait(driver, Duration.ofSeconds(5L), Duration.ofSeconds(50L));
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);

        RO = new Objects_util(driver);
    }

    @FindBy(id="idcs-signin-basic-signin-form-username")
    public WebElement userName;

    @FindBy(xpath="//span[contains(text(),'Next')]")
    private WebElement btnNext;

    @FindBy(xpath="//input[@id='idcs-mfa-mfa-auth-user-password-text-input|input']")
    private WebElement password;

    @FindBy(xpath="//span[contains(text(),'Verify')]")
    private WebElement btnVerify;

/*
    public void doLogin1(String strUsername, String strPassword) {
        userName.sendKeys(strUsername);
        btnNext.click();
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(strPassword);
        btnVerify.click();

    }

    public void doLogin(String strUsername, String strPassword) {
        userName.sendKeys(strUsername);
        btnNext.click();
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(strPassword);
        btnVerify.click();

    }

 */
    //stepDesc = "Enter UserName";
    public void enterUserId(String strUserId) {
        RO.editBox(userName,strUserId);
        ExtentTestManager.getTest().log(Status.PASS, "Enter UserName");

    }

    //stepDesc = "Click Next button";
    public void clkNxt() {
        RO.click(btnNext);
        ExtentTestManager.getTest().log(Status.PASS, "Click Verify button");

    }

    //stepDesc = "Enter Password";
    public void enterPwd(String strPwd) {
        RO.editBox(password,strPwd);
        ExtentTestManager.getTest().log(Status.PASS, "Enter Password");

    }

    //stepDesc = "Click Verify button";
    public void clkVry() {
        RO.click(btnVerify);
        ExtentTestManager.getTest().log(Status.PASS, "Click Next button");

    }

/*
    public void enterUsername(String strUsername) {
        if(userName!=null){
            userName.sendKeys(strUsername);
            ExtentTestManager.getTest().log(Status.PASS, "Enter UserName");
        }
        else {
            System.out.println("Object "+userName+"is not present");
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    public void enterPassword(String strPassword) {
        if(userName!=null){
            password.sendKeys(strPassword);
            ExtentTestManager.getTest().log(Status.PASS, "Enter Password");

        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    public void clickNext() {
        if(btnNext!=null){
            btnNext.click();
            ExtentTestManager.getTest().log(Status.PASS, "Click Next button");
        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");
        }
    }

    public void clickVerify() {
        if(btnVerify!=null){
            btnVerify.click();
            ExtentTestManager.getTest().log(Status.PASS, "Click Verify button");
        }
        else {
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");
        }
    }

 */

    /*
    public void editBox(WebElement objElement, String input) {
        wait.until(ExpectedConditions.visibilityOf(objElement));
        if(objElement!=null){
            objElement.sendKeys(input);
            //ExtentTestManager.getTest().log(Status.PASS, "Enter UserName");
        }
        else {
            //System.out.println("Object "+objElement+"is not present");
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    public void click(WebElement objElement) {
        wait.until(ExpectedConditions.visibilityOf(objElement));
        if(objElement!=null){
            objElement.click();
            //ExtentTestManager.getTest().log(Status.PASS, "Enter UserName");
        }
        else {
            //System.out.println("Object "+objElement+"is not present");
            ExtentTestManager.getTest().log(Status.FAIL, "Object not present");

        }
    }

    */

}
