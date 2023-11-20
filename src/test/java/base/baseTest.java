package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static javax.xml.datatype.DatatypeConstants.SECONDS;

public class baseTest {
    protected WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                //driver = new FirefoxDriver();
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
        }
    }

    private static WebDriver initChromeDriver() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://faxoqa.oci.oraclecorp.com:4443/PSCR_JET/?ojr=home");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        return driver;
    }


    @Parameters({"browserType"})
    @BeforeSuite
    public void initializeSetup(String browserType) {
        try {
            System.out.println(browserType);
            setDriver(browserType);
            System.out.println("setDriver completed");

        } catch (Exception e) {
                System.out.println("Error....." + e.getStackTrace());
            }
        }

   @AfterSuite
    public void afterSuite() {

        driver.quit();
    }

}
