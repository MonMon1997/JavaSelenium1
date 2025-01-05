package TestNGListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener{

    ExtentSparkReporter spark;
    ExtentReports reports;
    ExtentTest test;

    @Override
    public void onFinish(ITestContext contextFinish) {
        reports.flush();
    }

    @Override
    public void onStart(ITestContext contextStart) {
        spark = new ExtentSparkReporter("P6.html");
        reports = new ExtentReports();
        reports.attachReporter(spark);
        test = reports.createTest("MyFirstTest");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Handle partial failures if necessary
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Method failed"+ result.getName());
        test.fail("This test is fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Method skipped"+ result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Start test "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Method passed"+ result.getName());
        test.pass("This test is pass");

    }
}
