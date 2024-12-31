package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class P4_YoutubeLoop {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\web_automation_1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        String url = driver.getCurrentUrl();

        do {
            driver.get("https://www.youtube.com/watch?v=91rrbNAJqQ8&list=PLFbqC_gGwNLn5DQ_E0awLwikA4MIv-4rY&index=12");
            WebElement playbutton = driver.findElement(By.xpath("//button[@class='ytp-play-button ytp-button'][@title='Play (k)']"));

            while (playbutton.isDisplayed()) {
                playbutton.click();
                break;
            }

            try {
                Thread.sleep(240000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
             while (!(url.equalsIgnoreCase("https://www.youtube.com/watch?v=91rrbNAJqQ8&list=PLFbqC_gGwNLn5DQ_E0awLwikA4MIv-4rY&index=12")));

        driver.close();
        driver.quit();
    }
}
