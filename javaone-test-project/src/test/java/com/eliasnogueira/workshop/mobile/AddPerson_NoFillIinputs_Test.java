package com.eliasnogueira.workshop.mobile;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Add a person
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
public class AddPerson_NoFillIinputs_Test {

    private AndroidDriver<MobileElement> driver = null;
    
    @Test
    public void addPerson_NoFillInputs_ExpectAlertMessage() throws MalformedURLException {

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

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        // click on add button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.eliasnogueira.workshop:id/fab")));
        driver.findElement(By.id("com.eliasnogueira.workshop:id/fab")).click();

        // try to save without fill all inputs
        clickOnSaveButton_AssertAlertMessage_ClickOK();
        
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_nome")).sendKeys("Jon Snow");
        clickOnSaveButton_AssertAlertMessage_ClickOK();
        
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_endereco")).sendKeys("The wall");
        clickOnSaveButton_AssertAlertMessage_ClickOK();
        
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_endereco")).clear();
        driver.findElement(By.id("com.eliasnogueira.workshop:id/txt_hobbies")).sendKeys("Know nothing");
        clickOnSaveButton_AssertAlertMessage_ClickOK();

        driver.quit();
    }
    
    private void clickOnSaveButton_AssertAlertMessage_ClickOK() {
        driver.findElement(By.id("com.eliasnogueira.workshop:id/button")).click();
        assertEquals("Fill all required fields!", driver.findElement(By.id("android:id/message")).getText());
        driver.findElement(By.id("android:id/button1")).click();
    }
}
