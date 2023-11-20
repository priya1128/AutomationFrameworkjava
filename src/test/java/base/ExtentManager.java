package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;
import java.io.IOException;

public class ExtentManager {

    private static ExtentReports extent;
    private static String reportFileName = "FAxO-Automation-Report: PSCR-QA"+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                createInstance();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() throws IOException {
        String fileName = getReportPath(reportFilepath);
        System.out.println("Report Location:" +fileName);

        System.getProperties().list(System.out);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        /*
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(reportFileName);
        sparkReporter.config().setCss(".badge {font-size:125%;font-weight:700}",);
        sparkReporter.config().setCss(".badge.log {font-size:100%; font-weight:700}");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(reportFileName);
        sparkReporter.config().setTimeStampFormat("dd-MM-YYYY hh:mm:ss");
        */

        sparkReporter.loadXMLConfig(new File("./src/test/java/resources/extent-reports-config.xml"));
        /*sparkReporter.viewConfigurer().viewOrder().as(new ViewName[] {
                ViewName.DASHBOARD,
                ViewName.CATEGORY,
                ViewName.EXCEPTION,
                ViewName.TEST,
                ViewName.DEVICE,
                ViewName.AUTHOR
        }).apply(); */

        extent = new ExtentReports();

        //Set environment details
        extent.setSystemInfo("OS" , System.getProperty("os.name"));
        extent.setSystemInfo("Environment" , "FAxO-QA");

        //sparkReporter.config().setCss(".badge.log {font-size:100%; font-weight:700}");
        extent.attachReporter(sparkReporter);

        return extent;
    }

    //Create the report path
    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }

}
