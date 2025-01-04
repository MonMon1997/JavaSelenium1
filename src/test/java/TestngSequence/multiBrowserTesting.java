package TestngSequence;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class multiBrowserTesting {

    WebDriver driver = null;

    @BeforeTest
    @Parameters("browserName")
    public void setup(String browserName){
        System.out.println("Browser name is :"+ browserName);

        if (browserName.equalsIgnoreCase("Chrome")){
            System.setProperty("chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")){
            System.setProperty("edge", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
    }

    @Test
    public void t1() throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Where is my mind");
        Thread.sleep(2000);
    }

    @Test
    public void t2(){
        driver.getTitle();
    }

    @AfterTest
    public void setup2(){
        driver.close();
        driver.quit();
    }
}
