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
public class AlterPerson_RemoveAllInformation_Test {

    private AndroidDriver<MobileElement> driver;
    
    @Test
    public void removeAllInformation_TryToSave() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.eliasnogueira.workshop");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "activities.ListActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // search for a person previously added
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/search_button")));
        driver.findElement(By.id("android:id/search_button")).click();
        driver.findElement(By.id("android:id/search_src_text")).sendKeys("Edson Yanaga");

        // find a person previously inserted
        driver.findElement(By.id("android:id/text1")).click();

        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_nome")).clear();
        clickOnSaveButton_AssertAlertMessage_ClickOK();
        
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_endereco")).clear();
        clickOnSaveButton_AssertAlertMessage_ClickOK();
        
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_hobbies")).clear();
        clickOnSaveButton_AssertAlertMessage_ClickOK();

        driver.quit();
    }
    
    private void clickOnSaveButton_AssertAlertMessage_ClickOK() {
        driver.findElement(By.id("com.eliasnogueira.workshop:id/button")).click();
        assertEquals("Fill all required fields!", driver.findElement(By.id("android:id/message")).getText());
        driver.findElement(By.id("android:id/button1")).click();
    }

}
