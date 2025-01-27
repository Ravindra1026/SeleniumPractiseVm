package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;


public class Table01 {
    @Test
    public void test_table_check(){

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");

        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("https://stagingclient.votermood.in/login");

        WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("akshita.jangid@zentekinfosoft.com");

        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("Akshita@123456789");

        WebElement signIn = driver.findElement(By.xpath("//button[@type='submit']"));
        signIn.click();

        //WebElement dashboard = driver.findElement(By.xpath("//span[text() = 'Dashboard']"));
        //System.out.println(dashboard.getText());

       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //wait.until(ExpectedConditions.visibilityOf(dashboard));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement boothList = driver.findElement(By.xpath("//p[text() = 'Booth List']"));
        boothList.click();


        String firstPart = "//table[@class='w-full caption-bottom text-sm']/tbody/tr[";
        String secondPart = "]/td[";
        String thirdPart = "]";
        int row = driver.findElements(By.xpath("//table[@class='w-full caption-bottom text-sm']/tbody/tr")).size();
        int col = driver.findElements(By.xpath("//table[@class='w-full caption-bottom text-sm']/tbody/tr[1]/td")).size();



        for(int i = 1; i<= row; i++){
            for(int j=2; j<=col; j++){
                String dynamicXpath = firstPart + i + secondPart + j + thirdPart;
                String data = driver.findElement(By.xpath(dynamicXpath)).getText();
                System.out.println(data);
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}
