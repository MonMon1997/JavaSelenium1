package Project;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


//var element = $x("//a[text()='About']")[0]; element.click();
public class P3_2_googlesearchpagetesting {


    //Set the browser in null due to it will choose browser to run
    WebDriver driver = null;
    WebElement combobox_v1;
    Logger logger;
    ExtentReports reports;
    ExtentTest automation_test1;


    @Parameters ("browserName")
    @BeforeTest
    void setupbrowser(String browserName){
        //Parellel Browser Section
        System.out.println("Browser name is : "+ browserName);
        System.out.println("Thread id :"+Thread.currentThread().getId()); //Check the thread


        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\web_automation_1\\chromedriver.exe");
            driver = new ChromeDriver();


            //ExtentReports
            reports = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("index3.html");
            reports.attachReporter(spark);
            automation_test1 = reports.createTest("Automation test 1 Chrome").assignAuthor("Edmond").assignDevice("Web");


        } else if (browserName.equalsIgnoreCase("Edge")){
            System.setProperty("webdriver.edge", "C:\\Program Files\\Intellij\\IdeaProjects\\web_automation_1\\msedgedriver.exe");
            driver = new EdgeDriver();


            //ExtentReports
            reports = new ExtentReports();
            ExtentSparkReporter spark2 = new ExtentSparkReporter("index4.html");
            reports.attachReporter(spark2);
            automation_test1 = reports.createTest("Automation test 1 Edge").assignAuthor("Edmond").assignDevice("Web");
        }


        //Browser Layout Setup
        driver.manage().window().maximize();


        //Log4j2
        logger = LogManager.getLogger(P3_2_googlesearchpagetesting.class);


