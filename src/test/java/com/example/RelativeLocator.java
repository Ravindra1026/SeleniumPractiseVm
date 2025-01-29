package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


import java.net.URL;

public class RelativeLocator {
    EdgeDriver driver;

    @BeforeTest
    public void openBrowser() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
    @Test
            public void test_relative_locator(){
        String URL = "https://admin.votermood.in/";
        driver.get(URL);
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("admin@gmail.com");
        WebElement password = driver.findElement(with(By.tagName("input")).below(email));
        password.sendKeys("Admin@123");
        WebElement emailLabel = driver.findElement(with(By.tagName("label")).near(email));
        String emailLabelText = emailLabel.getText();
        Assert.assertEquals(emailLabelText,"Email Id/ Mobile Number\n" +
                "*");
        WebElement signInButton = driver.findElement(with(By.tagName("button")).below(password));
        signInButton.click();

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
