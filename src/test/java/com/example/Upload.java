package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Upload {
    EdgeDriver driver;
    @BeforeTest
    public void openBrowser(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
    @Test
    public void test_upload_file(){
        String URL = "https://the-internet.herokuapp.com/upload";
        driver.get(URL);
        WebElement chooseFile = driver.findElement(By.xpath("//input[@id='file-upload']"));
        String filePath = "C:\\Users\\r.singh\\IdeaProjects\\SeleniumPractiseVm\\src\\test\\java\\com\\example\\lion.jpg";
        chooseFile.sendKeys(filePath);
        WebElement upload = driver.findElement(By.xpath("//input[@id='file-submit']"));
        upload.click();

    }

    @AfterTest
    public void closeBrowser(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
