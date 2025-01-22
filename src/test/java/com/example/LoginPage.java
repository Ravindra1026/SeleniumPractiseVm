package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPage {

        @Test
        public void test_verify_Login_Functionality () {
            //for(int i=0; i<100; i++) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                EdgeDriver driver = new EdgeDriver(edgeOptions);
                driver.get("https://stagingapp.votermood.in/login");

                WebElement emailElement = driver.findElement(By.xpath("//input[@id = \"email\"]"));
                emailElement.sendKeys("rahul02@mailinator.com");
                WebElement password = driver.findElement(By.xpath("//input[@id = \"password\"]"));
                password.sendKeys("Test@123");
                WebElement signIn = driver.findElement(By.xpath("//button[text() = 'Sign In']"));
                signIn.click();


                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role = 'status']")));

                WebElement successMessage = driver.findElement(By.xpath("//li[@role = 'status']"));
                Assert.assertEquals(successMessage.getText(), "Successfully Login on VoterMood");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
               // System.out.println("Number of times browser run ==>" + i);
                driver.quit();
            //}
    }

}
