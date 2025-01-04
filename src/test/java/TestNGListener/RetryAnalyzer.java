package TestNGListener;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RetryAnalyzer implements IRetryAnalyzer {

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

    WebDriver driver;

    @BeforeTest
    public void set1(){
        System.setProperty("Chorme", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void s1(){
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("塵大師", Keys.RETURN);
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void s2(){
        driver.navigate().back();

        Assert.assertTrue(false);
    }

    @AfterTest
    public void set2(){
        driver.close();
        driver.quit();

    }

}
