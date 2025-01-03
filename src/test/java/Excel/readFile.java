package Excel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class readFile {

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


        public static void main(String[] args) throws IOException {
            String filePath = "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\excel\\test.xlsx";
            Object[][] excelData = excel(filePath);

            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.facebook.com/login.php/");

            for(Object[]row:excelData){
                String user = (String) row[0];
                String pass = (String) row[1];

                WebElement userblank = driver.findElement(By.id("email"));
                userblank.clear();
                userblank.sendKeys(user);

                System.out.println("Now Test:"+user);

                WebElement passblank = driver.findElement(By.id("pass"));
                passblank.clear();
                passblank.sendKeys(pass);
            }
        }
    }


