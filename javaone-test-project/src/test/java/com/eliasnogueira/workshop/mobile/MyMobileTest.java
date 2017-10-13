package com.eliasnogueira.workshop.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MyMobileTest {

    @Test
    public void myAndroidTest() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        driver.findElement(By.id("")).click();

    }
}
