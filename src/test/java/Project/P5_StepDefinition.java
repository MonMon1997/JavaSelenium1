package Project;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class P5_StepDefinition {

    private ExtentReports reports;
    private ExtentSparkReporter spark;
    private ExtentTest feature;
    private ExtentTest scenario;
    private Logger logger;
    private WebDriver driver;

    @Before
    public void setup(){
        //Log4j2
        logger = LogManager.getLogger(P5_GoogleSearchwithCucumber.class);

        System.setProperty("chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Given("User in the main page")
    public void userInTheMainPage() {
        //Extent Report
        reports = new ExtentReports();
        spark = new ExtentSparkReporter("P5.html");
        reports.attachReporter(spark);
        //ExtentTest test = reports.createTest("MyFirstTest").pass("Pass");
        //test.pass("TEST");
        feature = reports.createTest(Feature.class, "Google Search Fuck Around").assignAuthor("Edmond").assignDevice("Web");
        scenario = feature.createNode(Scenario.class, "Google Main Page PlayGround");
        scenario.createNode(com.aventstack.extentreports.gherkin.model.Given.class, "User in the main page").pass("pass");

        driver.navigate().to("https://www.google.com/");
        logger.trace("Now Access: "+driver.getCurrentUrl());
    }

    @When("User navigate to About Button")
    public void userNavigateToAboutButton() {
        //About Button
        WebElement aboutbutton = driver.findElement(By.xpath("//*[text()='About']"));
        aboutbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(com.aventstack.extentreports.gherkin.model.When.class, "User navigate to About Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

    }

    @And("User navigate to GoogleApp Button")
    public void userNavigateToGoogleAppButton() {
        //GoogleApp Button
        WebElement googleappsbutton = driver.findElement(By.xpath("//*[@class='gb_A'][@aria-label='Google apps']"));
        googleappsbutton.click();

        WebElement frame = driver.findElement(By.xpath("//iframe[@role ='presentation'][@name='app']"));
        driver.switchTo().frame(frame);

        WebElement bloggerbutton = driver.findElement(By.xpath("//*[@class='MrEfLc'][@style='background-position: 0 -2552px;']"));
        bloggerbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "User navigate to GoogleApp Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());
    }

    @And("User navigate to Recording Button")
    public void userNavigateToRecordingButton() {
        //Recording Button
        WebElement recordbutton = driver.findElement(By.xpath("//*[@class='goxjub'][@viewBox='0 0 24 24']"));
        recordbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "User navigate to Recording Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

    }

    @And("User navigate to Langaugebutton")
    public void userNavigateToLangaugebutton() {
        //Langaugebutton
        WebElement francebutton = driver.findElement(By.xpath("//*[text()='Français']"));
        francebutton.click();

        WebElement englishbutton = driver.findElement(By.xpath("//*[text()='English']"));
        englishbutton.click();

        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "User navigate to Langaugebutton").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

    }

    @And("User navigate to SettingButton")
    public void userNavigateToSettingButton() {
        //SettingButton
        WebElement settingbutton = driver.findElement(By.xpath("//*[@jsname='LgbsSe'][@class='ayzqOc pHiOh'][text()='Settings']"));
        settingbutton.click();

        scenario.createNode(com.aventstack.extentreports.gherkin.model.And.class, "User navigate to SettingButton").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

    }

    @Then("User turn mode")
    public void userTurnMode() {
        //DarkMode
        WebElement darktheme = driver.findElement(By.xpath("//*[text()='Dark theme: On']"));
        darktheme.click();

        scenario.createNode(com.aventstack.extentreports.gherkin.model.Then.class, "User turn mode").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        try {
            Desktop.getDesktop().browse(new File("P5.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reports.flush();
    }

    @After
    public void teardown() throws IOException {
        driver.close();
        driver.quit();
    }
}
