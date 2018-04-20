package com.eliasnogueira.workshop.mobile;

import static org.testng.Assert.*;

import java.io.File;
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
 * Add a person
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class AddPerson_Successfully_Test {

    @Test
    public void addPerson_Successfully() throws MalformedURLException {

        /* not used but you can use the app to install before the test execution
         * you need to add the following line and remove the APP_PACKAGe and APP_ACTIVITY lines
         *    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
         */
        File app = new File("src/main/resources/app/workshop.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.eliasnogueira.workshop");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "activities.ListActivity");

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // click on add button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.eliasnogueira.workshop:id/fab")));
        driver.findElement(By.id("com.eliasnogueira.workshop:id/fab")).click();

        // fill data and submit
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_nome")).sendKeys("Jon Snow");
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_endereco")).sendKeys("The wall");
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_hobbies")).sendKeys("Know nothing");
        driver.findElement(By.id("com.eliasnogueira.workshop:id/button")).click();

        // after fill, search for the person created
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/search_button")));
        driver.findElement(By.id("android:id/search_button")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("Jon Snow");

        // assert the person returned
        String texto = driver.findElement(By.id("android:id/text1")).getText();
        assertEquals("Jon Snow", texto);

        driver.quit();
    }
}
