package com.eliasnogueira.workshop.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Add a person
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class AddPerson_NoFillAllInputs_Test {

    @Test
    public void addPerson_NoFillIputs_ValidateEnableDisableSaveButton() {
        /*
         * You'll need to donwload chromedriver from the link bellow and 
         * replace the second parameter (complete file location and name) 
         * 
         * https://sites.google.com/a/chromium.org/chromedriver/
         */
        System.setProperty("webdriver.chrome.driver", "/Users/eliasnogueira/Selenium/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost:8888/javaone");

        By addButton  = By.id("add");
        wait.until(ExpectedConditions.presenceOfElementLocated(addButton));
        driver.findElement(addButton).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("back")));
        By saveButton = By.cssSelector(".w3-btn.w3-teal");
        
        driver.findElement(By.id("name")).sendKeys("Edson Yanaga");
        assertEquals("true", driver.findElement(saveButton).getAttribute("disabled"));
        
        driver.findElement(By.name("address")).sendKeys("Dragonstone");
        assertEquals("true", driver.findElement(saveButton).getAttribute("disabled"));
        
        driver.findElement(By.cssSelector("input[ng-model='post.hobbies']")).sendKeys("Break Chains");
        assertNull(driver.findElement(saveButton).getAttribute("disabled"));
        
        driver.quit();

    }
}
