package GoogleWalkthrough;

import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.model.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class googleSearch_Junit {

    WebDriver driver;

    @BeforeTest
    public void set1(){
        System.setProperty("webdriver", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void t1(){
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("you are good", Keys.RETURN);
    }

    @Test
    public void t2(){
        driver.get("https://monmon1997.github.io/FontAwsome/");
    }

    @AfterTest
    public void set3(){
        driver.close();
        driver.quit();
    }
}
