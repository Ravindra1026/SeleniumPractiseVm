package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SvgElemet {
    EdgeDriver driver;

    @BeforeTest
    public void openBrowser() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        driver = new EdgeDriver(edgeOptions);
    }
    @Test
    public void test_svg_element(){
        String URL = "https://votermood.com/blog/one-nation-one-election-history-to-present";
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //js.executeScript("arguments[0].scrollIntoView(true);", svgElements);
        js.executeScript("window.scrollBy(0, 800);");
        List <WebElement> svgElements = driver.findElements(By.xpath("//*[name() = 'svg'][1]"));
        int svgCount = svgElements.size();
        Actions actions = new Actions(driver);
        actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        //actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        //svgElement.click();
        //svgCount(2).click();
        System.out.println(svgCount);
       // actions.moveToElement(svgElements).click().build().perform();

        //svgElements.get(1).click();




        if(svgElements.size()>=3){
            WebElement firstSvgElement = svgElements.get(2);
            //actions.moveToElement(firstSvgElement).click().perform();


        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
