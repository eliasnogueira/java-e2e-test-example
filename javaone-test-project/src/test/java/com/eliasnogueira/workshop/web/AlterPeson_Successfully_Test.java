package com.eliasnogueira.workshop.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlterPeson_Successfully_Test {

    @Test
    public void alterPersonSuccessfully() {
        System.setProperty("webdriver.chrome.driver", "/Users/eliasnogueira/Selenium/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost:8888/javaone");

        // search for a person
        By searchInput = By.id("search");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Daenerys Targaryen");

        // wait for the fields
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

        // do some assertions
        assertEquals("Daenerys Targaryen", driver.findElement(By.id("name")).getText());
        assertEquals("Dragonstone", driver.findElement(By.id("address")).getText());
        assertEquals("Break Chains", driver.findElement(By.id("hobbies")).getText());

        // click on edit
        driver.findElement(By.id("edit")).click();

        // wait for the next fields
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("back")));
        
        // find the fields and puts them into variables to clear they before change
        WebElement name = driver.findElement(By.id("name"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement hobbies = driver.findElement(By.cssSelector("input[ng-model='post.hobbies']"));

        // change all the fields
        name.clear();
        name.sendKeys("Daenerys Mother of Dragons");

        address.clear();
        address.sendKeys("King's Landing");

        hobbies.clear();
        hobbies.sendKeys("Mother of Dragons");

        // click on save
        driver.findElement(By.cssSelector(".w3-btn.w3-teal")).click();

        // wait for the next fields
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit")));

        // assert search for the itens changed anywhere on the screen
        String pageContent = driver.getPageSource();
        assertTrue(pageContent.contains("Daenerys Mother of Dragons"));
        assertTrue(pageContent.contains("King's Landing"));
        assertTrue(pageContent.contains("Mother of Dragons"));

        // close the browser
        driver.quit();
    }

}
