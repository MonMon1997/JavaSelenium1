package PlayGround;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class playGround {

    WebDriver driver =null;
    ExtentReports reports;
    ExtentSparkReporter spark;
    ExtentTest test ;
    Logger logger;

    public static class RetryFailure_AListener implements IRetryAnalyzer {


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
    }

    public static Object[][] excel(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);


        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();


        Object[][] data = new Object[rowCount-1][colCount];


        for(int i = 1; i< rowCount;i++){
            Row row = sheet.getRow(i);
            for (int j=0; j< colCount;j++){
                data[i-1][j] = row.getCell(j).toString();
            }
        }


        workbook.close();
        inputStream.close();


        return data;
    }



    //TestNg Before Test
    @BeforeTest (groups = {"UAT", "Regression"})
    @Parameters ("browser")
    public void set1(@Optional("Chrome") String browserName){
        //Extent Reports
        reports = new ExtentReports();
        spark = new ExtentSparkReporter("P6.html");
        reports.attachReporter(spark);

        test = reports.createTest("Selenium Walkthrough");

        //Log4J2
        logger = LogManager.getLogger(playGround.class);
        logger.info("User start the RUN case");

        //Set Up driver
        if (browserName.equalsIgnoreCase("Chrome")){
            System.setProperty("chrome", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            System.setProperty("Edge", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.navigate().to("https://www.selenium.dev/");
    }

    //UAT Test 1 - Click Project Structure and Governance
    @Test (groups = {"UAT", "Regression"}, priority = 1, retryAnalyzer = RetryFailure_AListener.class)
    public void t1(){
        try {
            driver.findElement(By.id("navbarDropdown")).click();
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[1]/div/a[2]")).click();
            test.pass("Pass - Click Project Structure and Governance");
            logger.info("Finish UAT Test 1");
        } catch (Exception e){
          test.fail("Fail - Click Project Structure and Governance");
        }
    }

    //UAT Test 2 - Click Download Button
    @Test (groups = {"UAT", "Regression"}, priority = 2, retryAnalyzer = RetryFailure_AListener.class)
    public void t2(){
        try {
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[2]/a/span")).click();
            test.pass("Pass - Click Download Button");
            logger.info("Finish UAT Test 2");
        } catch (Exception e){
            test.fail("Fail - Click Download Button");
        }
    }

    //UAT Test 3 - Turn it into Japanese
    @Test (groups = {"UAT"}, priority = 3, retryAnalyzer = RetryFailure_AListener.class)
    public void t3(){
        try {
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[7]/div/a")).click();
            driver.findElement(By.xpath("//*[@id=\"main_navbar\"]/ul/li[7]/div/ul/li[3]/a")).click();
            test.pass("Pass - Turn it into Japanese");
            logger.info("Finish UAT Test 3");

            Assert.assertTrue(false);
        } catch (Exception e){
            test.fail("Fail - Turn it into Japanese");
        }
    }

    //UAT Test 4 - Turn it into Japanese
    @Test (groups = {"Regression"},priority = 4, retryAnalyzer = RetryFailure_AListener.class)
    public void t4(){
        try {
            driver.navigate().back();
            test.pass("Pass - Navigate Back");
            logger.info("Finish Test 4");
        } catch (Exception e){
            test.fail("Fail - Navigate Back");
        }
    }

    //UAT Test 5 - Click Document Button
    @Ignore
    @Test (groups = {"UAT, Regression"}, retryAnalyzer = RetryFailure_AListener.class)
    public void t5(){
        try {
            driver.findElement(By.xpath("/html/body/div/main/div[1]/div/div/a[1]"));
            test.pass("Pass - Click Document Button");
            logger.info("Finish Test 5");
        } catch (Exception e){
            test.fail("Fail - Click Document Button");
        }
    }

    //UAT Test 6 - Input Excel
    @Test (groups = {"UAT, Regression"}, retryAnalyzer = RetryFailure_AListener.class, priority = 5)
    public void t6(){
        try {
            //Properties file
            Properties properties = new Properties();

            FileOutputStream outputStream = new FileOutputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\PlayGround\\config.properties");
            properties.setProperty("search", "Red Pills or Blue Pills");
            properties.store(outputStream,null);

            FileInputStream inputStream = new FileInputStream("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\src\\test\\java\\PlayGround\\config.properties");
            properties.load(inputStream);
            String searchfield = properties.getProperty("search");
            System.out.println("Your input is:" + searchfield);

            driver.navigate().to("https://www.google.com/");
            WebElement search = driver.findElement(By.name("q"));
            search.sendKeys(searchfield);

            test.pass("Pass - Input Excel");
            logger.info("Finish Test 6");
        } catch (Exception e){
            test.fail("Fail - Input Excel");
        }
    }

    //UAT Test 7 - enterUsername
    @Test (groups = {"UAT, Regression"}, retryAnalyzer = RetryFailure_AListener.class, priority = 6)
    public void t7(){
        try {
            String filePath = "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\excel\\test.xlsx";
            Object[][] excelData = excel(filePath);

            driver.navigate().to("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Faccounts.google.com%2F&followup=https%3A%2F%2Faccounts.google.com%2F&ifkv=AeZLP994VvkckXPWT43MmTq3NPx8D6u2DmHd3boLHi2e1g-aTnrU32tlaDgdylOSQlhczVBG-jakyA&passive=1209600&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S1479746831%3A1736113023406101&ddm=1");

            for (Object[]row:excelData) {
                String user = (String) row[0];

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"identifierId\"]")
                ));

                searchInput.clear();
                searchInput.sendKeys(user);
                System.out.println("Now test:"+ user);
            }

            test.pass("Pass - enterUsername");
            logger.info("Finish Test 7");
        } catch (Exception e){
            test.fail("Fail - enterUsername");
        }
    }

    //Flush and Open the report
    @AfterTest (groups = {"UAT, Regression"})
    public void set2(){

        //Open the ExtentsReport
        try {
            Desktop.getDesktop().browse(new File("P6.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("User END the case");
        reports.flush();
    }
}
