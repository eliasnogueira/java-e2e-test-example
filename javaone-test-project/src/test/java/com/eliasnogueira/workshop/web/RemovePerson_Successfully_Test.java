package com.eliasnogueira.workshop.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemovePerson_Successfully_Test {

    @Test
    public void testeRemoverPessoa() {
        System.setProperty("webdriver.chrome.driver", "/Users/eliasnogueira/Selenium/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost:8888/javaone");

        // search for a previously added person
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
        driver.findElement(By.id("search")).sendKeys("Daenerys Mother of Dragons");

        // wait for the next page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("remove")));

        // click on edit
        driver.findElement(By.id("remove")).click();

        // first we need to focus on alert
        Alert alerta = driver.switchTo().alert();

        // assert if this is the right alert
        assertEquals("Are you sure?", alerta.getText());

        // accept the removal
        alerta.accept();

        // clear the search field
        driver.findElement(By.id("search")).clear();

        // assert that person no longer exist on the list
        String pageContent = driver.getPageSource();
        assertFalse(pageContent.contains("Daenerys Mother of Dragons"));
        
        // fecha o browser
        driver.quit();
    }

}
