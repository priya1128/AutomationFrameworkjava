package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener implements ITestListener {
    public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Listener.class);

    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest("<b>" +result.getMethod().getMethodName() + "</b>", result.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        //ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName()+" Test passed");
        ExtentTestManager.getTest().log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));

    }

    public void onTestFailure(ITestResult result) {

        System.out.println("onTestFailure");

        log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        log.info((result.getMethod().getMethodName() + " failed!"));
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("myDriver");


        String targetLocation = null;

        //String testClassName = getTestClassName(result.getInstanceName()).trim();
        //by Priya
        String testClassName = result.getInstanceName().trim();
        System.out.println("testClassName: "+testClassName);

        //String timeStamp = Util.getCurrentTimeStamp(); // get timestamp

        //by Priya
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        String formattedTimestamp = dateFormat.format(currentDate);

        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + formattedTimestamp + ".png";
        String fileSeperator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
                + "screenshots";
        log.info("Screen shots reports path - " + reportsPath);
        System.out.println("reportsPath: "+reportsPath);
        try {
            File file = new File(reportsPath + fileSeperator + testClassName); // Set
            // screenshots
            // folder
            if (!file.exists()) {
                if (file.mkdirs()) {
                    log.info("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    log.info("Failed to create directory: " + file.getAbsolutePath());
                }

            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
            // location
            System.out.println("targetLocation: "+targetLocation);
            File targetFile = new File(targetLocation);
            log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
            log.info("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
            log.info("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            log.info("An exception occurred while taking screenshot " + e.getCause());
        }

        // attach screenshots to report
        System.out.println("attach screenshots to report targetLocation: "+targetLocation);

        assert targetLocation != null;
        //ExtentTestManager.getTest().log(Status.FAIL, "Test Failed").addScreenCaptureFromBase64String(targetLocation,testClassName);

        ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String("/Users/shubalas/Downloads/WhatsApp Image 2023-11-12 at 1.47.11 PM.png").build());

        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+" Test Case FAILED", ExtentColor.RED));


    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        //ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        ExtentTestManager.getTest().log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName()+" Test Case SKIPPED", ExtentColor.ORANGE));

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }


}
