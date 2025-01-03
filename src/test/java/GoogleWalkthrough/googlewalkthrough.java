package GoogleWalkthrough;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class googlewalkthrough {
    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver;

        //Properties file
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\GoogleWalkthrough\\config.properties");
        properties.load(inputStream);
        String browser = properties.getProperty("browser");
        String path = properties.getProperty("path");
        System.out.println(browser + "\n"+ path);

        //Log4j2
        Logger logger = LogManager.getLogger(googlewalkthrough.class);
        logger.info("User is in prepare");

        //trace message cannot appear
        logger.info("This is a info message");
        logger.error("This is a error message");
        logger.warn("This is a warn message");
        logger.fatal("This is a warn message");
        System.out.println("Complete");

        //EXTENTSREPORTS SETUP
            ExtentSparkReporter spark = new ExtentSparkReporter("P6.html");
            ExtentReports extent = new ExtentReports();
            extent.attachReporter(spark);
            ExtentTest test = extent.createTest(Feature.class, "Google Search");
            ExtentTest scenario = test.createNode(Scenario.class, "Walkthrough the whole google");
            //SET UP DRIVER
            System.setProperty(browser, path);
            driver = new ChromeDriver();


        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com/");
            scenario.createNode(Given.class, "SET UP DRIVER").pass("pass");
        } catch (Exception e){
            scenario.createNode(Given.class, "SET UP DRIVER").fail("fail");
        }

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        //Click I'm feeling Good button and Return
        try {
                WebElement open = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[2]")));
                open.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@title, 'Google doodles')]")));
                driver.navigate().back();
                driver.switchTo().defaultContent();
                scenario.createNode(When.class, "User Click I'm feeling Good button and Return").pass("pass");
        }catch (Exception e){
                scenario.createNode(When.class, "User Click I'm feeling Good button and Return").fail("fail");
        }

        //Dismiss the Iframe
        try {
            WebElement call = driver.findElement(By.name("callout"));
            driver.switchTo().frame(call);
            WebElement signOutButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Stay signed out')]"));
            signOutButton.click();
            driver.switchTo().defaultContent();
            scenario.createNode(And.class, "Dismiss the Iframe").pass("pass");
        } catch (Exception e){
            scenario.createNode(And.class, "Dismiss the Iframe").fail("fail");
        }

        //WRITE CONTENT IN TEXTAREA AND DELETE IT
        try {
            driver.findElement(By.name("q")).sendKeys("Los Santos");
            driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q"))).sendKeys(Keys.ESCAPE);
            scenario.createNode(And.class, "WRITE CONTENT IN TEXTAREA AND DELETE IT").pass("pass");
        } catch (Exception e){
            scenario.createNode(And.class, "WRITE CONTENT IN TEXTAREA AND DELETE IT").fail("fail");
        }

        //CHANGE LAN FROM ENGLISH > FRANCE
       try {
           driver.findElement(By.xpath("//a[text()='Français']")).click();
           scenario.createNode(Then.class, "CHANGE LAN FROM ENGLISH > FRANCE").pass("pass");
       } catch (Exception e){
           scenario.createNode(Then.class, "CHANGE LAN FROM ENGLISH > FRANCE").fail("fail");
       }

       extent.flush();
       driver.close();
       driver.quit();
    }
}

//        try {
//            WebElement dialog = driver.findElement(By.name("callout"));
//            driver.switchTo().frame(dialog);
//            driver.findElement(By.xpath("//button[contains(@aria-label,'Stay signed out')]")).click();
//        } catch (Exception e){
//            System.out.println("No dialog is out");
//        }
//
//        //find button
//        WebElement pathElement = driver.findElement(By.className("DoEL0b"));
//
//        //use mouse action to click svg
//        Actions actions = new Actions(driver);
//        actions.moveToElement(pathElement).click().perform();
