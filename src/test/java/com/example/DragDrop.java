package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragDrop {
    EdgeDriver driver;
    @BeforeTest
    public void openBrowser(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
    @Test
    public void test_drag_drop(){
        String URL = "https://the-internet.herokuapp.com/drag_and_drop";
        driver.get(URL);
        WebElement elementA = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement elementB = driver.findElement(By.xpath("//div[@id='column-b']"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementA, elementB).build().perform();

    }
    @AfterTest
    public void closeBrowser(){
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
