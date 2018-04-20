package com.eliasnogueira.workshop.mobile;

import static org.testng.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.annotations.Test;

/**
 * Alter a person and validate if the changed was applied
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class AlterPerson_Sucessfully_Test {

    @Test
    public void alterAPerson_Successfully() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.eliasnogueira.workshop");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "activities.ListActivity");

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // search for a person previously added
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/search_button")));
        driver.findElement(By.id("android:id/search_button")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("Jon Snow");

        // get the text of the person returned and compare if is the correct person
        MobileElement item = driver.findElement(By.id("android:id/text1"));
        String texto = item.getText();
        assertEquals("Jon Snow", texto);

        // click on the person to change
        item.click();

        // change the person's name
        MobileElement nomePessoa = driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_nome"));
        nomePessoa.clear();
        nomePessoa.sendKeys("Jon Sand");
        driver.findElement(By.id("com.eliasnogueira.workshop:id/button")).click();

        // search for the person
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/search_button")));
        driver.findElement(By.id("android:id/search_button")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("Jon Sand");

        // assert the same person with the name changed
        item = driver.findElement(By.id("android:id/text1"));
        texto = item.getText();
        assertEquals("Jon Sand", texto);

        driver.quit();
    }

}
