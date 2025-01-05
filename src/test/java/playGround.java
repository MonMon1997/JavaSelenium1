import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;



public class playGround {

    WebDriver driver;
    ExtentReports reports;
    ExtentSparkReporter spark;
    ExtentTest test ;
    Logger logger;

    public static class RetryFailure_AListener implements IRetryAnalyzer {


        private int retryCount = 0;
        private static final int maxRetryCount = 3; //the times want to rerun


        @Override
        public boolean retry(ITestResult result) {
            if (retryCount < maxRetryCount) {
                retryCount++;
                return true;
            }
            return false;
        }
    }


    //TestNg Before Test
    @BeforeTest (groups = {"UAT", "Regression"})
    public void set1(){
        //Extent Reports
        reports = new ExtentReports();
        spark = new ExtentSparkReporter("P6.html");
        reports.attachReporter(spark);

        test = reports.createTest("Selenium Walkthrough");

        //Log4J2
        logger = LogManager.getLogger(playGround.class);
        logger.info("User start the RUN case");

        //Set Up driver
        System.setProperty("chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.selenium.dev/");

    }

    //UAT Test 1 - Click Project Structure and Governance
    @Test (groups = {"UAT", "Regression"}, priority = 1, retryAnalyzer = RetryFailure_AListener.class)
    public void t1(){
        try {
            driver.findElement(By.id("navbarDropdown")).click();
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[1]/div/a[2]")).click();
            test.pass("Pass - Click Project Structure and Governance");
            logger.info("Finish UAT Test 1");
        } catch (Exception e){
          test.fail("Fail - Click Project Structure and Governance");
        }
    }

    //UAT Test 2 - Click Download Button
    @Test (groups = {"UAT", "Regression"}, priority = 2, retryAnalyzer = RetryFailure_AListener.class)
    public void t2(){
        try {
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[2]/a/span")).click();
            test.pass("Pass - Click Download Button");
            logger.info("Finish UAT Test 2");
        } catch (Exception e){
            test.fail("Fail - Click Download Button");
        }
    }

    //UAT Test 3 - Turn it into Japanese
    @Test (groups = {"UAT"}, priority = 3, retryAnalyzer = RetryFailure_AListener.class)
    public void t3(){
        try {
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[7]/div/a")).click();
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[7]/div/ul/li[3]/a")).click();
            test.pass("Pass - Turn it into Japanese");
            logger.info("Finish UAT Test 3");

            Assert.assertTrue(false);
        } catch (Exception e){
            test.fail("Fail - Turn it into Japanese");
        }
    }

    //UAT Test 4 - Turn it into Japanese
    @Test (groups = {"Regression"},priority = 4, retryAnalyzer = RetryFailure_AListener.class)
    public void t4(){
        try {
            driver.navigate().back();
            test.pass("Pass - Navigate Back");
            logger.info("Finish Test 4");
        } catch (Exception e){
            test.fail("Fail - Navigate Back");
        }
    }

    //UAT Test 5 - Click Document Button
    @Ignore
    @Test (groups = {"UAT, Regression"}, retryAnalyzer = RetryFailure_AListener.class)
    public void t5(){
        try {
            driver.findElement(By.xpath("/html/body/div/main/div[1]/div/div/a[1]"));
            test.pass("Pass - Click Document Button");
            logger.info("Finish Test 5");
        } catch (Exception e){
            test.fail("Fail - Click Document Button");
        }
    }

    //Flush and Open the report
    @AfterTest (groups = {"UAT, Regression"})
    public void set2(){

        //Open the ExtentsReport
        try {
            Desktop.getDesktop().browse(new File("P6.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("User END the case");
        reports.flush();
    }
}
