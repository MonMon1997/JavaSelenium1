package HeadlessBrowser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class headlessBrowser {

    public static void main(String[] args) {

        //Define ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--desktop-window-1080p");

        Logger logger = LogManager.getLogger(headlessBrowser.class);
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("P6.html");
        extent.attachReporter(spark);

        ExtentTest test = extent.createTest("Fight Club");


            //Need to add in ChromeDriver
            System.setProperty("Chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
            WebDriver driver = new ChromeDriver(options);

            try {
            driver.navigate().to("https://www.google.com/");
            logger.info("User access the Google");
            test.pass("You are strong enough");

        } catch (Exception e){
            test.fail("You are not strong enough");
        }

        try{
            WebElement search = driver.findElement(By.name("q"));
            search.sendKeys("Tyler Durden");
            logger.info("User passing the text to the textarea");
            test.pass("Pass, You win the fight");

        } catch (Exception e){
            test.fail("Fail");
        }

        extent.flush();
    }
}
