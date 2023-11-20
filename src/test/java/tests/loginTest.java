package tests;

import base.ExtentTestManager;
import base.baseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.homePage;
import pages.loginPage;


public class loginTest extends baseTest {
    //private WebDriver driver;
    private loginPage loginP;
    private homePage homeP;


    @BeforeClass
    public void beforeTest(ITestContext context) {
        driver = getDriver();
        context.setAttribute("myDriver", driver);

    }

    @Test(description = "Login to the FAxO Application")
    public void doLogin() {
        loginP = new loginPage(driver);
        //loginP.doLogin("shunmugapriya.balasubramaniya@oracle.com", "Vijayruchi@654");
        loginP.enterUserId("shunmugapriya.balasubramaniya@oracle.com");
        loginP.clkNxt();
        loginP.enterPwd("Vijayruchi@654");
        loginP.clkVry();

    }
   /*
    @Test(description = "Verify the display of Welcome Image in Home Page")
    public void verifyWelcomeImage() {
        homeP = new homePage(driver);
        boolean boo_ActWelImg = homeP.isDisplayed();
        System.out.println("Wel_Image: " + boo_ActWelImg);
        Assert.assertTrue(boo_ActWelImg, "Welcome Image is displayed");
    }

    @Test(description = "Verify the Health Monitor text in Home Page")
    public void verifyHealthMonitor() {
        homeP = new homePage(driver);

        String str_HealthMonitor = homeP.getText();
        System.out.println("HealthMonitor: "+str_HealthMonitor);
        Assert.assertTrue(str_HealthMonitor.contains("Preflight"));
    }
    */

    @Test(description = "Verify the display of Welcome Image in Home Page")
    public void verifyWelcomeImage() {
        homeP = new homePage(driver);
        homeP.welImg_isPresent();
    }

    @Test(description = "Verify the Health Monitor text in Home Page")
    public void verifyHealthMonitor() {
        homeP = new homePage(driver);
        homeP.healthMonitor_GetText("Preflight");
    }

}
