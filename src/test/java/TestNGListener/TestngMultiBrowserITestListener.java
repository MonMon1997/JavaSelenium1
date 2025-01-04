package TestNGListener;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

@Listeners (TestngMultiBrowser_ITestListener.class)
public class TestngMultiBrowserITestListener {

    WebDriver driver = null;

    @BeforeClass
    @Parameters ("browser")
    public void set1(String browserName){
        if (browserName.equalsIgnoreCase("Chrome")){
            System.setProperty("Chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
             driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")){
            System.setProperty("Edge", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\msedgedriver.exe");
             driver = new EdgeDriver();
        }
    }

    @Test
    public void t1(){
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.clear();
        search.sendKeys("Power", Keys.RETURN);
    }

    @Test
    public void t2(){
        driver.navigate().back();
    }

    @AfterTest
    public void set2(){
        driver.close();
        driver.quit();

    }
}
