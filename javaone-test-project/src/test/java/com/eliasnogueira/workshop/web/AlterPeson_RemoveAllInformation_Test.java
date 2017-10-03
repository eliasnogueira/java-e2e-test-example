package com.eliasnogueira.workshop.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlterPeson_RemoveAllInformation_Test {

    @Test
    public void removeAllInformation_TryToSave() {
        System.setProperty("webdriver.chrome.driver", "/Users/eliasnogueira/Selenium/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost:8888/javaone");

        // search for a person
        By searchInput = By.id("search");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Edson Yanaga");

        // wait for the fields
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

        // click on edit
        driver.findElement(By.id("edit")).click();

        // wait for the next fields
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("back")));
        By saveButton = By.cssSelector(".w3-btn.w3-teal");

        // find the fields and puts them into variables to clear they before change
        // assert that save button is disabled
        driver.findElement(By.id("name")).clear();
        assertEquals("true", driver.findElement(saveButton).getAttribute("disabled"));
        
        driver.findElement(By.name("address")).clear();
        assertEquals("true", driver.findElement(saveButton).getAttribute("disabled"));
        
        driver.findElement(By.cssSelector("input[ng-model='post.hobbies']")).clear();
        assertEquals("true", driver.findElement(saveButton).getAttribute("disabled"));

        // close the browser
        driver.quit();
    }

}
