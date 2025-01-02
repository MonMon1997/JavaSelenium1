package GoogleWalkthrough;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class googleSearch_Testng {

    WebDriver driver;
    ExtentSparkReporter spark;
    ExtentReports extent;
    ExtentTest test;
    ExtentTest scenario;
    WebDriverWait wait;

    @BeforeTest
    public void setup0(){
        spark = new ExtentSparkReporter("P6.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest(Feature.class, "Google Search");
        scenario = test.createNode(Scenario.class, "Walkthrough the whole google");
        //SET UP DRIVER
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void setup1(){
        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            scenario.createNode(Given.class, "SET UP DRIVER").pass("pass");
        } catch (Exception e){
            scenario.createNode(Given.class, "SET UP DRIVER").fail("fail");
        }
    }

    @Test
    public void setup2(){
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
    }

    @Test
    public void setup3(){
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
    }

    @Test
    public void setup4(){
        //WRITE CONTENT IN TEXTAREA AND DELETE IT
        try {
            driver.findElement(By.name("q")).sendKeys("Los Santos");
            driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q"))).sendKeys(Keys.ESCAPE);
            scenario.createNode(And.class, "WRITE CONTENT IN TEXTAREA AND DELETE IT").pass("pass");
        } catch (Exception e){
            scenario.createNode(And.class, "WRITE CONTENT IN TEXTAREA AND DELETE IT").fail("fail");
        }
    }

    @Test
    public void setup5(){
        //CHANGE LAN FROM ENGLISH > FRANCE
        try {
            driver.findElement(By.xpath("//a[text()='Français']")).click();
            scenario.createNode(Then.class, "CHANGE LAN FROM ENGLISH > FRANCE").pass("pass");
        } catch (Exception e){
            scenario.createNode(Then.class, "CHANGE LAN FROM ENGLISH > FRANCE").fail("fail");
        }
    }


    @AfterTest
    public void setup6(){
        extent.flush();
        driver.close();
        driver.quit();
    }

}
