package com.eliasnogueira.workshop.mobile;

import static org.testng.Assert.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Remove a person
 * Is required to long press the person on the list to remove it
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class RemovePersonTest {
    
    @Test
    public void removerPessoa() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.eliasnogueira.workshop");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "activities.ListActivity");

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // search for the person
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/search_button")));
        driver.findElement(By.id("android:id/search_button")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("Jon Sand");
        
        // get the mobile component
        MobileElement item = driver.findElement(By.id("android:id/text1"));
        
        // do a long press on the person
        TouchAction touch = new TouchAction(driver);
        touch.longPress(item).perform();
        
        // first the message to continue is displayed
        assertEquals("Are you sure to delete this person?", driver.findElement(By.id("android:id/message")).getText());
        driver.findElement(By.id("android:id/button1")).click();
        
        // assert the success message after deletion
        assertEquals("Person was successfully removed!", driver.findElement(By.id("android:id/message")).getText());
        driver.findElement(By.id("android:id/button3")).click();
        
        driver.quit();
    }

}
