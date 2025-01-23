package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class TableHandling {
    @Test
    public void test_table_handling(){
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

        EdgeDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://awesomeqa.com/webtable.html");

        // //table[@id = 'customers']/tbody/tr[2]/td[1]
        // //table[@id = 'customers']/tbody/tr
        // //table[@id = 'customers']/tbody/tr[2]/td

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id = 'customers']/tbody/tr"));
        int rowCount = rows.size();
        List<WebElement> cols = driver.findElements(By.xpath("//table[@id = 'customers']/tbody/tr[2]/td"));
        int colCount = cols.size();
        System.out.println(rowCount);
        System.out.println(colCount);

        String firstPart = "//table[@id = 'customers']/tbody/tr[";
        String secondPart = "]/td[";
        String thirdPart = "]";


       for(int i = 2; i<=rows.size(); i++){
           for(int j=1; j<=cols.size(); j++){
               String dynamic_xpath = firstPart + i + secondPart + j + thirdPart;
               //String data = driver.findElements(By.xpath(dynamic_xpath)).getText();

               String data =  driver.findElement(By.xpath(dynamic_xpath)).getText();

              // System.out.println(data);
               if (data.contains("Helen Bennett")){
                   String country_path = dynamic_xpath + "/following-sibling::td";
                   String country_company = dynamic_xpath + "/preceding-sibling::td";
                   String country_text = driver.findElement(By.xpath(country_path)).getText();
                   String company_text = driver.findElement(By.xpath(country_company)).getText();

                   System.out.println("========================================================");
                   System.out.println("Helen Bennett is in- " + country_text);
                   System.out.println("Helen Bennett is in- " + company_text);
                   System.out.println("========================================================");

               }
           }


       }
        driver.quit();
    }
}
