package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class BiharDataTable {

    EdgeDriver driver;

    @BeforeTest
    public void openBrowser() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
    @Test
    public void test_get_bihar_data(){
        String URL = "https://voters.eci.gov.in/download-eroll?stateCode=S04";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name = 'district']")));
        WebElement district = driver.findElement(By.xpath("//select[@name = 'district']"));
        district.click();
        WebElement option = driver.findElement(By.xpath("//select[@name = 'district']/option[2]"));
        option.click();

        WebElement acElement = driver.findElement(By.xpath("(//div[@class=' css-hlgwow'])[1]"));
        acElement.click();
        WebElement acOption = driver.findElement(By.xpath("//div[text() = '46 - Narpatganj']"));
        acOption.click();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> rows = driver.findElements(By.xpath("//table[@role = 'table']/tbody/tr[1]"));
        List<WebElement> cols = driver.findElements(By.xpath("//table[@role = 'table']/tbody/tr[1]/td"));

        // //table[@role = 'table']/tbody/tr[1]/td[1]
        String partOne = "//table[@role = 'table']/tbody/tr[";
        String partTwo = "]/td[";
        String partThree = "]";

        for(int i = 2; i<=rows.size(); i++){
            for(int j=1; j<= cols.size(); j++) {
                String dynamicXpath = partOne + i + partTwo + j + partThree;
                String data = driver.findElement(By.xpath(dynamicXpath)).getText();
                System.out.println(data);
            }
        }

    }


    @AfterTest
    public void closeBrowser() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}


