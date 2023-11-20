package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class demo {

        public static void main(String[] args) throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://faxoqa.oci.oraclecorp.com:4443/PSCR_JET/?ojr=home");
            Thread.sleep(5000);
            driver.findElement(By.id("idcs-signin-basic-signin-form-username")).sendKeys("shunmugapriya.balasubramaniya@oracle.com");
            driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@id='idcs-mfa-mfa-auth-user-password-text-input|input']")).sendKeys("Vijayruchi@654");
            driver.findElement(By.xpath("//span[contains(text(),'Verify')]")).click();

           /* @FindBy(xpath="//span[contains(text(),'Verify')]")
            private WebElement btnVerify;


            public void doLogin(String strUsername, String strPassword) {
                System.out.println("loginPage doLogin...");
                userName.sendKeys(strUsername);
                signIn.click();
                password.sendKeys(strPassword);
                btnVerify.click();
            }
        */
            driver.quit();
        }

}
