package com.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicAuth {
    EdgeDriver driver;
    @BeforeTest
    public void openBrowser(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);

    }
    @Test
    public void test_basic_auth(){
        String URL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath("//div[@id='content']/div/p")));

//        Alert alert = driver.switchTo().alert();
//        alert.sendKeys("admin");
//        alert.sendKeys("admin");
//        alert.accept();
        String successTextElement = driver.findElement(By.xpath("//div[@id='content']/div/p")).getText();
        //String str = successTextElement.getText();
        String expectedString = "Congratulations! You must have the proper credentials.";
        String actualString = driver.getPageSource();
        Assert.assertTrue(actualString.contains(expectedString));
        Assert.assertTrue(successTextElement.contains(expectedString));




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
