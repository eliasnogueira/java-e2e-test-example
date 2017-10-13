package com.eliasnogueira.workshop.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnAddButton() {
        driver.findElement(By.id("add")).click();
    }

}
