package com.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DataCopying {

    EdgeDriver driver;

    @BeforeTest
    public void openBrowser() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }

    @Test
    public void test_get_bihar_data() {
        String URL = "https://voters.eci.gov.in/download-eroll?stateCode=S04";
        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name = 'district']")));

        WebElement district = driver.findElement(By.xpath("//select[@name = 'district']"));
        district.click();

        WebElement option = driver.findElement(By.xpath("//select[@name = 'district']/option[2]"));
        option.click();

        WebElement acElement = driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[1]"));
        acElement.click();

        WebElement acOption = driver.findElement(By.xpath("//div[text() = '46 - Narpatganj']"));
        acOption.click();

        // Wait for the table to load after selection
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@role='table']/tbody")));

        WebElement maxOption = driver.findElement(By.xpath("//select[@class=' search-bar search-bar2']"));
        maxOption.click();

        WebElement maxOptionSelect = driver.findElement(By.xpath("//option[@value='50']"));
        maxOptionSelect.click();

        WebElement lastPage = driver.findElement(By.xpath("//button[normalize-space()='>>']"));
        lastPage.click();
        WebElement pageCount = driver.findElement(By.xpath("(//span[@class='m-2 control-btn2'])[1]"));
        int pageNumber = Integer.parseInt(pageCount.getText());
        System.out.println(pageNumber);

        WebElement firstPage = driver.findElement(By.xpath("//button[normalize-space()='<<']"));
        firstPage.click();
        WebElement nextPageElement = driver.findElement(By.xpath("//button[normalize-space()='>']"));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Voter Data");

        int rowCount = 0;

        for (int k = 1; k <= pageNumber; k++) {
            if (firstPage.isDisplayed()) {


                // Get all rows in the table
                List<WebElement> rows = driver.findElements(By.xpath("//table[@role='table']/tbody/tr"));

                // Loop through each row
                for (int i = 1; i <= rows.size(); i++) {
                    // Get all columns in the current row
                    Row excelRow = sheet.createRow(rowCount++);
                    List<WebElement> cols = driver.findElements(By.xpath("//table[@role='table']/tbody/tr[" + i + "]/td"));

                    // Loop through each column in the row
                    for (int j = 1; j <= cols.size(); j++) {
                        String dynamicXpath = "//table[@role='table']/tbody/tr[" + i + "]/td[" + j + "]";
                        String data = driver.findElement(By.xpath(dynamicXpath)).getText();
                        System.out.print(data + "\t");
                        Cell cell = excelRow.createCell(j - 1);
                        cell.setCellValue(data);
                    }
                    System.out.println();  // Move to the next line after each row
                }
                nextPageElement.click();
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream(new File("voter_data.xlsx"))) {
            workbook.write(fileOut);
            System.out.println("Data written to Excel file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}