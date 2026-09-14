package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // This method runs before the TestNG execution starts
    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        String reportPath = System.getProperty("user.dir")
                + "\\reports\\" + repName;

        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System",
                System.getProperty("os.name"));
        extent.setSystemInfo("User Name",
                System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Veeranna");
    }

    // Test passed
    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.PASS, "Test Passed");
        test.log(Status.PASS, result.getName());
    }

    // Test failed
    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    // Test skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable());
    }

    // Runs after all tests are completed
    @Override
    public void onFinish(ITestContext testContext) {

        extent.flush();
    }
}