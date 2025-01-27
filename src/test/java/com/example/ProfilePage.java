package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProfilePage {
    @Test
    public void test_Basic_Details (){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        EdgeDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://stagingapp.votermood.in/login");

        WebElement emailElement = driver.findElement(By.xpath("//input[@id = \"email\"]"));
        emailElement.sendKeys("officebearer02@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id = \"password\"]"));
        password.sendKeys("Test@123");
        WebElement signIn = driver.findElement(By.xpath("//button[text() = 'Sign In']"));
        signIn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text() = 'Profile']")));

        WebElement profile = driver.findElement(By.xpath("//p[text() = 'Profile']"));
        profile.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Personal Information']")));
        WebElement personalInformation = driver.findElement(By.xpath("//span[normalize-space()='Personal Information']"));
        personalInformation.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Edit']")));
        WebElement editButton = driver.findElement(By.xpath("//button[normalize-space()='Edit']"));
        editButton.click();





        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