        //DesiredCap and Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setAcceptInsecureCerts(true);


    }


    @Test (priority = 1)
    void googlesearch_searchbox(){
        try {
            //Driver action
            driver.get("https://www.google.com/");


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            combobox_v1 = driver.findElement(By.name("q"));
            combobox_v1.sendKeys("Madiwala",Keys.BACK_SPACE);
            combobox_v1.sendKeys("Edamane");
            combobox_v1.clear();
            driver.navigate().refresh();
            logger.trace("Now Access: " + driver.getCurrentUrl());
            automation_test1.pass("Searchbox_pass");


            //Driver action
            combobox_v1 = driver.findElement(By.name("q"));
            combobox_v1.sendKeys("Vancouver", Keys.RETURN);
            logger.trace("Now Access: "+driver.getCurrentUrl());
            driver.navigate().back();
        }catch (Exception e){
            automation_test1.fail("Searchbox_fail");
        }
    }


    @Test (priority = 2)
    void googlesearch_box_return(){
        //Driver action
        try {
            driver.get("https://www.google.com/");


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            combobox_v1 = driver.findElement(By.name("q"));
            combobox_v1.sendKeys("Madiwala",Keys.BACK_SPACE);
            combobox_v1.sendKeys("Edamane");
            combobox_v1.clear();
            driver.navigate().refresh();
            logger.trace("Now Access: " + driver.getCurrentUrl());


            //Driver action
            combobox_v1 = driver.findElement(By.name("q"));
            combobox_v1.sendKeys("Vancouver", Keys.RETURN);
            logger.trace("Now Access: "+driver.getCurrentUrl());
            driver.navigate().back();


            automation_test1.pass("Searchbox Return_pass");
        } catch (Exception e){
            automation_test1.pass("Searchbox Return_fail");
        }
    }


    //var element = $x("//a[text()='About']")[0]; element.click();
    @Test (priority = 3)
    void aboutbutton() throws InterruptedException {
        try {
            driver.get("https://www.google.com/");
            WebElement about_button = driver.findElement(By.xpath("//a[text()='About']"));
            about_button.click();
            logger.trace("Now Access: "+driver.getCurrentUrl());
            driver.navigate().back();
            automation_test1.pass("About button");
        } catch (Exception e){
            automation_test1.fail("About button");
        }
    }


    @Test (priority = 4)
    void storebutton(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement store_button = driver.findElement(By.xpath("//a[text()='Store']"));
            store_button.click();
            logger.trace("Now Access: "+driver.getCurrentUrl());
            driver.navigate().back();
            automation_test1.pass("Store button");
        } catch (Exception e){
            automation_test1.fail("Store button");
        }


    }


    @Test (priority = 5)
    void gmailbutton() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement gmailbutton = driver.findElement(By.xpath("//a[@class='gb_y'][text()='Gmail']"));
            gmailbutton.click();
            logger.trace("Now Access: " +driver.getCurrentUrl());
            driver.navigate().back();
            automation_test1.pass("Gmail button");
        } catch (Exception e) {
            automation_test1.fail("Gmail button");


        }
    }


    @Test (priority = 6)
    void imagebutton() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement imagebutton = driver.findElement(By.xpath("//a[@class='gb_y'][text()='Images']"));
            imagebutton.click();
            driver.navigate().back();
            logger.trace("Now Access: " + driver.getCurrentUrl());


            automation_test1.pass("Image button");
        } catch (Exception e) {
            automation_test1.fail("Image button");


        }
    }


    @Test (priority = 7)
    void appbutton() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement googleplaybutton = driver.findElement(By.xpath("//a[@class='gb_d'][@aria-label='Google apps']"));
            googleplaybutton.click();
            driver.navigate().back();


            logger.trace("Now Access: " + driver.getCurrentUrl());




            automation_test1.pass("App button");
        } catch (Exception e) {
            automation_test1.fail("App button");


        }


    }


    @Test (priority = 8)
    void googleplaybutton() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            //Navigate to Google
            driver.get("https://www.google.com/");


            //Click the frame
            WebElement googleplaybutton = driver.findElement(By.xpath("//a[@class='gb_d'][@aria-label='Google apps']"));
            googleplaybutton.click();


            //Relocate the target inside the new frame
            WebElement frame1 = driver.findElement(By.xpath("//iframe[@name='app']"));
            driver.switchTo().frame(frame1);


            //Successful click the Button
            WebElement newsbutton = driver.findElement(By.xpath("//span[@class='MrEfLc'][@style='background-position: 0 -348px;']"));
            newsbutton.click();
            logger.trace("Now Access: " + driver.getCurrentUrl());


            automation_test1.pass("GoogleNews button");
        } catch (Exception e) {
            automation_test1.fail("GoogleNews button");
        }
    }


    @Test (priority = 9)
    void googlemapbutton(){
        try {


            driver.get("https://www.google.com/");


            WebElement element = driver.findElement(By.xpath("//a[@class='gb_d'][@role='button']"));
            element.click();


            Thread.sleep(5000);
            WebElement frame = driver.findElement(By.xpath("//iframe[@name='app']"));
            driver.switchTo().frame(frame);


            Thread.sleep(5000);
            WebElement googlemapbutton = driver.findElement(By.xpath("//span[@class='MrEfLc'][@style='background-position: 0 -1566px;']"));
            googlemapbutton.click();


            Thread.sleep(5000);
            logger.trace("Now Access: " + driver.getCurrentUrl());


            automation_test1.pass("GoogleMap Button");
        } catch (Exception e){
            automation_test1.fail("GoogleMap Button");
        }
    }


    @Test (priority = 10)
    void loginbutton(){
        try {


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");


            WebElement loginbutton = driver.findElement(By.xpath("//a[@class='gb_va gb_nd gb_ed gb_9d']"));
            loginbutton.click();


            WebElement createaccountbutton = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][@jsname='V67aGc'][text()='Create account']"));
            createaccountbutton.click();


            WebElement usagebutton = driver.findElement(By.xpath("//span[@class='VfPpkd-StrnGf-rymPhb-b9t22c'][text()='For my personal use']"));
            usagebutton.click();


            logger.trace("Now Access: " + driver.getCurrentUrl());
            automation_test1.pass("Login Button");
        } catch (Exception e){
            automation_test1.fail("Login Button");
        }


    }


    @Test(priority = 11)
    void goodhandsbutton (){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            driver.get("https://www.google.com/");
            WebElement goodhandsbutton = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]"));
            goodhandsbutton.click();


            logger.trace("Now Access: " + driver.getCurrentUrl());
            automation_test1.pass("GoodHands Button");
        } catch (Exception e){
            automation_test1.fail("GoodHands Button");
        }
    }


    @Test (priority = 12)
    void languagelink(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement languagelink_eng = driver.findElement(By.linkText("Français"));
            languagelink_eng.click();
            Thread.sleep(5000);
            WebElement languagelink_france = driver.findElement(By.linkText("English"));
            languagelink_france.click();


            logger.trace("Now Access:"+ driver.getCurrentUrl());
            automation_test1.pass("Languagelink");
        } catch (Exception e) {
            automation_test1.fail("Languagelink");
        }
    }


    @Ignore
    @Test (priority = 13)
    void settingbutton_advancesearch(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement settingbutton = driver.findElement(By.xpath("//div[@jsname='LgbsSe'][@class='ayzqOc pHiOh'][text()='Settings']"));
            settingbutton.click();


            WebElement advancesetting = driver.findElement(By.xpath("//a[@role='menuitem'][text()='Advanced search']"));
            advancesetting.click();


            logger.trace("Now Access:"+ driver.getCurrentUrl());
            automation_test1.pass("Settingbutton");
        } catch (Exception e) {
            automation_test1.fail("Settingbutton");
        }
    }


    @Test (priority = 14)
    void settingbutton_theme(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement settingbutton = driver.findElement(By.xpath("//div[@jsname='LgbsSe'][@class='ayzqOc pHiOh'][text()='Settings']"));
            settingbutton.click();


            WebElement theme = driver.findElement(By.xpath("//div[@class='tFYjZe'][@role='link']"));
            theme.click();


            logger.trace("Now Access:"+ driver.getCurrentUrl());
            automation_test1.pass("Settingbutton");
        } catch (Exception e) {
            automation_test1.fail("Settingbutton");
        }
    }


    @Test (priority = 15)
    void photosearchbutton(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://www.google.com/");
            WebElement photosearchbutton = driver.findElement(By.xpath("//*[local-name()='svg' and @class='Gdd5U']"));
            photosearchbutton.click();




            logger.trace("Now Access:"+ driver.getCurrentUrl());
            automation_test1.pass("PhotoSearchbutton");
        } catch (Exception e) {
            automation_test1.fail("PhotoSearchbutton");
        }
    }


    @AfterTest
    void teardown() throws InterruptedException, IOException {
        Thread.sleep(10000);


        driver.close();
        driver.quit();
        reports.flush();
        Desktop.getDesktop().browse(new File("index3.html").toURI());
        Desktop.getDesktop().browse(new File("index4.html").toURI());
    }


}



