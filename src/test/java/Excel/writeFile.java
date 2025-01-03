package Excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class writeFile {


    public static void main(String[] args) throws IOException {


        // 1. 創建 WebDriver（這裡使用 ChromeDriver 為例）
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        // 2. 打開一個網站
        driver.get("https://www.google.com/");


        // 3. 執行測試，這裡假設測試目的是檢查頁面標題是否正確
        String pageTitle = driver.getTitle();
        boolean testResult = pageTitle.equals("Example Domain");


        // 4. 創建 Excel 文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");


        // 5. 創建標題行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test Case");
        headerRow.createCell(1).setCellValue("Expected Result");
        headerRow.createCell(2).setCellValue("Actual Result");
        headerRow.createCell(3).setCellValue("Test Passed");


        // 6. 寫入測試結果
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue("Check Page Title");
        row.createCell(1).setCellValue("Example Domain");
        row.createCell(2).setCellValue(pageTitle);
        row.createCell(3).setCellValue(testResult ? "Yes" : "No");


        // 7. 將數據保存到文件
        FileOutputStream fileOut = new FileOutputStream(new File("C:\\Program Files\\Intellij\\IdeaProjects\\Web_Automation_2\\excel\\output.xlsx"));
        workbook.write(fileOut);
        fileOut.close();


        // 8. 關閉 WebDriver
        driver.quit();


        // 9. 關閉工作簿
        workbook.close();


    }
}
