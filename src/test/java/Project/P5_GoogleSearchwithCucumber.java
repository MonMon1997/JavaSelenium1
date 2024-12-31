package Project;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;

public class P5_GoogleSearchwithCucumber {

    public static void main(String[] args) {

        //Extent Report
        ExtentReports reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("P5.html");
        reports.attachReporter(spark);
        //ExtentTest test = reports.createTest("MyFirstTest").pass("Pass");
        //test.pass("TEST");
        ExtentTest feature = reports.createTest(Feature.class, "Google Search Fuck Around").assignAuthor("Edmond");
        ExtentTest scenario = feature.createNode(Scenario.class, "Google Main Page PlayGround");
        scenario.createNode(Given.class, "User in the main page").pass("pass");

        //Log4j2
        Logger logger = LogManager.getLogger(P5_GoogleSearchwithCucumber.class);

        System.setProperty("chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.navigate().to("https://www.google.com/");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //About Button
        WebElement aboutbutton = driver.findElement(By.xpath("//*[text()='About']"));
        aboutbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(When.class, "User navigate to About Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //GoogleApp Button
        WebElement googleappsbutton = driver.findElement(By.xpath("//*[@class='gb_A'][@aria-label='Google apps']"));
        googleappsbutton.click();

        WebElement frame = driver.findElement(By.xpath("//iframe[@role ='presentation'][@name='app']"));
        driver.switchTo().frame(frame);

        WebElement bloggerbutton = driver.findElement(By.xpath("//*[@class='MrEfLc'][@style='background-position: 0 -2552px;']"));
        bloggerbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(And.class, "User navigate to GoogleApp Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //Recording Button
        WebElement recordbutton = driver.findElement(By.xpath("//*[@class='goxjub'][@viewBox='0 0 24 24']"));
        recordbutton.click();

        driver.navigate().to("https://www.google.com/");
        scenario.createNode(And.class, "User navigate to Recording Button").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //Langaugebutton
        WebElement francebutton = driver.findElement(By.xpath("//*[text()='Français']"));
        francebutton.click();

        WebElement englishbutton = driver.findElement(By.xpath("//*[text()='English']"));
        englishbutton.click();

        scenario.createNode(And.class, "User navigate to Langaugebutton").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //SettingButton
        WebElement settingbutton = driver.findElement(By.xpath("//*[@jsname='LgbsSe'][@class='ayzqOc pHiOh'][text()='Settings']"));
        settingbutton.click();

        scenario.createNode(And.class, "User navigate to SettingButton").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        //DarkMode
        WebElement darktheme = driver.findElement(By.xpath("//*[text()='Dark theme: On']"));
        darktheme.click();

        scenario.createNode(Then.class, "User turn mode").pass("pass");
        logger.trace("Now Access: "+driver.getCurrentUrl());

        try {
            Desktop.getDesktop().browse(new File("P5.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        reports.flush();

    }
}
