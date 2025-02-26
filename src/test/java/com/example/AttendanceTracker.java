package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AttendanceTracker {
    EdgeDriver driver;
    @BeforeTest
    public void openBrowser(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);

    }
    @Test
    public void test_read_attendance(){
        String URL = "https://hiretek.greythr.com/uas/portal/auth/login?login_challenge=f42272a85cf64220b0ac367b8885bee9";
        driver.get(URL);
        WebElement loginId = driver.findElement(By.xpath("//input[@id='username']"));
        loginId.sendKeys("ZENEMP20230828192");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("Ravindra@1026");
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text() =' Home ']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement attendance = (WebElement)js.executeScript("")





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
