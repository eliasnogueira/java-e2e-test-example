package com.eliasnogueira.workshop.web;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebTest {

    @Test
    public void createPersonTest() {

        System.setProperty("webdriver.chrome.driver",
                "/Users/eliasnogueira/Selenium/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8888/javaone");

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("Edson");


    }

}
